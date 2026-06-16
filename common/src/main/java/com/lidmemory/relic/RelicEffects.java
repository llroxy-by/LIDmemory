package com.lidmemory.relic;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public final class RelicEffects {
    private static final int PASSIVE_DURATION_TICKS = 240;
    private static final int XP_PER_HUNGER = 50;

    // Players who have Garry relic equipped (checked by mixin for canEat)
    public static final Set<UUID> garryEquippedPlayers = new HashSet<>();
    // Track last saturation level for XP conversion
    private static final Map<UUID, Float> lastSaturation = new HashMap<>();

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
        } else if (type == RelicType.GARRY) {
            tickGarry(player);
        }
    }

    private static void tickGarry(Player player) {
        UUID id = player.getUUID();
        float currentSat = player.getFoodData().getSaturationLevel();
        Float prevSat = lastSaturation.get(id);

        if (prevSat != null && currentSat > prevSat && player.getFoodData().getFoodLevel() >= 19) {
            // Player ate while full/nearly full — convert saturation gain to XP
            float satGain = currentSat - prevSat;
            int xp = Math.round(satGain * 25.0f);
            if (xp > 0) {
                player.giveExperiencePoints(xp);
            }
        }
        lastSaturation.put(id, currentSat);
    }

    public static void onEquip(RelicType type, Player player) {
        if (player.level().isClientSide()) return;
        if (type == RelicType.AX) {
            player.getAbilities().mayfly = true;
            player.onUpdateAbilities();
        } else if (type == RelicType.GARRY) {
            garryEquippedPlayers.add(player.getUUID());
            lastSaturation.put(player.getUUID(), player.getFoodData().getSaturationLevel());
        }
    }

    public static void onUnequip(RelicType type, Player player) {
        if (player.level().isClientSide()) return;
        if (type == RelicType.AX) {
            player.getAbilities().mayfly = false;
            player.getAbilities().flying = false;
            player.onUpdateAbilities();
        } else if (type == RelicType.GARRY) {
            garryEquippedPlayers.remove(player.getUUID());
            lastSaturation.remove(player.getUUID());
        }
    }
}