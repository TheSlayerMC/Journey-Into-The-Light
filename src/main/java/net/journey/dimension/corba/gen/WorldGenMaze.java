package net.journey.dimension.corba.gen;

import java.util.ArrayList;
import java.util.Random;

import net.journey.dimension.corba.gen.maze.MazeAddition;
import net.journey.dimension.corba.gen.maze.MazeRoom;
import net.minecraft.world.World;

public class WorldGenMaze extends MazeRoom {

	public ArrayList<MazeRoom> rooms;

	@Override
	public boolean generate(MazeAddition w, Random r, int x, int y, int z) {
		rooms = new ArrayList(0);
		rooms.get(r.nextInt(rooms.size())).generate(w, r, x + 7, y, z);
		return true;
	}
}