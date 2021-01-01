package net.jitl.client.render;

import ru.timeconqueror.timecore.api.client.resource.BlockStateResource;
import ru.timeconqueror.timecore.api.client.resource.location.BlockModelLocation;

import static ru.timeconqueror.timecore.api.client.resource.JSONTimeResource.*;

public class JBlockStates {

	public static BlockStateResource orientableState(BlockModelLocation location) {
		String json =
				object(null, listOf(
						object("variants", listOf(
								object("facing=north", listOf(
										property("model", location.toString())
								)),
								object("facing=south", listOf(
										property("model", location.toString()),
										property("y", 180)
								)),
								object("facing=west", listOf(
										property("model", location.toString()),
										property("y", 270)
								)),
								object("facing=east", listOf(
										property("model", location.toString()),
										property("y", 90)
								))
						))
						)
				);
		return BlockStateResource.fromJson(json);
	}
}
