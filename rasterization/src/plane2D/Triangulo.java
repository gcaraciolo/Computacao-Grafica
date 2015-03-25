package plane2D;

import java.util.ArrayList;

import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;

public class Triangulo extends Polygon {

	public WCPt2D p1, p2, p3;
	private int ID;
	ArrayList<WCPt2D> points = new ArrayList<WCPt2D>();

	public Triangulo(int ID) {
		this.ID = ID;
		this.p1 = new WCPt2D(0, 0);
		this.p2 = new WCPt2D(100, 0);
		this.p3 = new WCPt2D(50, 100);
		
		points.add(p1);
		points.add(p2);
		points.add(p3);
		
	}

	public Triangulo(int ID, WCPt2D p1, WCPt2D p2, WCPt2D p3) {
		this.ID = ID;
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = p3;		
		
		points.add(p1);
		points.add(p2);
		points.add(p3);
	}

	public int getID() {
		return ID;
	}


	public void translate(double tx, double ty) 
	{
		super.translate(points, tx, ty);
	}
	
	public void rotate(WCPt2D pivot, double theta) 
	{				
		super.rotate(points, pivot, theta);
	}

	public void scale(WCPt2D fixedPt, double sx, double sy) 
	{
		super.scale(points, fixedPt, sx, sy);
	}

	public void reflexion(boolean x, boolean y) 
	{
		super.reflexion(points, x, y);
	}
	
	
	public void draw(GL2 gl, GLU glu) {
		gl.glPushMatrix();
		gl.glLoadIdentity();
		gl.glBegin(GL2.GL_TRIANGLES);
			gl.glVertex3d(p1.x, p1.y, 1);
			gl.glVertex3d(p2.x, p2.y, 1);
			gl.glVertex3d(p3.x, p3.y, 1);
		gl.glEnd();
		gl.glPopMatrix();	
	}



}
