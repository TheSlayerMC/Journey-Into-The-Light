package net.journey.util.compat.jei;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.ingredients.IIngredientBlacklist;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import net.journey.JourneyBlocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class JeiHooks implements IModPlugin {

	static {
		JEIInteractions.setJEIActive();
	}
	
	@Override
	public void register(IModRegistry registry) {
		registerHiddenItems(registry);	
		registry.addRecipeCatalyst(new ItemStack(JourneyBlocks.summoningTable), "journey.summoning_table");
	}
	
	private void registerHiddenItems(IModRegistry registry) {
		
	}
}
