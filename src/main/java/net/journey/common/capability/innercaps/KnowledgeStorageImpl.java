package net.journey.common.capability.innercaps;

import net.journey.api.capability.PlayerStats;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

public class KnowledgeStorageImpl implements PlayerStats.KnowledgeStorage, INBTSerializable<NBTTagCompound> {
	private float amountOnLevel;
	private int levels;

	@Override
	public void add(float amount) {
		if (amountOnLevel + amount >= getLevelCapacity(levels)) {
			amountOnLevel = amountOnLevel + amount - getLevelCapacity(levels);
			levels += 1;
		} else {
			amountOnLevel += amount;
		}
	}

	@Override
	public float remove(float amount) {
		float total = getTotal();

		//nullifies stored knowledge if amount to remove is bigger than stored one
		if (amount > total) {
			float left = amount - total;

			levels = 0;
			amountOnLevel = 0;

			return left;
		}

		if (amountOnLevel - amount < 0) {
			amount -= amountOnLevel;

			while (amount > 0) {
				float levelCapacity = getLevelCapacity(levels);
				if (levelCapacity > amount) {
					levels--;
					amountOnLevel = levelCapacity - amount;
					return 0;
				} else {
					amount -= levelCapacity;
					levels--;
				}
			}

			throw new IllegalStateException("This shouldn't be achieved, because if all levels' acapacity is smaller than removed amount, it should be cut in the start of the method");
		} else {
			amountOnLevel -= amount;
			return 0;
		}
	}

	@Override
	public float getLevelCapacity(int level) {
		return 32;//TODO change to formula, where cap increases on every level
	}

	@Override
	public float getAmountOnCurrentLevel() {
		return amountOnLevel;
	}

	@Override
	public int getLevelCount() {
		return levels;
	}

	@Override
	public float getTotal() {
		float amount = 0;

		for (int i = 0; i < levels; i++) {
			amount += getLevelCapacity(levels);
		}

		return amount + amountOnLevel;
	}

	@Override
	public NBTTagCompound serializeNBT() {
		NBTTagCompound compound = new NBTTagCompound();
		compound.setFloat("amount_on_level", amountOnLevel);
		compound.setInteger("levels", levels);

		return compound;
	}

	@Override
	public void deserializeNBT(NBTTagCompound nbt) {
		amountOnLevel = nbt.getFloat("amount_on_level");
		levels = nbt.getInteger("levels");
	}
}