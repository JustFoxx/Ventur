package io.github.justfoxx.venturorigin.powers;

import io.github.apace100.apoli.action.block.BonemealAction;
import io.github.apace100.apoli.power.PowerType;
import io.github.justfoxx.venturorigin.Main;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.BoneMealItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class OverGrown extends BasePower {
    private final int radius = 3;

    public OverGrown(PowerType<?> type, LivingEntity entity) {
        super(type, entity);
    }

    private boolean bonemeal(World world, BlockPos blockPos) {
        return BoneMealItem.useOnFertilizable(ItemStack.EMPTY, world, blockPos);
    }

    private int ambientChance;

    private void resetSoundDelay() {
        this.ambientChance = -this.getMinAmbientDelay();
    }
    private int getMinAmbientDelay() {
        return 5;
    }

    @Override
    public void tick() {
        if(isActive()) {
            int random = new Random().nextInt(5000);
            if (entity.isAlive() && random < this.ambientChance++) {
                resetSoundDelay();
                function();
            }

        }
    }

    private void function() {
        BlockPos blockPosOfEntity = entity.getBlockPos();
        for (BlockPos blockPos : BlockPos.iterateRandomly(entity.world.getRandom(), 10, blockPosOfEntity, radius)) {
            BlockState blockState = entity.world.getBlockState(blockPos);
            if(blockPos.getY() != blockPosOfEntity.getY()) {
                continue;
            }
            if (blockState.isAir()) {
                continue;
            }
            if (bonemeal(entity.world, blockPos)) {
                break;
            }
        }
    }
}
