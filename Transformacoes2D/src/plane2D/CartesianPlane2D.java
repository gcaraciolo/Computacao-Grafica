package plane2D;

import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;

/**
 * 
 * @author Guilherme Caraciolo 201210799-5
 * @author Dimas Santos
 * @author Arthur Brito
 *
 */
public class CartesianPlane2D extends Polygon{
	
	private Line abscissa;
	private Line ordered;
	
	public CartesianPlane2D(int center, int x, int y) {
		abscissa = new Line(new WCPt2D(-x, center), new WCPt2D(x, center));
		ordered = new Line(new WCPt2D(center, -y), new WCPt2D(center,	y));
		
	}

	public void draw(GL2 gl, GLU glu) {
		abscissa.draw(gl, glu);		
		ordered.draw(gl, glu);		
	}

}
