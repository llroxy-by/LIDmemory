package com.lidmemory.mixin;

import com.lidmemory.relic.RelicEffects;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Player.class)
public abstract class PlayerMixin {

    @Inject(method = "canEat", at = @At("RETURN"), cancellable = true)
    private void lidmemory_canEatWhenFull(boolean ignoreHunger, CallbackInfoReturnable<Boolean> cir) {
        if (!ignoreHunger && !cir.getReturnValue()) {
            Player self = (Player) (Object) this;
            if (RelicEffects.garryEquippedPlayers.contains(self.getUUID())) {
                cir.setReturnValue(true);
            }
        }
    }
}