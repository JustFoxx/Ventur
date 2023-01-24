package io.github.justfoxx.venturorigin.powers;

import io.github.apace100.apoli.power.Power;
import io.github.justfoxx.venturorigin.interfaces.IEGetEntity;
import io.github.justfoxx.venturorigin.interfaces.IERemoved;
import io.github.justfoxx.venturorigin.interfaces.IETicking;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;
import virtuoel.pehkui.api.ScaleData;
import virtuoel.pehkui.api.ScaleTypes;

public class SizeChange extends PowerWrapperImpl implements IETicking, IERemoved {
    public final float baseScale = 0.6F;
    public final float reachScale = 2.0F;

    public SizeChange(Identifier identifier) {
        super(identifier);
    }

    @Override
    public void tick(LivingEntity livingEntity) {
        final ScaleData baseData = ScaleTypes.BASE.getScaleData(livingEntity);
        final ScaleData reachData = ScaleTypes.REACH.getScaleData(livingEntity);

        if(!isActive(livingEntity)) {
            if(baseData.getScale() != 1) baseData.setTargetScale(1);
            if(reachData.getScale() != 1) reachData.setTargetScale(1);

            return;
        }

        if(baseData.getScale() != baseScale) baseData.setTargetScale(baseScale);
        if(reachData.getScale() != reachScale) reachData.setTargetScale(reachScale);
    }

    @Override
    public void onRemoved(Power powerInstance) {
        LivingEntity livingEntity = ((IEGetEntity) powerInstance).getEntity();

        final ScaleData baseData = ScaleTypes.BASE.getScaleData(livingEntity);
        final ScaleData reachData = ScaleTypes.REACH.getScaleData(livingEntity);

        if(baseData.getScale() != 1) baseData.setTargetScale(1);
        if(reachData.getScale() != 1) reachData.setTargetScale(1);
    }
}
