package plane2D;

import java.util.ArrayList;

import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;
/**
 * 
 * @author Guilherme Caraciolo 201210799-5
 * @author Dimas Santos
 * @author Arthur Brito
 *
 */
public class Quadrado extends Polygon {

	public WCPt2D p1, p2, p3, p4;
	private int ID;
	ArrayList<WCPt2D> verts = new ArrayList<WCPt2D>();

	public Quadrado(int ID) {
		super();
		this.ID = ID;
		this.p1 = new WCPt2D(0, 0);
		this.p2 = new WCPt2D(100, 0);
		this.p3 = new WCPt2D(100, 100);
		this.p4 = new WCPt2D(0, 100);
		
		verts.add(p1);
		verts.add(p2);
		verts.add(p3);
		verts.add(p4);
		
	}

	public Quadrado(int ID, WCPt2D p1, WCPt2D p2, WCPt2D p3, WCPt2D p4) {
		super();
		this.ID = ID;
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = p3;		
		this.p4 = p4;
		
		verts.add(p1);
		verts.add(p2);
		verts.add(p3);
		verts.add(p4);
	}

	public int getID() {
		return ID;
	}

	/* calculate position of quad centroid */
	public WCPt2D getCenterIDPt() {			
		double centerX = (p1.x + p2.x) / 2;
		double centerY = (p1.y + p4.y) / 2;
		return new WCPt2D(centerX, centerY);
	}

	public void translate(double tx, double ty) 
	{
		super.translate(verts, tx, ty);
	}
	
	public void rotate(WCPt2D pivot, double theta) 
	{				
		super.rotate(pivot, theta);
		
		//make matrix of old points
		double oldPoints[][] = new double[3][4];
		
		oldPoints[0][0] = p1.x;  oldPoints[0][1] = p2.x;  oldPoints[0][2] = p3.x; 	oldPoints[0][3] = p4.x;  
		oldPoints[1][0] = p1.y;  oldPoints[1][1] = p2.y;  oldPoints[1][2] = p3.y; 	oldPoints[1][3] = p4.y;
		oldPoints[2][0] = 1;	 oldPoints[2][1] = 1;     oldPoints[2][2] = 1;		oldPoints[2][3] = 1;
				
		double[][] newPoints = super.transformVerts2D(oldPoints);
		
		
		p1.x = newPoints[0][0];  p2.x = newPoints[1][0];  p3.x = newPoints[2][0];   p4.x = newPoints[3][0];
		p1.y = newPoints[0][1];  p2.y = newPoints[1][1];  p3.y = newPoints[2][1];   p4.y = newPoints[3][1];
		
		
		
	}

	public void scale(WCPt2D fixedPt, double sx, double sy) 
	{
		super.scale(verts, fixedPt, sx, sy);
	}

	public void reflexion(boolean x, boolean y) 
	{
		super.reflexion(verts, x, y);
	}
	
	// when the shear function is called, the matComposite is made.
	public void shear(double a, double b) {
		super.shear(a, b);
		
		//make matrix of old points
		double oldPoints[][] = new double[3][4];
		
		oldPoints[0][0] = p1.x;  oldPoints[0][1] = p2.x;  oldPoints[0][2] = p3.x; 	oldPoints[0][3] = p4.x;  
		oldPoints[1][0] = p1.y;  oldPoints[1][1] = p2.y;  oldPoints[1][2] = p3.y; 	oldPoints[1][3] = p4.y;
		oldPoints[2][0] = 1;	 oldPoints[2][1] = 1;     oldPoints[2][2] = 1;		oldPoints[2][3] = 1;
		
		double[][] newPoints = super.transformVerts2D(oldPoints);
		
		p1.x = newPoints[0][0];  p2.x = newPoints[1][0];  p3.x = newPoints[2][0];   p4.x = newPoints[3][0];
		p1.y = newPoints[0][1];  p2.y = newPoints[1][1];  p3.y = newPoints[2][1];   p4.y = newPoints[3][1];
	}
	
	public void draw(GL2 gl, GLU glu) {
		gl.glPushMatrix();
		gl.glLoadIdentity();
		gl.glBegin(GL2.GL_POLYGON);
			gl.glVertex2d(p1.x, p1.y);
			gl.glVertex2d(p2.x, p2.y);
			gl.glVertex2d(p3.x, p3.y);
			gl.glVertex2d(p4.x, p4.y);
		gl.glEnd();
		gl.glPopMatrix();	
	}



}
