package net.jitl.common.tile;

import net.jitl.init.JTiles;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import ru.timeconqueror.timecore.api.common.tile.SyncableTile;

public class GuardianTowerBrainTile extends SyncableTile {
    public GuardianTowerBrainTile(BlockPos pos, BlockState state) {
        super(JTiles.GUARDIAN_TOWER_BRAIN, pos, state);
    }

}
