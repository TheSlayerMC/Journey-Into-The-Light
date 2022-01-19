package net.jitl.common.capability.player;

import net.jitl.common.capability.JCapabilityProvider;
import net.jitl.common.capability.player.data.Essence;
import net.jitl.common.capability.player.data.FogDensity;
import net.jitl.common.capability.player.data.Sentacoins;
import net.jitl.init.JCapabilities;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.timeconqueror.timecore.common.capability.CoffeeCapability;
import ru.timeconqueror.timecore.common.capability.owner.CapabilityOwner;
import ru.timeconqueror.timecore.common.capability.owner.serializer.CapabilityOwnerSerializer;

public class JPlayer implements IJPlayer {

    private final Player player;

    public JPlayer(Player player) {
        this.player = player;
    }

   /* public void detectAndSendChanges() {
        detectAndSendChanges(player.level, player);
    }

    public void sendAllData() {
        sendAllData(player.level, player);
    }*/

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public Essence getEssence() {
        return new Essence();
    }

    @Override
    public Sentacoins getSentacoins() {
        return new Sentacoins();
    }

    @Override
    public FogDensity fogDensity() {
        return new FogDensity();
    }

    @Override
    public CompoundTag serializeNBT() {
        return player.serializeNBT();
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        this.player.deserializeNBT(nbt);
    }
}
