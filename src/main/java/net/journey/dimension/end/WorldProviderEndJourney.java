package net.journey.dimension.end;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Biomes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldServer;
import net.minecraft.world.biome.BiomeProviderSingle;
import net.minecraft.world.end.DragonFightManager;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class WorldProviderEndJourney extends WorldProvider {
    private DragonFightManager dragonFightManager;

    public void init() {
        this.biomeProvider = new BiomeProviderSingle(Biomes.SKY);
        NBTTagCompound nbttagcompound = this.world.getWorldInfo().getDimensionData(this.world.provider.getDimension());
        this.dragonFightManager = this.world instanceof WorldServer
                ? new DragonFightManager((WorldServer) this.world, nbttagcompound.getCompoundTag("DragonFight")) : null;
    }

    @Override
    public IChunkGenerator createChunkGenerator() {
        return new ChunkProviderEndJourney(this.world, this.world.getWorldInfo().isMapFeaturesEnabled(), this.world.getSeed(),
                this.getSpawnCoordinate());
    }

    @Override
    public float calculateCelestialAngle(long worldTime, float partialTicks) {
        return 0.0F;
    }

    @Nullable
    @SideOnly(Side.CLIENT)
    @Override
    public float[] calcSunriseSunsetColors(float celestialAngle, float partialTicks) {
        return null;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public Vec3d getFogColor(float par1, float par2) {
        int i = 10518688;
        float f = MathHelper.cos(par1 * ((float) Math.PI * 2F)) * 2.0F + 0.5F;
        f = MathHelper.clamp(f, 0.0F, 1.0F);
        float f1 = 0.627451F;
        float f2 = 0.5019608F;
        float f3 = 0.627451F;
        f1 = f1 * (f * 0.0F + 0.15F);
        f2 = f2 * (f * 0.0F + 0.15F);
        f3 = f3 * (f * 0.0F + 0.15F);
        return new Vec3d(f1, f2, f3);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public boolean isSkyColored() {
        return false;
    }

    @Override
    public boolean canRespawnHere() {
        return false;
    }

    @Override
    public boolean isSurfaceWorld() {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public float getCloudHeight() {
        return 8.0F;
    }

    @Override
    public boolean canCoordinateBeSpawn(int x, int z) {
        return this.world.getGroundAboveSeaLevel(new BlockPos(x, 0, z)).getMaterial().blocksMovement();
    }

    @Override
    public BlockPos getSpawnCoordinate() {
        return new BlockPos(100, 50, 0);
    }

    @Override
    public int getAverageGroundLevel() {
        return 50;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public boolean doesXZShowFog(int x, int z) {
        return false;
    }

    @Override
    public DimensionType getDimensionType() {
        return DimensionType.THE_END;
    }

    @Override
    public void onWorldSave() {
        NBTTagCompound nbttagcompound = new NBTTagCompound();

        if (this.dragonFightManager != null) {
            nbttagcompound.setTag("DragonFight", this.dragonFightManager.getCompound());
        }

        this.world.getWorldInfo().setDimensionData(this.world.provider.getDimension(), nbttagcompound);
    }

    @Override
    public void onWorldUpdateEntities() {
        if (this.dragonFightManager != null) {
            this.dragonFightManager.tick();
        }
    }

    @Nullable
    public DragonFightManager getDragonFightManager() {
        return this.dragonFightManager;
    }

    @Override
    public void onPlayerAdded(EntityPlayerMP player) {
        if (this.dragonFightManager != null) {
            this.dragonFightManager.addPlayer(player);
        }
    }

    @Override
    public void onPlayerRemoved(EntityPlayerMP player) {
        if (this.dragonFightManager != null) {
            this.dragonFightManager.removePlayer(player);
        }
    }
}