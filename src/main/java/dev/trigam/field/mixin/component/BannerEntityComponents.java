package dev.trigam.field.mixin.component;

import dev.trigam.field.Field;
import dev.trigam.field.component.ItemComponentInit;
import dev.trigam.field.component.GlowingLayersComponent;
import dev.trigam.field.impl.FieldBannerBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BannerBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.component.ComponentMap;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtOps;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Nameable;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin( BannerBlockEntity.class )
public abstract class BannerEntityComponents extends BlockEntity implements Nameable, FieldBannerBlockEntity {

    @Unique private GlowingLayersComponent glowingLayers = GlowingLayersComponent.DEFAULT;

    public BannerEntityComponents( BlockEntityType<?> type, BlockPos pos, BlockState state ) {
        super(type, pos, state);
    }

    @Override
    public GlowingLayersComponent field$getGlowingLayers() {
        return this.glowingLayers;
    }

    @Override
    public void field$setLayerGlowing( int layerIndex, boolean glowing ) {
        this.glowingLayers.setLayerGlowing( layerIndex, glowing );
    }

    @Override
    public boolean field$isEmpty () {
        return this.glowingLayers.glowingLayers.isEmpty();
    }

    @Inject(
        method = "readComponents",
        at = @At( value = "TAIL" )
    )
    private void readComponents( BlockEntity.ComponentsAccess components, CallbackInfo ci ) {
        this.glowingLayers = components.getOrDefault( ItemComponentInit.GLOWING_LAYERS, GlowingLayersComponent.DEFAULT );
    }

    @Inject(
        method = "addComponents",
        at = @At( value = "TAIL" )
    )
    private void addComponents( ComponentMap.Builder builder, CallbackInfo ci ) {
        if ( !this.field$isEmpty() ) builder.add( ItemComponentInit.GLOWING_LAYERS, this.glowingLayers );
    }

    @Inject(
        method = "readNbt",
        at = @At( value = "TAIL" )
    )
    private void readNbt( NbtCompound nbt, RegistryWrapper.WrapperLookup registries, CallbackInfo ci ) {
        if ( nbt.contains( "glowing_layers" ) ) {
            GlowingLayersComponent.CODEC.parse(
                registries.getOps( NbtOps.INSTANCE ),
                nbt.get( "glowing_layers" )
            ).resultOrPartial(
                ( glowingLayers -> Field.LOGGER.error( "Failed to parse banner patterns: '{}'", glowingLayers ))
            ).ifPresent(
                ( glowingLayers -> this.glowingLayers = glowingLayers )
            );
        }
    }

    @Inject(
        method = "writeNbt",
        at = @At( value = "TAIL" )
    )
    private void writeNbt( NbtCompound nbt, RegistryWrapper.WrapperLookup registries, CallbackInfo ci ) {
        if ( this.glowingLayers != null && !this.field$isEmpty() ) {
            nbt.put( "glowing_layers",
                GlowingLayersComponent.CODEC.encodeStart(
                    registries.getOps( NbtOps.INSTANCE ),
                    this.glowingLayers
                ).getOrThrow()
            );
        }
    }
}