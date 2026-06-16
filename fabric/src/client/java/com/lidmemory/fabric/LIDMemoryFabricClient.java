package com.lidmemory.fabric;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;

public class LIDMemoryFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ScreenRegistry.register(LIDMemoryFabric.RELIC_WORKBENCH_MENU, RelicWorkbenchScreen::new);
    }
}
