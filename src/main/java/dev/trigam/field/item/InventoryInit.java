package dev.trigam.field.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;

public class InventoryInit {

    public static void init () {
        ItemGroupEvents.modifyEntriesEvent( ItemGroups.INGREDIENTS ).register(content -> {
            content.addAfter( Items.GUSTER_BANNER_PATTERN, ItemInit.STAR_BANNER_PATTERN );
        });
    }

}
