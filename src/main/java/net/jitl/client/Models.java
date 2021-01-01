package net.jitl.client;

import net.jitl.JITL;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import ru.timeconqueror.timecore.api.client.render.model.TimeModelLoader;
import ru.timeconqueror.timecore.client.render.model.TimeModel;

public class Models {
    public static TimeModel fullCube;

    public static void regToBus(IEventBus modBus, IEventBus forgeBus) {
        modBus.addListener(Models::onClientSetup);
    }

    public static void onClientSetup(FMLClientSetupEvent event) {
        fullCube = TimeModelLoader.loadJsonModel(JITL.rl("models/tile/full_cube.json"), RenderType::entityCutout);
    }
}
