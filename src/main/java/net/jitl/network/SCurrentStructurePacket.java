package net.jitl.network;

import net.jitl.client.music.StructureMusicHandler;
import net.jitl.common.helper.EnumStructureMusic;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.World;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.network.NetworkEvent;
import org.jetbrains.annotations.NotNull;
import ru.timeconqueror.timecore.api.common.packet.ITimePacket;

public class SCurrentStructurePacket implements ITimePacket {
    private final int storedID;

    public SCurrentStructurePacket(int id) {
        storedID = id;
    }

    @Override
    public @NotNull LogicalSide getReceptionSide() {
        return LogicalSide.CLIENT;
    }

    public static class Handler implements ITimePacketHandler<SCurrentStructurePacket> {
        @Override
        public void encode(SCurrentStructurePacket packet, PacketBuffer buffer) {
            buffer.writeInt(packet.storedID);
        }

        @Override
        public @NotNull SCurrentStructurePacket decode(PacketBuffer buffer) {
            return new SCurrentStructurePacket(buffer.readInt());
        }

        @Override
        public void onPacketReceived(SCurrentStructurePacket packet, NetworkEvent.Context ctx, World world) {
            StructureMusicHandler.currentMusic = EnumStructureMusic.getFromID(packet.storedID);
        }
    }
}
