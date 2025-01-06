package dev.trigam.field.component;

import dev.trigam.field.Field;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import java.util.function.UnaryOperator;

public class ItemComponentInit {

    public static final ComponentType< GlowingLayersComponent > GLOWING_LAYERS =
        register( "glowing_layers",
            builder -> builder
                .codec( GlowingLayersComponent.CODEC )
                .cache()
        );

    private static <T> ComponentType<T> register( String id, UnaryOperator<ComponentType.Builder<T>> builderOperator ) {
        return Registry.register(
            Registries.DATA_COMPONENT_TYPE,
            Field.id( id ),
            ( builderOperator.apply( ComponentType.builder() ) ).build()
        );
    }

    public static void register () {}
}
