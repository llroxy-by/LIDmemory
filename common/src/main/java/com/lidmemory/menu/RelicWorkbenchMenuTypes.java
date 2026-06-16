package com.lidmemory.menu;

import com.lidmemory.LIDMemory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;

public final class RelicWorkbenchMenuTypes {
    public static final ResourceLocation RELIC_WORKBENCH_ID = new ResourceLocation(LIDMemory.MOD_ID, "relic_workbench");

    private static MenuType<RelicWorkbenchMenu> MENU_TYPE;

    public static MenuType<RelicWorkbenchMenu> get() {
        return MENU_TYPE;
    }

    public static MenuType<RelicWorkbenchMenu> createMenuType() {
        // Use a final reference for the lambda to capture our MenuType correctly
        final MenuType<RelicWorkbenchMenu>[] ref = new MenuType[1];
        MenuType<RelicWorkbenchMenu> t = new MenuType<>((id, inv) -> new RelicWorkbenchMenu(id, inv, ref[0]), FeatureFlags.DEFAULT_FLAGS);
        ref[0] = t;
        MENU_TYPE = t;
        return t;
    }

    private RelicWorkbenchMenuTypes() {}
}
