package dev.trigam.field.mixin.client.glowingBanners;

import dev.trigam.field.Field;
import dev.trigam.field.component.GlowingLayersComponent;
import dev.trigam.field.impl.FieldBannerBlockEntity;
import net.minecraft.block.entity.BannerBlockEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BannerBlockEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin( BannerBlockEntityRenderer.class )
public class RenderGlowingLayers {

    @Unique
    private static GlowingLayersComponent glowingLayers = new GlowingLayersComponent();

    @Inject(
        method = "render(Lnet/minecraft/block/entity/BannerBlockEntity;FLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;II)V",
        at = @At(
            value = "HEAD"
        )
    )
    private void getBannerData( BannerBlockEntity bannerBlockEntity, float f, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, int j, CallbackInfo ci ) {
        glowingLayers = ( (FieldBannerBlockEntity) bannerBlockEntity ).field$getGlowingLayer();
    }

    @ModifyArg(
        method = "renderCanvas(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;IILnet/minecraft/client/model/ModelPart;Lnet/minecraft/client/util/SpriteIdentifier;ZLnet/minecraft/util/DyeColor;Lnet/minecraft/component/type/BannerPatternsComponent;ZZ)V",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/render/block/entity/BannerBlockEntityRenderer;renderLayer(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;IILnet/minecraft/client/model/ModelPart;Lnet/minecraft/client/util/SpriteIdentifier;Lnet/minecraft/util/DyeColor;)V"
        ),
        index = 2
    )
    private static int whatever( int light ) {
        Field.LOGGER.info( glowingLayers.toString() );
        return light;
    }

}
