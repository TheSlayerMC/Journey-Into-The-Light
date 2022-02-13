package net.jitl.common.tile.spawner;

import net.jitl.common.tile.JMobSpawnerTile;
import net.jitl.core.init.JTiles;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class MiniGhastSpawnerTile extends JMobSpawnerTile {

    public MiniGhastSpawnerTile(BlockPos pos, BlockState state) {
        super(pos, state, JTiles.MINI_GHAST_SPAWNER);
    }
}