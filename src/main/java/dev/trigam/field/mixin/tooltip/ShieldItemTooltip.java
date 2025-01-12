package dev.trigam.field.mixin.tooltip;

import dev.trigam.field.util.Tooltip;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShieldItem;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import java.util.List;

@Mixin( ShieldItem.class )
public class ShieldItemTooltip {

    @Redirect(
        method = "appendTooltip",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/item/BannerItem;appendBannerTooltip(Lnet/minecraft/item/ItemStack;Ljava/util/List;)V"
        )
    )
    private void appendBannerTooltip ( ItemStack stack, List<Text> tooltip ) {
        Tooltip.appendBannerTooltip( stack, tooltip );
    }
}