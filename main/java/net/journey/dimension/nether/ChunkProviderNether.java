package net.journey.dimension.nether;

import java.util.List;
import java.util.Random;

import net.journey.JourneyBlocks;
import net.journey.entity.mob.nether.EntityMiniGhast;
import net.journey.entity.mob.nether.EntityWitherspine;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.gen.MapGenCavesHell;
import net.minecraft.world.gen.NoiseGenerator;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.feature.WorldGenFire;
import net.minecraft.world.gen.feature.WorldGenGlowStone1;
import net.minecraft.world.gen.feature.WorldGenGlowStone2;
import net.minecraft.world.gen.feature.WorldGenHellLava;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.structure.MapGenNetherBridge;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.event.terraingen.TerrainGen;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class ChunkProviderNether implements IChunkProvider {

    private final World worldObj;
    private final boolean par1;
    private final Random hellRNG;
    private double[] slowsandNoise = new double[256];
    private double[] gravelNoise = new double[256];
    private double[] heatsoilNoise = new double[256];
    private double[] netherrackExclusivityNoise = new double[256];
    private double[] noiseField;
    private final NoiseGeneratorOctaves netherNoiseGen1;
    private final NoiseGeneratorOctaves netherNoiseGen2;
    private final NoiseGeneratorOctaves netherNoiseGen3;
    private final NoiseGeneratorOctaves slowsandGravelNoiseGen;
    private final NoiseGeneratorOctaves netherrackExculsivityNoiseGen;
    public final NoiseGeneratorOctaves netherNoiseGen6;
    public final NoiseGeneratorOctaves netherNoiseGen7;
    private final WorldGenFire field_177470_t = new WorldGenFire();
    private final WorldGenGlowStone1 field_177469_u = new WorldGenGlowStone1();
    private final WorldGenGlowStone2 field_177468_v = new WorldGenGlowStone2();
    private final WorldGenerator field_177467_w = new WorldGenMinable(Blocks.quartz_ore.getDefaultState(), 14, BlockHelper.forBlock(Blocks.netherrack));
    private final WorldGenHellLava field_177473_x = new WorldGenHellLava(Blocks.flowing_lava, true);
    private final WorldGenHellLava field_177472_y = new WorldGenHellLava(Blocks.flowing_lava, false);
    private final GeneratorBushFeature field_177471_z = new GeneratorBushFeature(Blocks.brown_mushroom);
    private final GeneratorBushFeature field_177465_A = new GeneratorBushFeature(Blocks.red_mushroom);
    private final MapGenNetherBridge genNetherBridge;
    private final MapGenBase netherCaveGenerator;
    double[] noiseData1;
    double[] noiseData2;
    double[] noiseData3;
    double[] noiseData4;
    double[] noiseData5;

    public ChunkProviderNether(World worldIn, boolean par2, long par3) {
        this.genNetherBridge = (MapGenNetherBridge) TerrainGen.getModdedMapGen(new MapGenNetherBridge(), net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.NETHER_BRIDGE);
        this.netherCaveGenerator = TerrainGen.getModdedMapGen(new MapGenCavesHell(), net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.NETHER_CAVE);
        this.worldObj = worldIn;
        this.par1 = par2;
        this.hellRNG = new Random(par3);
        NoiseGeneratorOctaves netherNoiseGen1 = new NoiseGeneratorOctaves(this.hellRNG, 16);
        NoiseGeneratorOctaves netherNoiseGen2 = new NoiseGeneratorOctaves(this.hellRNG, 16);
        NoiseGeneratorOctaves netherNoiseGen3 = new NoiseGeneratorOctaves(this.hellRNG, 8);
        NoiseGeneratorOctaves slowsandGravelNoiseGen = new NoiseGeneratorOctaves(this.hellRNG, 4);
        NoiseGeneratorOctaves netherrackExculsivityNoiseGen = new NoiseGeneratorOctaves(this.hellRNG, 4);
        NoiseGeneratorOctaves netherNoiseGen6 = new NoiseGeneratorOctaves(this.hellRNG, 10);
        NoiseGeneratorOctaves netherNoiseGen7 = new NoiseGeneratorOctaves(this.hellRNG, 16);
        NoiseGenerator[] noiseGens = new NoiseGenerator[] {
                netherNoiseGen1, netherNoiseGen2, netherNoiseGen3, slowsandGravelNoiseGen, netherrackExculsivityNoiseGen, netherNoiseGen6, netherNoiseGen7
        };
        noiseGens = net.minecraftforge.event.terraingen.TerrainGen.getModdedNoiseGenerators(worldIn, this.hellRNG, noiseGens);
        this.netherNoiseGen1 = (NoiseGeneratorOctaves)noiseGens[0];
        this.netherNoiseGen2 = (NoiseGeneratorOctaves)noiseGens[1];
        this.netherNoiseGen3 = (NoiseGeneratorOctaves)noiseGens[2];
        this.slowsandGravelNoiseGen = (NoiseGeneratorOctaves)noiseGens[3];
        this.netherrackExculsivityNoiseGen = (NoiseGeneratorOctaves)noiseGens[4];
        this.netherNoiseGen6 = (NoiseGeneratorOctaves)noiseGens[5];
        this.netherNoiseGen7 = (NoiseGeneratorOctaves)noiseGens[6];
        worldIn.setSeaLevel(63);
    }

    public void generateTerrainFeatures(int chunkX, int chunkZ, ChunkPrimer primer) {
        int i = 4;
        int j = this.worldObj.getSeaLevel() / 2 + 1;
        int k = i + 1;
        int l = 17;
        int i1 = i + 1;
        this.noiseField = this.initializeNoiseField(this.noiseField, chunkX * i, 0, chunkZ * i, k, l, i1);

        for (int j1 = 0; j1 < i; ++j1) {
            for (int k1 = 0; k1 < i; ++k1) {
                for (int l1 = 0; l1 < 16; ++l1) {
                    double d0 = 0.125D;
                    double d1 = this.noiseField[((j1 + 0) * i1 + k1 + 0) * l + l1 + 0];
                    double d2 = this.noiseField[((j1 + 0) * i1 + k1 + 1) * l + l1 + 0];
                    double d3 = this.noiseField[((j1 + 1) * i1 + k1 + 0) * l + l1 + 0];
                    double d4 = this.noiseField[((j1 + 1) * i1 + k1 + 1) * l + l1 + 0];
                    double d5 = (this.noiseField[((j1 + 0) * i1 + k1 + 0) * l + l1 + 1] - d1) * d0;
                    double d6 = (this.noiseField[((j1 + 0) * i1 + k1 + 1) * l + l1 + 1] - d2) * d0;
                    double d7 = (this.noiseField[((j1 + 1) * i1 + k1 + 0) * l + l1 + 1] - d3) * d0;
                    double d8 = (this.noiseField[((j1 + 1) * i1 + k1 + 1) * l + l1 + 1] - d4) * d0;

                    for (int i2 = 0; i2 < 8; ++i2) {
                        double d9 = 0.25D;
                        double d10 = d1;
                        double d11 = d2;
                        double d12 = (d3 - d1) * d9;
                        double d13 = (d4 - d2) * d9;

                        for (int j2 = 0; j2 < 4; ++j2) {
                            double d14 = 0.25D;
                            double d15 = d10;
                            double d16 = (d11 - d10) * d14;

                            for (int k2 = 0; k2 < 4; ++k2) {
                                IBlockState iblockstate = null;

                                if (l1 * 8 + i2 < j) {
                                    iblockstate = Blocks.lava.getDefaultState();
                                }

                                if (d15 > 0.0D) {
                                    iblockstate = Blocks.netherrack.getDefaultState();
                                }

                                int l2 = j2 + j1 * 4;
                                int i3 = i2 + l1 * 8;
                                int j3 = k2 + k1 * 4;
                                primer.setBlockState(l2, i3, j3, iblockstate);
                                d15 += d16;
                            }

                            d10 += d12;
                            d11 += d13;
                        }

                        d1 += d5;
                        d2 += d6;
                        d3 += d7;
                        d4 += d8;
                    }
                }
            }
        }
    }

    public void generateNetherTerrain(int chunkX, int chunkZ, ChunkPrimer primer){
        net.minecraftforge.event.terraingen.ChunkProviderEvent.ReplaceBiomeBlocks event = new net.minecraftforge.event.terraingen.ChunkProviderEvent.ReplaceBiomeBlocks(this, chunkX, chunkZ, primer, this.worldObj);
        net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(event);
        if (event.getResult() == net.minecraftforge.fml.common.eventhandler.Event.Result.DENY) return;

        int i = this.worldObj.getSeaLevel() + 1;
        double d0 = 0.03125D;
        this.slowsandNoise = this.slowsandGravelNoiseGen.generateNoiseOctaves(this.slowsandNoise, chunkX * 16, chunkZ * 16, 0, 16, 16, 1, d0, d0, 1.0D);
        this.gravelNoise = this.slowsandGravelNoiseGen.generateNoiseOctaves(this.gravelNoise, chunkX * 16, 109, chunkZ * 16, 16, 1, 16, d0, 1.0D, d0);
        this.heatsoilNoise = this.slowsandGravelNoiseGen.generateNoiseOctaves(this.heatsoilNoise, chunkX * 24, chunkZ * 16, 0, 16, 16, 1, d0, d0, 1.0D);
        this.netherrackExclusivityNoise = this.netherrackExculsivityNoiseGen.generateNoiseOctaves(this.netherrackExclusivityNoise, chunkX * 16, chunkZ * 16, 0, 16, 16, 1, d0 * 2.0D, d0 * 2.0D, d0 * 2.0D);

        for (int j = 0; j < 16; ++j){
            for (int k = 0; k < 16; ++k){
                boolean flag = this.slowsandNoise[j + k * 16] + this.hellRNG.nextDouble() * 0.2D > 0.0D;
                boolean flag1 = this.gravelNoise[j + k * 16] + this.hellRNG.nextDouble() * 0.2D > 0.0D;
                boolean flag2 = this.heatsoilNoise[j + k * 16] + this.hellRNG.nextDouble() * 0.2D > 0.0D;
                int l = (int)(this.netherrackExclusivityNoise[j + k * 16] / 3.0D + 3.0D + this.hellRNG.nextDouble() * 0.25D);
                int i1 = -1;
                IBlockState iblockstate = Blocks.netherrack.getDefaultState();
                IBlockState iblockstate1 = Blocks.netherrack.getDefaultState();

                for (int j1 = 127; j1 >= 0; --j1){
                    if (j1 < 127 - this.hellRNG.nextInt(5) && j1 > this.hellRNG.nextInt(5)){
                        IBlockState iblockstate2 = primer.getBlockState(k, j1, j);

                        if (iblockstate2.getBlock() != null && iblockstate2.getBlock().getMaterial() != Material.air){
                            if (iblockstate2.getBlock() == Blocks.netherrack) {
                                if (i1 == -1){
                                    if (l <= 0) {
                                        iblockstate = null;
                                        iblockstate1 = Blocks.netherrack.getDefaultState();
                                    }
                                    else if (j1 >= i - 4 && j1 <= i + 1) {
                                        iblockstate = Blocks.netherrack.getDefaultState();
                                        iblockstate1 = Blocks.netherrack.getDefaultState();

                                        if (flag1) {
                                            iblockstate = Blocks.gravel.getDefaultState();
                                            iblockstate1 = Blocks.netherrack.getDefaultState();
                                        }
                                        
                                        if (flag) {
                                            iblockstate = Blocks.soul_sand.getDefaultState();
                                            iblockstate1 = Blocks.soul_sand.getDefaultState();
                                        }

                                        if (flag2) {
                                            iblockstate = JourneyBlocks.heatSoil.getDefaultState();
                                            iblockstate1 = JourneyBlocks.heatSoil.getDefaultState();
                                        }
                                    }

                                    if (j1 < i && (iblockstate == null || iblockstate.getBlock().getMaterial() == Material.air)){
                                        iblockstate = Blocks.lava.getDefaultState();
                                    }

                                    i1 = l;

                                    if (j1 >= i - 1){
                                        primer.setBlockState(k, j1, j, iblockstate);
                                    }
                                    else {
                                        primer.setBlockState(k, j1, j, iblockstate1);
                                    }
                                }
                                else if (i1 > 0) {
                                    --i1;
                                    primer.setBlockState(k, j1, j, iblockstate1);
                                }
                            }
                        }
                        else {
                            i1 = -1;
                        }
                    }
                    else {
                        primer.setBlockState(k, j1, j, Blocks.netherrack.getDefaultState());
                    }
                }
            }
        }
    }

    @Override
    public Chunk provideChunk(int x, int z) {
        this.hellRNG.setSeed(x * 341873128712L + z * 132897987541L);
        ChunkPrimer chunkprimer = new ChunkPrimer();
        this.generateTerrainFeatures(x, z, chunkprimer);
        this.generateNetherTerrain(x, z, chunkprimer);
        this.netherCaveGenerator.generate(this, this.worldObj, x, z, chunkprimer);

        if (this.par1) {
            this.genNetherBridge.generate(this, this.worldObj, x, z, chunkprimer);
        }

        Chunk chunk = new Chunk(this.worldObj, chunkprimer, x, z);
        BiomeGenBase[] abiomegenbase = this.worldObj.getWorldChunkManager().loadBlockGeneratorData((BiomeGenBase[])null, x * 16, z * 16, 16, 16);
        byte[] abyte = chunk.getBiomeArray();

        for (int i = 0; i < abyte.length; ++i) {
            abyte[i] = (byte)abiomegenbase[i].biomeID;
        }

        chunk.resetRelightChecks();
        return chunk;
    }

    private double[] initializeNoiseField(double[] par1, int par2, int par3, int par4, int par5, int par6, int par7)  {
        net.minecraftforge.event.terraingen.ChunkProviderEvent.InitNoiseField event = new net.minecraftforge.event.terraingen.ChunkProviderEvent.InitNoiseField(this, par1, par2, par3, par4, par5, par6, par7);
        net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(event);
        if (event.getResult() == net.minecraftforge.fml.common.eventhandler.Event.Result.DENY) return event.noisefield;

        if (par1 == null) {
            par1 = new double[par5 * par6 * par7];
        }

        double d0 = 684.412D;
        double d1 = 2053.236D;
        this.noiseData4 = this.netherNoiseGen6.generateNoiseOctaves(this.noiseData4, par2, par3, par4, par5, 1, par7, 1.0D, 0.0D, 1.0D);
        this.noiseData5 = this.netherNoiseGen7.generateNoiseOctaves(this.noiseData5, par2, par3, par4, par5, 1, par7, 100.0D, 0.0D, 100.0D);
        this.noiseData1 = this.netherNoiseGen3.generateNoiseOctaves(this.noiseData1, par2, par3, par4, par5, par6, par7, d0 / 80.0D, d1 / 60.0D, d0 / 80.0D);
        this.noiseData2 = this.netherNoiseGen1.generateNoiseOctaves(this.noiseData2, par2, par3, par4, par5, par6, par7, d0, d1, d0);
        this.noiseData3 = this.netherNoiseGen2.generateNoiseOctaves(this.noiseData3, par2, par3, par4, par5, par6, par7, d0, d1, d0);
        int i = 0;
        double[] adouble = new double[par6];

        for (int j = 0; j < par6; ++j) {
            adouble[j] = Math.cos(j * Math.PI * 6.0D / par6) * 2.0D;
            double d2 = j;

            if (j > par6 / 2) {
                d2 = par6 - 1 - j;
            }

            if (d2 < 4.0D) {
                d2 = 4.0D - d2;
                adouble[j] -= d2 * d2 * d2 * 10.0D;
            }
        }

        for (int l = 0; l < par5; ++l) {
            for (int i1 = 0; i1 < par7; ++i1) {
                double d3 = 0.0D;

                for (int k = 0; k < par6; ++k) {
                    double d4 = 0.0D;
                    double d5 = adouble[k];
                    double d6 = this.noiseData2[i] / 512.0D;
                    double d7 = this.noiseData3[i] / 512.0D;
                    double d8 = (this.noiseData1[i] / 10.0D + 1.0D) / 2.0D;

                    if (d8 < 0.0D) {
                        d4 = d6;
                    }
                    else if (d8 > 1.0D) {
                        d4 = d7;
                    }
                    else {
                        d4 = d6 + (d7 - d6) * d8;
                    }
                    d4 = d4 - d5;
                    if (k > par6 - 4) {
                        double d9 = (k - (par6 - 4)) / 3.0F;
                        d4 = d4 * (1.0D - d9) + -10.0D * d9;
                    }
                    if (k < d3) {
                        double d10 = (d3 - k) / 4.0D;
                        d10 = MathHelper.clamp_double(d10, 0.0D, 1.0D);
                        d4 = d4 * (1.0D - d10) + -10.0D * d10;
                    }
                    par1[i] = d4;
                    ++i;
                }
            }
        }
        return par1;
    }

    @Override
    public boolean chunkExists(int x, int z) {
        return true;
    }

    @Override
    public void populate(IChunkProvider provider, int chunkX, int chunkZ) {
        BlockFalling.fallInstantly = true;
        net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(new net.minecraftforge.event.terraingen.PopulateChunkEvent.Pre(provider, worldObj, hellRNG, chunkX, chunkZ, false));
        BlockPos blockpos = new BlockPos(chunkX * 16, 0, chunkZ * 16);
        ChunkCoordIntPair chunkcoordintpair = new ChunkCoordIntPair(chunkX, chunkZ);
        this.genNetherBridge.generateStructure(this.worldObj, this.hellRNG, chunkcoordintpair);

        boolean doGen = net.minecraftforge.event.terraingen.TerrainGen.populate(provider, worldObj, hellRNG, chunkX, chunkZ, false, net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate.EventType.NETHER_LAVA);
        for (int i = 0; doGen && i < 8; ++i)
        {
            this.field_177472_y.generate(this.worldObj, this.hellRNG, blockpos.add(this.hellRNG.nextInt(16) + 8, this.hellRNG.nextInt(120) + 4, this.hellRNG.nextInt(16) + 8));
        }

        doGen = net.minecraftforge.event.terraingen.TerrainGen.populate(provider, worldObj, hellRNG, chunkX, chunkZ, false, net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate.EventType.FIRE);
        for (int j = 0; doGen && j < this.hellRNG.nextInt(this.hellRNG.nextInt(10) + 1) + 1; ++j)
        {
            this.field_177470_t.generate(this.worldObj, this.hellRNG, blockpos.add(this.hellRNG.nextInt(16) + 8, this.hellRNG.nextInt(120) + 4, this.hellRNG.nextInt(16) + 8));
        }

        doGen = net.minecraftforge.event.terraingen.TerrainGen.populate(provider, worldObj, hellRNG, chunkX, chunkZ, false, net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate.EventType.GLOWSTONE);
        for (int k = 0; doGen && k < this.hellRNG.nextInt(this.hellRNG.nextInt(10) + 1); ++k)
        {
            this.field_177469_u.generate(this.worldObj, this.hellRNG, blockpos.add(this.hellRNG.nextInt(16) + 8, this.hellRNG.nextInt(120) + 4, this.hellRNG.nextInt(16) + 8));
        }

        for (int l = 0; doGen && l < 10; ++l)
        {
            this.field_177468_v.generate(this.worldObj, this.hellRNG, blockpos.add(this.hellRNG.nextInt(16) + 8, this.hellRNG.nextInt(128), this.hellRNG.nextInt(16) + 8));
        }

        net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(new net.minecraftforge.event.terraingen.DecorateBiomeEvent.Pre(worldObj, hellRNG, blockpos));

        doGen = net.minecraftforge.event.terraingen.TerrainGen.decorate(worldObj, hellRNG, blockpos, net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.SHROOM);
        if (doGen && this.hellRNG.nextBoolean())
        {
            this.field_177471_z.generate(this.worldObj, this.hellRNG, blockpos.add(this.hellRNG.nextInt(16) + 8, this.hellRNG.nextInt(128), this.hellRNG.nextInt(16) + 8));
        }

        if (doGen && this.hellRNG.nextBoolean())
        {
            this.field_177465_A.generate(this.worldObj, this.hellRNG, blockpos.add(this.hellRNG.nextInt(16) + 8, this.hellRNG.nextInt(128), this.hellRNG.nextInt(16) + 8));
        }

        doGen = net.minecraftforge.event.terraingen.TerrainGen.generateOre(worldObj, hellRNG, field_177467_w, blockpos, net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.QUARTZ);
        for (int i1 = 0; doGen && i1 < 16; ++i1)
        {
            this.field_177467_w.generate(this.worldObj, this.hellRNG, blockpos.add(this.hellRNG.nextInt(16), this.hellRNG.nextInt(108) + 10, this.hellRNG.nextInt(16)));
        }

        doGen = net.minecraftforge.event.terraingen.TerrainGen.populate(provider, worldObj, hellRNG, chunkX, chunkZ, false, net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate.EventType.NETHER_LAVA2);
        for (int j1 = 0; doGen && j1 < 16; ++j1)
        {
            this.field_177473_x.generate(this.worldObj, this.hellRNG, blockpos.add(this.hellRNG.nextInt(16), this.hellRNG.nextInt(108) + 10, this.hellRNG.nextInt(16)));
        }

        net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(new net.minecraftforge.event.terraingen.PopulateChunkEvent.Post(provider, worldObj, hellRNG, chunkX, chunkZ, false));
        BlockFalling.fallInstantly = false;
    }

    @Override
    public boolean func_177460_a(IChunkProvider p_177460_1_, Chunk p_177460_2_, int p_177460_3_, int p_177460_4_) {
        return false;
    }

    @Override
    public boolean saveChunks(boolean p_73151_1_, IProgressUpdate progressCallback) {
        return true;
    }

    @Override
    public void saveExtraData() {}

    @Override
    public boolean unloadQueuedChunks() {
        return false;
    }
    
    @Override
    public boolean canSave() {
        return true;
    }

    @Override
    public String makeString() {
        return "HellRandomLevelSource";
    }

    @Override
    public List<BiomeGenBase.SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos)
    {
        if (creatureType == EnumCreatureType.MONSTER)
        {
            if (this.genNetherBridge.func_175795_b(pos))
            {
                return this.genNetherBridge.getSpawnList();
            }

            if (this.genNetherBridge.func_175796_a(this.worldObj, pos) && this.worldObj.getBlockState(pos.down()).getBlock() == Blocks.nether_brick)
            {
                return this.genNetherBridge.getSpawnList();
            }
        }
        BiomeGenBase biomegenbase = this.worldObj.getBiomeGenForCoords(pos);
        return biomegenbase.getSpawnableList(creatureType);
    }

    @Override
    public BlockPos getStrongholdGen(World worldIn, String structureName, BlockPos position) {
        return null;
    }

    @Override
    public int getLoadedChunkCount() {
        return 0;
    }

    @Override
    public void recreateStructures(Chunk p_180514_1_, int p_180514_2_, int p_180514_3_) {
        this.genNetherBridge.generate(this, this.worldObj, p_180514_2_, p_180514_3_, (ChunkPrimer)null);
    }

    @Override
    public Chunk provideChunk(BlockPos blockPosIn) {
        return this.provideChunk(blockPosIn.getX() >> 4, blockPosIn.getZ() >> 4);
    }
}