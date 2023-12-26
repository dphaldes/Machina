package com.mystchonky.machina.common.nexus;

import net.minecraft.nbt.CompoundTag;
import net.neoforged.neoforge.common.util.INBTSerializable;

public interface IExtendedNexusData<T extends IExtendedNexusData<T>> extends INBTSerializable<CompoundTag> {
}
