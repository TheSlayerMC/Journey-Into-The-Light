package net.journey.items.bows;

import net.journey.entity.projectile.EntityEssenceArrow;
import net.journey.items.ItemModBow;
import net.journey.util.PotionEffects;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.PotionEffect;

public class FrozenBow extends ItemModBow {

    public FrozenBow(String unlocalizedName, int damage, int durability) {
        super(unlocalizedName, "Frozen Bow", durability, damage, "Slows targets");
        this.effect = EntityEssenceArrow.BowEffects.FROZEN_BOW;
    }

}
