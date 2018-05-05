package net.journey.misc;

import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TextureTropical extends TextureAtlasSprite {

	public TextureTropical(String spriteName) {
		super(spriteName);
	}

	@SideOnly(Side.CLIENT)
	public static TextureAtlasSprite createTexture(ResourceLocation loc) {
		return TextureAtlasSprite.makeAtlasSprite(loc);
	}
}
