package net.jitl.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraftforge.common.data.ForgeRecipeProvider;

import java.util.function.Consumer;

public class JRecipeProvider extends ForgeRecipeProvider {

	public JRecipeProvider(DataGenerator generatorIn) {
		super(generatorIn);
	}

	@Override
	protected void buildShapelessRecipes(Consumer<IFinishedRecipe> recipeConsumer) {
		JRecipeRegister recipeRegister = JRecipeRegister.INSTANCE;
		recipeRegister.init(recipeConsumer);
	}
}
