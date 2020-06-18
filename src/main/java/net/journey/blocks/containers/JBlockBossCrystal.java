package net.journey.blocks.containers;

import net.journey.JITL;
import net.journey.api.block.IHasCustomItemPath;
import net.journey.api.block.IHasTeisr;
import net.journey.blocks.tileentity.TileEntityBossCrystal;
import net.journey.blocks.tileentity.TileEntityCloudAltar;
import net.journey.blocks.tileentity.TileEntityJourneyChest;
import net.journey.client.render.block.JourneyChestTESR;
import net.journey.client.render.model.block.ModelCloudAltar;
import net.journey.init.JourneyTabs;
import net.journey.init.blocks.JourneyBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.InventoryLargeChest;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.ILockableContainer;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.EnumMaterialTypes;
import net.slayer.api.SlayerAPI;
import net.slayer.api.entity.tileentity.container.BlockModContainer;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class JBlockBossCrystal extends BlockModContainer implements IHasTeisr, IHasCustomItemPath {

    public final JBlockBossCrystal.Type type;
    private AxisAlignedBB size = new AxisAlignedBB(0.0F, 0.0F, 0.0F, 1.0F, 3.5F, 1.0F);

    public JBlockBossCrystal(String name, String finalName, JBlockBossCrystal.Type type) {
        super(name, finalName);
        this.type = type;
        setLightLevel(1.0F);
        setSoundType(EnumMaterialTypes.GLASS.getSound());
        setCreativeTab(JourneyTabs.INTERACTIVE_BLOCKS);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityBossCrystal();
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

    @Override
    @SideOnly(Side.CLIENT)
    public boolean hasCustomBreakingProgress(IBlockState state) {
        return true;
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        TileEntityBossCrystal crystal = (TileEntityBossCrystal) worldIn.getTileEntity(pos);
        if (crystal != null) {
            InventoryHelper.dropInventoryItems(worldIn, pos, crystal);
            worldIn.updateComparatorOutputLevel(pos, this);
        }

        super.breakBlock(worldIn, pos, state);
    }

    @NotNull
    @Override
    public Supplier<TileEntityItemStackRenderer> createTeisr() {
        return JBlockBossCrystal.CrystalTEISR::new;
    }

    @NotNull
    @Override
    public ResourceLocation getItemModelResourceLocation() {
        return new ResourceLocation(JITL.MOD_ID, "block/base_crystal");
    }

    public static class CrystalTEISR extends TileEntityItemStackRenderer {

        private final TileEntityBossCrystal crystal = new TileEntityBossCrystal();

        @Override
        public void renderByItem(ItemStack itemStackIn, float partialTicks) {
            JBlockBossCrystal.Type type = ((JBlockBossCrystal) Block.getBlockFromItem(itemStackIn.getItem())).type;
            crystal.setChestType(type);
            TileEntityRendererDispatcher.instance.render(crystal, 0.0D, 0.0D, 0.0D, 0.0F, partialTicks);
        }
    }

    public enum Type {
        BASE(new ResourceLocation(JITL.MOD_ID, "textures/models/blocks/crystal.png")),
        NETHER(new ResourceLocation(JITL.MOD_ID, "textures/models/blocks/crystal_nether.png")),
        BOIL(new ResourceLocation(JITL.MOD_ID, "textures/models/blocks/crystal_boil.png")),
        EUCA(new ResourceLocation(JITL.MOD_ID, "textures/models/blocks/crystal_euca.png")),
        DEPTHS(new ResourceLocation(JITL.MOD_ID, "textures/models/blocks/crystal_depths.png")),
        CORBA(new ResourceLocation(JITL.MOD_ID, "textures/models/blocks/crystal_corba.png")),
        TERRANIA(new ResourceLocation(JITL.MOD_ID, "textures/models/blocks/crystal_terrania.png")),
        CLOUDIA(new ResourceLocation(JITL.MOD_ID, "textures/models/blocks/crystal_cloudia.png")),
        SENTERIAN(new ResourceLocation(JITL.MOD_ID, "textures/models/blocks/crystal_senterian.png")),
        FROZEN(new ResourceLocation(JITL.MOD_ID, "textures/models/blocks/crystal_frozen.png"));

        private final ResourceLocation crystalTexture;

        Type(ResourceLocation crystalTexture) {
            this.crystalTexture = crystalTexture;
        }

        public ResourceLocation getCrystalTexture() {
            return crystalTexture;
        }
    }
}