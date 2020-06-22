package net.journey.items.bauble;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import net.journey.init.JourneyTabs;
import net.journey.items.base.JItem;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.SlayerAPI;

import java.util.List;
import java.util.UUID;

public class ItemIceAmulet extends JItem implements IBauble {

	protected static final UUID SPEED_MODIFIER = UUID.fromString("758787ea-2eda-4941-8f41-4e3efd1a95a7");
	protected static final UUID DAMAGE_MODIFIER = UUID.fromString("b0d292cf-74cd-4c6e-925f-eb81e78e3582");
	protected static final UUID ATTACK_MODIFIER = UUID.fromString("c0d86a67-553d-4c53-9f68-d8df3a891d38");

	protected static final AttributeModifier SPEED_MOD = new AttributeModifier(SPEED_MODIFIER, "Speed Modifier", 0.2F, 0);
	protected static final AttributeModifier DAMAGE_MOD = new AttributeModifier(DAMAGE_MODIFIER, "Damage Modifier", 1.0F, 0);
	protected static final AttributeModifier ATTACK_MOD = new AttributeModifier(ATTACK_MODIFIER, "Attack Speed Modifier", 0.5F, 0);

	public ItemIceAmulet(String name, String enName) {
		super(name, enName);
		setCreativeTab(JourneyTabs.UTIL);
		setMaxStackSize(1);
	}

	@Override
	public BaubleType getBaubleType(ItemStack itemStack) {
		return BaubleType.AMULET;
	}

	@Override
	public void onWornTick(ItemStack itemstack, EntityLivingBase player) {
		if (!player.world.isRemote) {
			IAttributeInstance attribMovementSpeed = player.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED);
			IAttributeInstance attribAttackDamage = player.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
			IAttributeInstance attribAttackSpeed = player.getEntityAttribute(SharedMonsterAttributes.ATTACK_SPEED);

			if (player.world.getBiome(player.getPosition()).getDefaultTemperature() <= 0.2F) {
				if (!attribMovementSpeed.hasModifier(SPEED_MOD) /*we apply all of them synchronously, so we can check only for one to exist*/) {
					attribMovementSpeed.applyModifier(SPEED_MOD);
					attribAttackDamage.applyModifier(DAMAGE_MOD);
					attribAttackSpeed.applyModifier(ATTACK_MOD);
				}
			} else {
				if (attribMovementSpeed.hasModifier(SPEED_MOD)) {
					attribMovementSpeed.removeModifier(SPEED_MOD);
					attribAttackDamage.removeModifier(DAMAGE_MOD);
					attribAttackSpeed.removeModifier(ATTACK_MOD);
				}
			}
		}
	}

	@Override
	public void onUnequipped(ItemStack itemstack, EntityLivingBase player) {
		if (!player.world.isRemote) {
			IAttributeInstance attribMovementSpeed = player.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED);
			IAttributeInstance attribAttackDamage = player.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
			IAttributeInstance attribAttackSpeed = player.getEntityAttribute(SharedMonsterAttributes.ATTACK_SPEED);

			if (attribMovementSpeed.hasModifier(SPEED_MOD)) {
				attribMovementSpeed.removeModifier(SPEED_MOD);
				attribAttackDamage.removeModifier(DAMAGE_MOD);
				attribAttackSpeed.removeModifier(ATTACK_MOD);
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack i, World worldIn, List<String> l, ITooltipFlag flagIn) {
		l.add(SlayerAPI.Colour.AQUA + "Grants various buffs while in cold biomes:");
		l.add(SlayerAPI.Colour.LIGHT_PURPLE + "Damage Boost");
		l.add(SlayerAPI.Colour.LIGHT_PURPLE + "Speed Boost");
		l.add(SlayerAPI.Colour.LIGHT_PURPLE + "Faster Attack Speed");
	}
}
