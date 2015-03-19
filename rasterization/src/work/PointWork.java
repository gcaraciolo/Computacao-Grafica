package work;

import javax.media.opengl.GLAutoDrawable; 
import javax.media.opengl.GLRunnable; 

public class PointWork implements Runnable{ 
	private GLAutoDrawable drawable; 
	private GLRunnable runnable; 

	PointWork(){ 
	} 

	PointWork(float x, float y, boolean in, GLAutoDrawable drawable){ 
		this.drawable = drawable; 
		this.runnable = new GLWork(x, y, in); 
	} 

	public void run(){ 
		synchronized(drawable){ 
			drawable.invoke(true, runnable); 
		} 
	} 
} 