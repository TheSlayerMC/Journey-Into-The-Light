package net.jitl.init;

import net.jitl.JITL;
import net.jitl.common.world.gen.structures.euca.AlloyMenderStructure;
import net.jitl.common.world.gen.structures.euca.EucaDungeonStructure;
import net.jitl.common.world.gen.structures.guardian.GuardianTowerPieces;
import net.jitl.common.world.gen.structures.overworld.BlacksmithStructure;
import net.jitl.common.world.gen.structures.overworld.MageHouseStructure;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import ru.timeconqueror.timecore.api.registry.SimpleVanillaRegister;
import ru.timeconqueror.timecore.api.registry.util.AutoRegistrable;
import ru.timeconqueror.timecore.api.registry.util.Promised;

public class JStructurePieces {
    @AutoRegistrable
    private static final SimpleVanillaRegister<IStructurePieceType> REGISTER = new SimpleVanillaRegister<>(JITL.MODID, Registry.STRUCTURE_PIECE);


    public static final Promised<IStructurePieceType> GUARDIAN_TOWER_PIECE = REGISTER.register("gt/p", () -> GuardianTowerPieces.Piece::new);
    public static final Promised<IStructurePieceType> GUARDIAN_TOWER_NO_GRASS_TOUCHED_PIECE = REGISTER.register("gt/ngtp", () -> GuardianTowerPieces.NoGrassTouchedPiece::new);

    public static final Promised<IStructurePieceType> BLACKSMITH = REGISTER.register("bs", () -> BlacksmithStructure.Piece::new);
    public static final Promised<IStructurePieceType> MAGE = REGISTER.register("mage", () -> MageHouseStructure.Piece::new);

    public static final Promised<IStructurePieceType> ALLOY_MENDER = REGISTER.register("alloy_mender", () -> AlloyMenderStructure.Piece::new);

    public static final Promised<IStructurePieceType> EUCA_DUNGEON = REGISTER.register("euca_dungeon", () -> EucaDungeonStructure.Piece::new);

}
