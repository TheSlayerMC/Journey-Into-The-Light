package net.journey.eventhandler;

import net.journey.init.items.JourneyArmory;
import net.journey.util.PotionEffects;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;

public class ArmorAbilityEvent {


    private final JourneyArmory item = new JourneyArmory();
    private Item boots = null, body = null, legs = null, helmet = null;

    @SubscribeEvent
    public void playerTick(PlayerTickEvent event) {
        ItemStack stackBoots = event.player.inventory.armorInventory.get(0);
        ItemStack stackLegs = event.player.inventory.armorInventory.get(1);
        ItemStack stackBody = event.player.inventory.armorInventory.get(2);
        ItemStack stackHelmet = event.player.inventory.armorInventory.get(3);
        if (stackBoots != null) boots = stackBoots.getItem();
        else boots = null;
        if (stackBody != null) body = stackBody.getItem();
        else body = null;
        if (stackLegs != null) legs = stackLegs.getItem();
        else legs = null;
        if (stackHelmet != null) helmet = stackHelmet.getItem();
        else helmet = null;

        if (helmet ==
                JourneyArmory.hellstoneHelmet && body ==
                JourneyArmory.hellstoneChest && legs ==
                JourneyArmory.hellstoneLegs && boots ==
                JourneyArmory.hellstoneBoots) {
            event.player.addPotionEffect(new PotionEffect(PotionEffects.setPotionEffect(PotionEffects.fireResistance, 10, 1)));
        } else if (helmet ==
                JourneyArmory.shadiumHelmet && body ==
                JourneyArmory.shadiumChest && legs ==
                JourneyArmory.shadiumLegs && boots ==
                JourneyArmory.shadiumBoots) {
            if (event.player.isInWater()) event.player.setAir(300);
        } else if (helmet ==
                JourneyArmory.sapphireHelmet && body ==
                JourneyArmory.sapphireChest && legs ==
                JourneyArmory.sapphireLegs && boots ==
                JourneyArmory.sapphireBoots) {
            if (event.player.isInWater()) event.player.setAir(300);
        } else if (helmet ==
                JourneyArmory.twilightHelmet && body ==
                JourneyArmory.twilightChest && legs ==
                JourneyArmory.twilightLegs && boots ==
                JourneyArmory.twilightBoots) {
            event.player.addPotionEffect(new PotionEffect(PotionEffects.setPotionEffect(PotionEffects.nightVision, 240, 55)));
            event.player.addPotionEffect(new PotionEffect(PotionEffects.setPotionEffect(PotionEffects.fireResistance, 240, 55)));
        } else if (helmet ==
                JourneyArmory.leapersHelmet && body ==
                JourneyArmory.leapersChest && legs ==
                JourneyArmory.leapersLegs && boots ==
                JourneyArmory.leapersBoots) {
            event.player.addPotionEffect(new PotionEffect(PotionEffects.setPotionEffect(PotionEffects.jump, 10, 1)));

        } else if (helmet ==
                JourneyArmory.snakeskinHelmet && body ==
                JourneyArmory.snakeskinChest && legs ==
                JourneyArmory.snakeskinLegs && boots ==
                JourneyArmory.snakeskinBoots) {
            event.player.addPotionEffect(new PotionEffect(PotionEffects.setPotionEffect(PotionEffects.moveSpeed, 10, 3)));
            if (event.player.isInLava())
                event.player.addPotionEffect(new PotionEffect(PotionEffects.setPotionEffect(PotionEffects.fireResistance, 100, 3)));
            if (event.player.isInLava())
                event.player.addPotionEffect(new PotionEffect(PotionEffects.setPotionEffect(PotionEffects.damageBoost, 10, 1)));
        } else if (helmet ==
                JourneyArmory.blazehornHelmet && body ==
                JourneyArmory.blazehornChest && legs ==
                JourneyArmory.blazehornLegs && boots ==
                JourneyArmory.blazehornBoots) {
            if (event.player.isInLava())
                event.player.addPotionEffect(new PotionEffect(PotionEffects.setPotionEffect(PotionEffects.fireResistance, 100, 10)));
        } else if (helmet ==
                JourneyArmory.crystalFlakeHelmet && body ==
                JourneyArmory.crystalFlakeChest && legs ==
                JourneyArmory.crystalFlakeLegs && boots ==
                JourneyArmory.crystalFlakeBoots) {
            event.player.addPotionEffect(new PotionEffect(PotionEffects.setPotionEffect(PotionEffects.waterBreathing, 10, 1)));
        } else if (helmet ==
                JourneyArmory.charskullHelmet && body ==
                JourneyArmory.charskullChest && legs ==
                JourneyArmory.charskullLegs && boots ==
                JourneyArmory.charskullBoots) {
            event.player.addPotionEffect(new PotionEffect(PotionEffects.setPotionEffect(PotionEffects.fireResistance, 10, 1)));
            event.player.addPotionEffect(new PotionEffect(PotionEffects.setPotionEffect(PotionEffects.damageBoost, 10, 2)));
        } else if (helmet ==
                JourneyArmory.golditeHelmet && body ==
                JourneyArmory.golditeChest && legs ==
                JourneyArmory.golditeLegs && boots ==
                JourneyArmory.golditeBoots) {
            event.player.fallDistance = 0;
            event.player.addPotionEffect(new PotionEffect(PotionEffects.setPotionEffect(PotionEffects.moveSpeed, 10, 1)));
        } else if (helmet
                == JourneyArmory.treehuggersHelmet && body ==
                JourneyArmory.treehuggersChest && legs ==
                JourneyArmory.treehuggersLegs && boots ==
                JourneyArmory.treehuggersBoots) {
            event.player.removePotionEffect(PotionEffects.getPotionFromID(19));
            event.player.removePotionEffect(PotionEffects.getPotionFromID(20));
        } else if (helmet ==
                JourneyArmory.flairiumHelmet && body ==
                JourneyArmory.flairiumChest && legs ==
                JourneyArmory.flairiumLegs && boots ==
                JourneyArmory.flairiumBoots) {
            event.player.addPotionEffect(new PotionEffect(PotionEffects.setPotionEffect(PotionEffects.fireResistance, 10, 1)));
        } else if (helmet ==
                JourneyArmory.darklyHelmet && body ==
                JourneyArmory.darklyChest && legs ==
                JourneyArmory.darklyLegs && boots ==
                JourneyArmory.darklyBoots) {
            event.player.addPotionEffect(new PotionEffect(PotionEffects.setPotionEffect(PotionEffects.nightVision, 240, 55)));

        } else if (helmet ==
                JourneyArmory.depthsHelmet && body ==
                JourneyArmory.depthsChest && legs ==
                JourneyArmory.depthsLegs && boots ==
                JourneyArmory.depthsBoots) {
            event.player.addPotionEffect(new PotionEffect(PotionEffects.setPotionEffect(PotionEffects.nightVision, 240, 55)));
            event.player.addPotionEffect(new PotionEffect(PotionEffects.setPotionEffect(PotionEffects.waterBreathing, 10, 1)));
        } else if (helmet ==
                JourneyArmory.enlightenerHelmet && body ==
                JourneyArmory.enlightenerChest && legs ==
                JourneyArmory.enlightenerLegs && boots ==
                JourneyArmory.enlightenerBoots) {
            event.player.addPotionEffect(new PotionEffect(PotionEffects.setPotionEffect(PotionEffects.jump, 10, 3)));
            event.player.addPotionEffect(new PotionEffect(PotionEffects.setPotionEffect(PotionEffects.regeneration, 10, 10)));
        } else if (helmet ==
                JourneyArmory.fireboundHelmet && body ==
                JourneyArmory.fireboundChest && legs ==
                JourneyArmory.fireboundLegs && boots ==
                JourneyArmory.fireboundBoots) {
            event.player.addPotionEffect(new PotionEffect(PotionEffects.setPotionEffect(PotionEffects.jump, 10, 3)));
            event.player.addPotionEffect(new PotionEffect(PotionEffects.setPotionEffect(PotionEffects.fireResistance, 10, 1)));
        } else if (helmet ==
                JourneyArmory.frostbittenHelmet && body ==
                JourneyArmory.frostbittenChest && legs ==
                JourneyArmory.frostbittenLegs && boots ==
                JourneyArmory.frostbittenBoots) {
            event.player.addPotionEffect(new PotionEffect(PotionEffects.setPotionEffect(PotionEffects.damageBoost, 10, 1)));
        } else if (helmet ==
                JourneyArmory.hollowHelmet && body ==
                JourneyArmory.hollowChest && legs ==
                JourneyArmory.hollowLegs && boots ==
                JourneyArmory.hollowBoots) {
            event.player.addPotionEffect(new PotionEffect(PotionEffects.setPotionEffect(PotionEffects.nightVision, 240, 55)));
        } else if (helmet ==
                JourneyArmory.bloodcrustHelmet && body ==
                JourneyArmory.bloodcrustChest && legs ==
                JourneyArmory.bloodcrustLegs && boots ==
                JourneyArmory.bloodcrustBoots) {
            event.player.addPotionEffect(new PotionEffect(PotionEffects.setPotionEffect(PotionEffects.fireResistance, 10, 5)));
            event.player.removePotionEffect(PotionEffects.getPotionFromID(20));
        } else if (helmet ==
                JourneyArmory.flameHelmet && body ==
                JourneyArmory.flameChest && legs ==
                JourneyArmory.flameLegs && boots ==
                JourneyArmory.flameBoots) {
            event.player.addPotionEffect(new PotionEffect(PotionEffects.setPotionEffect(PotionEffects.fireResistance, 10, 5)));
        } else if (helmet ==
                JourneyArmory.bleedrockHelmet && body ==
                JourneyArmory.bleedrockChest && legs ==
                JourneyArmory.bleedrockLegs && boots ==
                JourneyArmory.bleedrockBoots) {
            event.player.addPotionEffect(new PotionEffect(PotionEffects.setPotionEffect(PotionEffects.damageBoost, 1, 1)));
            event.player.removePotionEffect(PotionEffects.getPotionFromID(20));
        } else if (helmet ==
                JourneyArmory.lightstoneHelmet && body ==
                JourneyArmory.lightstoneChest && legs ==
                JourneyArmory.lightstoneLegs && boots ==
                JourneyArmory.lightstoneBoots) {
            event.player.addPotionEffect(new PotionEffect(PotionEffects.setPotionEffect(PotionEffects.nightVision, 240, 55)));
            event.player.addPotionEffect(new PotionEffect(PotionEffects.setPotionEffect(PotionEffects.moveSpeed, 10, 2)));
            event.player.removePotionEffect(PotionEffects.getPotionFromID(19));
            event.player.removePotionEffect(PotionEffects.getPotionFromID(20));
        } else if (helmet ==
                JourneyArmory.livegreenHelmet && body ==
                JourneyArmory.livegreenChest && legs ==
                JourneyArmory.livegreenLegs && boots ==
                JourneyArmory.livegreenBoots) {
            event.player.removePotionEffect(PotionEffects.getPotionFromID(19));
        } else if (helmet ==
                JourneyArmory.starlightHelmet && body ==
                JourneyArmory.starlightChest && legs ==
                JourneyArmory.starlightLegs && boots ==
                JourneyArmory.starlightBoots) {
            event.player.fallDistance = 0.0F;
        } else if (helmet ==
                JourneyArmory.celestiumHelmet && body ==
                JourneyArmory.celestiumChest && legs ==
                JourneyArmory.celestiumLegs && boots ==
                JourneyArmory.celestiumBoots) {
            event.player.addPotionEffect(new PotionEffect(PotionEffects.setPotionEffect(PotionEffects.moveSpeed, 10, 2)));
            event.player.fallDistance = 0.0F;
        }
    }

    @SubscribeEvent
    public void onJump(LivingJumpEvent event) {
        if (event.getEntityLiving() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.getEntityLiving();
            ItemStack stackBoots = player.inventory.armorInventory.get(0);
            ItemStack stackLegs = player.inventory.armorInventory.get(1);
            ItemStack stackBody = player.inventory.armorInventory.get(2);
            ItemStack stackHelmet = player.inventory.armorInventory.get(3);
            if (stackBoots != null) boots = stackBoots.getItem();
            else boots = null;
            if (stackBody != null) body = stackBody.getItem();
            else body = null;
            if (stackLegs != null) legs = stackLegs.getItem();
            else legs = null;
            if (stackHelmet != null) helmet = stackHelmet.getItem();
            else helmet = null;

            if (helmet ==
                    JourneyArmory.celestiumHelmet && body ==
                    JourneyArmory.celestiumChest && legs ==
                    JourneyArmory.celestiumLegs && boots ==
                    JourneyArmory.celestiumBoots) {
                player.addVelocity(0, 0.3, 0);
            } else if (helmet ==
                    JourneyArmory.starlightHelmet && body ==
                    JourneyArmory.starlightChest && legs ==
                    JourneyArmory.starlightLegs && boots ==
                    JourneyArmory.starlightBoots) {
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
        if (e.getEntity() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) e.getEntity();
            ItemStack stackBoots = player.inventory.armorInventory.get(0);
            ItemStack stackLegs = player.inventory.armorInventory.get(1);
            ItemStack stackBody = player.inventory.armorInventory.get(2);
            ItemStack stackHelmet = player.inventory.armorInventory.get(3);
            if (stackBoots != null) boots = stackBoots.getItem();
            else boots = null;
            if (stackBody != null) body = stackBody.getItem();
            else body = null;
            if (stackLegs != null) legs = stackLegs.getItem();
            else legs = null;
            if (stackHelmet != null) helmet = stackHelmet.getItem();
            else helmet = null;
        }

        if (!(e.getEntity() instanceof EntityPlayer)) {
            float amount = e.getAmount();
            if (helmet == JourneyArmory.luniumHelmet && body == JourneyArmory.luniumChest && legs == JourneyArmory.luniumLegs && boots == JourneyArmory.luniumBoots)
                e.setAmount(amount += 4);

            if (helmet == JourneyArmory.bronzedHelmet && body == JourneyArmory.bronzedChest && legs == JourneyArmory.bronzedLegs && boots == JourneyArmory.bronzedBoots)
                e.setAmount(amount += 6);

            if (helmet == JourneyArmory.blazehornHelmet && body == JourneyArmory.blazehornChest && legs == JourneyArmory.blazehornLegs && boots == JourneyArmory.blazehornBoots)
                e.setAmount(amount += 3);

            if (helmet == JourneyArmory.darklyHelmet && body == JourneyArmory.darklyChest && legs == JourneyArmory.darklyLegs && boots == JourneyArmory.darklyBoots)
                e.setAmount(amount += 7);

            if (helmet == JourneyArmory.crystalFlakeHelmet && body == JourneyArmory.crystalFlakeChest && legs == JourneyArmory.crystalFlakeLegs && boots == JourneyArmory.crystalFlakeBoots)
                e.setAmount(amount += 3);

            if (helmet == JourneyArmory.frostbittenHelmet && body == JourneyArmory.frostbittenChest && legs == JourneyArmory.frostbittenLegs && boots == JourneyArmory.frostbittenBoots)
                e.setAmount(amount += 4);

            if (helmet == JourneyArmory.corbarkHelmet && body == JourneyArmory.corbarkChest && legs == JourneyArmory.corbarkLegs && boots == JourneyArmory.corbarkBoots) {
                e.getEntityLiving().addPotionEffect(new PotionEffect(PotionEffects.setPotionEffect(PotionEffects.moveSlow, 1000, 10)));
                e.getEntityLiving().addPotionEffect(new PotionEffect(PotionEffects.setPotionEffect(PotionEffects.poison, 1000, 10)));
            }

            if (helmet == JourneyArmory.livegreenHelmet && body == JourneyArmory.livegreenChest && legs == JourneyArmory.livegreenLegs && boots == JourneyArmory.livegreenBoots) {
                e.getEntityLiving().addPotionEffect(new PotionEffect(PotionEffects.setPotionEffect(PotionEffects.poison, 1000, 10)));
            }

            if (helmet == JourneyArmory.hollowHelmet && body == JourneyArmory.hollowChest && legs == JourneyArmory.hollowLegs && boots == JourneyArmory.hollowBoots) {
                e.getEntityLiving().addPotionEffect(new PotionEffect(PotionEffects.setPotionEffect(PotionEffects.wither, 1000, 10)));
            } else if (helmet == JourneyArmory.flameHelmet && body == JourneyArmory.flameChest && legs == JourneyArmory.flameLegs && boots == JourneyArmory.flameBoots)
                e.getEntityLiving().setFire(5);
        }
    }
}
