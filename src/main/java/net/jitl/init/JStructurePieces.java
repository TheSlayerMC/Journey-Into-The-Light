package net.jitl.init;

import net.jitl.JITL;
import net.jitl.common.world.gen.structures.GuardianTowerPieces;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import ru.timeconqueror.timecore.registry.AutoRegistrable;
import ru.timeconqueror.timecore.registry.newreg.StructurePieceRegister;

public class JStructurePieces {
    @AutoRegistrable
    private static final StructurePieceRegister REGISTER = new StructurePieceRegister(JITL.MODID);

    public static final IStructurePieceType GUARDIAN_TOWER_PIECE = REGISTER.register("gt/p", GuardianTowerPieces.Piece::new);
    public static final IStructurePieceType GUARDIAN_TOWER_NO_GRASS_TOUCHED_PIECE = REGISTER.register("gt/ngtp", GuardianTowerPieces.NoGrassTouchedPiece::new);
}
