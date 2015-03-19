package model;

import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;

public class Point2D extends Model {
	private float x;
	private float y;
	
	public Point2D(float x, float y) {
		this.setX(x);
		this.setY(y);
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	@Override
	public void draw(GL2 gl, GLU glu) {
		gl.glBegin (GL2.GL_POINTS);
		
		gl.glPointParameteri(GL2.GL_POINT_SPRITE_COORD_ORIGIN, GL2.GL_LOWER_LEFT);
		gl.glPointSize(GL2.GL_POINT_SIZE_MAX);
		gl.glVertex3f( x,  y, 0);		
		gl.glEnd();
		
	}
	
}
