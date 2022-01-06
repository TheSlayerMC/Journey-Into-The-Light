package net.jitl.network;

import net.jitl.client.eventhandler.music.StructureMusicHandler;
import net.jitl.common.helper.EnumStructureMusic;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import org.jetbrains.annotations.NotNull;
import ru.timeconqueror.timecore.api.common.packet.ITimePacketHandler;

public class SCurrentStructurePacket {
    private final int storedID;

    public SCurrentStructurePacket(int id) {
        storedID = id;
    }

    public static class Handler implements ITimePacketHandler<SCurrentStructurePacket> {
        @Override
        public void encode(SCurrentStructurePacket packet, FriendlyByteBuf buffer) {
            buffer.writeInt(packet.storedID);
        }

        @Override
        public @NotNull SCurrentStructurePacket decode(FriendlyByteBuf buffer) {
            return new SCurrentStructurePacket(buffer.readInt());
        }

        @Override
        public boolean handle(SCurrentStructurePacket packet, NetworkEvent.Context ctx) {
            ctx.enqueueWork(() -> {
                StructureMusicHandler.currentMusic = EnumStructureMusic.getFromID(packet.storedID);//TODO check if really needed to be enqueued?
            });

            return true;
        }
    }
}
