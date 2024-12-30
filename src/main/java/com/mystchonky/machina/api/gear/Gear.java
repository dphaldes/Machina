package com.mystchonky.machina.api.gear;

import com.mojang.serialization.Codec;
import com.mystchonky.machina.Machina;
import com.mystchonky.machina.api.RegistryKeys;
import com.mystchonky.machina.api.gear.trait.Trait;
import com.mystchonky.machina.client.screen.tooltip.Tooltip;
import com.mystchonky.machina.common.item.GearItem;
import com.mystchonky.machina.common.registrar.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.player.Player;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public abstract class Gear implements Tooltip.Provider {


    private final String id;
    private final List<Trait> traits = new ArrayList<>();
    @Nullable
    private GearItem gearItem;

    public Gear(String id) {
        this.id = id;
    }

    public String id() {
        return id;
    }

    public GearItem getGearItem() {
        if (gearItem == null) {
            this.gearItem = new GearItem(this);
        }
        return gearItem;
    }

    public abstract String displayName();

    protected boolean checkCompatibility(Gear other) {
        return this != other;
    }

    public final boolean isCompatibleWith(Gear other) {
        return this.checkCompatibility(other) && other.checkCompatibility(this);
    }

    public final <T extends Trait> void addTrait(T type) {
        traits.add(type);
    }

    public final void onEquip(Player player) {
        if (player.level().isClientSide()) return;

        // handle traits
        traits.forEach(it -> it.onEquip(player));

    }

    public final void onRemove(Player player) {
        if (player.level().isClientSide()) return;

        // handle traits
        traits.forEach(it -> it.onRemove(player));
    }

    @Override
    public final void getAdditionalTooltip(List<Component> tooltip) {
        traits.forEach(it -> it.getTooltip(tooltip));
    }

    public List<Trait> getTraits() {
        return traits;
    }

    @Override
    public String toString() {
        return id();
    }

    public final String localizationKey() {
        return "name." + Machina.ID + "." + id();
    }

    public static final Gear EMPTY = new Gear("empty") {
        @Override
        public String displayName() {
            return "EMPTY";
        }
    };


    public static final Codec<Gear> CODEC = ResourceKey.codec(RegistryKeys.GEARS).xmap(
            key -> Registries.GEARS_REGISTRY.getOptional(key).orElse(Gear.EMPTY),
            gear -> Registries.GEARS_REGISTRY.getResourceKey(gear).orElse(null)
    );

    public static final StreamCodec<RegistryFriendlyByteBuf, Gear> STREAM_CODEC =
            ByteBufCodecs.registry(Registries.GEARS_REGISTRY.key());
}
