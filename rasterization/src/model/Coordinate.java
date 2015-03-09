package model;
/**
 *  
 * @author guilherme
 *
 * A classe onde especificarei as coordenadas de um vértice (ou pixel, no caso). 
 *
 */
public class Coordinate {
	
	private int x;
	private int y;
	
	public Coordinate () {
		
	}
	
	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() { return x; }
	
	public int getY() { return y; }

}
