package net.journey.items.bauble;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import net.journey.init.JourneyTabs;
import net.journey.items.base.JItem;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.SlayerAPI;

import java.util.List;

public class ItemDeathCap extends JItem implements IBauble {

	public ItemDeathCap(String name, String enName) {
		super(name, enName);
		setCreativeTab(JourneyTabs.UTIL);
	}

	@Override
	public BaubleType getBaubleType(ItemStack itemStack) {
		return BaubleType.HEAD;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack i, World worldIn, List<String> l, ITooltipFlag flagIn) {
		l.add(SlayerAPI.Colour.GOLD + "Grants increased jump height and poisons attacking foe");
	}
}
