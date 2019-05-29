package net.journey.items.bows;

import net.journey.entity.projectile.EntityEssenceArrow;
import net.journey.items.ItemModBow;
import net.journey.util.PotionEffects;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.PotionEffect;

public class DeathPiercerBow extends ItemModBow {

    public DeathPiercerBow(String unlocalizedName, int damage, int durability) {
        super(unlocalizedName, "Death Piercer Bow", durability, damage, "Withers targets");
        this.effect = EntityEssenceArrow.BowEffects.DARKNESS_BOW;
    }

}
