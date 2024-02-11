package io.github.justfoxx.ventur.powers.additions;

import io.github.apace100.apoli.power.VariableIntPower;
import io.github.justfoxx.ventur.MathOperators;
import lombok.NonNull;
import net.minecraft.entity.LivingEntity;

@NonNull
public interface Resource {
    void modifyResource(VariableIntPower power, int value, MathOperators operators, LivingEntity livingEntity);
}
