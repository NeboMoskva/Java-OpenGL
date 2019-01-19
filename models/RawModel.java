package models;

/*
 * This class is going to represent a 3D model
 * load in memory.
 */

public class RawModel {

	// V.A.O ID.
	private int vaoID;

	// Number of vertices in the 3D model.
	private int vertexCount;

	// Basic constructor for our class.
	public RawModel(int vaoID, int vertexCount) {
		this.vaoID = vaoID;
		this.vertexCount = vertexCount;
	}

	public int getVaoID() {
		return vaoID;
	}

	public int getVertexCount() {
		return vertexCount;
	}

}
