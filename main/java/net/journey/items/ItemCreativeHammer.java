package net.journey.items;

import java.util.List;

import net.journey.JourneyItems;
import net.journey.JourneyTabs;
import net.journey.client.server.DarkEnergyBar;
import net.journey.client.server.EssenceBar;
import net.journey.entity.projectile.EntityBasicProjectile;
import net.journey.entity.projectile.EntityChaosProjectile;
import net.journey.entity.projectile.EntityLightningBall;
import net.journey.enums.EnumSounds;
import net.journey.util.EssenceToolMaterial;
import net.journey.util.LangHelper;
import net.journey.util.LangRegistry;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.SlayerAPI;

public class ItemCreativeHammer extends ItemSword{

	protected int use;
	protected int dam;
	protected boolean unbreakable;
	protected Class<? extends EntityBasicProjectile> projectile;
	protected EssenceToolMaterial mat;

	public ItemCreativeHammer(String name, String f, EssenceToolMaterial toolMaterial, boolean unbreakable, Class<? extends EntityLightningBall> projectile) {
		super(toolMaterial.getToolMaterial());
		this.projectile=projectile;
		this.unbreakable=unbreakable;
		setMaxStackSize(1);
		LangRegistry.addItem(name, f);
		setUnlocalizedName(name);
		mat = toolMaterial;
		setCreativeTab(JourneyTabs.hammers);
		JourneyItems.itemNames.add(name);
		JourneyItems.items.add(this);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		EnumSounds.playSound(EnumSounds.PLASMA, world, player);
		if(!unbreakable) stack.damageItem(1, player);
		try {
			world.spawnEntityInWorld(projectile.getConstructor(World.class, EntityLivingBase.class, float.class).newInstance(world, player, dam));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return stack;
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
	public void addInformation(ItemStack item, EntityPlayer player, List infoList, boolean par4) {
		if(item.getMaxDamage() != -1) infoList.add(item.getMaxDamage() - item.getItemDamage() + " " + LangHelper.getUsesRemaining());
		else infoList.add(SlayerAPI.Colour.GREEN + LangHelper.getInfiniteUses());
		infoList.add(SlayerAPI.Colour.YELLOW + "Creative Only");
	}
}