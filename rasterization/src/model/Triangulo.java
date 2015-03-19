package model;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.glu.GLU;

import com.jogamp.opengl.util.FPSAnimator;


/**
 * 
 * @author gcaraciolo
 *
 * formato:
 * 
 *     p3
 * 
 * p1      p2
 *
 */
public class Triangulo  extends Model{
	
	private Point2D p1, p2, p3;
	private Point2D center;
	
	public Triangulo(Point2D p1, Point2D p2, Point2D p3) {
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = p3;
		updateCenter();
	}
	
	private void updateCenter() {
		float cX = (p1.getX() + p2.getX()) / 2;		
		float cY = (cX +  p3.getY()) / 2;
		//primeiro quadrante only
		center = new Point2D(cX + p1.getX(), cY + p1.getY());
	}
	
	public Point2D getCenter() {
		return center;
	}
	
	public Point2D getP1() {
		return p1;
	}
	
	public void setP1(Point2D p1) {
		this.p1 = p1;
		updateCenter();
	}
	
	public Point2D getP2() {
		return p2;
	}
	
	public void setP2(Point2D p2) {
		this.p2 = p2;
		updateCenter();
	}
	
	public Point2D getP3() {
		return p3;
	}

	public void setP3(Point2D p3) {
		this.p3 = p3;
		updateCenter();
	}
	

	
	boolean teto, chao = true;
	float yt = 0.0f;
	//change to render this name of method
	@Override
	public void draw(GL2 gl, GLU glu) {
		if (super.isAnimated()) {
			changeAnpha(gl, glu);						
		} else {
			gl.glBegin(GL2.GL_TRIANGLES);
				gl.glVertex3d(this.getP1().getX(), this.getP1().getY(), 1);
				gl.glVertex3d(this.getP2().getX(), this.getP2().getY(), 1);
				gl.glVertex3d(this.getP3().getX(), this.getP3().getY(), 1);
			gl.glEnd();	
		}
		
	}
	
	
	private void changeAnpha(GL2 gl, GLU glu) {
		if (chao) {
			yt += 0.02;
			if (yt > 2) {
				teto = true;
				chao = false;
			}
		} else if (teto) {
			yt += -0.02;
			if (yt < 0) {
				chao = true;
				teto = false;
			}
		}			
	
		System.out.println(yt);
				
		gl.glBegin(GL2.GL_TRIANGLES);
			gl.glVertex3d(this.getP1().getX() - yt * 50, this.getP1().getY(), 1);		
			gl.glVertex3d(this.getP2().getX()- yt * 50, this.getP2().getY(), 1);
			gl.glVertex3d(this.getP3().getX(), this.getP3().getY(), 1);
		gl.glEnd();	
	
	}

	
	
}
