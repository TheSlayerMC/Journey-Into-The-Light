package net.jitl.common.tile;

import net.jitl.init.JTiles;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;

public class EssenciaAltarTile extends TileEntity implements ITickableTileEntity {
    public EssenciaAltarTile() {
        super(JTiles.ESSENCIA_ALTAR);
    }

    @Override
    public void tick() {

    }
}
