package dev.trigam.field.entity.model;

import dev.trigam.field.block.entity.BlockEntityInit;
import dev.trigam.field.entity.renderer.banner.HangingBannerRenderer;
import dev.trigam.field.entity.renderer.banner.WallHangingBannerRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;

public class EntityModelInit {

    public static void registerModels() {
        BlockEntityRendererFactories.register( BlockEntityInit.HANGING_BANNER, HangingBannerRenderer::new );
        BlockEntityRendererFactories.register( BlockEntityInit.WALL_HANGING_BANNER, WallHangingBannerRenderer::new );
    }

    public static void init() {
        registerModels();
    }

}
