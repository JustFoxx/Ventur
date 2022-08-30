package io.github.justfoxx.venturorigin.mixin.minecraft;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import io.github.apace100.apoli.component.PowerHolderComponent;
import io.github.justfoxx.venturorigin.powers.Sounds;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {

    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "getDeathSound", at = @At("TAIL"), cancellable = true)
    public void deathSound(CallbackInfoReturnable<SoundEvent> cir) {
        if(PowerHolderComponent.hasPower(this, Sounds.class)) {
            for(Sounds sounds : PowerHolderComponent.getPowers(this,Sounds.class)) {
                cir.setReturnValue(sounds.deathSound());
                return;
            }
        }
    }

    @Inject(method = "getFallSounds", at = @At("TAIL"), cancellable = true)
    public void fallSound(CallbackInfoReturnable<FallSounds> cir) {
        if(PowerHolderComponent.hasPower(this, Sounds.class)) {
            for(Sounds sounds : PowerHolderComponent.getPowers(this,Sounds.class)) {
                cir.setReturnValue(sounds.fallSound());
                return;
            }
        }
    }

    @Override
    public SoundEvent getEatSound(ItemStack stack) {
        if(PowerHolderComponent.hasPower(this, Sounds.class)) {
            for(Sounds sounds : PowerHolderComponent.getPowers(this,Sounds.class)) {
                return sounds.eatSound();
            }
        }
        return stack.getEatSound();
    }

    @Inject(method = "getHurtSound", at = @At("HEAD"), cancellable = true)
    public void hurtSound(DamageSource source, CallbackInfoReturnable<SoundEvent> cir) {
        if(PowerHolderComponent.hasPower(this, Sounds.class)) {
            for(Sounds sounds : PowerHolderComponent.getPowers(this,Sounds.class)) {
                cir.setReturnValue(sounds.hurtSound());
                return;
            }
        }
    }

    @ModifyArg(method = "eatFood", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/World;playSound(Lnet/minecraft/entity/player/PlayerEntity;DDDLnet/minecraft/sound/SoundEvent;Lnet/minecraft/sound/SoundCategory;FF)V"
    ))
    public SoundEvent eatSound(SoundEvent sound) {
        if(PowerHolderComponent.hasPower(this, Sounds.class)) {
            for(Sounds sounds : PowerHolderComponent.getPowers(this,Sounds.class)) {
                return sounds.eatSound();
            }
        }
        return sound;
    }

    @Inject(method = "dropItem(Lnet/minecraft/item/ItemStack;ZZ)Lnet/minecraft/entity/ItemEntity;", at = @At("TAIL"))
    public void dropSound(ItemStack stack, boolean throwRandomly, boolean retainOwnership, CallbackInfoReturnable<ItemEntity> cir) {
        if(!stack.isEmpty()) {
            if(PowerHolderComponent.hasPower(this, Sounds.class)) {
                for(Sounds sounds : PowerHolderComponent.getPowers(this,Sounds.class)) {
                    world.playSound(null,this.getX(),this.getY(),this.getZ(),sounds.dropSound(),SoundCategory.PLAYERS,1.0F,1.0F);
                }
            }
        }
    }
}