package net.journey.blocks;

import com.google.common.base.Predicates;

import net.journey.init.JourneySounds;
import net.journey.init.blocks.JourneyBlocks;
import net.journey.init.items.JourneyItems;
import net.journey.util.RandUtils;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.BlockWorldState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockPattern;
import net.minecraft.block.state.pattern.BlockStateMatcher;
import net.minecraft.block.state.pattern.FactoryBlockPattern;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.slayer.api.SlayerAPI;
import net.slayer.api.block.BlockMod;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BlockAncientCatalyst extends BlockMod {

	private static BlockPattern pattern;
	
    public BlockAncientCatalyst(String name, String f) {
        super(name, f, false);
    }
    
    public static BlockPattern getOrCreatepattern() {
        if (pattern == null) {
            pattern = FactoryBlockPattern.start().aisle(
            "v?v", 
            "???", 
            "v?v").where(
            '?', BlockWorldState.hasState(BlockStateMatcher.ANY)).where(
            'v', BlockWorldState.hasState(BlockStateMatcher.forBlock(JourneyBlocks.ANCIENT_SOCKET).where(BlockAncientSocket.INSERT, Predicates.equalTo(Boolean.valueOf(true))))).build();
        }
        return pattern;
    }
}