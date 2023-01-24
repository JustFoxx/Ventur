package io.github.justfoxx.venturorigin.mixin;

import io.github.justfoxx.venturorigin.Main;
import io.github.justfoxx.venturorigin.interfaces.IEPowerWrapper;
import io.github.justfoxx.venturorigin.registry.RegistryTypes;
import io.github.justfoxx.venturorigin.interfaces.IEDamaging;
import io.github.justfoxx.venturorigin.interfaces.IETicking;
import io.github.justfoxx.venturorigin.powers.PowerWrapperImpl;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {

    @Inject(method = "tick", at = @At("HEAD"))
    public void onTickOffHand(CallbackInfo ci) {
        IEPowerWrapper power = Main.registry.get(RegistryTypes.POWER, Main.g.id("no_block_offhand"));

        if (!power.isActive((LivingEntity) (Object)this)) return;
        if (!(power instanceof IETicking tickingPower)) return;

        tickingPower.tick((LivingEntity) (Object)this);
    }

    @Inject(method = "tick", at = @At("HEAD"))
    public void onTickSounds(CallbackInfo ci) {
        IEPowerWrapper power = Main.registry.get(RegistryTypes.POWER, Main.g.id("sounds"));

        if (!power.isActive((LivingEntity) (Object)this)) return;
        if (!(power instanceof IETicking tickingPower)) return;

        tickingPower.tick((LivingEntity) (Object)this);
    }

    @Inject(method = "tick", at = @At("HEAD"))
    public void onTickSizeChange(CallbackInfo ci) {
        IEPowerWrapper power = Main.registry.get(RegistryTypes.POWER, Main.g.id("size_change"));

        if (!power.isActive((LivingEntity) (Object)this)) return;
        if (!(power instanceof IETicking tickingPower)) return;

        tickingPower.tick((LivingEntity) (Object)this);
    }

    @Inject(method = "tick", at = @At("HEAD"))
    public void onTickRiding(CallbackInfo ci) {
        IEPowerWrapper power = Main.registry.get(RegistryTypes.POWER, Main.g.id("riding_effect"));

        if (!power.isActive((LivingEntity) (Object)this)) return;
        if (!(power instanceof IETicking tickingPower)) return;

        tickingPower.tick((LivingEntity) (Object)this);
    }

    @Inject(method = "tick", at = @At("HEAD"))
    public void onTickOvergrown(CallbackInfo ci) {
        IEPowerWrapper power = Main.registry.get(RegistryTypes.POWER, Main.g.id("overgrown"));

        if (!power.isActive((LivingEntity) (Object)this)) return;
        if (!(power instanceof IETicking tickingPower)) return;

        tickingPower.tick((LivingEntity) (Object)this);
    }

    @Inject(method = "damage", at = @At("RETURN"))
    private void onDamage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        boolean bl = cir.getReturnValue();

        if(!bl) return;

        IEPowerWrapper power = Main.registry.get(RegistryTypes.POWER, Main.g.id("riding_effect"));

        if(!power.isActive((LivingEntity) (Object) this)) return;
        if (!(power instanceof IEDamaging damagingPower)) return;

        damagingPower.onDamage((LivingEntity) (Object) this, source, amount);
    }
}
