import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;

import entities.Entity;
import models.RawModel;
import models.TexturedModel;
import renderEngine.DisplayManager;
import renderEngine.ModelLoader;
import renderEngine.Renderer;
import shaders.StaticShader;
import textures.ModelTexture;

public class Main {

	public static void main(String[] args) {
		
		// Create a new instance of the display manager.
		DisplayManager dspm = new DisplayManager();
		
		// Create the display.
		dspm.createDisplay();
		
		// Create a new instance of ModelLoader, Renderer and Static Shader.
		ModelLoader ModelLoad = new ModelLoader();
		StaticShader shader = new StaticShader();
		Renderer renderer = new Renderer(shader);
		
		/*
		 * Create an array of floating points values that will represent the
		 * different vertices of our quad. Notice that each vertices has three 
		 * components (x,y,z).
		 */
		float[] vertices = { -0.5f, 0.5f, 0f, // v0
				-0.5f, -0.5f, 0f, // v1
				0.5f, -0.5f, 0f, // v2
				0.5f, 0.5f, 0f, // v3
		};
		
		/*
		 * Create an array of integer values that will tell to OpenGL how-to
		 * render our quad.
		 */
		int[] indices = { 0, 1, 3, // top left triangle (v0, v1, v3)
				3, 1, 2 // bottom right triangle (v3, v1, v2)
		};
		
		float[] texturecoords = {	
			0.0f, 0.0f,
			0.0f, 1.0f,
			1.0f, 1.0f,
			1.0f, 0.0f
		};
		
		
		
		RawModel Model = ModelLoad.loadToVAO(vertices, texturecoords, indices);
		ModelTexture texture = new ModelTexture(ModelLoad.loadTextures("espacio"));
		TexturedModel texturedModel = new TexturedModel(Model, texture);
		Entity entity = new Entity(texturedModel, new Vector3f(-1,0,0),0,0,0,1);
		/*
		 * This is our main loop, it will continue to run until
		 * a close signal is sent.
		 */
		while (!GLFW.glfwWindowShouldClose(dspm.getWindow())) {
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
			
			entity.increasePosition(0, 0, 0);
			//entity.increaseRotation(0, 2, 0);
			
			renderer.prepare();
			shader.start();
			renderer.render(entity, shader);
			shader.stop();
			
			// This two functions are the "equivalent" to -> Display.update(); in legacy
			// versions of LWJGL.
			GLFW.glfwPollEvents();
			GLFW.glfwSwapBuffers(dspm.getWindow());
		}
		shader.cleanUp();
		ModelLoad.cleanUp();
		dspm.closeDisplay();
	}
}