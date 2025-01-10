package dev.trigam.field.entity.model;

import dev.trigam.field.block.entity.BlockEntityInit;
import dev.trigam.field.entity.renderer.banner.HangingBannerRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;

public class EntityModelInit {

    public static void registerModels() {
        BlockEntityRendererFactories.register( BlockEntityInit.HANGING_BANNER, HangingBannerRenderer::new );
    }

    public static void init() {
        registerModels();
    }

}
