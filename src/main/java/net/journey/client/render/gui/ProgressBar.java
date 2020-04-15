package net.journey.client.render.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class ProgressBar {
	
	private int value;
	private int maxValue = 100;

	public ProgressBar() { }

	public void setProgress(int progress){
		value = progress;
	}
	
	public void draw(int x, int y, int height, int width) {
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glEnable(GL11.GL_COLOR_MATERIAL);
		GL11.glPushMatrix();
		FontRenderer r = Minecraft.getMinecraft().fontRenderer;
		Tessellator t = Tessellator.getInstance();
        BufferBuilder tessellator = t.getBuffer();
		GL11.glDisable(3553);
		tessellator.begin(GL11.GL_QUADS, tessellator.getVertexFormat());
		tessellator.putColorRGB_F4(0.0F, 0.0F, 0.0F);
		tessellator.pos(x - width / 2 + 1, y + 1, 0.0);
		tessellator.pos(x - width / 2 + 1, y + height - 1, 0.0);
		tessellator.pos(x + width / 2 - 1, y + height - 1, 0.0);
		tessellator.pos(x + width / 2 - 1, y + 1, 0.0);
		
		int barWidth = (int) (((float) value) / ((float) maxValue) * ((width) - 6f));
		tessellator.putColorRGB_F4(1.0F -((float)value / (float)maxValue) * 0.8F, 0.2F + ((float)value / (float)maxValue) * 0.8F, 0.2F);
		tessellator.pos(x - width / 2 + 3, y + 3, 0.0);
		tessellator.pos(x - width / 2 + 3, y + height - 3, 0.0);
		tessellator.pos(x - width / 2 + 3 + barWidth, y + height - 3, 0.0);
		tessellator.pos(x - width / 2 + 3 + barWidth, y + 3, 0.0);
		t.draw();
		
		GL11.glPopMatrix();
		GL11.glEnable(3553);
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glDisable(GL11.GL_COLOR_MATERIAL);
	}
}