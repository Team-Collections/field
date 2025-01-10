package dev.trigam.field.event;

import dev.trigam.field.Field;
import dev.trigam.field.block.HangingBannerBlock;
import net.fabricmc.fabric.api.event.player.PlayerPickItemEvents;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.component.ComponentMap;
import net.minecraft.item.ItemStack;

public class EventInit {

    public static void init () {

        PlayerPickItemEvents.BLOCK.register( ( player, pos, state, includeData ) -> {
            if ( state.getBlock() instanceof HangingBannerBlock hangingBanner ) {

                Block returnBlock = HangingBannerBlock.asBanner( hangingBanner );

                BlockEntity hangingBannerEntity = player.getServerWorld().getBlockEntity( pos );
                ComponentMap components = ComponentMap.EMPTY;
                if ( hangingBannerEntity != null ) {
                    components = hangingBannerEntity.createComponentMap();
                }

                ItemStack returnItem = new ItemStack( returnBlock.asItem() );
                returnItem.applyComponentsFrom( components );

                Field.LOGGER.info(String.valueOf(returnItem));

                return returnItem;
            }

            return null;
        });

    }

}
