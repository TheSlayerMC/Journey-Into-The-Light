package net.journey.entity.mob.corba;

import net.journey.JITL;
import net.journey.blocks.BlockTotem;
import net.journey.init.JourneyLootTables;
import net.journey.init.blocks.JourneyBlocks;
import net.journey.util.LootHelper;
import net.minecraft.block.Block;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

import java.util.List;
import java.util.Random;

public class EntitySpiritCrystal extends Entity {

	private int spawnTimer;

	public EntitySpiritCrystal(World worldIn) {
		super(worldIn);

		setSize(1.0F, 1.0F);
	}

	@Override
	public boolean canBeCollidedWith() {
		return true;
	}

	@Override
	public void onEntityUpdate() {
		if (ticksExisted % 20 == 0) {
			if (checkSide(world, JourneyBlocks.totemScared, BlockTotem.ACTIVATED, EnumFacing.EAST)
					&& checkSide(world, JourneyBlocks.totemAngry, BlockTotem.ACTIVATED, EnumFacing.WEST)
					&& checkSide(world, JourneyBlocks.totemHappy, BlockTotem.ACTIVATED, EnumFacing.SOUTH)
					&& checkSide(world, JourneyBlocks.totemSad, BlockTotem.ACTIVATED, EnumFacing.NORTH)) {

				if (spawnTimer == 0) {
					spawnTimer = 10;
					if (!world.isRemote) {
						Random r = new Random();
						List<ItemStack> lootTable = LootHelper.genFromLootTable(JourneyLootTables.LOOT_BASIC, (WorldServer) world, builder -> builder.withLootedEntity(this));
						int index = r.nextInt(lootTable.size());
						ItemStack itemToSpawn = lootTable.get(index);

						EntityItem item = new EntityItem(world, this.posX, this.posY, this.posZ, itemToSpawn);
						world.spawnEntity(item);
					}
				}
				//TODO: stop the timer once it's finished
				if (spawnTimer >= 0) {
					spawnTimer--;
				}

				if (spawnTimer <= 0) {
					spawnTimer = 0;
				}

				JITL.LOGGER.info("" + spawnTimer);
			}
		}
	}

	private boolean checkSide(World world, Block requiredBlock, PropertyBool activatedProp, EnumFacing direction) {
		IBlockState state = world.getBlockState(getPosition().offset(direction, 4));

		return state.getBlock() == requiredBlock && state.getValue(activatedProp);
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound nbtTagCompound) {
		spawnTimer = nbtTagCompound.getInteger("spawnTimer");
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound nbtTagCompound) {
		nbtTagCompound.setInteger("spawnTimer", 0);
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
