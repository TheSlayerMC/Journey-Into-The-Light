package net.jitl.init;

import net.jitl.JITL;
import net.jitl.client.render.tile.EssenciaAltarTER;
import net.jitl.client.render.tile.LaserEmitterTER;
import net.jitl.common.block.tileentity.PedestalTile;
import net.jitl.common.tile.EssenciaAltarTile;
import net.jitl.common.tile.GuardianTowerBrainTile;
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
    public static final TileEntityType<JMobSpawnerTile> MOB_SPAWNER = promise();
    public static final TileEntityType<EssenciaAltarTile> ESSENCIA_ALTAR = promise();
    public static final TileEntityType<GuardianTowerBrainTile> GUARDIAN_TOWER_BRAIN = promise();
    public static final TileEntityType<PedestalTile> PEDESTAL = promise();


    private static class Registrator {
        @AutoRegistrable
        private static final TileEntityRegister REGISTER = new TileEntityRegister(JITL.MODID);

        @AutoRegistrable.InitMethod
        private static void register() {
            REGISTER.registerSingleBound("laser_emitter", LaserEmitterTile::new, () -> JBlocks.LASER_EMITTER).regCustomRenderer(() -> LaserEmitterTER::new);
            REGISTER.registerSingleBound("mob_spawner", JMobSpawnerTile::new, () -> JBlocks.TEST_SPAWNER);
            REGISTER.registerSingleBound("essencia_altar", EssenciaAltarTile::new, () -> JBlocks.ESSENCIA_ALTAR).regCustomRenderer(() -> EssenciaAltarTER::new);
            REGISTER.registerSingleBound("guardian_tower_brain", GuardianTowerBrainTile::new, () -> JBlocks.GUARDIAN_TOWER_BRAIN);
            REGISTER.registerSingleBound("pedestal", PedestalTile::new, () -> JBlocks.FROZEN_PEDESTAL);
        }
    }
}
