package net.jitl.block;

import net.jitl.block.base.JBlock;
import net.jitl.helper.EnumMaterialTypes;
import net.minecraft.item.ItemGroup;

public class JBlockOre extends JBlock {

    public JBlockOre(String enName, float hardness) {
        super(EnumMaterialTypes.STONE, enName, hardness);
    }

    public JBlockOre(String enName) {
        this(enName, 2.0F);
    }
}
