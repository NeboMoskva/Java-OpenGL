package renderEngine;

/*
 * This class is going to be in charge of
 * managing the display.
 */

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import static org.lwjgl.system.MemoryUtil.NULL;

public class DisplayManager {

	// Defines height and width.
	private int height = 800;
	private int width = 600;
	// Window identifier.
	private long window;
	// Defines a single video mode.
	private GLFWVidMode vidmode;

	// Function to create the display.
	public void createDisplay() {
		/*
		 * If we failed to initialize GLFW throw a "runtime exception". Else create the
		 * window with its associated OpenGL/ES context, in case the window points to a
		 * null pointer address throw a "runtime exception".
		 */
		if (!GLFW.glfwInit()) {
			throw new RuntimeException("Failed to initialize GLFW");
		} else {
			GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GLFW.GLFW_FALSE);
			window = GLFW.glfwCreateWindow(height, width, "3D", NULL, NULL);
			if (window == NULL) {
				throw new RuntimeException("Failed to create Window");
			}
			// Make the specified window visible.
			GLFW.glfwShowWindow(window);
			// Sets the video mode
			setVidmode(GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor()));
			// Makes the OpenGL/ES context of the specified window current on the calling
			// thread.
			GLFW.glfwMakeContextCurrent(window);
			// Creates a new GLCapabilities instance for the OpenGL context that is current
			// in the current thread.
			GL.createCapabilities();
		}
	}

	// Function to be called once the window is closed.
	public void closeDisplay() {
		// This will destroy the specified window and its context.
		GLFW.glfwDestroyWindow(window);
		/*
		 * This will close any remaining windows and cursors, restores any modified
		 * gamma ramps and frees any other allocated resources. This function should be
		 * called before the application exits.
		 */
		GLFW.glfwTerminate();
	}

	public long getWindow() {
		return window;
	}

	public GLFWVidMode getVidmode() {
		return vidmode;
	}

	public void setVidmode(GLFWVidMode vidmode) {
		this.vidmode = vidmode;
	}

}
