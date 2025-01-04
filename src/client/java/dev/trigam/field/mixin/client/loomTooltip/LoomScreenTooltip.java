package dev.trigam.field.mixin.client.loomTooltip;

import com.llamalad7.mixinextras.sugar.Local;
import dev.trigam.field.config.FieldConfig;
import net.minecraft.block.entity.BannerPattern;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.LoomScreen;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.apache.commons.compress.utils.Lists;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import java.util.List;

@Mixin( LoomScreen.class )
public class LoomScreenTooltip {

    @Inject(
        method = "drawBackground",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/gui/screen/ingame/LoomScreen;drawBanner(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/registry/entry/RegistryEntry;II)V",
            shift = At.Shift.AFTER
        )
    )
    private void drawBannerPatternTooltip(
        DrawContext context, float delta, int mouseX, int mouseY, CallbackInfo ci,
        @Local( ordinal = 0 ) boolean isHovered, @Local( ordinal = 10 ) int patternIndex
    ) {
        LoomScreen thiz = ( LoomScreen )( Object ) this;
        if ( isHovered && FieldConfig.loomTooltip) {
            RegistryEntry<BannerPattern> hoveredPattern = thiz.getScreenHandler().getBannerPatterns().get( patternIndex );
            List<Text> tooltipFields = Lists.newArrayList();
            boolean advancedTooltips = MinecraftClient.getInstance().options.advancedItemTooltips;

            Text patternName = Text.translatable( hoveredPattern.value().translationKey() );
            Text patternId = Text.translatable( hoveredPattern.getIdAsString() )
                .formatted( Formatting.DARK_GRAY );

            tooltipFields.add( patternName );
            if ( advancedTooltips ) tooltipFields.add( patternId );

            context.drawTooltip(
                MinecraftClient.getInstance().textRenderer, tooltipFields,
                mouseX, mouseY
            );
        }
    }

}
