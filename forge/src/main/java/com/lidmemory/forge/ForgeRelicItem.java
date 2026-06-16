package com.lidmemory.forge;

import com.lidmemory.relic.RelicEffects;
import com.lidmemory.relic.RelicType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public final class ForgeRelicItem extends Item implements ICurioItem {
    private final RelicType type;

    public ForgeRelicItem(RelicType type, Properties properties) {
        super(properties);
        this.type = type;
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity() instanceof Player player) {
            RelicEffects.tick(type, player, stack);
        }
    }

    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        if (slotContext.entity() instanceof Player player) {
            RelicEffects.onEquip(type, player);
        }
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        if (slotContext.entity() instanceof Player player) {
            RelicEffects.onUnequip(type, player);
        }
    }
}
