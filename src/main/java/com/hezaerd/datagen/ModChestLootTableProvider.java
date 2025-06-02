package com.hezaerd.datagen;

import com.hezaerd.registry.ModLootTables;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class ModChestLootTableProvider extends SimpleFabricLootTableProvider {
    public ModChestLootTableProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(output, registryLookup, LootContextTypes.CHEST);
    }
    
    @Override
    public void accept(BiConsumer<RegistryKey<LootTable>, LootTable.Builder> lootTableBiConsumer) {
        lootTableBiConsumer.accept(ModLootTables.CHERRY_POND_BARREL, LootTable.builder()
                // Pool 1: Music Disc (100% chance for one random disc from 5)
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1.0F)) // Guarantees one item from this pool
                                .with(ItemEntry.builder(Items.MUSIC_DISC_MELLOHI))
                                .with(ItemEntry.builder(Items.MUSIC_DISC_BLOCKS))
                                .with(ItemEntry.builder(Items.MUSIC_DISC_OTHERSIDE))
                                .with(ItemEntry.builder(Items.MUSIC_DISC_MALL))
                                .with(ItemEntry.builder(Items.MUSIC_DISC_FAR))
                        // Each disc has an equal chance (1/5) of being chosen.
                )
                // Pool 2: Guaranteed Cooked Beef (exactly 1)
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1.0F))
                                .with(
                                        ItemEntry.builder(Items.COOKED_BEEF)
                                                .apply(
                                                        SetCountLootFunction.builder(
                                                                ConstantLootNumberProvider.create(1.0F)
                                                        )
                                                )
                                )
                )
                // Pool 3: Guaranteed Bread (exactly 1)
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1.0F))
                                .with(
                                        ItemEntry.builder(Items.BREAD)
                                                .apply(
                                                        SetCountLootFunction.builder(
                                                                ConstantLootNumberProvider.create(1.0F)
                                                        )
                                                )
                                )
                )
                // Pool 4: Bonus Cooked Beef (50% chance for 1-2 more)
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1.0F)) // One attempt to add items from this pool
                                .conditionally(
                                        RandomChanceLootCondition.builder(0.5F)
                                ) // 50% chance for this pool to activate
                                .with(
                                        ItemEntry.builder(Items.COOKED_BEEF)
                                                .apply(
                                                        SetCountLootFunction.builder( // Gives 1 or 2 additional beef
                                                                UniformLootNumberProvider.create(
                                                                        1.0F,
                                                                        2.0F
                                                                )
                                                        )
                                                )
                                )
                )
                // Pool 5: Bonus Bread (50% chance for 1-2 more)
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1.0F))
                                .conditionally(
                                        RandomChanceLootCondition.builder(0.5F)
                                ) // 50% chance for this pool to activate
                                .with(
                                        ItemEntry.builder(Items.BREAD)
                                                .apply(
                                                        SetCountLootFunction.builder( // Gives 1 or 2 additional bread
                                                                UniformLootNumberProvider.create(
                                                                        1.0F,
                                                                        2.0F
                                                                )
                                                        )
                                                )
                                )
                )
                // Pool 6: Coal (1-4 items in different stacks)
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1.0F)) // One attempt to add items from this pool
                                .with(
                                        ItemEntry.builder(Items.COAL)
                                                .apply(
                                                        SetCountLootFunction.builder( // Gives 1-4 coal in different stacks
                                                                UniformLootNumberProvider.create(
                                                                        1.0F,
                                                                        4.0F
                                                                )
                                                        )
                                                )
                                )
                )
                // Pool 7: Cherry Sapling (1-2 items)
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1.0F)) // One attempt to add items from this pool
                                .with(
                                        ItemEntry.builder(Items.CHERRY_SAPLING)
                                                .apply(
                                                        SetCountLootFunction.builder( // Gives 1-2 cherry saplings
                                                                UniformLootNumberProvider.create(
                                                                        1.0F,
                                                                        2.0F
                                                                )
                                                        )
                                                )
                                )
                )
        );
    }
}
