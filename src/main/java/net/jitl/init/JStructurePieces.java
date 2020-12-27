package net.jitl.init;

import net.jitl.JITL;
import net.jitl.common.world.gen.structures.GuardianTowerPieces;
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
}
