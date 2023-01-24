package io.github.justfoxx.venturorigin.powers;

import io.github.justfoxx.venturorigin.interfaces.IEDamaging;
import io.github.justfoxx.venturorigin.interfaces.IETicking;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;

public class RidingEffect extends PowerWrapperImpl implements IETicking, IEDamaging {

    public RidingEffect(Identifier identifier) {
        super(identifier);
    }

    @Override
    public void tick(LivingEntity livingEntity) {
        Entity vehicle = livingEntity.getVehicle();

        if(!(vehicle instanceof PlayerEntity)) return;

        StatusEffectInstance effect = new StatusEffectInstance(StatusEffects.LUCK, 20, 1, true,true, false);
        ((LivingEntity)vehicle).addStatusEffect(effect);
    }

    @Override
    public void onDamage(LivingEntity livingEntity, DamageSource source, float amount) {
        if(!(livingEntity.getVehicle() instanceof LivingEntity))return;

        livingEntity.dismountVehicle();
    }
}
