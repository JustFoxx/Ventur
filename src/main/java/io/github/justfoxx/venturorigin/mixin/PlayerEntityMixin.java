package io.github.justfoxx.venturorigin.mixin;

import io.github.justfoxx.venturorigin.Main;
import io.github.justfoxx.venturorigin.interfaces.IEPowerWrapper;
import io.github.justfoxx.venturorigin.registry.RegistryTypes;
import io.github.justfoxx.venturorigin.interfaces.IESounding;
import io.github.justfoxx.venturorigin.powers.PowerWrapperImpl;
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
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {

    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "getDeathSound", at = @At("TAIL"), cancellable = true)
    public void deathSound(CallbackInfoReturnable<SoundEvent> cir) {
        IEPowerWrapper power = Main.registry.get(RegistryTypes.POWER, Main.g.id("sounds"));

        if (!power.isActive(this)) return;
        if (!(power instanceof IESounding soundingPower)) return;

        cir.setReturnValue(soundingPower.deathSound());
    }

    @Inject(method = "getFallSounds", at = @At("TAIL"), cancellable = true)
    public void fallSound(CallbackInfoReturnable<FallSounds> cir) {
        IEPowerWrapper power = Main.registry.get(RegistryTypes.POWER, Main.g.id("sounds"));

        if (!power.isActive(this)) return;
        if (!(power instanceof IESounding soundingPower)) return;

        cir.setReturnValue(soundingPower.fallSound());
    }

    @Override
    public SoundEvent getEatSound(ItemStack stack) {
        IEPowerWrapper power = Main.registry.get(RegistryTypes.POWER, Main.g.id("sounds"));

        if (!power.isActive(this)) return stack.getEatSound();
        if (!(power instanceof IESounding soundingPower)) return stack.getEatSound();

        return soundingPower.eatSound();
    }

    @Inject(method = "getHurtSound", at = @At("HEAD"), cancellable = true)
    public void hurtSound(DamageSource source, CallbackInfoReturnable<SoundEvent> cir) {
        IEPowerWrapper power = Main.registry.get(RegistryTypes.POWER, Main.g.id("sounds"));

        if (!power.isActive(this)) return;
        if (!(power instanceof IESounding soundingPower)) return;

        cir.setReturnValue(soundingPower.hurtSound());
    }

    @ModifyArg(method = "eatFood", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/World;playSound(Lnet/minecraft/entity/player/PlayerEntity;DDDLnet/minecraft/sound/SoundEvent;Lnet/minecraft/sound/SoundCategory;FF)V"
    ))
    public SoundEvent eatSound(SoundEvent sound) {
        IEPowerWrapper power = Main.registry.get(RegistryTypes.POWER, Main.g.id("sounds"));

        if (!power.isActive(this)) return sound;
        if (!(power instanceof IESounding soundingPower)) return sound;

        return soundingPower.eatSound();
    }

    @Inject(method = "dropItem(Lnet/minecraft/item/ItemStack;ZZ)Lnet/minecraft/entity/ItemEntity;", at = @At("TAIL"))
    public void dropSound(ItemStack stack, boolean throwRandomly, boolean retainOwnership, CallbackInfoReturnable<ItemEntity> cir) {
        if (stack.isEmpty()) return;

        IEPowerWrapper power = Main.registry.get(RegistryTypes.POWER, Main.g.id("sounds"));
        
        if (!power.isActive(this)) return;
        if (!(power instanceof IESounding soundingPower)) return;
        
        world.playSound(null,this.getX(),this.getY(),this.getZ(),soundingPower.dropSound(),SoundCategory.PLAYERS,1.0F,1.0F);
    }
}