package dev.trigam.field.component;

import com.mojang.serialization.Codec;
import dev.trigam.field.Field;
import io.netty.buffer.ByteBuf;
import net.minecraft.block.entity.BannerBlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.registry.RegistryWrapper;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GlowingLayersComponent implements AutoSyncedComponent {

    public List<Integer> glowingLayers = new ArrayList<>();
    private BannerBlockEntity banner;

    public GlowingLayersComponent () {}
    public GlowingLayersComponent ( BannerBlockEntity banner ) {
        this.banner = banner;
    }
    public GlowingLayersComponent ( List<Integer> glowingLayers ) {
        this.glowingLayers = glowingLayers;
    }

    // Codecs
    public static final Codec<GlowingLayersComponent> CODEC = Codec.INT
        .listOf()
        .xmap( GlowingLayersComponent::new, GlowingLayersComponent::getGlowingLayers );
    public static final PacketCodec<ByteBuf, GlowingLayersComponent> PACKET_CODEC = PacketCodecs.INTEGER
        .collect( PacketCodecs.toList() )
        .xmap( GlowingLayersComponent::new, GlowingLayersComponent::getGlowingLayers );

    public List<Integer> getGlowingLayers() { return this.glowingLayers; }

    public boolean isLayerGlowing( int layerIndex ) {
        return this.glowingLayers.contains( layerIndex );
    }

    public void setLayerGlowing( int layerIndex, boolean glowing ) {
        Field.LOGGER.info( "Current: {}", this.glowingLayers );
        Field.LOGGER.info( "Setting layer {} to {}", layerIndex, glowing ? "glowing" : "not glowing" );

        List<Integer> layers = new ArrayList<>( this.glowingLayers );

        if ( glowing ) layers.add( layerIndex );
        else layers.remove( Integer.valueOf( layerIndex ) );

        this.glowingLayers = layers;
        ComponentInit.GLOWING_LAYERS.sync( this.banner );
    }

    @Override
    public void readFromNbt( NbtCompound tag, RegistryWrapper.WrapperLookup wrapperLookup ) {
        int[] nbtLayers = tag.getIntArray("glowing_layers");
        this.glowingLayers = Arrays.stream( nbtLayers ).boxed().toList();
    }

    @Override
    public void writeToNbt( NbtCompound tag, RegistryWrapper.WrapperLookup wrapperLookup ) {
        tag.putIntArray( "glowing_layers", this.glowingLayers.stream().toList() );
    }
}
