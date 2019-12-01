package net.journey.entity.mob.boss;

import net.journey.JourneyArmory;
import net.journey.JourneyBlocks;
import net.journey.JourneyItems;
import net.journey.JourneySounds;
import net.journey.JourneyWeapons;
import net.journey.blocks.tileentity.TileEntityJourneyChest;
import net.journey.entity.MobStats;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.slayer.api.entity.EntityEssenceBoss;

public class EntityLogger extends EntityEssenceBoss {

	private int attackTimer;

	public EntityLogger(World par1World) {
		super(par1World);
		addAttackingAI();
		setSize(3.0F, 3.5F);
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();
		if(this.attackTimer > 0) --this.attackTimer;
		if (this.motionX * this.motionX + this.motionZ * this.motionZ > 2.500000277905201E-7D && this.rand.nextInt(5) == 0) {
			int i = MathHelper.floor(this.posX);
			int j = MathHelper.floor(this.posY - 0.20000000298023224D);
			int k = MathHelper.floor(this.posZ);
			IBlockState iblockstate = this.world.getBlockState(new BlockPos(i, j, k));
			Block block = iblockstate.getBlock();
			if(block.getMaterial(iblockstate) != Material.AIR)
				this.world.spawnParticle(EnumParticleTypes.BLOCK_CRACK, this.posX + (this.rand.nextFloat() - 0.5D) * this.width, this.getEntityBoundingBox().minY + 0.1D, this.posZ + (this.rand.nextFloat() - 0.5D) * this.width, 4.0D * (this.rand.nextFloat() - 0.5D), 0.5D, (this.rand.nextFloat() - 0.5D) * 4.0D, new int[] {Block.getStateId(iblockstate)});
		}
	}

	@Override
	public boolean attackEntityAsMob(Entity e) {
		this.attackTimer = 5;
		this.world.setEntityState(this, (byte)4);
		boolean flag = e.attackEntityFrom(DamageSource.causeMobDamage(this), (float)getAttackDamage());
		if(flag) { 
			e.motionY += 1.0000000059604645D;
			e.setFire(10 + rand.nextInt(10));
		}
		this.playSound(SoundEvents.ENTITY_IRONGOLEM_ATTACK, 1.0F, 1.0F);
		return flag;
	}

	@Override
	public double setAttackDamage(MobStats s) {
		return MobStats.loggerDamage;
	}

	@Override
	public double setMaxHealth(MobStats s) {
		return MobStats.loggerHealth;
	}

	@Override
	public SoundEvent setLivingSound() {
		return JourneySounds.BEAST_OF_THE_NETHER;
	}

	@Override
	public SoundEvent setHurtSound() {
		return JourneySounds.BEAST_OF_THE_NETHER_HURT;
	}

	@Override
	public SoundEvent setDeathSound() {
		return JourneySounds.BOSS_DEATH;
	}

	@Override
	public void onDeath(DamageSource damage) {
		if(damage.getImmediateSource() instanceof EntityPlayer) {
			EntityPlayer p = (EntityPlayer)damage.getImmediateSource();
			//p.triggerAchievement(JourneyAchievements.achievementlogger);
		}
		this.world.setBlockState(new BlockPos((int)Math.floor(this.posX + 0), ((int)Math.floor(this.posY + 1)), ((int)Math.floor(this.posZ + 0))), JourneyBlocks.trophyLogger.getStateFromMeta(5));
		this.world.setBlockState(new BlockPos((int)Math.floor(this.posX + 0), ((int)Math.floor(this.posY + 0)), ((int)Math.floor(this.posZ + 0))), JourneyBlocks.journeyChest.getStateFromMeta(5));
		TileEntityJourneyChest te = (TileEntityJourneyChest)world.getTileEntity(new BlockPos((int)Math.floor(this.posX + 0), ((int)Math.floor(this.posY + 0)), ((int)Math.floor(this.posZ + 0))));
		switch(rand.nextInt(2)) {
		case 0:
			te.setInventorySlotContents(15, new ItemStack(JourneyItems.terraniaPortalGem, 8));
			te.setInventorySlotContents(1, new ItemStack(JourneyWeapons.naturesBlade, 1));
			te.setInventorySlotContents(5, new ItemStack(JourneyWeapons.loggersBow, 1));

			te.setInventorySlotContents(3, new ItemStack(JourneyArmory.hollowChest, 1));
			te.setInventorySlotContents(11, new ItemStack(JourneyArmory.hollowHelmet, 1));
			te.setInventorySlotContents(7, new ItemStack(JourneyArmory.hollowLegs, 1));
			te.setInventorySlotContents(8, new ItemStack(JourneyArmory.hollowBoots, 1));
			break;
		case 1:
			te.setInventorySlotContents(1, new ItemStack(JourneyItems.terraniaPortalGem, 10));
			te.setInventorySlotContents(2, new ItemStack(JourneyWeapons.loggersSword, 1));
			te.setInventorySlotContents(10, new ItemStack(JourneyWeapons.loggersBow, 1));

			te.setInventorySlotContents(3, new ItemStack(JourneyArmory.hollowChest, 1));
			te.setInventorySlotContents(11, new ItemStack(JourneyArmory.hollowHelmet, 1));
			te.setInventorySlotContents(5, new ItemStack(JourneyArmory.hollowLegs, 1));
			te.setInventorySlotContents(6, new ItemStack(JourneyArmory.hollowBoots, 1));
			break;
		}
	}

	@Override
	protected void dropFewItems(boolean b, int j) {
		switch(rand.nextInt(3)) {
		}
	}

	@Override
	public Item getItemDropped() {
		return null;
	}
}