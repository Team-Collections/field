package dev.trigam.field.mixin.client.glowingLayers;

import com.llamalad7.mixinextras.sugar.Local;
import dev.trigam.field.FieldClient;
import dev.trigam.field.component.GlowingLayersComponent;
import dev.trigam.field.impl.FieldBannerBlockEntity;
import net.minecraft.block.entity.BannerBlockEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BannerBlockEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

// Adapted from: https://github.com/UltrusBot/glow-banners/blob/1.21/common/src/main/java/me/ultrusmods/glowingbanners/mixin/client/BannerRendererMixin.java
@Mixin( BannerBlockEntityRenderer.class )
public class RenderGlowingLayers {

    // Banner context
    @Inject(
        method = "render(Lnet/minecraft/block/entity/BannerBlockEntity;FLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;II)V",
        at = @At( value = "HEAD" )
    )
    private void getBannerData( BannerBlockEntity bannerBlockEntity, float f, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, int j, CallbackInfo ci ) {
        if ( bannerBlockEntity.hasWorld() ) {
            FieldClient.setGlowingContext( bannerBlockEntity );
        }
    }

    @Inject(
        method = "render(Lnet/minecraft/block/entity/BannerBlockEntity;FLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;II)V",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/render/block/entity/BannerBlockEntityRenderer;render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;IIFLnet/minecraft/client/render/block/entity/model/BannerBlockModel;Lnet/minecraft/client/render/block/entity/model/BannerFlagBlockModel;FLnet/minecraft/util/DyeColor;Lnet/minecraft/component/type/BannerPatternsComponent;)V",
            shift = At.Shift.AFTER
        )
    )
    private void clearBannerData( BannerBlockEntity bannerBlockEntity, float f, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, int j, CallbackInfo ci ) {
        FieldClient.setGlowingContext( null );
    }

    @ModifyArg(
        method = "renderCanvas(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;IILnet/minecraft/client/model/ModelPart;Lnet/minecraft/client/util/SpriteIdentifier;ZLnet/minecraft/util/DyeColor;Lnet/minecraft/component/type/BannerPatternsComponent;ZZ)V",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/render/block/entity/BannerBlockEntityRenderer;renderLayer(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;IILnet/minecraft/client/model/ModelPart;Lnet/minecraft/client/util/SpriteIdentifier;Lnet/minecraft/util/DyeColor;)V",
            ordinal = 0
        ),
        index = 2
    )
    private static int getBaseLight( int light ) {
        if ( FieldClient.glowingContext == null ) return light;

        GlowingLayersComponent glowingLayers = ((FieldBannerBlockEntity) FieldClient.glowingContext).field$getGlowingLayers();
        boolean isBaseGlowing = glowingLayers.isLayerGlowing( 0 );

        if ( isBaseGlowing ) return 15728880;
        else return light;
    }

    @ModifyArg(
        method = "renderCanvas(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;IILnet/minecraft/client/model/ModelPart;Lnet/minecraft/client/util/SpriteIdentifier;ZLnet/minecraft/util/DyeColor;Lnet/minecraft/component/type/BannerPatternsComponent;ZZ)V",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/render/block/entity/BannerBlockEntityRenderer;renderLayer(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;IILnet/minecraft/client/model/ModelPart;Lnet/minecraft/client/util/SpriteIdentifier;Lnet/minecraft/util/DyeColor;)V",
            ordinal = 1
        ),
        index = 2
    )
    private static int getLayerLight( int light, @Local( ordinal = 2 ) int layerIndex ) {
        if ( FieldClient.glowingContext == null ) return light;
        GlowingLayersComponent glowingLayers = ((FieldBannerBlockEntity) FieldClient.glowingContext).field$getGlowingLayers();
        boolean isLayerGlowing = glowingLayers.isLayerGlowing( layerIndex + 1 );

        if ( isLayerGlowing ) return 15728880;
        else return light;
    }

}
