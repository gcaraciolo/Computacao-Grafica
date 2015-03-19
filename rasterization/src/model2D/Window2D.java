package model2D;

import java.awt.Frame;
import java.awt.List;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.GLU;

import com.jogamp.opengl.util.FPSAnimator;

import model.Model;
import model.Point2D;
import model.Triangulo;

public class Window2D implements GLEventListener{
	
	//GL
	private GLProfile profile;
	private GLCapabilities capabilities;
	private GLCanvas canvas;
	private Frame frame;
	private GLU glu;
	
	
	//datas
	private String title;
	public static int FPS = 60;
	
	private ArrayList<Model> polygons;
	
	public Window2D(String title, int width, int height) {
		this.title = title;
		this.glu = new GLU();
		this.polygons = new ArrayList<>();
		initFrame(width, height);
		
		objetos(); //add objects to be draw
	}

	@Override
	public void display(GLAutoDrawable drawable) 
	{
		GL2 gl = drawable.getGL().getGL2();
		render(gl, glu, drawable);
		
	}

	@Override
	public void dispose(GLAutoDrawable drawable) 
	{
	
		GL2 gl = drawable.getGL().getGL2();
	}

	@Override
	public void init(GLAutoDrawable drawable) 
	{
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
		configViewport(gl, glu, width, height);
		
	}
	
	private void initFrame(int width, int height) {
		initGL();
		
		frame = new Frame(title);
        frame.setSize(width, height);
        frame.add(canvas); //need to be after initialize GL, to add canvas listener
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0); // exit program when touch in X button
            }
        });
        frame.setVisible(true);
        
	}
	
	private void initGL(){
		profile = GLProfile.get(GLProfile.GL2);
		capabilities = new GLCapabilities(profile);
        canvas = new GLCanvas(capabilities);
        canvas.addGLEventListener(this);
        
        FPSAnimator animator = new FPSAnimator(canvas, FPS);
		animator.start();
	}
	
	private void configViewport(GL2 gl, GLU glu, int width, int height) {
		
		int[] viewport = new int[4];
		double[] modelview = new double[16];
		double[] projection = new double[16];
		
		gl.glClearColorIi(1, 0, 0, 0);
		gl.glGetIntegerv(GL.GL_VIEWPORT, viewport, 0); //coordinates of screen
		gl.glGetDoublev(GL2.GL_MODELVIEW_MATRIX, modelview, 0);
		gl.glGetDoublev(GL2.GL_PROJECTION_MATRIX, projection, 0);
		
		gl.glViewport(0, 0, width, height);
		gl.glMatrixMode(GL2.GL_PROJECTION);
		glu.gluOrtho2D(0.0, (float)width, 0.0, (float)height);		
		gl.glMatrixMode(GL2.GL_MODELVIEW);
	}
	
	public void render(GL2 gl, GLU glu, GLAutoDrawable drawable) {
		
		
		
		gl.glClearColorIi(0, 1, 0, 0);
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT); // limpa a tela
		
		Triangulo t = (Triangulo) polygons.get(2);
		t.draw(gl, glu);
		
		Triangulo t2 = (Triangulo) polygons.get(1);
		t2.draw(gl, glu);
		t2.setAnimated(true);
		
		
		//t.setAnimated(true);
		
//		
//		
//		for (Model model : polygons) {
//			model.draw(gl, glu);
//			model.setAnimated(true);
//		}
//		
		
			
		
		gl.glFlush();
	}
	
	public void objetos() {
		Triangulo t = new Triangulo(new Point2D(10, 10), new Point2D(20, 10), new Point2D(15, 30));
		Triangulo t2 = new Triangulo(new Point2D(100, 100), new Point2D(200, 100), new Point2D(100, 250));
		Triangulo t3 = new Triangulo(new Point2D(200, 100), new Point2D(400, 100), new Point2D(300, 200));
		
		polygons.add(t);
		polygons.add(t2);
		polygons.add(t3);
		
		
		System.out.println(t.getCenter().getX() + " " + t.getCenter().getY());
	}

}
