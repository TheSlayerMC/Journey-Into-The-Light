package net.journey.entity.mob.boss;

import com.google.common.collect.Lists;
import jeresources.api.drop.LootDrop;
import net.journey.entity.MobStats;
import net.journey.init.JourneySounds;
import net.journey.init.items.JourneyItems;
import net.journey.init.items.JourneyWeapons;
import net.journey.util.PotionEffects;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.slayer.api.entity.EntityEssenceBoss;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class EntityCalcia extends EntityEssenceBoss {

    private int firetick;
    private int firemax = 400, firemax2 = 300;
    private boolean isInvi;

    public EntityCalcia(World par1World) {
        super(par1World);
        addMeleeAttackingAI();
        this.setSize(1.6F, 3.2F);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return JourneySounds.CALCIA;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource d) {
        return JourneySounds.CALCIA_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
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
                ((EntityPlayer) entity).addPotionEffect(PotionEffects.setPotionEffect(PotionEffects.blindness, 25, 2));
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
    protected void dropFewItems(boolean par1, int par2) {
        this.dropItem(JourneyItems.eucaPortalGem, 6 + rand.nextInt(4));
        this.dropItem(JourneyWeapons.calciaSword, 1);

        //if(rand.nextInt(1) == 0)
        //	this.dropItem(Item.getItemFromBlock(EssenceBlocks.calciaStatue), 1);
    }

    @Override
    public @NotNull List<LootDrop> getJERDrops() {
        return Lists.newArrayList(
                new LootDrop(JourneyItems.eucaPortalGem, 6, 9),
                new LootDrop(JourneyWeapons.calciaSword, 1, 1)
        );
    }

    @Override
    public @NotNull EntitySettings getEntitySettings() {
        return MobStats.CALCIA;
    }
}