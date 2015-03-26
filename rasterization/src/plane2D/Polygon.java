package plane2D;

import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;

public abstract class Polygon  {

	double matComposite[][];
	int dim;

	public Polygon() {
		dim = 3;
		matComposite = new double[dim][dim];
		matrix3x3SetIdentity(matComposite);
	}

	public void matrix3x3SetIdentity(double matIdent3x3[][] ) {
		for(int row = 0; row < dim; row++) {
			for(int col = 0; col < dim; col++){ 
				if (row == col)	matIdent3x3[row][col] = 1;
				else matIdent3x3[row][col] = 0;
			}
		}				
	}

	/* premultiply matrix m1 times matrix m2, and return a new matrix multiplied*/
	public double[][] matrix3x3PreMultiply(double m1[][], double m2[][]) {
		double matTemp[][] = new double[dim][dim];
		for(int row = 0; row < dim; row++) {
			for(int col = 0; col < dim; col++){
				matTemp[row][col] = m1[row][0] * m2[0][col] +
									m1[row][1] * m2[1][col] +
									m1[row][2] * m2[2][col];
			}
		}

		return matTemp;
	}

	public double[][] transposta(double [][] orige) {
		double mt[][] = new double[3][3];
		
		mt[0][0] = orige[0][0];    	mt[0][1] = orige[1][0];		mt[0][2] = orige[2][0];		
		mt[1][0] = orige[0][1];    	mt[1][1] = orige[1][1];		mt[1][2] = orige[2][1];		
		mt[2][0] = orige[0][2];    	mt[2][1] = orige[1][2];		mt[2][2] = orige[2][2];	
		
		return mt;
	}

	/* using the composite matrix, calculate transformed coordinates */
	public double[][] transformVerts2D(double[][] oldPoints) {
		
		double[][] newPoints = new double[3][3];
		
		for(int col = 0; col < dim; col++) {
			for(int row = 0; row < dim; row++) {					
				newPoints[col][row] = 	matComposite[row][0] * oldPoints[0][col] +
										matComposite[row][1] * oldPoints[1][col] +
										matComposite[row][2] * oldPoints[2][col];
			}
		}
		
		return newPoints;
	}

	protected void translate(ArrayList<WCPt2D> verts, double tx, double ty) {
		for (int k = 0; k < verts.size(); k++) {
			verts.get(k).x += tx; 
			verts.get(k).y += ty;
		}		
	}

	private double[][] MatrixRotate(double cos, double sin) {
		double matRot[][] = new double[dim][dim];
		matrix3x3SetIdentity(matRot);

		matRot[0][0] = cos; matRot[0][1] = -sin;
		matRot[1][0] = sin; matRot[1][1] = cos;

		return matRot;
	}
	
	private double[][] MatrixShear(double a, double b) {
		double matRot[][] = new double[dim][dim];
		matrix3x3SetIdentity(matRot);

		matRot[1][0] = a; 
		matRot[0][1] = b;

		return matRot;
	}

	private double[][] MatrixTranslate(double tx, double ty) {
		double matRot[][] = new double[dim][dim];
		matrix3x3SetIdentity(matRot);

		matRot[0][2] = tx; matRot[1][2] = ty;

		return matRot;
	}


	protected void rotate(WCPt2D pivot, double theta) {

		matrix3x3SetIdentity(matComposite);
		
		double cos = Math.cos(Math.toRadians(theta));
		double sin = Math.sin(Math.toRadians(theta));
		
		cos = (float) Math.round(cos * 100) / 100; // for 90 and 270 degrees

		double goOrigem[][] = MatrixTranslate(- pivot.x, - pivot.y);
		double rot[][] = MatrixRotate(cos, sin);
		double goBack[][] = MatrixTranslate(pivot.x, pivot.y); 
		
	
		
		matComposite = matrix3x3PreMultiply(goOrigem, rot);
		matComposite = matrix3x3PreMultiply(matComposite, goBack);
	
	}

	protected void scale(ArrayList<WCPt2D> verts, WCPt2D fixedPt, double sx, double sy) {
		for (int k = 0; k < verts.size(); k++) {
			verts.get(k).x = verts.get(k).x * sx + fixedPt.x * (1 - sx); 
			verts.get(k).y = verts.get(k).y * sy + fixedPt.y * (1 - sy);
		}
	}

	protected void reflexion(ArrayList<WCPt2D> verts, boolean x, boolean y) {
		for (int k = 0; k < verts.size(); k++) {
			if (x)  verts.get(k).x *= -1;
			if (y)  verts.get(k).y *= -1;
		}

	}
	
	protected void shear(double a, double b) {
		matComposite =  MatrixShear(a, b);
	}

	public abstract void draw(GL2 gl, GLU glu);

}
