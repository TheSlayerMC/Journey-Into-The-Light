package net.journey.dimension.corba.gen.maze;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public abstract class MazeRoom {

	public abstract boolean generate(MazeAddition w, Random r, int x, int y, int z);
}