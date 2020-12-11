package net.jitl.client.render.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.jitl.JITL;
import net.jitl.common.container.ContainerEmpty;
import net.jitl.common.helper.EnumHexColor;
import net.jitl.common.helper.EnumKnowledgeType;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.opengl.GL11;

public class ScreenPlayerStats extends ContainerScreen<ContainerEmpty> {

    private final ResourceLocation knowledge_sprite = JITL.rl("textures/gui/knowledge/knowledge_sprites.png");
    private final ResourceLocation background = JITL.rl("textures/gui/stats.png");

    private ScreenPlayerStats.PageButton nextButton;
    private ScreenPlayerStats.PageButton previousButton;
    //private final PlayerStats stats;
    private int pageNumber = 0;

    public ScreenPlayerStats(PlayerInventory inv) {
        super(new ContainerEmpty(), inv, new StringTextComponent(""));
        this.imageWidth = 242;
        this.imageHeight = 204;
       // this.stats = JCapabilityManager.asJourneyPlayer(Minecraft.getInstance().player).getPlayerStats();
    }

    @Override
    protected void init() {
        super.init();
        int i = (this.width - this.imageWidth) / 2;
        int j = (this.height - this.imageHeight) / 2;
        int k = j + 16 + 2;

        this.nextButton = this.addButton(new ScreenPlayerStats.PageButton(i + 5, k, false, (button_) -> { }));
        this.previousButton = this.addButton(new ScreenPlayerStats.PageButton(i + 5, k + 20, true, (button_) -> { }));
    }

    @Override
    protected void renderBg(@NotNull MatrixStack matrixStack, float v, int i, int i1) {
        this.renderBackground(matrixStack);

        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;
        RenderSystem.pushMatrix();
        RenderSystem.enableRescaleNormal();
        assert minecraft != null;
        minecraft.getTextureManager().bind(background);
        blit(matrixStack, x, y, 0, 0, imageWidth, imageHeight);
        switch(pageNumber) {
            case 0:
                page1(matrixStack);
                break;
            case 1:
                page2(matrixStack);
                break;
            default:
                break;
        }
        RenderSystem.popMatrix();
        RenderSystem.enableDepthTest();
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int buttonIn) {

        return super.mouseClicked(mouseX, mouseY, buttonIn);
    }

    public void page1(MatrixStack stack) {
        int height = 43;
        int x = 9;
        int h = 9;
        drawSprite(stack, x, h, 0, 74, "Sentacoins:");
        drawKnowledgeSprite(stack, 126, h, 32, 10, EnumKnowledgeType.OVERWORLD, "Overworld");

        h += height;

        drawKnowledgeSprite(stack, 126, h, 160, 10, EnumKnowledgeType.FROZEN, "Frozen Lands");
        drawKnowledgeSprite(stack, x, h, 64, 10, EnumKnowledgeType.NETHER, "Nether");

        h += height;

        drawKnowledgeSprite(stack, 126, h, 96, 10, EnumKnowledgeType.END, "End");
        drawKnowledgeSprite(stack, x, h, 128, 10, EnumKnowledgeType.BOIL, "Boiling Point");

        h += height - 2;

        drawKnowledgeSprite(stack, x, h, 192, 10, EnumKnowledgeType.EUCA, "Euca");
        drawKnowledgeSprite(stack, 126, h, 224, 10, EnumKnowledgeType.DEPTHS, "The Depths");
    }

    public void page2(MatrixStack stack) {
        int height = 43;
        int x = 9;
        int h = 9;

        drawKnowledgeSprite(stack, x, h, 0, 42, EnumKnowledgeType.CORBA, "Corba");
        drawKnowledgeSprite(stack, 126, h, 32, 42, EnumKnowledgeType.TERRANIA, "Terrania");

        h += height;

        drawKnowledgeSprite(stack, x, h, 64, 42, EnumKnowledgeType.CLOUDIA, "Cloudia");
        drawKnowledgeSprite(stack, 126, h, 96, 42, EnumKnowledgeType.SENTERIAN, "Senterain");
		/*h += height;
		drawKnowledgeSprite(x, h, 128, 10, 0.30F, "SDHFSDH");
		drawKnowledgeSprite(126, h, 160, 10, 0.75F, "Frozen Lands");
		h+= height - 2;
		drawKnowledgeSprite(x, h, 192, 10, 0.10F, "Euca");
		drawKnowledgeSprite(126, h, 224, 10, 0.60F, "The Depths");*/
    }

    public void drawSprite(MatrixStack matrixStack, int x, int y, int spriteX, int spriteY, String s) {
        RenderSystem.pushMatrix();
        RenderSystem.enableRescaleNormal();
        int k = (width - imageWidth) / 2;
        int l = (height - imageHeight) / 2;
        assert minecraft != null;
        minecraft.getTextureManager().bind(background);
        blit(matrixStack, k + x - 4, l + y - 4, 138, 212, 115, 40);
        minecraft.getTextureManager().bind(knowledge_sprite);
        blit(matrixStack, k + x, l + y, spriteX, spriteY, 32, 32);
        font.draw(matrixStack, s, k + x + 35, l + y + 5, 4210752);
        if(s.equals("Sentacoins:"))
            font.draw(matrixStack, "x" + 156/*stats.getSentacoinValue()*/, k + x + 35, l + y + 15, 4210752);
        RenderSystem.popMatrix();
        RenderSystem.enableDepthTest();
    }

    public void drawKnowledgeSprite(MatrixStack matrixStack, int x, int y, int spriteX, int spriteY, EnumKnowledgeType type, String s) {
        drawSprite(matrixStack, x, y, spriteX, spriteY, s);
        int progressBarSize = 65;
        int k = (width - imageWidth) / 2;
        int l = (height - imageHeight) / 2;
        RenderSystem.pushMatrix();
        RenderSystem.enableRescaleNormal();
        assert minecraft != null;
        minecraft.getTextureManager().bind(knowledge_sprite);

        //PlayerStats.KnowledgeStorage knowledge = stats.getKnowledge(type);

        //float percents = knowledge.getAmountOnCurrentLevel() / knowledge.getLevelCapacity(knowledge.getLevelCount());
        float percents = 64F;
        int width = (int) (percents * progressBarSize);

        int progressBarX = k + x + 35, progressBarY = l + y + 19;

        blit(matrixStack, progressBarX, progressBarY, 0, 5, progressBarSize, 5);
        blit(matrixStack, progressBarX, progressBarY, 0, 0, width, 5);

        int lvX = progressBarX + 29, lvY = progressBarY - 1;

        int getLevelCount = 48;

        font.drawShadow(matrixStack, "" + getLevelCount, getLevelCount > 10 ? lvX - 2 : getLevelCount > 100 ? lvX - 4 : lvX, lvY, EnumHexColor.LIGHT_BLUE.getInt());
        RenderSystem.popMatrix();
        RenderSystem.enableDepthTest();
    }

    @Override
    public void tick() {
        super.tick();
        this.nextButton.active = pageNumber < 1;
        this.previousButton.active = pageNumber > 0;
    }

    @Override
    protected void renderLabels(@NotNull MatrixStack matrixStack, int x, int y) { }

    private class PageButton extends Button implements Button.IPressable {
        private final boolean prev;

        public PageButton(int x, int y, Boolean prev, Button.IPressable pressable) {
            super(x, y, 12, 19, StringTextComponent.EMPTY, pressable);
            this.prev = prev;
            this.visible = true;
        }

        @Override
        public void onPress(@NotNull Button b) {//why wont this call
            if(prev) {
                pageNumber++;
                System.out.println("!!!!!");
            } else {
                pageNumber--;
                System.out.println("XXXXX");
            }
        }

        @Override
        public void renderButton(@NotNull MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
            assert minecraft != null;
            minecraft.getTextureManager().bind(JITL.rl("textures/gui/stats.png"));
            RenderSystem.pushMatrix();
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
            RenderSystem.enableDepthTest();
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            height = 15;
            boolean flag = x >= this.x && y >= this.y && x < this.x + this.width && y < this.y + this.height;
            int k = 217;
            int l = 4;

            if (!this.active) {
                l += this.width * 2;
            } else if (flag) {
                l += this.width;
            }

            if (!this.prev) {
                k += this.height;
            }
            this.blit(matrixStack, this.x, this.y, l, k, this.width, this.height);
            RenderSystem.disableBlend();
            RenderSystem.disableDepthTest();
            RenderSystem.popMatrix();
        }
    }
}