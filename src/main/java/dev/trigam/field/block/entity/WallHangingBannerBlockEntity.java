package dev.trigam.field.block.entity;

import dev.trigam.field.block.WallHangingBannerBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BannerBlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.BlockPos;

// Adapted from: https://github.com/anweisemods/BannersEverywhere/blob/1.21/src/main/java/net/anweisen/bannerseverywhere/hanging/HangingBannerBlockEntity.java
public class WallHangingBannerBlockEntity extends BannerBlockEntity {

    public WallHangingBannerBlockEntity(BlockPos pos, BlockState state ) {
        super( pos, state );
    }

    public WallHangingBannerBlockEntity(BlockPos pos, BlockState state, DyeColor baseColor ) {
        super( pos, state, baseColor );
    }

    @Override
    public boolean supports( BlockState state ) {
        return state.getBlock() instanceof WallHangingBannerBlock || super.supports( state );
    }

    @Override
    public BlockEntityType<?> getType() {
        return BlockEntityInit.WALL_HANGING_BANNER;
    }
}
