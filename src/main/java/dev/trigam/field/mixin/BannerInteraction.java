package dev.trigam.field.mixin;

import dev.trigam.field.component.GlowingLayersComponent;
import dev.trigam.field.impl.FieldBannerBlockEntity;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.AbstractBannerBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BannerBlockEntity;
import net.minecraft.component.type.BannerPatternsComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import java.util.List;

@Mixin( AbstractBannerBlock.class )
abstract class BannerInteraction extends BlockWithEntity  {

    protected BannerInteraction ( Settings settings ) { super( settings ); }

    @Override protected ActionResult onUseWithItem (
        ItemStack stack, BlockState state, World world,
        BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit
    ) {

        BannerBlockEntity banner = (BannerBlockEntity) world.getBlockEntity( pos );

        // Used item
        boolean addGlowing = stack.isOf( Items.GLOW_INK_SAC );
        boolean removeGlowing = stack.isOf( Items.INK_SAC );

        if ( addGlowing || removeGlowing ) {
            if ( !world.isClient() && banner != null ) {

                // Layer data
                List<BannerPatternsComponent.Layer> bannerLayers = banner.getPatterns().layers();
                GlowingLayersComponent glowingLayers = ((FieldBannerBlockEntity) banner).field$getGlowingLayers();

                // Target layer
                int targetLayer = getTargetLayer( bannerLayers, glowingLayers, addGlowing );
                boolean isLayerGlowing = glowingLayers.isLayerGlowing( targetLayer );

                // Can interact
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
                if ( !player.isCreative() ) stack.decrement( 1 );
                Criteria.ITEM_USED_ON_BLOCK.trigger(
                    (ServerPlayerEntity) player, pos, stack
                );
                player.incrementStat( Stats.USED.getOrCreateStat( stack.getItem() ) );

                // Mark for update
                world.markDirty( pos );
                ((ServerWorld) world).getChunkManager().markForUpdate( pos );

                return ActionResult.SUCCESS_SERVER;
            }
        }

        return ActionResult.PASS_TO_DEFAULT_BLOCK_ACTION;
    }

    @Unique
    private static boolean canInteract ( boolean isLayerGlowing, boolean addGlowing, boolean removeGlowing ) {
        return ( addGlowing && !isLayerGlowing ) || ( removeGlowing && isLayerGlowing );
    }

    @Unique
    private static int getTargetLayer (
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