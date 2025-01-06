package dev.trigam.field;

import dev.trigam.field.component.ComponentInit;
import dev.trigam.field.component.ItemComponentInit;
import dev.trigam.field.config.FieldConfig;
import dev.trigam.field.event.EventInit;
import dev.trigam.field.item.InventoryInit;
import dev.trigam.field.item.ItemInit;
import dev.trigam.field.item.ModifyItemInit;
import dev.trigam.field.tag.BannerPatternTagInit;
import eu.midnightdust.lib.config.MidnightConfig;
import net.fabricmc.api.ModInitializer;

import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Field implements ModInitializer {
	public static final String MOD_ID = "field";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static Identifier id (String path) {
		return Identifier.of(MOD_ID, path);
	}

	@Override
	public void onInitialize() {
		MidnightConfig.init(MOD_ID, FieldConfig.class);

		ItemInit.register();
		InventoryInit.sort();
		ModifyItemInit.init();

		EventInit.register();
		ComponentInit.register();
		ItemComponentInit.register();

		BannerPatternTagInit.register();
	}
}