package net.jitl.common.tile;

import net.jitl.init.JTiles;
import net.minecraft.tileentity.ITickableTileEntity;
import ru.timeconqueror.timecore.api.common.tile.SyncableTile;

public class GuardianTowerBrainTile extends SyncableTile implements ITickableTileEntity {
    public GuardianTowerBrainTile() {
        super(JTiles.GUARDIAN_TOWER_BRAIN);
    }

    @Override
    public void tick() {

    }
}
