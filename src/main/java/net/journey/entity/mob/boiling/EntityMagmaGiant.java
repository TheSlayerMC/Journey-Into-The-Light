package net.journey.entity.mob.boiling;

import net.journey.entity.MobStats;
import net.journey.init.JourneySounds;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.slayer.api.entity.EntityModMob;

public class EntityMagmaGiant extends EntityModMob {

    public EntityMagmaGiant(World par1World) {
        super(par1World);
        addAttackingAI();
        setSize(1.0F, 2.7F);
        isImmuneToFire = true;
    }

    @Override
    public double setAttackDamage(MobStats s) {
        return MobStats.MagmaGiantDamage;
    }

    @Override
    public double setMaxHealth(MobStats s) {
        return MobStats.MagmaGiantHealth;
    }

    @Override
    public SoundEvent setLivingSound() {
        return JourneySounds.MAGMA_GIANT;
    }

    @Override
    public SoundEvent setHurtSound() {
        return JourneySounds.MAGMA_GIANT_HURT;
    }

    @Override
    public SoundEvent setDeathSound() {
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
    public Item getItemDropped() {
        return null;
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