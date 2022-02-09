package net.jitl.common.tile;

import net.jitl.common.tile.base.BaseBloodRuneTile;
import net.jitl.core.init.JTiles;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class BloodRuneDeathTile extends BaseBloodRuneTile {

    public BloodRuneDeathTile(BlockPos pos, BlockState state) {
        super(JTiles.BLOOD_RUNE_DEATH, pos, state);
    }

    @Override
    protected @NotNull Component getDefaultName() {
        return new TranslatableComponent("jitl.tile.blood_rune_death");
    }
}