package net.journey.blocks.machines;

import net.journey.blocks.tileentity.TileEntitySenterianAltar;
import net.journey.client.render.model.block.ModelSenterianAltar;
import net.journey.init.JourneyTabs;
import net.journey.init.blocks.JourneyBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.SlayerAPI;
import net.slayer.api.entity.tileentity.container.BlockModContainer;

public class BlockSenterianAltar extends BlockModContainer {

    private AxisAlignedBB size = new AxisAlignedBB(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);

    public BlockSenterianAltar(String name, String finalName) {
        super(name, finalName);
        setCreativeTab(JourneyTabs.MACHINE_BLOCKS);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntitySenterianAltar();
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.INVISIBLE;
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

    public class SenterianAltarTEISR extends TileEntityItemStackRenderer {

        private final ModelSenterianAltar model = new ModelSenterianAltar();

        @Override
        public void renderByItem(ItemStack itemStackIn) {
            if (itemStackIn.getItem() == SlayerAPI.toItem(JourneyBlocks.senterianAltar)) {
                this.model.render(1.0F);
            }
        }
    }
}