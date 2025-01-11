package dev.trigam.field.block;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.trigam.field.block.entity.WallHangingBannerBlockEntity;
import dev.trigam.field.tag.BlockTagInit;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldView;
import net.minecraft.world.tick.ScheduledTickView;
import java.util.Map;

// Adapted from: https://github.com/anweisemods/BannersEverywhere/blob/1.21/src/main/java/net/anweisen/bannerseverywhere/hanging/HangingBannerBlock.java
public class WallHangingBannerBlock extends AbstractBannerBlock {

    // Codec
    public static final MapCodec<WallHangingBannerBlock> CODEC = RecordCodecBuilder.mapCodec(instance ->
        instance.group(
            DyeColor.CODEC.fieldOf( "color" ).forGetter( AbstractBannerBlock::getColor ),
            createSettingsCodec()
        ).apply( instance, WallHangingBannerBlock::new )
    );
    public MapCodec<WallHangingBannerBlock> getCodec() {
        return CODEC;
    }

    // Properties
    public static final EnumProperty<Direction> FACING = HorizontalFacingBlock.FACING;
    private static final Map<DyeColor, Block> COLORED_BANNERS = Maps.newHashMap();

    // Shapes
    public static final VoxelShape NORTH_SOUTH_COLLISION_SHAPE = Block.createCuboidShape(0F, 14F, 6F, 16F, 16F, 10F);
    public static final VoxelShape EAST_WEST_COLLISION_SHAPE = Block.createCuboidShape(6F, 14F, 0F, 10F, 16F, 16F);

    public static final VoxelShape NORTH_SOUTH_BANNER_SHAPE = Block.createCuboidShape(1F, -16F, 6F, 15F, 13.5F, 10F);
    public static final VoxelShape EAST_WEST_BANNER_SHAPE = Block.createCuboidShape(6F, -16F, 1F, 10F, 13.5F, 15F);

    public static final VoxelShape NORTH_SOUTH_SHAPE = VoxelShapes.union( NORTH_SOUTH_COLLISION_SHAPE, NORTH_SOUTH_BANNER_SHAPE );
    public static final VoxelShape EAST_WEST_SHAPE = VoxelShapes.union( EAST_WEST_COLLISION_SHAPE, EAST_WEST_BANNER_SHAPE );

    private static final Map<Direction, VoxelShape> OUTLINE_SHAPES = Maps.newEnumMap( ImmutableMap.of(
        Direction.NORTH, NORTH_SOUTH_SHAPE,
        Direction.SOUTH, NORTH_SOUTH_SHAPE,
        Direction.EAST, EAST_WEST_SHAPE,
        Direction.WEST, EAST_WEST_SHAPE)
    );

    public WallHangingBannerBlock(DyeColor dyeColor, Settings settings ) {
        super( dyeColor, settings );
        this.setDefaultState( ( this.stateManager.getDefaultState() )
            .with( FACING, Direction.NORTH )
        );
        COLORED_BANNERS.put( dyeColor, this) ;
    }

    @Override
    public BlockEntity createBlockEntity ( BlockPos pos, BlockState state ) {
        return new WallHangingBannerBlockEntity( pos, state, this.getColor() );
    }

    // Placement
    protected boolean canAttachAt ( BlockState state, WorldView world, BlockPos pos ) {
        Direction direction = ( state.get( FACING ) ).rotateYClockwise();
        Direction direction2 = ( state.get( FACING ) ).rotateYCounterclockwise();
        return this.canAttachTo( world, state, pos.offset( direction ), direction2 )
            || this.canAttachTo( world, state, pos.offset( direction2 ), direction );
    }

    public boolean canAttachTo ( WorldView world, BlockState state, BlockPos pos, Direction direction ) {
        BlockState blockState = world.getBlockState( pos );
        return blockState.isIn( BlockTagInit.WALL_HANGING_BANNERS )
            ? ( blockState.get( FACING ) ).getAxis().test( state.get( FACING ) )
            : blockState.isSideSolid( world, pos, direction, SideShapeType.FULL );
    }

    @Override
    public BlockState getPlacementState ( ItemPlacementContext ctx ) {
        BlockState blockState = this.getDefaultState();
        WorldView worldView = ctx.getWorld();
        BlockPos blockPos = ctx.getBlockPos();

        for ( Direction direction : ctx.getPlacementDirections() ) {
            if ( direction.getAxis().isHorizontal() && !direction.getAxis().test( ctx.getSide() ) ) {
                Direction facing = direction.getOpposite();
                blockState = blockState.with( FACING, facing );
                if ( blockState.canPlaceAt( worldView, blockPos ) && this.canAttachAt( blockState, worldView, blockPos ) ) {
                    return blockState;
                }
            }
        }

        return null;
    }

    // State
    @Override
    protected BlockState getStateForNeighborUpdate ( BlockState state, WorldView world, ScheduledTickView tickView, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, Random random ) {
        return direction.getAxis() == ( state.get( FACING ) ).rotateYClockwise().getAxis() && !state.canPlaceAt( world, pos )
            ? Blocks.AIR.getDefaultState()
            : super.getStateForNeighborUpdate( state, world, tickView, pos, direction, neighborPos, neighborState, random );
    }

    @Override
    protected BlockState rotate ( BlockState state, BlockRotation rotation ) {
        return state.with( FACING, rotation.rotate( state.get( FACING ) ) );
    }

    @Override
    protected BlockState mirror ( BlockState state, BlockMirror mirror ) {
        return state.rotate( mirror.getRotation( state.get( FACING ) ) );
    }

    @Override
    protected void appendProperties ( StateManager.Builder<Block, BlockState> builder ) {
        builder.add( FACING );
    }

    public static Block getForColor ( DyeColor color ) {
        return COLORED_BANNERS.getOrDefault( color, BlockInit.WHITE_WALL_HANGING_BANNER );
    }

    // Shape
    @Override
    protected VoxelShape getOutlineShape ( BlockState state, BlockView world, BlockPos pos, ShapeContext context ) {
        return OUTLINE_SHAPES.get( state.get ( FACING ) );
    }

    @Override
    protected VoxelShape getSidesShape ( BlockState state, BlockView world, BlockPos pos ) {
        return this.getOutlineShape ( state, world, pos, ShapeContext.absent() );
    }

    @Override
    protected VoxelShape getCollisionShape ( BlockState state, BlockView world, BlockPos pos, ShapeContext context ) {
        return switch ( state.get( FACING ) ) {
            case EAST, WEST -> EAST_WEST_COLLISION_SHAPE;
            default -> NORTH_SOUTH_COLLISION_SHAPE;
        };
    }

}
