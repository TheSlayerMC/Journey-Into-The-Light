package net.jitl.util.calculation;

import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.World;
import ru.timeconqueror.timecore.api.util.VecUtils;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class BeamCalculation {
    private static final Vector3d BLOCK_CENTER = new Vector3d(0.5, 0.5, 0.5);

    /**
     * Calculates beam data till the first found block, through which the beam won't pass.
     *
     * @param world             world in which beam should be calculated
     * @param sourcePos         position of block from which beam will be emitted
     * @param beamRelativeStart beam start relatively to the block center
     * @param rotation          beam rotation
     * @param beamMaxLength     max beam length in blocks
     */
    public static TillBlockResult tillBlock(World world, BlockPos sourcePos, Vector3d beamRelativeStart, Quaternion rotation, int beamMaxLength) {
        Vector3f beamStart = new Vector3f(beamRelativeStart);
        beamStart.transform(rotation);

        Vector3f rayTraceStart = beamStart.copy();
        VecUtils.cubify(rayTraceStart, 1, 1, 1);

        Vector3f beamEnd = new Vector3f((float) (beamRelativeStart.x() + beamMaxLength), (float) beamRelativeStart.y(), (float) beamRelativeStart.z());
        beamEnd.transform(rotation);

        VecUtils.addToFirst(beamStart, BLOCK_CENTER);
        VecUtils.addToFirst(rayTraceStart, BLOCK_CENTER);
        VecUtils.addToFirst(beamEnd, BLOCK_CENTER);

        Vector3d blockPos = VecUtils.vec3d(sourcePos);
        Vector3d absRayTraceStart = VecUtils.add(blockPos, rayTraceStart);

        BlockRayTraceResult rayTraceResult = world.clip(new RayTraceContext(absRayTraceStart, VecUtils.add(blockPos, beamEnd), RayTraceContext.BlockMode.VISUAL, RayTraceContext.FluidMode.NONE, null));
        Vector3d absRayTraceEnd = rayTraceResult.getLocation();

        return new TillBlockResult(beamStart, absRayTraceStart, absRayTraceEnd, sourcePos);
    }

    public static void forAllEntitiesOnWay(World world, TillBlockResult beamTillBlock, Predicate<Entity> filter, Consumer<Entity> action) {
        Vector3d rayTraceStart = beamTillBlock.getRayTraceStart();
        Vector3d rayTraceEnd = beamTillBlock.getRayTraceEnd();

        raytraceEntitiesOnWay(world,
                rayTraceStart,
                rayTraceEnd,
                new AxisAlignedBB(rayTraceStart.x(),
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
    private static void raytraceEntitiesOnWay(World worldIn, Vector3d startVec, Vector3d endVec, AxisAlignedBB boundingBox, Predicate<Entity> filter, Consumer<Entity> doForEveryEntity) {
        for (Entity entity : worldIn.getEntities((Entity) null, boundingBox, filter)) {
            AxisAlignedBB axisalignedbb = entity.getBoundingBox();
            Optional<Vector3d> optional = axisalignedbb.clip(startVec, endVec);
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
        private final Vector3d rayTraceStart;
        /**
         * End of raytrace, the actual beam end.
         */
        private final Vector3d rayTraceEnd;

        private final BlockPos sourcePos;

        public TillBlockResult(Vector3f beamStart, Vector3d rayTraceStart, Vector3d rayTraceEnd, BlockPos sourcePos) {
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

        public Vector3d getRayTraceEnd() {
            return rayTraceEnd;
        }

        public Vector3d getRayTraceStart() {
            return rayTraceStart;
        }

        public BlockPos getSourcePos() {
            return sourcePos;
        }
    }
}
