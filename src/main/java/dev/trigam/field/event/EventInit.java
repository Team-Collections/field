package dev.trigam.field.event;

import net.fabricmc.fabric.api.event.player.UseBlockCallback;

public class EventInit {

    public static void register() {

        UseBlockCallback.EVENT.register( ToggleBannerGlow::toggleBannerGlow );

    }

}
