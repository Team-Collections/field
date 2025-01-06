package dev.trigam.field.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.entity.BannerBlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;
import java.util.*;
import java.util.stream.Collectors;

public class GlowingLayersComponent implements AutoSyncedComponent {

    public Set<Integer> glowingLayers = new TreeSet<>();
    private BannerBlockEntity banner;

    public GlowingLayersComponent( BannerBlockEntity banner ) {
        this.banner = banner;
    }
    public GlowingLayersComponent(Set<Integer> glowingLayers) {
        this.glowingLayers = glowingLayers;
    }

    // Codecs
    public static final Codec<GlowingLayersComponent> CODEC = RecordCodecBuilder.create(instance ->
        instance.group(
            Codec.list( Codec.INT ).optionalFieldOf( "glowing_layers", List.of() )
                .xmap(Set::copyOf, List::copyOf )
                .forGetter( GlowingLayersComponent::getGlowingLayers )
        ).apply( instance, GlowingLayersComponent::new )
    );

    public Set<Integer> getGlowingLayers() { return this.glowingLayers; }

    public boolean isLayerGlowing( int layerIndex ) {
        return this.glowingLayers.contains( layerIndex );
    }

    public void setLayerGlowing( int layerIndex, boolean glowing ) {
        if ( glowing ) this.glowingLayers.add( layerIndex );
        else this.glowingLayers.remove( layerIndex );

        ComponentInit.GLOWING_LAYERS.sync( this.banner );
    }

    @Override
    public void readFromNbt( NbtCompound tag, RegistryWrapper.WrapperLookup wrapperLookup ) {
        int[] nbtLayers = tag.getIntArray("glowing_layers");
        this.glowingLayers = Arrays.stream( nbtLayers ).boxed().collect( Collectors.toSet() );
    }

    @Override
    public void writeToNbt( NbtCompound tag, RegistryWrapper.WrapperLookup wrapperLookup ) {
        tag.putIntArray( "glowing_layers", this.glowingLayers.stream().toList() );
    }
}
