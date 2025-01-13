package dev.trigam.field.impl;

import dev.trigam.field.component.GlowingLayersComponent;

public interface FieldBannerBlockEntity {

    GlowingLayersComponent field$getGlowingLayers();

    void field$setGlowing( int layerIndex, boolean glowing );

}