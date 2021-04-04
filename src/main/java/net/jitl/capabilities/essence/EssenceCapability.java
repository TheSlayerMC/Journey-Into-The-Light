package net.jitl.capabilities.essence;

import net.jitl.JITL;
import net.jitl.capabilities.JourneyCapabilityProvider;
import net.jitl.network.EssenceUpdatePacket;
import net.jitl.network.JPacketHandler;
import net.jitl.network.SCurrentStructurePacket;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.PacketDistributor;
import ru.timeconqueror.timecore.api.util.Requirements;

@Mod.EventBusSubscriber(modid = JITL.MODID)
public class EssenceCapability implements IEssenceCapability {
    private float maxEssence = 10.0F;

    private float currentEssence = maxEssence;

    @Override
    public void setEssence(ServerPlayerEntity player, float value) {
        if (value != currentEssence) {
            currentEssence = value;
            JPacketHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new EssenceUpdatePacket(currentEssence));
        }
    }

    @Override
    public void addEssence(ServerPlayerEntity player, float add) {
        Requirements.greaterThan(add, 0);
        setEssence(player, Math.max(add, maxEssence));
    }

    @Override
    public boolean consumeEssence(ServerPlayerEntity player, float price) {
        Requirements.greaterThan(price, 0);
        float postValue = currentEssence - price;
        if (postValue >= 0) {
            setEssence(player, postValue);
            return true;
        }
        return false;
    }

    @SubscribeEvent()
    public static void registerCapabilities(AttachCapabilitiesEvent<Entity> event) {
        if (!event.getObject().level.isClientSide() && event.getObject() instanceof PlayerEntity) {
            event.addCapability(JITL.rl("essence"), new JourneyCapabilityProvider());
        }
    }
}
