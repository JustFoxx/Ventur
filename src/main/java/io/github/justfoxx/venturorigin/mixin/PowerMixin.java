package io.github.justfoxx.venturorigin.mixin;

import io.github.apace100.apoli.power.Power;
import io.github.justfoxx.venturorigin.interfaces.IEGetEntity;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Power.class)
public class PowerMixin implements IEGetEntity {
    @Shadow protected LivingEntity entity;

    @Override
    public LivingEntity getEntity() {
        return this.entity;
    }
}
