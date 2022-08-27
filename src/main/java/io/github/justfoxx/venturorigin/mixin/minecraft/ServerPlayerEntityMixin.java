package io.github.justfoxx.venturorigin.mixin.minecraft;

import com.mojang.authlib.GameProfile;
import io.github.justfoxx.venturorigin.Powers;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.passive.AllayEntity;
import net.minecraft.entity.passive.FoxEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.encryption.PlayerPublicKey;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin extends PlayerEntity {

    @Shadow public abstract ItemEntity dropItem(ItemStack stack, boolean throwRandomly, boolean retainOwnership);

    public ServerPlayerEntityMixin(World world, BlockPos pos, float yaw, GameProfile gameProfile, @Nullable PlayerPublicKey publicKey) {
        super(world, pos, yaw, gameProfile, publicKey);
    }

    @Inject(method = "playerTick", at = @At("HEAD"))
    private void onTick(CallbackInfo ci) {
        if(Powers.NO_BLOCK_OFFHAND.isActive(this)) {
            if(getOffHandStack() != ItemStack.EMPTY) {
                dropItem(getOffHandStack(), true);
                setStackInHand(Hand.OFF_HAND, ItemStack.EMPTY);
            }
        }
    }



//    @Inject(method = "wakeUp", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;wakeUp(ZZ)V"))
//    private void onWakeUp(boolean bl, boolean updateSleepingPlayers, CallbackInfo info) {
//        EntitySleepEvents.STOP_SLEEPING.invoker().onStopSleeping((ServerPlayerEntity)(Object) this, this.getBlockPos());
//    }
}
