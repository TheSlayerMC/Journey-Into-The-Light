package net.journey.items.bows;

import net.journey.entity.projectile.EntityEssenceArrow;
import net.journey.items.ItemModBow;
import net.journey.util.PotionEffects;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.PotionEffect;

public class GolemBow extends ItemModBow {

    public GolemBow(String unlocalizedName, int damage, int durability) {
        super(unlocalizedName, "Golem Bow", durability, damage, "Withers targets");
        this.effect = EntityEssenceArrow.BowEffects.DARKNESS_BOW;
    }

}
