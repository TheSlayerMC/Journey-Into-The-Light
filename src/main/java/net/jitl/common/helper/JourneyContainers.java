package net.jitl.common.helper;

import net.jitl.common.container.ContainerEmpty;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class JourneyContainers {
    public static ContainerType<ContainerEmpty> EMPTY_CONTAINER;

    @SubscribeEvent
    public static void registerContainers(RegistryEvent.Register<ContainerType<?>> event) {
        EMPTY_CONTAINER = IForgeContainerType.create(ContainerEmpty::createContainerClientSide);
        EMPTY_CONTAINER.setRegistryName("empty_container");
        event.getRegistry().register(EMPTY_CONTAINER);
    }

}