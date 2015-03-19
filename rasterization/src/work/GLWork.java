package work;

/** a work class that implements a GLRunnable this class includes all the code necessary 
 * to do the work of drawing each point following is the content of this class 
 */

import javax.media.opengl.GLRunnable; 
import javax.media.opengl.GLAutoDrawable; 
import javax.media.opengl.GL2; 

public class GLWork implements GLRunnable{ 
	private float x; 
	private float y; 
	private boolean in; 

	GLWork(){ 
		x = 0.0f; 
		y = 0.0f; 
		in = false; 
	} 

	GLWork(float x, float y, boolean in){ 
		this.x = x; 
		this.y = y; 
		this.in = in; 
	} 

	@Override 
	public boolean run(GLAutoDrawable drawable){ 
		GL2 gl = drawable.getGL().getGL2(); 

		gl.glPointSize(10.0f); 
		gl.glPushMatrix(); 
		gl.glBegin(GL2.GL_POINTS); 
		if(in) 
			gl.glColor3f(1.0f, 0.0f, 0.0f); 
		else 
			gl.glColor3f(0.0f, 0.0f, 1.0f); 
		gl.glVertex3f(x, y, 0.0f); 
		gl.glEnd(); 
		gl.glPopMatrix(); 

		return true; 
	} 
} 

