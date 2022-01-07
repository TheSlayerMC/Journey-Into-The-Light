package net.jitl.common.tile;

import com.mojang.math.Quaternion;
import com.mojang.math.Vector3f;
import net.jitl.common.tile.base.InitableTile;
import net.jitl.init.JTiles;
import net.jitl.util.calculation.BeamCalculation;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import ru.timeconqueror.timecore.api.common.tile.SerializationType;

//TODO beam burning particles near beam end vector
public class LaserEmitterTile extends InitableTile {
    public static final int MAX_DISTANCE = 20;
    public static final Vec3 BEAM_OFFSET = new Vec3(0.5, 0, 0);

    private long activationTime;
    private final float speed = 0.9F;

    public LaserEmitterTile(BlockPos pos, BlockState state) {
        super(JTiles.LASER_EMITTER, pos, state);
    }

    @Override
    public void init() {
        activationTime = level.getGameTime();
    }

    public void serverTick(Level level, BlockPos pos, BlockState state_, LaserEmitterTile blockEntity_) {
        BeamCalculation.TillBlockResult beamTillBlock = BeamCalculation.tillBlock(level, pos, BEAM_OFFSET, getLaserRotation(0), MAX_DISTANCE);

        BeamCalculation.forAllEntitiesOnWay(level, beamTillBlock, entity -> true, entity -> {
            System.out.println("Found!");
        });
    }

    @Override
    protected void writeNBT(CompoundTag nbt, SerializationType type) {
        super.writeNBT(nbt, type);
        nbt.putLong("activation_time", activationTime);
    }

    @Override
    protected void readNBT(CompoundTag nbt, SerializationType type) {
        super.readNBT(nbt, type);

        activationTime = nbt.getLong("activation_time");
    }

    public float getLaserAngle() {
        long gameTime = level.getGameTime();
        long delta = gameTime - activationTime;
        return Mth.wrapDegrees(delta * speed);
    }

    @Override
    public AABB getRenderBoundingBox() {
        return INFINITE_EXTENT_AABB;
    }

    public Quaternion getLaserRotation(float partialTicks) {
        return Vector3f.YP.rotationDegrees(getLaserAngle() + speed * partialTicks);
    }
}
