package dev.trigam.flagpole.item;

import dev.trigam.flagpole.Flagpole;
import dev.trigam.flagpole.tag.BannerPatternTagInit;
import net.minecraft.item.BannerPatternItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

import java.util.function.Function;

public class ItemInit {
    public static final Item STAR_BANNER_PATTERN = registerItem( "star_banner_pattern",
        settings -> new BannerPatternItem(
            BannerPatternTagInit.STAR_BANNER_PATTERN,
            settings
        ),
        new Item.Settings().maxCount( 1 )
    );

    public static Item registerItem ( String name, Function<Item.Settings, Item> constructor, Item.Settings settings ) {
        RegistryKey<Item> key = RegistryKey.of( RegistryKeys.ITEM, Flagpole.id( name ) );
        return Registry.register(
            Registries.ITEM, key,
            constructor.apply( settings.useItemPrefixedTranslationKey().registryKey( key ) )
        );
    }

    public static void register () {}

}
