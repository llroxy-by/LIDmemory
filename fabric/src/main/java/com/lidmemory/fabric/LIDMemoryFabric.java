package com.lidmemory.fabric;

import com.lidmemory.LIDMemory;
import com.lidmemory.block.MangoPlantBlock;
import com.lidmemory.block.RelicWorkbenchBlock;
import com.lidmemory.menu.RelicWorkbenchMenu;
import com.lidmemory.menu.RelicWorkbenchMenuTypes;
import com.lidmemory.relic.RelicType;
import com.lidmemory.recipe.RelicWorkbenchRecipe;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public final class LIDMemoryFabric implements ModInitializer {
    public static final Item LLROXY_RELIC = registerItem(LIDMemory.LLROXY_RELIC_ID, new FabricRelicItem(RelicType.LLROXY, new Item.Properties().stacksTo(1).rarity(Rarity.RARE)));
    public static final Item IEGG_CUTE_RELIC = registerItem(LIDMemory.IEGG_CUTE_RELIC_ID, new FabricRelicItem(RelicType.IEGG_CUTE, new Item.Properties().stacksTo(1).rarity(Rarity.RARE)));
    public static final Item MANGO_RELIC = registerItem(LIDMemory.MANGO_RELIC_ID, new FabricRelicItem(RelicType.MANGO, new Item.Properties().stacksTo(1).rarity(Rarity.RARE)));
    public static final Item AX_RELIC = registerItem(LIDMemory.AX_RELIC_ID, new FabricRelicItem(RelicType.AX, new Item.Properties().stacksTo(1).rarity(Rarity.RARE)));
    public static final Item GARRY_RELIC = registerItem(LIDMemory.GARRY_RELIC_ID, new FabricRelicItem(RelicType.GARRY, new Item.Properties().stacksTo(1).rarity(Rarity.RARE)));

    public static final Block MANGO_PLANT_BLOCK = Registry.register(
        BuiltInRegistries.BLOCK,
        new ResourceLocation(LIDMemory.MOD_ID, LIDMemory.MANGO_PLANT_ID),
        new MangoPlantBlock()
    );
    public static final Item MANGO_PLANT_ITEM = registerItem(LIDMemory.MANGO_PLANT_ID,
        new BlockItem(MANGO_PLANT_BLOCK, new Item.Properties()));

    public static final Block RELIC_WORKBENCH_BLOCK = Registry.register(
        BuiltInRegistries.BLOCK,
        new ResourceLocation(LIDMemory.MOD_ID, LIDMemory.RELIC_WORKBENCH_ID),
        new RelicWorkbenchBlock()
    );
    public static final Item RELIC_WORKBENCH_ITEM = registerItem(
        LIDMemory.RELIC_WORKBENCH_ID,
        new BlockItem(RELIC_WORKBENCH_BLOCK, new Item.Properties().stacksTo(64).rarity(Rarity.COMMON))
    );

    public static final MenuType<RelicWorkbenchMenu> RELIC_WORKBENCH_MENU =
        Registry.register(BuiltInRegistries.MENU, RelicWorkbenchMenuTypes.RELIC_WORKBENCH_ID, RelicWorkbenchMenuTypes.createMenuType());

    private static final ResourceKey<PlacedFeature> MANGO_PATCH_KEY = ResourceKey.create(
        Registries.PLACED_FEATURE,
        new ResourceLocation(LIDMemory.MOD_ID, "patch_mango_plant")
    );

    @Override
    public void onInitialize() {
        RelicWorkbenchRecipe.initRecipes();

        BiomeModifications.addFeature(
            BiomeSelectors.foundInOverworld(),
            GenerationStep.Decoration.VEGETAL_DECORATION,
            MANGO_PATCH_KEY
        );

        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.TOOLS_AND_UTILITIES).register(entries -> {
            entries.accept(LLROXY_RELIC);
            entries.accept(IEGG_CUTE_RELIC);
            entries.accept(MANGO_RELIC);
            entries.accept(AX_RELIC);
            entries.accept(GARRY_RELIC);
            entries.accept(RELIC_WORKBENCH_ITEM);
        });
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.NATURAL_BLOCKS).register(entries -> {
            entries.accept(MANGO_PLANT_ITEM);
        });
    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(LIDMemory.MOD_ID, name), item);
    }
}