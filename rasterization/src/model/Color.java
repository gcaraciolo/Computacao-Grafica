package model;

public class Color {

	private char R;
	private char G;
	private char B;
	private char A;
	
	public Color() { 	}
	
	public Color(char R, char G, char B, char A) {
        this.R   = R;
        this.G   = G;
        this.B   = B;
        this.A   = A;
    }
	
	public char getR() { return R; }
	public char getG() { return G; }
	public char getB() { return B; }
	public char getA() { return A; }
	
	
}
