package net.jitl.client.render.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.jitl.JITL;
import net.jitl.common.container.ContainerEmpty;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.screen.inventory.MerchantScreen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;

import java.awt.*;

public class ScreenPlayerStats extends ContainerScreen<ContainerEmpty> {

    private final ResourceLocation knowledge_sprite = JITL.rl("textures/gui/knowledge/knowledge_sprites.png");
    private final ResourceLocation background = JITL.rl("textures/gui/stats.png");

    private final ScreenPlayerStats.PageButton[] pageButton = new ScreenPlayerStats.PageButton[2];

    public ScreenPlayerStats(PlayerInventory inv) {
        super(new ContainerEmpty(), inv, new StringTextComponent(""));
        this.imageWidth = 242;
        this.imageHeight = 204;
    }

    @Override
    protected void init() {
        super.init();
        int i = (this.width - this.imageWidth) / 2;
        int j = (this.height - this.imageHeight) / 2;
        int k = j + 16 + 2;

        for(int l = 0; l < 2; ++l) {
            this.pageButton[l] = this.addButton(new ScreenPlayerStats.PageButton(i + 5, k, l, (button_) -> { }));
            k += 20;
        }
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);

        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;


    }

    @Override
    protected void renderBg(MatrixStack matrixStack, float v, int i, int i1) {
        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;
        RenderSystem.pushMatrix();
        RenderSystem.enableRescaleNormal();
        minecraft.getTextureManager().bind(background);
        blit(matrixStack, x, y, 0, 0, imageWidth, imageHeight);
        RenderSystem.popMatrix();
        RenderSystem.enableDepthTest();


    }

    public void drawSprite(MatrixStack matrixStack, int x, int y, int spriteX, int spriteY, String s) {
        RenderSystem.pushMatrix();
        RenderSystem.enableRescaleNormal();
        int k = (width - imageWidth) / 2;
        int l = (height - imageHeight) / 2;
        minecraft.getTextureManager().bind(background);
        blit(matrixStack, k + x - 4, l + y - 4, 138, 212, 115, 40);
        minecraft.getTextureManager().bind(knowledge_sprite);
        blit(matrixStack, k + x, l + y, spriteX, spriteY, 32, 32);
        font.draw(matrixStack, s, k + x + 35, l + y + 5, 4210752);
        if(s == "Sentacoins:")
            font.draw(matrixStack, "x" + 156/*stats.getSentacoinValue()*/, k + x + 35, l + y + 15, 4210752);
        RenderSystem.popMatrix();
        RenderSystem.enableDepthTest();
    }

    public void drawKnowledgeSprite(MatrixStack matrixStack, int x, int y, int spriteX, int spriteY, /*EnumKnowledgeType type,*/ String s) {
        drawSprite(matrixStack, x, y, spriteX, spriteY, s);
        int progressBarSize = 65;
        int k = (width - imageWidth) / 2;
        int l = (height - imageHeight) / 2;
        RenderSystem.pushMatrix();
        RenderSystem.enableRescaleNormal();
        minecraft.getTextureManager().bind(knowledge_sprite);

       // PlayerStats.KnowledgeStorage knowledge = stats.getKnowledge(type);

        //float percents = knowledge.getAmountOnCurrentLevel() / knowledge.getLevelCapacity(knowledge.getLevelCount());
        float percents = 63F;
        int width = (int) (percents * progressBarSize);

        int progressBarX = k + x + 35, progressBarY = l + y + 19;

        blit(matrixStack, progressBarX, progressBarY, 0, 5, progressBarSize, 5);
        blit(matrixStack, progressBarX, progressBarY, 0, 0, width, 5);

        int lvX = progressBarX + 29, lvY = progressBarY - 1;

        int getLevelCount = 48;

        //font.draw(matrixStack, getLevelCount > 10 ? lvX - 2 : getLevelCount > 100 ? lvX - 4 : lvX, lvY, "" + getLevelCount, EnumHexColor.LIGHT_BLUE, EnumHexColor.BLACK);
        RenderSystem.popMatrix();
        RenderSystem.enableDepthTest();
    }

    @Override
    protected void renderLabels(MatrixStack matrixStack, int x, int y) { }

    private class PageButton extends Button {
        private final int index;

        public PageButton(int x, int y, int id, Button.IPressable pressable) {
            super(x, y, 12, 19, StringTextComponent.EMPTY, pressable);
            this.index = id;
            this.visible = true;
        }

        public int getIndex() {
            return this.index;
        }
    }
}