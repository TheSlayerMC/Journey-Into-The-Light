package net.jitl.common.tile;

import net.jitl.core.init.JTiles;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class ObeliskTile extends BlockEntity {

    public ObeliskTile(BlockPos pos, BlockState state) {
        super(JTiles.OBELISK, pos, state);
    }
}
