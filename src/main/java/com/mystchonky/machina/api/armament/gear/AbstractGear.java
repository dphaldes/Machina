package com.mystchonky.machina.api.armament.gear;

import com.mojang.serialization.Codec;
import com.mystchonky.machina.Machina;
import com.mystchonky.machina.api.armament.gear.traits.Trait;
import com.mystchonky.machina.client.screen.TooltipProvider;
import com.mystchonky.machina.common.item.GearItem;
import com.mystchonky.machina.common.registrar.MachinaRegistries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.entity.player.Player;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractGear implements TooltipProvider {
    public static final Codec<AbstractGear> CODEC = MachinaRegistries.GEARS_REGISTRY.byNameCodec();
    public static final StreamCodec<RegistryFriendlyByteBuf, AbstractGear> STREAM_CODEC = ByteBufCodecs.registry(MachinaRegistries.GEARS_REGISTRY.key());
    private final String id;
    private final List<Trait> traits = new ArrayList<>();
    @Nullable
    private GearItem gearItem;

    protected AbstractGear(String id) {
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

    protected boolean checkCompatibility(AbstractGear other) {
        return this != other;
    }

    public final boolean isCompatibleWith(AbstractGear other) {
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
    public final void getTooltip(List<Component> tooltip) {
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
        return "name." + Machina.MODID + "." + id();
    }
}
