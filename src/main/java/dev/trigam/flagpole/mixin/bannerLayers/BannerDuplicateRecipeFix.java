package dev.trigam.flagpole.mixin.bannerLayers;

import dev.trigam.flagpole.config.FlagpoleConfig;
import net.minecraft.recipe.BannerDuplicateRecipe;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin( BannerDuplicateRecipe.class )
public class BannerDuplicateRecipeFix {

    @ModifyConstant(
        method = "matches(Lnet/minecraft/recipe/input/CraftingRecipeInput;Lnet/minecraft/world/World;)Z",
        constant = @Constant( intValue = 6 )
    )
    private int getMatchesLayerLimit( int limit ) {
        return FlagpoleConfig.bannerLayers;
    }

    @ModifyConstant(
        method = "craft(Lnet/minecraft/recipe/input/CraftingRecipeInput;Lnet/minecraft/registry/RegistryWrapper$WrapperLookup;)Lnet/minecraft/item/ItemStack;",
        constant = @Constant( intValue = 6 )
    )
    private int getCraftLayerLimit( int limit ) {
        return FlagpoleConfig.bannerLayers;
    }

}
