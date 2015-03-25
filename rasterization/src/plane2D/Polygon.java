package plane2D;

import java.util.ArrayList;

import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;

public abstract class Polygon  {

	public void translate(ArrayList<WCPt2D> verts, double tx, double ty) {
		for (int k = 0; k < verts.size(); k++) {
			verts.get(k).x += tx; 
			verts.get(k).y += ty;
		}		
	}
	
	protected void rotate(ArrayList<WCPt2D> verts, WCPt2D pivot, double theta) {	
		for (int k = 0; k < verts.size(); k++) {
			verts.get(k).x = pivot.x + (verts.get(k).x - pivot.x) * Math.cos(theta)
					- (verts.get(k).y - pivot.y) * Math.sin(theta);
			verts.get(k).y = pivot.x + (verts.get(k).x - pivot.x) * Math.sin(theta)
					+ (verts.get(k).y - pivot.y) * Math.cos(theta);
		}		
	}
	
	public void scale(ArrayList<WCPt2D> verts, WCPt2D fixedPt, double sx, double sy) {
		for (int k = 0; k < verts.size(); k++) {
			verts.get(k).x = verts.get(k).x * sx + fixedPt.x * (1 - sx); 
			verts.get(k).y = verts.get(k).y * sy + fixedPt.y * (1 - sy);
		}
	}
	
	public void reflexion(ArrayList<WCPt2D> verts, boolean x, boolean y) {
		for (int k = 0; k < verts.size(); k++) {
			if (x)  verts.get(k).x *= -1;
			if (y)  verts.get(k).y *= -1;
		}

	}
	
	public abstract void draw(GL2 gl, GLU glu);
	
}
