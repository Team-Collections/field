package dev.trigam.field.component;

import com.mojang.serialization.Codec;
import dev.trigam.field.Field;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;

import java.util.ArrayList;
import java.util.List;

public class GlowingLayersComponent {

    public List<Integer> glowingLayers = new ArrayList<>();

    public GlowingLayersComponent () {}
    public GlowingLayersComponent ( List<Integer> glowingLayers ) { this.glowingLayers = glowingLayers; }

    // Codecs
//    public static final Codec<GlowingLayersComponent> CODEC = RecordCodecBuilder.create(instance ->
//        instance.group(
//            Codec.list( Codec.INT ).optionalFieldOf( "glowing_layers", List.of() )
//                .forGetter( GlowingLayersComponent::getGlowingLayers )
//        ).apply( instance, GlowingLayersComponent::new )
//    );
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

        Field.LOGGER.info( "Layer: {}", layerIndex );
        Field.LOGGER.info( "Glowing layers: {}", this.glowingLayers );

        if ( this.glowingLayers == null ) return;
        if ( glowing ) this.glowingLayers.add( layerIndex );
        else this.glowingLayers.remove( layerIndex );

        //ComponentInit.GLOWING_LAYERS.sync( this.banner );
    }

//    @Override
//    public void readFromNbt( NbtCompound tag, RegistryWrapper.WrapperLookup wrapperLookup ) {
//        int[] nbtLayers = tag.getIntArray("glowing_layers");
//        this.glowingLayers = Arrays.stream( nbtLayers ).boxed().collect( Collectors.toSet() );
//    }
//
//    @Override
//    public void writeToNbt( NbtCompound tag, RegistryWrapper.WrapperLookup wrapperLookup ) {
//        tag.putIntArray( "glowing_layers", this.glowingLayers.stream().toList() );
//    }
}
