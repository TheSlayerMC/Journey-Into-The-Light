package net.jitl.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import ru.timeconqueror.timecore.devtools.gen.loottable.LootTableSet;
import ru.timeconqueror.timecore.devtools.gen.loottable.TimeLootTableProvider;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenStartPoint {
    public static void onDataGathering(GatherDataEvent event) {
        DataGenerator dataGenerator = event.getGenerator();

        TimeLootTableProvider lootTableProvider = new TimeLootTableProvider(dataGenerator);

        LootTableSet set = new JLootTableSet();

        lootTableProvider.addLootTableSet(set);

        dataGenerator.addProvider(lootTableProvider);
    }
}
