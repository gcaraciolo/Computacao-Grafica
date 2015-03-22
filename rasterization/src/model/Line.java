package model;

import java.awt.event.MouseEvent;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.glu.GLU;

public class Line extends Model{

	private Point2D p1, p2;
	private Color color;
	
	public Line(Point2D p1, Point2D p2){
		this.p1 = p1;
		this.p2 = p2;
	}
	
	public Line(Point2D p1, Point2D p2, Color color){
		this.p1 = p1;
		this.p2 = p2;
		this.color = color;
	}
	
	@Override
	public void draw(GL2 gl, GLU glu) {
		
		gl.glBegin( GL2.GL_LINES );
			if (this.color != null) { 
				gl.glColor3f(this.color.getR(), this.color.getG(), this.color.getB());
			}
			gl.glVertex3f( this.p1.getX(), this.p1.getY(), 0);	    		  
			gl.glVertex3f( this.p2.getX(), this.p2.getY(), 0);
	    gl.glEnd();
	    		
	}

	@Override
	public void display(GLAutoDrawable arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose(GLAutoDrawable arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(GLAutoDrawable arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3,
			int arg4) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void makeTransformation(GL2 gl, GLU glu) {
		// TODO Auto-generated method stub
		
	}

	
	

	
}
