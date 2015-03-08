package screens;

import java.util.ArrayList;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;

import model.Line;
import model.Model;
import model.Point2D;
import model.Triangulo;

public class MainWindow implements GLEventListener{
	
	private ArrayList<Model> models;
		
	public MainWindow() {
		super();
		models = new ArrayList<Model>();	
		Line l = new Line(new Point2D(0.0f, 0.75f), new Point2D(-0.75f, 0f));
		Line l2 = new Line(new Point2D(-0.75f, 0f), new Point2D(0f, -0.75f));
		this.models.add(l);
		this.models.add(l2);
		
		Triangulo t = new Triangulo(new Point2D(0,0), new Point2D(-1,-1), new Point2D(1,1));
		this.models.add(t);
	}
	
	
	@Override
	public void display(GLAutoDrawable drawable) {
		
		final GL2 gl = drawable.getGL().getGL2();
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT); 
		gl.glLoadIdentity();				
		for (Model model : this.models) {
			model.draw(gl);
			gl.glFlush();
		}	       
		
	}

	@Override
	public void dispose(GLAutoDrawable drawable) {
		final GL2 gl = drawable.getGL().getGL2();
		
	}

	@Override
	public void init(GLAutoDrawable drawable) {
		final GL2 gl = drawable.getGL().getGL2();
		gl.glClearColor(0, 0, 0, 1);
	}

	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {	
		final GL2 gl = drawable.getGL().getGL2();		
		
	}
	
	
	

}
