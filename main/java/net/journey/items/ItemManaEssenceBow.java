package net.journey.items;

import java.util.List;

import net.journey.JourneyTabs;
import net.journey.client.ItemDescription;
import net.journey.client.server.EssenceBar;
import net.journey.entity.projectile.EntityBasicProjectile;
import net.journey.entity.projectile.EntityGreenpace;
import net.journey.enums.EnumSounds;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.SlayerAPI;
import net.slayer.api.item.ItemMod;

public class ItemManaEssenceBow extends ItemMod {

	protected int usage;
	protected int damage;
	protected boolean essence, unBreakable;
	protected Class<? extends EntityBasicProjectile> projectile; 
	public int dur = 18;
	//protected String damageString;
	public String ability;

	public ItemManaEssenceBow(String name, String f, boolean essence, int magic, int uses, int dam, boolean unbreakable, Class<? extends EntityBasicProjectile> projectile) {
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
		setRegistryName(SlayerAPI.MOD_ID, name);
	}

	@SuppressWarnings("unused")
	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityPlayer playerIn, int timeLeft) {
		int j = this.getMaxItemUseDuration(stack) - timeLeft;
		net.minecraftforge.event.entity.player.ArrowLooseEvent event = new net.minecraftforge.event.entity.player.ArrowLooseEvent(playerIn, stack, j);
		if (net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(event)) return;
		j = event.charge;

		boolean flag = playerIn.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, stack) > 0;

		if(essence) {
			float f = j / 20.0F;
			f = (f * f + f * 2.0F) / 3.0F;
			if(f < 0.1D) return;
			if(f > 1.0F) f = 1.0F;

			EntityGreenpace entityarrow = null;
			try {
			} catch(Exception e) {
				e.printStackTrace();
			}
			if(entityarrow != null)

			stack.damageItem(1, playerIn);
			worldIn.playSoundAtEntity(playerIn, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
			if(!worldIn.isRemote) worldIn.spawnEntityInWorld(entityarrow);
		} 
	}

	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityPlayer playerIn) {
		return stack;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 72000;
	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.BOW;
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list) {
		ItemDescription.addInformation(stack, player, list);
		list.add("Ammo: " + SlayerAPI.Colour.GREEN + "Essence");
		list.add("Damage: " +SlayerAPI.Colour.GOLD + damage + " - " + SlayerAPI.Colour.GOLD + damage*4);
		list.add("Ability: " + SlayerAPI.Colour.GOLD + ability);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn) {
		net.minecraftforge.event.entity.player.ArrowNockEvent event = new net.minecraftforge.event.entity.player.ArrowNockEvent(playerIn, itemStackIn);
		if(net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(event)) return event.result;
		if(essence) {
			if(!worldIn.isRemote && EssenceBar.getProperties(playerIn).useBar(usage)) {
				EnumSounds.playSound(EnumSounds.SPARKLE, worldIn, playerIn);
				if(!unBreakable) itemStackIn.damageItem(1, playerIn);
				try {
					worldIn.spawnEntityInWorld(projectile.getConstructor(World.class, EntityLivingBase.class, float.class).newInstance(worldIn, playerIn, damage));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return itemStackIn;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public ModelResourceLocation getModel(ItemStack stack, EntityPlayer player, int useRemaining) {
		if(stack.getItem() instanceof ItemManaEssenceBow && player.getItemInUse() != null) {
			int i = stack.getMaxItemUseDuration() - player.getItemInUseCount();
			if(i >= 18) return new ModelResourceLocation(Item.itemRegistry.getNameForObject(stack.getItem()) + "_2", "inventory");
			else if(i > 13) return new ModelResourceLocation(Item.itemRegistry.getNameForObject(stack.getItem()) + "_1", "inventory");
			else if(i > 0) return new ModelResourceLocation(Item.itemRegistry.getNameForObject(stack.getItem()) + "_0", "inventory");
		}
		return null;
	}

	@Override
	public int getItemEnchantability() {
		return 1;
	}
}