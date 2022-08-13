package io.github.anonymous123_code.boat_mod.entity;

import net.minecraft.entity.*;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

/**
 * @author anonymous123-code
 */
public class BoatEntity extends Entity {
	Vec3d lastPos = Vec3d.ZERO;


	public BoatEntity(EntityType<? extends Entity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected float getEyeHeight(EntityPose pose, EntityDimensions dimensions) {
		return 1.5f;
	}

	@Override
	public void tick() {
		super.tick();
		handleVelocity();
	}

	private void handleVelocity() {
		LocationState locationState = this.getLocationState();
		switch (locationState) {
			case AIR -> {
				this.setVelocity(this.getVelocity().add(0,-0.02,0));
				double absVel = this.getVelocity().length();
				this.setVelocity(this.getVelocity().multiply(1/absVel*Math.min(Math.max(absVel, -0.3), 0.3)));
			}
			case SUBMERGED -> this.setVelocity(0, -0.02, 0);
			case SWIMMING, LAND -> this.setVelocity(Vec3d.ZERO);
		}
		this.move(MovementType.SELF, this.getVelocity());
	}

	private LocationState getLocationState() {
		FluidState blockPosFluidState = this.getWorld().getFluidState(new BlockPos(this.getPos().add(0, 1.5, 0)));
		if (blockPosFluidState.isOf(Fluids.FLOWING_WATER) || blockPosFluidState.isOf(Fluids.WATER)) {
			return LocationState.SUBMERGED;
		}
		FluidState blockPosFluidStateBelow = this.getWorld().getFluidState(new BlockPos(this.getPos().add(0, 1.5, 0)).down());
		if ((blockPosFluidStateBelow.isOf(Fluids.FLOWING_WATER) || blockPosFluidStateBelow.isOf(Fluids.WATER)) &&
			true
		) {
			return LocationState.SWIMMING;
		}
		if (this.isOnGround()) {
			return LocationState.LAND;
		}
		return LocationState.AIR;
	}

	@Override
	protected void initDataTracker() {

	}

	@Override
	protected void readCustomDataFromNbt(NbtCompound nbt) {

	}

	@Override
	protected void writeCustomDataToNbt(NbtCompound nbt) {

	}

	@Override
	public Packet<?> createSpawnPacket() {
		return new EntitySpawnS2CPacket(this);
	}

	public enum LocationState {
		LAND,
		AIR,
		SWIMMING,
		SUBMERGED
	}
}
