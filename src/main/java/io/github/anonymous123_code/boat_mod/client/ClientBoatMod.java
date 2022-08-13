package io.github.anonymous123_code.boat_mod.client;

import io.github.anonymous123_code.boat_mod.BoatMod;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer;

/**
 * @author anonymous123-code
 */
public class ClientBoatMod implements ClientModInitializer {
	public static EntityModelLayer MODEL_BOAT_LAYER;
	@Override
	public void onInitializeClient(ModContainer mod) {
		MODEL_BOAT_LAYER = new EntityModelLayer(new Identifier(mod.metadata().id(), "boat"), "main");

		EntityRendererRegistry.register(BoatMod.BOAT, (context) -> new BoatEntityRenderer(context, mod.metadata().id()));

		EntityModelLayerRegistry.registerModelLayer(MODEL_BOAT_LAYER, BoatEntityModel::getTexturedModelData);
	}
}
