package net.journey.items.bauble;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.SlayerAPI;

import java.util.List;

public class ItemStringOfTethering extends ItemBaubleBase implements IBauble {

	public ItemStringOfTethering() {
		setMaxStackSize(1);
	}

	@Override
	public BaubleType getBaubleType(ItemStack itemStack) {
		return BaubleType.BELT;
	}

	@Override
	public void onWornTick(ItemStack itemstack, EntityLivingBase player) {
		if (player.motionY < 0.0D && !player.onGround && !player.isInWater() && !player.isInLava() && player.isSneaking()) {
			if (player.world.getBlockState(player.getPosition().east()).isBlockNormalCube() ||
					player.world.getBlockState(player.getPosition().west()).isBlockNormalCube() ||
					player.world.getBlockState(player.getPosition().north()).isBlockNormalCube() ||
					player.world.getBlockState(player.getPosition().south()).isBlockNormalCube()) {
				player.motionY *= 0.75F;
				player.fallDistance = -1.0F;
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack i, World worldIn, List<String> l, ITooltipFlag flagIn) {
		l.add(SlayerAPI.Colour.YELLOW + "Crouch on the side of a block to slow down");
	}
}
