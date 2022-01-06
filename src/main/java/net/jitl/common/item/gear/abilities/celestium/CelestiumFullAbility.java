package net.jitl.common.item.gear.abilities.celestium;

import net.jitl.common.item.gear.abilities.FullArmorAbility;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.phys.Vec3;
import net.minecraft.server.level.ServerLevel;

public class CelestiumFullAbility extends FullArmorAbility {
    public CelestiumFullAbility(CompoundTag nbt) {
        super(nbt);
    }

    @Override
    public void tick(LivingEntity entity) {
        //TODO: Make enemies be smart and use the ability or something
        //System.out.println("Dash cooldown for " + entity.getName().getString() + ": " + tag.getInt("cooldown"));
        //System.out.println("Dash for " + entity.getName().getString() + " " + (tag.getBoolean("Jump ready") ? "is" : "is not") + " ready");
        int cooldown = tag.getInt("cooldown");
        if (cooldown <= 0) {
            if (entity.isOnGround() && !tag.getBoolean("Jump ready")) {
                tag.putBoolean("Jump ready", true);
                double halfSize = entity.getBbWidth() / 2;
                ((ServerLevel) entity.level).sendParticles(ParticleTypes.POOF, entity.getX(), entity.getY(), entity.getZ(), 200, halfSize, entity.getBbHeight(), halfSize, 0.1);
            }
        } else {
            tag.putInt("cooldown", cooldown - 1);
        }
    }

    @Override
    public void keyPressed(LivingEntity entity) {
        if (!entity.isOnGround() && tag.getBoolean("Jump ready")) {
            System.out.println("Dash");
            Vec3 look = entity.getLookAngle();
            entity.setDeltaMovement(look.x() * 2.5, 0, look.z() * 2.5);
            entity.hurtMarked = true;
            ((ServerLevel) entity.level).sendParticles(ParticleTypes.EXPLOSION, entity.getX(), entity.getY(), entity.getZ(), 1, 0, 0, 0, 1);
            tag.putBoolean("Jump ready", false);
            tag.putInt("cooldown", 40);
        }
    }
}
