package dev.trigam.field.attachments;

import dev.trigam.field.Field;
import dev.trigam.field.component.GlowingLayersComponent;
import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentSyncPredicate;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
import java.util.ArrayList;

public class AttachmentInit {

    public static final AttachmentType<GlowingLayersComponent> BANNER_GLOWING_LAYERS = AttachmentRegistry.create(
        Field.id( "banner_glowing_layers" ),
        builder -> builder
            .initializer( () -> new GlowingLayersComponent( new ArrayList<>() ) )
            .persistent( GlowingLayersComponent.CODEC )
            .syncWith( GlowingLayersComponent.PACKET_CODEC, AttachmentSyncPredicate.all() )
    );

    public static void init () {

    }

}
