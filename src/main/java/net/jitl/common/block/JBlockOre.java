package net.jitl.common.block;

import net.jitl.common.block.base.JBlock;
import net.jitl.common.helper.EnumMaterialTypes;

public class JBlockOre extends JBlock {

    public JBlockOre(String enName, float hardness) {
        super(EnumMaterialTypes.STONE, enName, hardness);
    }

    public JBlockOre(String enName) {
        this(enName, 2.0F);
    }
}
