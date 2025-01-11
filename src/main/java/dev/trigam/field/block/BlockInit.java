package dev.trigam.field.block;

import dev.trigam.field.Field;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.DyeColor;
import java.util.function.Function;

public class BlockInit {

    // Hanging Banners
    public static final Block WHITE_HANGING_BANNER = registerBlock(
        "white_hanging_banner",
        settings -> new HangingBannerBlock( DyeColor.WHITE, settings ),
        AbstractBlock.Settings.copy( Blocks.WHITE_BANNER )
            .sounds( BlockSoundGroup.HANGING_SIGN )
    );

    public static final Block LIGHT_GRAY_HANGING_BANNER = registerBlock(
        "light_gray_hanging_banner",
        settings -> new HangingBannerBlock( DyeColor.LIGHT_GRAY, settings ),
        AbstractBlock.Settings.copy( Blocks.LIGHT_GRAY_BANNER )
            .sounds( BlockSoundGroup.HANGING_SIGN )
    );

    public static final Block GRAY_HANGING_BANNER = registerBlock(
        "gray_hanging_banner",
        settings -> new HangingBannerBlock( DyeColor.GRAY, settings ),
        AbstractBlock.Settings.copy( Blocks.GRAY_BANNER )
            .sounds( BlockSoundGroup.HANGING_SIGN )
    );

    public static final Block BLACK_HANGING_BANNER = registerBlock(
        "black_hanging_banner",
        settings -> new HangingBannerBlock( DyeColor.BLACK, settings ),
        AbstractBlock.Settings.copy( Blocks.BLACK_BANNER )
            .sounds( BlockSoundGroup.HANGING_SIGN )
    );

    public static final Block BROWN_HANGING_BANNER = registerBlock(
        "brown_hanging_banner",
        settings -> new HangingBannerBlock( DyeColor.BROWN, settings ),
        AbstractBlock.Settings.copy( Blocks.BROWN_BANNER )
            .sounds( BlockSoundGroup.HANGING_SIGN )
    );

    public static final Block RED_HANGING_BANNER = registerBlock(
        "red_hanging_banner",
        settings -> new HangingBannerBlock( DyeColor.RED, settings ),
        AbstractBlock.Settings.copy( Blocks.RED_BANNER )
            .sounds( BlockSoundGroup.HANGING_SIGN )
    );

    public static final Block ORANGE_HANGING_BANNER = registerBlock(
        "orange_hanging_banner",
        settings -> new HangingBannerBlock( DyeColor.ORANGE, settings ),
        AbstractBlock.Settings.copy( Blocks.ORANGE_BANNER )
            .sounds( BlockSoundGroup.HANGING_SIGN )
    );

    public static final Block YELLOW_HANGING_BANNER = registerBlock(
        "yellow_hanging_banner",
        settings -> new HangingBannerBlock( DyeColor.YELLOW, settings ),
        AbstractBlock.Settings.copy( Blocks.YELLOW_BANNER )
            .sounds( BlockSoundGroup.HANGING_SIGN )
    );

    public static final Block LIME_HANGING_BANNER = registerBlock(
        "lime_hanging_banner",
        settings -> new HangingBannerBlock( DyeColor.LIME, settings ),
        AbstractBlock.Settings.copy( Blocks.LIME_BANNER )
            .sounds( BlockSoundGroup.HANGING_SIGN )
    );

    public static final Block GREEN_HANGING_BANNER = registerBlock(
        "green_hanging_banner",
        settings -> new HangingBannerBlock( DyeColor.GREEN, settings ),
        AbstractBlock.Settings.copy( Blocks.GREEN_BANNER )
            .sounds( BlockSoundGroup.HANGING_SIGN )
    );

    public static final Block CYAN_HANGING_BANNER = registerBlock(
        "cyan_hanging_banner",
        settings -> new HangingBannerBlock( DyeColor.CYAN, settings ),
        AbstractBlock.Settings.copy( Blocks.CYAN_BANNER )
            .sounds( BlockSoundGroup.HANGING_SIGN )
    );

    public static final Block LIGHT_BLUE_HANGING_BANNER = registerBlock(
        "light_blue_hanging_banner",
        settings -> new HangingBannerBlock( DyeColor.LIGHT_BLUE, settings ),
        AbstractBlock.Settings.copy( Blocks.LIGHT_BLUE_BANNER )
            .sounds( BlockSoundGroup.HANGING_SIGN )
    );

    public static final Block BLUE_HANGING_BANNER = registerBlock(
        "blue_hanging_banner",
        settings -> new HangingBannerBlock( DyeColor.BLUE, settings ),
        AbstractBlock.Settings.copy( Blocks.BLUE_BANNER )
            .sounds( BlockSoundGroup.HANGING_SIGN )
    );

    public static final Block PURPLE_HANGING_BANNER = registerBlock(
        "purple_hanging_banner",
        settings -> new HangingBannerBlock( DyeColor.PURPLE, settings ),
        AbstractBlock.Settings.copy( Blocks.PURPLE_BANNER )
            .sounds( BlockSoundGroup.HANGING_SIGN )
    );

    public static final Block MAGENTA_HANGING_BANNER = registerBlock(
        "magenta_hanging_banner",
        settings -> new HangingBannerBlock( DyeColor.MAGENTA, settings ),
        AbstractBlock.Settings.copy( Blocks.MAGENTA_BANNER )
            .sounds( BlockSoundGroup.HANGING_SIGN )
    );

    public static final Block PINK_HANGING_BANNER = registerBlock(
        "pink_hanging_banner",
        settings -> new HangingBannerBlock( DyeColor.PINK, settings ),
        AbstractBlock.Settings.copy( Blocks.PINK_BANNER )
            .sounds( BlockSoundGroup.HANGING_SIGN )
    );

    // Wall Hanging Banners
    public static final Block WHITE_WALL_HANGING_BANNER = registerBlock(
        "white_wall_hanging_banner",
        settings -> new WallHangingBannerBlock( DyeColor.WHITE, settings ),
        AbstractBlock.Settings.copy( Blocks.WHITE_BANNER )
            .sounds( BlockSoundGroup.HANGING_SIGN )
    );

    public static final Block LIGHT_GRAY_WALL_HANGING_BANNER = registerBlock(
        "light_gray_wall_hanging_banner",
        settings -> new WallHangingBannerBlock( DyeColor.LIGHT_GRAY, settings ),
        AbstractBlock.Settings.copy( Blocks.LIGHT_GRAY_BANNER )
            .sounds( BlockSoundGroup.HANGING_SIGN )
    );

    public static final Block GRAY_WALL_HANGING_BANNER = registerBlock(
        "gray_wall_hanging_banner",
        settings -> new WallHangingBannerBlock( DyeColor.GRAY, settings ),
        AbstractBlock.Settings.copy( Blocks.GRAY_BANNER )
            .sounds( BlockSoundGroup.HANGING_SIGN )
    );

    public static final Block BLACK_WALL_HANGING_BANNER = registerBlock(
        "black_wall_hanging_banner",
        settings -> new WallHangingBannerBlock( DyeColor.BLACK, settings ),
        AbstractBlock.Settings.copy( Blocks.BLACK_BANNER )
            .sounds( BlockSoundGroup.HANGING_SIGN )
    );

    public static final Block BROWN_WALL_HANGING_BANNER = registerBlock(
        "brown_wall_hanging_banner",
        settings -> new WallHangingBannerBlock( DyeColor.BROWN, settings ),
        AbstractBlock.Settings.copy( Blocks.BROWN_BANNER )
            .sounds( BlockSoundGroup.HANGING_SIGN )
    );

    public static final Block RED_WALL_HANGING_BANNER = registerBlock(
        "red_wall_hanging_banner",
        settings -> new WallHangingBannerBlock( DyeColor.RED, settings ),
        AbstractBlock.Settings.copy( Blocks.RED_BANNER )
            .sounds( BlockSoundGroup.HANGING_SIGN )
    );

    public static final Block ORANGE_WALL_HANGING_BANNER = registerBlock(
        "orange_wall_hanging_banner",
        settings -> new WallHangingBannerBlock( DyeColor.ORANGE, settings ),
        AbstractBlock.Settings.copy( Blocks.ORANGE_BANNER )
            .sounds( BlockSoundGroup.HANGING_SIGN )
    );

    public static final Block YELLOW_WALL_HANGING_BANNER = registerBlock(
        "yellow_wall_hanging_banner",
        settings -> new WallHangingBannerBlock( DyeColor.YELLOW, settings ),
        AbstractBlock.Settings.copy( Blocks.YELLOW_BANNER )
            .sounds( BlockSoundGroup.HANGING_SIGN )
    );

    public static final Block LIME_WALL_HANGING_BANNER = registerBlock(
        "lime_wall_hanging_banner",
        settings -> new WallHangingBannerBlock( DyeColor.LIME, settings ),
        AbstractBlock.Settings.copy( Blocks.LIME_BANNER )
            .sounds( BlockSoundGroup.HANGING_SIGN )
    );

    public static final Block GREEN_WALL_HANGING_BANNER = registerBlock(
        "green_wall_hanging_banner",
        settings -> new WallHangingBannerBlock( DyeColor.GREEN, settings ),
        AbstractBlock.Settings.copy( Blocks.GREEN_BANNER )
            .sounds( BlockSoundGroup.HANGING_SIGN )
    );

    public static final Block CYAN_WALL_HANGING_BANNER = registerBlock(
        "cyan_wall_hanging_banner",
        settings -> new WallHangingBannerBlock( DyeColor.CYAN, settings ),
        AbstractBlock.Settings.copy( Blocks.CYAN_BANNER )
            .sounds( BlockSoundGroup.HANGING_SIGN )
    );

    public static final Block LIGHT_BLUE_WALL_HANGING_BANNER = registerBlock(
        "light_blue_wall_hanging_banner",
        settings -> new WallHangingBannerBlock( DyeColor.LIGHT_BLUE, settings ),
        AbstractBlock.Settings.copy( Blocks.LIGHT_BLUE_BANNER )
            .sounds( BlockSoundGroup.HANGING_SIGN )
    );

    public static final Block BLUE_WALL_HANGING_BANNER = registerBlock(
        "blue_wall_hanging_banner",
        settings -> new WallHangingBannerBlock( DyeColor.BLUE, settings ),
        AbstractBlock.Settings.copy( Blocks.BLUE_BANNER )
            .sounds( BlockSoundGroup.HANGING_SIGN )
    );

    public static final Block PURPLE_WALL_HANGING_BANNER = registerBlock(
        "purple_wall_hanging_banner",
        settings -> new WallHangingBannerBlock( DyeColor.PURPLE, settings ),
        AbstractBlock.Settings.copy( Blocks.PURPLE_BANNER )
            .sounds( BlockSoundGroup.HANGING_SIGN )
    );

    public static final Block MAGENTA_WALL_HANGING_BANNER = registerBlock(
        "magenta_wall_hanging_banner",
        settings -> new WallHangingBannerBlock( DyeColor.MAGENTA, settings ),
        AbstractBlock.Settings.copy( Blocks.MAGENTA_BANNER )
            .sounds( BlockSoundGroup.HANGING_SIGN )
    );

    public static final Block PINK_WALL_HANGING_BANNER = registerBlock(
        "pink_wall_hanging_banner",
        settings -> new WallHangingBannerBlock( DyeColor.PINK, settings ),
        AbstractBlock.Settings.copy( Blocks.PINK_BANNER )
            .sounds( BlockSoundGroup.HANGING_SIGN )
    );

    public static Block registerBlock ( String name, Function<AbstractBlock.Settings, Block> constructor, AbstractBlock.Settings settings ) {
        // Register block
        RegistryKey<Block> key = RegistryKey.of( RegistryKeys.BLOCK, Field.id( name ) );
        return Registry.register(
            Registries.BLOCK, key,
            constructor.apply( settings.registryKey( key ) )
        );
    }

    public static void init() {}

}
