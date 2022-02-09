package net.jitl.common.tile;

import net.jitl.common.tile.base.BaseBloodRuneTile;
import net.jitl.core.init.JTiles;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class BloodRuneLifeTile extends BaseBloodRuneTile {

    public BloodRuneLifeTile(BlockPos pos, BlockState state) {
        super(JTiles.BLOOD_RUNE_LIFE, pos, state);
    }

    @Override
    protected @NotNull Component getDefaultName() {
        return new TranslatableComponent("jitl.tile.blood_rune_life");
    }
}