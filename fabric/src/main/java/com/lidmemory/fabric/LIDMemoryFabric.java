package com.lidmemory.fabric;

import com.lidmemory.LIDMemory;
import com.lidmemory.block.RelicWorkbenchBlock;
import com.lidmemory.menu.RelicWorkbenchMenu;
import com.lidmemory.menu.RelicWorkbenchMenuTypes;
import com.lidmemory.relic.RelicType;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;

public final class LIDMemoryFabric implements ModInitializer {
    public static final Item LLROXY_RELIC = registerItem(LIDMemory.LLROXY_RELIC_ID, new FabricRelicItem(RelicType.LLROXY, new Item.Properties().stacksTo(1).rarity(Rarity.RARE)));
    public static final Item IEGG_CUTE_RELIC = registerItem(LIDMemory.IEGG_CUTE_RELIC_ID, new FabricRelicItem(RelicType.IEGG_CUTE, new Item.Properties().stacksTo(1).rarity(Rarity.RARE)));
    public static final Item MANGO_RELIC = registerItem(LIDMemory.MANGO_RELIC_ID, new FabricRelicItem(RelicType.MANGO, new Item.Properties().stacksTo(1).rarity(Rarity.RARE)));
    public static final Item AX_RELIC = registerItem(LIDMemory.AX_RELIC_ID, new FabricRelicItem(RelicType.AX, new Item.Properties().stacksTo(1).rarity(Rarity.RARE)));

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

    @Override
    public void onInitialize() {
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.TOOLS_AND_UTILITIES).register(entries -> {
            entries.accept(LLROXY_RELIC);
            entries.accept(IEGG_CUTE_RELIC);
            entries.accept(MANGO_RELIC);
            entries.accept(AX_RELIC);
            entries.accept(RELIC_WORKBENCH_ITEM);
        });
    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(LIDMemory.MOD_ID, name), item);
    }
}
