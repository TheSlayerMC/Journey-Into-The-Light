package net.journey.items;

import java.util.List;

import net.journey.JourneySounds;
import net.journey.JourneyTabs;
import net.journey.client.server.EssenceProvider;
import net.journey.client.server.IEssence;
import net.journey.entity.projectile.EntityBasicProjectile;
import net.journey.util.LangHelper;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.SlayerAPI;
import net.slayer.api.item.ItemMod;

public class ItemStaff extends ItemMod {

	protected int usage;
	protected int damage;
	protected boolean unBreakable;
	protected Class<? extends EntityBasicProjectile> projectile; 

	public ItemStaff(String name, String f, int magic, int uses, int dam, boolean unbreakable, Class<? extends EntityBasicProjectile> projectile) {
		super(name, f);
		this.projectile = projectile;
		damage = dam;
		usage = magic;
		this.unBreakable = unbreakable;
		setMaxDamage(uses);
		setMaxStackSize(1);
		setFull3D();
		setCreativeTab(JourneyTabs.weapons);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand handIn) {
		ItemStack stack = player.getHeldItem(handIn);
		IEssence mana = player.getCapability(EssenceProvider.ESSENCE_CAP, null);

		if(!world.isRemote && mana.useEssence(usage)) {
			JourneySounds.playSound(JourneySounds.MAGIC_SPARKLE, world, player);
			if(!unBreakable) stack.damageItem(1, player);
			try {
				EntityBasicProjectile shoot = projectile.getConstructor(World.class, EntityLivingBase.class, float.class).newInstance(world, player, damage);
				shoot.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, 3.5F, 0.2F);
				world.spawnEntity(shoot);
				stack.damageItem(1, player);
				return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return new ActionResult<ItemStack>(EnumActionResult.FAIL, stack);	
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack i, World worldIn, List<String> l, ITooltipFlag flagIn) {
		l.add(SlayerAPI.Colour.DARK_GREEN + LangHelper.rangedDamage(damage));
		LangHelper.useEssence(usage);
		if(unBreakable) LangHelper.getInfiniteUses();
		else l.add(i.getMaxDamage() - i.getItemDamage() + " " + LangHelper.getUsesRemaining());
	}
}