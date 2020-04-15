package net.journey.entity.mob.overworld.underground;

import net.journey.JourneyConsumables;
import net.journey.JourneySounds;
import net.journey.entity.MobStats;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.slayer.api.entity.EntityModMob;

public class EntityGreenHonglow extends EntityModMob {

	public EntityGreenHonglow(World par1World) {
		super(par1World);
		addAttackingAI();
		this.setSize(1.0F, 2.0F);
	}

	@Override
	public float getBrightness() {
		return 15728880F;
	}

	@Override
	public int getBrightnessForRender() {
		return 2000000;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if(rand.nextInt(10) == 0) {
			if(getHealth() >= 0) {
				this.world.setLightFor(EnumSkyBlock.BLOCK, new BlockPos((int)this.posX, (int)this.posY, (int)this.posZ), 6);
				this.world.markBlockRangeForRenderUpdate((int)this.posX, (int)this.posY, (int)this.posX, 12, 12, 12);
				this.world.checkLightFor(EnumSkyBlock.BLOCK, new BlockPos((int)this.posX, (int)this.posY + 1, (int)this.posZ));
				this.world.checkLightFor(EnumSkyBlock.BLOCK, new BlockPos((int)this.posX + 1, (int)this.posY + 1, (int)this.posZ));
				this.world.checkLightFor(EnumSkyBlock.BLOCK, new BlockPos((int)this.posX + 1, (int)this.posY + 1, (int)this.posZ + 1));
				this.world.checkLightFor(EnumSkyBlock.BLOCK, new BlockPos((int)this.posX + 1, (int)this.posY + 1, (int)this.posZ - 1));
				this.world.checkLightFor(EnumSkyBlock.BLOCK, new BlockPos((int)this.posX - 1, (int)this.posY + 1, (int)this.posZ + 1));
				this.world.checkLightFor(EnumSkyBlock.BLOCK, new BlockPos((int)this.posX - 1, (int)this.posY + 1, (int)this.posZ - 1));
				this.world.checkLightFor(EnumSkyBlock.BLOCK, new BlockPos((int)this.posX - 1, (int)this.posY + 1, (int)this.posZ));
				this.world.checkLightFor(EnumSkyBlock.BLOCK, new BlockPos((int)this.posX, (int)this.posY + 1, (int)this.posZ + 1));
				this.world.checkLightFor(EnumSkyBlock.BLOCK, new BlockPos((int)this.posX, (int)this.posY + 1, (int)this.posZ - 1));
				this.world.checkLightFor(EnumSkyBlock.BLOCK, new BlockPos((int)this.posX, (int)this.posY - 1, (int)this.posZ));
				this.world.checkLightFor(EnumSkyBlock.BLOCK, new BlockPos((int)this.posX + 1, (int)this.posY - 1, (int)this.posZ));
				this.world.checkLightFor(EnumSkyBlock.BLOCK, new BlockPos((int)this.posX + 1, (int)this.posY - 1, (int)this.posZ + 1));
				this.world.checkLightFor(EnumSkyBlock.BLOCK, new BlockPos((int)this.posX + 1, (int)this.posY - 1, (int)this.posZ - 1));
				this.world.checkLightFor(EnumSkyBlock.BLOCK, new BlockPos((int)this.posX - 1, (int)this.posY - 1, (int)this.posZ + 1));
				this.world.checkLightFor(EnumSkyBlock.BLOCK, new BlockPos((int)this.posX - 1, (int)this.posY - 1, (int)this.posZ - 1));
				this.world.checkLightFor(EnumSkyBlock.BLOCK, new BlockPos((int)this.posX - 1, (int)this.posY - 1, (int)this.posZ));
				this.world.checkLightFor(EnumSkyBlock.BLOCK, new BlockPos((int)this.posX, (int)this.posY - 1, (int)this.posZ + 1));
				this.world.checkLightFor(EnumSkyBlock.BLOCK, new BlockPos((int)this.posX, (int)this.posY - 1, (int)this.posZ - 1));
				this.world.checkLightFor(EnumSkyBlock.BLOCK, new BlockPos((int)this.posX + 1, (int)this.posY, (int)this.posZ));
				this.world.checkLightFor(EnumSkyBlock.BLOCK, new BlockPos((int)this.posX + 1, (int)this.posY, (int)this.posZ + 1));
				this.world.checkLightFor(EnumSkyBlock.BLOCK, new BlockPos((int)this.posX + 1, (int)this.posY, (int)this.posZ - 1));
				this.world.checkLightFor(EnumSkyBlock.BLOCK, new BlockPos((int)this.posX - 1, (int)this.posY, (int)this.posZ + 1));
				this.world.checkLightFor(EnumSkyBlock.BLOCK, new BlockPos((int)this.posX - 1, (int)this.posY, (int)this.posZ - 1));
				this.world.checkLightFor(EnumSkyBlock.BLOCK, new BlockPos((int)this.posX - 1, (int)this.posY, (int)this.posZ));
				this.world.checkLightFor(EnumSkyBlock.BLOCK, new BlockPos((int)this.posX, (int)this.posY, (int)this.posZ + 1));
				this.world.checkLightFor(EnumSkyBlock.BLOCK, new BlockPos((int)this.posX, (int)this.posY, (int)this.posZ - 1));
			}
		}
	}

	@Override
	public double setAttackDamage(MobStats s) {
		return MobStats.GreenHonglowDamage;
	}

	@Override
	public double setMaxHealth(MobStats s) {
		return MobStats.GreenHonglowHealth;
	}

	@Override
	public SoundEvent setLivingSound() {
		return JourneySounds.HONGO;
	}

	@Override
	public SoundEvent setHurtSound() {
		return JourneySounds.HONGO_HURT;
	}

	@Override
	public SoundEvent setDeathSound() {
		return JourneySounds.HONGO_HURT;
	}

	@Override
	public Item getItemDropped() {
		return null;

	}

	@Override
	public void onDeath(DamageSource d) {
		super.onDeath(d);
		if(d.getImmediateSource() instanceof EntityPlayer) {
			EntityPlayer p = (EntityPlayer)d.getImmediateSource();
			//p.triggerAchievement(JourneyAchievements.achievementCave);
		}
		this.world.setLightFor(EnumSkyBlock.BLOCK, new BlockPos((int)this.posX, (int)this.posY, (int)this.posZ), 0);
		this.world.markBlockRangeForRenderUpdate((int)this.posX, (int)this.posY, (int)this.posX, 12, 12, 12);
		this.world.checkLightFor(EnumSkyBlock.BLOCK, new BlockPos((int)this.posX, (int)this.posY + 1, (int)this.posZ));
		this.world.checkLightFor(EnumSkyBlock.BLOCK, new BlockPos((int)this.posX + 1, (int)this.posY + 1, (int)this.posZ));
		this.world.checkLightFor(EnumSkyBlock.BLOCK, new BlockPos((int)this.posX + 1, (int)this.posY + 1, (int)this.posZ + 1));
		this.world.checkLightFor(EnumSkyBlock.BLOCK, new BlockPos((int)this.posX + 1, (int)this.posY + 1, (int)this.posZ - 1));
		this.world.checkLightFor(EnumSkyBlock.BLOCK, new BlockPos((int)this.posX - 1, (int)this.posY + 1, (int)this.posZ + 1));
		this.world.checkLightFor(EnumSkyBlock.BLOCK, new BlockPos((int)this.posX - 1, (int)this.posY + 1, (int)this.posZ - 1));
		this.world.checkLightFor(EnumSkyBlock.BLOCK, new BlockPos((int)this.posX - 1, (int)this.posY + 1, (int)this.posZ));
		this.world.checkLightFor(EnumSkyBlock.BLOCK, new BlockPos((int)this.posX, (int)this.posY + 1, (int)this.posZ + 1));
		this.world.checkLightFor(EnumSkyBlock.BLOCK, new BlockPos((int)this.posX, (int)this.posY + 1, (int)this.posZ - 1));
		this.world.checkLightFor(EnumSkyBlock.BLOCK, new BlockPos((int)this.posX, (int)this.posY - 1, (int)this.posZ));
		this.world.checkLightFor(EnumSkyBlock.BLOCK, new BlockPos((int)this.posX + 1, (int)this.posY - 1, (int)this.posZ));
		this.world.checkLightFor(EnumSkyBlock.BLOCK, new BlockPos((int)this.posX + 1, (int)this.posY - 1, (int)this.posZ + 1));
		this.world.checkLightFor(EnumSkyBlock.BLOCK, new BlockPos((int)this.posX + 1, (int)this.posY - 1, (int)this.posZ - 1));
		this.world.checkLightFor(EnumSkyBlock.BLOCK, new BlockPos((int)this.posX - 1, (int)this.posY - 1, (int)this.posZ + 1));
		this.world.checkLightFor(EnumSkyBlock.BLOCK, new BlockPos((int)this.posX - 1, (int)this.posY - 1, (int)this.posZ - 1));
		this.world.checkLightFor(EnumSkyBlock.BLOCK, new BlockPos((int)this.posX - 1, (int)this.posY - 1, (int)this.posZ));
		this.world.checkLightFor(EnumSkyBlock.BLOCK, new BlockPos((int)this.posX, (int)this.posY - 1, (int)this.posZ + 1));
		this.world.checkLightFor(EnumSkyBlock.BLOCK, new BlockPos((int)this.posX, (int)this.posY - 1, (int)this.posZ - 1));
		this.world.checkLightFor(EnumSkyBlock.BLOCK, new BlockPos((int)this.posX + 1, (int)this.posY, (int)this.posZ));
		this.world.checkLightFor(EnumSkyBlock.BLOCK, new BlockPos((int)this.posX + 1, (int)this.posY, (int)this.posZ + 1));
		this.world.checkLightFor(EnumSkyBlock.BLOCK, new BlockPos((int)this.posX + 1, (int)this.posY, (int)this.posZ - 1));
		this.world.checkLightFor(EnumSkyBlock.BLOCK, new BlockPos((int)this.posX - 1, (int)this.posY, (int)this.posZ + 1));
		this.world.checkLightFor(EnumSkyBlock.BLOCK, new BlockPos((int)this.posX - 1, (int)this.posY, (int)this.posZ - 1));
		this.world.checkLightFor(EnumSkyBlock.BLOCK, new BlockPos((int)this.posX - 1, (int)this.posY, (int)this.posZ));
		this.world.checkLightFor(EnumSkyBlock.BLOCK, new BlockPos((int)this.posX, (int)this.posY, (int)this.posZ + 1));
		this.world.checkLightFor(EnumSkyBlock.BLOCK, new BlockPos((int)this.posX, (int)this.posY, (int)this.posZ - 1));
	}

	@Override
	public boolean getCanSpawnHere() {
		return this.posY < 40.0D && super.getCanSpawnHere() && 
				this.world.getBlockState(new BlockPos(this.posX, this.posY - 1, this.posZ)).getMaterial() == Material.ROCK && this.dimension == 0;
	}

	@Override
	protected void dropFewItems(boolean b, int j) {
		if(rand.nextInt(1) == 0) dropItem(JourneyConsumables.greenHonglowShroom, rand.nextInt(2));
		super.dropFewItems(b, j);
	}
}