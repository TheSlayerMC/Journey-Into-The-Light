package net.slayer.api.block;

import net.journey.JITL;
import net.journey.JourneyBlocks;
import net.journey.JourneyItems;
import net.journey.JourneyTabs;
import net.journey.enums.EnumParticlesClasses;
import net.journey.util.LangRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.EnumMaterialTypes;
import net.slayer.api.EnumToolType;
import net.slayer.api.SlayerAPI;

import java.util.Random;

public class BlockMod extends Block {

    public String name;
    protected EnumMaterialTypes blockType;
    protected Item drop = null;
    protected Random rand;
    protected boolean isOpaque = true, isNormalCube = true;

    public BlockMod(String name, String finalName, float hardness) {
        this(EnumMaterialTypes.STONE, name, finalName, hardness, JourneyTabs.blocks);
    }

    public BlockMod(String name, String finalName) {
        this(EnumMaterialTypes.STONE, name, finalName, 2.0F, JourneyTabs.blocks);
    }

    public BlockMod(EnumMaterialTypes type, String name, String finalName, float hardness) {
        this(type, name, finalName, hardness, JourneyTabs.blocks);
    }

    public BlockMod(String name, String finalName, boolean breakable, CreativeTabs tab) {
        this(EnumMaterialTypes.STONE, name, finalName, tab);
    }

    public BlockMod(String name, String finalName, boolean breakable) {
        this(name, finalName, breakable, JourneyTabs.blocks);
    }

    public BlockMod(EnumMaterialTypes blockType, String name, String finalName, CreativeTabs tab) {
        super(blockType.getMaterial());
        LangRegistry.addBlock(name, finalName);
        this.blockType = blockType;
        setHardness(2.0F);
        rand = new Random();
        setSoundType(blockType.getSound());
        setCreativeTab(tab);
        setTranslationKey(name);
        this.name = name;
        JourneyBlocks.blocks.add(this);
        JourneyBlocks.blockName.add(SlayerAPI.PREFIX + name);
        setRegistryName(SlayerAPI.MOD_ID, name);
        JourneyItems.items.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }

    public BlockMod(EnumMaterialTypes blockType, String name, String finalName, float hardness, CreativeTabs tab) {
        super(blockType.getMaterial());
        LangRegistry.addBlock(name, finalName);
        this.blockType = blockType;
        rand = new Random();
        setSoundType(blockType.getSound());
        setCreativeTab(tab);
        setTranslationKey(name);
        setHardness(hardness);
        this.name = name;
        JourneyBlocks.blockName.add(SlayerAPI.PREFIX + name);
        JourneyBlocks.blocks.add(this);
        setRegistryName(SlayerAPI.MOD_ID, name);
        JourneyItems.items.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }

    public void registerItemModel(Item itemBlock) {
        JITL.proxy.registerItemRenderer(itemBlock, 0, name);
    }

    public Item createItemBlock() {
        return new ItemBlock(this).setRegistryName(getRegistryName());
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

    public BlockMod setHarvestLevel(EnumToolType type) {
        setHarvestLevel(type.getType(), type.getLevel());
        return this;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.SOLID;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return true;
    }

    @Override
    public boolean isNormalCube(IBlockState state) {
        return true;
    }

    @Override
    public int quantityDropped(Random rand) {
        return 1;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        if (this == JourneyBlocks.ashBlock && worldIn.getBlockState(pos.up()) == Blocks.AIR.getDefaultState())
            if (rand.nextInt(40) == 0) {
                JITL.proxy.spawnParticle(EnumParticlesClasses.SMOKE, worldIn, pos.getX(), pos.getY() + rand.nextInt(3), pos.getZ(), 0, 0, 0);
            }
    }

    @Override
    public boolean isFireSource(World world, BlockPos pos, EnumFacing side) {
        return true;
    }
}