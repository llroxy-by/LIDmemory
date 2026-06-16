package com.lidmemory.forge;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import com.lidmemory.LIDMemory;

@Mod.EventBusSubscriber(modid = LIDMemory.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public final class LIDMemoryForgeClient {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> MenuScreens.register(LIDMemoryForge.RELIC_WORKBENCH_MENU.get(), ForgeRelicWorkbenchScreen::new));
    }
    private LIDMemoryForgeClient() {}
}
