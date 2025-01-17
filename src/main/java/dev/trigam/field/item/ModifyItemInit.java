package dev.trigam.field.item;

import dev.trigam.field.component.GlowingLayersComponent;
import dev.trigam.field.component.ItemComponentInit;
import dev.trigam.field.config.FieldConfig;
import net.fabricmc.fabric.api.item.v1.DefaultItemComponentEvents;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.EquippableComponent;
import net.minecraft.entity.EquipmentSlot;
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
            // Banner Stack Size
            if ( FieldConfig.bannerStackSize != 16 ) {
                for ( Item banner : BANNERS ) {
                    context.modify( banner, builder ->
                        builder.add(
                            DataComponentTypes.MAX_STACK_SIZE,
                            FieldConfig.bannerStackSize
                        )
                    );
                }
            }
            // Wearable Banners
            if ( FieldConfig.wearableBanners ) {
                for ( Item banner : BANNERS ) {
                    context.modify( banner, builder ->
                        builder.add(
                            DataComponentTypes.EQUIPPABLE,
                            EquippableComponent.builder( EquipmentSlot.HEAD ).build()
                        )
                    );
                }
            }
            // Glowing Banners
            for ( Item banner : BANNERS ) {
                context.modify( banner, builder ->
                    builder.add(
                        ItemComponentInit.GLOWING_LAYERS,
                        GlowingLayersComponent.DEFAULT
                    )
                );
            }
        });
    }
}
