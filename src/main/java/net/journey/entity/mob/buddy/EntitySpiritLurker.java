package net.journey.entity.mob.buddy;

import net.journey.entity.base.JEntityBuddy;
import net.journey.items.base.JItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

//TODO: create move helper to make it float in the air. also noclip
public class EntitySpiritLurker extends JEntityBuddy {

	public EntitySpiritLurker(World w) {
		super(w);
	}

	public EntitySpiritLurker(World w, EntityPlayer owner, JItem bauble) {
		super(w, owner, bauble);
	}

}
