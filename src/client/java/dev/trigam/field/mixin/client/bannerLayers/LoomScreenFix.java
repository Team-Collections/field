package dev.trigam.field.mixin.client.bannerLayers;

import dev.trigam.field.config.FieldConfig;
import net.minecraft.client.gui.screen.ingame.LoomScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin( LoomScreen.class )
public class LoomScreenFix {

    @ModifyConstant(
        method = "onInventoryChanged",
        constant = @Constant( intValue = 6 )
    )
    private int getLayerLimit( int limit ) {
        return FieldConfig.bannerLayers;
    }

}
