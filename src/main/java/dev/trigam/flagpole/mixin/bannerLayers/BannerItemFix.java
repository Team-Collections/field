package dev.trigam.flagpole.mixin.bannerLayers;

import dev.trigam.flagpole.config.FlagpoleConfig;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.BannerPatternsComponent;
import net.minecraft.item.BannerItem;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.List;

@Mixin( BannerItem.class )
public class BannerItemFix {

    @Redirect(
        method = "appendTooltip",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/item/BannerItem;appendBannerTooltip(Lnet/minecraft/item/ItemStack;Ljava/util/List;)V"
        )
    )
    private void appendBannerTooltip( ItemStack stack, List<Text> tooltip ) {
        BannerPatternsComponent patterns = stack.get( DataComponentTypes.BANNER_PATTERNS );
        if (patterns != null) {
            List<BannerPatternsComponent.Layer> bannerLayers = patterns.layers().reversed();
            int numLayers = bannerLayers.size();
            int shownLayers = Math.min( numLayers, FlagpoleConfig.bannerTooltipLayers );
            int remaining = numLayers - shownLayers;

            // Order with newest first
            for ( int i = 0; i < shownLayers; i++ ) {
                BannerPatternsComponent.Layer layer = bannerLayers.get( i );
                tooltip.add( layer.getTooltipText().formatted( Formatting.GRAY ) );
            }
            // If more than the tooltip layer limit, display the
            // number of remaining layers
            if ( remaining > 0 ) {
                Text remainingTooltip = Text.translatable( "block.flagpole.banner.more", remaining )
                    .formatted( Formatting.GRAY, Formatting.ITALIC );
                tooltip.add( remainingTooltip );
            }
        }
    }

}
