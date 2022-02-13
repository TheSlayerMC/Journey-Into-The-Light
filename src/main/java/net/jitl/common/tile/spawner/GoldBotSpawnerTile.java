package net.jitl.common.tile.spawner;

import net.jitl.common.tile.JMobSpawnerTile;
import net.jitl.core.init.JTiles;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class GoldBotSpawnerTile extends JMobSpawnerTile {

    public GoldBotSpawnerTile(BlockPos pos, BlockState state) {
        super(pos, state, JTiles.GOLD_BOT_SPAWNER);
    }
}
