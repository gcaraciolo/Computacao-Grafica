package model;

import java.awt.event.MouseEvent;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.glu.GLU;

public class CartesianPlane2D extends Model{
	
	private Line abscissa;
	private Line ordered;
	private Line topLeftToBottomRight;
	private Line bottomLeftToTopRight;
	
	public CartesianPlane2D(int center, int x, int y) {
		abscissa = new Line(new Point2D(-x, center), new Point2D(x, center));
		ordered = new Line(new Point2D(center, -y), new Point2D(center,	y));
		
	}
//	public CartesianPlane2D() {
//		abscissa = new Line(new Point2D(-400, 0), new Point2D(400, 0));
//		ordered = new Line(new Point2D(0, -300), new Point2D(0,	300));
//		//octeto plane
//		topLeftToBottomRight = new Line(new Point2D(-400, 300), new Point2D(400, -300));
//		bottomLeftToTopRight = new Line(new Point2D(-400, -300), new Point2D(400, 300));
//	}

	@Override
	public void draw(GL2 gl, GLU glu) {
		abscissa.draw(gl, glu);
		ordered.draw(gl, glu);
		gl.glFlush();
	}
	
	public void drawOcteto(GL2 gl, GLU glu) {
		topLeftToBottomRight.draw(gl, glu);
		bottomLeftToTopRight.draw(gl, glu);
		gl.glFlush();
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
