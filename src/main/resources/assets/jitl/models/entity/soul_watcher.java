// Made with Blockbench 4.0.5
// Exported for Minecraft version 1.17 with Mojang mappings
// Paste this class into your mod and generate all required imports


public class soul_watcher<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "soul_watcher"), "main");
	private final ModelPart head;
	private final ModelPart eyelids_top;
	private final ModelPart eyelids_bottom;

	public soul_watcher(ModelPart root) {
		this.head = root.getChild("head");
		this.eyelids_top = root.getChild("eyelids_top");
		this.eyelids_bottom = root.getChild("eyelids_bottom");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(24, 46).addBox(-11.0F, -25.0F, -5.0F, 2.0F, 10.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(0, 44).addBox(7.0F, -25.0F, -5.0F, 2.0F, 10.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-9.0F, -28.0F, -8.0F, 16.0F, 16.0F, 16.0F, new CubeDeformation(0.0F))
		.texOffs(48, 4).addBox(-6.0F, -25.0F, 8.0F, 10.0F, 10.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(30, 34).addBox(-6.0F, -30.0F, -5.0F, 10.0F, 2.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(0, 32).addBox(-6.0F, -12.0F, -5.0F, 10.0F, 2.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition eyelids_top = partdefinition.addOrReplaceChild("eyelids_top", CubeListBuilder.create().texOffs(38, 50).addBox(-6.0F, -3.5F, -4.5F, 12.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(38, 46).addBox(-7.0F, 0.5F, -5.5F, 14.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 1.5F, -4.5F, -0.5236F, 0.0F, 0.0F));

		PartDefinition eyelids_bottom = partdefinition.addOrReplaceChild("eyelids_bottom", CubeListBuilder.create().texOffs(48, 56).addBox(-6.0F, -1.5F, -4.5F, 12.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(48, 0).addBox(-7.0F, -2.5F, -5.5F, 14.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 6.5F, -4.5F, 0.5236F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		head.render(poseStack, buffer, packedLight, packedOverlay);
		eyelids_top.render(poseStack, buffer, packedLight, packedOverlay);
		eyelids_bottom.render(poseStack, buffer, packedLight, packedOverlay);
	}
}