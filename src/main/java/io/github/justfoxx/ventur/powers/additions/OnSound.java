package io.github.justfoxx.ventur.powers.additions;

import lombok.NonNull;
import net.minecraft.entity.LivingEntity;
import net.minecraft.sound.SoundEvent;

@NonNull
public interface OnSound {
    SoundEvent deathSound();

    LivingEntity.FallSounds fallSound();

    SoundEvent hurtSound();

    SoundEvent eatSound();

    SoundEvent dropSound();

    SoundEvent sleepSound();
}
