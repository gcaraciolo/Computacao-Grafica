package model2D;


/**
 * 
 * @author gcaraciolo
 *
 *
 * type :
 *  0 -> translacao
 *  1 -> rotacao
 *  2 -> reflexao
 *  3 -> escala
 *  4 -> cisalhamento
 */
public class Operation {
	
	private Float x;
	private Float y;
	private Float angulo;
	private Integer type;	
	
		
	public Operation(Integer type) {
		this.type = type;
		this.x = 0f;
		this.y = 0f;
	}
	
	public Operation(Integer type, Float x, Float y) {
		this.type = type;
		this.x = x;
		this.y = y;
	}
	
	public Operation(Integer type, Float angulo) {
		this.type = type;
		this.angulo = angulo;
		this.x = 0f;
		this.y = 0f;
	}
	
	public Float getX() {
		return x;
	}

	public void setX(Float x) {
		this.x = x;
	}

	public Float getY() {
		return y;
	}

	public void setY(Float y) {
		this.y = y;
	}

	public Float getAngulo() {
		return angulo;
	}

	public void setAngulo(Float angulo) {
		this.angulo = angulo;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	//methods
	public void makeOperation() {
		
	}
	
	
}
