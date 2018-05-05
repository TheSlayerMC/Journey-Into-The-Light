package net.journey.items;

import java.util.List;

import net.journey.JourneyTabs;
import net.journey.client.server.DarkEnergyBar;
import net.journey.client.server.EssenceBar;
import net.journey.entity.projectile.EntityBasicProjectile;
import net.journey.enums.EnumSounds;
import net.journey.util.LangHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.SlayerAPI;
import net.slayer.api.item.ItemMod;

public class ItemStaff extends ItemMod {

	protected int usage;
	protected int damage;
	protected boolean essence, unBreakable;
	protected Class<? extends EntityBasicProjectile> projectile; 

	public ItemStaff(String name, String f, boolean essence, int magic, int uses, int dam, boolean unbreakable, Class<? extends EntityBasicProjectile> projectile) {
		super(name, f);
		this.projectile = projectile;
		damage = dam;
		usage = magic;
		this.essence = essence;
		this.unBreakable = unbreakable;
		setMaxDamage(uses);
		setMaxStackSize(1);
		setFull3D();
		setCreativeTab(JourneyTabs.staves);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		if(essence) {
			if(!world.isRemote && EssenceBar.getProperties(player).useBar(usage)) {
				EnumSounds.playSound(EnumSounds.SPARKLE, world, player);
				if(!unBreakable) stack.damageItem(1, player);
				try {
					world.spawnEntityInWorld(projectile.getConstructor(World.class, EntityLivingBase.class, float.class).newInstance(world, player, damage));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} else {
			if(!world.isRemote && DarkEnergyBar.getProperties(player).useBar(usage)) {
				EnumSounds.playSound(EnumSounds.SPARKLE, world, player);
				if(!unBreakable) stack.damageItem(1, player);
				try {
					world.spawnEntityInWorld(projectile.getConstructor(World.class, EntityLivingBase.class, float.class).newInstance(world, player, damage));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return stack;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List list) {
		list.add(SlayerAPI.Colour.DARK_GREEN + LangHelper.rangedDamage(damage));
		if(essence) LangHelper.useDarkEnergy(usage);
		else LangHelper.useDarkEnergy(usage);
		if(unBreakable) LangHelper.getInfiniteUses();
		else list.add(stack.getMaxDamage() - stack.getItemDamage() + " " + LangHelper.getUsesRemaining());
	}
}