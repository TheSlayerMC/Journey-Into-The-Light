package net.jitl.util.calculation;

import net.minecraft.world.entity.Entity;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.world.phys.AABB;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.level.ClipContext;
import com.mojang.math.Quaternion;
import net.minecraft.world.phys.Vec3;
import com.mojang.math.Vector3f;
import net.minecraft.world.level.Level;
import ru.timeconqueror.timecore.api.util.VecUtils;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;

import Vec3;

public class BeamCalculation {
    private static final Vec3 BLOCK_CENTER = new Vec3(0.5, 0.5, 0.5);

    /**
     * Calculates beam data till the first found block, through which the beam won't pass.
     *
     * @param world             world in which beam should be calculated
     * @param sourcePos         position of block from which beam will be emitted
     * @param beamRelativeStart beam start relatively to the block center
     * @param rotation          beam rotation
     * @param beamMaxLength     max beam length in blocks
     */
    public static TillBlockResult tillBlock(Level world, BlockPos sourcePos, Vec3 beamRelativeStart, Quaternion rotation, int beamMaxLength) {
        Vector3f beamStart = new Vector3f(beamRelativeStart);
        beamStart.transform(rotation);

        Vector3f rayTraceStart = beamStart.copy();
        VecUtils.cubify(rayTraceStart, 1, 1, 1);

        Vector3f beamEnd = new Vector3f((float) (beamRelativeStart.x() + beamMaxLength), (float) beamRelativeStart.y(), (float) beamRelativeStart.z());
        beamEnd.transform(rotation);

        VecUtils.addToFirst(beamStart, BLOCK_CENTER);
        VecUtils.addToFirst(rayTraceStart, BLOCK_CENTER);
        VecUtils.addToFirst(beamEnd, BLOCK_CENTER);

        Vec3 blockPos = VecUtils.vec3d(sourcePos);
        Vec3 absRayTraceStart = VecUtils.add(blockPos, rayTraceStart);

        BlockHitResult rayTraceResult = world.clip(new ClipContext(absRayTraceStart, VecUtils.add(blockPos, beamEnd), ClipContext.Block.VISUAL, ClipContext.Fluid.NONE, null));
        Vec3 absRayTraceEnd = rayTraceResult.getLocation();

        return new TillBlockResult(beamStart, absRayTraceStart, absRayTraceEnd, sourcePos);
    }

    public static void forAllEntitiesOnWay(Level world, TillBlockResult beamTillBlock, Predicate<Entity> filter, Consumer<Entity> action) {
        Vec3 rayTraceStart = beamTillBlock.getRayTraceStart();
        Vec3 rayTraceEnd = beamTillBlock.getRayTraceEnd();

        raytraceEntitiesOnWay(world,
                rayTraceStart,
                rayTraceEnd,
                new AABB(rayTraceStart.x(),
                        rayTraceStart.y(),
                        rayTraceStart.z(),
                        rayTraceEnd.x(),
                        rayTraceEnd.y(),
                        rayTraceEnd.z()),
                filter,
                action);
    }


    /**
     * Modified version of {@link ProjectileHelper#getEntityHitResult(World, Entity, Vector3d, Vector3d, AxisAlignedBB, Predicate)}
     */
    private static void raytraceEntitiesOnWay(Level worldIn, Vec3 startVec, Vec3 endVec, AABB boundingBox, Predicate<Entity> filter, Consumer<Entity> doForEveryEntity) {
        for (Entity entity : worldIn.getEntities((Entity) null, boundingBox, filter)) {
            AABB axisalignedbb = entity.getBoundingBox();
            Optional<Vec3> optional = axisalignedbb.clip(startVec, endVec);
            if (optional.isPresent() || axisalignedbb.contains(startVec) || axisalignedbb.contains(endVec)) {
                doForEveryEntity.accept(entity);
            }
        }
    }

    /**
     * Result of raytrace till block.
     * All values are final and won't be change inside later, so fill free to use it as you want.
     */
    public static class TillBlockResult {
        /**
         * Real beam start.
         * Can be used as start point at drawing beam.
         * <p>
         * Warning: It's relative to the {@link #sourcePos}, it's not absolute!
         */
        private final Vector3f beamStart;
        /**
         * Since raytrace can't normally raytrace blocks, when the block from which it was started satisfies raytrace stop condition,
         * we need to start raytrace from the next block on our path.
         * Our raytrace process starts at this point.
         * This field can be considered as raytrace end vector till the first neighbour block.
         */
        private final Vec3 rayTraceStart;
        /**
         * End of raytrace, the actual beam end.
         */
        private final Vec3 rayTraceEnd;

        private final BlockPos sourcePos;

        public TillBlockResult(Vector3f beamStart, Vec3 rayTraceStart, Vec3 rayTraceEnd, BlockPos sourcePos) {
            this.beamStart = beamStart;
            this.rayTraceStart = rayTraceStart;
            this.rayTraceEnd = rayTraceEnd;
            this.sourcePos = sourcePos;
        }

        public Vector3f getRelBeamStart() {
            return beamStart;
        }

        public Vector3f getBeamStart() {
            Vector3f copy = beamStart.copy();
            BlockPos pos = getSourcePos();
            copy.add(pos.getX(), pos.getY(), pos.getZ());
            return copy;
        }

        public Vec3 getRayTraceEnd() {
            return rayTraceEnd;
        }

        public Vec3 getRayTraceStart() {
            return rayTraceStart;
        }

        public BlockPos getSourcePos() {
            return sourcePos;
        }
    }
}
