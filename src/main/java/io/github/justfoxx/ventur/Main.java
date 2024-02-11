package io.github.justfoxx.ventur;

import lombok.val;
import net.fabricmc.api.ModInitializer;

public final class Main implements ModInitializer {

    @Override
    public void onInitialize() {
        Utils.logger.info("Bzzzz");
        val venturOrigin = new VenturOrigin();
        venturOrigin.init();
    }
}
