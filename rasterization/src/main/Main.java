package main;

import java.awt.Dimension;
import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.GLU;
import javax.swing.JFrame;

import screens.MainWindow;

import com.jogamp.opengl.util.FPSAnimator;
/*
 * UNICAP
 * Guilherme Caraciolo 201210799-5
 * Atividade 1 - Desenhar um triangulo em OpenGL
 * Foi usando JOGL
 */

public class Main {
	
	private static GraphicsEnvironment graphicsEnviorment;
	private static boolean isFullScreen = false;
	public static DisplayMode dm, dm_old;
	private static Dimension xgraphic;
	private static Point pont = new Point(0,0);
	
	private GLU glu = new GLU();

	public static void main(String[] args) {
			init();			
	}
	
	private static void init() {
		//setting up openGL 
				final GLProfile profile = GLProfile.get(GLProfile.GL2);
				GLCapabilities capabilities = new GLCapabilities(profile);
				
				// the canvas
				final GLCanvas glCanvas = new GLCanvas(capabilities);
				MainWindow r = new MainWindow();
				glCanvas.addGLEventListener(r);
				glCanvas.setSize(800, 600);
				
				// control the frames per second
				final FPSAnimator animator = new FPSAnimator(glCanvas, 300, true);
				
				final JFrame frame = new JFrame("Paint");
				frame.getContentPane().add(glCanvas);
				
				// shutdown
				frame.addWindowListener(new WindowAdapter(){
					public void windowClosing(WindowEvent e) {
						if (animator.isStarted()) {
							animator.stop();
						}
						System.exit(0);
					}
				});
				
				frame.setSize(frame.getContentPane().getPreferredSize());
				
				/**
				 * 
				 * centers the screen on start up
				 * 
				 */
				graphicsEnviorment = GraphicsEnvironment.getLocalGraphicsEnvironment();
				GraphicsDevice[] devices = graphicsEnviorment.getScreenDevices();
				
				dm_old = devices[0].getDisplayMode();
				dm = dm_old;
				
				Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
				
				int windowX = Math.max(0, (screenSize.width - frame.getWidth()) / 2);
				int windowY = Math.max(0, (screenSize.height - frame.getHeight()) /2 );
				
				frame.setLocation(windowX, windowY);
				frame.setVisible(true);	
				
			
	}

}
