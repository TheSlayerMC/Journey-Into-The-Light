package net.jitl.common.item.gearabilities.celestium;

import net.jitl.common.item.gearabilities.BaseToolAbilities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class CelestiumToolAbilities extends BaseToolAbilities {
    public static final CelestiumToolAbilities INSTANCE = new CelestiumToolAbilities();

    @Override
    public void onSweep(ItemStack itemStack, Entity victim, LivingEntity attacker) {
        CompoundNBT tag = itemStack.hasTag() ? itemStack.getTag() : new CompoundNBT();
        tag.putInt("Swings", tag.contains("Swings") ? tag.getInt("Swings") + 1 : 1);
        System.out.println("Swings: " + tag.getInt("Swings"));
        itemStack.setTag(tag);
        //TODO: add cooldown reduction
    }

    @Override
    public double getSwordDamageModifier(LivingHurtEvent event) {
        if (event.getSource().getDirectEntity() instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) event.getSource().getDirectEntity();
            ItemStack stack = player.getMainHandItem();
            int swings = (stack.hasTag() ? stack.getTag() : new CompoundNBT()).getInt("Swings");
        }
        return 0;
    }
}
