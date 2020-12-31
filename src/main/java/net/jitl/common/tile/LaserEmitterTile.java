package net.jitl.common.tile;

import net.jitl.init.JTiles;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import ru.timeconqueror.timecore.api.util.RandHelper;

public class LaserEmitterTile extends TileEntity {
    public double laserAngle = RandHelper.RAND.nextDouble() * 360;

    public LaserEmitterTile() {
        super(JTiles.LASER_EMITTER);
    }

    public double getLaserAngle() {
        return laserAngle;
    }

    @Override
    public AxisAlignedBB getRenderBoundingBox() {
        return INFINITE_EXTENT_AABB;
    }
}
