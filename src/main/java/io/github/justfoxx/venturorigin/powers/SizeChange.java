package io.github.justfoxx.venturorigin.powers;

import io.github.apace100.apoli.power.Power;
import io.github.apace100.apoli.power.PowerType;
import net.minecraft.entity.LivingEntity;
import virtuoel.pehkui.Pehkui;
import virtuoel.pehkui.api.ScaleData;
import virtuoel.pehkui.api.ScaleTypes;
import virtuoel.pehkui.command.argument.ScaleOperationArgumentType;

public class SizeChange extends BasePower {
    public SizeChange(PowerType<?> type, LivingEntity entity) {
        super(type, entity);
    }
    public final float baseScale = 0.3F;
    public final float reachScale = 2.0F;


    @Override
    public void tick() {
        final ScaleData baseData = ScaleTypes.BASE.getScaleData(entity);
        final ScaleData reachData = ScaleTypes.REACH.getScaleData(entity);
        if(isActive()) {
            if(baseData.getScale() != baseScale) {
                baseData.setTargetScale(baseScale);
            } else if(reachData.getScale() != reachScale) {
                reachData.setTargetScale(reachScale);
            }
        } else if(!isActive()){
            if(baseData.getScale() != 1) {
                baseData.setTargetScale(1);
            } else if(reachData.getScale() != 1) {
                reachData.setTargetScale(1);
            }
        }
    }

    @Override
    public void onLost() {
        final ScaleData baseData = ScaleTypes.BASE.getScaleData(entity);
        final ScaleData reachData = ScaleTypes.REACH.getScaleData(entity);
        baseData.setTargetScale(1);
        reachData.setTargetScale(1);
    }
}
