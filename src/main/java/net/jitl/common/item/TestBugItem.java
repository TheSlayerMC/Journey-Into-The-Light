package net.jitl.common.item;

import net.jitl.common.capability.player.JPlayer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import ru.timeconqueror.timecore.common.capability.CallbackProperty;

import java.util.Random;

public class TestBugItem extends Item {
    public TestBugItem(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
//        if (!worldIn.isClientSide()) {
//            JEffectCloudEntity e = new JEffectCloudEntity(playerIn, worldIn, playerIn.position(), 0.5F);
//            e.excludeOwner();
//
//        }

//        if (worldIn.isClientSide()) {
//            //TODO: create dialogue registry and network syncing *looks at TimeConqueror*
//            Minecraft.getInstance().setScreen(Hacks.safeCast(new DialogueScreen(new ClientDialogueNode(JITL.rl("floro"), "Hello! This line is supposed to very long to demonstrate wrapped text. So, how are you? How are the kids? Aw that's too bad, I hate my kids too. Anyways, see ya later.", Arrays.asList("helols", "hey", "heyyooooo")))));
//        }
        if (!worldIn.isClientSide() && playerIn.isShiftKeyDown()) {
            JPlayer cap = JPlayer.byPlayer(playerIn);
            CallbackProperty<Float> essence = cap.essence.get().currentEssence;
            System.out.println("Was: " + essence.get());
            essence.set(new Random().nextFloat() * 5);
            cap.detectAndSendChanges();
            System.out.println("Become: " + essence.get());
        }

        if (!playerIn.isShiftKeyDown()) {
            JPlayer cap = JPlayer.byPlayer(playerIn);
            CallbackProperty<Float> essence = cap.essence.get().currentEssence;

            System.out.println("essence.get() = " + essence.get());
        }

        return ActionResult.success(playerIn.getItemInHand(handIn));
    }


    public boolean isInMainHand(PlayerEntity player) {
        return player.getItemInHand(Hand.MAIN_HAND).getItem() == this;
    }
}
