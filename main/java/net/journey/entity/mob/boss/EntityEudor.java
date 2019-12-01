package net.journey.entity.mob.boss;

import net.journey.JourneyBlocks;
import net.journey.JourneyItems;
import net.journey.JourneySounds;
import net.journey.JourneyWeapons;
import net.journey.blocks.tileentity.TileEntityJourneyChest;
import net.journey.entity.MobStats;
import net.journey.util.PotionEffects;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.slayer.api.entity.EntityEssenceBoss;

public class EntityEudor extends EntityEssenceBoss {

	private int firetick;
	private int firemax = 400, firemax2 = 300;
	private boolean isInvi;

	public EntityEudor(World par1World) {
		super(par1World);
		addAttackingAI();
		this.setSize(1.6F, 3.2F);
	}

	@Override
	public double setAttackDamage(MobStats s) {
		return MobStats.calciaDamage;
	}

	@Override
	public double setKnockbackResistance() {
		return 1.0D;
	}

	@Override
	public double setMaxHealth(MobStats s) {
		return MobStats.eudorHealth;
	}

	@Override
	public SoundEvent setLivingSound() {
		return JourneySounds.CALCIA;
	}

	@Override
	public SoundEvent setHurtSound() {
		return JourneySounds.CALCIA_HURT;
	}

	@Override
	public SoundEvent setDeathSound() {
		return JourneySounds.BOSS_DEATH;
	}

	public boolean isInv() {
		return isInvi;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if(isInv()) {
			for(int i = 0; i < 5; i++) this.world.spawnParticle(EnumParticleTypes.ENCHANTMENT_TABLE, this.posX + (this.rand.nextDouble() - 0.5D) * this.width, this.posY + this.rand.nextDouble() * this.height - 0.25D, this.posZ + (this.rand.nextDouble() - 0.5D) * this.width, (this.rand.nextDouble() - 0.5D) * 2.0D, -this.rand.nextDouble(), (this.rand.nextDouble() - 0.5D) * 2.0D, new int[0]);
			Entity entity = attackingPlayer;
			if(entity != null)
				((EntityPlayer)entity).addPotionEffect(new PotionEffect(PotionEffects.setPotionEffect(PotionEffects.blindness, 60, 5)));
		}
	}

	@Override
	public void onLivingUpdate() {
		if(firemax == firetick && firetick != 0) {
			this.isInvi = true;
			this.firetick = 0;
		} else {
			firetick++;
		} 

		if(firemax2 == firetick && firetick != 0) {
			this.isInvi = false;
			this.firetick = 0;
		} else {
			firetick++;
		}
		super.onLivingUpdate();
	}
	@Override
	public Item getItemDropped() {
		return null;
	}
	
	@Override
	public void onDeath(DamageSource damage) {
		/* if(damage.getEntity() instanceof EntityPlayer) {
			EntityPlayer p = (EntityPlayer)damage.getEntity();
			p.triggerAchievement(JourneyAchievements.achievementEudor); {
			}
		} */
		this.world.setBlockState(new BlockPos((int)Math.floor(this.posX + 0), ((int)Math.floor(this.posY + 1)), ((int)Math.floor(this.posZ + 0))), JourneyBlocks.trophyEudor.getStateFromMeta(5));
		this.world.setBlockState(new BlockPos((int)Math.floor(this.posX + 0), ((int)Math.floor(this.posY + 0)), ((int)Math.floor(this.posZ + 0))), JourneyBlocks.journeyChest.getStateFromMeta(5));
		TileEntityJourneyChest te = (TileEntityJourneyChest)world.getTileEntity(new BlockPos((int)Math.floor(this.posX + 0), ((int)Math.floor(this.posY + 0)), ((int)Math.floor(this.posZ + 0))));
		switch(rand.nextInt(2)) {
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
}