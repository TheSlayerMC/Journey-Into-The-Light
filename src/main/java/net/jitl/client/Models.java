package net.jitl.client;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import ru.timeconqueror.timecore.client.render.model.TimeModel;

public class Models {
    public static TimeModel fullCube;

    public static void regToBus(IEventBus modBus, IEventBus forgeBus) {
        modBus.addListener(Models::onClientSetup);
    }

    public static void onClientSetup(FMLClientSetupEvent event) {
        //FIXME fix when JsonModelParser is ported for TimeCore
        //fullCube = TimeModelLoader.loadJsonModel(JITL.rl("models/tile/full_cube.json"), RenderType::entityCutout);
    }
}
