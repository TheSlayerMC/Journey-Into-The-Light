package net.jitl.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import ru.timeconqueror.timecore.devtools.gen.loottable.TimeLootTableGenerator;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenStartPoint {

    @SubscribeEvent
    public static void onDataGathering(GatherDataEvent event) {
        DataGenerator dataGenerator = event.getGenerator();

        TimeLootTableGenerator lootTableGenerator = new TimeLootTableGenerator(dataGenerator)
                .addSet(new JLootTableSet());
        JRecipeProvider recipeProvider = new JRecipeProvider(dataGenerator);
        JBiomeProvider biomeProvider = new JBiomeProvider(dataGenerator);

        //Adds data providers
        dataGenerator.addProvider(lootTableGenerator);
        dataGenerator.addProvider(recipeProvider);
        //dataGenerator.addProvider(biomeProvider);
    }
}
