package main;

import refactoring2.Window;


/**
 * UNICAP
 * Guilherme Caraciolo 201210799-5
 * Atividade 1 - Desenhar um triangulo em OpenGL
 * Foi usando JOGL
 */

public class Main {
	
	public static int WINDOW_WIDHT = 800;
	public static int WINDOW_HEIGHT = 600;	
	
	public static void main(String[] args) {
		try {
			new Window("Projeto de openGL", WINDOW_WIDHT, WINDOW_HEIGHT).show();	
		} catch (Exception e) {

		}		
	}
	
}
