package io.github.justfoxx.venturorigin.powers;

import io.github.apace100.apoli.power.Power;
import io.github.apace100.apoli.power.PowerType;
import net.minecraft.entity.LivingEntity;

public class BasePower extends Power {
    public BasePower(PowerType<?> type, LivingEntity entity) {
        super(type, entity);
        setTicking(false);
    }
}
