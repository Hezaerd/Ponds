package com.hezaerd;

import com.hezaerd.registry.ModLootTables;
import com.hezaerd.utils.ModLib;
import net.fabricmc.api.ModInitializer;

public class Ponds implements ModInitializer {
	
	@Override
	public void onInitialize() {
		ModLib.LOGGER.info("Initializing..");

		ModLootTables.init();
	}
}