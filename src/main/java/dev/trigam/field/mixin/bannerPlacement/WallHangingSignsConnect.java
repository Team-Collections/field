package dev.trigam.field.mixin.bannerPlacement;

import dev.trigam.field.tag.BlockTagInit;
import net.minecraft.block.Block;
import net.minecraft.block.WallHangingSignBlock;
import net.minecraft.registry.tag.TagKey;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin( WallHangingSignBlock.class )
public class WallHangingSignsConnect {

    @ModifyArg(
        method = "canAttachTo",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/block/BlockState;isIn(Lnet/minecraft/registry/tag/TagKey;)Z"
        )
    )
    private TagKey<Block> replaceBlockTag ( TagKey<Block> original ) {
        return BlockTagInit.WALL_HANGING_DECORATIONS;
    }

}
