package com.lidmemory.relic;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public final class RelicEffects {
    private static final int PASSIVE_DURATION_TICKS = 240;

    private RelicEffects() {
    }

    public static void tick(RelicType type, Player player, ItemStack stack) {
        if (player.level().isClientSide()) {
            return;
        }

        if (type == RelicType.LLROXY) {
            player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, PASSIVE_DURATION_TICKS, 0, true, false, true));
        } else if (type == RelicType.IEGG_CUTE) {
            player.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, PASSIVE_DURATION_TICKS, 0, true, false, true));
        } else if (type == RelicType.MANGO) {
            player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, PASSIVE_DURATION_TICKS, 0, true, false, true));
        } else if (type == RelicType.AX) {
            // flight is handled in onEquip/onUnequip
        }
    }

    public static void onEquip(RelicType type, Player player) {
        if (player.level().isClientSide()) return;
        if (type == RelicType.AX) {
            player.getAbilities().mayfly = true;
            player.onUpdateAbilities();
        }
    }

    public static void onUnequip(RelicType type, Player player) {
        if (player.level().isClientSide()) return;
        if (type == RelicType.AX) {
            player.getAbilities().mayfly = false;
            player.getAbilities().flying = false;
            player.onUpdateAbilities();
        }
    }
}
