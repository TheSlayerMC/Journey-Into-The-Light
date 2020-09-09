package net.journey.entity.mob.boiling;

import net.journey.common.knowledge.EnumKnowledgeType;
import net.journey.entity.MobStats;
import net.journey.entity.base.EntityAttributesHelper;
import net.journey.entity.base.JEntityMob;
import net.journey.init.JourneyLootTables;
import net.journey.init.JourneySounds;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityMagmaGiant extends JEntityMob {

    public EntityMagmaGiant(World par1World) {
        super(par1World);
        addMeleeAttackingAI();
        setSize(1.0F, 2.7F);
        isImmuneToFire = true;
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();

        EntityAttributesHelper.setMaxHealth(this, MobStats.MAGMA_GIANT_HEALTH);
        EntityAttributesHelper.setAttackDamage(this, MobStats.MAGMA_GIANT_DAMAGE);
		this.setKnowledge(EnumKnowledgeType.BOIL, 5);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return JourneySounds.MAGMA_GIANT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource d) {
        return JourneySounds.MAGMA_GIANT_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return JourneySounds.MAGMA_GIANT_HURT;
    }

    @Override
    public boolean attackEntityFrom(DamageSource e, float a) {
        if (e.getImmediateSource() instanceof EntityPlayer)
            e.getImmediateSource().setFire(5 + rand.nextInt(7));
        return super.attackEntityFrom(e, a);
    }

    @Override
    public int getBrightnessForRender() {
        return 15728880;
    }

    @Override
    public float getBrightness() {
        return 1.0F;
    }

	@Override
	public ResourceLocation getLootTable() {
		return JourneyLootTables.MAGMA_GIANT;
	}

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        int i = MathHelper.floor(this.posX);
        int j = MathHelper.floor(this.posY);
        int k = MathHelper.floor(this.posZ);

        if (this.isWet()) this.attackEntityFrom(DamageSource.DROWN, 1.0F);

        for (int l = 0; l < 4; ++l) {
            i = MathHelper.floor(this.posX + (l % 2 * 2 - 1) * 0.25F);
            j = MathHelper.floor(this.posY);
            k = MathHelper.floor(this.posZ + (l / 2 % 2 * 2 - 1) * 0.25F);
            if (this.world.isAirBlock(new BlockPos(i, j, k)) && Blocks.FIRE.canPlaceBlockAt(this.world, new BlockPos(i, j, k)))
                this.world.setBlockState(new BlockPos(i, j, k), Blocks.FIRE.getDefaultState());
        }
    }
}