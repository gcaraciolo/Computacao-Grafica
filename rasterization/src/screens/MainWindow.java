package screens;

import java.awt.event.MouseEvent;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.GLU;

import model.CartesianPlane2D;
import model.Point2D;

import com.jogamp.opengl.util.FPSAnimator;

import event.JoglEventListener;

public class MainWindow extends JoglEventListener {

	public static int FPS = 60;
	
		
	public MainWindow(GLCanvas canvas, boolean animated) {
		super(canvas);		
		if (animated) {
        	FPSAnimator animator = new FPSAnimator(canvas, FPS);        
            animator.start();  
		}   
		
	}
	
	
	@Override
	public void display(GLAutoDrawable drawable) 
	{
		GL2 gl = drawable.getGL().getGL2();
		showMouseCoordinate(drawable, super.getMouse(), super.getGlu());	
		//new CartesianPlane2D().draw(gl);
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
		gl.glViewport(0, 0, width, height);
	    gl.glMatrixMode(GL2.GL_PROJECTION);
	    gl.glLoadIdentity();
	    super.getGlu().gluPerspective(45.0, (float) width / (float) height, 1.0, 100.0);
	    gl.glMatrixMode(GL2.GL_MODELVIEW);
	    gl.glLoadIdentity();
		
	}
	
	public void showMouseCoordinate(GLAutoDrawable drawable, MouseEvent mouse, GLU glu) {
		GL2 gl = drawable.getGL().getGL2();
		int viewport[] = new int[4];
	    double mvmatrix[] = new double[16];
	    double projmatrix[] = new double[16];
		    
	    if (mouse != null)
	    {
		      int x = mouse.getX(), y = mouse.getY();
		      switch (mouse.getButton()) {
		        case MouseEvent.BUTTON1:
		          gl.glGetIntegerv(GL.GL_VIEWPORT, viewport, 0);
		          gl.glGetDoublev(GL2.GL_MODELVIEW_MATRIX, mvmatrix, 0);
		          gl.glGetDoublev(GL2.GL_PROJECTION_MATRIX, projmatrix, 0);
		          /* note viewport[3] is height of window in pixels */
		          y = viewport[3] - (int) y - 1;
		          System.out.println("Coordinates at cursor are (" + x + ", " + y + ")");
		          
		          
		          new Point2D(x, y).draw(gl, glu);
	
		          break;
		        case MouseEvent.BUTTON2:
		          break;
		      }
		 }
	}
	
}
