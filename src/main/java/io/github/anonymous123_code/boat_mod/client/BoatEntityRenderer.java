package io.github.anonymous123_code.boat_mod.client;

import io.github.anonymous123_code.boat_mod.entity.BoatEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Quaternion;

/**
 * @author anonymous123-code
 */
@Environment(EnvType.CLIENT)
public class BoatEntityRenderer extends EntityRenderer<BoatEntity> {
	private final String modId;
	private final BoatEntityModel model;

	private float frame;

	protected BoatEntityRenderer(EntityRendererFactory.Context context, String modId) {
		super(context);
		this.model = new BoatEntityModel(context.getPart(ClientBoatMod.MODEL_BOAT_LAYER));
		this.modId = modId;
	}

	@Override
	public void render(BoatEntity entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
		matrices.push();

		matrices.multiply(Quaternion.fromEulerXyz((float) Math.PI, 0, 0));
		matrices.translate(0, -1.5, 0);
		matrices.multiply(Quaternion.fromEulerXyz(0, (float) (-Math.PI/2 + yaw/360*Math.PI*2), 0));
		matrices.translate(0.5, 0, 0);

		this.model.render(matrices, vertexConsumers.getBuffer(
				this.model.getLayer(this.getTexture(entity))),
				light, OverlayTexture.DEFAULT_UV);

		matrices.pop();
		super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
	}

	@Override
	public Identifier getTexture(BoatEntity entity) {
		return new Identifier(modId, "textures/entity/boat/boat.png");
	}
}
