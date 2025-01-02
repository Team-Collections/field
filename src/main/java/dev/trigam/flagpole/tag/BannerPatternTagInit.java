package dev.trigam.flagpole.tag;

import dev.trigam.flagpole.Flagpole;
import net.minecraft.block.entity.BannerPattern;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;

public class BannerPatternTagInit {

    public static final TagKey<BannerPattern> STAR_BANNER_PATTERN = of( "pattern_item/star" );

    private static TagKey<BannerPattern> of( String id ) {
        return TagKey.of( RegistryKeys.BANNER_PATTERN, Flagpole.id( id ));
    }

    public static void register () {}

}