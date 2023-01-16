package io.github.justfoxx.venturorigin.mixin;

import io.github.justfoxx.venturorigin.Main;
import io.github.justfoxx.venturorigin.RegistryTypes;
import io.github.justfoxx.venturorigin.powers.PowerWrapper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(ActiveTargetGoal.class)
public abstract class ActiveTargetGoalMixin {
    @ModifyArg(method = "start", at = @At(value = "INVOKE", target = "net/minecraft/entity/mob/MobEntity.setTarget (Lnet/minecraft/entity/LivingEntity;)V"))
    public LivingEntity canTarget(LivingEntity target) {
        PowerWrapper power = Main.registry.get(RegistryTypes.POWER, Main.g.id("no_mob_attack"));

        if(power.isActive(target)) return null;

        return target;
    }
}
