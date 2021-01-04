package net.jitl.client.render;

import ru.timeconqueror.timecore.api.client.resource.BlockStateResource;
import ru.timeconqueror.timecore.api.client.resource.location.BlockModelLocation;

import static ru.timeconqueror.timecore.api.client.resource.JSONTimeResource.*;

public class JBlockStateResources {

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

	public static BlockStateResource rotatablePillarState(BlockModelLocation location) {
		String json =
				object(null, listOf(
						object("variants", listOf(
								object("axis=y", listOf(
										property("model", location.toString())
								)),
								object("axis=z", listOf(
										property("model", location.toString()),
										property("x", 90)
								)),
								object("axis=x", listOf(
										property("model", location.toString()),
										property("x", 90),
										property("y", 90)
								))
						))
						)
				);
		return BlockStateResource.fromJson(json);
	}

	public static BlockStateResource doublePlantState(BlockModelLocation bottom, BlockModelLocation top) {
		String json =
				object(null, listOf(
						object("variants", listOf(
								object("half=lower", listOf(
										property("model", bottom.toString())
								)),
								object("half=upper", listOf(
										property("model", top.toString())
								))
							))
						)
				);
		return BlockStateResource.fromJson(json);
	}
}
