package net.journey.items.bows;

import net.journey.entity.projectile.EntityEssenceArrow;
import net.journey.items.ItemModBow;
import net.journey.util.PotionEffects;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.PotionEffect;

public class LoggersBow extends ItemModBow {

    public LoggersBow(String unlocalizedName, int damage, int durability) {
        super(unlocalizedName, "Loggers Bow", durability, damage, "Poisons targets");
        this.effect = EntityEssenceArrow.BowEffects.POISON_BOW;
    }



}
