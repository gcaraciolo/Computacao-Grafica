package model;

import java.util.List;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;

import model2D.Operation;

public abstract class Model implements GLEventListener{

	private boolean isAnimated;
	private List<Operation> operations;
	
	public abstract void draw(GL2 gl, GLU glu);
	public abstract void makeTransformation(GL2 gl, GLU glu);
	
	public boolean isAnimated() {
		return isAnimated;
	}
	
	public void setAnimated(boolean isAnimated) {
		this.isAnimated = isAnimated;
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
	
	public List<Operation> getOperations() {
		return operations;
	}

	public void setOperations(List<Operation> operations) {
		this.operations = operations;
	}
	
}
