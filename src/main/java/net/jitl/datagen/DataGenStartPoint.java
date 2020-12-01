package net.jitl.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import ru.timeconqueror.timecore.devtools.gen.loottable.LootTableSet;
import ru.timeconqueror.timecore.devtools.gen.loottable.TimeLootTableProvider;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenStartPoint {

    @SubscribeEvent
    public static void onDataGathering(GatherDataEvent event) {
        DataGenerator dataGenerator = event.getGenerator();

        TimeLootTableProvider lootTableProvider = new TimeLootTableProvider(dataGenerator);
        JRecipeProvider recipeProvider = new JRecipeProvider(dataGenerator);
        JBiomeProvider biomeProvider = new JBiomeProvider(dataGenerator);

        //Adds data providers
        dataGenerator.addProvider(lootTableProvider);
        dataGenerator.addProvider(recipeProvider);
        //dataGenerator.addProvider(biomeProvider);

        //Adds loot table set to the loot table provider
        LootTableSet set = new JLootTableSet();
        lootTableProvider.addLootTableSet(set);
    }
}
