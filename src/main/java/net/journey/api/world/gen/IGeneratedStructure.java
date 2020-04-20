package net.journey.api.world.gen;

import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.gen.structure.template.PlacementSettings;

public interface IGeneratedStructure {

    WorldServer getWorldServer(World w);

    PlacementSettings getSetting();

}