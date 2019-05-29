package net.journey.entity.projectile;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

import net.journey.JourneyItems;
import net.journey.util.PotionEffects;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.play.server.SPacketChangeGameState;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityDarknessArrow extends EntityTippedArrow implements IProjectile {

    private static final Predicate<Entity> ARROW_TARGETS = Predicates.and(EntitySelectors.NOT_SPECTATING, EntitySelectors.IS_ALIVE, new Predicate<Entity>() {
        public boolean apply(@Nullable Entity e) {
            return e.canBeCollidedWith();
        }
    });

    private PotionEffect potioneffect = new PotionEffect(PotionEffects.getPotionFromID(PotionEffects.wither), 100, 2);

	public EntityDarknessArrow(World worldIn) {
		super(worldIn);
	}

	public EntityDarknessArrow(World worldIn, EntityLivingBase shooter) {
		super(worldIn, shooter);
	}

	public EntityDarknessArrow(World worldIn, double x, double y, double z) {
		super(worldIn, x, y, z);
	}

	//apparently used for Fourfa's attack
	public EntityDarknessArrow(World worldIn, EntityLivingBase e, EntityLivingBase eb, float f, float f1) {
		super(worldIn);
		Entity.setRenderDistanceWeight(10.0D);
		this.shootingEntity = e;

		if (e instanceof EntityPlayer) {
			//lol idk what this is
		}

		this.posY = e.posY + e.getEyeHeight() - 0.10000000149011612D;
		double d0 = eb.posX - e.posX;
		double d1 = eb.getEntityBoundingBox().minY + eb.height / 3.0F - this.posY;
		double d2 = eb.posZ - e.posZ;
		double d3 = (double)MathHelper.sqrt(d0 * d0 + d2 * d2);

		if (d3 >= 1.0E-7D) {
			float f2 = (float)(Math.atan2(d2, d0) * 180.0D / Math.PI) - 90.0F;
			float f3 = (float)(-(Math.atan2(d1, d3) * 180.0D / Math.PI));
			double d4 = d0 / d3;
			double d5 = d2 / d3;
			this.setLocationAndAngles(e.posX + d4, this.posY, e.posZ + d5, f2, f3);
			float f4 = (float)(d3 * 0.20000000298023224D);
			this.shoot(d0, d1 + f4, d2, f, f1);
		}
	}

	@Override
	protected ItemStack getArrowStack() {
		return new ItemStack(JourneyItems.essenceArrow);
	}



}