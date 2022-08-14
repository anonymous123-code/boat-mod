package io.github.anonymous123_code.boat_mod.collision;

import net.minecraft.util.math.Matrix4f;

import java.nio.FloatBuffer;

/**
 * Changes by anonymous123-code:
 * Use readRowMajor for reading, change package, migrate to quilt mappings
 *
 * Original file was licensed under LGPLv2.1
 * <a href="https://github.com/Fabricators-of-Create/Porting-Lib/blob/2ed576fb5d5e6bed9fcb072f9bcc3e647b5ea0ea/src/main/java/io/github/fabricators_of_create/porting_lib/util/Matrix4fHelper.java">original file</a>
 * @author The Fabricators Of Create
 */


public final class Matrix4fHelper {

	public static Matrix4f fromFloatArray(float[] values) {
		Matrix4f matrix = new Matrix4f();
		matrix.readRowMajor(FloatBuffer.wrap(values));
		return matrix;
	}

	private Matrix4fHelper() {}
}
