package net.jitl.core.init.client;

import net.jitl.client.render.gui.base.JFurnaceMenu;
import net.jitl.client.render.screen.JFurnaceScreen;
import net.jitl.core.JITL;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class JMenu {

    public static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, JITL.MODID);

    public static final RegistryObject<MenuType<JFurnaceMenu>> JFURNACE = CONTAINERS.register("jfurnace", () -> IForgeMenuType.create(
            (id, inv, data) -> new JFurnaceMenu(id, inv)));

    public static void init(IEventBus bus) {
        CONTAINERS.register(bus);
    }

    public static void register() {
        MenuScreens.register(JFURNACE.get(), JFurnaceScreen::new);
    }
}
