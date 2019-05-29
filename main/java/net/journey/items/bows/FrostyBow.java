package net.journey.items.bows;

import net.journey.entity.projectile.EntityEssenceArrow;
import net.journey.items.ItemModBow;
import net.journey.util.PotionEffects;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.PotionEffect;

public class FrostyBow extends ItemModBow {

    public FrostyBow(String unlocalizedName, int damage, int durability) {
        super(unlocalizedName, "Frosty Bow", durability, damage, "Slows targets");
        this.effect = EntityEssenceArrow.BowEffects.FROZEN_BOW;
    }

}
