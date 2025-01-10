package dev.trigam.field.block;

import dev.trigam.field.block.entity.HangingBannerBlockEntity;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldView;
import java.util.Map;
import static java.util.Map.entry;

// Adapted from: https://github.com/anweisemods/BannersEverywhere/blob/1.21/src/main/java/net/anweisen/bannerseverywhere/hanging/HangingBannerBlock.java
public class HangingBannerBlock extends BannerBlock {

    public HangingBannerBlock( DyeColor dyeColor, Settings settings ) {
        super( dyeColor, settings );
    }

    @Override
    protected boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos ) {
        return world.getBlockState( pos.up() )
            .isSideSolid( world, pos.up(), Direction.DOWN, SideShapeType.CENTER );
    }

    @Override
    public BlockEntity createBlockEntity( BlockPos pos, BlockState state ) {
        return new HangingBannerBlockEntity( pos, state, this.getColor() );
    }

    public static Block getHangingBanner (DyeColor color ) {
        Map< DyeColor, Block > hangingBanners = Map.ofEntries(
            entry( DyeColor.WHITE, BlockInit.WHITE_HANGING_BANNER ),
            entry( DyeColor.LIGHT_GRAY, BlockInit.LIGHT_GRAY_HANGING_BANNER ),
            entry( DyeColor.GRAY, BlockInit.GRAY_HANGING_BANNER ),
            entry( DyeColor.BLACK, BlockInit.BLACK_HANGING_BANNER ),
            entry( DyeColor.BROWN, BlockInit.BROWN_HANGING_BANNER ),
            entry( DyeColor.RED, BlockInit.RED_HANGING_BANNER ),
            entry( DyeColor.ORANGE, BlockInit.ORANGE_HANGING_BANNER ),
            entry( DyeColor.YELLOW, BlockInit.YELLOW_HANGING_BANNER ),
            entry( DyeColor.LIME, BlockInit.LIME_HANGING_BANNER ),
            entry( DyeColor.GREEN, BlockInit.GREEN_HANGING_BANNER ),
            entry( DyeColor.CYAN, BlockInit.CYAN_HANGING_BANNER ),
            entry( DyeColor.LIGHT_BLUE, BlockInit.LIGHT_BLUE_HANGING_BANNER ),
            entry( DyeColor.BLUE, BlockInit.BLUE_HANGING_BANNER ),
            entry( DyeColor.PURPLE, BlockInit.PURPLE_HANGING_BANNER ),
            entry( DyeColor.MAGENTA, BlockInit.MAGENTA_HANGING_BANNER ),
            entry( DyeColor.PINK, BlockInit.PINK_HANGING_BANNER )
        );
        return hangingBanners.get( color );
    }

    public static Block asBanner ( Block hangingBanner ) {
        Map< Block, Block > banners = Map.ofEntries(
            entry( BlockInit.WHITE_HANGING_BANNER, Blocks.WHITE_BANNER ),
            entry( BlockInit.LIGHT_GRAY_HANGING_BANNER, Blocks.LIGHT_GRAY_BANNER ),
            entry( BlockInit.GRAY_HANGING_BANNER, Blocks.GRAY_BANNER ),
            entry( BlockInit.BLACK_HANGING_BANNER, Blocks.BLACK_BANNER ),
            entry( BlockInit.BROWN_HANGING_BANNER, Blocks.BROWN_BANNER ),
            entry( BlockInit.RED_HANGING_BANNER, Blocks.RED_BANNER ),
            entry( BlockInit.ORANGE_HANGING_BANNER, Blocks.ORANGE_BANNER ),
            entry( BlockInit.YELLOW_HANGING_BANNER, Blocks.YELLOW_BANNER ),
            entry( BlockInit.LIME_HANGING_BANNER, Blocks.LIME_BANNER ),
            entry( BlockInit.GREEN_HANGING_BANNER, Blocks.GREEN_BANNER ),
            entry( BlockInit.CYAN_HANGING_BANNER, Blocks.CYAN_BANNER ),
            entry( BlockInit.LIGHT_BLUE_HANGING_BANNER, Blocks.LIGHT_BLUE_BANNER ),
            entry( BlockInit.BLUE_HANGING_BANNER, Blocks.BLUE_BANNER ),
            entry( BlockInit.PURPLE_HANGING_BANNER, Blocks.PURPLE_BANNER ),
            entry( BlockInit.MAGENTA_HANGING_BANNER, Blocks.MAGENTA_BANNER ),
            entry( BlockInit.PINK_HANGING_BANNER, Blocks.PINK_BANNER )
        );
        return banners.get( hangingBanner );
    }

}
