package net.slayer.api.block;

import java.util.Random;

import net.journey.JourneyTabs;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.world.World;
import net.slayer.api.EnumMaterialTypes;
import net.slayer.api.entity.tileentity.container.BlockModContainer;

public class BlockModSpawner extends BlockModContainer {

    protected String mobName;

    public BlockModSpawner(String name, String finalName, String mobName) {
        super(EnumMaterialTypes.STONE, name, finalName, -1, JourneyTabs.spawners);
        this.mobName = mobName;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int par1) {
        TileEntityMobSpawner spawner = new TileEntityMobSpawner();
        spawner.getSpawnerBaseLogic().setEntityName("essence." + mobName);
        return spawner;
    }

    @Override
    public int quantityDropped(Random par1Random) {
        return 0;
    }
    
    @Override
    public EnumWorldBlockLayer getBlockLayer() {
    	return EnumWorldBlockLayer.CUTOUT;
    }

    @Override
    public void dropBlockAsItemWithChance(World world, BlockPos pos, IBlockState par5, float par6, int par7) {
        super.dropBlockAsItemWithChance(world, pos, par5, par6, par7);
        int var8 = 15 + world.rand.nextInt(15) + world.rand.nextInt(15);
        this.dropXpOnBlockBreak(world, pos, var8);
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }
}