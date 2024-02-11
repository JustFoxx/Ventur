package io.github.justfoxx.ventur.powers;

import io.github.apace100.apoli.power.Power;
import io.github.justfoxx.ventur.Utils;
import io.github.justfoxx.ventur.mixins.PowerAccessor;
import io.github.justfoxx.ventur.powers.additions.OnRemove;
import io.github.justfoxx.ventur.powers.additions.OnTick;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.NonNull;
import lombok.val;
import net.minecraft.entity.LivingEntity;
import virtuoel.pehkui.api.ScaleData;
import virtuoel.pehkui.api.ScaleTypes;

@NonNull
public class SizeChange extends PowerWrapper implements OnTick, OnRemove {
    private static final int MAX_TICKS = 20;

    public SizeChange() {
        super(Utils.id("size_change"));
    }

    @Override
    public void onRemove(Power powerInstance) {
        val livingEntity = ((PowerAccessor) powerInstance).getEntity();
        val baseData = ScaleTypes.BASE.getScaleData(livingEntity);
        val reachData = ScaleTypes.REACH.getScaleData(livingEntity);

        if (baseData.getScale() != 1) baseData.setTargetScale(1);
        if (reachData.getScale() != 1) reachData.setTargetScale(1);
    }

    private static final AtomicInteger tickEvery = new AtomicInteger(MAX_TICKS);

    @Override
    public void tick(LivingEntity livingEntity) {
        val it = tickEvery.getAndDecrement();
        if (it != 0) {
            return;
        }
        tickEvery.set(MAX_TICKS);

        final ScaleData baseData = ScaleTypes.BASE.getScaleData(livingEntity);
        final ScaleData reachData = ScaleTypes.REACH.getScaleData(livingEntity);

        if (!isActive(livingEntity)) {
            if (baseData.getScale() != 1) baseData.setTargetScale(1);
            if (reachData.getScale() != 1) reachData.setTargetScale(1);

            return;
        }

        if (baseData.getScale() != getBaseScale()) baseData.setTargetScale((float) getBaseScale());
        if (reachData.getScale() != getReachScale()) reachData.setTargetScale((float) getReachScale());
    }

    private double getBaseScale() {
        return Utils.config.get("base_scale");
    }

    private double getReachScale() {
        return Utils.config.get("reach_scale");
    }
}
