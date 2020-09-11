package net.journey.common.capability;

import net.journey.JITL;
import net.journey.api.capability.JourneyPlayer;
import net.journey.common.capability.innercaps.EssenceStorageImpl;
import net.journey.common.capability.innercaps.PlayerPortalOverlayImpl;
import net.journey.common.capability.innercaps.PlayerStatsImpl;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.jetbrains.annotations.NotNull;

@Mod.EventBusSubscriber
public class JCapabilityManager {
	public static final ResourceLocation JOURNEY_PLAYER_CAP = JITL.rl("journey_player");

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
		CapabilityManager.INSTANCE.register(JourneyPlayer.class, JourneyPlayerImpl.Serializer.INSTANCE, () -> new JourneyPlayerImpl(new EssenceStorageImpl(), new PlayerStatsImpl(), new PlayerPortalOverlayImpl()));
	}

	@SubscribeEvent
	public static void attachCapabilities(AttachCapabilitiesEvent<Entity> event) {
		if (event.getObject() instanceof EntityPlayer) {
			event.addCapability(JOURNEY_PLAYER_CAP, new JourneyPlayerCapProvider());
		}
	}

	@SubscribeEvent
	public static void syncCapabilities(EntityJoinWorldEvent event) {
		if (!event.getWorld().isRemote && event.getEntity() instanceof EntityPlayer) {
			EntityPlayerMP player = (EntityPlayerMP) event.getEntity();
			JourneyPlayerImpl journeyPlayer = (JourneyPlayerImpl) asJourneyPlayer(player);

			journeyPlayer.bindPlayer(player);
			journeyPlayer.sendUpdates();
		}
	}

	@SubscribeEvent
	public static void onPlayerDeath(PlayerEvent.Clone event) {
		JourneyPlayerImpl.Serializer.INSTANCE.copy(asJourneyPlayer(event.getOriginal()), asJourneyPlayer(event.getEntityPlayer()));
		//We don't need to send packets here, because after this method, the method onPlayerJoin will be fired.
	}
}
