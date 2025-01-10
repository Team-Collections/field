package dev.trigam.field.entity.model.banner;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.math.MathHelper;

@Environment( EnvType.CLIENT )
public class BannerFlagModel extends Model {

    public final ModelPart flag;

    public BannerFlagModel( ModelPart root ) {
        super( root, RenderLayer::getEntitySolid );
        this.flag = root.getChild( "flag" );
    }

    public static TexturedModelData getTexturedModelData () {
        ModelData model = new ModelData();
        ModelPartData root = model.getRoot();

        // Flag
        root.addChild(
            "flag",
            ModelPartBuilder.create()
                .uv(0, 0)
                .cuboid(-10.0F, 0.0F, -2.0F, 20.0F, 40.0F, 1.0F),
            ModelTransform.pivot(0.0F, -44.0F, 0.0F)
        );

        return TexturedModelData.of( model, 64, 64 );
    }

    public void sway ( float rotation ) {
        this.flag.pitch = (-0.0125F + 0.01F * MathHelper.cos(((float)Math.PI * 2F) * rotation)) * (float)Math.PI;
    }

}
