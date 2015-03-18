package template;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;

public class Renderer implements GLEventListener {
    private GLU glu = new GLU();
    
    
    public void display(GLAutoDrawable gLDrawable) {
    	final GL2 gl = gLDrawable.getGL().getGL2();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();
    }
    
    
   
    public void displayChanged(GLAutoDrawable gLDrawable, boolean modeChanged, 
            boolean deviceChanged) {
    }
    
  
    public void init(GLAutoDrawable gLDrawable) {
    	final GL2 gl = gLDrawable.getGL().getGL2();
        gl.glShadeModel(GL2.GL_SMOOTH);              // Enable Smooth Shading
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.5f);    // Black Background
    }
    
    
    
    public void reshape(GLAutoDrawable gLDrawable, int x, int y, int width, 
            int height) {
    	final GL2 gl = gLDrawable.getGL().getGL2();
        
        if (height <= 0) height = 1;
        final float h = (float) width / (float) height;
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(45.0f, h, 1.0, 20.0);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
        
    }


	@Override
	public void dispose(GLAutoDrawable arg0) {
		
		
	}


}
