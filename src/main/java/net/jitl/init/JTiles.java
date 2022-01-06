package net.jitl.init;

import net.jitl.JITL;
import net.jitl.client.render.tile.EssenciaAltarTER;
import net.jitl.client.render.tile.LaserEmitterTER;
import net.jitl.client.render.tile.ObeliskTER;
import net.jitl.client.render.tile.PedestalTER;
import net.jitl.common.tile.*;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.ObjectHolder;
import ru.timeconqueror.timecore.api.registry.TileEntityRegister;
import ru.timeconqueror.timecore.api.registry.util.AutoRegistrable;

import static ru.timeconqueror.timecore.api.util.Hacks.promise;

import BlockEntityType;

@ObjectHolder(JITL.MODID)
public class JTiles {

    public static final BlockEntityType<LaserEmitterTile> LASER_EMITTER = promise();
    public static final BlockEntityType<JMobSpawnerTile> MOB_SPAWNER = promise();
    public static final BlockEntityType<EssenciaAltarTile> ESSENCIA_ALTAR = promise();
    public static final BlockEntityType<GuardianTowerBrainTile> GUARDIAN_TOWER_BRAIN = promise();
    public static final BlockEntityType<PedestalTile> PEDESTAL = promise();
    public static final BlockEntityType<PotTile> ANCIENT_POTTERY = promise();
    public static final BlockEntityType<PedestalTile> OBELISK = promise();


    private static class Registrator {
        @AutoRegistrable
        private static final TileEntityRegister REGISTER = new TileEntityRegister(JITL.MODID);

        @AutoRegistrable.InitMethod
        private static void register() {
            REGISTER.registerSingleBound("laser_emitter", LaserEmitterTile::new, () -> JBlocks.LASER_EMITTER).regCustomRenderer(() -> LaserEmitterTER::new);
            REGISTER.registerSingleBound("mob_spawner", JMobSpawnerTile::new, () -> JBlocks.TEST_SPAWNER);
            REGISTER.registerSingleBound("essencia_altar", EssenciaAltarTile::new, () -> JBlocks.ESSENCIA_ALTAR).regCustomRenderer(() -> EssenciaAltarTER::new);
            REGISTER.registerSingleBound("guardian_tower_brain", GuardianTowerBrainTile::new, () -> JBlocks.GUARDIAN_TOWER_BRAIN);
            REGISTER.registerSingleBound("pedestal", PedestalTile::new, () -> JBlocks.FROZEN_PEDESTAL).regCustomRenderer(() -> PedestalTER::new);
            REGISTER.registerSingleBound("ancient_pottery", PotTile::new, () -> JBlocks.ANCIENT_POTTERY);
            REGISTER.registerSingleBound("obelisk", ObeliskTile::new, () -> JBlocks.ANCIENT_OBELISK).regCustomRenderer(() -> ObeliskTER::new);

        }
    }
}
