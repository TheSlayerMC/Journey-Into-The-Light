package net.journey;

import javax.annotation.Nullable;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.RegistryNamespaced;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.registries.GameData;
import net.slayer.api.SlayerAPI;

@EventBusSubscriber(modid = SlayerAPI.MOD_ID)
public class JourneySounds {
	
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
	public static SoundEvent BEAST_OF_THE_NETHER;
	public static SoundEvent BEAST_OF_THE_NETHER_HURT;
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
	
	public static SoundEvent RACE_TO_SHORE;
	public static SoundEvent DEEP_BLUE;
	public static SoundEvent COMPETITION_BEGINS; 
	public static SoundEvent RACE_STAR;
	public static SoundEvent BLUE_WATER;
	public static SoundEvent UNDERWATER_WORLD;
	

	public static void init() {

		CAVE_MOB = registerSound("cave_mob");
		ROCK = registerSound("rock");
		BASE_MOB_HURT = registerSound("base_mob_hurt");
		BOSS_DEATH = registerSound("boss_death");
		TERRA_SLUG = registerSound("terraslug");
		TERRA_SLUG_HURT = registerSound("terraslug_hurt");
		TERRA_SLUG_DEATH = registerSound("terraslug_death");
		SUMMON_BOSS = registerSound("summon_boss");
		SUMMON_TABLE = registerSound("summon_table");
		SORCERER_DEATH = registerSound("sorcerer_death");
		SORCERER_HURT = registerSound("sorcerer_hurt");
		SORCERER = registerSound("sorcerer");
		CHEST_OPEN_0 = registerSound("chest_open_0");
		CHEST_OPEN = registerSound("chest_open");
		CHEST_CLOSE = registerSound("chest_close");
		ROBOT = registerSound("robot");
		ROBOT_HURT = registerSound("robot_hurt");
		ROBOT_DEATH = registerSound("robot_death");
		PSYOLLOM = registerSound("psyollom");
		PSYOLLOM_HURT = registerSound("psyollom_hurt");
		INSECTO = registerSound("insecto");
		INSECTO_HURT = registerSound("insecto_hurt");
		SPIKED_BEAST = registerSound("spiked_beast");
		SPIKED_BEAST_HURT = registerSound("spiked_beast_hurt");
		MAGMA_GIANT = registerSound("magma_giant");
		MAGMA_GIANT_HURT = registerSound("magma_giant_hurt");
		SPYCLOPS = registerSound("spyclops");
		SPYCLOPS_HURT = registerSound("spyclops_hurt");
		TURTLE_HURT = registerSound("turtle_hurt");
		HONGO = registerSound("hongo");
		SMALL_HONGO = registerSound("small_hongo");
		SMALL_HONGO_HURT = registerSound("small_hongo_hurt");
		HONGO_HURT = registerSound("hongo_hurt");
		REAPER = registerSound("reaper");
		REAPER_HURT = registerSound("reaper_hurt");
		SAND_CRAWLER = registerSound("sand_crawler");
		DEPTHS_HUNTER = registerSound("depths_hunter");
		DEPTHS_HUNTER_HURT = registerSound("depths_hunter_hurt");
		BEAST_OF_THE_NETHER = registerSound("beast_of_the_nether");
		BEAST_OF_THE_NETHER_HURT = registerSound("beast_of_the_nether_hurt");
		CALCIA = registerSound("calcia");
		CALCIA_HURT = registerSound("calcia_hurt");
		STAFF = registerSound("staff");
		WRAPPER = registerSound("wrapper");
		PAGE_FLIP = registerSound("page_flip");
		MAGIC_SPARKLE = registerSound("magic_sparkle");
		CANNON = registerSound("cannon");
		PLASMA = registerSound("plasma");
		DYNASTER = registerSound("dynaster");
		DYNASTER_HURT = registerSound("dynaster_hurt");
		DYNASTER_DEATH = registerSound("dynaster_death");
		SHIMMERER = registerSound("shimmerer");
		SHIMMERER_HURT = registerSound("shimmerer_hurt");
		SHIMMERER_DEATH = registerSound("shimmerer_death");
		BUSH = registerSound("bush");
		BUSH_HURT = registerSound("bush_hurt");
		BUSH_DEATH = registerSound("bush_death");
		WRAITH = registerSound("wraith");
		WRAITH_DEATH = registerSound("wraith_death");
		WRAITH_HURT = registerSound("wraith_hurt");
		OVERSEER = registerSound("overseer");
		OVERSEER_HURT = registerSound("overseer_hurt");
		OVERSEER_DEATH = registerSound("overseer_death");
		HAMMER = registerSound("hammer");
		BIRD = registerSound("bird");
		BIRD_HURT = registerSound("bird_hurt");
		BIRD_DEATH = registerSound("bird_death");
		GATE_CREAK = registerSound("gate_creak");
		UNLOCK = registerSound("unlock");
		EMPTY = registerSound("empty");
		RACE_TO_SHORE = registerSound("race_shore");
		DEEP_BLUE = registerSound("deep_blue");
		COMPETITION_BEGINS = registerSound("comp_begins");
		RACE_STAR = registerSound("race_star");
		BLUE_WATER = registerSound("blue_water");
		UNDERWATER_WORLD = registerSound("underwater_world");
	}

	public static SoundEvent registerSound(String sound) {
		ResourceLocation LOC = new ResourceLocation(SlayerAPI.MOD_ID, sound);
		SoundEvent s = new SoundEvent(LOC);
		s.setRegistryName(LOC);
		
		ForgeRegistries.SOUND_EVENTS.register(s);
        Registrys.SOUNDS.add(s);
		return s;
	}

	public static void playSound(SoundEvent sound, World w, @Nullable EntityPlayer e){
		w.playSound(e, new BlockPos(e.posX, e.posY, e.posZ), sound, SoundCategory.NEUTRAL, 1.0F, 1.0F);
	}
	
	public static void playSound(SoundEvent sound, World w, @Nullable EntityLiving e){
		e.playSound(sound, 1.0F, 1.0F);
	}

	public static void playSound(SoundEvent sound, World w, @Nullable EntityLiving e, float volume, float pitch){
		e.playSound(sound, volume, pitch);
	}

	public static void playSound(SoundEvent sound, World w, @Nullable EntityPlayer e, float volume, float pitch){
		w.playSound(e, new BlockPos(e.posX, e.posY, e.posZ), sound, SoundCategory.NEUTRAL, volume, pitch);
	}
}