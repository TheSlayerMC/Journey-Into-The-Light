package net.journey.items.bows;

import net.journey.entity.projectile.EntityEssenceArrow;
import net.journey.items.ItemModBow;
import net.journey.util.PotionEffects;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.PotionEffect;

public class SpringBow extends ItemModBow {

    public SpringBow(String unlocalizedName, int damage, int durability) {
        super(unlocalizedName, "Spring Bow", durability, damage, "Sets targets ablaze");
        this.effect = EntityEssenceArrow.BowEffects.FLAME_BOW;
    }
}
