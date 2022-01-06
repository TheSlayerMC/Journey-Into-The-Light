package net.jitl.common.world.gen.util;

import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;

import net.minecraft.core.BlockPos.MutableBlockPos;

public class BoundMutablePos extends MutableBlockPos {
    private final MutableBlockPos bound;

    public BoundMutablePos() {
        bound = new MutableBlockPos(0, 0, 0);
    }

    public BoundMutablePos(Vec3i vec) {
        this(vec.getX(), vec.getY(), vec.getZ());
    }

    public BoundMutablePos(int x, int y, int z) {
        super(x, y, z);
        bound = new MutableBlockPos(x, y, z);
    }

    public BoundMutablePos bindAndSet(Vec3i vec) {
        bound.set(vec);
        set(vec);
        return this;
    }

    public BoundMutablePos bindAndSet(int x, int y, int z) {
        bound.set(x, y, z);
        set(x, y, z);
        return this;
    }

    public BoundMutablePos moveFromBound(int x, int y, int z) {
        set(bound.getX() + x, bound.getY() + y, bound.getZ() + z);
        return this;
    }

    public BoundMutablePos moveFromBound(Vec3i vec) {
        return moveFromBound(vec.getX(), vec.getY(), vec.getZ());
    }

    public BoundMutablePos moveFromBound(Direction offset, int n) {
        set(bound.getX() + offset.getStepX() * n, bound.getY() + offset.getStepY() * n, bound.getZ() + offset.getStepZ() * n);
        return this;
    }
}