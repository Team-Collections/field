package dev.trigam.field.event;

import dev.trigam.field.component.GlowingLayersComponent;
import dev.trigam.field.impl.FieldBannerBlockEntity;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.entity.BannerBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.component.type.BannerPatternsComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import java.util.List;

// Adapted from: https://github.com/UltrusBot/glow-banners/blob/1.21/common/src/main/java/me/ultrusmods/glowingbanners/GlowBannersMod.java
public class ToggleBannerGlow {

    public static ActionResult toggleBannerGlow( PlayerEntity player, World world, Hand hand, BlockHitResult result ) {
        // Data
        ItemStack usedItem = player.getStackInHand( hand );
        BlockPos pos = result.getBlockPos();
        BlockEntity blockEntity = world.getBlockEntity( pos );

        // Used item
        boolean addGlowing = usedItem.isOf( Items.GLOW_INK_SAC );
        boolean removeGlowing = usedItem.isOf( Items.INK_SAC );

        if ( blockEntity instanceof BannerBlockEntity banner ) {
            // Layer data
            List<BannerPatternsComponent.Layer> bannerLayers = banner.getPatterns().layers();
            GlowingLayersComponent glowingLayers = ((FieldBannerBlockEntity) banner).field$getGlowingLayers();

            int targetLayer = getTargetLayer( bannerLayers, glowingLayers, addGlowing );
            boolean isLayerGlowing = glowingLayers.isLayerGlowing( targetLayer );

            boolean canInteract = canInteract( isLayerGlowing, addGlowing, removeGlowing );
            if ( !canInteract ) return ActionResult.PASS;
            if ( world.isClient() ) return ActionResult.SUCCESS;

            // Play sound
            SoundEvent useSound = addGlowing ? SoundEvents.ITEM_GLOW_INK_SAC_USE : SoundEvents.ITEM_INK_SAC_USE;
            world.playSound( null, pos, useSound, SoundCategory.BLOCKS, 1.0F, 1.0F );

            // Toggle glowing
            if ( addGlowing ) ((FieldBannerBlockEntity) banner).field$setLayerGlowing( targetLayer, true );
            if ( removeGlowing ) ((FieldBannerBlockEntity) banner).field$setLayerGlowing( targetLayer, false );

            // Stack + stats
            if ( !player.isCreative() ) usedItem.decrement( 1 );
            Criteria.ITEM_USED_ON_BLOCK.trigger(
                (ServerPlayerEntity) player, pos, usedItem
            );
            player.incrementStat( Stats.USED.getOrCreateStat( usedItem.getItem() ) );

            //glowingLayers.syncFromAttachment( banner );
            return ActionResult.SUCCESS_SERVER;
        }
        return ActionResult.PASS;
    }

    public static boolean canInteract( boolean isLayerGlowing, boolean addGlowing, boolean removeGlowing ) {
        return ( addGlowing && !isLayerGlowing ) || ( removeGlowing && isLayerGlowing );
    }

    public static int getTargetLayer(
        List<BannerPatternsComponent.Layer> bannerLayers,
        GlowingLayersComponent glowingLayers,
        boolean addGlowing
    ) {
        int targetLayer = bannerLayers.size();
        for ( int i = bannerLayers.size(); i >= 0; i-- ) {
            boolean isLayerGlowing = glowingLayers.isLayerGlowing( i );
            if ( addGlowing && !isLayerGlowing ) return i;
            if ( !addGlowing && isLayerGlowing ) return i;
        }
        return targetLayer;
    }

}
