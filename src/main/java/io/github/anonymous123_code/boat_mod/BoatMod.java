package io.github.anonymous123_code.boat_mod;

import io.github.anonymous123_code.boat_mod.entity.BoatEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BoatMod implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod name as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("Boat Mod");
	public static EntityType<BoatEntity> BOAT;

	@Override
	public void onInitialize(ModContainer mod) {
		LOGGER.info("Hello Quilt world from {}!", mod.metadata().name());
		BOAT = Registry.register(
				Registry.ENTITY_TYPE,
				new Identifier(mod.metadata().id(), "boat"),
				FabricEntityTypeBuilder.create(SpawnGroup.MISC, BoatEntity::new).dimensions(EntityDimensions.fixed(8.5f, 3.5f)).build());

	}
}
