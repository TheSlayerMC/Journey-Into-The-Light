package net.jitl.common.tile;

import net.jitl.init.JTiles;
import net.minecraft.tileentity.ITickableTileEntity;
import ru.timeconqueror.timecore.api.common.tile.SyncableTile;

public class GuardianTowerHeartTile extends SyncableTile implements ITickableTileEntity {
    public GuardianTowerHeartTile() {
        super(JTiles.GUARDIAN_TOWER_HEART);
    }

    @Override
    public void tick() {

    }
}
