// Made with Blockbench 3.5.4
// Exported for Minecraft version 1.12
// Paste this class into your mod and generate all required imports


public class custom_model extends ModelBase {
	private final ModelRenderer leg1;
	private final ModelRenderer leg2;
	private final ModelRenderer head;
	private final ModelRenderer ear1;
	private final ModelRenderer ear2;
	private final ModelRenderer arm1;
	private final ModelRenderer arm2;
	private final ModelRenderer body;

	public custom_model() {
		textureWidth = 64;
		textureHeight = 64;

		leg1 = new ModelRenderer(this);
		leg1.setRotationPoint(-2.0F, 18.0F, 0.0F);
		leg1.cubeList.add(new ModelBox(leg1, 32, 0, -2.0F, 0.0F, -2.0F, 4, 6, 4, 0.0F, false));

		leg2 = new ModelRenderer(this);
		leg2.setRotationPoint(2.0F, 18.0F, 0.0F);
		leg2.cubeList.add(new ModelBox(leg2, 28, 12, -2.0F, 0.0F, -2.0F, 4, 6, 4, 0.0F, false));

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, 10.0F, 0.0F);
		head.cubeList.add(new ModelBox(head, 0, 0, -1.0F, -5.0F, -6.0F, 2, 4, 2, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 0, 0, -4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 32, 10, -3.0F, -6.0F, -4.5F, 6, 1, 1, 0.0F, false));

		ear1 = new ModelRenderer(this);
		ear1.setRotationPoint(-4.5F, -7.0F, 0.5F);
		head.addChild(ear1);
		setRotationAngle(ear1, -0.4363F, -0.0873F, 0.0F);
		ear1.cubeList.add(new ModelBox(ear1, 34, 22, -0.3257F, -3.842F, 0.3057F, 1, 6, 3, 0.0F, false));

		ear2 = new ModelRenderer(this);
		ear2.setRotationPoint(4.5F, -7.0F, 0.5F);
		head.addChild(ear2);
		setRotationAngle(ear2, -0.4363F, 0.0F, 0.0F);
		ear2.cubeList.add(new ModelBox(ear2, 34, 34, -0.5F, -3.8452F, 0.3126F, 1, 6, 3, 0.0F, false));

		arm1 = new ModelRenderer(this);
		arm1.setRotationPoint(-5.0F, 10.0F, 0.0F);
		arm1.cubeList.add(new ModelBox(arm1, 0, 28, -2.0F, 0.0F, -2.0F, 3, 10, 4, 0.0F, false));

		arm2 = new ModelRenderer(this);
		arm2.setRotationPoint(5.0F, 10.0F, 0.0F);
		arm2.cubeList.add(new ModelBox(arm2, 20, 24, -1.0F, 0.0F, -2.0F, 3, 10, 4, 0.0F, false));

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 24.0F, 0.0F);
		body.cubeList.add(new ModelBox(body, 0, 16, -4.0F, -14.0F, -2.0F, 8, 8, 4, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		leg1.render(f5);
		leg2.render(f5);
		head.render(f5);
		arm1.render(f5);
		arm2.render(f5);
		body.render(f5);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}