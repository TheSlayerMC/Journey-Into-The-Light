package net.journey.entity.mob.corba;

import net.journey.blocks.BlockTotem;
import net.minecraft.block.state.pattern.BlockPattern;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntitySpiritCrystal extends Entity {

	public EntitySpiritCrystal(World worldIn) {
		super(worldIn);

		setSize(1.0F, 1.0F);
	}

	@Override
	public boolean canBeCollidedWith() {
		return true;
	}

	//FIXME: Horrible FPS lag?? something's also wrong with the pattern
	@Override
	public void onEntityUpdate() {
		BlockPattern.PatternHelper totemPattern = BlockTotem.getTotemPattern().match(world, this.getPosition().add(0, 0, 0));
		if (totemPattern != null) {
			BlockPos blockpos = totemPattern.getFrontTopLeft().add(-1, 0, -1);
			world.setBlockState(blockpos.add(0, 1, 0), Blocks.OBSIDIAN.getDefaultState(), 2);
		}
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound nbtTagCompound) {
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound nbtTagCompound) {
	}

	@Override
	protected void entityInit() {
	}

	@Override
	public boolean shouldRenderInPass(int pass) {
		return pass == 1;
	}

	@Override
	public boolean processInitialInteract(EntityPlayer player, EnumHand hand) {
		if (!this.world.isRemote) {
			//if totems are activated, make shoot out a bunch of entityItems from a loot table
		}
		return true;
	}
}
