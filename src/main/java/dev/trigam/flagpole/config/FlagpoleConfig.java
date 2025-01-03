package dev.trigam.flagpole.config;

import eu.midnightdust.lib.config.MidnightConfig;

public class FlagpoleConfig extends MidnightConfig {

    // Categories
    public static final String MAIN = "Main";

    @Entry( category = MAIN, min = 0, max = 64 ) public static int bannerLayers = 32;
    @Entry( category = MAIN, min = 0, max = 64 ) public static int bannerTooltipLayers = 8;

    @Entry( category = MAIN, min = 0, max = 64 ) public static int bannerStackSize = 64;

}
