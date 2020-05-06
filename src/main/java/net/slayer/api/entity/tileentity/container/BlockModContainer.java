package net.slayer.api.entity.tileentity.container;

import net.journey.JITL;
import net.journey.init.JourneyTabs;
import net.journey.init.blocks.JourneyBlocks;
import net.journey.init.items.JourneyItems;
import net.journey.util.gen.lang.LangGeneratorFacade;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.EnumMaterialTypes;
import net.slayer.api.EnumToolType;
import net.slayer.api.SlayerAPI;

import java.util.Random;

public abstract class BlockModContainer extends BlockContainer {

    public int boostBrightnessLow;
    public int boostBrightnessHigh;
    public boolean enhanceBrightness;
    public String name;
    protected EnumMaterialTypes blockType;
    protected Item drop = null;
    protected Random rand;
    protected boolean isOpaque = true, isNormalCube = true;

    public BlockModContainer(String name, String finalName, float hardness) {
        this(EnumMaterialTypes.STONE, name, finalName, hardness, JourneyTabs.BLOCKS);
    }

    public BlockModContainer(String name, String finalName) {
        this(EnumMaterialTypes.STONE, name, finalName, 2.0F, JourneyTabs.BLOCKS);
    }

    public BlockModContainer(EnumMaterialTypes type, String name, String finalName, float hardness) {
        this(type, name, finalName, hardness, JourneyTabs.BLOCKS);
    }

    public BlockModContainer(String name, String finalName, boolean breakable, CreativeTabs tab) {
        this(EnumMaterialTypes.STONE, name, finalName, tab);
    }

    public BlockModContainer(String name, String finalName, boolean breakable) {
        this(name, finalName, breakable, JourneyTabs.BLOCKS);
    }

    public BlockModContainer(EnumMaterialTypes blockType, String name, String finalName, CreativeTabs tab) {
        super(blockType.getMaterial());
        this.blockType = blockType;
        setHardness(2.0F);
        rand = new Random();
        setSoundType(blockType.getSound());
        setCreativeTab(tab);
        setTranslationKey(name);
        this.name = name;
        JourneyBlocks.blocks.add(this);
	    JourneyBlocks.blockName.add(SlayerAPI.PREFIX + name);
	    setRegistryName(JITL.MOD_ID, name);
	    LangGeneratorFacade.addBlockEntry(this, finalName);
        JourneyItems.items.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }

    public BlockModContainer(EnumMaterialTypes blockType, String name, String finalName, float hardness, CreativeTabs tab) {
        super(blockType.getMaterial());
        this.blockType = blockType;
        rand = new Random();
        setSoundType(blockType.getSound());
        setCreativeTab(tab);
        setTranslationKey(name);
        setHardness(hardness);
        this.name = name;
        JourneyBlocks.blockName.add(SlayerAPI.PREFIX + name);
	    JourneyBlocks.blocks.add(this);
	    setRegistryName(JITL.MOD_ID, name);
	    LangGeneratorFacade.addBlockEntry(this, finalName);
        JourneyItems.items.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }

    public Block addName(String name) {
        JourneyBlocks.blockName.add(SlayerAPI.PREFIX + name);
        return this;
    }

    public String getName() {
        return name;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        if (drop == null) return SlayerAPI.toItem(this);
        return drop;
    }

    public BlockModContainer setHarvestLevel(EnumToolType type) {
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
    public boolean isOpaqueCube(IBlockState state) {
        return isOpaque;
    }

    @Override
    public boolean isNormalCube(IBlockState state, IBlockAccess world, BlockPos pos) {
        return isNormalCube;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
    }
}