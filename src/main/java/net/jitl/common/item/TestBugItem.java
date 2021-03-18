package net.jitl.common.item;

import net.jitl.JITL;
import net.jitl.client.dialogue.ClientDialogueNode;
import net.jitl.client.render.gui.dialogue.DialogueScreen;
import net.jitl.common.entity.projectile.base.JEffectCloudEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import ru.timeconqueror.timecore.api.util.Hacks;

import java.util.Arrays;

public class TestBugItem extends Item {
    public TestBugItem(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if (!worldIn.isClientSide()) {
            JEffectCloudEntity e = new JEffectCloudEntity(playerIn, worldIn, playerIn.position(), 0.5F);
            e.excludeOwner();

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
