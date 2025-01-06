package dev.trigam.field.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

// Adapted from: https://github.com/UltrusBot/glow-banners/blob/1.21/common/src/main/java/me/ultrusmods/glowingbanners/component/BannerGlowComponent.java
public class GlowingLayersComponent {

    // Codecs
    public static final Codec<GlowingLayersComponent> CODEC = RecordCodecBuilder.create( instance ->
        instance.group(
            Codec.list( Codec.INT ).optionalFieldOf( "glowing_layers", List.of() )
                .xmap(Collections::unmodifiableCollection, List::copyOf )
                .forGetter( GlowingLayersComponent::getGlowingLayers )
        ).apply( instance, GlowingLayersComponent::new )
    );
    public static final PacketCodec< ByteBuf, GlowingLayersComponent > PACKET_CODEC = PacketCodecs.INTEGER
        .collect( PacketCodecs.toList() )
        .xmap( GlowingLayersComponent::new, GlowingLayersComponent::getGlowingLayersList );


    private final Collection<Integer> glowingLayers = new TreeSet<>( Integer::compare );

    public GlowingLayersComponent() { this( List.of() ); }
    public GlowingLayersComponent( Collection<Integer> glowingLayers ) {
        this.glowingLayers.addAll( glowingLayers );
    }

    public Collection<Integer> getGlowingLayers() {
        return this.glowingLayers;
    }
    public List<Integer> getGlowingLayersList() {
        return this.glowingLayers.stream().toList();
    }

    public boolean isGlowing( int layerIndex ) {
        return this.glowingLayers.contains( layerIndex );
    }

    public void setGlowing( int layerIndex, boolean glowing ) {
        if ( glowing ) this.glowingLayers.add( layerIndex );
        else this.glowingLayers.remove( layerIndex );
    }

    public void clearGlowing() {
        this.glowingLayers.clear();
    }

}
