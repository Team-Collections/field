package dev.trigam.field.block.entity;

import dev.trigam.field.block.HangingBannerBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BannerBlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.BlockPos;

// Adapted from: https://github.com/anweisemods/BannersEverywhere/blob/1.21/src/main/java/net/anweisen/bannerseverywhere/hanging/HangingBannerBlockEntity.java
public class HangingBannerBlockEntity extends BannerBlockEntity {

    public HangingBannerBlockEntity( BlockPos pos, BlockState state ) {
        super( pos, state );
    }

    public HangingBannerBlockEntity( BlockPos pos, BlockState state, DyeColor baseColor ) {
        super( pos, state, baseColor );
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
