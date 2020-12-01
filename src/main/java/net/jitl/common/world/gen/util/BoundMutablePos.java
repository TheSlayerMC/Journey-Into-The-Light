package net.jitl.common.world.gen.util;

import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3i;

public class BoundMutablePos extends BlockPos.Mutable {
    private final Mutable bound;

    public BoundMutablePos() {
        bound = new Mutable(0, 0, 0);
    }

    public BoundMutablePos(Vector3i vec) {
        this(vec.getX(), vec.getY(), vec.getZ());
    }

    public BoundMutablePos(int x, int y, int z) {
        super(x, y, z);
        bound = new Mutable(x, y, z);
    }

    public BoundMutablePos bindAndSet(Vector3i vec) {
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

    public BoundMutablePos moveFromBound(Vector3i vec) {
        return moveFromBound(vec.getX(), vec.getY(), vec.getZ());
    }

    public BoundMutablePos moveFromBound(Direction offset, int n) {
        set(bound.getX() + offset.getStepX() * n, bound.getY() + offset.getStepY() * n, bound.getZ() + offset.getStepZ() * n);
        return this;
    }
}