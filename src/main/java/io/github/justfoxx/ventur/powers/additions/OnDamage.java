package io.github.justfoxx.ventur.powers.additions;

import lombok.NonNull;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;

@NonNull
public interface OnDamage {
    void onDamage(LivingEntity livingEntity, DamageSource source, float amount);
}
