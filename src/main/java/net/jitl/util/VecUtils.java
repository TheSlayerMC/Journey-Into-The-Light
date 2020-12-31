package net.jitl.util;

import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3i;

//TODO move to TimeCore
public class VecUtils {
    public static Vector3d vec3d(Vector3i vector3i) {
        return new Vector3d(vector3i.getX(), vector3i.getY(), vector3i.getZ());
    }
}
