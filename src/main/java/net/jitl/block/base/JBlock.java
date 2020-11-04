package net.jitl.block.base;

import net.jitl.helper.EnumMaterialTypes;
import net.jitl.init.JourneyTabs;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.ItemGroup;

public class JBlock extends Block {

    public JBlock(EnumMaterialTypes blockType, String enName, float hardness, ItemGroup tab) {
        super(AbstractBlock.Properties.of(blockType.getMaterial(), blockType.getMaterial().getColor()).strength(hardness).sound(blockType.getSound()));
    }

    public JBlock(String enName, float hardness) {
        this(EnumMaterialTypes.STONE, enName, hardness, JourneyTabs.BLOCKS);
    }

    public JBlock(String enName) {
        this(EnumMaterialTypes.STONE, enName, JourneyTabs.BLOCKS);
    }

    public JBlock(EnumMaterialTypes type, String enName, float hardness) {
        this(type,  enName, hardness, JourneyTabs.BLOCKS);
    }

    public JBlock(String enName, boolean breakable, ItemGroup tab) {
        this(EnumMaterialTypes.STONE, enName, tab);
    }

    public JBlock(String enName, boolean breakable) {
        this(enName, breakable, JourneyTabs.BLOCKS);
    }

    public JBlock(EnumMaterialTypes blockType, String enName, ItemGroup tab) {
        this(blockType, enName, 2.0F, tab);
    }
}