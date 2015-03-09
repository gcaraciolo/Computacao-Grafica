package model;

public class Vertex {

	private Coordinate coordinate;
	private Color color;
	
	public Vertex () { 		}
	
	public Vertex (Coordinate coordinate, Color color) { 	
		this.coordinate = coordinate;
		this.color = color;
	}
	
	public Coordinate getCoordinate() { return this.coordinate; }
	public Color getColor() { return this.color; }
}
