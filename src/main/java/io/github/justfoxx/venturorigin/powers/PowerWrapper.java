package io.github.justfoxx.venturorigin.powers;

import io.github.apace100.apoli.component.PowerHolderComponent;
import io.github.apace100.apoli.power.*;
import io.github.justfoxx.venturorigin.helpers.MathEnum;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;

public class PowerWrapper {
    private final Identifier id;
    protected final PowerTypeReference<Power> powerTypeReference;
    protected PowerType<?> powerType;
    protected PowerHolderComponent powerHolderComponent;

    public PowerWrapper(Identifier identifier) {
        this.id = identifier;
        this.powerTypeReference = new PowerTypeReference<>(this.id);
    }

    public boolean isActive(LivingEntity livingEntity) {
        return powerTypeReference.isActive(livingEntity);
    }

    public PowerHolderComponent getPowerHolder(LivingEntity entity) {
        if (powerHolderComponent == null) {
            powerHolderComponent = PowerHolderComponent.KEY.get(entity);
        }
        return powerHolderComponent;
    }

    public Power getPower(LivingEntity entity) {
        if (this.powerType == null) {
            this.powerType = PowerTypeRegistry.get(id);
        }
        return getPowerHolder(entity).getPower(this.powerType);
    }

    public void modifyResource(VariableIntPower power, int value, MathEnum mathEnum, LivingEntity livingEntity) {
        switch (mathEnum) {
            case ADD ->power.setValue(power.getValue() + value);
            case REMOVE ->power.setValue(power.getValue() - value);
            case SET ->power.setValue(value);
        }
        getPowerHolder(livingEntity).sync();
    }
}
