package io.github.justfoxx.venturorigin.powers;

import io.github.apace100.apoli.power.PowerType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.command.EffectCommand;

public class RidingEffect extends BasePower{

    public RidingEffect(PowerType<?> type, LivingEntity entity) {
        super(type, entity);
    }

    @Override
    public void tick() {
        if(isActive()) {
            LivingEntity vehicle = (LivingEntity) entity.getVehicle();
            if(vehicle instanceof PlayerEntity) {
                StatusEffectInstance effect = new StatusEffectInstance(StatusEffects.LUCK, 20, 1, true,true, false);
                vehicle.addStatusEffect(effect);
            }
        }
    }
}
