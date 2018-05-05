package net.journey.items;

import java.util.List;

import net.journey.JourneyItems;
import net.journey.JourneyTabs;
import net.journey.client.ItemDescription;
import net.journey.util.LangHelper;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.SlayerAPI;
import net.slayer.api.item.ItemMod;

public class ItemModBow extends ItemMod {

	private Class<? extends EntityArrow> arrowClass;
	public Item arrowItem;
	public int dur = 18;
	protected int damage;
	protected int uses;
	//protected String damageString;
	public String ability;

	public ItemModBow(String name, String f, int uses, int damage, /**String damageString,*/ Item arrow, int duration, String ability, Class<? extends EntityArrow> arrowEnt) {
		super(name, f, JourneyTabs.bows);
		this.maxStackSize = 1;
		this.dur = duration;
		this.arrowClass = arrowEnt;
		this.arrowItem = arrow;
		this.damage = damage;
		this.uses = uses;
		//this.damageString = damageString;
		this.setMaxDamage(uses);
		this.setFull3D();
		this.ability = ability;
	}
	
	public ItemModBow(String name, String f, int uses, int damage, /**String damageString*/ Item arrow, String ability, Class<? extends EntityArrow> arrowEnt) {
		super(name, f, JourneyTabs.bows);
		this.maxStackSize = 1;
		this.ability = ability;
		this.arrowClass = arrowEnt;
		this.arrowItem = arrow;
		this.damage = damage;
		this.uses = uses;
		//this.damageString = damageString;
		this.setMaxDamage(uses);
		this.setFull3D();
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityPlayer playerIn, int timeLeft) {
		int j = this.getMaxItemUseDuration(stack) - timeLeft;
		net.minecraftforge.event.entity.player.ArrowLooseEvent event = new net.minecraftforge.event.entity.player.ArrowLooseEvent(playerIn, stack, j);
		if (net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(event)) return;
		j = event.charge;

		boolean flag = playerIn.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, stack) > 0;

		if (flag || playerIn.inventory.hasItem(arrowItem)) {
			float f = j / 20.0F;
			f = (f * f + f * 2.0F) / 3.0F;
			if(f < 0.1D) return;
			if(f > 1.0F) f = 1.0F;

			EntityArrow entityarrow = null;
			try {
				entityarrow = arrowClass.getConstructor(World.class, EntityLivingBase.class, float.class).newInstance(worldIn, playerIn, f * 2.0F);
			} catch(Exception e) {
				e.printStackTrace();
			}
			if(entityarrow != null);
			entityarrow.setDamage(this.damage);
			if(f == 1.0F) entityarrow.setIsCritical(true);
			int k = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, stack);
			if(k > 0) entityarrow.setDamage(entityarrow.getDamage() + k * 0.5D + 0.5D);
			int l = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, stack);
			if (l > 0) entityarrow.setKnockbackStrength(l);
			if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, stack) > 0) entityarrow.setFire(100);

			stack.damageItem(1, playerIn);
			worldIn.playSoundAtEntity(playerIn, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
			if(flag) entityarrow.canBePickedUp = 2;
			else playerIn.inventory.consumeInventoryItem(arrowItem);
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
		list.add("Ammo: " + StatCollector.translateToLocal(arrowItem.getUnlocalizedName() + ".name"));
		list.add("Damage: " +SlayerAPI.Colour.GOLD + damage + " - " + SlayerAPI.Colour.GOLD + damage*4);
		list.add("Ability: " + SlayerAPI.Colour.GOLD + ability);
		list.add("Uses remaining: " + SlayerAPI.Colour.GRAY + uses);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn) {
		net.minecraftforge.event.entity.player.ArrowNockEvent event = new net.minecraftforge.event.entity.player.ArrowNockEvent(playerIn, itemStackIn);
		if(net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(event)) return event.result;
		if(playerIn.capabilities.isCreativeMode || playerIn.inventory.hasItem(arrowItem)) playerIn.setItemInUse(itemStackIn, this.getMaxItemUseDuration(itemStackIn));
		return itemStackIn;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public ModelResourceLocation getModel(ItemStack stack, EntityPlayer player, int useRemaining) {
		if(stack.getItem() instanceof ItemModBow && player.getItemInUse() != null) {
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