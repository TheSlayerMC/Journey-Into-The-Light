package net.journey.common.capability;

import net.journey.JITL;
import net.journey.api.capability.JourneyPlayer;
import net.journey.common.capability.innercaps.EssenceStorageImpl;
import net.journey.common.capability.innercaps.PlayerStatsImpl;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.jetbrains.annotations.NotNull;

@Mod.EventBusSubscriber
public class JCapabilityManager {
    public static final ResourceLocation JOURNEY_PLAYER_CAP = new ResourceLocation(JITL.MOD_ID, "journey_player");

    @CapabilityInject(JourneyPlayer.class)
    private static final Capability<JourneyPlayer> JOURNEY_PLAYER = null;

    public static JourneyPlayer asJourneyPlayer(EntityPlayer player) {
        return player.getCapability(getJourneyPlayerCap(), null);
    }

    @NotNull
    @SuppressWarnings("ConstantConditions")
    public static Capability<JourneyPlayer> getJourneyPlayerCap() {
        return JOURNEY_PLAYER;
    }

    public static void init() {
        CapabilityManager.INSTANCE.register(JourneyPlayer.class, new JourneyPlayerImpl.Serializer(), () -> new JourneyPlayerImpl(new EssenceStorageImpl(10), new PlayerStatsImpl()));
    }

    @SubscribeEvent
    public static void attachCapability(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof EntityPlayer) {
            event.addCapability(JOURNEY_PLAYER_CAP, new JourneyPlayerCapProvider());
        }
    }
}
