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

import net.minecraft.util.math.Vec3d;

import static java.lang.Math.abs;
import static java.lang.Math.signum;

public class OBBCollider {

	static final Vec3d uA0 = new Vec3d(1, 0, 0);
	static final Vec3d uA1 = new Vec3d(0, 1, 0);
	static final Vec3d uA2 = new Vec3d(0, 0, 1);

	public static Vec3d separateBBs(Vec3d cA, Vec3d cB, Vec3d eA, Vec3d eB, Matrix3d m) {
		SeparationManifold mf = new SeparationManifold();

		Vec3d t = cB.subtract(cA);

		double a00 = abs(m.m00);
		double a01 = abs(m.m01);
		double a02 = abs(m.m02);
		double a10 = abs(m.m10);
		double a11 = abs(m.m11);
		double a12 = abs(m.m12);
		double a20 = abs(m.m20);
		double a21 = abs(m.m21);
		double a22 = abs(m.m22);

		Vec3d uB0 = new Vec3d(m.m00, m.m10, m.m20);
		Vec3d uB1 = new Vec3d(m.m01, m.m11, m.m21);
		Vec3d uB2 = new Vec3d(m.m02, m.m12, m.m22);

		checkCount = 0;

		if (
		// Separate along A's local axes (global XYZ)
		!(isSeparatedAlong(mf, uA0, t.x, eA.x, a00 * eB.x + a01 * eB.y + a02 * eB.z)
			|| isSeparatedAlong(mf, uA1, t.y, eA.y, a10 * eB.x + a11 * eB.y + a12 * eB.z)
			|| isSeparatedAlong(mf, uA2, t.z, eA.z, a20 * eB.x + a21 * eB.y + a22 * eB.z)

			// Separate along B's local axes
			|| isSeparatedAlong(mf, uB0, (t.x * m.m00 + t.y * m.m10 + t.z * m.m20),
				eA.x * a00 + eA.y * a10 + eA.z * a20, eB.x)
			|| isSeparatedAlong(mf, uB1, (t.x * m.m01 + t.y * m.m11 + t.z * m.m21),
				eA.x * a01 + eA.y * a11 + eA.z * a21, eB.y)
			|| isSeparatedAlong(mf, uB2, (t.x * m.m02 + t.y * m.m12 + t.z * m.m22),
				eA.x * a02 + eA.y * a12 + eA.z * a22, eB.z)))
			return mf.asSeparationVec();

		return null;
	}

	static int checkCount = 0;

	static boolean isSeparatedAlong(SeparationManifold mf, Vec3d axis, double TL, double rA, double rB) {
		checkCount++;
		double distance = abs(TL);
		double diff = distance - (rA + rB);
		if (diff > 0)
			return true;

		boolean isBestSeperation = checkCount == 2; // Debug specific separations

		if (isBestSeperation) {
			double sTL = signum(TL);
			double value = sTL * abs(diff);
			mf.axis = axis.normalize();
			mf.separation = value;
		}

		return false;
	}

	static class SeparationManifold {
		Vec3d axis;
		double separation;

		public SeparationManifold() {
			axis = Vec3d.ZERO;
			separation = Double.MAX_VALUE;
		}

		public Vec3d asSeparationVec() {
			double sep = separation;
			Vec3d axis = this.axis;
			return createSeparationVec(sep, axis);
		}

		protected Vec3d createSeparationVec(double sep, Vec3d axis) {
			return axis.normalize().multiply(signum(sep) * (abs(sep) + 1E-4));
		}
	}

}
