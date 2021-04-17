package net.jitl.common.capability.morphingnbt;

import net.jitl.JITL;
import net.jitl.common.capability.JCapabilityProvider;
import net.jitl.common.item.LiveNBTUpdateItem;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = JITL.MODID)
public class MorphingNBTCapability implements IMorphingNBTCapability {
    private CompoundNBT nbt = new CompoundNBT();

    @Override
    public void setNBT(CompoundNBT nbt) {
        this.nbt = nbt;
    }

    @Override
    public CompoundNBT getNBT() {
        return nbt;
    }

    @SubscribeEvent()
    public static void registerCapabilities(AttachCapabilitiesEvent<ItemStack> event) {
        if (event.getObject().getItem() instanceof LiveNBTUpdateItem) {
            event.addCapability(JITL.rl("morphing_nbt"), new JCapabilityProvider());
        }
    }
}
