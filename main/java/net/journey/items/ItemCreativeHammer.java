package net.journey.items;

import java.util.List;

import net.journey.JourneyItems;
import net.journey.JourneySounds;
import net.journey.JourneyTabs;
import net.journey.entity.projectile.EntityBasicProjectile;
import net.journey.entity.projectile.EntityLightningBall;
import net.journey.util.JourneyToolMaterial;
import net.journey.util.LangHelper;
import net.journey.util.LangRegistry;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.SlayerAPI;

public class ItemCreativeHammer extends ItemSword {

	protected int use;
	protected int dam;
	protected boolean unbreakable;
	protected Class<? extends EntityBasicProjectile> projectile;
	protected JourneyToolMaterial mat;

	public ItemCreativeHammer(String name, String f, JourneyToolMaterial toolMaterial, boolean unbreakable, Class<? extends EntityLightningBall> projectile) {
		super(toolMaterial.getToolMaterial());
		this.projectile=projectile;
		this.unbreakable=unbreakable;
		setMaxStackSize(1);
		LangRegistry.addItem(name, f);
		setUnlocalizedName(name);
		mat = toolMaterial;
		setCreativeTab(JourneyTabs.hammers);
		JourneyItems.itemNames.add(SlayerAPI.PREFIX + name);
		JourneyItems.items.add(this);
		setRegistryName(SlayerAPI.MOD_ID, name);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand handIn) {
		ItemStack stack = player.getHeldItem(handIn);
		JourneySounds.playSound(JourneySounds.PLASMA, world, player);
		if(!unbreakable) stack.damageItem(1, player);
		try {
			world.spawnEntity(projectile.getConstructor(World.class, EntityLivingBase.class, float.class).newInstance(world, player, dam));
			return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ActionResult<ItemStack>(EnumActionResult.FAIL, stack);	
	}

	@Override
	public boolean isEnchantable(ItemStack stack) {
		return true;
	}

	@Override
	public boolean getIsRepairable(ItemStack i, ItemStack i1) {
		boolean canRepair = mat.getRepairItem() != null;
		if(canRepair) return mat.getRepairItem() == i1.getItem() ? true : super.getIsRepairable(i, i1);
		return super.getIsRepairable(i, i1);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack item, World worldIn, List<String> infoList, ITooltipFlag flagIn) {
		if(item.getMaxDamage() != -1) infoList.add(item.getMaxDamage() - item.getItemDamage() + " " + LangHelper.getUsesRemaining());
		else infoList.add(SlayerAPI.Colour.GREEN + LangHelper.getInfiniteUses());
		infoList.add(SlayerAPI.Colour.YELLOW + "Creative Only");
	}
}