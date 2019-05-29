package net.journey.items.bows;

import net.journey.entity.projectile.EntityEssenceArrow;
import net.journey.items.ItemModBow;
import net.journey.util.PotionEffects;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.PotionEffect;

public class LavenderBow extends ItemModBow {

    public LavenderBow(String unlocalizedName, int damage, int durability) {
        super(unlocalizedName, "Lavender Bow", durability, damage, "Slows targets");
        this.effect = EntityEssenceArrow.BowEffects.FROZEN_BOW;
    }

}
