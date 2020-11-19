package net.jitl.common.block;

import net.jitl.common.tile.LaserEmitterTile;
import net.minecraft.block.ContainerBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;
import org.jetbrains.annotations.Nullable;

public class LaserEmitterBlock extends ContainerBlock {
    public LaserEmitterBlock(Properties builder) {
        super(builder);
    }

    @Nullable
    @Override
    public TileEntity newBlockEntity(IBlockReader worldIn) {
        return new LaserEmitterTile();
    }
}
