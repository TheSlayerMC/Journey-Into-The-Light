package ru.timeconqueror.timecore.registry.newreg;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import ru.timeconqueror.timecore.api.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class StructurePieceRegister extends TimeRegister {
    private List<Pair<ResourceLocation, IStructurePieceType>> entries = new ArrayList<>();

    public StructurePieceRegister(String modId) {
        super(modId);
    }

    /**
     * Adds Structure Piece type to the delayed registry array, all entries from which will be registered later.
     *
     * @param name The structure piece type's name, will automatically have the modid as a namespace.
     *             It's better to provide name as shorter as possible, because they will be used for storing data in nbt files.
     * @param type type to be registered.
     */
    public IStructurePieceType register(String name, IStructurePieceType type) {
        entries.add(Pair.of(new ResourceLocation(getModId(), name), type));

        return type;
    }

    @Override
    public void regToBus(IEventBus bus) {
        bus.addListener(this::onSetup);
    }

    private void onSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            entries.forEach(entry -> Registry.register(Registry.STRUCTURE_PIECE, entry.getA(), entry.getB()));
            entries = null;
        });
    }
}
