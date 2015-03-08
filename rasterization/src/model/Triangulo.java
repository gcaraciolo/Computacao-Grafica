package model;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;


public class Triangulo extends Model {
	
	private Point2D p1, p2, p3;
	
	public Triangulo(Point2D p1, Point2D p2, Point2D p3) {
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = p3;
	}
	
	public void draw(final GL2 gl){	
		gl.glBegin(GL.GL_TRIANGLE_FAN);
		gl.glColor3f(1, 0, 0);
		gl.glVertex2f(p1.getX(), p1.getY());
		gl.glColor3f(1, 0, 0);
		gl.glVertex2f(p2.getX(), p2.getY());
		gl.glColor3f(1, 0, 0);
		gl.glVertex2f(p3.getX(), p3.getY());		
		gl.glEnd();
	}

	
	
}
