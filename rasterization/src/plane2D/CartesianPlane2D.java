package plane2D;

import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;

public class CartesianPlane2D extends Polygon{
	
	private Line abscissa;
	private Line ordered;
//	private Line topLeftToBottomRight;
//	private Line bottomLeftToTopRight;
	
	public CartesianPlane2D(int center, int x, int y) {
		abscissa = new Line(new WCPt2D(-x, center), new WCPt2D(x, center));
		ordered = new Line(new WCPt2D(center, -y), new WCPt2D(center,	y));
		
	}
//	public CartesianPlane2D() {
//		abscissa = new Line(new WCPt2D(-400, 0), new WCPt2D(400, 0));
//		ordered = new Line(new WCPt2D(0, -300), new WCPt2D(0,	300));
//		//octeto plane
//		topLeftToBottomRight = new Line(new WCPt2D(-400, 300), new WCPt2D(400, -300));
//		bottomLeftToTopRight = new Line(new WCPt2D(-400, -300), new WCPt2D(400, 300));
//	}

	public void draw(GL2 gl, GLU glu) {
		abscissa.draw(gl, glu);		
		ordered.draw(gl, glu);		
	}
	
//	public void drawOcteto(GL2 gl, GLU glu) {
//		topLeftToBottomRight.draw(gl, glu);
//		bottomLeftToTopRight.draw(gl, glu);
//	}

}
