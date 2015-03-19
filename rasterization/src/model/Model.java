package model;

import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;

public abstract class Model {

	private boolean isAnimated;
	
	public abstract void draw(GL2 gl, GLU glu);
	
	public boolean isAnimated() {
		return isAnimated;
	}
	
	public void setAnimated(boolean isAnimated) {
		this.isAnimated = isAnimated;
	}
	
	
}
