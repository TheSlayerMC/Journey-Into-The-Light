package net.jitl.common.tile;

import net.jitl.common.tile.base.InitableTile;
import net.jitl.init.JTiles;
import net.jitl.util.VecUtils;
import net.minecraft.block.BlockState;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.util.math.*;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import ru.timeconqueror.timecore.api.common.tile.SerializationType;

public class LaserEmitterTile extends InitableTile implements ITickableTileEntity {
    private static final int MAX_DISTANCE = 20;
    private static final Vector3d BLOCK_CENTER = new Vector3d(0.5, 0.5, 0.5);
    private long activationTime;
    public float laserAngle;
    private final float speed = 0.9F;

    public LaserEmitterTile() {
        super(JTiles.LASER_EMITTER);
    }

    @Override
    public void init() {
        activationTime = level.getGameTime();
    }

    @Override
    public void tick() {
        this.laserAngle = MathHelper.wrapDegrees(laserAngle + speed);

        Quaternion rotationQuaternion = Vector3f.YP.rotationDegrees(laserAngle);
        Vector3f beamEndVec = new Vector3f(MAX_DISTANCE, 0.5F, 0);
        beamEndVec.transform(rotationQuaternion);

        Vector3d posVec = VecUtils.vec3d(getBlockPos());

        Vector3f beamStartVec = new Vector3f(0.5F, 0, 0);
        beamStartVec.transform(rotationQuaternion);
        VecUtils.addToFirst(beamStartVec, BLOCK_CENTER);

        Vector3f blockSide = new Vector3f(1F, 0, 0);
        blockSide.transform(rotationQuaternion);
        VecUtils.cubify(blockSide, 1, 1, 1);
        VecUtils.addToFirst(blockSide, BLOCK_CENTER);

        Vector3d start = VecUtils.add(posVec, blockSide);
        Vector3d end = VecUtils.add(posVec, beamEndVec);

        BlockRayTraceResult rayTraceResult = level.clip(new RayTraceContext(start, end, RayTraceContext.BlockMode.VISUAL, RayTraceContext.FluidMode.NONE, null));

        Vector3d rayTraceLoc = rayTraceResult.getLocation();

        //noinspection ConstantConditions
        EntityRayTraceResult result = ProjectileHelper.getEntityHitResult(level, null, start, rayTraceLoc, new AxisAlignedBB(getBlockPos().getX(), getBlockPos().getY(), getBlockPos().getZ(), rayTraceLoc.x, rayTraceLoc.y, rayTraceLoc.z), entity -> true);
        if (result != null) {
            System.out.println("Detected: " + result.getEntity().getName().toString());
        }
    }

    @Override
    protected void writeNBT(CompoundNBT nbt, SerializationType type) {
        super.writeNBT(nbt, type);

        nbt.putLong("activation_time", activationTime);
    }

    @Override
    protected void readNBT(BlockState state, CompoundNBT nbt, SerializationType type) {
        super.readNBT(state, nbt, type);

        activationTime = nbt.getLong("activation_time");
    }

    public float getLaserAngle() {
        long gameTime = level.getGameTime();
        long delta = gameTime - activationTime;
        return MathHelper.wrapDegrees(delta * speed);
    }

    @Override
    public AxisAlignedBB getRenderBoundingBox() {
        return INFINITE_EXTENT_AABB;
    }
}
