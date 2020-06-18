package net.journey.blocks.containers;

import net.journey.blocks.tileentity.TileEntityBossBlock;
import net.journey.blocks.tileentity.TileEntityCloudAltar;
import net.journey.blocks.tileentity.TileEntityBossBlock.State;
import net.journey.client.render.model.block.ModelCloudAltar;
import net.journey.init.JourneyTabs;
import net.journey.init.blocks.JourneyBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.SlayerAPI;
import net.slayer.api.entity.tileentity.container.BlockModContainer;

public class BlockBossBlock extends BlockModContainer {

    private AxisAlignedBB size = new AxisAlignedBB(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);

    public BlockBossBlock(String name, String finalName) {
        super(name, finalName);
        setCreativeTab(JourneyTabs.INTERACTIVE_BLOCKS);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityBossBlock();
    }
    
    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		TileEntityBossBlock entity = (TileEntityBossBlock)worldIn.getTileEntity(pos);

    	if(entity != null) {
    		if(entity.getState() == State.IDLE)
    			entity.set(State.INERT);
    	}
    	
    	return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.ENTITYBLOCK_ANIMATED;
    }

    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.SOLID;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return size;
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
        return size;
    }

    public class CloudAltarTEISR extends TileEntityItemStackRenderer {

        private final ModelCloudAltar model = new ModelCloudAltar();

        @Override
        public void renderByItem(ItemStack itemStackIn) {
            if (itemStackIn.getItem() == SlayerAPI.toItem(JourneyBlocks.BOSS_BLOCK)) {
                this.model.render(1.0F, true);
            }
        }
    }
}