package net.journey.event;

import net.journey.client.render.gui.JourneyLoadingScreen;
import net.journey.client.render.gui.JourneyMainMenu;
import net.journey.client.server.EssenceProvider;
import net.journey.client.server.IEssence;
import net.journey.init.items.JourneyArmory;
import net.journey.items.interactive.ItemTeleport;
import net.journey.items.ranged.ItemGun;
import net.journey.items.ranged.ItemHammer;
import net.journey.items.ranged.ItemStaff;
import net.journey.util.Config;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.RenderTickEvent;
import net.slayer.api.SlayerAPI;
import org.lwjgl.opengl.GL11;

import java.util.Random;

public class ClientTickEvent {

	public static final ResourceLocation TEXTURE = new ResourceLocation(SlayerAPI.MOD_ID, "textures/gui/misc.png");
	private Item boots = null, body = null, legs = null, helmet = null;

	@SubscribeEvent
	public void onClientTick(TickEvent.ClientTickEvent event) throws Exception {
		Minecraft mc = Minecraft.getMinecraft();
		TickEvent.Phase phase = event.phase;
		TickEvent.Type type = event.type;

		if (phase == TickEvent.Phase.END) {
			if (type.equals(TickEvent.Type.CLIENT)) {
				if (Config.changeBackground == true) {
					if (!(mc.loadingScreen instanceof JourneyLoadingScreen)) {
						mc.loadingScreen = new JourneyLoadingScreen(mc);
					}
				}
			}
		}
	}

	@SubscribeEvent(priority = EventPriority.LOWEST)
	public void openGui(GuiOpenEvent event) {
		if (Config.changeMainMenu == true) {
			if (event.getGui() instanceof GuiMainMenu) {
				JourneyMainMenu customMainMenu = new JourneyMainMenu();
				if (customMainMenu != null) {
					event.setGui(customMainMenu);
				}
			}
		}
	}

	@SubscribeEvent
	public void clientTickEvent(PlayerTickEvent event) {
		ItemStack stackBoots = event.player.inventory.armorItemInSlot(0);
		ItemStack stackLegs = event.player.inventory.armorItemInSlot(1);
		ItemStack stackBody = event.player.inventory.armorItemInSlot(2);
		ItemStack stackHelmet = event.player.inventory.armorItemInSlot(3);
		if (stackBoots != null) boots = stackBoots.getItem();
		else boots = null;
		if (stackBody != null) body = stackBody.getItem();
		else body = null;
		if (stackLegs != null) legs = stackLegs.getItem();
		else legs = null;
		if (stackHelmet != null) helmet = stackHelmet.getItem();
		else helmet = null;
		Random rand = new Random();
		if (event.phase == Phase.END) {
			for (int i = 0; i < 2; i++) {
				if (helmet == JourneyArmory.flameHelmet && body == JourneyArmory.flameChest && legs == JourneyArmory.flameLegs && boots == JourneyArmory.flameBoots) {
					event.player.world.spawnParticle(EnumParticleTypes.FLAME, event.player.posX + rand.nextFloat() - 0.5D, event.player.posY + 0.1D, event.player.posZ + rand.nextFloat() - 0.5D, -event.player.motionX, +event.player.motionY + 0.2D, -event.player.motionZ);
					event.player.world.spawnParticle(EnumParticleTypes.FLAME, event.player.posX + rand.nextFloat() - 0.5D, event.player.posY + 0.1D, event.player.posZ + rand.nextFloat() - 0.5D, 0, 0, 0);
				}
			}
		}
	}

	@SubscribeEvent
	public void renderEvent(RenderTickEvent event) {
		if (Config.showManaBar)
			onTickRender(Minecraft.getMinecraft().player);
	}

	private void onTickRender(EntityPlayer player) {
		Minecraft mc = Minecraft.getMinecraft();
		if (player != null) {
			if (mc.currentScreen == null && player.getHeldItemMainhand() != null  && 
					player.getHeldItemMainhand().getItem() instanceof ItemStaff || 
					player.getHeldItemMainhand().getItem() instanceof ItemTeleport || 
					player.getHeldItemMainhand().getItem() instanceof ItemGun || 
					player.getHeldItemMainhand().getItem() instanceof ItemHammer) {
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
}