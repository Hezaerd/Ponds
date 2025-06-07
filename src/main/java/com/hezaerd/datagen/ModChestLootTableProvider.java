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
                // Pool 1: Music Disc
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
        
        lootTableBiConsumer.accept(ModLootTables.DESERT_OASIS_BARREL, LootTable.builder()
                // Pool 1: Music Discs
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1.0F)) // Guarantees one item from this pool
                                .with(ItemEntry.builder(Items.MUSIC_DISC_MALL).weight(4)) // 50%
                                .with(ItemEntry.builder(Items.MUSIC_DISC_OTHERSIDE).weight(2)) // 25%
                                .with(ItemEntry.builder(Items.MUSIC_DISC_RELIC).weight(1)) // 12.5%
                                .with(ItemEntry.builder(Items.MUSIC_DISC_CREATOR).weight(1)) // 12.5%
                )
                // Pool 2: Gold Nuggets (guaranteed, multiple)
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1.0F)) // Always rolls once
                                .with(
                                        ItemEntry.builder(Items.GOLD_NUGGET)
                                                .apply(
                                                        SetCountLootFunction.builder(
                                                                UniformLootNumberProvider.create(
                                                                        3.0F,
                                                                        7.0F
                                                                )
                                                        )
                                                ) // 3 to 7 nuggets
                                )
                )
                // Pool 3: Emeralds (lower chance, fewer quantity)
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1.0F))
                                .conditionally(
                                        RandomChanceLootCondition.builder(0.3F)
                                ) // 30% chance to get emeralds
                                .with(
                                        ItemEntry.builder(Items.EMERALD)
                                                .apply(
                                                        SetCountLootFunction.builder(
                                                                UniformLootNumberProvider.create(
                                                                        1.0F,
                                                                        2.0F
                                                                )
                                                        )
                                                ) // 1 or 2 emeralds if successful
                                )
                )
                // Pool 4: Empty Bucket (low chance, fixed quantity)
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1.0F))
                                .conditionally(
                                        RandomChanceLootCondition.builder(0.2F)
                                ) // 20% chance for a bucket
                                .with(
                                        ItemEntry.builder(Items.BUCKET)
                                                .apply(
                                                        SetCountLootFunction.builder(
                                                                ConstantLootNumberProvider.create(1.0F)
                                                        )
                                                ) // Always 1 bucket if successful
                                )
                )
                // Pool 5: Cobwebs (moderate chance, multiple)
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1.0F))
                                .conditionally(
                                        RandomChanceLootCondition.builder(0.6F)
                                ) // 60% chance for cobwebs
                                .with(
                                        ItemEntry.builder(Items.COBWEB)
                                                .apply(
                                                        SetCountLootFunction.builder(
                                                                UniformLootNumberProvider.create(
                                                                        1.0F,
                                                                        3.0F
                                                                )
                                                        )
                                                ) // 1 to 3 cobwebs if successful
                                )
                )
                // Pool 6: Brush (very low chance, single)
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1.0F))
                                .conditionally(
                                        RandomChanceLootCondition.builder(0.05F)
                                ) // 5% chance for a brush
                                .with(
                                        ItemEntry.builder(Items.BRUSH)
                                                .apply(
                                                        SetCountLootFunction.builder(
                                                                ConstantLootNumberProvider.create(1.0F)
                                                        )
                                                ) // Always 1 brush if successful
                                )
                )
        );
    }
}
