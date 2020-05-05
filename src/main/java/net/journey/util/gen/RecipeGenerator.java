package net.journey.util.gen;

import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.journey.JITL;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

public class RecipeGenerator {
	private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
	private static final Set<String> USED_OD_NAMES = new TreeSet<>();

	private final File outputDir;

	public RecipeGenerator() throws IOException {
		this(new File("../src/main/resources/assets/journey/recipes/"));
	}

	public RecipeGenerator(File outputDir) throws IOException {
		this.outputDir = outputDir;
		prepareOutputDir();
	}

	/**
	 * Generates shaped recipe depending on params.
	 *
	 * @param output recipe output
	 * @param params the first 1-3 elements should represent grid pattern strings, the next elements should be in pair: key, value, key, value...
	 *               acceptable key: char
	 *               acceptable value: Block, Item, ItemStack, oredict string.
	 */
	public void genShapedRecipe(ItemStack output, Object... params) {
		if (params.length == 0) {
			throw new IllegalArgumentException("Provided empty component array!");
		}

		String patternLine1;
		if (params[0] instanceof String) {
			patternLine1 = (String) params[0];
		} else
			throw new IllegalArgumentException("First params must be String instances that represent pattern! Provided: " + params[0]);

		int patternSize = patternLine1.length();
		if (patternSize < 1 || patternSize > 3)
			throw new IllegalArgumentException("Pattern size must be in the range of 1 to 3! Provided grid size: " + patternSize + ". Check you first going params!");

		int i = 0;
		List<String> pattern = new ArrayList<>(patternSize);
		Object arg;

		while (i < 3) {
			arg = params[i];
			if (arg instanceof String) {
				String patternLine = (String) arg;
				if ((patternLine).length() != patternSize)
					throw new IllegalArgumentException("Wrong param: " + arg + " with index " + i + ". First " + (patternSize == 1 ? 1 : "1-" + patternSize) + " params in current recipe should represent pattern and all must be with the same length. Required length: " + patternSize + ", provided: " + (patternLine).length());
				pattern.add(patternLine);
			} else if (arg instanceof Character) {//if key entries start from this param
				break;
			} else {
				throw new IllegalArgumentException("Wrong param: " + arg + " with index " + i + ". First " + (patternSize == 1 ? 1 : "1-" + patternSize) + " params in current recipe should be pattern strings.");
			}

			i++;
		}

		if (params.length == i + 1) throw new IllegalArgumentException("You didn't provide ingredients!");

		JsonObject jsonIngreds = new JsonObject();
		boolean isOreDict = false;
		while (params.length != i) {
			if (params.length < i + 2 || params.length - (i - 2) == 1)
				throw new IllegalArgumentException("Expected that the length of the array would be at least " + (i + 2) + ", provided: " + params.length);

			Object key = params[i];
			if (key instanceof Character) {
				if (key.equals(' '))
					throw new IllegalArgumentException("Invalid key entry with index " + i + ": ' ' is a reserved symbol.");
			} else throw new IllegalArgumentException("Expected key entry in the element with index " + i);

			Object ingred = params[i + 1];
			JsonObject jsonIngred;
			if (ingred instanceof Character) {
				throw new IllegalArgumentException("Provided char key as an ingredient. Index: " + (i + 1));
			} else {
				try {
					if (ingred instanceof String) isOreDict = true;

					jsonIngred = serialize(ingred);
				} catch (IllegalArgumentException e) {
					throw new IllegalArgumentException("Wrong value param with index " + (i + 1), e);
				}
			}

			jsonIngreds.add(key.toString(), jsonIngred);

			i += 2;
		}

		JsonObject json = new JsonObject();
		json.addProperty("type", isOreDict ? "forge:ore_shaped" : "minecraft:crafting_shaped");

		JsonArray jsonPattern = new JsonArray();
		pattern.forEach(jsonPattern::add);
		json.add("pattern", jsonPattern);
		json.add("key", jsonIngreds);
		json.add("result", serialize(output));

		save(output, json);
	}

	/**
	 * Generates shapeless recipe with provided data.
	 *
	 * @param output      recipe output
	 * @param ingredients acceptable values: Block, Item, ItemStack, oredict string
	 */
	public void genShapelessRecipe(ItemStack output, Object... ingredients) {
		if (ingredients.length == 0 || ingredients.length > 9)
			throw new IllegalArgumentException("Ingredient array must contain as least 1 and at most 9 elements.");

		JsonArray jsonIngreds = new JsonArray();
		boolean isOreDict = false;
		int i = 0;
		for (Object ingred : ingredients) {
			JsonObject jsonIngred;
			try {
				if (ingred instanceof String) isOreDict = true;

				jsonIngred = serialize(ingred);
			} catch (IllegalArgumentException e) {
				throw new IllegalArgumentException("Wrong value param with index " + (i + 1), e);
			}

			jsonIngreds.add(jsonIngred);
		}

		JsonObject json = new JsonObject();
		json.addProperty("type", isOreDict ? "forge:ore_shapeless" : "minecraft:crafting_shapeless");

		json.add("ingredients", jsonIngreds);
		json.add("result", serialize(output));

		save(output, json);
	}

	private void save(ItemStack stack, JsonObject json) {
		String base = stack.getItem().getRegistryName().getPath() + (stack.getItem().getHasSubtypes() ? "_m" + stack.getItemDamage() : "");
		File f = new File(outputDir, base + ".json");

		int index = 0;
		while (f.exists()) {
			f = new File(outputDir, base + "_" + index + ".json");
			index++;
		}

		try (FileWriter w = new FileWriter(f)) {
			GSON.toJson(json, w);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void prepareOutputDir() throws IOException {
		JITL.LOGGER.info("Preparing directory " + outputDir.getAbsolutePath() + " for generated recipe files");
		if (outputDir.exists() && outputDir.isDirectory()) {
			if (!Files.isWritable(outputDir.toPath()))
				throw new IOException("Output directory " + outputDir.toPath() + " is not writable");
			File[] files = outputDir.listFiles();

			if (files != null) {
				for (File file : files) {
					if (!file.delete()) {
						throw new IOException("Can't delete file " + file);
					}
				}
			}
		} else {
			Files.createDirectories(outputDir.toPath());
		}
	}

	private JsonObject serialize(Object serializable) {
		if (serializable instanceof Item) {
			return serialize(new ItemStack((Item) serializable));
		} else if (serializable instanceof Block) {
			return serialize(new ItemStack((Block) serializable));
		} else if (serializable instanceof ItemStack) {
			ItemStack stack = (ItemStack) serializable;
			JsonObject ret = new JsonObject();

			if (ItemStack.areItemStacksEqual(stack, ItemStack.EMPTY))
				throw new IllegalArgumentException("Empty itemstack mustn't be used for recipes.");
			ret.addProperty("item", stack.getItem().getRegistryName().toString());

			if (stack.getItem().getHasSubtypes() || stack.getItemDamage() != 0) {
				ret.addProperty("data", stack.getItemDamage());
			}

			if (stack.getCount() > 1) {
				ret.addProperty("count", stack.getCount());
			}

			if (stack.hasTagCompound()) {
				ret.addProperty("type", "minecraft:item_nbt");
				ret.addProperty("nbt", stack.getTagCompound().toString());
			}

			return ret;
		} else if (serializable instanceof String) {
			JsonObject itemJson = new JsonObject();
			USED_OD_NAMES.add((String) serializable);
			itemJson.addProperty("item", "#" + ((String) serializable).toUpperCase(Locale.ROOT));
			return itemJson;
		}

		throw new IllegalArgumentException(serializable + " is not a block, item, itemstack, or ore dictionary name");
	}

	// Call this after you are done generating
	public void generateConstants() {
		if (USED_OD_NAMES.isEmpty()) return;

		List<Map<String, Object>> json = new ArrayList<>();
		for (String s : USED_OD_NAMES) {
			Map<String, Object> entry = new HashMap<>();
			entry.put("name", s.toUpperCase(Locale.ROOT));
			entry.put("ingredient", ImmutableMap.of("type", "forge:ore_dict", "ore", s));
			json.add(entry);
		}

		try (FileWriter w = new FileWriter(new File(outputDir, "_constants.json"))) {
			GSON.toJson(json, w);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
