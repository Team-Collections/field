package dev.trigam.field;

import dev.trigam.field.entity.model.EntityModelInit;
import dev.trigam.field.entity.model.EntityModelLayerInit;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FieldClient implements ClientModInitializer {
	public static final String MOD_ID = "field";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static Identifier id (String path) {
		return Identifier.of(MOD_ID, path);
	}

	@Override
	public void onInitializeClient() {

		EntityModelInit.init();
		EntityModelLayerInit.init();

	}
}