package net.jitl.common.helper;

import net.jitl.common.container.EmptyContainer;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class JContainers {
    public static MenuType<EmptyContainer> EMPTY_CONTAINER;

    @SubscribeEvent
    public static void registerContainers(RegistryEvent.Register<MenuType<?>> event) {
        EMPTY_CONTAINER = IForgeMenuType.create(EmptyContainer::createContainerClientSide);
        EMPTY_CONTAINER.setRegistryName("empty_container");
        event.getRegistry().register(EMPTY_CONTAINER);
    }

}