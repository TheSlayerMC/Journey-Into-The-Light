package net.jitl.common.block.base;

import net.jitl.common.tile.base.JFurnaceTile;
import net.jitl.core.init.JTiles;
import net.minecraft.core.BlockPos;
import net.minecraft.stats.Stats;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class JFurnaceBlock extends AbstractFurnaceBlock {

    public JFurnaceBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected void openContainer(Level l, BlockPos b, Player p) {
        BlockEntity e = l.getBlockEntity(b);
        if(e instanceof JFurnaceTile) {
            p.openMenu((MenuProvider)e);
            p.awardStat(Stats.INTERACT_WITH_FURNACE);
        }
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos p, BlockState s) {
        return new JFurnaceTile(p, s);
    }

    @Override
    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level l, BlockState s, BlockEntityType<T> e) {
        return createFurnaceTicker(l, e, JTiles.JFURNACE);
    }
}
