package net.jitl.client.render.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.jitl.common.capability.JCapabilityProvider;
import net.jitl.common.container.ContainerEmpty;
import net.jitl.common.helper.ArgbColor;
import net.jitl.common.helper.EnumKnowledgeType;
import net.jitl.common.knowledge.PlayerStats;
import net.jitl.core.JITL;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.PageButton;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import org.jetbrains.annotations.NotNull;

public class ScreenPlayerStats extends AbstractContainerScreen<ContainerEmpty> {

    private PageButton nextButton;
    private PageButton previousButton;
    private final ResourceLocation KNOWLEDGE_SPRITE = JITL.rl("textures/gui/knowledge/knowledge_sprites.png");
    private final ResourceLocation BACKGROUND = JITL.rl("textures/gui/stats.png");
    public int pageNumber = 0;
    //private final PlayerStats stats;

    public ScreenPlayerStats(Inventory playerInventory) {
        super(new ContainerEmpty(), playerInventory, new TranslatableComponent("jitl.stats"));
        this.imageWidth = 242;
        this.imageHeight = 204;
        //this.stats = JCapabilityProvider.asJourneyPlayer(Minecraft.getInstance().player).getPlayerStats();
    }

    @Override
    protected void init() {
        super.init();
        int i = (this.width - this.imageWidth) / 2;
        int j = (this.height - this.imageHeight) / 2;
        int k = j + 16 + 2;

        this.nextButton = this.addWidget(new PageButton(i + 5, k, false, new StatButtonPress(i + 5, k, false), true));
        this.previousButton = this.addWidget(new PageButton(i + 5, k + 20, true, new StatButtonPress(i + 5, k + 20, true), true));
    }

    class StatButtonPress extends Button implements Button.OnPress {

        private final boolean forward;
        public StatButtonPress(int x, int y, boolean forward) {
            super(x, y, 12, 19, TextComponent.EMPTY, (button_) -> { });
            this.forward = forward;
        }
        @Override
        public void onPress(@NotNull Button button) {
            if(forward) pageNumber++;
            else pageNumber--;
        }

        @Override
        public void renderButton(@NotNull PoseStack matrixStack, int mouseX, int mouseY, float partialTicks) {
            matrixStack.pushPose();
            RenderSystem.setShader(GameRenderer::getPositionTexColorNormalShader);
            RenderSystem.setShaderTexture(0, BACKGROUND);
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
            RenderSystem.enableDepthTest();
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            height = 15;
            boolean flag = x >= this.x && y >= this.y && x < this.x + this.width && y < this.y + this.height;
            int k = 217;
            int l = 4;

            if (!this.active) {
                l += this.width * 2;
            } else if (flag) {
                l += this.width;
            }

            if (this.forward) {
                k += this.height;
            }
            this.blit(matrixStack, this.x, this.y, l, k, this.width, this.height);
            RenderSystem.disableBlend();
            RenderSystem.disableDepthTest();
            matrixStack.popPose();
        }
    }

    @Override
    protected void renderBg(PoseStack poseStack, float partialTick, int mouseX, int mouseY) {
        this.renderBackground(poseStack);

        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;

        poseStack.pushPose();
        RenderSystem.setShader(GameRenderer::getPositionTexColorNormalShader);
        RenderSystem.setShaderTexture(0, this.BACKGROUND);

        blit(poseStack, x, y, 0, 0, this.imageWidth, this.imageHeight);
        switch(pageNumber) {
            case 0:
                page1(poseStack);
                break;
            case 1:
                //page2(poseStack);
                break;
            default:
                break;
        }
        poseStack.popPose();
        RenderSystem.enableDepthTest();
    }

    public void page1(PoseStack stack) {
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

    public void drawSprite(PoseStack matrixStack, int x, int y, int spriteX, int spriteY, String s) {

        int k = (width - imageWidth) / 2;
        int l = (height - imageHeight) / 2;
        matrixStack.pushPose();
        RenderSystem.setShader(GameRenderer::getPositionTexColorNormalShader);
        RenderSystem.setShaderTexture(0, this.BACKGROUND);
        blit(matrixStack, k + x - 4, l + y - 4, 138, 212, 115, 40);
        matrixStack.popPose();

        matrixStack.pushPose();
        RenderSystem.setShader(GameRenderer::getPositionTexColorNormalShader);
        RenderSystem.setShaderTexture(0, this.KNOWLEDGE_SPRITE);
        blit(matrixStack, k + x, l + y, spriteX, spriteY, 32, 32);
        font.draw(matrixStack, s, k + x + 35, l + y + 5, 4210752);
        if(s.contains("Sentacoins"))
            font.draw(matrixStack, "0"/*stats.getSentacoinValue()*/, k + x + 35, l + y + 15, 4210752);
        matrixStack.popPose();
        RenderSystem.enableDepthTest();
    }

    public void drawKnowledgeSprite(PoseStack matrixStack, int x, int y, int spriteX, int spriteY, EnumKnowledgeType type, String s) {
        drawSprite(matrixStack, x, y, spriteX, spriteY, s);
        int progressBarSize = 65;
        int k = (width - imageWidth) / 2;
        int l = (height - imageHeight) / 2;
        matrixStack.pushPose();
        RenderSystem.setShader(GameRenderer::getPositionTexColorNormalShader);
        RenderSystem.setShaderTexture(0, this.KNOWLEDGE_SPRITE);
        //PlayerStats.KnowledgeStorage knowledge = stats.getKnowledge(type);

        //float percents = knowledge.getAmountOnCurrentLevel() / knowledge.getLevelCapacity(knowledge.getLevelCount());
        float percents = 50F;
        int width = (int) (percents * progressBarSize);

        int progressBarX = k + x + 35, progressBarY = l + y + 19;

        blit(matrixStack, progressBarX, progressBarY, 0, 5, progressBarSize, 5);
        blit(matrixStack, progressBarX, progressBarY, 0, 0, width, 5);

        int lvX = progressBarX + 29, lvY = progressBarY - 1;

        int getLevelCount = 48;

        font.drawShadow(matrixStack, "" + getLevelCount, getLevelCount > 10 ? lvX - 2 : getLevelCount > 100 ? lvX - 4 : lvX, lvY, ArgbColor.from(ChatFormatting.BLUE));
        matrixStack.popPose();
        RenderSystem.enableDepthTest();
    }

    public void page2(PoseStack stack) {
        int height = 43;
        int x = 9;
        int h = 9;

        drawKnowledgeSprite(stack, x, h, 0, 42, EnumKnowledgeType.CORBA, "Corba");
        drawKnowledgeSprite(stack, 126, h, 32, 42, EnumKnowledgeType.TERRANIA, "Terrania");

        h += height;

        drawKnowledgeSprite(stack, x, h, 64, 42, EnumKnowledgeType.CLOUDIA, "Cloudia");
        drawKnowledgeSprite(stack, 126, h, 96, 42, EnumKnowledgeType.SENTERIAN, "Senterain");
        //h += height;
        //drawKnowledgeSprite(x, h, 128, 10, 0.30F, E"SDHFSDH");
        //drawKnowledgeSprite(126, h, 160, 10, 0.75F, "Frozen Lands");
        //h += height - 2;
        //drawKnowledgeSprite(x, h, 192, 10, 0.10F, "Euca");
        //drawKnowledgeSprite(126, h, 224, 10, 0.60F, "The Depths");
    }

    @Override
    protected void containerTick() {
        super.containerTick();
        this.nextButton.active = pageNumber < 1;
        this.previousButton.active = pageNumber > 0;
    }

    @Override
    protected void renderLabels(@NotNull PoseStack matrixStack, int x, int y) { }

    /*
    }

    /*



    private class PageButton extends Button implements Button.OnPress {
        private final boolean prev;

        public PageButton(int x, int y, Boolean prev, OnPress pressable) {
            super(x, y, 12, 19, TextComponent.EMPTY, pressable);
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

        */
}