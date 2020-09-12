package net.journey.items.bows;

import net.journey.api.capability.EssenceStorage;
import net.journey.api.capability.JourneyPlayer;
import net.journey.client.ItemDescription;
import net.journey.common.capability.JCapabilityManager;
import net.journey.entity.projectile.arrow.EntityEssenceArrow;
import net.journey.init.items.JourneyItems;
import net.journey.items.ItemEssenceArrow;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.*;
import net.minecraft.stats.StatList;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.SlayerAPI;

import javax.annotation.Nullable;
import java.text.DecimalFormat;
import java.util.EnumSet;
import java.util.List;

public class ItemModBow extends ItemBow {

	public static final int DEFAULT_MAX_USE_DURATION = 72000;
	protected int maxUseDuration;
	public Item arrowItem;
	public int dur = 18;
	public String ability;
	protected float damage;
	protected int uses;
	protected int manaUse = 3;
	protected EnumSet<EntityEssenceArrow.BowEffects> effects;
	private final Class<? extends EntityArrow> arrowClass;


	public ItemModBow(float damage, int uses, EnumSet<EntityEssenceArrow.BowEffects> effects, int pullbackSpeed) {
		this.effects = effects;
		this.maxStackSize = 1;
		this.arrowItem = JourneyItems.essenceArrow;
		this.arrowClass = EntityEssenceArrow.class;
		this.damage = damage;
		this.uses = uses;
		this.maxUseDuration = pullbackSpeed;
		this.setMaxDamage(uses);
		this.setFull3D();
		addPropertyOverrides();
	}

	public float getScaledArrowVelocity(int charge) {
		float timeRatio = ((float) DEFAULT_MAX_USE_DURATION / (float) this.maxUseDuration);
		float f = ((float) charge / 20.0F) * timeRatio;
		f = (f * f + f * 2.0F) / 2.0F;

		if (f > 1.0F) {
			f = 1.0F;
		}

        return f;
    }

    public void addPropertyOverrides() {
        addPropertyOverride(new ResourceLocation("pull"), new IItemPropertyGetter() {
            @Override
            @SideOnly(Side.CLIENT)
            public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn) {
                if (entityIn == null) {
                    return 0.0F;
                } else {
                    ItemStack itemstack = entityIn.getActiveItemStack();
                    return !itemstack.isEmpty() && itemstack.getItem() instanceof ItemModBow ?
                            ((stack.getMaxItemUseDuration() - entityIn.getItemInUseCount()) / 20.0F)
                                    * (DEFAULT_MAX_USE_DURATION / stack.getMaxItemUseDuration()) :
                            0.0F;
                }
            }
        });
        addPropertyOverride(new ResourceLocation("pulling"), new IItemPropertyGetter() {
            @Override
            @SideOnly(Side.CLIENT)
            public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn) {
                return entityIn != null && entityIn.isHandActive() && entityIn.getActiveItemStack() == stack ? 1.0F :
                        0.0F;
            }
        });
    }

    @Override
    public ItemStack findAmmo(EntityPlayer player) {
        if (this.isArrow(player.getHeldItem(EnumHand.OFF_HAND))) {
            return player.getHeldItem(EnumHand.OFF_HAND);
            
        } else if (this.isArrow(player.getHeldItem(EnumHand.MAIN_HAND))) {
			return player.getHeldItem(EnumHand.MAIN_HAND);

		} else if (effects.contains(EntityEssenceArrow.BowEffects.ESSENCE_BOW)) {
			return ItemStack.EMPTY;

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

    @Override
    protected boolean isArrow(ItemStack stack) {
        return stack.getItem() instanceof ItemEssenceArrow;
    }
    
    public int setEssenceValue(int essence) {
    	return essence;
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft) {
        if (entityLiving instanceof EntityPlayer) {
			EntityPlayer entityplayer = (EntityPlayer) entityLiving;
			boolean flag = entityplayer.capabilities.isCreativeMode ||
					EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, stack) > 0 ||
					effects.contains(EntityEssenceArrow.BowEffects.ESSENCE_BOW);

			ItemStack itemstack = this.findAmmo(entityplayer);

			JourneyPlayer journeyPlayer = JCapabilityManager.asJourneyPlayer(entityplayer);
			EssenceStorage mana = journeyPlayer.getEssenceStorage();

			int i = this.maxUseDuration - timeLeft;
			i = ForgeEventFactory.onArrowLoose(stack, worldIn, entityplayer, i, !itemstack.isEmpty() || flag);
			if (i < 0) return;

			if (!itemstack.isEmpty() || flag) {
				if (itemstack.isEmpty()) {
					itemstack = new ItemStack(arrowItem);
				}

				float f = getScaledArrowVelocity(i);
				if ((double) f >= 0.1D) {

					if (!worldIn.isRemote) {
						EntityArrow entityarrow = null;
						EntityArrow entityarrow2 = null;
						try {
							entityarrow = new EntityEssenceArrow(worldIn, entityplayer, effects);
							entityarrow2 = new EntityEssenceArrow(worldIn, entityplayer, effects);
						} catch (Exception e) {
							e.printStackTrace();
						}

						/*
						 * shoot 2 arrows if bow is Wasteful Bow
						 */
						if (effects.contains(EntityEssenceArrow.BowEffects.DOUBLE_ARROW)) {
							entityarrow.shoot(entityplayer, entityplayer.rotationPitch, entityplayer.rotationYaw + 3.25F, 0.0F, f * 3.0F, 1.0F);
							entityarrow2.shoot(entityplayer, entityplayer.rotationPitch, entityplayer.rotationYaw - 3.25F, 0.0F, f * 3.0F, 1.0F);

							if (f == 1.0F) {
								entityarrow.setIsCritical(true);
								entityarrow2.setIsCritical(true);
							}

							int j = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, stack);

							int k = EnchantmentHelper.getEnchantmentLevel(Enchantments.PUNCH, stack);

							if (k > 0) {
								entityarrow.setKnockbackStrength(k);
								entityarrow2.setKnockbackStrength(k);
							}

							if (EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, stack) > 0) {
								entityarrow.setFire(100);
								entityarrow2.setFire(100);
							}

							entityarrow.setDamage(this.damage);
							entityarrow2.setDamage(this.damage);

							stack.damageItem(1, entityplayer);

							if (flag || entityplayer.capabilities.isCreativeMode
									&& (itemstack.getItem() == Items.SPECTRAL_ARROW
											|| itemstack.getItem() == Items.TIPPED_ARROW)) {
								entityarrow.pickupStatus = EntityArrow.PickupStatus.CREATIVE_ONLY;
								entityarrow2.pickupStatus = EntityArrow.PickupStatus.CREATIVE_ONLY;
							}
							worldIn.spawnEntity(entityarrow);
							worldIn.spawnEntity(entityarrow2);
						}
						/*
						 * shoot 1 arrow if bow isn't Wasteful Bow
						 */
						else {
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

							if (flag || entityplayer.capabilities.isCreativeMode
									&& (itemstack.getItem() == Items.SPECTRAL_ARROW
									|| itemstack.getItem() == Items.TIPPED_ARROW)) {
								entityarrow.pickupStatus = EntityArrow.PickupStatus.CREATIVE_ONLY;
							}

							if (effects.contains(EntityEssenceArrow.BowEffects.ESSENCE_BOW)) {
								if (mana.useEssence(this.manaUse)) {
									journeyPlayer.sendUpdates();
									worldIn.spawnEntity(entityarrow);
								}
							} else if (effects.contains(EntityEssenceArrow.BowEffects.ESSENCE_BOW)) {
								worldIn.spawnEntity(entityarrow);
							}
						}
					}

					worldIn.playSound(null, entityplayer.posX, entityplayer.posY, entityplayer.posZ,
							SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F,
							1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);

					if (!flag && !entityplayer.capabilities.isCreativeMode) {

						if (effects.contains(EntityEssenceArrow.BowEffects.DOUBLE_ARROW)) {
							itemstack.shrink(2);
						} else {
							itemstack.shrink(1);
						}
						if (itemstack.isEmpty()) {
							entityplayer.inventory.deleteStack(itemstack);
						}
					}

					entityplayer.addStat(StatList.getObjectUseStats(this));
				}
			}
		}
	}

	/* @Override
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityPlayer playerIn) {
		return stack;
	} */

    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return this.maxUseDuration;
    }

    @Override
    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.BOW;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> list, ITooltipFlag flagIn) {
		ItemDescription.addInformation(stack, list);
		list.add("Damage: " + SlayerAPI.Colour.GOLD + damage + " - " + SlayerAPI.Colour.GOLD + damage * 4);

		float maxUse = (float) DEFAULT_MAX_USE_DURATION / (float) this.maxUseDuration;
		DecimalFormat df = new DecimalFormat("#.##");
		list.add("Pull Back Speed: " + SlayerAPI.Colour.GOLD + df.format(maxUse));

		if (effects.contains(EntityEssenceArrow.BowEffects.DARKNESS_BOW)) {
			list.add(SlayerAPI.Colour.DARK_GRAY + "Ability: Withers foe");
		} else if (effects.contains(EntityEssenceArrow.BowEffects.FLAME_BOW)) {
			list.add(SlayerAPI.Colour.GOLD + "Ability: Sets foe ablaze");
		} else if (effects.contains(EntityEssenceArrow.BowEffects.POISON_BOW)) {
			list.add(SlayerAPI.Colour.GREEN + "Ability: Poisons foe");
		} else if (effects.contains(EntityEssenceArrow.BowEffects.FROZEN_BOW)) {
			list.add(SlayerAPI.Colour.BLUE + "Ability: Stuns foe");
		} else if (effects.contains(EntityEssenceArrow.BowEffects.DOUBLE_ARROW)) {
			list.add(SlayerAPI.Colour.BLUE + "Ability: Shoots 2 arrows");
		} else if (effects.contains(EntityEssenceArrow.BowEffects.ESSENCE_BOW)) {
			list.add(SlayerAPI.Colour.BLUE + "Ability: Consumes Essence instead of arrows");
		}
		list.add("Uses remaining: " + SlayerAPI.Colour.GRAY + uses);
	}

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ItemStack itemstack = playerIn.getHeldItem(handIn);
		boolean flag = !this.findAmmo(playerIn).isEmpty() || effects.contains(EntityEssenceArrow.BowEffects.ESSENCE_BOW);

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

    @Override
    public int getItemEnchantability() {
        return 1;
    }
}