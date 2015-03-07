package screens;

/* 
 * UNICAP
 * Guilherme Caraciolo 201210799-5
 * Atividade 1 - Desenhar um triangulo em OpenGL
 * Foi usando JOGL
 */

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;
import javax.swing.JFrame;

public class MainWindow extends JFrame implements GLEventListener  {
    private static final long serialVersionUID = 1L;
	private String title;
	private int width;
	private int height;
	
    public MainWindow(String title, int width, int height, boolean visible) {
    	super(title);
    	GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);
        GLCanvas canvas = new GLCanvas(capabilities);
        canvas.addGLEventListener(this);
        this.getContentPane().add(canvas);
        
    	this.title = title;
        this.width = width;
        this.height = height;        
        
        this.setName(this.title);        
        this.setSize(this.width, this.height);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(visible);
        this.setResizable(false);
                
        canvas.requestFocusInWindow();
    	
    }
    
    @Override
    public void display(GLAutoDrawable drawable) {
    	GL2 gl = drawable.getGL().getGL2();
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        
        gl.glFlush();
    }

    @Override
    public void dispose(GLAutoDrawable drawable) {
    }

    @Override
    public void init(GLAutoDrawable drawable) {
    	 GL2 gl = drawable.getGL().getGL2();
         gl.glClearColor(1, 1, 1, 1);
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
    	
    }
        
}