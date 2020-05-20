package net.slayer.api.block;

import net.journey.blocks.tileentity.TileEntityJourneyMobSpawner;
import net.journey.init.JourneyTabs;
import net.journey.util.StuffConstructor;
import net.minecraft.block.BlockMobSpawner;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.EnumMaterialTypes;
import net.slayer.api.SlayerAPI;
import net.slayer.api.entity.tileentity.container.BlockModContainer;

import java.util.Random;

public class BlockModSpawner extends BlockMobSpawner {

    protected String mobName;

    public BlockModSpawner(String name, String enName, String mobName) {
        super();
        setSoundType(SoundType.METAL);
        this.mobName = mobName;
        StuffConstructor.regAndSetupBlock(this, name, enName, 1.0F, JourneyTabs.INTERACTIVE_BLOCKS);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int par1) {
        TileEntityJourneyMobSpawner spawner = new TileEntityJourneyMobSpawner();
        spawner.getSpawnerBaseLogic().setEntityId(new ResourceLocation(SlayerAPI.PREFIX + mobName));
        return spawner;
    }

    @Override
    public int quantityDropped(Random par1Random) {
        return 0;
    }
}