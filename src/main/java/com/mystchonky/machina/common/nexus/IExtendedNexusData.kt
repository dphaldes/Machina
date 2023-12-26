package com.mystchonky.machina.common.nexus

import net.minecraft.nbt.CompoundTag
import net.neoforged.neoforge.common.util.INBTSerializable

interface IExtendedNexusData<T : IExtendedNexusData<T>> : INBTSerializable<CompoundTag>
