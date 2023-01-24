package io.github.justfoxx.venturorigin.powers;

import io.github.justfoxx.venturorigin.Main;
import io.github.justfoxx.venturorigin.interfaces.IETicking;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.BoneMealItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

import java.util.Random;

public class OverGrown extends PowerWrapperImpl implements IETicking {
    private final int radius = 3;
    private int ambientChance;
    private final int minAmbientDelay = 5;

    private final TagKey<Block> crops = TagKey.of(Registry.BLOCK_KEY, Identifier.of("minecraft", "crops"));

    public OverGrown(Identifier identifier) {
        super(identifier);
    }

    private boolean bonemeal(World world, BlockPos blockPos) {
        return BoneMealItem.useOnFertilizable(ItemStack.EMPTY, world, blockPos);
    }

    private void resetSoundDelay() {
        this.ambientChance = -this.minAmbientDelay;
    }

    @Override
    public void tick(LivingEntity livingEntity) {
        int random = new Random().nextInt(5000);

        if (!(livingEntity.isAlive() && random < this.ambientChance++)) return;

        resetSoundDelay();
        growCrops(livingEntity);
    }

    private void growCrops(LivingEntity livingEntity) {
        BlockPos blockPosOfEntity = livingEntity.getBlockPos();
        for (BlockPos blockPos : BlockPos.iterateRandomly(livingEntity.world.getRandom(), 30, blockPosOfEntity, radius)) {
            BlockState blockState = livingEntity.world.getBlockState(blockPos);

            if (blockPos.getY() != blockPosOfEntity.getY())continue;
            if (blockState.isAir()) continue;
            if (!blockState.isIn(crops)) continue;
            if (!bonemeal(livingEntity.world, blockPos)) continue;

            return;
        }
    }
}
