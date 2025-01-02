package dev.trigam.flagpole.mixin.bannerLayers;

import dev.trigam.flagpole.config.FlagpoleConfig;
import net.minecraft.screen.LoomScreenHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin( LoomScreenHandler.class )
public class LoomScreenHandlerFix {

    @ModifyConstant(
        method = "onContentChanged",
        constant = @Constant( intValue = 6 )
    )
    private int getLayerLimit( int limit ) {
        return FlagpoleConfig.bannerLayers;
    }

}
