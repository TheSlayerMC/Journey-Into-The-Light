package net.jitl.core.network.dialogue;

import net.jitl.common.dialog.DialogCharacter;
import net.jitl.common.dialog.DialogNetHandler;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;
import ru.timeconqueror.timecore.api.common.packet.SimplePacketHandler;

import java.io.IOException;

public record SSetCharacterPacket(DialogCharacter character) {
    public static class Handler extends SimplePacketHandler<SSetCharacterPacket> {
        @Override
        public void encode(SSetCharacterPacket packet, FriendlyByteBuf buffer) {
            DialogCharacter character = packet.character;
            buffer.writeRegistryIdUnsafe(ForgeRegistries.ENTITIES, character.entityType());

            buffer.writeBoolean(character.customName() != null);
            if (character.customName() != null) {
                buffer.writeUtf(character.customName());
            }

            buffer.writeBoolean(character.data() != null);
            if (character.data() != null) {
                buffer.writeNbt(character.data());
            }
        }

        @Override
        @SuppressWarnings("unchecked")
        public @NotNull SSetCharacterPacket decode(FriendlyByteBuf buffer) throws IOException {
            EntityType<? extends LivingEntity> type = (EntityType<? extends LivingEntity>) buffer.readRegistryIdUnsafe(ForgeRegistries.ENTITIES);

            String customName = null;
            if (buffer.readBoolean()) {
                customName = buffer.readUtf();
            }

            CompoundTag nbt = null;
            if (buffer.readBoolean()) {
                nbt = buffer.readNbt();
            }

            return new SSetCharacterPacket(new DialogCharacter(type, customName, nbt));
        }

        @Override
        public void handleOnMainThread(SSetCharacterPacket packet, NetworkEvent.Context ctx) {
            DialogNetHandler.handleSetCharacter(packet);
        }
    }
}
