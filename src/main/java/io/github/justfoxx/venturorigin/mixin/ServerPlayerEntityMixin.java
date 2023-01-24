package io.github.justfoxx.venturorigin.mixin;

import com.mojang.authlib.GameProfile;
import io.github.justfoxx.venturorigin.Main;
import io.github.justfoxx.venturorigin.interfaces.IEPowerWrapper;
import io.github.justfoxx.venturorigin.registry.RegistryTypes;
import net.minecraft.entity.ItemEntity;
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
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin extends PlayerEntity {

    @Shadow public abstract ItemEntity dropItem(ItemStack stack, boolean throwRandomly, boolean retainOwnership);

    public ServerPlayerEntityMixin(World world, BlockPos pos, float yaw, GameProfile gameProfile, @Nullable PlayerPublicKey publicKey) {
        super(world, pos, yaw, gameProfile, publicKey);
    }

    @Inject(method = "playerTick", at = @At("HEAD"))
    private void onTick(CallbackInfo ci) {
        IEPowerWrapper power = Main.registry.get(RegistryTypes.POWER, Main.g.id("no_block_offhand"));

        if (!power.isActive(this)) return;
        if (getOffHandStack().isEmpty()) return;

        dropItem(getOffHandStack(), true);
        setStackInHand(Hand.OFF_HAND, ItemStack.EMPTY);

    }

}
