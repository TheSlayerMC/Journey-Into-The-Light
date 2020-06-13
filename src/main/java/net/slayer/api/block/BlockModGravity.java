package net.slayer.api.block;

import net.journey.JITL;
import net.journey.init.JourneyTabs;
import net.journey.init.blocks.JourneyBlocks;
import net.journey.util.gen.lang.LangGeneratorFacade;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.EnumMaterialTypes;
import net.slayer.api.EnumToolType;
import net.slayer.api.SlayerAPI;

import java.util.Random;

public class BlockModGravity extends BlockFalling {
    protected EnumMaterialTypes blockType;
    protected Item drop = null;

    public BlockModGravity(String name, String finalName, float hardness) {
        this(EnumMaterialTypes.STONE, name, finalName, hardness, JourneyTabs.BLOCKS);
    }

    public BlockModGravity(String name, String finalName) {
        this(EnumMaterialTypes.STONE, name, finalName, 2.0F, JourneyTabs.BLOCKS);
    }

    public BlockModGravity(EnumMaterialTypes type, String name, String finalName, float hardness) {
        this(type, name, finalName, hardness, JourneyTabs.BLOCKS);
    }

    public BlockModGravity(String name, String finalName, boolean breakable, CreativeTabs tab) {
        this(EnumMaterialTypes.STONE, name, finalName, tab);
    }

    public BlockModGravity(String name, String finalName, boolean breakable) {
        this(name, finalName, breakable, JourneyTabs.BLOCKS);
    }

    public BlockModGravity(EnumMaterialTypes blockType, String name, String finalName, CreativeTabs tab) {
        super(blockType.getMaterial());
        this.blockType = blockType;
        setHardness(2.0F);
        setSoundType(blockType.getSound());
        setCreativeTab(tab);
        setTranslationKey(name);
        JourneyBlocks.blocks.add(this);
        setRegistryName(JITL.MOD_ID, name);
        LangGeneratorFacade.addBlockEntry(this, finalName);
        JourneyBlocks.itemBlocks.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }

    public BlockModGravity(EnumMaterialTypes blockType, String name, String finalName, float hardness, CreativeTabs tab) {
        super(blockType.getMaterial());
        this.blockType = blockType;
        setSoundType(blockType.getSound());
        setCreativeTab(tab);
        setTranslationKey(name);
        setHardness(hardness);
        JourneyBlocks.blocks.add(this);
        setRegistryName(JITL.MOD_ID, name);
        LangGeneratorFacade.addBlockEntry(this, finalName);
        JourneyBlocks.itemBlocks.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        if (drop == null) return SlayerAPI.toItem(this);
        return drop;
    }

    public BlockModGravity setHarvestLevel(EnumToolType type) {
        setHarvestLevel(type.getType(), type.getLevel());
        return this;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    public int quantityDropped(Random rand) {
        return 1;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
    }
}
