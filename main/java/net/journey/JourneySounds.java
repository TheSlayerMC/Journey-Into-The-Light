package net.journey;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.RegistryNamespaced;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.registries.GameData;
import net.slayer.api.SlayerAPI;

public class JourneySounds {

	private static int size = 0;
	
	public static SoundEvent CAVE_MOB;
	public static SoundEvent ROCK;
	public static SoundEvent BASE_MOB_HURT;
	public static SoundEvent BOSS_DEATH;
	public static SoundEvent TERRA_SLUG;
	public static SoundEvent TERRA_SLUG_HURT;
	public static SoundEvent TERRA_SLUG_DEATH;
	public static SoundEvent SUMMON_BOSS;
	public static SoundEvent SUMMON_TABLE;
	public static SoundEvent SORCERER_DEATH;
	public static SoundEvent SORCERER_HURT;
	public static SoundEvent SORCERER;
	public static SoundEvent CHEST_OPEN_0;
	public static SoundEvent CHEST_OPEN;
	public static SoundEvent CHEST_CLOSE;
	public static SoundEvent ROBOT;
	public static SoundEvent ROBOT_HURT;
	public static SoundEvent ROBOT_DEATH;
	public static SoundEvent PSYOLLOM;
	public static SoundEvent PSYOLLOM_HURT;
	public static SoundEvent INSECTO;
	public static SoundEvent INSECTO_HURT;
	public static SoundEvent SPIKED_BEAST;
	public static SoundEvent SPIKED_BEAST_HURT;
	public static SoundEvent MAGMA_GIANT;
	public static SoundEvent MAGMA_GIANT_HURT;
	public static SoundEvent SPYCLOPS;
	public static SoundEvent SPYCLOPS_HURT;
	public static SoundEvent TURTLE_HURT;
	public static SoundEvent HONGO;
	public static SoundEvent SMALL_HONGO;
	public static SoundEvent SMALL_HONGO_HURT;
	public static SoundEvent HONGO_HURT;
	public static SoundEvent REAPER;
	public static SoundEvent REAPER_HURT;
	public static SoundEvent SAND_CRAWLER;
	public static SoundEvent DEPTHS_HUNTER;
	public static SoundEvent DEPTHS_HUNTER_HURT;
	public static SoundEvent NETHER_BEAST;
	public static SoundEvent NETHER_BEAST_HURT;
	public static SoundEvent CALCIA;
	public static SoundEvent CALCIA_HURT;
	public static SoundEvent STAFF;
	public static SoundEvent WRAPPER;
	public static SoundEvent PAGE_FLIP;
	public static SoundEvent MAGIC_SPARKLE;
	public static SoundEvent CANNON;
	public static SoundEvent PLASMA;
	public static SoundEvent DYNASTER;
	public static SoundEvent DYNASTER_HURT;
	public static SoundEvent DYNASTER_DEATH;
	public static SoundEvent SHIMMERER;
	public static SoundEvent SHIMMERER_HURT;
	public static SoundEvent SHIMMERER_DEATH;
	public static SoundEvent BUSH;
	public static SoundEvent BUSH_HURT;
	public static SoundEvent BUSH_DEATH;
	public static SoundEvent WRAITH;
	public static SoundEvent WRAITH_DEATH;
	public static SoundEvent WRAITH_HURT;
	public static SoundEvent OVERSEER;
	public static SoundEvent OVERSEER_HURT;
	public static SoundEvent OVERSEER_DEATH;
	public static SoundEvent HAMMER;
	public static SoundEvent BIRD;
	public static SoundEvent BIRD_HURT;
	public static SoundEvent BIRD_DEATH;
	public static SoundEvent GATE_CREAK;
	public static SoundEvent UNLOCK;
	public static SoundEvent EMPTY;

	public static void init() {

		CAVE_MOB = registerSounds("cave_mob");
		ROCK = registerSounds("rock");
		BASE_MOB_HURT = registerSounds("base_mob_hurt");
		BOSS_DEATH = registerSounds("boss_death");
		TERRA_SLUG = registerSounds("terra_slug");
		TERRA_SLUG_HURT = registerSounds("terra_slug_hurt");
		TERRA_SLUG_DEATH = registerSounds("terra_slug_death");
		SUMMON_BOSS = registerSounds("summon_boss");
		SUMMON_TABLE = registerSounds("summon_table");
		SORCERER_DEATH = registerSounds("sorcerer_death");
		SORCERER_HURT = registerSounds("sorcerer_hurt");
		SORCERER = registerSounds("sorcerer");
		CHEST_OPEN_0 = registerSounds("chest_open_0");
		CHEST_OPEN = registerSounds("chest_open");
		CHEST_CLOSE = registerSounds("chest_close");
		ROBOT = registerSounds("robot");
		ROBOT_HURT = registerSounds("robot_hurt");
		ROBOT_DEATH = registerSounds("robot_death");
		PSYOLLOM = registerSounds("psyollom");
		PSYOLLOM_HURT = registerSounds("psyollom_hurt");
		INSECTO = registerSounds("insecto");
		INSECTO_HURT = registerSounds("insecto_hurt");
		SPIKED_BEAST = registerSounds("spiked_beast");
		SPIKED_BEAST_HURT = registerSounds("spiked_beast_hurt");
		MAGMA_GIANT = registerSounds("magma_giant");
		MAGMA_GIANT_HURT = registerSounds("magma_giant_hurt");
		SPYCLOPS = registerSounds("spyclops");
		SPYCLOPS_HURT = registerSounds("spyclops_hurt");
		TURTLE_HURT = registerSounds("turtle_hurt");
		HONGO = registerSounds("hongo");
		SMALL_HONGO = registerSounds("small_hongo");
		SMALL_HONGO_HURT = registerSounds("small_hongo_hurt");
		HONGO_HURT = registerSounds("hongo_hurt");
		REAPER = registerSounds("reaper");
		REAPER_HURT = registerSounds("reaper_hurt");
		SAND_CRAWLER = registerSounds("sand_crawler");
		DEPTHS_HUNTER = registerSounds("depths_hunter");
		DEPTHS_HUNTER_HURT = registerSounds("depths_hunter_hurt");
		NETHER_BEAST = registerSounds("nether_beast");
		NETHER_BEAST_HURT = registerSounds("nether_beast_hurt");
		CALCIA = registerSounds("calcia");
		CALCIA_HURT = registerSounds("calcia_hurt");
		STAFF = registerSounds("staff");
		WRAPPER = registerSounds("wrapper");
		PAGE_FLIP = registerSounds("page_flip");
		MAGIC_SPARKLE = registerSounds("magic_sparkle");
		CANNON = registerSounds("cannon");
		PLASMA = registerSounds("plasma");
		DYNASTER = registerSounds("dynaster");
		DYNASTER_HURT = registerSounds("dynaster_hurt");
		DYNASTER_DEATH = registerSounds("dynaster_death");
		SHIMMERER = registerSounds("shimmerer");
		SHIMMERER_HURT = registerSounds("shimmerer_hurt");
		SHIMMERER_DEATH = registerSounds("shimmerer_death");
		BUSH = registerSounds("bush");
		BUSH_HURT = registerSounds("bush_hurt");
		BUSH_DEATH = registerSounds("bush_death");
		WRAITH = registerSounds("wraith");
		WRAITH_DEATH = registerSounds("wraith_death");
		WRAITH_HURT = registerSounds("wraith_hurt");
		OVERSEER = registerSounds("overseer");
		OVERSEER_HURT = registerSounds("overseer_hurt");
		OVERSEER_DEATH = registerSounds("overseer_death");
		HAMMER = registerSounds("hammer");
		BIRD = registerSounds("bird");
		BIRD_HURT = registerSounds("bird_hurt");
		BIRD_DEATH = registerSounds("bird_death");
		GATE_CREAK = registerSounds("gate_creak");
		UNLOCK = registerSounds("unlock");
		EMPTY = registerSounds("empty");
	}

	public static SoundEvent registerSounds(String sound) {
		ResourceLocation LOC = new ResourceLocation(SlayerAPI.MOD_ID, sound);
		SoundEvent s = new SoundEvent(LOC);
		
		ForgeRegistries.SOUND_EVENTS.register(s);
		return s;
	}

	public static void playSound(SoundEvent sound, World w, EntityPlayer e){
		w.playSound(e, new BlockPos(e.posX, e.posY, e.posZ), sound, SoundCategory.NEUTRAL, 1.0F, 1.0F);
	}
	
	public static void playSound(SoundEvent sound, World w, EntityLiving e){
		e.playSound(sound, 1.0F, 1.0F);
	}

	public static void playSound(SoundEvent sound, World w, EntityLiving e, float volume, float pitch){
		e.playSound(sound, volume, pitch);
	}

	public static void playSound(SoundEvent sound, World w, EntityPlayer e, float volume, float pitch){
		w.playSound(e, new BlockPos(e.posX, e.posY, e.posZ), sound, SoundCategory.NEUTRAL, volume, pitch);
	}
}