package net.jitl.client.render.tile;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import net.jitl.client.render.JModelLayers;
import net.jitl.common.block.base.JChestBlock;
import net.jitl.common.tile.JChestBlockEntity;
import net.jitl.core.JITL;
import net.jitl.core.init.JBlocks;
import net.jitl.core.init.JItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.BrightnessCombiner;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoubleBlockCombiner;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.LidBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.ChestType;
import org.jetbrains.annotations.NotNull;

public class JChestTER <T extends BlockEntity & LidBlockEntity> implements BlockEntityRenderer<T> {

    protected final ModelPart lid;
    protected final ModelPart bottom;
    protected final ModelPart lock;
    protected final ModelPart doubleLeftLid;
    protected final ModelPart doubleLeftBottom;
    protected final ModelPart doubleLeftLock;
    protected final ModelPart doubleRightLid;
    protected final ModelPart doubleRightBottom;
    protected final ModelPart doubleRightLock;

    public JChestTER(BlockEntityRendererProvider.Context context) {
        ModelPart modelpart = context.bakeLayer(JModelLayers.JCHEST);
        this.bottom = modelpart.getChild("bottom");
        this.lid = modelpart.getChild("lid");
        this.lock = modelpart.getChild("lock");
        ModelPart modelpart1 = context.bakeLayer(JModelLayers.JDOUBLE_CHEST_LEFT);
        this.doubleLeftBottom = modelpart1.getChild("bottom");
        this.doubleLeftLid = modelpart1.getChild("lid");
        this.doubleLeftLock = modelpart1.getChild("lock");
        ModelPart modelpart2 = context.bakeLayer(JModelLayers.JDOUBLE_CHEST_RIGHT);
        this.doubleRightBottom = modelpart2.getChild("bottom");
        this.doubleRightLid = modelpart2.getChild("lid");
        this.doubleRightLock = modelpart2.getChild("lock");
    }

    public static LayerDefinition createSingleBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        partdefinition.addOrReplaceChild("bottom", CubeListBuilder.create().texOffs(0, 19).addBox(1.0F, 0.0F, 1.0F, 14.0F, 10.0F, 14.0F), PartPose.ZERO);
        partdefinition.addOrReplaceChild("lid", CubeListBuilder.create().texOffs(0, 0).addBox(1.0F, 0.0F, 0.0F, 14.0F, 5.0F, 14.0F), PartPose.offset(0.0F, 9.0F, 1.0F));
        partdefinition.addOrReplaceChild("lock", CubeListBuilder.create().texOffs(0, 0).addBox(7.0F, -1.0F, 15.0F, 2.0F, 4.0F, 1.0F), PartPose.offset(0.0F, 8.0F, 0.0F));
        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    public static LayerDefinition createDoubleBodyRightLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        partdefinition.addOrReplaceChild("bottom", CubeListBuilder.create().texOffs(0, 19).addBox(1.0F, 0.0F, 1.0F, 15.0F, 10.0F, 14.0F), PartPose.ZERO);
        partdefinition.addOrReplaceChild("lid", CubeListBuilder.create().texOffs(0, 0).addBox(1.0F, 0.0F, 0.0F, 15.0F, 5.0F, 14.0F), PartPose.offset(0.0F, 9.0F, 1.0F));
        partdefinition.addOrReplaceChild("lock", CubeListBuilder.create().texOffs(0, 0).addBox(15.0F, -1.0F, 15.0F, 1.0F, 4.0F, 1.0F), PartPose.offset(0.0F, 8.0F, 0.0F));
        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    public static LayerDefinition createDoubleBodyLeftLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        partdefinition.addOrReplaceChild("bottom", CubeListBuilder.create().texOffs(0, 19).addBox(0.0F, 0.0F, 1.0F, 15.0F, 10.0F, 14.0F), PartPose.ZERO);
        partdefinition.addOrReplaceChild("lid", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, 0.0F, 0.0F, 15.0F, 5.0F, 14.0F), PartPose.offset(0.0F, 9.0F, 1.0F));
        partdefinition.addOrReplaceChild("lock", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -1.0F, 15.0F, 1.0F, 4.0F, 1.0F), PartPose.offset(0.0F, 8.0F, 0.0F));
        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void render(T blockEntity, float partialTick, PoseStack poseStack, @NotNull MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        Level level = blockEntity.getLevel();
        boolean viable = level != null;
        BlockState blockstate = viable ? blockEntity.getBlockState() : blockEntity.getBlockState().getBlock().defaultBlockState().setValue(JChestBlock.FACING, Direction.SOUTH);
        ChestType chesttype = blockstate.hasProperty(JChestBlock.TYPE) ? blockstate.getValue(JChestBlock.TYPE) : ChestType.SINGLE;
        Block block = blockstate.getBlock();
        JChestBlock chest = (JChestBlock)block;
        boolean isDouble = chesttype != ChestType.SINGLE;
        poseStack.pushPose();
        float f = blockstate.getValue(JChestBlock.FACING).toYRot();
        poseStack.translate(0.5D, 0.5D, 0.5D);
        poseStack.mulPose(Vector3f.YP.rotationDegrees(-f));
        poseStack.translate(-0.5D, -0.5D, -0.5D);
        DoubleBlockCombiner.NeighborCombineResult<? extends JChestBlockEntity> res;
        if(viable) {
            res = chest.combine(blockstate, level, blockEntity.getBlockPos(), true);
        } else {
            res = DoubleBlockCombiner.Combiner::acceptNone;
        }
        float f1 = res.apply(JChestBlock.opennessCombiner(blockEntity)).get(partialTick);
        f1 = 1.0F - f1;
        f1 = 1.0F - f1 * f1 * f1;
        int i = res.apply(new BrightnessCombiner<>()).applyAsInt(packedLight);
        VertexConsumer vertexconsumer = bufferSource.getBuffer(RenderType.entitySolid(JITL.rl("textures/models/block/chest/" + getNameFromBlock(chest) + ".png")));

        if(isDouble) {
            if (chesttype == ChestType.LEFT) {
                vertexconsumer = bufferSource.getBuffer(RenderType.entitySolid(JITL.rl("textures/models/block/chest/" + getNameFromBlock(chest) + "_left.png")));
                this.render(poseStack, vertexconsumer, this.doubleLeftLid, this.doubleLeftLock, this.doubleLeftBottom, f1, i, packedOverlay);
            } else {
                vertexconsumer = bufferSource.getBuffer(RenderType.entitySolid(JITL.rl("textures/models/block/chest/" + getNameFromBlock(chest) + "_right.png")));
                this.render(poseStack, vertexconsumer, this.doubleRightLid, this.doubleRightLock, this.doubleRightBottom, f1, i, packedOverlay);
            }
        } else {
            this.render(poseStack, vertexconsumer, this.lid, this.lock, this.bottom, f1, i, packedOverlay);
        }
        poseStack.popPose();

        if(blockstate.getValue(JChestBlock.IS_LOCKED)) {
            renderItem(new ItemStack(JItems.PADLOCK), new double[]{0.5D, 0.2D, 0.945D}, poseStack, bufferSource, packedOverlay, packedLight, blockstate);
        }
    }

    private void renderItem(ItemStack stack, double[] translation, PoseStack matrixStack, MultiBufferSource buffer, int combinedOverlay, int lightLevel, BlockState state) {
        ItemRenderer renderer = Minecraft.getInstance().getItemRenderer();
        matrixStack.pushPose();
        float f = state.getValue(JChestBlock.FACING).toYRot();
        matrixStack.translate(0.5D, 0.5D, 0.5D);
        matrixStack.mulPose(Vector3f.YP.rotationDegrees(-f));
        matrixStack.translate(-0.5D, -0.5D, -0.5D);
        matrixStack.translate(translation[0], translation[1], translation[2]);
        float scale = 1.0F;
        matrixStack.scale(scale, scale, scale);
        BakedModel model = renderer.getModel(stack, null, null, 0);
        renderer.render(stack, ItemTransforms.TransformType.GROUND, true, matrixStack, buffer, lightLevel, combinedOverlay, model);
        matrixStack.popPose();
    }

    public String getNameFromBlock(JChestBlock chest) {
        String name = "";
        if(chest == JBlocks.EUCA_CHEST) {
            name = "euca_chest";
        }
        if(chest == JBlocks.FROZEN_CHEST) {
            name = "frozen_chest";
        }
        if(chest == JBlocks.BOIL_CHEST) {
            name = "boiling_chest";
        }
        return name;
    }

    private void render(PoseStack poseStack, VertexConsumer consumer, ModelPart lidPart, ModelPart lockPart, ModelPart bottomPart, float lidAngle, int packedLight, int packedOverlay) {
        lidPart.xRot = -(lidAngle * ((float) Math.PI / 2F));
        lockPart.xRot = lidPart.xRot;
        lidPart.render(poseStack, consumer, packedLight, packedOverlay);
        lockPart.render(poseStack, consumer, packedLight, packedOverlay);
        bottomPart.render(poseStack, consumer, packedLight, packedOverlay);
    }
}