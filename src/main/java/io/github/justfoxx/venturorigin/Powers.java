package io.github.justfoxx.venturorigin;

import io.github.apace100.apoli.power.Power;
import io.github.apace100.apoli.power.PowerTypeReference;
import net.minecraft.util.Identifier;

import static io.github.justfoxx.venturorigin.Main.ID;

public class Powers {
    public static class PowerT extends PowerTypeReference<Power> {
        public PowerT(Identifier id) {
            super(id);
        }
    }
    public static final PowerT NO_BLOCK_OFFHAND = new PowerT(ID("paws_no_block_offhand"));
    public static final PowerT NO_MOB_ATTACK = new PowerT(ID("spirit_mobattack"));

    public static final PowerT SLEEP_ON_TREES = new PowerT(ID("green_sleeping"));
}
