package net.slayer.api.item;

import net.journey.JITL;
import net.journey.client.ItemDescription;
import net.journey.client.render.particles.EntityFloroWaterFX;
import net.journey.client.render.particles.EntityHellstoneFX;
import net.journey.client.render.particles.EntityModSnowFX;
import net.journey.client.render.particles.EntityPoisionFX;
import net.journey.enums.EnumParticlesClasses;
import net.journey.enums.EnumSwordType;
import net.journey.util.JourneyToolMaterial;
import net.journey.util.LangHelper;
import net.journey.util.PotionEffects;
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

import java.util.List;
import java.util.Random;

public class ItemModSword extends ItemSword {

	protected JourneyToolMaterial mat;
	private final EnumSwordType type;
	private float health;

	public ItemModSword(EnumSwordType type, JourneyToolMaterial toolMaterial, float heart) {
		super(toolMaterial.getToolMaterial());
		this.type = type;
		this.health = heart;
		mat = toolMaterial;
	}

	public ItemModSword(EnumSwordType type, JourneyToolMaterial toolMaterial) {
		super(toolMaterial.getToolMaterial());
		this.type = type;
		mat = toolMaterial;
	}

	public ItemModSword(JourneyToolMaterial toolMaterial) {
		this(null, toolMaterial, 0);
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase player) {
		float hearts = player.getHealth();
		if (type != null) {
			switch (type) {
				case FREEZE:
					target.addPotionEffect(new PotionEffect(PotionEffects.setPotionEffect(PotionEffects.moveSlow, 100, 5)));
					target.extinguish();
				break;
			case POISON:
				target.addPotionEffect(new PotionEffect(PotionEffects.setPotionEffect(PotionEffects.poison, 100, 2)));
				break;
			case FIRE:
				target.setFire(10);
				break;
				case FIRE_HEALTH:
					target.setFire(10);
					if ((hearts >= 1F) & (hearts < 20F)) {
						player.setHealth(hearts + this.health);
					} else if (itemRand.nextInt(4) == 0) {
						player.setHealth(hearts - 10);
					}
					player.addPotionEffect(new PotionEffect(PotionEffects.setPotionEffect(PotionEffects.moveSlow, 100, 2)));
					break;
				case FIRE_WITHER:
					target.setFire(10);
					target.addPotionEffect(new PotionEffect(PotionEffects.setPotionEffect(PotionEffects.wither, 100, 20)));
					break;
				case HEALTH:
					if (hearts >= 1F) {
						player.setHealth(hearts + health);
					} else if (itemRand.nextInt(2) == 0) {
						player.addPotionEffect(new PotionEffect(PotionEffects.setPotionEffect(PotionEffects.moveSlow, 100, 2)));
						player.addPotionEffect(new PotionEffect(PotionEffects.setPotionEffect(PotionEffects.blindness, 100, 2)));
					} else if (itemRand.nextInt(4) == 0) {
						player.setHealth(hearts - 4);
					}
					break;
				case NIGHTVISION_HEALTH:
					player.addPotionEffect(new PotionEffect(PotionEffects.setPotionEffect(PotionEffects.nightVision, 1000, 20)));
					player.addPotionEffect(new PotionEffect(PotionEffects.setPotionEffect(PotionEffects.regeneration, 10, 200)));
					break;
				case POISON_HEALTH:
					target.addPotionEffect(new PotionEffect(PotionEffects.setPotionEffect(PotionEffects.poison, 100, 2)));
					if (hearts >= 1F) {
						player.setHealth(hearts + this.health);
					} else if (itemRand.nextInt(2) == 0) {
						player.addPotionEffect(new PotionEffect(PotionEffects.setPotionEffect(PotionEffects.poison, 100, 2)));
					}
					break;
				case REGEN:
					player.addPotionEffect(new PotionEffect(PotionEffects.setPotionEffect(PotionEffects.regeneration, 100, 1)));
					break;
				case STUN:
					target.addPotionEffect(new PotionEffect(PotionEffects.setPotionEffect(PotionEffects.wither, 70, 1)));
					target.addPotionEffect(new PotionEffect(PotionEffects.setPotionEffect(PotionEffects.harm, 50, 1)));
					break;
				case STUN_WITHER:
					target.addPotionEffect(new PotionEffect(PotionEffects.setPotionEffect(PotionEffects.moveSlow, 1000, 200)));
					target.addPotionEffect(new PotionEffect(PotionEffects.setPotionEffect(PotionEffects.wither, 100, 2)));
					break;
				case WITHER:
					target.addPotionEffect(new PotionEffect(PotionEffects.setPotionEffect(PotionEffects.wither, 100, 5)));
					break;
				case NIGHTVISION:
					player.addPotionEffect(new PotionEffect(PotionEffects.setPotionEffect(PotionEffects.nightVision, 10, 2)));
					break;
				default:
					break;
			}
			addParticles(target);
		}
		return super.hitEntity(stack, target, player);
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
			JITL.proxy.spawnParticle(EnumParticlesClasses.MOD_LAVA, hit.world, hit.posX + r.nextFloat() - 0.5F, hit.posY + 0.5D + r.nextFloat(), hit.posZ + r.nextFloat() - 0.5F, false);
			break;
		case FIRE_HEALTH:
			p = new EntityFloroWaterFX(hit.world, hit.posX + r.nextFloat() - 0.5F, hit.posY + 0.5D + r.nextFloat(), hit.posZ + r.nextFloat() - 0.5F, 0.0D, 0.0D, 0.0D);
			break;
		case FIRE_WITHER: 
			p = new EntityFloroWaterFX(hit.world, hit.posX + r.nextFloat() - 0.5F, hit.posY + 0.5D + r.nextFloat(), hit.posZ + r.nextFloat() - 0.5F, 0.0D, 0.0D, 0.0D);
			break;
		case HEALTH:
			p = new EntityHellstoneFX(hit.world, hit.posX + r.nextFloat() - 0.5F, hit.posY + 0.5D + r.nextFloat(), hit.posZ + r.nextFloat() - 0.5F, 0.0D, 0.0D, 0.0D);
			break;
		case NIGHTVISION_HEALTH:
			p = new EntityFloroWaterFX(hit.world, hit.posX + r.nextFloat() - 0.5F, hit.posY + 0.5D + r.nextFloat(), hit.posZ + r.nextFloat() - 0.5F, 0.0D, 0.0D, 0.0D);
			break;
		case POISON_HEALTH:
			p = new EntityFloroWaterFX(hit.world, hit.posX + r.nextFloat() - 0.5F, hit.posY + 0.5D + r.nextFloat(), hit.posZ + r.nextFloat() - 0.5F, 0.0D, 0.0D, 0.0D);
			break;
		case REGEN:
			p = new EntityHellstoneFX(hit.world, hit.posX + r.nextFloat() - 0.5F, hit.posY + 0.5D + r.nextFloat(), hit.posZ + r.nextFloat() - 0.5F, 0.0D, 0.0D, 0.0D);
			break;
		case STUN:
			p = new EntityFloroWaterFX(hit.world, hit.posX + r.nextFloat() - 0.5F, hit.posY + 0.5D + r.nextFloat(), hit.posZ + r.nextFloat() - 0.5F, 0.0D, 0.0D, 0.0D);
			break;
		case STUN_WITHER:
			p = new EntityFloroWaterFX(hit.world, hit.posX + r.nextFloat() - 0.5F, hit.posY + 0.5D + r.nextFloat(), hit.posZ + r.nextFloat() - 0.5F, 0.0D, 0.0D, 0.0D);
			break;
		case WITHER:
			p = new EntityFloroWaterFX(hit.world, hit.posX + r.nextFloat() - 0.5F, hit.posY + 0.5D + r.nextFloat(), hit.posZ + r.nextFloat() - 0.5F, 0.0D, 0.0D, 0.0D);
			break;
		case NIGHTVISION:
			p = new EntityFloroWaterFX(hit.world, hit.posX + r.nextFloat() - 0.5F, hit.posY + 0.5D + r.nextFloat(), hit.posZ + r.nextFloat() - 0.5F, 0.0D, 0.0D, 0.0D);
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
		if(canRepair) return mat.getRepairItem() == i1.getItem() || super.getIsRepairable(i, i1);
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
				infoList.add(SlayerAPI.Colour.DARK_RED + "On hit: Burns for 10 seconds");
				break;
			case FIRE_HEALTH:
				infoList.add(SlayerAPI.Colour.DARK_GREEN + "On hit: Sets enemies ablaze and heals player " + health / 2 + " heart(s)");
				infoList.add(SlayerAPI.Colour.RED + "Drawback: slows the user on hit");
				infoList.add(SlayerAPI.Colour.RED + "Random chance to steal 5 full hearts from the user on hit");
				break;
			case FIRE_WITHER: 
				infoList.add(SlayerAPI.Colour.DARK_GREEN + "On hit: Withers and sets enemies ablaze");
				break;
			case HEALTH:
				infoList.add(SlayerAPI.Colour.RED + "On hit: Heals player " + health / 2 + " heart(s)");
				infoList.add(SlayerAPI.Colour.RED + "Drawback: Random chance to slow and blind the user on hit");
				infoList.add(SlayerAPI.Colour.RED + "Random chance to steal 2 full hearts from the user on hit");
				break;
			case NIGHTVISION_HEALTH:
				infoList.add(SlayerAPI.Colour.DARK_GREEN + "On hit: Poisons and Withers enemies");
				break;
			case POISON_HEALTH:
				infoList.add(SlayerAPI.Colour.DARK_GREEN + "On hit: Poisons enemies and heals player " + health / 2 + " heart(s)");
				infoList.add(SlayerAPI.Colour.RED + "Drawback: Random chance to poison the user on hit");
				break;
			case REGEN:
				infoList.add(SlayerAPI.Colour.RED + "On hit: Grants player regeneration");
				break;
			case STUN:
				infoList.add(SlayerAPI.Colour.DARK_GREEN + "On hit: Harms and stuns ememies");
				break;
			case STUN_WITHER:
				infoList.add(SlayerAPI.Colour.DARK_GREEN + "On hit: Withers and stuns ememies");
				break;
			case WITHER:
				infoList.add(SlayerAPI.Colour.DARK_GRAY + LangHelper.setWitherSword(6));
				break;
			case NIGHTVISION:
				infoList.add(SlayerAPI.Colour.DARK_GREEN + "On hit: Grants the player Nightvision");
				break;
			default: break;
			}
		}
		if (item.getMaxDamage() != -1)
			infoList.add(item.getMaxDamage() - item.getItemDamage() + " " + LangHelper.getUsesRemaining());
		else infoList.add(SlayerAPI.Colour.GREEN + LangHelper.getInfiniteUses());
	}
}