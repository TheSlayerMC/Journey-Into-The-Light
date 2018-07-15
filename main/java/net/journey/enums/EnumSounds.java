package net.journey.enums;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public enum EnumSounds {

	CAVE_MOB("journey:caveMob"),
	ROCK("journey:rock"),
	BASE_MOB_HURT("journey:baseMobHurt"),
	BOSS_DEATH("journey:bossDeath"),
	SLUG("journey:terraSlug"),
	SLUG_HURT("journey:terraSlugHurt"),
	SLUG_DEATH("journey:terraSlugDeath"),
	SUMMON("journey:summonBoss"),
	SUMMON_TABLE("journey:summon"),
	SORCERER_DEATH("journey:sorcererDeath"),
	SORCERER_HURT("journey:sorcererHurt"),
	SORCERER("journey:sorcerer"),
	CHEST_OPEN_0("journey:chestOpen_0"),
	CHEST_OPEN("journey:chest"),
	CHEST_CLOSED("journey:chestClose"),
	ROBOT("journey:robot"),
	ROBOT_HURT("journey:robotHurt"),
	ROBOT_DEATH("journey:robotDeath"),
	PSYOLLOM("journey:psyollom"),
	PSYOLLOM_HURT("journey:psyollomHurt"),
	INSECTO("journey:insecto"),
	INSECTO_HURT("journey:turtleHurt"),
	SPIKED_BEAST("journey:spikedBeast"),
	SPIKED_BEAST_HURT("journey:spikedBeastHurt"),
	MAGMA_GIANT("journey:magmaGiant"),
	MAGMA_GIANT_HURT("journey:magmaGiantHurt"),
	SPYCLOPS("journey:spyclops"),
	SPYCLOPS_HURT("journey:spyclopsHurt"),
	TURTLE("journey:turtle"),
	TURTLE_HURT("journey:turtleHurt"),
	BUNNY("journey:bunny"),
	BUNNY_HURT("journey:bunnyHurt"),
	HONGO("journey:hongo"),
	SMALL_HONGO("journey:smallHongo"),
	SMALL_HONGO_HURT("journey:smallHongoHurt"),
	HONGO_HURT("journey:hongoHurt"),
	REAPER("journey:reaper"),
	REAPER_HURT("journey:reaperHurt"),
	SAND_CRAWLER("journey:sandCrawler"),
	DEPTHS_HUNTER("journey:depthsHunter"),
	DEPTHS_HUNTER_HURT("journey:depthsHunterHurt"),
	NETHER_BEAST("journey:netherBeast"),
	NETHER_BEAST_HURT("journey:netherBeastHurt"),
	CALCIA("journey:calcia"),
	CALCIA_HURT("journey:calciaHurt"),
	IRON_GOLEM_HURT("mob.irongolem.hit"),
	IRON_GOLEM_DEATH("mob.irongolem.death"),
	BLAZE("mob.blaze.breathe"),
	BLAZE_HURT("mob.blaze.hit"),
	BLAZE_DEATH("mob.blaze.death"),
	WITHER("mob.wither.idle"),
	WITHER_HURT("mob.wither.hurt"),
	WITHER_DEATH("mob.wither.death"),
	CREEPER("mob.creeper.say"),
	CREEPER_DEATH("mob.creeper.death"),
	STAFF("journey:staff"),
	WRAPPER("journey:wrapper"),
	PAGE_FLIP("journey:pageFlip"),
	SPARKLE("journey:magicSparkle"),
	CANNON("journey:cannon"),
	PLASMA("journey:plasma"),
	DYNASTER("journey:dynaster"),
	DYNASTER_HURT("journey:dynasterHurt"),
	DYNASTER_DEATH("journey:dynasterDeath"),
	SHIMMERER("journey:shimmerer"),
	SHIMMERER_HURT("journey:shimmererHurt"),
	SHIMMERER_DEATH("journey:shimmererDeath"),
	BUSH("journey:bush"),
	BUSH_DEATH("journey:bushDeath"),
	BUSH_HURT("journey:bushHurt"),
	WRAITH("journey:wraith"),
	WRAITH_DEATH("journey:wraithDeath"),
	WRAITH_HURT("journey:wraithHurt"),
	OVERSEER("journey:overseer"),
	OVERSEER_DEATH("journey:overseerDeath"),
	OVERSEER_HURT("journey:overeerHurt"),
	HAMMER("journey:hammer"),
	BIRD("journey:bird"),
	BIRD_HURT("journey:birdHurt"),
	BIRD_DEATH("journey:birdDeath"),
	CREAK("journey:gateCreak"),
	UNLOCK("journey:unlock"),
	EMPTY("");
	
	private SoundEvent sound;

	private EnumSounds(String sound) {
		this.sound = new SoundEvent(new ResourceLocation(sound));
	}
	
	public SoundEvent getPrefixedName() {
		return sound;
	}
	
	public SoundEvent getNonPrefixedName() {
		return sound;
	}
	
	public static void playSound(EnumSounds sound, World w, Entity e){
		//w.playSoundAtEntity(e, sound.getNonPrefixedName(), 1.0F, 1.0F);
	}
	
	public static void playSound(String sound, World w, Entity e){
		//w.playSoundAtEntity(e, sound, 1.0F, 1.0F);
	}
	
	public static void playSound(String sound, World w, EntityLivingBase e){
		//w.playSoundAtEntity(e, sound, 1.0F, 1.0F);
	}
	
	public static void playSound(String sound, World w, EntityLivingBase e, float volume, float pitch){
		//w.playSoundAtEntity(e, sound, volume, pitch);
	}
	
	public static void playSound(EnumSounds sound, World w, EntityLivingBase e){
		//w.playSoundAtEntity(e, sound.getNonPrefixedName(), 1.0F, 1.0F);
	}
	
	public static void playSound(EnumSounds sound, World w, EntityLivingBase e, float volume, float pitch){
		//w.playSoundAtEntity(e, sound.getNonPrefixedName(), volume, pitch);
	}
	
	public static void playSound(String sound, World w, TileEntity e){
		//w.playSound((double)e.getPos().getX(), (double)e.getPos().getY(), (double)e.getPos().getZ(), sound, 1.0F, 1.0F, true);
	}
	
	public static void playSound(String sound, World w, TileEntity e, float volume, float pitch){
		//w.playSound((double)e.getPos().getX(), (double)e.getPos().getY(), (double)e.getPos().getZ(), sound, volume, pitch, true);
	}
}