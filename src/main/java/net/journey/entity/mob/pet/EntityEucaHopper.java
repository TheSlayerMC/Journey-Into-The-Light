package net.journey.entity.mob.pet;

import net.journey.entity.MobStats;
import net.journey.init.JourneySounds;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.slayer.api.entity.EntityJourneyPet;
import net.slayer.api.entity.EntityModTameable;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class EntityEucaHopper extends EntityJourneyPet {


    public EntityEucaHopper(World w) {
        super(w);
        this.setSize(1.0F, 1.0F);
    }
    
    public EntityEucaHopper(World worldIn, EntityPlayer owner) {
		super(worldIn, owner);
        this.setSize(1.0F, 1.0F);
	}

    @Override
    public SoundEvent setLivingSound() {
        return JourneySounds.HONGO;
    }

    @Override
    public SoundEvent setHurtSound() {
        return JourneySounds.TURTLE_HURT;
    }

    @Override
    public SoundEvent setDeathSound() {
        return JourneySounds.TURTLE_HURT;
    }

    @Override
    public @NotNull EntitySettings getEntitySettings() {
        return new EntitySettings(100, 10);
    }

	@Override
	public EntityAgeable createChild(EntityAgeable ageable) {
		return null;
	}
}