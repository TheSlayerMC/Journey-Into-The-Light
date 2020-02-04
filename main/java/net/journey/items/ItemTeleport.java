package net.journey.items;

import java.util.List;

import net.journey.JourneyTabs;
import net.journey.client.server.EssenceBar;
import net.journey.client.server.EssenceProvider;
import net.journey.client.server.IEssence;
import net.journey.util.LangHelper;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.RayTraceResult.Type;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.SlayerAPI;
import net.slayer.api.item.ItemMod;

public class ItemTeleport extends ItemMod {

	public ItemTeleport(String name, String f) {
		super(name, f, JourneyTabs.util);
		setMaxStackSize(1);
		setMaxDamage(100);
	}

	@Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		IEssence mana = player.getCapability(EssenceProvider.ESSENCE_CAP, null);
		float var4 = player.rotationPitch;
		float var5 = player.rotationYaw;
		double var6 = player.posX;
		double var8 = player.posY + 1.62D;
		double var10 = player.posZ;
		Vec3d var12 = new Vec3d(var6, var8, var10);
		float var13 = MathHelper.cos(-var5 * 0.01745329F - (float)Math.PI);
		float var14 = MathHelper.sin(-var5 * 0.01745329F - (float)Math.PI);
		float var15 = -MathHelper.cos(-var4 * 0.01745329F);
		float var16 = MathHelper.sin(-var4 * 0.01745329F);
		float var17 = var14 * var15;
		float var18 = var13 * var15;
		double var19 = 30.0D;
		Vec3d var21 = var12.addVector(var17 * var19, var16 * var19, var18 * var19);
		RayTraceResult var22 = worldIn.rayTraceBlocks(var12, var21);
		if(var22 == null) {
			return EnumActionResult.FAIL;
		} else {
			if (var22.typeOfHit == Type.BLOCK) {
				int var23 = var22.getBlockPos().getX();
				int var24 = var22.getBlockPos().getY();
				int var25 = var22.getBlockPos().getZ();
				int var26 = var22.subHit;

				if (var26 == 0) --var24;                
				if (var26 == 1) ++var24;               
				if (var26 == 2) --var25;                
				if (var26 == 3) ++var25;                
				if (var26 == 4) --var23;                
				if (var26 == 5) ++var23;                

				if(mana.useEssence(5)) {
					player.getLook(1);
					this.teleportTo(player, worldIn, var23, var24 + 1, var25);
					player.getHeldItem(EnumHand.MAIN_HAND).damageItem(1, player);
				}
			}
		}
		return EnumActionResult.SUCCESS;
	}

	protected void teleportTo(EntityPlayer par1, World par2, double par3, double par4, double par5) {
		par1.setPosition(par3, par4, par5);
		par2.playSound(par1, new BlockPos(par3, par4, par5), SoundEvents.ENTITY_ENDERMEN_TELEPORT, SoundCategory.PLAYERS, 1.0F, 1.0F);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World worldIn, List<String> list, ITooltipFlag flagIn) {
		list.add(SlayerAPI.Colour.DARK_GREEN + LangHelper.useEssence(5));
		list.add(stack.getMaxDamage() - stack.getItemDamage() + " " + LangHelper.getUsesRemaining());
	}
}