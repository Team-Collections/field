package dev.trigam.field.network;

import dev.trigam.field.Field;
import dev.trigam.field.component.GlowingLayersComponent;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.math.BlockPos;
import java.util.Optional;

// Adapted from: https://github.com/UltrusBot/glow-banners/blob/1.21/common/src/main/java/me/ultrusmods/glowingbanners/network/s2c/SyncBannerGlowS2CPacket.java
public record SyncBannerGlowS2CPacket ( BlockPos pos, Optional<GlowingLayersComponent> glowingLayers ) implements CustomPayload {

    public static final CustomPayload.Id<SyncBannerGlowS2CPacket> ID =
        new Id<>(Field.id("sync_glowing_layers")
    );

    @Override
    public Id<? extends CustomPayload> getId () {
        return null;
    }
}
