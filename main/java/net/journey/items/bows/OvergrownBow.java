package net.journey.items.bows;

import net.journey.entity.projectile.EntityEssenceArrow;
import net.journey.items.ItemModBow;
import net.journey.util.PotionEffects;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.PotionEffect;

public class OvergrownBow extends ItemModBow {

    public OvergrownBow(String unlocalizedName, int damage, int durability) {
        super(unlocalizedName, "Overgrown Bow", durability, damage, "Poisons targets");
        this.effect = EntityEssenceArrow.BowEffects.POISON_BOW;
    }



}
