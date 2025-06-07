package com.hezaerd.registry;

import com.hezaerd.utils.ModLib;
import net.minecraft.loot.LootTable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

public class ModLootTables {
    public static final RegistryKey<LootTable> CHERRY_POND_BARREL = register("chests/cherry_pond_barrel");
    public static final RegistryKey<LootTable> JUNGLE_POND_BARREL = register("chests/jungle_pond_barrel");
    public static final RegistryKey<LootTable> DESERT_OASIS_BARREL = register("chests/desert_oasis_barrel");
    
    public static void init() {
        ModLib.LOGGER.info("Successfully registered loot tables!");
    }
    
    private static RegistryKey<LootTable> register(String id) {
        return RegistryKey.of(RegistryKeys.LOOT_TABLE, ModLib.of(id));
    }
}
