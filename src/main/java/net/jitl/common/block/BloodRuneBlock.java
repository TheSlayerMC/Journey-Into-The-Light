package net.jitl.common.block;

import net.jitl.common.entity.EssenciaBoltEntity;
import net.jitl.init.JEntities;
import net.jitl.init.JItems;
import net.jitl.init.JParticleManager;
import net.jitl.init.JSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

//TODO: I don't think we're going to use this (remove?)
public class BloodRuneBlock extends Block {

	public BloodRuneBlock(Properties properties) {
		super(properties);
	}

	@Override
	public @NotNull InteractionResult use(@NotNull BlockState state, @NotNull Level worldIn, @NotNull BlockPos pos, Player player, @NotNull InteractionHand handIn, @NotNull BlockHitResult hit) {
		ItemStack inHandItem = player.getItemInHand(handIn);
		if (inHandItem.getItem() == JItems.POWDER_OF_ESSENCIA) {
			for (ItemEntity itementity : worldIn.getEntitiesOfClass(ItemEntity.class, new AABB(pos.above()))) {
				Block rune = null;
				if (itementity.getItem().getItem() == Items.ROTTEN_FLESH) {
//					rune = JBlocks.BLOOD_RUNE_FLESH;
				}
				if (itementity.getItem().getItem() == Items.TORCH) {
//					rune = JBlocks.BLOOD_RUNE_SOUL;
				}
				if (itementity.getItem().getItem() == Items.BONE) {
//					rune = JBlocks.BLOOD_RUNE_DEATH;
				}
				if (itementity.getItem().getItem() == Items.APPLE) {
//					rune = JBlocks.BLOOD_RUNE_LIFE;
				}

				if (rune != null) {
					EssenciaBoltEntity essenciaBoltEntity = new EssenciaBoltEntity(JEntities.ESSENCIA_BOLT_TYPE, worldIn);
					essenciaBoltEntity.setPos(pos.getX(), pos.above().getY(), pos.getZ());
					essenciaBoltEntity.setARGB(0xff4800);
					essenciaBoltEntity.setVisualOnly(true);

					worldIn.addFreshEntity(essenciaBoltEntity);
					worldIn.setBlock(pos, rune.defaultBlockState(), 1);
					itementity.remove(Entity.RemovalReason.DISCARDED);
					worldIn.playSound(null, pos, JSounds.RUNE_ACTIVATE.get(), SoundSource.BLOCKS, 1.0F, player.getRandom().nextFloat() + 0.5F);
					if (!player.isCreative()) {
						inHandItem.shrink(1);
					}
					if (worldIn.isClientSide) {
						spawnParticles(worldIn, player, pos);
					}
				} else {
					return InteractionResult.FAIL;
				}
			}
		}
		return InteractionResult.PASS;
	}

	public void spawnParticles(Level worldIn, Player player, BlockPos pos) {
		for (int i = 0; i < 6; i++) {
			ParticleOptions particle = JParticleManager.RED_FLAME.get();
			int posThreshold = 16;
			int speedThreshold = 64;
			float posRandom0 = (float) player.getRandom().nextInt(2 + i) / posThreshold;
			float posRandom1 = (float) player.getRandom().nextInt(2 + i) / posThreshold;
			float posRandom2 = (float) player.getRandom().nextInt(2 + i) / posThreshold;
			float posRandom3 = (float) player.getRandom().nextInt(2 + i) / posThreshold;
			float speedRandom0 = (posRandom0 + 1) / speedThreshold;
			float speedRandom1 = (posRandom1 + 2) / speedThreshold;
			float speedRandom2 = (posRandom2 + 1) / speedThreshold;
			float speedRandom3 = (posRandom3 + 2) / speedThreshold;
			worldIn.addParticle(particle, (pos.getX() + posRandom0) + 0.5F, pos.above().getY(), (pos.getZ() - posRandom0) + 0.5F, speedRandom0, speedRandom2, -speedRandom3);
			worldIn.addParticle(particle, (pos.getX() - posRandom1) + 0.5F, pos.above().getY(), (pos.getZ() + posRandom1) + 0.5F, -speedRandom1, speedRandom1, speedRandom2);
			worldIn.addParticle(particle, (pos.getX() - posRandom2) + 0.5F, pos.above().getY(), (pos.getZ() - posRandom2) + 0.5F, -speedRandom2, speedRandom0, -speedRandom1);
			worldIn.addParticle(particle, (pos.getX() + posRandom3) + 0.5F, pos.above().getY(), (pos.getZ() + posRandom3) + 0.5F, speedRandom3, speedRandom3, speedRandom0);
		}
	}
}
