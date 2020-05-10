package net.slayer.api.item;

import java.util.List;
import java.util.Random;

import net.journey.JITL;
import net.journey.client.ItemDescription;
import net.journey.client.render.particles.EntityModSnowFX;
import net.journey.client.render.particles.EntityPoisionFX;
import net.journey.enums.EnumSwordType;
import net.journey.init.JourneyTabs;
import net.journey.init.items.JourneyItems;
import net.journey.util.JourneyToolMaterial;
import net.journey.util.LangHelper;
import net.journey.util.PotionEffects;
import net.journey.util.gen.lang.LangGeneratorFacade;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.SlayerAPI;

public class ItemModSword extends ItemSword {

	protected JourneyToolMaterial mat;
	private EnumSwordType type;

	public ItemModSword(String name, String f, EnumSwordType type, JourneyToolMaterial toolMaterial) {
		super(toolMaterial.getToolMaterial());
		this.type = type;
		setTranslationKey(name);
		mat = toolMaterial;
		setCreativeTab(JourneyTabs.WEAPONS);
		JourneyItems.itemNames.add(SlayerAPI.PREFIX + name);
		JourneyItems.items.add(this);
		setRegistryName(JITL.MOD_ID, name);
		LangGeneratorFacade.addItemEntry(this, f);
	}

	public ItemModSword(String name, String f, JourneyToolMaterial toolMaterial) {
		this(name, f, null, toolMaterial);
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
		if(type != null) {
			switch(type) {
			case FREEZE:
				target.addPotionEffect(new PotionEffect(PotionEffects.setPotionEffect(PotionEffects.moveSlow, 100, 5)));
				target.extinguish();
				break;
			case POISON:
				target.addPotionEffect(new PotionEffect(PotionEffects.setPotionEffect(PotionEffects.poison, 100, 2)));
				break;
			case FIRE:
				break;
			case FIRE_HEALTH:

				break;
			case FIRE_WITHER: 

				break;
			case HEALTH:

				break;
			case NIGHTVISION_HEALTH:

				break;
			case POISON_HEALTH:

				break;
			case REGEN:

				break;
			case STUN:

				break;
			case STUN_WITHER:

				break;
			case WITHER:

				break;
			default: break;
			}
			addParticles(target);
		}
		return super.hitEntity(stack, target, attacker);
	}

	@SideOnly(Side.CLIENT)
	public void addParticles(EntityLivingBase hit) {
		Random r = new Random();
		Particle p = null;
		switch(type) {
		case FREEZE:
			p = new EntityModSnowFX(hit.world, hit.posX + r.nextFloat() - 0.5F, hit.posY + 0.5D + r.nextFloat(), hit.posZ + r.nextFloat() - 0.5F, 0.0D, 0.0D, 0.0D);
			break;
		case POISON:
			p = new EntityPoisionFX(hit.world, hit.posX + r.nextFloat() - 0.5F, hit.posY + 0.5D + r.nextFloat(), hit.posZ + r.nextFloat() - 0.5F, 0.0D, 0.0D, 0.0D);
			break;
		case FIRE:
			break;
		case FIRE_HEALTH:

			break;
		case FIRE_WITHER: 

			break;
		case HEALTH:

			break;
		case NIGHTVISION_HEALTH:

			break;
		case POISON_HEALTH:

			break;
		case REGEN:

			break;
		case STUN:

			break;
		case STUN_WITHER:

			break;
		case WITHER:

			break;
		default:
			break;
		}
		if(p != null) {
			for (int i = 0; i < 50; i++) {
				FMLClientHandler.instance().getClient().effectRenderer.addEffect(p);
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
	public void addInformation(ItemStack item, World player, List<String> infoList, ITooltipFlag par4) {
		ItemDescription.addInformation(item, infoList);
		if(type != null) {
			switch(type) {
			case FREEZE:
				infoList.add(SlayerAPI.Colour.DARK_AQUA + "On hit: Freezes the target for 6 seconds");
				break;
			case POISON:
				infoList.add(SlayerAPI.Colour.DARK_GREEN + "On hit: Poisions for 6 seconds");
				break;
			case FIRE:
				break;
			case FIRE_HEALTH:

				break;
			case FIRE_WITHER: 

				break;
			case HEALTH:

				break;
			case NIGHTVISION_HEALTH:

				break;
			case POISON_HEALTH:

				break;
			case REGEN:

				break;
			case STUN:

				break;
			case STUN_WITHER:

				break;
			case WITHER:

				break;
			default: break;
			}
		}
		if (item.getMaxDamage() != -1)
			infoList.add(item.getMaxDamage() - item.getItemDamage() + " " + LangHelper.getUsesRemaining());
		else infoList.add(SlayerAPI.Colour.GREEN + LangHelper.getInfiniteUses());
	}
}