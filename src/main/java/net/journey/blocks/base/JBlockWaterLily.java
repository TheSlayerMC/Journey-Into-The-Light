package net.journey.blocks.base;

import net.journey.JITL;
import net.journey.api.block.IHasCustomItemPath;
import net.journey.init.JourneyTabs;
import net.journey.util.StuffConstructor;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.slayer.api.EnumMaterialTypes;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

public class JBlockWaterLily extends BlockBush implements IHasCustomItemPath {

    protected static final AxisAlignedBB LILY_PAD_AABB = new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 0.09375D, 0.9375D);

    public JBlockWaterLily(String name, String enName) {
        super();
        setSoundType(EnumMaterialTypes.GRASS.getSound());
        StuffConstructor.regAndSetupBlock(this, name, enName, 1.0F, JourneyTabs.DECORATION);
    }

    @Override
    public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean isActualState) {
        if (!(entityIn instanceof EntityBoat)) {
            addCollisionBoxToList(pos, entityBox, collidingBoxes, LILY_PAD_AABB);
        }

    }

    @Override
    public void onEntityCollision(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
        super.onEntityCollision(worldIn, pos, state, entityIn);
        if (entityIn instanceof EntityBoat) {
            worldIn.destroyBlock(new BlockPos(pos), true);
        }
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return LILY_PAD_AABB;
    }

    @Override
    protected boolean canSustainBush(IBlockState state) {
        return state.getBlock() == Blocks.WATER || state.getMaterial() == Material.ICE;
    }

    @Override
    public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state) {
        if (pos.getY() >= 0 && pos.getY() < 256) {
            IBlockState iblockstate = worldIn.getBlockState(pos.down());
            Material material = iblockstate.getMaterial();
            return material == Material.WATER && iblockstate.getValue(BlockLiquid.LEVEL) == 0 || material == Material.ICE;
        } else {
            return false;
        }
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return 0;
    }

    @Override
    public @NotNull ResourceLocation getItemModelResourceLocation() {
		return new ResourceLocation(JITL.MOD_ID, "block/plant/" + getRegistryName().getPath());
    }
}
