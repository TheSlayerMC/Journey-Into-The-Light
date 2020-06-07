package net.journey.eventhandler;

import net.journey.dimension.nether.JNWorldGenerator;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Random;

public class NetherEvent {

    /* Created by paulevs, from the Better Nether mod
     * Big thanks to him*/

    static NoiseGeneratorOctaves featureScatter;
    private static boolean worldLoaded;

    public static void init() {
        featureScatter = new NoiseGeneratorOctaves(new Random(1337), 3);
        worldLoaded = false;
    }

    @SubscribeEvent
    public void onWorldLoad(WorldEvent.Load event) {
        if (!worldLoaded && event.getWorld().provider.getDimensionType() == DimensionType.NETHER) {
            JNWorldGenerator.init(event.getWorld().getSeed());
            worldLoaded = true;
        }
    }

    @SubscribeEvent
    public void onPopulate(PopulateChunkEvent.Post event) {
        if (event.getWorld().provider.getDimensionType() == DimensionType.NETHER) {
            Random random = event.getRand();
            World world = event.getWorld();
            Chunk chunk = world.getChunk(event.getChunkX(), event.getChunkZ());
            JNWorldGenerator.generate(world, chunk, world.rand);
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public void onPrePopulate(PopulateChunkEvent.Pre event) {
        if (event.getWorld().provider.getDimensionType() == DimensionType.NETHER) {
            Chunk chunk = event.getWorld().getChunk(event.getChunkX(), event.getChunkZ());
            JNWorldGenerator.smoothChunk(chunk);
        }
    }
}
