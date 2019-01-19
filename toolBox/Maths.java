package toolBox;

import org.joml.Matrix4f;
import org.joml.Vector3f;

public class Maths {

	public static Matrix4f createTransformationMatrix(Vector3f vector3f, float rx, float ry, float rz, float scale) {
		Matrix4f matrix = new Matrix4f();
		matrix.identity();
		matrix.translate(vector3f);
		matrix.rotateX((float) Math.toRadians(rx), matrix);
		matrix.rotateY((float) Math.toRadians(ry), matrix);
		matrix.rotateZ((float) Math.toRadians(rz), matrix);
		matrix.scale(new Vector3f(scale, scale, scale), matrix);
		return matrix;
	}

}