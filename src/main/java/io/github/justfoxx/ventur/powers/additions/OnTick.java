package io.github.justfoxx.ventur.powers.additions;

import lombok.NonNull;
import net.minecraft.entity.LivingEntity;

@NonNull
public interface OnTick {
    void tick(LivingEntity livingEntity);
}
