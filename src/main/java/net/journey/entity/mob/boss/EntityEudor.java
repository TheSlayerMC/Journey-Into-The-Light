package net.journey.entity.mob.boss;

import net.journey.blocks.tileentity.TileEntityJourneyChest;
import net.journey.entity.MobStats;
import net.journey.init.JourneySounds;
import net.journey.init.blocks.JourneyBlocks;
import net.journey.init.items.JourneyItems;
import net.journey.init.items.JourneyWeapons;
import net.journey.util.PotionEffects;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.slayer.api.entity.EntityEssenceBoss;
import org.jetbrains.annotations.NotNull;

public class EntityEudor extends EntityEssenceBoss {

    private int firetick;
    private int firemax = 400, firemax2 = 300;
    private boolean isInvi;

    public EntityEudor(World par1World) {
        super(par1World);
        addAttackingAI();
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
		/* if(damage.getEntity() instanceof EntityPlayer) {
			EntityPlayer p = (EntityPlayer)damage.getEntity();
			p.triggerAchievement(JourneyAchievements.achievementEudor); {
			}
		} */
        this.world.setBlockState(new BlockPos((int) Math.floor(this.posX + 0), ((int) Math.floor(this.posY + 1)), ((int) Math.floor(this.posZ + 0))), JourneyBlocks.trophyEudor.getStateFromMeta(5));
        this.world.setBlockState(new BlockPos((int) Math.floor(this.posX + 0), ((int) Math.floor(this.posY + 0)), ((int) Math.floor(this.posZ + 0))), JourneyBlocks.journeyChest.getStateFromMeta(5));
        TileEntityJourneyChest te = (TileEntityJourneyChest) world.getTileEntity(new BlockPos((int) Math.floor(this.posX + 0), ((int) Math.floor(this.posY + 0)), ((int) Math.floor(this.posZ + 0))));
        switch (rand.nextInt(2)) {
            case 0:
                te.setInventorySlotContents(15, new ItemStack(JourneyItems.depthsPortalGem, 8));
                te.setInventorySlotContents(1, new ItemStack(JourneyWeapons.kingsSword, 1));
                break;
            case 1:
                te.setInventorySlotContents(1, new ItemStack(JourneyItems.depthsPortalGem, 7));
                te.setInventorySlotContents(10, new ItemStack(JourneyWeapons.kingsSword, 1));
                break;
        }
    }

    @Override
    public @NotNull EntitySettings getEntitySettings() {
        return MobStats.EUDOR;
    }
}