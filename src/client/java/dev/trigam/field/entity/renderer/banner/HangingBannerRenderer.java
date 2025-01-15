package dev.trigam.field.entity.renderer.banner;

import dev.trigam.field.Field;
import dev.trigam.field.FieldClient;
import dev.trigam.field.block.entity.HangingBannerBlockEntity;
import dev.trigam.field.entity.model.EntityModelLayerInit;
import dev.trigam.field.entity.model.banner.BannerFlagModel;
import dev.trigam.field.entity.model.banner.HangingBannerModel;
import dev.trigam.field.impl.FieldBannerBlockEntity;
import net.minecraft.block.BannerBlock;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BannerBlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.entity.model.LoadedEntityModels;
import net.minecraft.client.render.model.ModelBaker;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.RotationPropertyHelper;

public class HangingBannerRenderer implements BlockEntityRenderer<HangingBannerBlockEntity> {

    public final HangingBannerModel bannerModel;
    public final BannerFlagModel flagModel;

    public static final SpriteIdentifier SPRITE = new SpriteIdentifier(
        TexturedRenderLayers.BANNER_PATTERNS_ATLAS_TEXTURE,
        Field.id( "entity/banner/base/hanging_banner_base" )
    );

    public HangingBannerRenderer ( BlockEntityRendererFactory.Context context ) {
        this( context.getLoadedEntityModels() );
    }
    public HangingBannerRenderer ( LoadedEntityModels models ) {
        this.bannerModel = new HangingBannerModel( models.getModelPart( EntityModelLayerInit.HANGING_BANNER ) );
        this.flagModel = new BannerFlagModel( models.getModelPart( EntityModelLayerInit.BANNER_FLAG ) );
    }

    @Override
    public void render(
        HangingBannerBlockEntity banner, float tickDelta, MatrixStack matrices,
        VertexConsumerProvider vertexConsumer, int light, int overlay
    ) {
        renderFrame( banner, matrices, vertexConsumer, light, overlay );
        renderFlag( banner, matrices, vertexConsumer, light, overlay, tickDelta );
    }

    public void renderFrame(
        HangingBannerBlockEntity banner, MatrixStack matrices,
        VertexConsumerProvider vertexProvider, int light, int overlay
    ) {
        matrices.push();

        // Transform
        matrices.translate( 0.5F, 0F, 0.5F );
        float rotation = -RotationPropertyHelper.toDegrees( banner.getCachedState().get( BannerBlock.ROTATION ) );
        matrices.multiply( RotationAxis.POSITIVE_Y.rotationDegrees( rotation ) );
        matrices.scale( 0.6666667F, -0.6666667F, -0.6666667F );

        VertexConsumer vertexConsumer = SPRITE.getVertexConsumer( vertexProvider, RenderLayer::getEntityCutoutNoCull );
        this.bannerModel.render( matrices, vertexConsumer, light, overlay );

        matrices.pop();
    }

    public void renderFlag(
        HangingBannerBlockEntity banner, MatrixStack matrices,
        VertexConsumerProvider vertexProvider, int light, int overlay,
        float tickDelta
    ) {
        matrices.push();

        // Transform
        matrices.translate( 0.5F, -1F, 0.5F );
        float rotation = -RotationPropertyHelper.toDegrees( banner.getCachedState().get( BannerBlock.ROTATION ) );
        matrices.multiply( RotationAxis.POSITIVE_Y.rotationDegrees( rotation ) );
        matrices.scale( 0.6666667F, -0.6666667F, -0.6666667F );

        // Sway
        long worldTime = banner.getWorld() != null ? banner.getWorld().getTime() : 0L;
        BlockPos pos = banner.getPos();
        float sway = ( Math.floorMod( ( pos.getX() * 7L + pos.getY() * 9L + pos.getZ() * 13L ) + worldTime, 100L ) + tickDelta ) / 100.0F;
        this.flagModel.sway( sway );

        VertexConsumer vertexConsumer = ModelBaker.BANNER_BASE.getVertexConsumer( vertexProvider, RenderLayer::getEntitySolid );
        this.flagModel.render( matrices, vertexConsumer, light, overlay );

        FieldClient.setGlowingContext( ( FieldBannerBlockEntity ) banner );
        BannerBlockEntityRenderer.renderCanvas(
            matrices, vertexProvider, light, overlay,
            this.flagModel.getRootPart(), SPRITE, true,
            banner.getColorForState(), banner.getPatterns()
        );

        matrices.pop();
    }
}
