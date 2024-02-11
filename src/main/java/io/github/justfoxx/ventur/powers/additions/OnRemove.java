package io.github.justfoxx.ventur.powers.additions;

import io.github.apace100.apoli.power.Power;
import lombok.NonNull;

@NonNull
public interface OnRemove {
    void onRemove(Power powerInstance);
}
