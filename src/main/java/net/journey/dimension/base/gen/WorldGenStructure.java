package net.journey.dimension.base.gen;

import net.journey.JITL;
import net.journey.api.world.gen.IGeneratedStructure;
import net.minecraft.block.state.IBlockState;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityLockableLoot;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;
import net.minecraftforge.fml.common.FMLCommonHandler;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

public class WorldGenStructure extends WorldGenerator implements IGeneratedStructure {

    private static String name;
    protected Function<TileEntityChestArgs, ResourceLocation> lootTableFunc;

    public WorldGenStructure(String name, Function<TileEntityChestArgs, ResourceLocation> lootTableFunc) {
        WorldGenStructure.name = name;
        this.lootTableFunc = lootTableFunc;
    }

    public WorldGenStructure(String name) {
        this(name, (ResourceLocation) null);
    }

    public WorldGenStructure(String name, ResourceLocation loot) {
        this(name, loot == null ? null : blockInfo -> loot);
    }

    @Override
    public boolean generate(World w, Random rand, BlockPos pos) {
        generateStructure(w, pos);
        return true;
    }

    public void generateStructure(World w, BlockPos pos) {
        MinecraftServer server = w.getMinecraftServer();
        TemplateManager manager = getWorldServer(w).getStructureTemplateManager();
        ResourceLocation location = new ResourceLocation(JITL.MOD_ID, name);
        Template template = manager.get(server, location);


        if (template != null) {
            IBlockState state = w.getBlockState(pos);
            w.notifyBlockUpdate(pos, state, state, 3);
            template.addBlocksToWorldChunk(w, pos, getSetting());
            generateLoot(w, template, pos);
        }
    }

    public void generateLoot(World world, Template template, BlockPos pos) {
        if (lootTableFunc == null)
            return;

        List<Template.BlockInfo> allBlocks;
        try {
            Field field = template.getClass().getDeclaredField("blocks");
            field.setAccessible(true);

            allBlocks = (List<Template.BlockInfo>) field.get(template);

        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return;

        } catch (NoSuchFieldException e) {
            System.out.println("Template class was changed, need to change code. Exception is below:\n" + e.toString());
            return;
        }

        if (allBlocks == null)
            return;
        allBlocks.forEach(blockInfo -> {
            if (blockInfo.tileentityData == null)
                return;
            BlockPos correct = blockInfo.pos.add(pos.getX(), pos.getY(), pos.getZ());
            TileEntity tileEntity = world.getTileEntity(correct);
            if (tileEntity instanceof TileEntityLockableLoot) {
                ResourceLocation lootTable = lootTableFunc.apply(new TileEntityChestArgs(world, (TileEntityLockableLoot) tileEntity, correct));
                if (lootTable != null)
                    ((TileEntityLockableLoot) tileEntity).setLootTable(lootTable, world.rand.nextLong());
                else {
                    System.out.println("Can't find loot table for tile entity :" + correct.toString());
                }
            }
        });

    }

    @Override
    public WorldServer getWorldServer(World w) {
        return FMLCommonHandler.instance().getMinecraftServerInstance().getWorld(w.provider.getDimension());
    }

    @Override
    public PlacementSettings getSetting() {
        return (new PlacementSettings()).setChunk(null).setIgnoreEntities(false).setIgnoreStructureBlock(false).setMirror(Mirror.NONE).setRotation(Rotation.NONE);
    }

    public class TileEntityChestArgs {

        private World world;
        private TileEntityLockableLoot tileEntity;
        private BlockPos pos;

        public TileEntityChestArgs(World world, TileEntityLockableLoot tileEntity, BlockPos pos) {
            this.world = world;
            this.tileEntity = tileEntity;
            this.pos = pos;
        }

        public BlockPos getPos() {
            return pos;
        }

        public World getWorld() {
            return world;
        }

        public TileEntityLockableLoot getTileEntity() {
            return tileEntity;
        }
    }
}