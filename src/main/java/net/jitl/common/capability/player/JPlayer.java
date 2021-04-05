package net.jitl.common.capability.player;

import net.jitl.common.capability.player.data.Essence;
import net.jitl.init.JCapabilities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.network.PacketDistributor;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.timeconqueror.timecore.common.capability.CoffeeCapability;
import ru.timeconqueror.timecore.common.capability.ICoffeeCapability;
import ru.timeconqueror.timecore.common.capability.owner.CapabilityOwner;
import ru.timeconqueror.timecore.common.capability.owner.serializer.CapabilityOwnerSerializer;
import ru.timeconqueror.timecore.common.capability.property.CoffeeProperty;
import ru.timeconqueror.timecore.common.capability.property.serializer.NBTPropertySerializer;

public class JPlayer extends CoffeeCapability<Entity> implements IJPlayer {
    @SuppressWarnings("Convert2MethodRef")
    public final CoffeeProperty<Essence> essence = prop("essence", new Essence(), new NBTPropertySerializer<>(() -> this.essence.get()));

    private final PlayerEntity player;

    public JPlayer(PlayerEntity player) {
        this.player = player;
    }

    @NotNull
    @Override
    public Capability<? extends ICoffeeCapability<Entity>> getCapability() {
        return JCapabilities.PLAYER;
    }

    @NotNull
    @Override
    public CapabilityOwnerSerializer<Entity> getOwnerSerializer() {
        return CapabilityOwner.ENTITY.getSerializer();
    }

    @Override
    public void onSendChangesToClients(@NotNull SimpleChannel channel, @NotNull Object data) {
        channel.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) player), data);
    }

    public void detectAndSendChanges() {
        detectAndSendChanges(player.level, player);
    }

    public void sendAllData() {
        sendAllData(player.level, player);
    }

    @Nullable
    public static JPlayer from(@Nullable PlayerEntity player) {
        if (player != null) {
            LazyOptional<IJPlayer> cap = player.getCapability(JCapabilities.PLAYER);

            if (cap.isPresent()) {
                return (JPlayer) cap.orElseThrow(IllegalStateException::new);
            }
        }

        return null;
    }
}
