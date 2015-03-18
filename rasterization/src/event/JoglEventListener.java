package event;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.GLU;

public abstract class JoglEventListener implements GLEventListener, 
											  KeyListener, 
											MouseListener, 
									  MouseMotionListener  
{

	private GLCanvas canvas;
	private MouseEvent mouse;
	private GLU glu;
	
	public JoglEventListener(GLCanvas canvas) 
	{
		this.canvas = canvas;
		glu = new GLU();
		canvas.addMouseListener(this);
	}
	
	@Override
	public void mouseDragged(MouseEvent e) 
	{
		System.out.println("mouseDragged");
	}

	@Override
	public void mouseMoved(MouseEvent e) 
	{
		System.out.println("mouseMoved");
	}

	@Override
	public void mouseClicked(MouseEvent e) 
	{
		System.out.println("mouseClicked");
	}

	@Override
	public void mousePressed(MouseEvent e) 
	{
		setMouse(e);
		canvas.display();
		System.out.println("mousePressed");
	}

	@Override
	public void mouseReleased(MouseEvent e) 
	{
		System.out.println("mouseReleased");
	}

	@Override
	public void mouseEntered(MouseEvent e) 
	{
		System.out.println("mouseEntered");
	}

	@Override
	public void mouseExited(MouseEvent e) 
	{
		System.out.println("mouseExited");
	}

	@Override
	public void keyTyped(KeyEvent e) 
	{
		System.out.println("mouseDragged");
	}

	@Override
	public void keyPressed(KeyEvent e) 
	{
		System.out.println("mouseDragged");
	}

	@Override
	public void keyReleased(KeyEvent e) 
	{
		System.out.println("mouseDragged");
	}

	@Override
	public void display(GLAutoDrawable drawable) 
	{
		GL2 gl = drawable.getGL().getGL2();
		System.out.println("display");
	}
	
	/**
	 * called when mouse is used
	 */
	

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
		System.out.println("reshape");
	}
	
	public abstract void showMouseCoordinate(GLAutoDrawable drawable, MouseEvent mouse, GLU glu);

	public MouseEvent getMouse() {
		return mouse;
	}

	public void setMouse(MouseEvent mouse) {
		this.mouse = mouse;
	}

	public GLU getGlu() {
		return glu;
	}

	public void setGlu(GLU glu) {
		this.glu = glu;
	}

}
