package dev.trigam.field.component;

import dev.trigam.field.Field;
import net.minecraft.block.entity.BannerBlockEntity;
import org.ladysnake.cca.api.v3.block.BlockComponentFactoryRegistry;
import org.ladysnake.cca.api.v3.block.BlockComponentInitializer;
import org.ladysnake.cca.api.v3.component.Component;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.ComponentRegistry;

public class ComponentInit implements BlockComponentInitializer {

    public static final ComponentKey< GlowingLayersComponent > GLOWING_LAYERS =
        register( "glowing_layers", GlowingLayersComponent.class );

    public static <C extends Component> ComponentKey<C> register( String id, Class<C> componentClass ) {
        return ComponentRegistry.getOrCreate( Field.id( id ), componentClass );
    }

    public static void register () {}

    @Override
    public void registerBlockComponentFactories( BlockComponentFactoryRegistry registry ) {
        registry.registerFor( BannerBlockEntity.class, GLOWING_LAYERS, GlowingLayersComponent::new );
    }
}
