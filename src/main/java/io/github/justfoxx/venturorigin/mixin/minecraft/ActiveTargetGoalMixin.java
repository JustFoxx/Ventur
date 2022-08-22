package io.github.justfoxx.venturorigin.mixin.minecraft;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import io.github.justfoxx.venturorigin.Powers;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.TrackTargetGoal;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.checkerframework.checker.units.qual.A;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ActiveTargetGoal.class)
public class ActiveTargetGoalMixin extends TrackTargetGoal {
    public ActiveTargetGoalMixin(MobEntity mob, boolean checkVisibility) {
        super(mob, checkVisibility);
    }

    @Inject(method = "start", at = @At("HEAD"))
    public void canTarget(CallbackInfo ci) {
        if(
                target instanceof PlayerEntity
                &&Powers.NO_MOB_ATTACK.isActive(target)
        ) return;
    }

    @Override
    public boolean canStart() {
        return false;
    }
}
