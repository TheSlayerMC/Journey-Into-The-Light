package net.jitl.common.item;

import net.jitl.JITL;
import net.jitl.client.dialogue.ClientDialogueNode;
import net.jitl.client.render.gui.dialogue.DialogueScreen;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import ru.timeconqueror.timecore.api.util.GenHelper;
import ru.timeconqueror.timecore.api.util.Hacks;

import java.util.Arrays;

public class TestBugItem extends Item {
    public TestBugItem(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if (!worldIn.isClientSide()) {

            GenHelper.genHollowCircle(10, Direction.Axis.Y, blockPos -> {
                worldIn.setBlockAndUpdate(new BlockPos(playerIn.position()).offset(blockPos), Blocks.DIAMOND_BLOCK.defaultBlockState());
            });

            GenHelper.genFilledCircle(10, Direction.Axis.Y, pos -> {
                worldIn.setBlockAndUpdate(new BlockPos(playerIn.position()).above().offset(pos), Blocks.STONE.defaultBlockState());
            });
        }

        if (worldIn.isClientSide()) {
            //TODO: create dialogue registry and network syncing *looks at TimeConqueror*
            Minecraft.getInstance().setScreen(Hacks.safeCast(new DialogueScreen(new ClientDialogueNode(JITL.rl("floro"), "Hello!", Arrays.asList("helols", "hey", "heyyooooo")))));
        }
        return ActionResult.success(playerIn.getItemInHand(handIn));
    }

    public boolean isInMainHand(PlayerEntity player) {
        return player.getItemInHand(Hand.MAIN_HAND).getItem() == this;
    }
}
