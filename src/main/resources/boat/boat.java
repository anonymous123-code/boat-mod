// Made with Blockbench 4.3.1
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class boat extends EntityModel<Entity> {
	private final ModelPart water_patch;
	private final ModelPart bb_main;
	public boat(ModelPart root) {
		this.water_patch = root.getChild("water_patch");
		this.bb_main = root.getChild("bb_main");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData water_patch = modelPartData.addChild("water_patch", ModelPartBuilder.create().uv(0, 0).cuboid(-24.0F, -19.0F, -8.0F, 48.0F, 2.0F, 16.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData bb_main = modelPartData.addChild("bb_main", ModelPartBuilder.create().uv(0, 80).cuboid(-24.0F, -8.0F, -8.0F, 48.0F, 8.0F, 16.0F, new Dilation(0.0F))
		.uv(0, 64).cuboid(-32.0F, -16.0F, -16.0F, 64.0F, 8.0F, 8.0F, new Dilation(0.0F))
		.uv(0, 48).cuboid(-32.0F, -16.0F, 8.0F, 64.0F, 8.0F, 8.0F, new Dilation(0.0F))
		.uv(144, 8).cuboid(-48.0F, -16.0F, -8.0F, 24.0F, 8.0F, 16.0F, new Dilation(0.0F))
		.uv(80, 128).cuboid(24.0F, -16.0F, -8.0F, 24.0F, 8.0F, 16.0F, new Dilation(0.0F))
		.uv(0, 24).cuboid(-32.0F, -24.0F, -24.0F, 64.0F, 8.0F, 16.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-32.0F, -24.0F, 8.0F, 64.0F, 8.0F, 16.0F, new Dilation(0.0F))
		.uv(96, 104).cuboid(24.0F, -24.0F, -8.0F, 32.0F, 8.0F, 16.0F, new Dilation(0.0F))
		.uv(0, 104).cuboid(-56.0F, -24.0F, -8.0F, 32.0F, 8.0F, 16.0F, new Dilation(0.0F))
		.uv(0, 152).cuboid(32.0F, -24.0F, 8.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.0F))
		.uv(128, 88).cuboid(32.0F, -24.0F, -16.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.0F))
		.uv(64, 128).cuboid(-40.0F, -24.0F, 8.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.0F))
		.uv(80, 104).cuboid(-40.0F, -24.0F, -15.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.0F))
		.uv(128, 64).cuboid(-64.0F, -32.0F, -8.0F, 24.0F, 8.0F, 16.0F, new Dilation(0.0F))
		.uv(0, 128).cuboid(-72.0F, -40.0F, -8.0F, 24.0F, 8.0F, 16.0F, new Dilation(0.0F))
		.uv(144, 32).cuboid(-72.0F, -48.0F, -8.0F, 16.0F, 8.0F, 16.0F, new Dilation(0.0F))
		.uv(144, 136).cuboid(-64.0F, -56.0F, -8.0F, 8.0F, 8.0F, 16.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));
		return TexturedModelData.of(modelData, 256, 256);
	}
	@Override
	public void setAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}
	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		water_patch.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		bb_main.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}
}
