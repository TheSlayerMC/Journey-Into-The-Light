package net.journey.client.render;

import org.lwjgl.opengl.GL11;

import net.journey.JITL;
import net.journey.client.server.EssenceProvider;
import net.journey.client.server.IEssence;
import net.journey.init.items.JourneyItems;
import net.journey.init.items.JourneyWeapons;
import net.journey.items.interactive.ItemAddEssence;
import net.journey.items.interactive.ItemEternalNight;
import net.journey.items.interactive.ItemTeleport;
import net.journey.items.ranged.ItemGun;
import net.journey.items.ranged.ItemHammer;
import net.journey.items.ranged.ItemStaff;
import net.journey.util.Config;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.RenderTickEvent;

public class RenderEssenceBar {

	public static final ResourceLocation TEXTURE = new ResourceLocation(JITL.MOD_ID, "textures/gui/misc.png");
	
    @SubscribeEvent
    public void onRender(RenderGameOverlayEvent.Post event) {
        if (event.getType() == RenderGameOverlayEvent.ElementType.ALL) {
        	if (Config.showManaBar == true) {
                onTickRender(FMLClientHandler.instance().getClientPlayerEntity());
        	}
        }
    }

	private void onTickRender(EntityPlayer player) {
		Minecraft mc = Minecraft.getMinecraft();
		if (player != null) {
			if (mc.currentScreen == null && player.getHeldItemMainhand() != null && instanceOfEssenceItem(player.getHeldItemMainhand().getItem())) {
				IEssence mana = player.getCapability(EssenceProvider.ESSENCE_CAP, null);
				if(!mc.gameSettings.hideGUI) {
					GL11.glPushMatrix();
					GlStateManager.enableBlend();
					GlStateManager.enableAlpha();
					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
					GuiIngame gig = mc.ingameGUI;
					ScaledResolution scaledresolution = new ScaledResolution(mc);
					mc.getTextureManager().bindTexture(TEXTURE);
					int y = scaledresolution.getScaledHeight() - 30, x = 10;
					gig.drawTexturedModalRect(x - 10, y + 10, 0, 177, 117, 19);
					gig.drawTexturedModalRect(x - 6, y + 17, 0, 23, 109, 5);
					for (int i = 0; i < mana.getEssenceValue(); i++) {
						if (!(i >= 10)) {
							x += 11;
							gig.drawTexturedModalRect(x - 17, y + 17, 0, 0, 10, 5);
						}
					}
					GlStateManager.disableAlpha();
					GlStateManager.disableBlend();
					GL11.glPopMatrix();
				}
			}
		}
	}
	
	public boolean instanceOfEssenceItem(Item isEssence) {
		return  isEssence instanceof ItemStaff || 
				isEssence instanceof ItemTeleport || 
				isEssence instanceof ItemGun || 
				isEssence instanceof ItemHammer || 
				isEssence instanceof ItemEternalNight || 
				isEssence instanceof ItemAddEssence ||
				isEssence == JourneyWeapons.darkEnforcer ||
				isEssence == JourneyItems.CURSED_TOMB ||
				isEssence == JourneyItems.CHARM_OF_WATER_BENDING ||
				isEssence == JourneyWeapons.ESSENCE_SHURIKEN;
	}
}
