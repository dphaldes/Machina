package com.mystchonky.machina.api.arsenal.gear;

import com.mojang.serialization.Codec;
import com.mystchonky.machina.common.item.GearItem;
import com.mystchonky.machina.common.registrar.MachinaRegistries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.entity.player.Player;

import javax.annotation.Nullable;
import javax.annotation.OverridingMethodsMustInvokeSuper;

public abstract class AbstractGear {
    public static final Codec<AbstractGear> CODEC = MachinaRegistries.GEAR_REGISTRY.byNameCodec();
    public static final StreamCodec<RegistryFriendlyByteBuf, AbstractGear> STREAM_CODEC = ByteBufCodecs.registry(MachinaRegistries.GEAR_REGISTRY.key());
    private final String id;
    @Nullable
    private GearItem gearItem;

    protected AbstractGear(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public GearItem getGearItem() {
        if (gearItem == null) {
            this.gearItem = new GearItem(this);
        }
        return gearItem;
    }

    public abstract String getDisplayName();

    protected boolean checkCompatibility(AbstractGear other) {
        return this != other;
    }

    public final boolean isCompatibleWith(AbstractGear other) {
        return this.checkCompatibility(other) && other.checkCompatibility(this);
    }

    @OverridingMethodsMustInvokeSuper
    public void onEquip(Player player) {
        if (player.level().isClientSide()) return;

        if (this instanceof AttributeGear attributeGear) {
            var gearMap = attributeGear.getModifiers();
            var playerMap = player.getAttributes();
            gearMap.forEach((holder, modifier) -> {
                var instance = playerMap.getInstance(holder);
                if (instance != null) instance.addPermanentModifier(modifier);
            });
        }
    }

    @OverridingMethodsMustInvokeSuper
    public void onUnequip(Player player) {
        if (player.level().isClientSide()) return;

        if (this instanceof AttributeGear attributeGear) {
            var gearMap = attributeGear.getModifiers();
            var playerMap = player.getAttributes();
            gearMap.forEach((holder, modifier) -> {
                var instance = playerMap.getInstance(holder);
                if (instance != null) instance.removeModifier(modifier);
            });
        }

    }

    @Override
    public String toString() {
        return getId();
    }
}
