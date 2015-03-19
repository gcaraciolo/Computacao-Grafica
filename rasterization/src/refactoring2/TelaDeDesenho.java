package refactoring2;

import java.awt.event.MouseEvent;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import javax.media.opengl.GL2;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.GLU;


public class TelaDeDesenho extends Viewport{
	
	//default size
	private static int X = 0;
	private static int Y = 0;
	
	private int x;
	private int y;
	private int width;
	private int height;
	
	
	private IntBuffer buffers = IntBuffer.allocate(2);
	private float[] square = {
	        -1.0f, -1.0f,
	        1.0f, -1.0f,
	        1.0f, 1.0f,
	        1.0f, 1.0f,
	        -1.0f, 1.0f,
	        -1.0f, -1.0f,
	};
	
	FloatBuffer vertexFB = FloatBuffer.wrap(square);

	
	public TelaDeDesenho(GLCanvas canvas, GLU glu, MouseEvent mouse, int width, int height) {
		super(canvas, glu, mouse, width, height);
		defaultSize(X, Y, width, height);
	}
	
	public TelaDeDesenho(int width, int height) {
		super(width, height);
		defaultSize(X, Y, width, height);
	}
	
	private void defaultSize(int x, int y, int width, int height) {
		setPosition(X + 20 , Y + 20, width - 200, height - 100);
		
	}	
	public void setPosition(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public void draw(GL2 gl, GLU glu) {		
		gl.glRecti(x, y, width, height);		
	}


}
