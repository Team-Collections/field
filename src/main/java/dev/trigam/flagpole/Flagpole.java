package dev.trigam.flagpole;

import dev.trigam.flagpole.config.FlagpoleConfig;
import dev.trigam.flagpole.item.InventoryInit;
import dev.trigam.flagpole.item.ItemInit;
import dev.trigam.flagpole.item.ModifyItemInit;
import dev.trigam.flagpole.tag.BannerPatternTagInit;
import eu.midnightdust.lib.config.MidnightConfig;
import net.fabricmc.api.ModInitializer;

import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Flagpole implements ModInitializer {
	public static final String MOD_ID = "flagpole";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static Identifier id (String path) {
		return Identifier.of(MOD_ID, path);
	}

	@Override
	public void onInitialize() {
		MidnightConfig.init(MOD_ID, FlagpoleConfig.class);

		ItemInit.register();
		InventoryInit.sort();
		ModifyItemInit.init();
		BannerPatternTagInit.register();
	}
}