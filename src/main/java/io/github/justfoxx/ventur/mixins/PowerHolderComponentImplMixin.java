package io.github.justfoxx.ventur.mixins;

import com.llamalad7.mixinextras.injector.ModifyReceiver;
import io.github.apace100.apoli.component.PowerHolderComponentImpl;
import io.github.apace100.apoli.power.Power;
import io.github.justfoxx.ventur.VenturOrigin;
import io.github.justfoxx.ventur.powers.PowerWrapper;
import io.github.justfoxx.ventur.powers.additions.OnRemove;
import java.util.List;
import lombok.val;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(PowerHolderComponentImpl.class)
public class PowerHolderComponentImplMixin {
    @Unique
    private static final List<PowerWrapper> onRemovePower = VenturOrigin.getCustomPowers().stream()
            .filter(p -> p instanceof OnRemove)
            .toList();

    @ModifyReceiver(
            method = "removePower",
            at = @At(value = "INVOKE", target = "Lio/github/apace100/apoli/power/Power;onRemoved()V"))
    public Power onRemovePowerA(Power powerInstance) {
        val powerId = powerInstance.getType().getIdentifier();
        for (PowerWrapper power : onRemovePower) {
            if (!power.getId().equals(powerId)) continue;

            val removePower = (OnRemove) power;

            removePower.onRemove(powerInstance);
        }
        return powerInstance;
    }

    @ModifyReceiver(
            method = "fromTag",
            at = @At(value = "INVOKE", target = "Lio/github/apace100/apoli/power/Power;onRemoved()V"))
    public Power onRemovePowerB(Power powerInstance) {
        val powerId = powerInstance.getType().getIdentifier();
        for (PowerWrapper power : onRemovePower) {
            if (!power.getId().equals(powerId)) continue;

            val removePower = (OnRemove) power;

            removePower.onRemove(powerInstance);
        }
        return powerInstance;
    }
}
