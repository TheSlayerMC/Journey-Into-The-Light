package net.jitl.common.capability.player;

import net.jitl.init.JCapabilities;
import net.minecraft.entity.Entity;
import net.minecraftforge.common.capabilities.Capability;
import org.jetbrains.annotations.NotNull;
import ru.timeconqueror.timecore.common.capability.CoffeeCapability;
import ru.timeconqueror.timecore.common.capability.ICoffeeCapability;
import ru.timeconqueror.timecore.common.capability.owner.CapabilityOwner;
import ru.timeconqueror.timecore.common.capability.owner.serializer.CapabilityOwnerSerializer;

public class JPlayer extends CoffeeCapability<Entity> implements IJPlayer {
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
}
