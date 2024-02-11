package io.github.justfoxx.ventur.mixins;

import io.github.justfoxx.ventur.VenturOrigin;
import io.github.justfoxx.ventur.powers.PowerWrapper;
import io.github.justfoxx.ventur.powers.additions.OnTick;
import java.util.List;
import lombok.val;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    @Unique
    private static final List<PowerWrapper> onTick = VenturOrigin.getCustomPowers().stream()
            .filter(p -> p instanceof OnTick)
            .toList();

    @Inject(method = "tick", at = @At("HEAD"))
    public void onTick(CallbackInfo ci) {
        for (PowerWrapper power : onTick) {
            val entity = (LivingEntity) (Object) this;

            if (!power.isActive(entity)) continue;

            val tickPower = (OnTick) power;

            tickPower.tick((LivingEntity) (Object) this);
        }
    }
}
