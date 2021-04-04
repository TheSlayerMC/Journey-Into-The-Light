package net.jitl.network;

import net.jitl.client.eventhandler.GuiEventHandler;
import net.jitl.client.eventhandler.music.StructureMusicHandler;
import net.jitl.common.helper.EnumStructureMusic;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.World;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.network.NetworkEvent;
import org.jetbrains.annotations.NotNull;
import ru.timeconqueror.timecore.api.common.packet.ITimePacket;

public class EssenceUpdatePacket implements ITimePacket {
    private final float essence;

    public EssenceUpdatePacket(float value) {
        essence = value;
    }

    @Override
    public @NotNull LogicalSide getReceptionSide() {
        return LogicalSide.CLIENT;
    }

    public static class Handler implements ITimePacketHandler<EssenceUpdatePacket> {
        @Override
        public void encode(EssenceUpdatePacket packet, PacketBuffer buffer) {
            buffer.writeFloat(packet.essence);
        }

        @Override
        public @NotNull EssenceUpdatePacket decode(PacketBuffer buffer) {
            return new EssenceUpdatePacket(buffer.readFloat());
        }

        @Override
        public void onPacketReceived(EssenceUpdatePacket packet, NetworkEvent.Context ctx, World world) {
            GuiEventHandler.essence = packet.essence;
        }
    }
}