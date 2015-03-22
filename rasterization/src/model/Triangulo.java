package model;

import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;

import model2D.Operation;


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
public class Triangulo extends Model{
	
	private Point2D p1, p2, p3;
	private Point2D center;
	private int ID;
	private Point2D newPoint;
	
	public Triangulo(int ID) {
		this.p1 = new Point2D(0, 0);
		this.p2 = new Point2D(100, 0);
		this.p3 = new Point2D(50, 100);
		this.ID = ID;
		updateCenter();
	}
	
	
	public Triangulo(Point2D p1, Point2D p2, Point2D p3, int ID) {
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = p3;
		this.ID = ID;
		updateCenter();
	}
	
	public int getID(){
		return ID;
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
	
	public void update() {
		if (newPoint == null) return;
		
		p1.setX(p1.getX() + newPoint.getX());
		p1.setY(p1.getY() + newPoint.getY());
		
		p2.setX(p2.getX() + newPoint.getX());
		p2.setY(p2.getY() + newPoint.getY());
		
		p3.setX(p3.getX() + newPoint.getX());
		p3.setY(p3.getY() + newPoint.getY());
		
		newPoint = null;
	}
	
	//change to render this name of method
	@Override
	public void draw(GL2 gl, GLU glu) {
		
		gl.glPushMatrix();
		gl.glLoadIdentity();
		makeTransformation(gl, glu);
		gl.glBegin(GL2.GL_TRIANGLES);
			gl.glVertex3d(this.getP1().getX(), this.getP1().getY(), 1);
			gl.glVertex3d(this.getP2().getX(), this.getP2().getY(), 1);
			gl.glVertex3d(this.getP3().getX(), this.getP3().getY(), 1);
			update();
		gl.glEnd();
		gl.glPopAttrib();
	}
	
	public void makeTransformation(GL2 gl, GLU glu) {
		
		if (super.getOperations() == null || super.getOperations().size() == 0) return;
		System.out.println("transformando t: " + ID);
		for (Operation operation : super.getOperations()) {
			switch (operation.getType()) {
			case 0: //translacao
				if (gl != null) {
					gl.glTranslatef(operation.getX(), operation.getY(), 0);		
					newPoint = new Point2D(operation.getX(), operation.getY());
				}
				break;
			case 1:
				if (gl != null) {
					gl.glTranslatef(operation.getX(), operation.getY(), 0);		
					newPoint = new Point2D(operation.getX(), operation.getY());
				}
				break;
			case 2:
				if (gl != null) {
					gl.glScalef(operation.getX(), operation.getY(), 0);		
					newPoint = new Point2D(operation.getX(), operation.getY());
				}
				break;
			case 3:
				if (gl != null) {
					if (operation.getX() == 0) operation.setX(1f);
					if (operation.getY() == 0) operation.setY(1f);
					
					//jogar imagem para origem
					gl.glEnable(GL2.GL_NORMALIZE);
					gl.glScalef(- 1, - 1, 0);		
				//	newPoint = new Point2D(operation.getX(), operation.getY());
				}
				break;
			case 4:
		
				break;

			default:
				break;
			}
			
		}
		
		super.setOperations(null);
		
	}

	
	
}
