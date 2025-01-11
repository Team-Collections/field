package dev.trigam.field.tag;

import dev.trigam.field.Field;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;

public class BlockTagInit {

    public static final TagKey<Block> WALL_HANGING_DECORATIONS = of( "wall_hanging_decorations" );

    private static TagKey<Block> of ( String id ) {
        return TagKey.of( RegistryKeys.BLOCK, Field.id( id ));
    }

    public static void init () {}

}
