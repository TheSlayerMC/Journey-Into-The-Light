package net.slayer.api.block;

import net.journey.JITL;
import net.journey.api.capability.JourneyPlayer;
import net.journey.api.capability.PlayerStats;
import net.journey.client.ItemDescription;
import net.journey.common.capability.JCapabilityManager;
import net.journey.common.knowledge.EnumKnowledgeType;
import net.journey.enums.EnumParticlesClasses;
import net.journey.init.JourneyTabs;
import net.journey.init.blocks.JourneyBlocks;
import net.journey.util.StuffConstructor;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.EnumMaterialTypes;
import net.slayer.api.EnumToolType;
import net.slayer.api.SlayerAPI;

import java.util.List;
import java.util.Random;

public class BlockMod extends Block {
    protected Boolean isFireSource;

    private EnumKnowledgeType knowledgeType;
    private int knowledgeAmount;

    public BlockMod(String name, String enName, float hardness) {
        this(EnumMaterialTypes.STONE, name, enName, hardness, JourneyTabs.BLOCKS);
    }

    public BlockMod(String name, String enName) {
        this(EnumMaterialTypes.STONE, name, enName, JourneyTabs.BLOCKS);
    }

    public BlockMod(EnumMaterialTypes type, String name, String enName, float hardness) {
        this(type, name, enName, hardness, JourneyTabs.BLOCKS);
    }

    public BlockMod(String name, String enName, boolean breakable, CreativeTabs tab) {
        this(EnumMaterialTypes.STONE, name, enName, tab);
    }

    public BlockMod(String name, String enName, boolean breakable) {
        this(name, enName, breakable, JourneyTabs.BLOCKS);
    }

    public BlockMod(EnumMaterialTypes blockType, String name, String enName, CreativeTabs tab) {
        this(blockType, name, enName, 2.0F, tab);
    }

    public BlockMod(EnumMaterialTypes blockType, String name, String enName, float hardness, CreativeTabs tab) {
        super(blockType.getMaterial());
        setSoundType(blockType.getSound());

        setHardness(hardness);
        StuffConstructor.regAndSetupBlock(this, name, enName, tab);
    }

    public BlockMod setFireSource(boolean isFireSource) {
        this.isFireSource = isFireSource;
        return this;
    }

    @Override
    public boolean isFireSource(World world, BlockPos pos, EnumFacing side) {
        return isFireSource != null ? isFireSource : super.isFireSource(world, pos, side);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return SlayerAPI.toItem(this);
    }

    public BlockMod setHarvestLevel(EnumToolType type) {
        setHarvestLevel(type.getType(), type.getLevel());
        return this;
    }

    public BlockMod applyKnowledge(EnumKnowledgeType type, int amount) {
        this.knowledgeType = type;
        this.knowledgeAmount = amount;
        return this;
    }

    @Override
    public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player) {
        super.onBlockHarvested(worldIn, pos, state, player);
        if (!worldIn.isRemote) {
            if (this.knowledgeType != null && this.knowledgeAmount > 0) {
                PlayerStats stats = JCapabilityManager.asJourneyPlayer(player).getPlayerStats();
                JourneyPlayer journeyPlayer = JCapabilityManager.asJourneyPlayer(player);
                stats.addKnowledge(this.knowledgeType, this.knowledgeAmount);
                journeyPlayer.sendUpdates(((EntityPlayerMP) player));
            }
        }
    }

    @Override
    public boolean isNormalCube(IBlockState state) {//TODO is it really needed? May prevent player from passing some block types.
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack i, World worldIn, List<String> l, ITooltipFlag flagIn) {
        ItemDescription.addInformation(i, l);
        if(this == JourneyBlocks.ANCIENT_STONE) {
            l.add("Unbreakable");
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        if (this == JourneyBlocks.ashBlock && worldIn.getBlockState(pos.up()) == Blocks.AIR.getDefaultState())
            if (rand.nextInt(40) == 0) {
                JITL.proxy.spawnParticle(EnumParticlesClasses.SMOKE, worldIn, pos.getX(), pos.getY() + rand.nextInt(3), pos.getZ(), 0, 0, 0);
            }
    }
}