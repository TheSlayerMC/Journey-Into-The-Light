package net.journey.entity.mob.buddy;

import net.journey.entity.base.JEntityBuddy;
import net.journey.items.base.JItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class EntitySpiritLurker extends JEntityBuddy {

	public EntitySpiritLurker(World w) {
		super(w);
	}

	public EntitySpiritLurker(World w, EntityPlayer owner, JItem bauble) {
		super(w, owner, bauble);
	}

}
