/* MIT LICENSE

Copyright (c) 2019 simibubi

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/

/*
original file:
https://github.com/Fabricators-of-Create/Create/blob/ebfe5b009eb38901a505abbe1986d308edd3da3e/src/main/java/com/simibubi/create/foundation/collision/OBBCollider.java
Modifications done by anonymous123-code:
remapped to quilt mappings, changed package
 */

package io.github.anonymous123_code.boat_mod.collision;

import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;

public class OrientedBB {

	Vec3d center;
	Vec3d extents;
	Matrix3d rotation;

	public OrientedBB(Box bb) {
		this(bb.getCenter(), extentsFromBB(bb), new Matrix3d().asIdentity());
	}

	public OrientedBB() {
		this(Vec3d.ZERO, Vec3d.ZERO, new Matrix3d().asIdentity());
	}

	public OrientedBB(Vec3d center, Vec3d extents, Matrix3d rotation) {
		this.setCenter(center);
		this.extents = extents;
		this.setRotation(rotation);
	}

	public OrientedBB copy() {
		return new OrientedBB(center, extents, rotation);
	}

	public Vec3d intersect(Box bb) {
		Vec3d extentsA = extentsFromBB(bb);
		Vec3d intersects = OBBCollider.separateBBs(bb.getCenter(), center, extentsA, extents, rotation);
		return intersects;
	}

	public ContinuousOBBCollider.ContinuousSeparationManifold intersect(Box bb, Vec3d motion) {
		Vec3d extentsA = extentsFromBB(bb);
		return ContinuousOBBCollider.separateBBs(bb.getCenter(), center, extentsA, extents, rotation, motion);
	}

	private static Vec3d extentsFromBB(Box bb) {
		return new Vec3d(bb.getXLength() / 2, bb.getYLength() / 2, bb.getZLength() / 2);
	}

	public Matrix3d getRotation() {
		return rotation;
	}

	public void setRotation(Matrix3d rotation) {
		this.rotation = rotation;
	}

	public Vec3d getCenter() {
		return center;
	}

	public void setCenter(Vec3d center) {
		this.center = center;
	}

	public void move(Vec3d offset) {
		setCenter(getCenter().add(offset));
	}

	public Box getAsAABB() {
		return new Box(0, 0, 0, 0, 0, 0).offset(center)
				.expand(extents.x, extents.y, extents.z);
	}

	/*
	 * The following checks (edge-to-edge) need special separation logic. They are
	 * not necessary as long as the obb is only rotated around one axis at a time
	 * (Which is the case for contraptions at the moment)
	 *
	 */

	// Separate along axes perpendicular to AxB
//		|| isSeparatedAlong(bestAxis, bestSep, uA0.crossProduct(uB0), t.z * m.m10 - t.y * m.m20,
//			eA.y * a20 + eA.z * a10, eB.y * a02 + eB.z * a01)
//		|| isSeparatedAlong(bestAxis, bestSep, uA0.crossProduct(uB1), t.z * m.m11 - t.y * m.m21,
//			eA.y * a21 + eA.z * a11, eB.x * a02 + eB.z * a00)
//		|| isSeparatedAlong(bestAxis, bestSep, uA0.crossProduct(uB2), t.z * m.m12 - t.y * m.m22,
//			eA.y * a22 + eA.z * a12, eB.x * a01 + eB.y * a00)
//
//		|| isSeparatedAlong(bestAxis, bestSep, uA1.crossProduct(uB0), t.x * m.m20 - t.z * m.m00,
//			eA.x * a20 + eA.z * a00, eB.y * a12 + eB.z * a11)
//		|| isSeparatedAlong(bestAxis, bestSep, uA1.crossProduct(uB1), t.x * m.m21 - t.z * m.m01,
//			eA.x * a21 + eA.z * a01, eB.x * a12 + eB.z * a10)
//		|| isSeparatedAlong(bestAxis, bestSep, uA1.crossProduct(uB2), t.x * m.m22 - t.z * m.m02,
//			eA.x * a22 + eA.z * a02, eB.x * a11 + eB.y * a10)
//
//		|| isSeparatedAlong(bestAxis, bestSep, uA2.crossProduct(uB0), t.y * m.m00 - t.x * m.m10,
//			eA.x * a10 + eA.y * a00, eB.y * a22 + eB.z * a21)
//		|| isSeparatedAlong(bestAxis, bestSep, uA2.crossProduct(uB1), t.y * m.m01 - t.x * m.m11,
//			eA.x * a11 + eA.y * a01, eB.x * a22 + eB.z * a20)
//		|| isSeparatedAlong(bestAxis, bestSep, uA2.crossProduct(uB2), t.y * m.m02 - t.x * m.m12,
//			eA.x * a12 + eA.y * a02, eB.x * a21 + eB.y * a20)

}
