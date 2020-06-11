package net.journey.items.interactive;

import net.journey.JITL;
import net.journey.api.scroll.ScrollAPI;
import net.journey.api.scroll.ScrollCategory;
import net.journey.api.scroll.ScrollEntry;
import net.journey.client.render.gui.scroll.GuiLoreScrollEntry;
import net.journey.items.base.JItem;
import net.journey.util.ChatUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.timeconqueror.timecore.api.util.Pair;

public class ItemLoreScroll extends JItem {

	public ItemLoreScroll(String name, String finalName) {
		super(name, finalName);
	}

	@Override
	public @NotNull ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, @NotNull EnumHand handIn) {
		ItemStack heldItem = playerIn.getHeldItem(handIn);

		if (worldIn.isRemote) {
			ScrollEntry entry = getScrollEntry(heldItem);
			if (entry != null) {
				displayScrollGui(null, entry);
			} else {
				ChatUtils.sendInformativeError(playerIn, "Can't retrieve entry from provided itemstack.", Pair.of("Itemstack", heldItem), Pair.of("Tag Compound", heldItem.getTagCompound()));
			}
		}

		return new ActionResult<>(EnumActionResult.SUCCESS, heldItem);
	}

	/**
	 * Utility method to display method.
	 * Created because implicit casting of GuiLoreScrollEntry to GuiScreen will cause crash on server
	 */
	@SideOnly(Side.CLIENT)
	private static void displayScrollGui(ScrollCategory category, ScrollEntry entry) {
		Minecraft.getMinecraft().displayGuiScreen(new GuiLoreScrollEntry(category, entry));
	}

	/**
	 * Writes scroll entry into provided itemstack.
	 * If itemstack is not an ItemLoreScroll item, it will print the error and won't write nbt tag.
	 */
	public static void bindScrollEntry(ItemStack stack, ScrollEntry entry) {
		if (stack.getItem() instanceof ItemLoreScroll) {
			NBTTagCompound tagCompound = stack.getTagCompound();

			if (tagCompound == null) tagCompound = new NBTTagCompound();

			tagCompound.setString("entry", entry.getId());

			stack.setTagCompound(tagCompound);
		} else {
			JITL.LOGGER.error("Provided stack param is not an {}", ItemLoreScroll.class, new IllegalArgumentException());
		}
	}

	/**
	 * Returns scroll entry of provided itemstack.
	 * If provided itemstack is not an ItemLoreScroll item, it will print an error and return null.
	 * If provided itemstack doesn't have tag compound, or there is no 'entry' record in it, it will return null.
	 *
	 * @return scroll entry of provided itemstack
	 */
	@Nullable
	public static ScrollEntry getScrollEntry(ItemStack stack) {
		if (stack.getItem() instanceof ItemLoreScroll) {
			NBTTagCompound tagCompound = stack.getTagCompound();
			if (tagCompound != null && tagCompound.hasKey("entry")) {
				String id = tagCompound.getString("entry");
				return ScrollAPI.getEntry(id);
			}
		} else {
			JITL.LOGGER.error("Provided stack param is not an {}", ItemLoreScroll.class, new IllegalArgumentException());
		}

		return null;
	}
}
