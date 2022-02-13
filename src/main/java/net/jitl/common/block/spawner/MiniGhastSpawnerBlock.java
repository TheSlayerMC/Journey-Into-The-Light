package net.jitl.common.block.spawner;

import net.jitl.common.tile.spawner.MiniGhastSpawnerTile;
import net.jitl.core.init.JEntities;
import net.jitl.core.init.JTiles;
import net.jitl.core.util.JBlockProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SpawnerBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MiniGhastSpawnerBlock extends SpawnerBlock {

    public MiniGhastSpawnerBlock() {
        super(JBlockProperties.SPAWNER_PROPS.create());
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        MiniGhastSpawnerTile spawner = new MiniGhastSpawnerTile(pos, state);
        spawner.getSpawner().setEntityId(JEntities.MINI_GHAST_TYPE);
        return spawner;
    }

    @NotNull
    @Override
    public ItemStack getCloneItemStack(BlockGetter level_, BlockPos pos_, BlockState state_) {
        return new ItemStack(this);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level_, @NotNull BlockState state_, @NotNull BlockEntityType<T> blockEntityType_) {
        return createTickerHelper(blockEntityType_, JTiles.MINI_GHAST_SPAWNER, level_.isClientSide ? MiniGhastSpawnerTile::clientTick : MiniGhastSpawnerTile::serverTick);
    }
}
