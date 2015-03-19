package main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.GLU;


/**
 * 
 * @author gcaraciolo
 *
 * the method draw is call by the display method.
 */

public   class GameWindow implements GLEventListener
{
	
		private GLCanvas canvas;
		private GLU glu;
		
		private int viewport[];
		private double modelview[];
		private double projection[];
		
		//size
		private int width;
		private int height;
		
		//vbo
		private int VBO[];
		
		
		public GameWindow (GLCanvas canvas, GLU glu, MouseEvent mouse, int width, int height) {
			this.canvas = canvas;
			this.glu = glu;		
			if (glu == null) this.glu = new GLU();
			
			viewport 	= new int[4];
			modelview 	= new double[16];
			projection  = new double[16];
			
			this.width = width;
			this.height = height;
			
			initListeners();
		}

		
		private void initListeners() {
			//set listeners
			canvas.addGLEventListener(this);
		}
		
		
		@Override
		public void init(GLAutoDrawable drawable) {
			System.out.println("init viewport");
			GL2 gl = drawable.getGL().getGL2();
		
		}
		
		@Override
		public void reshape(GLAutoDrawable drawable, 
										      int x, 
										      int y, 
										  int width,
										  int height) 
		{
			GL2 gl = drawable.getGL().getGL2();
			System.out.println("reshap viewport");
			
			
			gl.glClearColorIi(0, 0, 0, 0);
			gl.glGetIntegerv(GL.GL_VIEWPORT, viewport, 0); //coordinates of screen
			gl.glGetDoublev(GL2.GL_MODELVIEW_MATRIX, modelview, 0);
			gl.glGetDoublev(GL2.GL_PROJECTION_MATRIX, projection, 0);
			
			gl.glViewport(0, 0, width, height);
			gl.glMatrixMode(GL2.GL_PROJECTION);
			glu.gluOrtho2D(0.0, (float)width, 0.0, (float)height);		
			gl.glMatrixMode(GL2.GL_MODELVIEW);
		}
		
		@Override
		public void display(GLAutoDrawable drawable) {
			GL2 gl = drawable.getGL().getGL2();
			System.out.println("display viewport");
			
			
			gl.glFlush();
		}
		
		@Override
		public void dispose(GLAutoDrawable drawable) {
			GL2 gl = drawable.getGL().getGL2();
			System.out.println("dispose viewport");
			
		}	

}
