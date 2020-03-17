package net.journey.dimension;

import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.gen.structure.template.PlacementSettings;

public interface IGeneratedStructure {

	public abstract WorldServer getWorldServer(World w);
	public abstract PlacementSettings getSetting();
	
}