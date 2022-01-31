package net.jitl.core.datagen;

import net.jitl.core.JITL;
import net.minecraft.advancements.Advancement;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.advancements.AdvancementProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class JAdvancementStartPoint extends AdvancementProvider {
    public JAdvancementStartPoint(DataGenerator generatorIn, ExistingFileHelper fileHelperIn) {
        super(generatorIn, fileHelperIn);
    }

    @Override
    protected void registerAdvancements(@NotNull Consumer<Advancement> consumer, @NotNull ExistingFileHelper fileHelper) {
        new JAdvancements().accept(consumer);
    }

    @Override
    public String getName() {
        return JITL.rl("advancements").toString();
    }
}
