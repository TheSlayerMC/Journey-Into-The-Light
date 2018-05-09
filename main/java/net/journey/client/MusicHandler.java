package net.journey.client;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundEventAccessor;
import net.minecraft.client.audio.SoundRegistry;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class MusicHandler implements IResourceManagerReloadListener {

	private static Map getMap(SoundRegistry reg) {
		Field[] fields = reg.getClass().getDeclaredFields();
		try {
			for(Field f : fields) {
				if(f.getType() == Map.class) {
					f.setAccessible(true);
					return (Map) f.get(reg);
				}
			}
		} catch(Exception e) { 
			e.printStackTrace();
		}
		return null;
	}

	private static SoundRegistry getSoundRegistry() {
		if(Minecraft.getMinecraft().getSoundHandler() != null) {
			Field[] fields = Minecraft.getMinecraft().getSoundHandler().getClass().getDeclaredFields();
			try {
				for(Field f : fields) {
					if(f.getType() == SoundRegistry.class) {
						f.setAccessible(true);
						return (SoundRegistry)f.get(Minecraft.getMinecraft().getSoundHandler());
					}
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public void onResourceManagerReload(IResourceManager var1) {
		clearMusic();
	}

	public void clearMusic() {
		SoundRegistry reg = getSoundRegistry();
		if(reg == null) return;
		Map soundMap = getMap(reg);
		Iterator i = soundMap.entrySet().iterator();
		while(i.hasNext()) {
			Map.Entry<ResourceLocation, SoundEventAccessorComposite> entry = (Map.Entry<ResourceLocation, SoundEventAccessorComposite>) i.next();
			SoundEventAccessorComposite sound = entry.getValue();
			if(sound.getSoundCategory() == SoundCategory.MUSIC) {
				List soundPool = getSoundPool(entry.getValue());
				for(int j = 0; j < soundPool.size(); j++) {
					if(soundPool.get(j) instanceof SoundEventAccessor) {
						SoundEventAccessor remSound = (SoundEventAccessor)soundPool.get(j);
						if(remSound.cloneEntry().getSoundPoolEntryLocation().getResourceDomain().equals("minecraft")) {
							soundPool.remove(j);
							j--;
						}
					}
				}
			} 
		}
	}

	private List getSoundPool(SoundEventAccessorComposite value) {
		Field[] fields = value.getClass().getDeclaredFields();
		try {
			for(Field f : fields) {
				if(f.getType() == List.class) {
					f.setAccessible(true);
					return (List)f.get(value);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
