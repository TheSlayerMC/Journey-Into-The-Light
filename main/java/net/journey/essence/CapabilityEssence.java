package net.journey.essence;

import net.journey.client.server.IEssence;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class CapabilityEssence implements IStorage<IEssence> {
    private static final String essence = "essence";
    private static final String regenDelay = "regenDelay";
    private static final String maxArcana = "maxEssence";

    @Override
    public NBTBase writeNBT(Capability<IEssence> capability, IEssence instance, EnumFacing side) {
        NBTTagCompound result = new NBTTagCompound();

        result.setInteger(essence, instance.getEssence());
        result.setInteger(regenDelay, instance.getRegenDelay());
        result.setInteger(maxArcana, instance.getMaxEssence());

        return result;
    }

    @Override
    public void readNBT(Capability<IEssence> capability, IEssence instance, EnumFacing side, NBTBase nbt) {
        // Compatibility for previous versions
        if (NBTBase.getTagTypeName(nbt.getId()).equals("TAG_Compound")) {
            NBTTagCompound saved = (NBTTagCompound) nbt;

            instance.set(saved.getInteger(essence));
            instance.setRegenDelay(Math.max(1, saved.getInteger(regenDelay)));
            instance.setMaxEssence(Math.max(1, saved.getInteger(maxArcana)));
        }
    }
}