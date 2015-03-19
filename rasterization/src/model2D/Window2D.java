package model2D;

import java.awt.Color;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.GLU;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;

import model.Model;
import model.Point2D;
import model.Triangulo;

import com.jogamp.opengl.util.FPSAnimator;

public class Window2D extends JFrame implements GLEventListener, 
												  MouseListener, 
											MouseMotionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//GL
	private GLProfile profile;
	private GLCapabilities capabilities;
	private GLCanvas canvas;
	private Frame frame;
	private GLU glu;
	
	
	//datas
	private String title;
	public static int FPS = 60;
	FPSAnimator animator;
	
	private ArrayList<Model> polygons;
	private JPanel menu;
	private JButton btnTriangulo;
	private JButton btnQuadrado;
	private JList list;
	
	public Window2D(String title, int width, int height) {
		setBackground(Color.YELLOW);
		this.title = title;
		this.glu = new GLU();
		this.polygons = new ArrayList<>();
			
		initFrame(width, height);		
	}

	@Override
	public void display(GLAutoDrawable drawable) 
	{
		GL2 gl = drawable.getGL().getGL2();
		render(gl, glu, drawable);
		
	}

	@Override
	public void dispose(GLAutoDrawable drawable) 
	{
	
		GL2 gl = drawable.getGL().getGL2();
	}

	@Override
	public void init(GLAutoDrawable drawable) 
	{
		GL2 gl = drawable.getGL().getGL2();
		
	}
	
	@Override
	public void reshape(GLAutoDrawable drawable, 
								    int x, 
								    int y, 
								    int width,
								    int height) 
	{
		GL2 gl = drawable.getGL().getGL2();
		configViewport(gl, glu, width, height);
		
	}
	
	private void initFrame(int width, int height) {
		initGL(width, height);
		
		//frame = new Frame(title);
        setSize(800, 600);
        getContentPane().add(canvas); //need to be after initialize GL, to add canvas listener
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0); // exit program when touch in X button
            }
        });
        
        getContentPane().setLayout(null);        
        menu = new JPanel();
        menu.setBackground(new Color(51, 204, 204));
        menu.setBounds(650, 0, 150, 600);
        getContentPane().add(menu);
        
        buttons(menu);       
        {
        	list = new JList<Model>();
        	
        	menu.add(list);
        }
       
        setVisible(true);        
	}
	
	private void buttons (JPanel panel) {
		btnTriangulo = new JButton("Triangulo");
        btnTriangulo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				createTriangulo();				
			}
		});
        
        btnQuadrado = new JButton("Quadrado");
        
        
        panel.add(btnTriangulo);
        panel.add(btnQuadrado);
	        
	}
	
	private void initGL(int width, int height) {
		profile = GLProfile.get(GLProfile.GL2);
		capabilities = new GLCapabilities(profile);	
		
		canvas = new GLCanvas(capabilities);
        canvas.addGLEventListener(this);
        canvas.addMouseListener(this);
        canvas.addMouseMotionListener(this);
        canvas.setBounds(0, 0, width - 150, height);
               
        animator = new FPSAnimator(canvas, FPS);
	}
	
	private void configViewport(GL2 gl, GLU glu, int width, int height) {
		
		int[] viewport = new int[4];
		double[] modelview = new double[16];
		double[] projection = new double[16];
		
		gl.glClearColorIi(1, 0, 0, 0);
		gl.glGetIntegerv(GL.GL_VIEWPORT, viewport, 0); //coordinates of screen
		gl.glGetDoublev(GL2.GL_MODELVIEW_MATRIX, modelview, 0);
		gl.glGetDoublev(GL2.GL_PROJECTION_MATRIX, projection, 0);
		
		gl.glViewport(0, 0, width, height);
		gl.glMatrixMode(GL2.GL_PROJECTION);
		glu.gluOrtho2D(0.0, (float)width, 0.0, (float)height);		
		gl.glMatrixMode(GL2.GL_MODELVIEW);
	}
	
	public void render(GL2 gl, GLU glu, GLAutoDrawable drawable) {
		
		gl.glClearColorIi(0, 1, 0, 0);
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT); // limpa a tela
		
		for (Model model : polygons) {
			model.draw(gl, glu);
			model.setAnimated(true);
		}		
		
		gl.glFlush();
		
	}
	int x = 10;
	public void createTriangulo() {
		Triangulo t = new Triangulo(new Point2D(x, 250), new Point2D(x + 100, 250), new Point2D(x + 50, 350), 2);
		polygons.add(t);
		x += 100;
		canvas.display();
		
	}

	@Override
	public void mouseDragged(MouseEvent e) 
	{
		System.out.println("mouseDragged");
	}

	@Override
	public void mouseMoved(MouseEvent e) 
	{
		//System.out.println("");
	}

	@Override
	public void mouseClicked(MouseEvent e) 
	{
		System.out.println("mouseClicked");
	}

	@Override
	public void mousePressed(MouseEvent e) 
	{
		//setMouse(e);
		//canvas.display();
		System.out.println("mousePressed");
	}

	@Override
	public void mouseReleased(MouseEvent e) 
	{
		System.out.println("mouseReleased");
	}

	@Override
	public void mouseEntered(MouseEvent e) 
	{
		System.out.println("mouseEntered");
	}

	@Override
	public void mouseExited(MouseEvent e) 
	{
		System.out.println("mouseExited");
	}
}
