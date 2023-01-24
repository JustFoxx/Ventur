package io.github.justfoxx.venturorigin.powers;

import io.github.justfoxx.venturorigin.interfaces.IESounding;
import io.github.justfoxx.venturorigin.interfaces.IETicking;
import net.minecraft.entity.LivingEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;

import java.util.Random;

public class Sounds extends PowerWrapperImpl implements IESounding, IETicking {
    private int ambientSoundChance;
    private final int minAmbientSoundDelay = 60;

    public Sounds(Identifier identifier) {
        super(identifier);
    }

    @Override
    public SoundEvent deathSound() {
        return SoundEvents.ENTITY_ALLAY_DEATH;
    }

    @Override
    public LivingEntity.FallSounds fallSound() {
        return new LivingEntity.FallSounds(SoundEvents.ENTITY_GENERIC_SMALL_FALL, SoundEvents.ENTITY_GENERIC_SMALL_FALL);
    }

    @Override
    public SoundEvent hurtSound() {
        return SoundEvents.ENTITY_FOX_HURT;
    }

    @Override
    public SoundEvent eatSound() {
        return SoundEvents.ENTITY_FOX_EAT;
    }

    @Override
    public SoundEvent dropSound() {
        return SoundEvents.ENTITY_FOX_SPIT;
    }

    @Override
    public SoundEvent sleepSound() {
        return SoundEvents.ENTITY_FOX_SLEEP;
    }
    private void resetSoundDelay() {
        this.ambientSoundChance = -minAmbientSoundDelay;
    }


    @Override
    public void tick(LivingEntity livingEntity) {
        if (!livingEntity.isAlive()) return;

        int random = new Random().nextInt(1000);

        if (random > this.ambientSoundChance++) return;
        if (!livingEntity.isSleeping()) return;

        livingEntity.world.playSound(null,livingEntity.getX(),livingEntity.getY(),livingEntity.getZ(),sleepSound(), SoundCategory.PLAYERS,1.0F,1.0F);
        resetSoundDelay();
    }
}

