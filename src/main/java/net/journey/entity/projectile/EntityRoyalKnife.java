/**
 * package net.journey.entity.projectile;
 * <p>
 * import java.util.List;
 * <p>
 * import net.journey.JourneyItems;
 * import net.minecraft.block.Block;
 * import net.minecraft.block.material.Material;
 * import net.minecraft.block.state.IBlockState;
 * import net.minecraft.enchantment.EnchantmentHelper;
 * import net.minecraft.entity.Entity;
 * import net.minecraft.entity.EntityLivingBase;
 * import net.minecraft.entity.monster.EntityEnderman;
 * import net.minecraft.entity.player.EntityPlayer;
 * import net.minecraft.entity.player.EntityPlayerMP;
 * import net.minecraft.entity.projectile.EntityThrowable;
 * import net.minecraft.item.ItemStack;
 * import net.minecraft.nbt.NBTTagCompound;
 * import net.minecraft.network.play.server.S2BPacketChangeGameState;
 * import net.minecraft.util.AxisAlignedBB;
 * import net.minecraft.util.BlockPos;
 * import net.minecraft.util.DamageSource;
 * import net.minecraft.util.EntityDamageSourceIndirect;
 * import net.minecraft.util.EnumFacing;
 * import net.minecraft.util.EnumParticleTypes;
 * import net.minecraft.util.MathHelper;
 * import net.minecraft.util.MovingObjectPosition;
 * import net.minecraft.util.ResourceLocation;
 * import net.minecraft.util.Vec3;
 * import net.minecraft.world.World;
 * import net.minecraftforge.fml.relauncher.Side;
 * import net.minecraftforge.fml.relauncher.SideOnly;
 * <p>
 * public class EntityRoyalKnife extends EntityThrowable{
 * <p>
 * private int xTile = -1;
 * private int yTile = -1;
 * private int zTile = -1;
 * private Block inBlock;
 * private int inData;
 * private boolean inGround;
 * public int canBePickedUp;
 * public int arrowShake;
 * public Entity shootingEntity;
 * private int ticksInGround;
 * private int ticksInAir;
 * private double damage = 6.0D;
 * private int knockbackStrength;
 * <p>
 * public EntityRoyalKnife(World worldIn) {
 * super(worldIn);
 * this.renderDistanceWeight = 10.0D;
 * this.setSize(0.5F, 0.5F);
 * }
 * <p>
 * public EntityRoyalKnife(World worldIn, double d, double d1, double d2) {
 * super(worldIn);
 * this.renderDistanceWeight = 10.0D;
 * this.setSize(0.5F, 0.5F);
 * this.setPosition(d, d1, d2);
 * }
 * <p>
 * public EntityRoyalKnife(World worldIn, EntityLivingBase e, EntityLivingBase eb, float f, float f1) {
 * super(worldIn);
 * this.renderDistanceWeight = 10.0D;
 * this.shootingEntity = e;
 * <p>
 * if (e instanceof EntityPlayer) {
 * this.canBePickedUp = 1;
 * }
 * <p>
 * this.posY = e.posY + (double)e.getEyeHeight() - 0.10000000149011612D;
 * double d0 = eb.posX - e.posX;
 * double d1 = eb.getEntityBoundingBox().minY + (double)(eb.height / 3.0F) - this.posY;
 * double d2 = eb.posZ - e.posZ;
 * double d3 = (double)MathHelper.sqrt_double(d0 * d0 + d2 * d2);
 * <p>
 * if (d3 >= 1.0E-7D) {
 * float f2 = (float)(Math.atan2(d2, d0) * 180.0D / Math.PI) - 90.0F;
 * float f3 = (float)(-(Math.atan2(d1, d3) * 180.0D / Math.PI));
 * double d4 = d0 / d3;
 * double d5 = d2 / d3;
 * this.setLocationAndAngles(e.posX + d4, this.posY, e.posZ + d5, f2, f3);
 * float f4 = (float)(d3 * 0.20000000298023224D);
 * this.setThrowableHeading(d0, d1 + (double)f4, d2, f, f1);
 * }
 * }
 * <p>
 * public EntityRoyalKnife(World worldIn, EntityLivingBase e, float f) {
 * super(worldIn);
 * this.renderDistanceWeight = 10.0D;
 * this.shootingEntity = e;
 * if(e instanceof EntityPlayer) this.canBePickedUp = 1;
 * this.setSize(0.5F, 0.5F);
 * this.setLocationAndAngles(e.posX, e.posY + (double)e.getEyeHeight(), e.posZ, e.rotationYaw, e.rotationPitch);
 * this.posX -= (double)(MathHelper.cos(this.rotationYaw / 180.0F * (float)Math.PI) * 0.16F);
 * this.posY -= 0.10000000149011612D;
 * this.posZ -= (double)(MathHelper.sin(this.rotationYaw / 180.0F * (float)Math.PI) * 0.16F);
 * this.setPosition(this.posX, this.posY, this.posZ);
 * this.motionX = (double)(-MathHelper.sin(this.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float)Math.PI));
 * this.motionZ = (double)(MathHelper.cos(this.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float)Math.PI));
 * this.motionY = (double)(-MathHelper.sin(this.rotationPitch / 180.0F * (float)Math.PI));
 * this.setThrowableHeading(this.motionX, this.motionY, this.motionZ, f * 1.5F, 1.0F);
 * }
 *
 * @Override protected void entityInit() {
 * this.dataWatcher.addObject(16, Byte.valueOf((byte)0));
 * }
 * @Override public void setThrowableHeading(double d, double d1, double d2, float f, float f1) {
 * float f2 = MathHelper.sqrt_double(d * d + d1 * d1 + d2 * d2);
 * d /= (double)f2;
 * d1 /= (double)f2;
 * d2 /= (double)f2;
 * d += this.rand.nextGaussian() * (double)(this.rand.nextBoolean() ? -1 : 1) * 0.007499999832361937D * (double)f1;
 * d1 += this.rand.nextGaussian() * (double)(this.rand.nextBoolean() ? -1 : 1) * 0.007499999832361937D * (double)f1;
 * d2 += this.rand.nextGaussian() * (double)(this.rand.nextBoolean() ? -1 : 1) * 0.007499999832361937D * (double)f1;
 * d *= (double)f;
 * d1 *= (double)f;
 * d2 *= (double)f;
 * this.motionX = d;
 * this.motionY = d1;
 * this.motionZ = d2;
 * float f3 = MathHelper.sqrt_double(d * d + d2 * d2);
 * this.prevRotationYaw = this.rotationYaw = (float)(Math.atan2(d, d2) * 180.0D / Math.PI);
 * this.prevRotationPitch = this.rotationPitch = (float)(Math.atan2(d1, (double)f3) * 180.0D / Math.PI);
 * this.ticksInGround = 0;
 * }
 * @Override
 * @SideOnly(Side.CLIENT) public void setPositionAndRotation2(double x, double y, double z, float r, float yaw, int i, boolean b) {
 * this.setPosition(x, y, z);
 * this.setRotation(r, yaw);
 * }
 * @Override
 * @SideOnly(Side.CLIENT) public void setVelocity(double x, double y, double z) {
 * this.motionX = x;
 * this.motionY = y;
 * this.motionZ = z;
 * <p>
 * if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F) {
 * float f = MathHelper.sqrt_double(x * x + z * z);
 * this.prevRotationYaw = this.rotationYaw = (float)(Math.atan2(x, z) * 180.0D / Math.PI);
 * this.prevRotationPitch = this.rotationPitch = (float)(Math.atan2(y, (double)f) * 180.0D / Math.PI);
 * this.prevRotationPitch = this.rotationPitch;
 * this.prevRotationYaw = this.rotationYaw;
 * this.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
 * this.ticksInGround = 0;
 * }
 * }
 * @Override public void onUpdate() {
 * super.onEntityUpdate();
 * <p>
 * if(this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F) {
 * float f = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
 * this.prevRotationYaw = this.rotationYaw = (float)(Math.atan2(this.motionX, this.motionZ) * 180.0D / Math.PI);
 * this.prevRotationPitch = this.rotationPitch = (float)(Math.atan2(this.motionY, (double)f) * 180.0D / Math.PI);
 * }
 * <p>
 * BlockPos blockpos = new BlockPos(this.xTile, this.yTile, this.zTile);
 * IBlockState iblockstate = this.worldObj.getBlockState(blockpos);
 * Block block = iblockstate.getBlock();
 * <p>
 * if (block.getMaterial() != Material.air) {
 * block.setBlockBoundsBasedOnState(this.worldObj, blockpos);
 * AxisAlignedBB axisalignedbb = block.getCollisionBoundingBox(this.worldObj, blockpos, iblockstate);
 * <p>
 * if (axisalignedbb != null && axisalignedbb.isVecInside(new Vec3(this.posX, this.posY, this.posZ))) {
 * this.inGround = true;
 * }
 * }
 * <p>
 * if (this.arrowShake > 0) {
 * --this.arrowShake;
 * }
 * <p>
 * if (this.inGround) {
 * int j = block.getMetaFromState(iblockstate);
 * <p>
 * if (block == this.inBlock && j == this.inData) {
 * ++this.ticksInGround;
 * <p>
 * if (this.ticksInGround >= 1200) {
 * this.setDead();
 * }
 * } else {
 * this.inGround = false;
 * this.motionX *= (double)(this.rand.nextFloat() * 0.2F);
 * this.motionY *= (double)(this.rand.nextFloat() * 0.2F);
 * this.motionZ *= (double)(this.rand.nextFloat() * 0.2F);
 * this.ticksInGround = 0;
 * this.ticksInAir = 0;
 * }
 * } else {
 * ++this.ticksInAir;
 * Vec3 vec31 = new Vec3(this.posX, this.posY, this.posZ);
 * Vec3 vec3 = new Vec3(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
 * MovingObjectPosition movingobjectposition = this.worldObj.rayTraceBlocks(vec31, vec3, false, true, false);
 * vec31 = new Vec3(this.posX, this.posY, this.posZ);
 * vec3 = new Vec3(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
 * <p>
 * if (movingobjectposition != null) {
 * vec3 = new Vec3(movingobjectposition.hitVec.xCoord, movingobjectposition.hitVec.yCoord, movingobjectposition.hitVec.zCoord);
 * }
 * <p>
 * Entity entity = null;
 * List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().addCoord(this.motionX, this.motionY, this.motionZ).expand(1.0D, 1.0D, 1.0D));
 * double d0 = 0.0D;
 * int i;
 * float f1;
 * <p>
 * for (i = 0; i < list.size(); ++i) {
 * Entity entity1 = (Entity)list.get(i);
 * <p>
 * if (entity1.canBeCollidedWith() && (entity1 != this.shootingEntity || this.ticksInAir >= 5)) {
 * f1 = 0.3F;
 * AxisAlignedBB axisalignedbb1 = entity1.getEntityBoundingBox().expand((double)f1, (double)f1, (double)f1);
 * MovingObjectPosition movingobjectposition1 = axisalignedbb1.calculateIntercept(vec31, vec3);
 * <p>
 * if (movingobjectposition1 != null) {
 * double d1 = vec31.distanceTo(movingobjectposition1.hitVec);
 * <p>
 * if (d1 < d0 || d0 == 0.0D) {
 * entity = entity1;
 * d0 = d1;
 * }
 * }
 * }
 * }
 * <p>
 * if (entity != null) {
 * movingobjectposition = new MovingObjectPosition(entity);
 * }
 * <p>
 * if (movingobjectposition != null && movingobjectposition.entityHit != null && movingobjectposition.entityHit instanceof EntityPlayer) {
 * EntityPlayer entityplayer = (EntityPlayer)movingobjectposition.entityHit;
 * <p>
 * if (entityplayer.capabilities.disableDamage || this.shootingEntity instanceof EntityPlayer && !((EntityPlayer)this.shootingEntity).canAttackPlayer(entityplayer)) {
 * movingobjectposition = null;
 * }
 * }
 * <p>
 * float f2;
 * float f3;
 * float f4;
 * <p>
 * if (movingobjectposition != null) {
 * if (movingobjectposition.entityHit != null) {
 * f2 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
 * int k = MathHelper.ceiling_double_int((double)f2 * this.damage);
 * <p>
 * if (this.getIsCritical()) {
 * k += this.rand.nextInt(k / 2 + 2);
 * }
 * <p>
 * DamageSource damagesource;
 * <p>
 * if (this.shootingEntity == null) {
 * damagesource = new EntityDamageSourceIndirect("arrow", this, this).setProjectile();
 * } else {
 * damagesource = new EntityDamageSourceIndirect("arrow", this, this.shootingEntity).setProjectile();
 * }
 * <p>
 * if (movingobjectposition.entityHit.attackEntityFrom(damagesource, (float)k)) {
 * if (movingobjectposition.entityHit instanceof EntityLivingBase) {
 * EntityLivingBase entitylivingbase = (EntityLivingBase)movingobjectposition.entityHit;
 * <p>
 * if (!this.worldObj.isRemote) {
 * entitylivingbase.setArrowCountInEntity(entitylivingbase.getArrowCountInEntity() + 1);
 * }
 * <p>
 * if (this.knockbackStrength > 0) {
 * f4 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
 * <p>
 * if (f4 > 0.0F) {
 * movingobjectposition.entityHit.addVelocity(this.motionX * (double)this.knockbackStrength * 0.6000000238418579D / (double)f4, 0.1D, this.motionZ * (double)this.knockbackStrength * 0.6000000238418579D / (double)f4);
 * }
 * }
 * <p>
 * if (this.shootingEntity instanceof EntityLivingBase) {
 * EnchantmentHelper.applyThornEnchantments(entitylivingbase, this.shootingEntity);
 * EnchantmentHelper.applyArthropodEnchantments((EntityLivingBase)this.shootingEntity, entitylivingbase);
 * }
 * <p>
 * if (this.shootingEntity != null && movingobjectposition.entityHit != this.shootingEntity && movingobjectposition.entityHit instanceof EntityPlayer && this.shootingEntity instanceof EntityPlayerMP) {
 * ((EntityPlayerMP)this.shootingEntity).playerNetServerHandler.sendPacket(new S2BPacketChangeGameState(6, 0.0F));
 * }
 * }
 * <p>
 * this.playSound("random.bowhit", 1.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));
 * <p>
 * if (!(movingobjectposition.entityHit instanceof EntityEnderman)) {
 * this.setDead();
 * }
 * } else {
 * this.motionX *= -0.10000000149011612D;
 * this.motionY *= -0.10000000149011612D;
 * this.motionZ *= -0.10000000149011612D;
 * this.rotationYaw += 180.0F;
 * this.prevRotationYaw += 180.0F;
 * this.ticksInAir = 0;
 * }
 * } else {
 * BlockPos blockpos1 = movingobjectposition.getBlockPos();
 * this.xTile = blockpos1.getX();
 * this.yTile = blockpos1.getY();
 * this.zTile = blockpos1.getZ();
 * iblockstate = this.worldObj.getBlockState(blockpos1);
 * this.inBlock = iblockstate.getBlock();
 * this.inData = this.inBlock.getMetaFromState(iblockstate);
 * this.motionX = (double)((float)(movingobjectposition.hitVec.xCoord - this.posX));
 * this.motionY = (double)((float)(movingobjectposition.hitVec.yCoord - this.posY));
 * this.motionZ = (double)((float)(movingobjectposition.hitVec.zCoord - this.posZ));
 * f3 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
 * this.posX -= this.motionX / (double)f3 * 0.05000000074505806D;
 * this.posY -= this.motionY / (double)f3 * 0.05000000074505806D;
 * this.posZ -= this.motionZ / (double)f3 * 0.05000000074505806D;
 * this.playSound("random.bowhit", 1.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));
 * this.inGround = true;
 * this.arrowShake = 7;
 * this.setIsCritical(false);
 * <p>
 * if (this.inBlock.getMaterial() != Material.air) {
 * this.inBlock.onEntityCollision(this.worldObj, blockpos1, iblockstate, this);
 * }
 * }
 * }
 * <p>
 * this.posX += this.motionX;
 * this.posY += this.motionY;
 * this.posZ += this.motionZ;
 * f2 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
 * this.rotationYaw = (float)(Math.atan2(this.motionX, this.motionZ) * 180.0D / Math.PI);
 * <p>
 * for (this.rotationPitch = (float)(Math.atan2(this.motionY, (double)f2) * 180.0D / Math.PI); this.rotationPitch - this.prevRotationPitch < -180.0F; this.prevRotationPitch -= 360.0F) {
 * ;
 * }
 * <p>
 * while (this.rotationPitch - this.prevRotationPitch >= 180.0F) {
 * this.prevRotationPitch += 360.0F;
 * }
 * <p>
 * while (this.rotationYaw - this.prevRotationYaw < -180.0F) {
 * this.prevRotationYaw -= 360.0F;
 * }
 * <p>
 * while (this.rotationYaw - this.prevRotationYaw >= 180.0F) {
 * this.prevRotationYaw += 360.0F;
 * }
 * <p>
 * this.rotationPitch = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * 0.2F;
 * this.rotationYaw = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * 0.2F;
 * f3 = 0.99F;
 * f1 = 0.05F;
 * <p>
 * if (this.isInWater()) {
 * for (int l = 0; l < 4; ++l) {
 * f4 = 0.25F;
 * this.worldObj.spawnParticle(EnumParticleTypes.WATER_BUBBLE, this.posX - this.motionX * (double)f4, this.posY - this.motionY * (double)f4, this.posZ - this.motionZ * (double)f4, this.motionX, this.motionY, this.motionZ, new int[0]);
 * }
 * f3 = 0.6F;
 * }
 * <p>
 * if (this.isWet()) {
 * this.extinguish();
 * }
 * <p>
 * this.motionX *= (double)f3;
 * this.motionY *= (double)f3;
 * this.motionZ *= (double)f3;
 * this.motionY -= (double)f1;
 * this.setPosition(this.posX, this.posY, this.posZ);
 * this.doBlockCollisions();
 * }
 * }
 * @Override public void writeEntityToNBT(NBTTagCompound tagCompound) {
 * tagCompound.setShort("xTile", (short)this.xTile);
 * tagCompound.setShort("yTile", (short)this.yTile);
 * tagCompound.setShort("zTile", (short)this.zTile);
 * tagCompound.setShort("life", (short)this.ticksInGround);
 * ResourceLocation resourcelocation = (ResourceLocation)Block.blockRegistry.getNameForObject(this.inBlock);
 * tagCompound.setString("inTile", resourcelocation == null ? "" : resourcelocation.toString());
 * tagCompound.setByte("inData", (byte)this.inData);
 * tagCompound.setByte("shake", (byte)this.arrowShake);
 * tagCompound.setByte("inGround", (byte)(this.inGround ? 1 : 0));
 * tagCompound.setByte("pickup", (byte)this.canBePickedUp);
 * tagCompound.setDouble("damage", this.damage);
 * }
 * @Override public void readEntityFromNBT(NBTTagCompound tagCompund) {
 * this.xTile = tagCompund.getShort("xTile");
 * this.yTile = tagCompund.getShort("yTile");
 * this.zTile = tagCompund.getShort("zTile");
 * this.ticksInGround = tagCompund.getShort("life");
 * <p>
 * if (tagCompund.hasKey("inTile", 8)) {
 * this.inBlock = Block.getBlockFromName(tagCompund.getString("inTile"));
 * } else {
 * this.inBlock = Block.getBlockById(tagCompund.getByte("inTile") & 255);
 * }
 * <p>
 * this.inData = tagCompund.getByte("inData") & 255;
 * this.arrowShake = tagCompund.getByte("shake") & 255;
 * this.inGround = tagCompund.getByte("inGround") == 1;
 * <p>
 * if (tagCompund.hasKey("damage", 99)) {
 * this.damage = tagCompund.getDouble("damage");
 * }
 * <p>
 * if (tagCompund.hasKey("pickup", 99)) {
 * this.canBePickedUp = tagCompund.getByte("pickup");
 * }
 * else if (tagCompund.hasKey("player", 99)) {
 * this.canBePickedUp = tagCompund.getBoolean("player") ? 1 : 0;
 * }
 * }
 * @Override public void onCollideWithPlayer(EntityPlayer entityIn) {
 * if (!this.worldObj.isRemote && this.inGround && this.arrowShake <= 0) {
 * boolean flag = this.canBePickedUp == 1 || this.canBePickedUp == 2 && entityIn.capabilities.isCreativeMode;
 * <p>
 * if (this.canBePickedUp == 1 && !entityIn.inventory.addItemStackToInventory(new ItemStack(JourneyItems.royalKnife, 1))) {
 * flag = false;
 * }
 * <p>
 * if (flag) {
 * this.playSound("random.pop", 0.2F, ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
 * entityIn.onItemPickup(this, 1);
 * this.setDead();
 * }
 * }
 * }
 * @Override protected boolean canTriggerWalking() {
 * return false;
 * }
 * <p>
 * public void setDamage(double d) {
 * this.damage = d;
 * }
 * <p>
 * public double getDamage() {
 * return this.damage;
 * }
 * <p>
 * public void setKnockbackStrength(int k) {
 * this.knockbackStrength = k;
 * }
 * @Override public boolean canAttackWithItem() {
 * return false;
 * }
 * <p>
 * public void setIsCritical(boolean c) {
 * byte b0 = this.dataWatcher.getWatchableObjectByte(16);
 * <p>
 * if(c) {
 * this.dataWatcher.updateObject(16, Byte.valueOf((byte)(b0 | 1)));
 * } else {
 * this.dataWatcher.updateObject(16, Byte.valueOf((byte)(b0 & -2)));
 * }
 * }
 * <p>
 * public boolean getIsCritical() {
 * byte b0 = this.dataWatcher.getWatchableObjectByte(16);
 * return (b0 & 1) != 0;
 * }
 * @Override protected void onImpact(MovingObjectPosition p_70184_1_) {
 * // TODO Auto-generated method stub
 * <p>
 * }
 * }
 */

