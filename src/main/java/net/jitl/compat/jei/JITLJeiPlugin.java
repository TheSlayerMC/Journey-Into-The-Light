package net.jitl.compat.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.ModIds;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.registration.IRecipeRegistration;
import net.jitl.JITL;
import net.jitl.init.JEnchantments;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.item.EnchantedBookItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@JeiPlugin
public class JITLJeiPlugin implements IModPlugin {

    @Override
    public @NotNull ResourceLocation getPluginUid() {
        return JITL.rl(ModIds.JEI_ID);
    }

    @Override
    public void registerRecipes(@NotNull IRecipeRegistration registration) {
        List<ItemStack> ambitBooks = IntStream.range(1, JEnchantments.AMBIT.get().getMaxLevel()).mapToObj((level) -> {
            ItemStack ambitBook = new ItemStack(Items.ENCHANTED_BOOK);
            EnchantedBookItem.addEnchantment(ambitBook, new EnchantmentData(JEnchantments.AMBIT.get(), level));
            JITL.LOGGER.info("registered books");
            return ambitBook;
        }).collect(Collectors.toList());
        JITL.LOGGER.info("registered books?");
        registration.addIngredientInfo(ambitBooks, VanillaTypes.ITEM, "The is awsome. it is cool! you can do something. !");
    }
}
