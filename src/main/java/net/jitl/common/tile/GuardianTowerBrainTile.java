package net.jitl.common.tile;

import net.jitl.init.JTiles;
import net.minecraft.world.level.block.entity.TickableBlockEntity;
import ru.timeconqueror.timecore.api.common.tile.SyncableTile;

public class GuardianTowerBrainTile extends SyncableTile implements TickableBlockEntity {
    public GuardianTowerBrainTile() {
        super(JTiles.GUARDIAN_TOWER_BRAIN);
    }

    @Override
    public void tick() {

    }
}
