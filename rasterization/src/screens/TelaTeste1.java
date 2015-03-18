package screens;

import java.awt.event.MouseEvent;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.GLU;

import model.Coordinate;
import model.Point2D;
import model.Triangulo;
import model.Vertex;

import com.jogamp.opengl.util.FPSAnimator;

import event.JoglEventListener;

public class TelaTeste1 extends JoglEventListener {
	public static int FPS = 60;
	private int viewport[];
	private double modelview[];
	private double projection[];
	
	
	public TelaTeste1(GLCanvas canvas, boolean animated) {
		super(canvas);		
		
		viewport 	= new int[4];
		modelview 	= new double[16];
		projection  = new double[16];
		
		if (animated) {
        	FPSAnimator animator = new FPSAnimator(canvas, FPS);        
            animator.start();  
		}   
		
	}
	
	
	@Override
	public void display(GLAutoDrawable drawable) 
	{
		GL2 gl = drawable.getGL().getGL2();	
		
		gl.glGetIntegerv(GL.GL_VIEWPORT, viewport, 0); //coordinates of screen
		gl.glGetDoublev(GL2.GL_MODELVIEW_MATRIX, modelview, 0);
		gl.glGetDoublev(GL2.GL_PROJECTION_MATRIX, projection, 0);
		
		gl.glViewport(0, 0, 400, 300);
		gl.glMatrixMode(GL2.GL_PROJECTION);
		gl.glLoadIdentity();
		super.getGlu().gluOrtho2D(0.0, 400.0, 0.0, 300.0);
		
		new Point2D(200, 110).draw(gl);
		
		//gl.glRectf(0.0f, 0.0f, 200.0f, 100.0f);
		
		gl.glFlush();
		
		
		
	}

	@Override
	public void dispose(GLAutoDrawable drawable) 
	{
		GL2 gl = drawable.getGL().getGL2();
		System.out.println("dispose");
	}

	@Override
	public void init(GLAutoDrawable drawable) 
	{
		GL2 gl = drawable.getGL().getGL2();
		System.out.println("init");
	}

	@Override
	public void reshape(GLAutoDrawable drawable, 
										  int x, 
										  int y, 
										  int width,
										  int height) 
	{
		
		GL2 gl = drawable.getGL().getGL2();
//		System.out.println("reshape");
//		
//		gl.glMatrixMode(GL2.GL_PROJECTION);
//		gl.glLoadIdentity();
//		gl.glViewport(x, y, width, height);
//		super.getGlu().gluOrtho2D(x, y, width, height);

		
	}


	@Override
	public void showMouseCoordinate(GLAutoDrawable drawable, MouseEvent mouse,
			GLU glu) {
		// TODO Auto-generated method stub
		
	}
	
	
}
