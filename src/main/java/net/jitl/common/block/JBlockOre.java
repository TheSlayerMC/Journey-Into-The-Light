package net.jitl.common.block;

import net.jitl.common.block.base.JBlock;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;

public class JBlockOre extends JBlock {
    public JBlockOre(Properties properties) {
        super(properties);
    }

//    public JBlockOre(String enName, float hardness) {
//        super(EnumMaterialTypes.STONE);
//    }
//
//    public JBlockOre(String enName) {
//        this(enName, 2.0F);
//    }

    @Override
    public int getExpDrop(BlockState state, IWorldReader world, BlockPos pos, int fortune, int silktouch) {
        return 0;
    }
}
