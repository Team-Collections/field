package dev.trigam.field.entity.model;

import dev.trigam.field.Field;
import dev.trigam.field.entity.model.banner.BannerFlagModel;
import dev.trigam.field.entity.model.banner.HangingBannerModel;
import dev.trigam.field.entity.model.banner.WallHangingBannerModel;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;

public class EntityModelLayerInit {

    // Banner
    public static final EntityModelLayer BANNER_FLAG = new EntityModelLayer(
        Field.id( "banner" ),
        "flag"
    );

    // Hanging Banner
    public static final EntityModelLayer HANGING_BANNER = new EntityModelLayer(
        Field.id( "hanging_banner" ),
        "main"
    );
    public static final EntityModelLayer WALL_HANGING_BANNER = new EntityModelLayer(
        Field.id( "wall_hanging_banner" ),
        "main"
    );

    // Hanging Wall Banner

    // Side Banner

    public static void registerEntityModelLayers() {
        // Banner
        EntityModelLayerRegistry.registerModelLayer( BANNER_FLAG, BannerFlagModel::getTexturedModelData );

        // Hanging Banner
        EntityModelLayerRegistry.registerModelLayer( HANGING_BANNER, HangingBannerModel::getTexturedModelData );
        EntityModelLayerRegistry.registerModelLayer( WALL_HANGING_BANNER, WallHangingBannerModel::getTexturedModelData );
    }

    public static void init() {
        registerEntityModelLayers();
    }

}
