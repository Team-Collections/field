package dev.trigam.field.event;

import net.fabricmc.fabric.api.event.player.UseBlockCallback;

public class EventInit {

    public static void init () {

        UseBlockCallback.EVENT.register( ToggleBannerGlow::toggleBannerGlow );

    }

}
