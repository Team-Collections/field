package dev.trigam.field.component;

import com.mojang.serialization.Codec;
import dev.trigam.field.attachments.AttachmentInit;
import io.netty.buffer.ByteBuf;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.component.ComponentMap;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import java.util.ArrayList;
import java.util.List;

public class GlowingLayersComponent {

    public List<Integer> glowingLayers = new ArrayList<>();

    public GlowingLayersComponent () {}
    public GlowingLayersComponent ( List<Integer> glowingLayers ) { this.glowingLayers = glowingLayers; }

    // Codecs
    public static final Codec<GlowingLayersComponent> CODEC = Codec.INT
        .listOf()
        .xmap( GlowingLayersComponent::new, GlowingLayersComponent::getGlowingLayers );
    public static final PacketCodec<ByteBuf, GlowingLayersComponent> PACKET_CODEC = PacketCodecs.INTEGER
        .collect( PacketCodecs.toList() )
        .xmap( GlowingLayersComponent::new, GlowingLayersComponent::getGlowingLayers );

    public List<Integer> getGlowingLayers() { return this.glowingLayers; }

    public boolean isLayerGlowing( int layerIndex ) {
        return this.glowingLayers != null && this.glowingLayers.contains( layerIndex );
    }

    public void setLayerGlowing( int layerIndex, boolean glowing ) {
        List<Integer> layers = new ArrayList<>( this.glowingLayers );

        if ( glowing ) layers.add( layerIndex );
        else layers.remove( Integer.valueOf( layerIndex ) );

        this.glowingLayers = layers;
    }

    public void syncFromAttachment( BlockEntity banner ) {
        if ( banner == null ) return;
        GlowingLayersComponent current = banner.getAttachedOrCreate(
            AttachmentInit.BANNER_GLOWING_LAYERS
        );
        this.glowingLayers = current.getGlowingLayers();
        banner.setComponents(
            ComponentMap.builder().add(
                ItemComponentInit.GLOWING_LAYERS,
                new GlowingLayersComponent( current.glowingLayers )
            ).build()
        );
    }
}
