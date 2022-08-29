package io.github.justfoxx.venturorigin.powers;

import io.github.apace100.apoli.Apoli;
import io.github.apace100.apoli.component.PowerHolderComponent;
import io.github.apace100.apoli.power.Power;
import io.github.apace100.apoli.power.PowerType;
import io.github.justfoxx.venturorigin.Main;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.FoxEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.command.PlaySoundCommand;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

import java.util.Random;

public class Sounds extends BasePower {
    public Sounds(PowerType<?> type, LivingEntity entity) {
        super(type, entity);
        setTicking(false);
    }

    public SoundEvent deathSound() {
        return SoundEvents.ENTITY_ALLAY_DEATH;
    }

    public LivingEntity.FallSounds fallSound() {
        return new LivingEntity.FallSounds(SoundEvents.ENTITY_GENERIC_SMALL_FALL, SoundEvents.ENTITY_GENERIC_SMALL_FALL);
    }

    public SoundEvent ambientSound() {
        return SoundEvents.ENTITY_ALLAY_AMBIENT_WITH_ITEM;
    }

    public SoundEvent hurtSound() {
        return SoundEvents.ENTITY_ALLAY_HURT;
    }

    public SoundEvent eatSound() {
        return SoundEvents.ENTITY_FOX_EAT;
    }

    public SoundEvent dropSound() {
        return SoundEvents.ENTITY_FOX_SPIT;
    }

    public SoundEvent sleepSound() {
        return SoundEvents.ENTITY_FOX_SLEEP;
    }

    private int ambientSoundChance;
    private void resetSoundDelay() {
        this.ambientSoundChance = -this.getMinAmbientSoundDelay();
    }
    private int getMinAmbientSoundDelay() {
        return 60;
    }

    @Override
    public void tick() {
        if(isActive() && entity != null && entity instanceof PlayerEntity) {
            int random = new Random().nextInt(1000);
            if (entity.isAlive() && random < this.ambientSoundChance++) {
                this.resetSoundDelay();
                SoundEvent sound;
                if(entity.isSleeping()) {
                    sound = sleepSound();
                } else {
                    sound = ambientSound();
                }
                entity.world.playSound(null,entity.getX(),entity.getY(),entity.getZ(),sound, SoundCategory.PLAYERS,1.0F,1.0F);
            }
        }
    }
}

