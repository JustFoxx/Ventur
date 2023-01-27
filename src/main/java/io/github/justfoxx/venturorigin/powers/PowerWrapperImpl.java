package io.github.justfoxx.venturorigin.powers;

import io.github.apace100.apoli.component.PowerHolderComponent;
import io.github.apace100.apoli.power.*;
import io.github.justfoxx.venturorigin.helpers.MathEnum;
import io.github.justfoxx.venturorigin.interfaces.IEPowerWrapper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;

public class PowerWrapperImpl implements IEPowerWrapper {
    private final Identifier id;
    protected final PowerTypeReference<Power> powerTypeReference;
    protected PowerType<?> powerType;
    protected PowerHolderComponent powerHolderComponent;

    public PowerWrapperImpl(Identifier identifier) {
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

//    public void modifyResource(VariableIntPower power, int value, MathEnum mathEnum, LivingEntity livingEntity) {

//    }

    public Identifier getId() {
        return id;
    }
}
