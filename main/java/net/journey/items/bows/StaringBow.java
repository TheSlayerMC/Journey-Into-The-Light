package net.journey.items.bows;

import net.journey.entity.projectile.EntityEssenceArrow;
import net.journey.items.ItemModBow;
import net.journey.util.PotionEffects;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.PotionEffect;

public class StaringBow extends ItemModBow {

    public StaringBow(String unlocalizedName, int damage, int durability) {
        super(unlocalizedName, "Staring Bow", durability, damage, "Sets targets ablaze");
        this.effect = EntityEssenceArrow.BowEffects.FLAME_BOW;
    }
}
