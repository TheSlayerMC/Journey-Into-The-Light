package net.journey.items;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.annotation.Nullable;

import net.journey.JITL;
import net.journey.JourneyItems;
import net.journey.JourneyTabs;
import net.journey.client.ItemDescription;
import net.journey.entity.projectile.EntityEssenceArrow;
import net.journey.util.LangHelper;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
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
	public String ability;

	protected EntityEssenceArrow.BowEffects effect;

	public ItemModBow(String name, String properName, int uses, int damage, String ability) {
		super(name, properName, JourneyTabs.weapons);
		this.maxStackSize = 1;
		this.arrowItem = JourneyItems.essenceArrow;
		this.arrowClass = EntityEssenceArrow.class;
		this.damage = damage;
		this.uses = uses;

		this.setMaxDamage(uses);
		this.setFull3D();
		this.ability = ability;
		addPropertyOverrides();

	}

	public ItemModBow(String name, String f, int uses, int damage, Item arrow, int duration, String ability, Class<? extends EntityArrow> arrowEnt) {
		super(name, f, JourneyTabs.weapons);
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
		addPropertyOverrides();
	}
	
	public ItemModBow(String name, String f, int uses, int damage, Item arrow, String ability, Class<? extends EntityArrow> arrowEnt) {
		super(name, f, JourneyTabs.weapons);
		this.maxStackSize = 1;
		this.ability = ability;
		this.arrowClass = arrowEnt;
		this.arrowItem = arrow;
		this.damage = damage;
		this.uses = uses;

		this.setMaxDamage(uses);
		this.setFull3D();
		addPropertyOverrides();
	}

	private void addPropertyOverrides() {
		this.addPropertyOverride(new ResourceLocation("pull"), new IItemPropertyGetter() {
			@SideOnly(Side.CLIENT)
			public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn) {
				if (entityIn == null) {
					return 0.0F;
				} else {
					return entityIn.getActiveItemStack().getItem() != Items.BOW ? 0.0F
							: (float) (stack.getMaxItemUseDuration() - entityIn.getItemInUseCount()) / 20.0F;
				}
			}
		});
		this.addPropertyOverride(new ResourceLocation("pulling"), new IItemPropertyGetter() {
			@SideOnly(Side.CLIENT)
			public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn) {
				return entityIn != null && entityIn.isHandActive() && entityIn.getActiveItemStack() == stack ? 1.0F
						: 0.0F;
			}
		});
	}

	private ItemStack findAmmo(EntityPlayer player) {
		if (this.isArrow(player.getHeldItem(EnumHand.OFF_HAND))) {
			return player.getHeldItem(EnumHand.OFF_HAND);
		} else if (this.isArrow(player.getHeldItem(EnumHand.MAIN_HAND))) {
			return player.getHeldItem(EnumHand.MAIN_HAND);
		} else {
			for (int i = 0; i < player.inventory.getSizeInventory(); ++i) {
				ItemStack itemstack = player.inventory.getStackInSlot(i);

				if (this.isArrow(itemstack)) {
					return itemstack;
				}
			}

			return ItemStack.EMPTY;
		}
	}

    protected boolean isArrow(ItemStack stack) {return stack.getItem() instanceof ItemEssenceArrow;}
    
	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft) {

		if (entityLiving instanceof EntityPlayer) {
			EntityPlayer entityplayer = (EntityPlayer)entityLiving;
			boolean flag = entityplayer.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, stack) > 0;
			ItemStack itemstack = this.findAmmo(entityplayer);

			int i = this.getMaxItemUseDuration(stack) - timeLeft;
			i = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(stack, worldIn, entityplayer, i, !itemstack.isEmpty() || flag);
			if (i < 0) return;

			/*
			EntityPlayer entityplayer = (EntityPlayer)entityLiving;
			int j1 = this.getMaxItemUseDuration(stack) - timeLeft;
			float f1 = (float) j1 / 20.0F;
			f1 = (f1 * f1 + f1 * 2.0F) / 3.0F;
			if ((double) f1 < 0.1D) return;
			if (f1 > 1.0F) f1 = 1.0F;
			*/

			if (!itemstack.isEmpty() || flag) {
				if (itemstack.isEmpty()) {
					itemstack = new ItemStack(arrowItem);
				}

				float f = getArrowVelocity(i);
				if ((double) f >= 0.1D) {

					if (!worldIn.isRemote) {
						EntityArrow entityarrow = null;
						try {
							entityarrow = new EntityEssenceArrow(worldIn, entityplayer, effect);
						} catch (Exception e) {
							e.printStackTrace();
						}

						entityarrow.shoot(entityplayer, entityplayer.rotationPitch, entityplayer.rotationYaw, 0.0F,
								f * 3.0F, 1.0F);

						if (f == 1.0F) {
							entityarrow.setIsCritical(true);
						}

						int j = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, stack);

						int k = EnchantmentHelper.getEnchantmentLevel(Enchantments.PUNCH, stack);

						if (k > 0) {
							entityarrow.setKnockbackStrength(k);
						}

						if (EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, stack) > 0) {
							entityarrow.setFire(100);
						}
						
						entityarrow.setDamage(this.damage);

						stack.damageItem(1, entityplayer);

						if (flag
								|| entityplayer.capabilities.isCreativeMode && (itemstack.getItem() == Items.SPECTRAL_ARROW
								|| itemstack.getItem() == Items.TIPPED_ARROW)) {
							entityarrow.pickupStatus = EntityArrow.PickupStatus.CREATIVE_ONLY;
						}

						worldIn.spawnEntity(entityarrow);
					}
				}

				worldIn.playSound((EntityPlayer) null, entityplayer.posX, entityplayer.posY, entityplayer.posZ,
						SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F,
						1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);

				if (!flag && !entityplayer.capabilities.isCreativeMode) {
					itemstack.shrink(1);

					if (itemstack.isEmpty()) {
						entityplayer.inventory.deleteStack(itemstack);
					}
				}

				entityplayer.addStat(StatList.getObjectUseStats(this));
			}
		}
	}

	public static float getArrowVelocity(int charge) {
		float f = (float) charge / 20.0F;
		f = (f * f + f * 2.0F) / 2.0F;

		if (f > 1.0F) {
			f = 1.0F;
		}

		return f;
	}

	/* @Override
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityPlayer playerIn) {
		return stack;
	} */

	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 72000;
	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.BOW;
	}
	
	@Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> list, ITooltipFlag flagIn) {
		ItemDescription.addInformation(stack, list);
		//list.add("Ammo: " + StatCollector.translateToLocal(arrowItem.getUnlocalizedName() + ".name"));
		list.add("Damage: " +SlayerAPI.Colour.GOLD + damage + " - " + SlayerAPI.Colour.GOLD + damage*4);
		list.add("Ability: " + SlayerAPI.Colour.GOLD + ability);
		list.add("Uses remaining: " + SlayerAPI.Colour.GRAY + uses);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ItemStack itemstack = playerIn.getHeldItem(handIn);
		boolean flag = !this.findAmmo(playerIn).isEmpty();

		ActionResult<ItemStack> ret = net.minecraftforge.event.ForgeEventFactory.onArrowNock(itemstack, worldIn,
				playerIn, handIn, flag);
		if (ret != null)
			return ret;

		if (!playerIn.capabilities.isCreativeMode && !flag) {
			return flag ? new ActionResult(EnumActionResult.PASS, itemstack)
					: new ActionResult(EnumActionResult.FAIL, itemstack);
		} else {
			playerIn.setActiveHand(handIn);
			return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
		}
	}

	/*@Override
	@SideOnly(Side.CLIENT)
	public ModelResourceLocation getModel(ItemStack stack, EntityPlayer player, int useRemaining) {
		if(stack.getItem() instanceof ItemModBow && player.getItemInUse() != null) {
			int i = stack.getMaxItemUseDuration() - player.getItemInUseCount();
			if(i >= 18) return new ModelResourceLocation(Item.REGISTRY.getNameForObject(stack.getItem()) + "_2", "inventory");
			else if(i > 13) return new ModelResourceLocation(Item.REGISTRY.getNameForObject(stack.getItem()) + "_1", "inventory");
			else if(i > 0) return new ModelResourceLocation(Item.REGISTRY.getNameForObject(stack.getItem()) + "_0", "inventory");
		}
		return null;
	} */

	@Override
	public int getItemEnchantability() {
		return 1;
	}
	
	@Override
	public void registerItemModel() {
		JITL.proxy.registerItemRenderer(this, 0, name);
	}
}