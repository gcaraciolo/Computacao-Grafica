

import model.Color;
import model.Coordinate;


public class Vertex {

	private Coordinate coordinate;
	private Color color;
	
	public Vertex(Coordinate coordinate, Color color) {
		this.setCoordinate(coordinate);
		this.setColor(color);
	}

	public Coordinate getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	
}
