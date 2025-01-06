package dev.trigam.field.mixin;

import dev.trigam.field.component.GlowingLayersComponent;
import dev.trigam.field.component.ItemComponentInit;
import dev.trigam.field.config.FieldConfig;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.BannerPatternsComponent;
import net.minecraft.item.BannerItem;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import java.util.ArrayList;
import java.util.List;

@Mixin( BannerItem.class )
public class BannerItemTooltip {

    @Redirect(
        method = "appendTooltip",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/item/BannerItem;appendBannerTooltip(Lnet/minecraft/item/ItemStack;Ljava/util/List;)V"
        )
    )
    private void appendBannerTooltip( ItemStack stack, List<Text> tooltip ) {
        BannerPatternsComponent patterns = stack.get( DataComponentTypes.BANNER_PATTERNS );
        GlowingLayersComponent glowingLayers = stack.get( ItemComponentInit.GLOWING_LAYERS );

        boolean baseGlowing = glowingLayers != null && glowingLayers.isLayerGlowing( 0 );
        if ( baseGlowing ) {
            tooltip.add( Text.translatable( "block.field.banner.glowing" ) );
        }
        if (patterns != null) {
            List<BannerPatternsComponent.Layer> bannerLayers = patterns.layers().reversed();
            int numLayers = bannerLayers.size();
            int shownLayers = Math.min( numLayers, FieldConfig.bannerTooltipLayers );
            int remaining = numLayers - shownLayers;

            if ( numLayers > 0 ) {
                if ( baseGlowing ) tooltip.add( ScreenTexts.EMPTY );
                tooltip.add( Text.translatable( "block.field.banner.patterns" ) );
            }

            // Order with newest first
            for ( int i = 0; i < shownLayers; i++ ) {
                BannerPatternsComponent.Layer layer = bannerLayers.get( i );
                boolean layerGlowing = glowingLayers != null && glowingLayers.isLayerGlowing( i + 1 );
                MutableText layerTooltip = getLayerTooltip( layer, layerGlowing );
                tooltip.add( ScreenTexts.space().append( layerTooltip.formatted( Formatting.GRAY ) ) );
            }
            // If more than the tooltip layer limit, display the
            // number of remaining layers
            if ( remaining > 0 ) {
                Text remainingTooltip = Text.translatable( "block.field.banner.more", remaining )
                    .formatted( Formatting.GRAY, Formatting.ITALIC );
                tooltip.add( ScreenTexts.space().append( remainingTooltip ) );
            }
        }
    }

    @Unique
    public MutableText getLayerTooltip(BannerPatternsComponent.Layer layer, boolean isGlowing ) {
        List<Text> parts = new ArrayList<>();

        parts.add( Text.translatable( "block.field.banner." + layer.color().getName() ) );
        parts.add( Text.translatable( layer.pattern().value().translationKey() ) );

        MutableText glowing = Text.literal( "(" )
            .append( Text.translatable( "block.field.banner.glowing" ) )
            .append( Text.literal( ")" ) );
        if ( isGlowing ) parts.add( glowing );

        // Build tooltip
        MutableText tooltip = Text.empty();
        tooltip.append( parts.getFirst() );
        for ( int i = 1; i < parts.size(); i++ ) {
            tooltip.append( ScreenTexts.space() );
            tooltip.append( parts.get( i ) );
        }

        return tooltip;
    }

}
