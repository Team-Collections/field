package dev.trigam.field.entity.model.banner;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;

@Environment( EnvType.CLIENT )
public class WallHangingBannerModel extends Model {

    public WallHangingBannerModel(ModelPart root ) { super( root, RenderLayer::getEntitySolid ); }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData root = modelData.getRoot();

        // Crossbar
        root.addChild( "bar", ModelPartBuilder.create()
                .uv(0, 42)
                .cuboid(-10F, -20F, -1F, 20F, 2F, 2F),
            ModelTransform.NONE
        );

        // Support Bar
        root.addChild( "support_bar", ModelPartBuilder.create()
                .uv(0, 48)
                .cuboid(-8F, -16F, -2F, 16F, 2F, 4F),
            ModelTransform.NONE
                .scaled( 1.5F )
        );

        // Chains
        root.addChild( "chain_1", ModelPartBuilder.create()
                .uv(44, -3)
                .cuboid(0F, 23F, -1.5F, 0F, 2F, 3F),
            ModelTransform.of(-5F, -45F, 0F, 0F, -0.7854F, 0F)
        );
        root.addChild( "chain_2", ModelPartBuilder.create()
                .uv(50, -3)
                .cuboid(0F, 23F, -1.5F, 0F, 2F, 3F),
            ModelTransform.of(-5F, -45F, 0F, 0F, 0.7854F, 0F)
        );
        root.addChild( "chain_3", ModelPartBuilder.create()
                .uv(44, -3)
                .cuboid(0F, 23F, -1.5F, 0F, 2F, 3F),
            ModelTransform.of(5F, -45F, 0F, 0F, -0.7854F, 0F)
        );
        root.addChild( "chain_4", ModelPartBuilder.create()
                .uv(50, -3)
                .cuboid(0F, 23F, -1.5F, 0F, 2F, 3F),
            ModelTransform.of(5F, -45F, 0F, 0F, 0.7854F, 0F)
        );

        return TexturedModelData.of( modelData, 64, 64 );
    }
}