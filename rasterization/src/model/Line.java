package model;

import javax.media.opengl.GL2;

public class Line extends Model{

	private Point2D p1, p2;
	
	public Line(Point2D p1, Point2D p2){
		this.p1 = p1;
		this.p2 = p2;
	}
	
	@Override
	public void draw(GL2 gl) {
		
		gl.glBegin( GL2.GL_LINES );
	      gl.glVertex3f( this.p1.getX(), this.p1.getY(), 0);	    		  
	      gl.glVertex3f( this.p2.getX(), this.p2.getY(), 0);
	    gl.glEnd();
		
	}

	
}
