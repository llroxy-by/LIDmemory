package com.lidmemory.fabric;

import com.lidmemory.relic.RelicEffects;
import com.lidmemory.relic.RelicType;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketItem;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public final class FabricRelicItem extends TrinketItem {
    private final RelicType type;

    public FabricRelicItem(RelicType type, Properties properties) {
        super(properties);
        this.type = type;
    }

    @Override
    public void tick(ItemStack stack, SlotReference slot, LivingEntity entity) {
        if (entity instanceof Player player) {
            RelicEffects.tick(type, player, stack);
        }
    }

    @Override
    public void onEquip(ItemStack stack, SlotReference slot, LivingEntity entity) {
        if (entity instanceof Player player) {
            RelicEffects.onEquip(type, player);
        }
    }

    @Override
    public void onUnequip(ItemStack stack, SlotReference slot, LivingEntity entity) {
        if (entity instanceof Player player) {
            RelicEffects.onUnequip(type, player);
        }
    }
}
