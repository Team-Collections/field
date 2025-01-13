package dev.trigam.field.block.entity;

import dev.trigam.field.attachments.AttachmentInit;
import dev.trigam.field.block.HangingBannerBlock;
import dev.trigam.field.component.GlowingLayersComponent;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BannerBlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.BlockPos;

// Adapted from: https://github.com/anweisemods/BannersEverywhere/blob/1.21/src/main/java/net/anweisen/bannerseverywhere/hanging/HangingBannerBlockEntity.java
public class HangingBannerBlockEntity extends BannerBlockEntity {

    public HangingBannerBlockEntity( BlockPos pos, BlockState state ) {
        super( pos, state );
        this.attach();
    }

    public HangingBannerBlockEntity( BlockPos pos, BlockState state, DyeColor baseColor ) {
        super( pos, state, baseColor );
        this.attach();
    }

    public void attach () {
        if ( this.getWorld() == null ) return;

        GlowingLayersComponent current = this.getAttachedOrElse(
            AttachmentInit.BANNER_GLOWING_LAYERS,
            new GlowingLayersComponent()
        );
        this.setAttached( AttachmentInit.BANNER_GLOWING_LAYERS, current );
    }

    @Override
    public boolean supports( BlockState state ) {
        return state.getBlock() instanceof HangingBannerBlock || super.supports( state );
    }

    @Override
    public BlockEntityType<?> getType() {
        return BlockEntityInit.HANGING_BANNER;
    }
}
