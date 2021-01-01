package net.jitl.util;

import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.util.math.vector.Vector3i;
import ru.timeconqueror.timecore.api.util.MathUtils;

//TODO move to TimeCore

/**
 * All methods here work on both sides
 */
public class VecUtils {
    /**
     * Coerces {@code direction} vector from region's center within its size.
     * <p>
     * (c) Written by mafs genius Socolio
     */
    public static void cubify(Vector3f direction, float xSize, float ySize, float zSize) {
        float scaleX = (xSize / 2) / Math.abs(direction.x());
        float scaleY = (ySize / 2) / Math.abs(direction.y());
        float scaleZ = (zSize / 2) / Math.abs(direction.z());
        float scale = MathUtils.min(scaleX, scaleY, scaleZ);
        scaleFirst(direction, scale, scale, scale);
    }

    public static Vector3d vec3d(Vector3i vector3i) {
        return new Vector3d(vector3i.getX(), vector3i.getY(), vector3i.getZ());
    }

    public static Vector3d vec3d(Vector3f vector3f) {
        return new Vector3d(vector3f.x(), vector3f.y(), vector3f.z());
    }

    public static Vector3d add(Vector3d vector3d, Vector3f vector3f) {
        return vector3d.add(vector3f.x(), vector3f.y(), vector3f.z());
    }

    public static Vector3d add(Vector3d vector3d, Vector3i vector3i) {
        return vector3d.add(vector3i.getX(), vector3i.getY(), vector3i.getZ());
    }

    public static Vector3d subtract(Vector3d vector3d, Vector3f vector3f) {
        return vector3d.subtract(vector3f.x(), vector3f.y(), vector3f.z());
    }

    public static Vector3d subtract(Vector3d vector3d, Vector3i vector3i) {
        return vector3d.subtract(vector3i.getX(), vector3i.getY(), vector3i.getZ());
    }

    public static void addToFirst(Vector3f first, Vector3d second) {
        first.set(first.x() + (float) second.x, first.y() + (float) second.y, first.z() + (float) second.z);
    }

    public static void subtractToFirst(Vector3f first, Vector3d second) {
        first.set(first.x() - (float) second.x, first.y() - (float) second.y, first.z() - (float) second.z);
    }

    public static void scaleFirst(Vector3f first, float x, float y, float z) {
        first.set(first.x() * x, first.y() * y, first.z() * z);
    }
}
