package dev.trigam.field.mixin.client.glowingBanners;

import dev.trigam.field.component.ComponentInit;
import dev.trigam.field.component.GlowingLayersComponent;
import dev.trigam.field.impl.FieldBannerBlockEntity;
import net.minecraft.block.entity.BannerBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.component.ComponentMap;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin( BannerBlockEntity.class )
public class BannerEntityComponents implements FieldBannerBlockEntity {

    @Unique private GlowingLayersComponent glowingLayers = new GlowingLayersComponent();

    @Inject(
        method = "readComponents",
        at = @At( value = "TAIL" )
    )
    private void readComponents( BlockEntity.ComponentsAccess components, CallbackInfo ci ) {
        this.glowingLayers = components.getOrDefault( ComponentInit.GLOWING_LAYERS, new GlowingLayersComponent() );
    }

    @Inject(
        method = "addComponents",
        at = @At( value = "TAIL" )
    )
    private void addComponents( ComponentMap.Builder builder, CallbackInfo ci ) {
        builder.add( ComponentInit.GLOWING_LAYERS, this.glowingLayers );
    }

    @Override
    public GlowingLayersComponent field$getGlowingLayer() {
        return this.glowingLayers;
    }
}
