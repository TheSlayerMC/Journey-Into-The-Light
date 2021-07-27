package net.jitl.common.item.gear.abilities.celestium;

import net.jitl.common.item.gear.abilities.FullArmorAbility;
import net.jitl.common.item.gear.abilities.IAbility;
import net.minecraft.nbt.CompoundNBT;

public class CelestiumArmorAbility implements IAbility {
    @Override
    public FullArmorAbility getFullAbility(CompoundNBT nbt) {
        return new CelestiumFullAbility(nbt);
    }
}
