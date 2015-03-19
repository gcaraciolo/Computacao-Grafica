package refactoring2;

import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;
import javax.swing.SwingUtilities;

import main.GameWindow;
import model.ShaderTest;

public class Window {
	//GL
	private GLProfile profile;
	private GLCapabilities capabilities;
	private GLCanvas canvas;
	
	//Frame
	Frame frame;
	
	//size
	private int width;
	private int height;
	
	//data
	private String title;
	
	
	public Window(String title, int width, int height) {
		this.title = title;
		this.width = width;
		this.height = height;
		initGL();
		initFrame();
	}
	
	private void initFrame(){
		frame = new Frame(title);
        frame.setSize(width, height);
        frame.add(canvas);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0); // exit program when touch in X button
            }
        });
	}
	
	public void show() {
		frame.setVisible(true); 
	}
	
	private  void initGL() {
		profile = GLProfile.get(GLProfile.GL2);
		capabilities = new GLCapabilities(profile);
        canvas = new GLCanvas(capabilities);   
        
        //View view = new View(canvas, null, null, width, height);
		GameWindow gw = new GameWindow(canvas, null, null, width, height);
        
       
      
	}

	
}
