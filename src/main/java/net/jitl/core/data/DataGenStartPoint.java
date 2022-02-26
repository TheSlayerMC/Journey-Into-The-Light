package net.jitl.core.data;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;
import ru.timeconqueror.timecore.api.devtools.gen.loottable.TimeLootTableGenerator;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenStartPoint {

    @SubscribeEvent
    public static void onDataGathering(GatherDataEvent event) {
        DataGenerator dataGenerator = event.getGenerator();
        ExistingFileHelper helper = event.getExistingFileHelper();

        TimeLootTableGenerator lootTableGenerator = new TimeLootTableGenerator(dataGenerator)
                .addSet(new JBlockLootTableSet())
                .addSet(new JChestLootTableSet());

        JRecipeRegister recipeProvider = new JRecipeRegister(dataGenerator);
        JBlockTags blockTags = new JBlockTags(dataGenerator, helper);
        JItemTags itemTags = new JItemTags(dataGenerator, blockTags, helper);
        JEntityTags entityTags = new JEntityTags(dataGenerator, helper);
        JAdvancementStartPoint advancements = new JAdvancementStartPoint(dataGenerator, helper);

        //Adds data providers
        if (event.includeServer()) {
            dataGenerator.addProvider(lootTableGenerator);
            dataGenerator.addProvider(recipeProvider);
            dataGenerator.addProvider(blockTags);
            dataGenerator.addProvider(itemTags);
            dataGenerator.addProvider(entityTags);
            dataGenerator.addProvider(advancements);
        }
        //dataGenerator.addProvider(biomeProvider);
    }
}
