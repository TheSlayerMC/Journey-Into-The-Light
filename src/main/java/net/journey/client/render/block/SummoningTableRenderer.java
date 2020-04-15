package net.journey.client.render.block;

import com.google.common.primitives.SignedBytes;
import net.journey.blocks.tileentity.TileEntitySummoningTable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.entity.RenderEntityItem;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class SummoningTableRenderer extends TileEntitySpecialRenderer {

    private Minecraft mc = Minecraft.getMinecraft();
    private RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
    private RenderEntityItem renderEntity;

    public SummoningTableRenderer() {
        renderEntity = new RenderEntityItem(Minecraft.getMinecraft().getRenderManager(), Minecraft.getMinecraft().getRenderItem()) {
            @Override
            protected int getTeamColor(EntityItem entityIn) {
                return SignedBytes.saturatedCast(Math.min(entityIn.getItem().getCount() / 32, 15) + 1);
            }

            @Override
            public boolean shouldBob() {
                return true;
            }

            @Override
            public boolean shouldSpreadItems() {
                return true;
            }
        };
    }

    @Override
    public void render(TileEntity t, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        TileEntitySummoningTable tile = (TileEntitySummoningTable) t;
        renderItem(tile.getStackInSlot(0), t.getWorld(), x + 0.85D, y, z + 0.85D, false);
        renderItem(tile.getStackInSlot(1), t.getWorld(), x + 0.85D, y, z + 0.5D, false);
        renderItem(tile.getStackInSlot(2), t.getWorld(), x + 0.85D, y, z + 0.15D, false);
        renderItem(tile.getStackInSlot(3), t.getWorld(), x + 0.5D, y, z + 0.5D, true);
        renderItem(tile.getStackInSlot(4), t.getWorld(), x + 0.15D, y, z + 0.85D, false);
        renderItem(tile.getStackInSlot(5), t.getWorld(), x + 0.15D, y, z + 0.5D, false);
        renderItem(tile.getStackInSlot(6), t.getWorld(), x + 0.15D, y, z + 0.15D, false);
    }

    public void renderItem(ItemStack stack, World w, double x, double y, double z, boolean middle) {
        if (stack != null) {
            float timeD = (float) (360.0 * (System.currentTimeMillis() & 0x3FFFL) / 0x3FFFL) * 8;
            float scale = middle ? 1.0F : 0.7F;
            GL11.glPushMatrix();
            GL11.glTranslated(x, y + 0.75D, z);
            GlStateManager.rotate(timeD, 0.0F, 1.0F, 0.0F);
            ItemStack i = stack;
            EntityItem item = new EntityItem(w, x, y, z, i);
            GL11.glScalef(scale, scale, scale);
            item.setItem(i);
            item.hoverStart = 0.0F;
            renderEntity.doRender(item, 0, 0, 0, 0, 0);
            GL11.glPopMatrix();
        }
    }
}