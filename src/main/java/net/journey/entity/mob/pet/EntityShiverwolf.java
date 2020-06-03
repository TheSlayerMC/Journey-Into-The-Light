package net.journey.entity.mob.pet;

import com.google.common.base.Predicate;

import net.journey.api.entity.ISettingsConsumer.EntitySettings;
import net.journey.entity.MobStats;
import net.journey.init.JourneySounds;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.entity.EntityJourneyPet;
import net.slayer.api.entity.EntityModTameable;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class EntityShiverwolf extends EntityJourneyPet {

	public EntityShiverwolf(World worldIn) {
		super(worldIn);
	}

	public EntityShiverwolf(World worldIn, EntityPlayer owner) {
		super(worldIn, owner);
		setSize(0.25F, 0.25F);
	}

	@Override
	public SoundEvent setLivingSound() {
		return JourneySounds.EMPTY;
	}

	@Override
	public SoundEvent setHurtSound() {
		return JourneySounds.EMPTY;
	}

	@Override
	public SoundEvent setDeathSound() {
		return JourneySounds.EMPTY;
	}


	@Override
	public EntityAgeable createChild(EntityAgeable ageable) {
		return null;
	}

	@Override
	public @NotNull EntitySettings getEntitySettings() {
		return MobStats.SHIVERWOLF;
	}
}