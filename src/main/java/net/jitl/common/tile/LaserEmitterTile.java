package net.jitl.common.tile;

import net.jitl.common.tile.base.InitableTile;
import net.jitl.init.JTiles;
import net.jitl.util.calculation.BeamCalculation;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import ru.timeconqueror.timecore.api.common.tile.SerializationType;

//TODO beam burning particles near beam end vector
public class LaserEmitterTile extends InitableTile implements ITickableTileEntity {
    public static final int MAX_DISTANCE = 20;
    public static final Vector3d BEAM_OFFSET = new Vector3d(0.5, 0, 0);

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
        this.laserAngle = MathHelper.wrapDegrees(getLaserAngle() + speed);

        BeamCalculation.TillBlockResult beamTillBlock = BeamCalculation.tillBlock(level, getBlockPos(), BEAM_OFFSET, getLaserRotation(0), MAX_DISTANCE);

        BeamCalculation.forAllEntitiesOnWay(level, beamTillBlock, entity -> true, entity -> {
            System.out.println("Found!");
        });
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

    public Quaternion getLaserRotation(float partialTicks) {
        return Vector3f.YP.rotationDegrees(getLaserAngle() + partialTicks);
    }
}
