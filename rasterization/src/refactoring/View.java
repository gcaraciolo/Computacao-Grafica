package refactoring;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.GLU;

import model.Point2D;


/**
 * every view has its own canvas, glu, mouse and keyboard listener.
 */
public class View implements GLEventListener, 
   						       MouseListener,
   						 MouseMotionListener
{
	
	private GLCanvas canvas;
	private MouseEvent mouse;
	private GLU glu;
	
	private int viewport[];
	private double modelview[];
	private double projection[];
	
	//size
	private int width;
	private int height;
	
	
	public View (GLCanvas canvas, GLU glu, MouseEvent mouse, int width, int height) {
		this.canvas = canvas;
		this.glu = glu;		
		this.mouse = mouse;
		
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
		//canvas.addMouseListener(this); //add event mouse to this canvas.
	}
	
	
	@Override
	public void init(GLAutoDrawable drawable) {
		System.out.println("init");
		GL2 gl = drawable.getGL().getGL2();	
		
		if (glu == null) {
			glu = new GLU();
		}	
	}
	
	@Override
	public void reshape(GLAutoDrawable drawable, 
									      int x, 
									      int y, 
									  int width,
									  int height) 
	{
		GL2 gl = drawable.getGL().getGL2();
		System.out.println("reshap");
		
		gl.glGetIntegerv(GL.GL_VIEWPORT, viewport, 0); //coordinates of screen
		gl.glGetDoublev(GL2.GL_MODELVIEW_MATRIX, modelview, 0);
		gl.glGetDoublev(GL2.GL_PROJECTION_MATRIX, projection, 0);
		
		gl.glViewport(0, 0, width, height);
		gl.glMatrixMode(GL2.GL_PROJECTION);
		gl.glLoadIdentity();
		glu.gluOrtho2D(0.0, (float)width, 0.0, (float)height);		
		
	}
	
	@Override
	public void display(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();
		System.out.println("display");
		
		gl.glRectf(10.0f, 10.0f, 790.0f, 570.0f);
		
		gl.glFlush();
	}

	@Override
	public void dispose(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();
		System.out.println("dispose");
		
	}	

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
