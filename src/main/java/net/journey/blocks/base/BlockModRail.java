package net.journey.blocks.base;

import net.journey.JITL;
import net.journey.init.JourneyTabs;
import net.journey.init.blocks.JourneyBlocks;
import net.minecraft.block.BlockRail;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.slayer.api.SlayerAPI;

public class BlockModRail extends BlockRail {

    private final boolean power;
    private final float speed;

    public BlockModRail(String name, boolean isPowered, float speed) {
        setCreativeTab(JourneyTabs.BLOCKS);
        setTranslationKey(SlayerAPI.PREFIX + name);
        JourneyBlocks.blocks.add(this);
        power = isPowered;
        this.speed = speed;
        setRegistryName(JITL.MOD_ID, name);

        JourneyBlocks.itemBlocks.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }

    @Override
    public float getRailMaxSpeed(World world, EntityMinecart cart, BlockPos pos) {
        return speed;
    }
}