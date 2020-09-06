package net.journey.common.capability;

import io.netty.buffer.Unpooled;
import net.journey.api.capability.EssenceStorage;
import net.journey.api.capability.JourneyPlayer;
import net.journey.api.capability.PlayerStats;
import net.journey.common.capability.innercaps.EssenceStorageImpl;
import net.journey.common.capability.innercaps.PlayerStatsImpl;
import net.journey.common.network.NetworkHandler;
import net.journey.common.network.S2CSyncJourneyCap;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.relauncher.Side;

import javax.annotation.Nullable;

public class JourneyPlayerImpl implements JourneyPlayer {
    private final EssenceStorageImpl essenceStorage;
    private final PlayerStatsImpl playerStats;

    public JourneyPlayerImpl(EssenceStorageImpl essenceStorage, PlayerStatsImpl playerStats) {
        this.essenceStorage = essenceStorage;
        this.playerStats = playerStats;
    }

    @Override
    public EssenceStorage getEssenceStorage() {
        return essenceStorage;
    }

    @Override
    public PlayerStats getPlayerStats() {
        return playerStats;
    }

    @Override
    public void onTick(Side side) {
        essenceStorage.onTick();
        playerStats.onTick();
    }

    @Override
    public void sendUpdates(EntityPlayerMP player) {
        NetworkHandler.INSTANCE.sendTo(new S2CSyncJourneyCap(this), player);
    }

    public static class Serializer extends SyncableStorage<JourneyPlayer, JourneyPlayerImpl> {
        public static final Serializer INSTANCE = new Serializer();

        public Serializer() {
            super(JourneyPlayerImpl.class);
        }

        @Nullable
        @Override
        public NBTBase writeNBT(Capability<JourneyPlayer> capability, JourneyPlayer instance, EnumFacing side) {
            JourneyPlayerImpl playerCap = validateDefaultImpl(instance);

            NBTTagCompound compound = new NBTTagCompound();
            compound.setTag("essence_storage", playerCap.essenceStorage.serializeNBT());
            compound.setTag("player_stats", playerCap.playerStats.serializeNBT());

            return compound;
        }

        @Override
        public void readNBT(Capability<JourneyPlayer> capability, JourneyPlayer instance, EnumFacing side, NBTBase nbt) {
            JourneyPlayerImpl playerCap = validateDefaultImpl(instance);

            NBTTagCompound compound = ((NBTTagCompound) nbt);

            if (compound.hasKey("essence_storage")) {
                playerCap.essenceStorage.deserializeNBT(compound.getTag("essence_storage"));
            }

            if (compound.hasKey("player_stats")) {
                playerCap.playerStats.deserializeNBT((NBTTagCompound) compound.getTag("player_stats"));
            }
        }

        @Override
        public void writeToBuffer(JourneyPlayer instance, PacketBuffer buffer) {
            JourneyPlayerImpl playerCap = validateDefaultImpl(instance);

            playerCap.essenceStorage.writeToBuffer(buffer);
            playerCap.playerStats.writeToBuffer(buffer);
        }

        @Override
        public void readFromBuffer(JourneyPlayer instance, PacketBuffer buffer) {
            JourneyPlayerImpl playerCap = validateDefaultImpl(instance);

            playerCap.essenceStorage.readFromBuffer(buffer);
            playerCap.playerStats.readFromBuffer(buffer);
        }

        @Override
        public void copy(JourneyPlayer from, JourneyPlayer to) {
            PacketBuffer buffer = new PacketBuffer(Unpooled.buffer());

            writeToBuffer(from, buffer);
            readFromBuffer(to, buffer);
        }
    }
}
