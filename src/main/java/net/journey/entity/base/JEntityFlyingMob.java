package net.journey.entity.base;

import net.journey.api.capability.JourneyPlayer;
import net.journey.api.capability.PlayerStats;
import net.journey.api.entity.IJERCompatible;
import net.journey.common.capability.JCapabilityManager;
import net.journey.common.knowledge.EnumKnowledgeType;
import net.journey.entity.MobStats;
import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.Nullable;

public abstract class JEntityFlyingMob extends EntityFlying implements IJERCompatible {

    private EnumKnowledgeType knowledgeType;
    private int knowledgeAmount;

    public JEntityFlyingMob(World w) {
        super(w);
        setSize(0.5F, 0.5F);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();

        EntityAttributesHelper.setFollowRange(this, MobStats.STANDARD_FLYING_FOLLOW_RANGE);
        EntityAttributesHelper.setMovementSpeed(this, MobStats.STANDARD_MOVEMENT_SPEED);
        EntityAttributesHelper.setKnockbackResistance(this, MobStats.STANDARD_KNOCKBACK_RESISTANCE);
        EntityAttributesHelper.setMaxHealth(this, getEntityMaxHealth());
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();

        if (!isServerWorld()) {
            onClientUpdate();
        }
    }

    @SideOnly(Side.CLIENT)
    protected void onClientUpdate() {

    }

    /**
     * Value in this method will be applied later in {@link #applyEntityAttributes()} for {@link SharedMonsterAttributes#MAX_HEALTH}
     */
    protected abstract double getEntityMaxHealth();

    @Override
    public abstract SoundEvent getAmbientSound();

    @Override
    public abstract SoundEvent getHurtSound(DamageSource source);

    @Override
    public abstract SoundEvent getDeathSound();

    public final double getMoveSpeed() {
        return getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue();
    }

    public final double getAttackDamage() {
        return getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue();
    }

    public final double getFollowRange() {
        return getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).getAttributeValue();
    }

    public final double getKnockbackResistance() {
        return getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).getAttributeValue();
    }

    public JEntityFlyingMob setKnowledge(EnumKnowledgeType type, int amount) {
        this.knowledgeType = type;
        this.knowledgeAmount = amount;
        return this;
    }

    @Override
    public void onDeath(DamageSource cause) {
        super.onDeath(cause);
        if (cause.getTrueSource() instanceof EntityPlayer && knowledgeType != null && knowledgeAmount > 0) {
            EntityPlayer player = (EntityPlayer) cause.getTrueSource();
            JourneyPlayer journeyPlayer = JCapabilityManager.asJourneyPlayer(player);
            PlayerStats stats = journeyPlayer.getPlayerStats();
            stats.addKnowledge(this.knowledgeType, this.knowledgeAmount);
            journeyPlayer.sendUpdates();
        }
    }

    @Override
    public @Nullable ResourceLocation getJERLootLocation() {
        return getLootTable();
    }
}

