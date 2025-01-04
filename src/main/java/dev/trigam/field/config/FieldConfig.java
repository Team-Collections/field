package dev.trigam.field.config;

import eu.midnightdust.lib.config.MidnightConfig;

public class FieldConfig extends MidnightConfig {

    // Categories
    public static final String MAIN = "Main";

    @Entry( category = MAIN, min = 0, max = 64 ) public static int bannerLayers = 32;
    @Entry( category = MAIN, min = 0, max = 64 ) public static int bannerTooltipLayers = 8;

    @Entry( category = MAIN, min = 0, max = 64 ) public static int bannerStackSize = 64;

    @Entry( category = MAIN ) public static boolean loomTooltip = true;

}
