package io.github.justfoxx.venturorigin;

import io.github.justfoxx.venturorigin.powers.*;

public class Powers {
    public void init() {
        Main.registry.add(RegistryTypes.POWER, Main.g.id("size_change"), new SizeChange(Main.g.id("smallbody_size")));
        Main.registry.add(RegistryTypes.POWER, Main.g.id("sounds"), new Sounds(Main.g.id("spirit_sound")));
        Main.registry.add(RegistryTypes.POWER, Main.g.id("riding_effect"), new RidingEffect(Main.g.id("spirit_lucky")));
        Main.registry.add(RegistryTypes.POWER, Main.g.id("overgrown"), new OverGrown(Main.g.id("overgrown")));
        Main.registry.add(RegistryTypes.POWER, Main.g.id("no_mob_attack"), new PowerWrapper(Main.g.id("spirit_mobattack")));
        Main.registry.add(RegistryTypes.POWER, Main.g.id("no_block_offhand"), new PowerWrapper(Main.g.id("paws_no_block_offhand")));
    }
}
