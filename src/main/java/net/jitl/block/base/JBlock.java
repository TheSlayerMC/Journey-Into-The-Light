package net.jitl.block.base;

import net.jitl.init.JourneyTabs;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.ItemGroup;

public class JBlock extends Block {

    public JBlock(EnumMaterialTypes blockType, String enName, float hardness, JourneyTabs tab) {
        super(AbstractBlock.Properties.create(blockType.getMaterial()).hardnessAndResistance(hardness).sound(blockType.getSound()));
    }

    public JBlock(String enName, float hardness) {
        this(EnumMaterialTypes.STONE, enName, hardness, JourneyTabs.blocks);
    }

    public JBlock(String enName) {
        this(EnumMaterialTypes.STONE, enName, JourneyTabs.blocks);
    }

    public JBlock(EnumMaterialTypes type, String enName, float hardness) {
        this(type,  enName, hardness, JourneyTabs.blocks);
    }

    public JBlock(String enName, boolean breakable, JourneyTabs tab) {
        this(EnumMaterialTypes.STONE, enName, tab);
    }

    public JBlock(String enName, boolean breakable) {
        this(enName, breakable, JourneyTabs.blocks);
    }

    public JBlock(EnumMaterialTypes blockType, String enName, JourneyTabs tab) {
        this(blockType, enName, 2.0F, tab);
    }
}
