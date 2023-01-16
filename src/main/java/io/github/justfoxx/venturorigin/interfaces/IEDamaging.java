package io.github.justfoxx.venturorigin.interfaces;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;

public interface IEDamaging {
    void onDamage(LivingEntity livingEntity, DamageSource source, float amount);
}
