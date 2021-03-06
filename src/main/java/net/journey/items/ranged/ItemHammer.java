package net.journey.items.ranged;

import net.journey.JITL;
import net.journey.api.block.FeatureProvider;
import net.journey.api.capability.EssenceStorage;
import net.journey.api.capability.JourneyPlayer;
import net.journey.api.item.IUsesEssence;
import net.journey.blocks.util.Features;
import net.journey.common.capability.JCapabilityManager;
import net.journey.entity.projectile.EntityDamagingProjectile;
import net.journey.init.JourneySounds;
import net.journey.util.JourneyToolMaterial;
import net.journey.util.LangHelper;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.SlayerAPI;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

public class ItemHammer extends ItemSword implements IUsesEssence, FeatureProvider {

	protected final int usage;
	protected int damage;
	protected boolean essence, unbreakable;
	protected Class<? extends EntityDamagingProjectile> projectile;
	protected JourneyToolMaterial mat;

	private boolean canUseChargedAttack = false;

	public ItemHammer(JourneyToolMaterial toolMaterial, boolean durability, Class<? extends EntityDamagingProjectile> projectile, int dam, int magic, int uses) {
		super(toolMaterial.getToolMaterial());
		this.projectile = projectile;
		damage = dam;
		usage = magic;
		setMaxDamage(uses);
		setMaxStackSize(1);
		mat = toolMaterial;

		this.addPropertyOverride(new ResourceLocation("pull"), new IItemPropertyGetter() {
			@SideOnly(Side.CLIENT)
			public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn) {
				if (entityIn == null) {
					return 0.0F;
				} else {
					return !(entityIn.getActiveItemStack().getItem() instanceof ItemHammer) ? 0.0F : (float) (stack.getMaxItemUseDuration() - entityIn.getItemInUseCount()) / 20.0F;
				}
			}
		});
		this.addPropertyOverride(new ResourceLocation("pulling"), new IItemPropertyGetter() {
			@SideOnly(Side.CLIENT)
			public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn) {
				return entityIn != null && entityIn.isHandActive() && entityIn.isSneaking() && entityIn.getActiveItemStack() == stack ? 1.0F : 0.0F;
			}
		});
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
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand handIn) {
		ItemStack stack = player.getHeldItem(handIn);
		EssenceStorage mana = JCapabilityManager.asJourneyPlayer(player).getEssenceStorage();
		if (mana.useEssence(usage) && !player.isSneaking()) {
			JourneySounds.playSound(JourneySounds.HAMMER, world, player);
			if (!world.isRemote) {
				if (!unbreakable)
					stack.damageItem(1, player);
				try {
					EntityDamagingProjectile shoot = projectile.getConstructor(World.class, EntityLivingBase.class, float.class).newInstance(world, player, damage);
					shoot.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, 3.5F, 0.2F);
					world.spawnEntity(shoot);
					stack.damageItem(1, player);
					return new ActionResult<>(EnumActionResult.SUCCESS, stack);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		if (!world.isRemote) {
			if (player.isSneaking()) {
				canUseChargedAttack = true;
			}
		}
		return new ActionResult<>(EnumActionResult.FAIL, stack);
	}

	/*TODO:
	 * > Make actual power attack
	 * > Make sure essence is at max value
	 * > When essence hits zero, use power attack
	 */
	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		EntityPlayer player = (EntityPlayer) entityIn;
		JourneyPlayer journeyPlayer = JCapabilityManager.asJourneyPlayer(player);
		EssenceStorage mana = journeyPlayer.getEssenceStorage();
		if (player.ticksExisted % 10 == 0) {
			if (player.isSneaking() && canUseChargedAttack) {
				if (!worldIn.isRemote) {
					if (mana.useEssence(1)) {
						EntityBlaze blaze = new EntityBlaze(worldIn);
						blaze.setPosition(player.posX, player.posY, player.posZ);
						worldIn.spawnEntity(blaze);
						JITL.LOGGER.info("GUMBUBMB");
						canUseChargedAttack = false;
						journeyPlayer.sendUpdates();
					}
				}
			}
		}
	}

	@Override
	public boolean isEnchantable(ItemStack stack) {
		return true;
	}

	@Override
	public boolean getIsRepairable(ItemStack i, ItemStack i1) {
		boolean canRepair = mat.getRepairItem() != null;
		if (canRepair) return mat.getRepairItem() == i1.getItem() || super.getIsRepairable(i, i1);
		return super.getIsRepairable(i, i1);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack item, World worldIn, List<String> l, ITooltipFlag flagIn) {
		if (item.getMaxDamage() != -1)
			l.add(item.getMaxDamage() - item.getItemDamage() + " " + LangHelper.getUsesRemaining());
		else l.add(SlayerAPI.Colour.GREEN + LangHelper.getInfiniteUses());
		LangHelper.useEssence(usage);
		l.add(SlayerAPI.Colour.DARK_GREEN + "+" + LangHelper.rangedDamage(damage));
	}

	@Override
	public @NotNull Features getExtraFeatures() {
		return Features.Builder.create()
				.setCustomItemModelLocation(JITL.rl("hammer/" + getRegistryName().getPath()))
				.build();
	}
}