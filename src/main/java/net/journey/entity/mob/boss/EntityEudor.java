package net.journey.entity.mob.boss;

import com.google.common.collect.Lists;
import jeresources.api.drop.LootDrop;
import net.journey.blocks.tileentity.TileEntityBossCrystal;
import net.journey.entity.MobStats;
import net.journey.init.JourneyLootTables;
import net.journey.init.JourneySounds;
import net.journey.init.blocks.JourneyBlocks;
import net.journey.init.items.JourneyItems;
import net.journey.init.items.JourneyWeapons;
import net.journey.util.PotionEffects;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.slayer.api.entity.EntityEssenceBoss;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class EntityEudor extends EntityEssenceBoss {

    private int firetick;
    private final int firemax = 400;
    private final int firemax2 = 300;
    private boolean isInvi;

    public EntityEudor(World par1World) {
        super(par1World);
        addMeleeAttackingAI();
        this.setSize(3.5F, 5.2F);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return JourneySounds.CALCIA;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource d) {
        return JourneySounds.CALCIA_HURT;
    }

    public boolean isInv() {
        return isInvi;
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (isInv()) {
            for (int i = 0; i < 5; i++)
                this.world.spawnParticle(EnumParticleTypes.ENCHANTMENT_TABLE, this.posX + (this.rand.nextDouble() - 0.5D) * this.width, this.posY + this.rand.nextDouble() * this.height - 0.25D, this.posZ + (this.rand.nextDouble() - 0.5D) * this.width, (this.rand.nextDouble() - 0.5D) * 2.0D, -this.rand.nextDouble(), (this.rand.nextDouble() - 0.5D) * 2.0D);
            Entity entity = attackingPlayer;
            if (entity != null)
                ((EntityPlayer) entity).addPotionEffect(new PotionEffect(PotionEffects.setPotionEffect(PotionEffects.blindness, 60, 5)));
        }
    }

    @Override
    public void onLivingUpdate() {
        if (firemax == firetick && firetick != 0) {
            this.isInvi = true;
            this.firetick = 0;
        } else {
            firetick++;
        }

        if (firemax2 == firetick && firetick != 0) {
            this.isInvi = false;
            this.firetick = 0;
        } else {
            firetick++;
        }
        super.onLivingUpdate();
    }

    @Override
    public void onDeath(DamageSource damage) {
        this.world.setBlockState(new BlockPos((int) Math.floor(this.posX + 0), ((int) Math.floor(this.posY + 0)), ((int) Math.floor(this.posZ + 0))), JourneyBlocks.bossCrystalEuca.getDefaultState());
        TileEntityBossCrystal te = (TileEntityBossCrystal) world.getTileEntity(new BlockPos((int) Math.floor(this.posX + 0), ((int) Math.floor(this.posY + 0)), ((int) Math.floor(this.posZ + 0))));
        te.setLootTable(JourneyLootTables.EUDOR, rand.nextLong());
    }

    @Override
    public @NotNull List<LootDrop> getJERDrops() {
        return Lists.newArrayList(
                new LootDrop(JourneyItems.depthsPortalGem, 7, 8),
                new LootDrop(JourneyWeapons.kingsSword, 1, 1)
        );
    }

    @Override
    public @NotNull EntitySettings getEntitySettings() {
        return MobStats.EUDOR;
    }
}