package net.journey.event;

import net.journey.JourneyItems;
import net.journey.util.PotionEffects;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;

public class ArmorAbilityEvent{


	private JourneyItems item = new JourneyItems();
	private Item boots = null, body = null, legs = null, helmet = null;

	@SubscribeEvent
	public void playerTick(PlayerTickEvent event) {
		ItemStack stackBoots = event.player.inventory.armorItemInSlot(0);
		ItemStack stackLegs = event.player.inventory.armorItemInSlot(1);
		ItemStack stackBody = event.player.inventory.armorItemInSlot(2);
		ItemStack stackHelmet = event.player.inventory.armorItemInSlot(3);
		if(stackBoots != null) boots = stackBoots.getItem();
		else boots = null;
		if(stackBody != null) body = stackBody.getItem();
		else body = null;
		if(stackLegs != null) legs = stackLegs.getItem();
		else legs = null;
		if(stackHelmet != null) helmet = stackHelmet.getItem();
		else helmet = null;

		if(helmet == 
				JourneyItems.hellstoneHelmet && body == 
				JourneyItems.hellstoneChest && legs ==
				JourneyItems.hellstoneLegs && boots == 
				JourneyItems.hellstoneBoots) {
			event.player.addPotionEffect(new PotionEffect(PotionEffects.setPotionEffect(PotionEffects.fireResistance, 10, 1)));
		}

		else if(helmet == 
				JourneyItems.shadiumHelmet && body == 
				JourneyItems.shadiumChest && legs ==
				JourneyItems.shadiumLegs && boots == 
				JourneyItems.shadiumBoots){
			if(event.player.isInWater()) event.player.setAir(300);
		}

		else if(helmet == 
				JourneyItems.sapphireHelmet && body == 
				JourneyItems.sapphireChest && legs == 
				JourneyItems.sapphireLegs && boots == 
				JourneyItems.sapphireBoots){
			if(event.player.isInWater()) event.player.setAir(300);
		}

		else if(helmet == 
				JourneyItems.twilightHelmet && body == 
				JourneyItems.twilightChest && legs == 
				JourneyItems.twilightLegs && boots == 
				JourneyItems.twilightBoots){
			event.player.addPotionEffect(new PotionEffect(PotionEffects.setPotionEffect(PotionEffects.nightVision, 240, 55)));
			event.player.addPotionEffect(new PotionEffect(PotionEffects.setPotionEffect(PotionEffects.fireResistance, 240, 55)));
		}

		else if(helmet == 
				JourneyItems.leapersHelmet && body == 
				JourneyItems.leapersChest && legs == 
				JourneyItems.leapersLegs && boots == 
				JourneyItems.leapersBoots){
			event.player.addPotionEffect(new PotionEffect(PotionEffects.setPotionEffect(PotionEffects.jump, 10, 1)));

		}

		else if(helmet == 
				JourneyItems.snakeskinHelmet && body == 
				JourneyItems.snakeskinChest && legs == 
				JourneyItems.snakeskinLegs && boots ==
				JourneyItems.snakeskinBoots){
			event.player.addPotionEffect(new PotionEffect(PotionEffects.setPotionEffect(PotionEffects.moveSpeed, 10, 3)));
			if(event.player.isInLava()) event.player.addPotionEffect(new PotionEffect(PotionEffects.setPotionEffect(PotionEffects.fireResistance, 100, 3)));
			if(event.player.isInLava()) event.player.addPotionEffect(new PotionEffect(PotionEffects.setPotionEffect(PotionEffects.damageBoost, 10, 1)));
		}

		else if(helmet == 
				JourneyItems.blazehornHelmet && body == 
				JourneyItems.blazehornChest && legs == 
				JourneyItems.blazehornLegs && boots ==
				JourneyItems.blazehornBoots){
			if(event.player.isInLava()) event.player.addPotionEffect(new PotionEffect(PotionEffects.setPotionEffect(PotionEffects.fireResistance, 100, 10)));
		}

		else if(helmet == 
				JourneyItems.crystalFlakeHelmet && body == 
				JourneyItems.crystalFlakeChest && legs == 
				JourneyItems.crystalFlakeLegs && boots ==
				JourneyItems.crystalFlakeBoots){
			event.player.addPotionEffect(new PotionEffect(PotionEffects.setPotionEffect(PotionEffects.waterBreathing, 10, 1)));
		}

		else if(helmet == 
				JourneyItems.charskullHelmet && body == 
				JourneyItems.charskullChest && legs == 
				JourneyItems.charskullLegs && boots == 
				JourneyItems.charskullBoots){
			event.player.addPotionEffect(new PotionEffect(PotionEffects.setPotionEffect(PotionEffects.fireResistance, 10, 1)));
			event.player.addPotionEffect(new PotionEffect(PotionEffects.setPotionEffect(PotionEffects.damageBoost, 10, 2)));
		}

		else if(helmet == 
				JourneyItems.golditeHelmet && body == 
				JourneyItems.golditeChest && legs == 
				JourneyItems.golditeLegs && boots == 
				JourneyItems.golditeBoots){
			event.player.fallDistance=0;
			event.player.addPotionEffect(new PotionEffect(PotionEffects.setPotionEffect(PotionEffects.moveSpeed, 10, 1)));
		}

		else if(helmet
				== JourneyItems.treehuggersHelmet && body ==
				JourneyItems.treehuggersChest && legs ==
				JourneyItems.treehuggersLegs && boots ==
				JourneyItems.treehuggersBoots) {
			event.player.removePotionEffect(PotionEffects.getPotionFromID(19));
			event.player.removePotionEffect(PotionEffects.getPotionFromID(20));
		}

		else if(helmet == 
				JourneyItems.flairiumHelmet && body == 
				JourneyItems.flairiumChest && legs == 
				JourneyItems.flairiumLegs && boots == 
				JourneyItems.flairiumBoots) {
			event.player.addPotionEffect(new PotionEffect(PotionEffects.setPotionEffect(PotionEffects.fireResistance, 10, 1)));
		}

		else if(helmet == 
				JourneyItems.darklyHelmet && body == 
				JourneyItems.darklyChest && legs == 
				JourneyItems.darklyLegs && boots == 
				JourneyItems.darklyBoots) {
			event.player.addPotionEffect(new PotionEffect(PotionEffects.setPotionEffect(PotionEffects.nightVision, 240, 55)));

		}

		else if(helmet == 
				JourneyItems.depthsHelmet && body == 
				JourneyItems.depthsChest && legs == 
				JourneyItems.depthsLegs && boots == 
				JourneyItems.depthsBoots) {
			event.player.addPotionEffect(new PotionEffect(PotionEffects.setPotionEffect(PotionEffects.nightVision, 240, 55)));
			event.player.addPotionEffect(new PotionEffect(PotionEffects.setPotionEffect(PotionEffects.waterBreathing, 10, 1)));
		}

		else if(helmet == 
				JourneyItems.enlightenerHelmet && body == 
				JourneyItems.enlightenerChest && legs == 
				JourneyItems.enlightenerLegs && boots == 
				JourneyItems.enlightenerBoots) {
			event.player.addPotionEffect(new PotionEffect(PotionEffects.setPotionEffect(PotionEffects.jump, 10, 3)));
			event.player.addPotionEffect(new PotionEffect(PotionEffects.setPotionEffect(PotionEffects.regeneration, 10, 10)));
		}

		else if(helmet == 
				JourneyItems.fireboundHelmet && body == 
				JourneyItems.fireboundChest && legs == 
				JourneyItems.fireboundLegs && boots == 
				JourneyItems.fireboundBoots) {
			event.player.addPotionEffect(new PotionEffect(PotionEffects.setPotionEffect(PotionEffects.jump, 10, 3)));
			event.player.addPotionEffect(new PotionEffect(PotionEffects.setPotionEffect(PotionEffects.fireResistance, 10, 1)));
		}

		else if(helmet == 
				JourneyItems.frostbittenHelmet && body == 
				JourneyItems.frostbittenChest && legs == 
				JourneyItems.frostbittenLegs && boots == 
				JourneyItems.frostbittenBoots) {
			event.player.addPotionEffect(new PotionEffect(PotionEffects.setPotionEffect(PotionEffects.damageBoost, 10, 1)));
		}

		else if(helmet == 
				JourneyItems.hollowHelmet && body == 
				JourneyItems.hollowChest && legs == 
				JourneyItems.hollowLegs && boots == 
				JourneyItems.hollowBoots) {
			event.player.addPotionEffect(new PotionEffect(PotionEffects.setPotionEffect(PotionEffects.nightVision, 240, 55)));
		}

		else if(helmet == 
				JourneyItems.bloodcrustHelmet && body == 
				JourneyItems.bloodcrustChest && legs == 
				JourneyItems.bloodcrustLegs && boots == 
				JourneyItems.bloodcrustBoots) {
			event.player.addPotionEffect(new PotionEffect(PotionEffects.setPotionEffect(PotionEffects.fireResistance, 10, 5)));
			event.player.removePotionEffect(PotionEffects.getPotionFromID(20));
		}
		else if(helmet == 
				JourneyItems.bleedrockHelmet && body == 
				JourneyItems.bleedrockChest && legs == 
				JourneyItems.bleedrockLegs && boots == 
				JourneyItems.bleedrockBoots) {
			event.player.addPotionEffect(new PotionEffect(PotionEffects.setPotionEffect(PotionEffects.damageBoost, 1, 1)));
			event.player.removePotionEffect(PotionEffects.getPotionFromID(20));
		}

		else if(helmet == 
				JourneyItems.lightstoneHelmet && body == 
				JourneyItems.lightstoneChest && legs == 
				JourneyItems.lightstoneLegs && boots == 
				JourneyItems.lightstoneBoots) {
			event.player.addPotionEffect(new PotionEffect(PotionEffects.setPotionEffect(PotionEffects.nightVision, 240, 55)));
			event.player.addPotionEffect(new PotionEffect(PotionEffects.setPotionEffect(PotionEffects.moveSpeed, 10, 2)));
			event.player.removePotionEffect(PotionEffects.getPotionFromID(19));
			event.player.removePotionEffect(PotionEffects.getPotionFromID(20));
		}

		else if(helmet == 
				JourneyItems.livegreenHelmet && body == 
				JourneyItems.livegreenChest && legs == 
				JourneyItems.livegreenLegs && boots == 
				JourneyItems.livegreenBoots) {
			event.player.removePotionEffect(PotionEffects.getPotionFromID(19));
		}

		else if(helmet == 
				JourneyItems.starlightHelmet && body == 
				JourneyItems.starlightChest && legs == 
				JourneyItems.starlightLegs && boots == 
				JourneyItems.starlightBoots) {
			event.player.fallDistance = 0.0F;
		}

		else if(helmet == 
				JourneyItems.celestiumHelmet && body == 
				JourneyItems.celestiumChest && legs == 
				JourneyItems.celestiumLegs && boots == 
				JourneyItems.celestiumBoots) {
			event.player.addPotionEffect(new PotionEffect(PotionEffects.setPotionEffect(PotionEffects.moveSpeed, 10, 2)));
			event.player.fallDistance = 0.0F;
		}
	}

	@SubscribeEvent
	public void onJump(LivingJumpEvent event) {
		if(event.getEntityLiving() instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.getEntityLiving();
			ItemStack stackBoots = player.inventory.armorItemInSlot(0);
			ItemStack stackLegs = player.inventory.armorItemInSlot(1);
			ItemStack stackBody = player.inventory.armorItemInSlot(2);
			ItemStack stackHelmet = player.inventory.armorItemInSlot(3);
			if(stackBoots != null) boots = stackBoots.getItem();
			else boots = null;
			if(stackBody != null) body = stackBody.getItem();
			else body = null;
			if(stackLegs != null) legs = stackLegs.getItem();
			else legs = null;
			if(stackHelmet != null) helmet = stackHelmet.getItem();
			else helmet = null;

			if(helmet == 
					JourneyItems.celestiumHelmet && body == 
					JourneyItems.celestiumChest && legs == 
					JourneyItems.celestiumLegs && boots == 
					JourneyItems.celestiumBoots){
				player.addVelocity(0, 0.3, 0);
			} 

			else if(helmet == 
					JourneyItems.starlightHelmet && body == 
					JourneyItems.starlightChest && legs == 
					JourneyItems.starlightLegs && boots == 
					JourneyItems.starlightBoots){
				player.addVelocity(0, 0.6, 0);
			} 
		}
	}

	/*@SubscribeEvent
	public void onPlayerHurtEvent(LivingHurtEvent e) { //Used when the player is hurt by something (for armor that doesn't take damage from certain things like arrows)
		if(e.entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) e.entity;
			ItemStack stackBoots = player.inventory.armorItemInSlot(0);
			ItemStack stackLegs = player.inventory.armorItemInSlot(1);
			ItemStack stackBody = player.inventory.armorItemInSlot(2);
			ItemStack stackHelmet = player.inventory.armorItemInSlot(3);
			if(stackBoots != null) boots = stackBoots.getItem();
			else boots = null;
			if(stackBody != null) body = stackBody.getItem();
			else body = null;
			if(stackLegs != null) legs = stackLegs.getItem();
			else legs = null;
			if(stackHelmet != null) helmet = stackHelmet.getItem();
			else helmet = null;
			DamageSource s = e.source;
		}
	}

	@SubscribeEvent
	public void onPlayerAttackEvent(LivingAttackEvent e) { //Used when the player is hurt by something (for armor that doesn't take damage from certain things like drowning)
		if(e.entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) e.entity;
			ItemStack stackBoots = player.inventory.armorItemInSlot(0);
			ItemStack stackLegs = player.inventory.armorItemInSlot(1);
			ItemStack stackBody = player.inventory.armorItemInSlot(2);
			ItemStack stackHelmet = player.inventory.armorItemInSlot(3);
			if(stackBoots != null) boots = stackBoots.getItem();
			else boots = null;
			if(stackBody != null) body = stackBody.getItem();
			else body = null;
			if(stackLegs != null) legs = stackLegs.getItem();
			else legs = null;
			if(stackHelmet != null) helmet = stackHelmet.getItem();
			else helmet = null;
			DamageSource s = e.source;
		}
	}*/

	@SubscribeEvent
	public void onLivingHurtEvent(LivingHurtEvent e) {
		DamageSource s = e.getSource();
		if(e.getEntity() instanceof EntityPlayer){
			EntityPlayer player = (EntityPlayer)e.getEntity();
			ItemStack stackBoots = player.inventory.armorItemInSlot(0);
			ItemStack stackLegs = player.inventory.armorItemInSlot(1);
			ItemStack stackBody = player.inventory.armorItemInSlot(2);
			ItemStack stackHelmet = player.inventory.armorItemInSlot(3);
			if(stackBoots != null) boots = stackBoots.getItem();
			else boots = null;
			if(stackBody != null) body = stackBody.getItem();
			else body = null;
			if(stackLegs != null) legs = stackLegs.getItem();
			else legs = null;
			if(stackHelmet != null) helmet = stackHelmet.getItem();
			else helmet = null;
		}

		if(!(e.getEntity() instanceof EntityPlayer)) {
			float amount = e.getAmount();
			if(helmet == JourneyItems.luniumHelmet && body == JourneyItems.luniumChest && legs == JourneyItems.luniumLegs && boots == JourneyItems.luniumBoots)
				e.setAmount(amount += 4);

			if(helmet == JourneyItems.bronzedHelmet && body == JourneyItems.bronzedChest && legs == JourneyItems.bronzedLegs && boots == JourneyItems.bronzedBoots)
				e.setAmount(amount += 6);

			if(helmet == JourneyItems.blazehornHelmet && body == JourneyItems.blazehornChest && legs == JourneyItems.blazehornLegs && boots == JourneyItems.blazehornBoots)
				e.setAmount(amount += 3);

			if(helmet == JourneyItems.darklyHelmet && body == JourneyItems.darklyChest && legs == JourneyItems.darklyLegs && boots == JourneyItems.darklyBoots)
				e.setAmount(amount += 7);

			if(helmet == JourneyItems.crystalFlakeHelmet && body == JourneyItems.crystalFlakeChest && legs == JourneyItems.crystalFlakeLegs && boots == JourneyItems.crystalFlakeBoots)
				e.setAmount(amount += 3);

			if(helmet == JourneyItems.frostbittenHelmet && body == JourneyItems.frostbittenChest && legs == JourneyItems.frostbittenLegs && boots == JourneyItems.frostbittenBoots)
				e.setAmount(amount += 4);

			if(helmet == JourneyItems.corbarkHelmet && body == JourneyItems.corbarkChest && legs == JourneyItems.corbarkLegs && boots == JourneyItems.corbarkBoots) {
				e.getEntityLiving().addPotionEffect(new PotionEffect(PotionEffects.setPotionEffect(PotionEffects.moveSlow, 1000, 10)));
				e.getEntityLiving().addPotionEffect(new PotionEffect(PotionEffects.setPotionEffect(PotionEffects.poison, 1000, 10)));
			}

			if(helmet == JourneyItems.livegreenHelmet && body == JourneyItems.livegreenChest && legs == JourneyItems.livegreenLegs && boots == JourneyItems.livegreenBoots) {
				e.getEntityLiving().addPotionEffect(new PotionEffect(PotionEffects.setPotionEffect(PotionEffects.poison, 1000, 10)));
			}

			if(helmet == JourneyItems.hollowHelmet && body == JourneyItems.hollowChest && legs == JourneyItems.hollowLegs && boots == JourneyItems.hollowBoots) {
				e.getEntityLiving().addPotionEffect(new PotionEffect(PotionEffects.setPotionEffect(PotionEffects.wither, 1000, 10)));
			}

			else if(helmet == JourneyItems.flameHelmet && body == JourneyItems.flameChest && legs == JourneyItems.flameLegs && boots == JourneyItems.flameBoots)
				e.getEntityLiving().setFire(5);
		}
	}
}