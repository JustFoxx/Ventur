package io.github.justfoxx.venturorigin.mixin.minecraft;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import io.github.justfoxx.venturorigin.Powers;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.TrackTargetGoal;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ActiveTargetGoal.class)
public class ActiveTargetGoalMixin extends TrackTargetGoal {
    public ActiveTargetGoalMixin(MobEntity mob, boolean checkVisibility) {
        super(mob, checkVisibility);
    }

    @Redirect(method = "start", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/entity/mob/MobEntity;setTarget(Lnet/minecraft/entity/LivingEntity;)V"
    ))
    public void canTarget(MobEntity instance, LivingEntity target) {
        if(
                !(target instanceof PlayerEntity
                &&Powers.NO_MOB_ATTACK.isActive(target))
        ) {
            instance.setTarget(target);
            super.start();
        }
    }

    @Override
    public boolean canStart() {
        return false;
    }
}
