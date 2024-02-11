package io.github.justfoxx.ventur;

import io.github.apace100.apoli.power.PowerTypeReference;
import io.github.apace100.origins.Origins;
import io.github.apace100.origins.integration.OriginDataLoadedCallback;
import io.github.apace100.origins.origin.*;
import io.github.justfoxx.ventur.mixins.OriginLayerAccessor;
import io.github.justfoxx.ventur.powers.PowerWrapper;
import io.github.justfoxx.ventur.powers.SizeChange;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import lombok.Getter;
import lombok.NonNull;
import lombok.val;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;

@NonNull
public class VenturOrigin {
    private final Origin origin;

    public VenturOrigin() {
        this.origin = new Origin(Utils.id("ventur"), Items.REDSTONE.getDefaultStack(), Impact.MEDIUM, 5, 0);
    }

    public void init() {
        registerPowers();
        OriginDataLoadedCallback.EVENT.register(this::originDataLoaded);
    }

    private void originDataLoaded(boolean isClient) {
        if (isClient) return;
        OriginRegistry.register(this.origin);
        val layer = (OriginLayerAccessor) OriginLayers.getLayer(Origins.identifier("origin"));
        val conditioned = new OriginLayer.ConditionedOrigin(null, List.of(this.origin.getIdentifier()));
        layer.getConditionedOrigins().add(conditioned);
    }

    private void registerPowers() {
        Arrays.stream(Powers.values()).map(Powers::getId).forEach(id -> this.origin.add(new PowerTypeReference<>(id)));
    }

    @Getter
    public enum Powers {
        WEAK_ARMS(Origins.identifier("weak_arms")),
        SIZE_CHANGE(new SizeChange());

        private final Optional<PowerWrapper> power;
        private final Identifier id;

        Powers(Identifier id) {
            this.power = Optional.empty();
            this.id = id;
        }

        Powers(PowerWrapper power) {
            this.power = Optional.of(power);
            this.id = power.getId();
        }
    }

    @Getter
    private static final List<PowerWrapper> customPowers = Arrays.stream(Powers.values())
            .filter(p -> p.power.isPresent())
            .map(p -> p.power.get())
            .toList();
}
