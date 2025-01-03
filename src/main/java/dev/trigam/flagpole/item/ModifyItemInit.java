package dev.trigam.flagpole.item;

import dev.trigam.flagpole.config.FlagpoleConfig;
import net.fabricmc.fabric.api.item.v1.DefaultItemComponentEvents;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

import java.util.Arrays;
import java.util.List;

public class ModifyItemInit {

    // To-do: Get all the banners from the item tag (for mod compat)
    public static final List<Item> BANNERS = Arrays.asList(
        Items.WHITE_BANNER, Items.LIGHT_GRAY_BANNER, Items.GRAY_BANNER, Items.BLACK_BANNER,
        Items.BROWN_BANNER, Items.RED_BANNER, Items.ORANGE_BANNER, Items.YELLOW_BANNER,
        Items.LIME_BANNER, Items.GREEN_BANNER, Items.CYAN_BANNER, Items.LIGHT_BLUE_BANNER,
        Items.BLUE_BANNER, Items.PURPLE_BANNER, Items.MAGENTA_BANNER, Items.PINK_BANNER
    );

    public static void init() {

        DefaultItemComponentEvents.MODIFY.register( context -> {
            // Banners
            if ( FlagpoleConfig.bannerStackSize != 16 ) {
                for ( Item banner : BANNERS ) {
                    context.modify( banner, builder ->
                        builder.add( DataComponentTypes.MAX_STACK_SIZE, FlagpoleConfig.bannerStackSize )
                    );
                }
            }
        });
    }
}
