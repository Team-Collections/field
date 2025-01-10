package dev.trigam.field.block.entity;

import dev.trigam.field.Field;
import dev.trigam.field.block.BlockInit;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

public class BlockEntityInit {

    public static final BlockEntityType<HangingBannerBlockEntity> HANGING_BANNER = registerBlockEntity(
        "hanging_banner", HangingBannerBlockEntity::new,
        BlockInit.WHITE_HANGING_BANNER, BlockInit.LIGHT_GRAY_HANGING_BANNER,
        BlockInit.GRAY_HANGING_BANNER, BlockInit.BLACK_HANGING_BANNER,
        BlockInit.BROWN_HANGING_BANNER, BlockInit.RED_HANGING_BANNER,
        BlockInit.ORANGE_HANGING_BANNER, BlockInit.YELLOW_HANGING_BANNER,
        BlockInit.LIME_HANGING_BANNER, BlockInit.GREEN_HANGING_BANNER,
        BlockInit.CYAN_HANGING_BANNER, BlockInit.LIGHT_BLUE_HANGING_BANNER,
        BlockInit.BLUE_HANGING_BANNER, BlockInit.PURPLE_HANGING_BANNER,
        BlockInit.MAGENTA_HANGING_BANNER, BlockInit.PINK_HANGING_BANNER
    );

    public static <T extends BlockEntity> BlockEntityType<T> registerBlockEntity (
        String name,
        FabricBlockEntityTypeBuilder.Factory<T> factory,
        Block... blocks
    ) {
        BlockEntityType<T> type = FabricBlockEntityTypeBuilder.create( factory, blocks ).build();
        RegistryKey<BlockEntityType<?>> key = RegistryKey.of( RegistryKeys.BLOCK_ENTITY_TYPE, Field.id( name ) );
        return Registry.register( Registries.BLOCK_ENTITY_TYPE, key, type );
    }

    public static void init() {}

}
