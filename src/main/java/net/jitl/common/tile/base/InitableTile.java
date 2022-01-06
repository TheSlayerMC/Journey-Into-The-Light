package net.jitl.common.tile.base;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntityType;
import ru.timeconqueror.timecore.api.common.tile.SerializationType;
import ru.timeconqueror.timecore.api.common.tile.SyncableTile;

import javax.annotation.OverridingMethodsMustInvokeSuper;

public class InitableTile extends SyncableTile {
    private boolean firstAdded = true;

    public InitableTile(BlockEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public void onLoad() {
        if (firstAdded) {
            firstAdded = false;
            init();
        }
    }

    /**
     * Server-side only method.
     * Here you can init values for tile entity. This method will be called only once upon tile entity is added to world at the first time.
     */
    public void init() {

    }

    @Override
    protected void writeNBT(CompoundTag nbt, SerializationType type) {
        super.writeNBT(nbt, type);
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    protected void readNBT(BlockState state, CompoundTag nbt, SerializationType type) {
        super.readNBT(state, nbt, type);

        firstAdded = false;
    }
}
