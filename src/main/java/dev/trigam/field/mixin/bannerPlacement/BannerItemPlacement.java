package dev.trigam.field.mixin.bannerPlacement;

import dev.trigam.field.block.HangingBannerBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BannerItem;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.VerticallyAttachableBlockItem;
import net.minecraft.util.math.Direction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

// Adapted from: https://github.com/anweisemods/BannersEverywhere/blob/1.21/src/main/java/net/anweisen/bannerseverywhere/mixin/BannerItemMixin.java
@Mixin( VerticallyAttachableBlockItem.class )
public class BannerItemPlacement {

    @Inject(
        method = "getPlacementState",
        at = @At( value = "HEAD" ),
        cancellable = true
    )
    public void getPlacementState( ItemPlacementContext context, CallbackInfoReturnable<BlockState> cir ) {
        if ( context.getPlayer() == null ) return;

        VerticallyAttachableBlockItem thiz = (VerticallyAttachableBlockItem) (Object) this;
        if ( thiz instanceof BannerItem banner ) {
            Direction side = context.getSide();

            if ( side == Direction.DOWN ) {
                Block hangingBanner = HangingBannerBlock.getHangingBanner( banner.getColor() );
                BlockState state = hangingBanner.getPlacementState( context );
                cir.setReturnValue( state );
            }
        }
    }

}
