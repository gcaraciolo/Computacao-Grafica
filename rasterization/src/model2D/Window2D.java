package model2D;

import java.awt.Color;
import java.awt.Frame;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.GLU;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import model.Model;
import model.Point2D;
import model.Triangulo;

import com.jogamp.opengl.util.FPSAnimator;

import javax.swing.JTextPane;

public class Window2D extends JFrame implements GLEventListener						   
{
	
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
	
	private JPanel panel;
	private JTextField txtX;
	private JTextField txtY;
	private JTextField txtAngulo;
	private JRadioButton rdbtnX;
	private JRadioButton rdbtnY;
	private JButton btnMakeTransformation;
	private JLabel lblX;    
	private JLabel lblY;    
	private JLabel lblAngulo;
	private JComboBox<String> cbTransformations;
	private TextArea showOperations;
	
	//lista de valores de operacao
	private HashMap<Integer, String> operations;
	
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
		
        setSize(800, 600);
        getContentPane().setLayout(null);
        getContentPane().add(canvas);
        
        panel = new JPanel();
        panel.setBackground(Color.CYAN);
        panel.setBounds(650, 0, 150, 600);
        getContentPane().add(panel);
        panel.setLayout(null);
        
        cbTransformations = new JComboBox<String>();
        cbTransformations.setBounds(16, 226, 115, 27);
        
        operations = new HashMap<>();
        operations.put(0, "Translação");
        operations.put(1, "Rotação");
        operations.put(2, "Reflexão");
        operations.put(3, "Escala");
        operations.put(4, "Cisalhamento");
        
        for (Integer operation : operations.keySet()) {
			cbTransformations.addItem(operation + " " + operations.get(operation));
		}
        
        panel.add(cbTransformations);
        
        JLabel lblTransformation = new JLabel("Transformação");
        lblTransformation.setBounds(16, 210, 115, 16);
        panel.add(lblTransformation);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0); // exit program when touch in X button
            }
        });
        setVisible(true);     
        buttons();
        createLabels();
        createRadioButtons();
        createTextFields();
        
	}
	
	private void createLabels( ) {
		lblX = new JLabel("X");
        lblX.setBounds(16, 93, 15, 16);
        panel.add(lblX);
        
        lblY = new JLabel("Y");
        lblY.setBounds(16, 116, 15, 16);
        panel.add(lblY);
        
        lblAngulo = new JLabel("° ");
        lblAngulo.setBounds(16, 144, 15, 16);
        panel.add(lblAngulo); 
        
        lblX.setVisible(true);
        lblY.setVisible(true);
        lblAngulo.setVisible(true);
	}
	
	private void createRadioButtons() {
		rdbtnX = new JRadioButton("");
		rdbtnX.setBounds(80, 93, 30, 16);
        panel.add(rdbtnX);
        
        rdbtnY = new JRadioButton("");
        rdbtnY.setBounds(81, 116, 30, 16);
        panel.add(rdbtnY);	
	}
	
	private void buttons () {
		btnTriangulo = new JButton("Triangulo");
		btnTriangulo.setLocation(6, 30);
		btnTriangulo.setSize(138, 29);
        btnTriangulo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				createTriangulo();				
			}
		});
        btnTriangulo.setVisible(true);
        
        btnQuadrado = new JButton("Quadrado");
        btnQuadrado.setLocation(6, 57);
        btnQuadrado.setSize(138, 29);
        btnQuadrado.setVisible(true);
        
        btnMakeTransformation = new JButton("Aplicar");
        btnMakeTransformation.setBounds(16, 535, 117, 29);
        btnMakeTransformation.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				makeTransformation();				
			}
		});
              
        
        panel.add(btnTriangulo);
        panel.add(btnQuadrado);
        panel.add(btnMakeTransformation);
         
                
        showOperations = new TextArea();
        showOperations.setBounds(6, 259, 138, 150);
        panel.add(showOperations);
        showOperations.setBackground(Color.WHITE);
	        
	}
	
	private void createTextFields() {
		txtX = new JTextField();
        txtX.setBounds(35, 93, 40, 16);        
        txtX.setColumns(10);
        
        txtY = new JTextField();
        txtY.setColumns(10);
        txtY.setBounds(35, 116, 40, 16);
                
        txtAngulo = new JTextField();
        txtAngulo.setColumns(10);
        txtAngulo.setBounds(35, 144, 40, 16);
        
        
        panel.add(txtX);
        panel.add(txtY);
        panel.add(txtAngulo);
	}
	
	private void initGL(int width, int height) {
		profile = GLProfile.get(GLProfile.GL2);
		capabilities = new GLCapabilities(profile);	
		
		canvas = new GLCanvas(capabilities);
		canvas.setBackground(Color.BLACK);
		canvas.setBounds(0, 0, 650, 0);
        canvas.addGLEventListener(this);
                       
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
	
	private void makeTransformation() {
		//make transformation in the last object on polygons list
		Model m = polygons.get(polygons.size() - 1);
		
		if (rdbtnX.isSelected()) {
			
		}
		
	}
}
