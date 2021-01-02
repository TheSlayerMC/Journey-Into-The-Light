package net.jitl.init;

import net.jitl.JITL;
import net.jitl.client.render.tile.LaserEmitterTER;
import net.jitl.common.tile.JMobSpawnerTile;
import net.jitl.common.tile.LaserEmitterTile;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.registries.ObjectHolder;
import ru.timeconqueror.timecore.api.registry.TileEntityRegister;
import ru.timeconqueror.timecore.api.registry.util.AutoRegistrable;

import static ru.timeconqueror.timecore.api.util.Hacks.promise;

@ObjectHolder(JITL.MODID)
public class JTiles {

    public static final TileEntityType<LaserEmitterTile> LASER_EMITTER = promise();
    public static final TileEntityType<JMobSpawnerTile> JMOB_SPAWNER = promise();

    private static class Registrator {
        @AutoRegistrable
        private static final TileEntityRegister REGISTER = new TileEntityRegister(JITL.MODID);

        @AutoRegistrable.InitMethod
        private static void register() {
            REGISTER.registerSingleBound("laser_emitter", LaserEmitterTile::new, () -> JBlocks.LASER_EMITTER).regCustomRenderer(() -> LaserEmitterTER::new);
            REGISTER.registerSingleBound("jmob_spawner", JMobSpawnerTile::new, () -> JBlocks.TEST_SPAWNER);
        }
    }
}
