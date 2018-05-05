package net.journey.entity.mob.boss;

import net.journey.JourneyAchievements;
import net.journey.JourneyBlocks;
import net.journey.JourneyItems;
import net.journey.blocks.tileentity.TileEntityJourneyChest;
import net.journey.entity.MobStats;
import net.journey.enums.EnumSounds;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.slayer.api.entity.EntityEssenceBoss;

public class EntityThunderbird extends EntityEssenceBoss {

	private int attackTimer;

	public EntityThunderbird(World par1World) {
		super(par1World);
		addAttackingAI();
		setSize(3.0F, 3.5F);
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();
		if(this.attackTimer > 0) --this.attackTimer;
		if (this.motionX * this.motionX + this.motionZ * this.motionZ > 2.500000277905201E-7D && this.rand.nextInt(5) == 0) {
			int i = MathHelper.floor_double(this.posX);
			int j = MathHelper.floor_double(this.posY - 0.20000000298023224D);
			int k = MathHelper.floor_double(this.posZ);
			IBlockState iblockstate = this.worldObj.getBlockState(new BlockPos(i, j, k));
            Block block = iblockstate.getBlock();
			if(block.getMaterial() != Material.air)
				 this.worldObj.spawnParticle(EnumParticleTypes.BLOCK_CRACK, this.posX + (this.rand.nextFloat() - 0.5D) * this.width, this.getEntityBoundingBox().minY + 0.1D, this.posZ + (this.rand.nextFloat() - 0.5D) * this.width, 4.0D * (this.rand.nextFloat() - 0.5D), 0.5D, (this.rand.nextFloat() - 0.5D) * 4.0D, new int[] {Block.getStateId(iblockstate)});
		}
	}

	@Override
	public boolean attackEntityAsMob(Entity e) {
		this.attackTimer = 5;
		this.worldObj.setEntityState(this, (byte)4);
		boolean flag = e.attackEntityFrom(DamageSource.causeMobDamage(this), (float)getAttackDamage());
		if(flag) { 
			e.motionY += 1.0000000059604645D;
			e.setFire(10 + rand.nextInt(10));
		}
		this.playSound("mob.irongolem.throw", 1.0F, 1.0F);
		return flag;
	}

	@Override
	public double setAttackDamage(MobStats s) {
		return MobStats.thunderbirdDamage;
	}

	@Override
	public double setMaxHealth(MobStats s) {
		return MobStats.thunderbirdHealth;
	}

	@Override
	public EnumSounds setLivingSound() {
		return EnumSounds.NETHER_BEAST;
	}

	@Override
	public EnumSounds setHurtSound() {
		return EnumSounds.NETHER_BEAST_HURT;
	}

	@Override
	public EnumSounds setDeathSound() {
		return EnumSounds.BOSS_DEATH;
	}
	
	@Override
	public Item getItemDropped() {
		return JourneyItems.corbaPortalGem;
	}
	
	@Override
	public void onDeath(DamageSource damage) {
		if(damage.getEntity() instanceof EntityPlayer) {
			EntityPlayer p = (EntityPlayer)damage.getEntity();
			p.triggerAchievement(JourneyAchievements.achievementThunderbird); {
			}
		}
		this.worldObj.setBlockState(new BlockPos((int)Math.floor(this.posX + 0), ((int)Math.floor(this.posY + 1)), ((int)Math.floor(this.posZ + 0))), JourneyBlocks.trophyRoc.getStateFromMeta(5));
		this.worldObj.setBlockState(new BlockPos((int)Math.floor(this.posX + 0), ((int)Math.floor(this.posY + 0)), ((int)Math.floor(this.posZ + 0))), JourneyBlocks.terraniaChest.getStateFromMeta(5));
		TileEntityJourneyChest te = (TileEntityJourneyChest)worldObj.getTileEntity(new BlockPos((int)Math.floor(this.posX + 0), ((int)Math.floor(this.posY + 0)), ((int)Math.floor(this.posZ + 0))));
		switch(rand.nextInt(2)) {
		case 0:
			te.setInventorySlotContents(2, new ItemStack(JourneyItems.rocsWing, 1));
			te.setInventorySlotContents(4, new ItemStack(JourneyItems.rocSword, 1));
			te.setInventorySlotContents(1, new ItemStack(JourneyItems.swordOfTheThunderbird, 1));
			te.setInventorySlotContents(18, new ItemStack(JourneyItems.thunderbirdBattleaxe, 1));
			te.setInventorySlotContents(11, new ItemStack(JourneyItems.corbaPortalGem, 5));
			break;
		case 1:
			te.setInventorySlotContents(1, new ItemStack(JourneyItems.rocsWing, 1));
			te.setInventorySlotContents(5, new ItemStack(JourneyItems.rocSword, 1));
			te.setInventorySlotContents(2, new ItemStack(JourneyItems.swordOfTheThunderbird, 1));
			te.setInventorySlotContents(14, new ItemStack(JourneyItems.thunderbirdBattleaxe, 1));
			te.setInventorySlotContents(13, new ItemStack(JourneyItems.corbaPortalGem, 5));
			break;
		}
	}
}