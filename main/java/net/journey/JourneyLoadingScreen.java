package net.journey;

import net.minecraft.client.LoadingScreenRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.util.ResourceLocation;

public class JourneyLoadingScreen extends LoadingScreenRenderer {

    public static final ResourceLocation BACKGROUND = new ResourceLocation("textures/blocks/eucabricks.png");
    
	private String message = "";
	/** A reference to the Minecraft object. */
	private final Minecraft mc;
	/**
	 * The text currently displayed (i.e. the argument to the last call to
	 * printText or displayString)
	 */
	private String currentlyDisplayedText = "";
	/** The system's time represented in milliseconds. */
	private long systemTime = Minecraft.getSystemTime();
	/** True if the loading ended with a success */
	private boolean loadingSuccess;
	private final Framebuffer framebuffer;

	public JourneyLoadingScreen(Minecraft mcIn) {
		super(mcIn);
		this.mc = mcIn;

		this.framebuffer = new Framebuffer(mcIn.displayWidth, mcIn.displayHeight, false);
		this.framebuffer.setFramebufferFilter(9728);
	}

	@Override
	public void setLoadingProgress(int progress) {
		long i = Minecraft.getSystemTime();

		if (i - this.systemTime >= 100L) {
			this.systemTime = i;
			ScaledResolution scaledresolution = new ScaledResolution(this.mc);
			int j = scaledresolution.getScaleFactor();
			int k = scaledresolution.getScaledWidth();
			int l = scaledresolution.getScaledHeight();

			if (OpenGlHelper.isFramebufferEnabled()) {
				this.framebuffer.framebufferClear();
			} else {
				GlStateManager.clear(256);
			}

			this.framebuffer.bindFramebuffer(false);
			GlStateManager.matrixMode(5889);
			GlStateManager.loadIdentity();
			GlStateManager.ortho(0.0D, scaledresolution.getScaledWidth_double(),
					scaledresolution.getScaledHeight_double(), 0.0D, 100.0D, 300.0D);
			GlStateManager.matrixMode(5888);
			GlStateManager.loadIdentity();
			GlStateManager.translate(0.0F, 0.0F, -200.0F);

			if (!OpenGlHelper.isFramebufferEnabled()) {
				GlStateManager.clear(16640);
			}

			try {
				if (!net.minecraftforge.fml.client.FMLClientHandler.instance().handleLoadingScreen(scaledresolution))

				{
					Tessellator tessellator = Tessellator.getInstance();
					BufferBuilder worldrenderer = tessellator.getBuffer();
					this.mc.getTextureManager().bindTexture(BACKGROUND);
					float f = 32.0F;
					worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
					worldrenderer.pos(0.0D, (double) l, 0.0D).tex(0.0D, (double) ((float) l / f)).color(64, 64, 64, 255)
							.endVertex();
					worldrenderer.pos((double) k, (double) l, 0.0D)
							.tex((double) ((float) k / f), (double) ((float) l / f)).color(64, 64, 64, 255).endVertex();
					worldrenderer.pos((double) k, 0.0D, 0.0D).tex((double) ((float) k / f), 0.0D).color(64, 64, 64, 255)
							.endVertex();
					worldrenderer.pos(0.0D, 0.0D, 0.0D).tex(0.0D, 0.0D).color(64, 64, 64, 255).endVertex();
					tessellator.draw();

					if (progress >= 0) {
						int i1 = 100;
						int j1 = 2;
						int k1 = k / 2 - i1 / 2;
						int l1 = l / 2 + 16;
						GlStateManager.disableTexture2D();
						worldrenderer.begin(7, DefaultVertexFormats.POSITION_COLOR);
						worldrenderer.pos((double) k1, (double) l1, 0.0D).color(128, 128, 128, 255).endVertex();
						worldrenderer.pos((double) k1, (double) (l1 + j1), 0.0D).color(128, 128, 128, 255).endVertex();
						worldrenderer.pos((double) (k1 + i1), (double) (l1 + j1), 0.0D).color(128, 128, 128, 255)
								.endVertex();
						worldrenderer.pos((double) (k1 + i1), (double) l1, 0.0D).color(128, 128, 128, 255).endVertex();
						worldrenderer.pos((double) k1, (double) l1, 0.0D).color(128, 255, 128, 255).endVertex();
						worldrenderer.pos((double) k1, (double) (l1 + j1), 0.0D).color(128, 255, 128, 255).endVertex();
						worldrenderer.pos((double) (k1 + progress), (double) (l1 + j1), 0.0D).color(128, 255, 128, 255)
								.endVertex();
						worldrenderer.pos((double) (k1 + progress), (double) l1, 0.0D).color(128, 255, 128, 255)
								.endVertex();
						tessellator.draw();
						GlStateManager.enableTexture2D();
					}

					GlStateManager.enableBlend();
					GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
					this.mc.fontRenderer.drawStringWithShadow(this.currentlyDisplayedText,
							(float) ((k - this.mc.fontRenderer.getStringWidth(this.currentlyDisplayedText)) / 2),
							(float) (l / 2 - 4 - 16), 16777215);
					this.mc.fontRenderer.drawStringWithShadow(this.message,
							(float) ((k - this.mc.fontRenderer.getStringWidth(this.message)) / 2),
							(float) (l / 2 - 4 + 8), 16777215);
				}
			} catch (java.io.IOException e) {
				throw new RuntimeException(e);
			}

			this.framebuffer.unbindFramebuffer();

			if (OpenGlHelper.isFramebufferEnabled()) {
				this.framebuffer.framebufferRender(k * j, l * j);
			}

			this.mc.updateDisplay();

			try {
				Thread.yield();
			} catch (Exception var15) {
				;
			}
		}
	}

}
