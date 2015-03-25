package plane2D;

import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;

public class Line {

	private WCPt2D p1, p2;
	
	public Line(WCPt2D p1, WCPt2D p2){
		this.p1 = p1;
		this.p2 = p2;
	}
	
	public void draw(GL2 gl, GLU glu) {
		
		
		gl.glPushMatrix();
		gl.glLoadIdentity();	
		gl.glBegin( GL2.GL_LINES );
			gl.glVertex3d( this.p1.x, this.p1.y, 0);	    		  
			gl.glVertex3d( this.p2.x, this.p2.y, 0);
		gl.glEnd();
		gl.glPopAttrib();
	}

}
