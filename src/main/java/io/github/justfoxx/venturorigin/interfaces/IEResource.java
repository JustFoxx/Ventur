package io.github.justfoxx.venturorigin.interfaces;

import io.github.apace100.apoli.power.VariableIntPower;
import io.github.justfoxx.venturorigin.helpers.MathEnum;
import net.minecraft.entity.LivingEntity;

public interface IEResource {
    void modifyResource(VariableIntPower power, int value, MathEnum mathEnum, LivingEntity livingEntity);
}
