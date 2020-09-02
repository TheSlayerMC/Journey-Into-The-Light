package net.journey.common.capability;

import net.journey.api.capability.EssenceStorage;
import net.journey.api.capability.JourneyPlayer;
import net.journey.api.capability.PlayerStats;
import net.journey.common.capability.innercaps.EssenceStorageImpl;
import net.journey.common.capability.innercaps.PlayerStatsImpl;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.relauncher.Side;

import javax.annotation.Nullable;

public class JourneyPlayerImpl implements JourneyPlayer {
    private static final EssenceStorageImpl.Serializer ESSENCE_STORAGE_SERIALIZER = new EssenceStorageImpl.Serializer();
    private static final PlayerStatsImpl.Serializer PLAYER_STATS_SERIALIZER = new PlayerStatsImpl.Serializer();
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
        essenceStorage.update();
        playerStats.update();
    }

    public static class Serializer implements Capability.IStorage<JourneyPlayer> {
        @Nullable
        @Override
        public NBTBase writeNBT(Capability<JourneyPlayer> capability, JourneyPlayer instance, EnumFacing side) {
            if (!(instance instanceof JourneyPlayerImpl)) {
                throw new IllegalArgumentException("Can not serialize an instance that isn't the default implementation");
            }

            JourneyPlayerImpl playerCap = (JourneyPlayerImpl) instance;

            NBTTagCompound compound = new NBTTagCompound();
            compound.setTag("essence_storage", ESSENCE_STORAGE_SERIALIZER.writeToNBT(playerCap.essenceStorage));
            compound.setTag("player_stats", PLAYER_STATS_SERIALIZER.writeToNBT(playerCap.playerStats));

            return compound;
        }

        @Override
        public void readNBT(Capability<JourneyPlayer> capability, JourneyPlayer instance, EnumFacing side, NBTBase nbt) {
            if (!(instance instanceof JourneyPlayerImpl)) {
                throw new IllegalArgumentException("Can not deserialize to an instance that isn't the default implementation");
            }

            JourneyPlayerImpl playerCap = (JourneyPlayerImpl) instance;

            NBTTagCompound compound = ((NBTTagCompound) nbt);

            if (compound.hasKey("essence_storage")) {
                ESSENCE_STORAGE_SERIALIZER.readFromNBT(playerCap.essenceStorage, compound.getTag("essence_storage"));
            }

            if (compound.hasKey("player_stats")) {
                PLAYER_STATS_SERIALIZER.readFromNBT(playerCap.playerStats, compound.getTag("player_stats"));
            }
        }
    }
}
