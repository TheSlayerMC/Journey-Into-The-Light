package net.jitl.init;

import net.jitl.JITL;
import net.jitl.common.world.gen.structures.euca.AlloyMenderStructure;
import net.jitl.common.world.gen.structures.euca.EucaDungeonStructure;
import net.jitl.common.world.gen.structures.euca.goldite.windmill.GolditeWindmillPieces;
import net.jitl.common.world.gen.structures.frozen.guardianruins.GuardianRuinPieces;
import net.jitl.common.world.gen.structures.overworld.AncientRuinsStructure;
import net.jitl.common.world.gen.structures.overworld.BlacksmithStructure;
import net.jitl.common.world.gen.structures.overworld.MageHouseStructure;
import net.jitl.common.world.gen.structures.overworld.guardian.GuardianTowerPieces;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.feature.StructurePieceType;
import ru.timeconqueror.timecore.api.registry.SimpleVanillaRegister;
import ru.timeconqueror.timecore.api.registry.util.AutoRegistrable;
import ru.timeconqueror.timecore.api.registry.util.Promised;

public class JStructurePieces {
    @AutoRegistrable
    private static final SimpleVanillaRegister<StructurePieceType> REGISTER = new SimpleVanillaRegister<>(JITL.MODID, Registry.STRUCTURE_PIECE);


    public static final Promised<StructurePieceType> GUARDIAN_TOWER_PIECE = REGISTER.register("gt/p", () -> GuardianTowerPieces.Piece::new);
    public static final Promised<StructurePieceType> GUARDIAN_TOWER_NO_GRASS_TOUCHED_PIECE = REGISTER.register("gt/ngtp", () -> GuardianTowerPieces.NoGrassTouchedPiece::new);

    public static final Promised<StructurePieceType> BLACKSMITH = REGISTER.register("bs", () -> BlacksmithStructure.Piece::new);
    public static final Promised<StructurePieceType> MAGE = REGISTER.register("mage", () -> MageHouseStructure.Piece::new);

    public static final Promised<StructurePieceType> ALLOY_MENDER = REGISTER.register("alloy_mender", () -> AlloyMenderStructure.Piece::new);
    public static final Promised<StructurePieceType> EUCA_DUNGEON = REGISTER.register("euca_dungeon", () -> EucaDungeonStructure.Piece::new);
    public static final Promised<StructurePieceType> GOLDITE_WINDMILL = REGISTER.register("goldite_windmill", () -> GolditeWindmillPieces.Piece::new);


    public static final Promised<StructurePieceType> GUARDIAN_RUIN = REGISTER.register("guardian_ruin", () -> GuardianRuinPieces.Piece::new);
    public static final Promised<StructurePieceType> ANCIENT_RUINS = REGISTER.register("ancient_ruins", () -> AncientRuinsStructure.Piece::new);



}