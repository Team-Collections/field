package dev.trigam.field.block;

import com.google.common.collect.Maps;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.trigam.field.block.entity.HangingBannerBlockEntity;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.RotationPropertyHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldView;
import net.minecraft.world.tick.ScheduledTickView;
import java.util.Map;

// Adapted from: https://github.com/anweisemods/BannersEverywhere/blob/1.21/src/main/java/net/anweisen/bannerseverywhere/hanging/HangingBannerBlock.java
public class HangingBannerBlock extends AbstractBannerBlock {

    // Codec
    public static final MapCodec<HangingBannerBlock> CODEC = RecordCodecBuilder.mapCodec( instance ->
        instance.group(
            DyeColor.CODEC.fieldOf( "color" ).forGetter( AbstractBannerBlock::getColor ),
            createSettingsCodec()
        ).apply( instance, HangingBannerBlock::new )
    );
    public MapCodec<HangingBannerBlock> getCodec() {
        return CODEC;
    }

    // Properties
    public static final IntProperty ROTATION = Properties.ROTATION;
    private static final Map<DyeColor, Block> COLORED_BANNERS = Maps.newHashMap();
    private static final VoxelShape SHAPE = Block.createCuboidShape( 4F, 0F, 4F, 12F, 16F, 12F );

    public HangingBannerBlock( DyeColor dyeColor, Settings settings ) {
        super( dyeColor, settings );
        this.setDefaultState( ( this.stateManager.getDefaultState() )
            .with(ROTATION, 0)
        );
        COLORED_BANNERS.put(dyeColor, this);
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

    public VoxelShape getOutlineShape( BlockState state, BlockView world, BlockPos pos, ShapeContext context ) {
        return SHAPE;
    }

    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState()
            .with( ROTATION, RotationPropertyHelper.fromYaw( ctx.getPlayerYaw() + 180.0F ) );
    }

    protected BlockState getStateForNeighborUpdate( BlockState state, WorldView world, ScheduledTickView tickView, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, Random random ) {
        return direction == Direction.DOWN && !state.canPlaceAt(world, pos)
            ? Blocks.AIR.getDefaultState()
            : super.getStateForNeighborUpdate( state, world, tickView, pos, direction, neighborPos, neighborState, random );
    }

    protected BlockState rotate ( BlockState state, BlockRotation rotation ) {
        return state.with( ROTATION, rotation.rotate( state.get( ROTATION ), 16 ) );
    }

    protected BlockState mirror ( BlockState state, BlockMirror mirror ) {
        return state.with( ROTATION, mirror.mirror( state.get( ROTATION ), 16 ) );
    }

    protected void appendProperties ( StateManager.Builder<Block, BlockState> builder ) {
        builder.add( ROTATION );
    }

    public static Block getForColor ( DyeColor color ) {
        return COLORED_BANNERS.getOrDefault( color, BlockInit.WHITE_HANGING_BANNER );
    }

}
