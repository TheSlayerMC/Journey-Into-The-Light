package net.jitl.common.item.gear;

import com.google.common.collect.Sets;
import net.jitl.common.helper.JToolTiers;
import net.jitl.common.item.gear.abilities.IAbility;
import net.jitl.init.JTabs;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MultitoolItem extends ToolItem implements JGear {
    IAbility ability;

    public MultitoolItem(JToolTiers tier, IAbility multiAbility) {
        super((int) tier.getShovelDam(), tier.getAttackSpeed(), tier, Sets.newHashSet(Registry.BLOCK), new Item.Properties().tab(JTabs.TOOLS)
                .addToolType(ToolType.HOE, tier.getLevel())
                .addToolType(ToolType.AXE, tier.getLevel())
                .addToolType(ToolType.PICKAXE, tier.getLevel())
                .addToolType(ToolType.SHOVEL, tier.getLevel()));
        ability = multiAbility;
    }

    @Override
    public boolean isCorrectToolForDrops(BlockState blockIn) {
        if (blockIn.is(Blocks.SNOW) || blockIn.is(Blocks.SNOW_BLOCK)) return true;
        int i = this.getTier().getLevel();
        if (blockIn.getHarvestTool() == net.minecraftforge.common.ToolType.PICKAXE)
            return i >= blockIn.getHarvestLevel();
        Material material = blockIn.getMaterial();
        return material == Material.STONE || material == Material.METAL || material == Material.HEAVY_METAL;
    }

    @Override
    public float getDestroySpeed(ItemStack stack, BlockState state) {
        Material material = state.getMaterial();
        float value = material != Material.METAL && material != Material.HEAVY_METAL && material != Material.STONE ? super.getDestroySpeed(stack, state) : this.speed;
        return ability.blockBreakSpeed(stack, state, value);
    }

    @Override
    public ActionResultType useOn(ItemUseContext context) {
        World world = context.getLevel();
        BlockPos blockpos = context.getClickedPos();
        int hook = net.minecraftforge.event.ForgeEventFactory.onHoeUse(context);
        if (hook != 0) return hook > 0 ? ActionResultType.SUCCESS : ActionResultType.FAIL;
        if (context.getClickedFace() != Direction.DOWN && world.isEmptyBlock(blockpos.above())) {
            BlockState blockstate = world.getBlockState(blockpos).getToolModifiedState(world, blockpos, context.getPlayer(), context.getItemInHand(), net.minecraftforge.common.ToolType.HOE);
            if (blockstate != null) {
                PlayerEntity playerentity = context.getPlayer();
                world.playSound(playerentity, blockpos, SoundEvents.HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
                if (!world.isClientSide) {
                    world.setBlock(blockpos, blockstate, 11);
                    if (playerentity != null) {
                        context.getItemInHand().hurtAndBreak(1, playerentity, (playerEntity_) -> {
                            playerEntity_.broadcastBreakEvent(context.getHand());
                        });
                    }
                }
                return ActionResultType.sidedSuccess(world.isClientSide);
            }
        }

        return ActionResultType.PASS;
    }

    @Override
    public IAbility getAbility() {
        return ability;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        ability.fillTooltips(stack, tooltip);
    }

    @Override
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        return ability.animate(super.shouldCauseReequipAnimation(oldStack, newStack, slotChanged), oldStack, newStack, slotChanged);
    }

    @Override
    public boolean shouldCauseBlockBreakReset(ItemStack oldStack, ItemStack newStack) {
        return ability.resetBreak(super.shouldCauseBlockBreakReset(oldStack, newStack), oldStack, newStack);
    }

    @Override
    public boolean mineBlock(ItemStack stack, World worldIn, BlockState state, BlockPos pos, LivingEntity entityLiving) {
        super.mineBlock(stack, worldIn, state, pos, entityLiving);
        ability.breakBlock(stack, worldIn, state, pos, entityLiving);
        return true;
    }
}
