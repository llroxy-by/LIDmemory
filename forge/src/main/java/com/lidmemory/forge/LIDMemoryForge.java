package com.lidmemory.forge;

import com.lidmemory.LIDMemory;
import com.lidmemory.block.RelicWorkbenchBlock;
import com.lidmemory.menu.RelicWorkbenchMenu;
import com.lidmemory.menu.RelicWorkbenchMenuTypes;
import com.lidmemory.relic.RelicType;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod(LIDMemory.MOD_ID)
public final class LIDMemoryForge {
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, LIDMemory.MOD_ID);
    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, LIDMemory.MOD_ID);
    private static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(ForgeRegistries.MENU_TYPES, LIDMemory.MOD_ID);

    public static final RegistryObject<Item> LLROXY_RELIC = ITEMS.register(
        LIDMemory.LLROXY_RELIC_ID, () -> new ForgeRelicItem(RelicType.LLROXY, new Item.Properties().stacksTo(1).rarity(Rarity.RARE)));
    public static final RegistryObject<Item> IEGG_CUTE_RELIC = ITEMS.register(
        LIDMemory.IEGG_CUTE_RELIC_ID, () -> new ForgeRelicItem(RelicType.IEGG_CUTE, new Item.Properties().stacksTo(1).rarity(Rarity.RARE)));
    public static final RegistryObject<Item> MANGO_RELIC = ITEMS.register(
        LIDMemory.MANGO_RELIC_ID, () -> new ForgeRelicItem(RelicType.MANGO, new Item.Properties().stacksTo(1).rarity(Rarity.RARE)));
    public static final RegistryObject<Item> AX_RELIC = ITEMS.register(
        LIDMemory.AX_RELIC_ID, () -> new ForgeRelicItem(RelicType.AX, new Item.Properties().stacksTo(1).rarity(Rarity.RARE)));

    public static final RegistryObject<Block> RELIC_WORKBENCH_BLOCK = BLOCKS.register(
        LIDMemory.RELIC_WORKBENCH_ID, RelicWorkbenchBlock::new);
    public static final RegistryObject<Item> RELIC_WORKBENCH_ITEM = ITEMS.register(
        LIDMemory.RELIC_WORKBENCH_ID, () -> new BlockItem(RELIC_WORKBENCH_BLOCK.get(), new Item.Properties().stacksTo(64).rarity(Rarity.COMMON)));

    public static final RegistryObject<MenuType<RelicWorkbenchMenu>> RELIC_WORKBENCH_MENU = MENUS.register(
        "relic_workbench", () -> RelicWorkbenchMenuTypes.createMenuType());

    public LIDMemoryForge() {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        ITEMS.register(modBus);
        BLOCKS.register(modBus);
        MENUS.register(modBus);
        modBus.addListener(this::addCreativeItems);
    }

    private void addCreativeItems(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.accept(LLROXY_RELIC);
            event.accept(IEGG_CUTE_RELIC);
            event.accept(MANGO_RELIC);
            event.accept(AX_RELIC);
            event.accept(RELIC_WORKBENCH_ITEM);
        }
    }
}
