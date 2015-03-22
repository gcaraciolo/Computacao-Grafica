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
import java.util.List;

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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.CartesianPlane2D;
import model.Model;
import model.Point2D;
import model.Triangulo;

import com.jogamp.opengl.util.FPSAnimator;

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
	private int polygonNumber;
	private JPanel menu;
	private JButton btnTriangulo;
	private JButton btnQuadrado;
	private JList list;

	private JPanel panel;
	private JTextField txtX;
	private JTextField txtY;
	private JTextField txtAngulo;
	private JButton btnAplicar;
	private JButton btnAdicionar;
	private JLabel lblX;    
	private JLabel lblY;    
	private JLabel lblAngulo;
	private JComboBox<String> cbTransformations;
	private TextArea showOperations;

	//lista de valores de operacao
	private HashMap<Integer, String> operationsText;
	
	//<tipo da operacao, x, y, angulo>
	
	private List<Operation> operations;

	public Window2D(String title, int width, int height) {
		setBackground(Color.YELLOW);
		this.title = title;
		this.glu = new GLU();
		this.polygons = new ArrayList<>();
		
		operations = new ArrayList<>();

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
		polygons.add(new CartesianPlane2D(0, 400, 400));

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
		cbTransformations.setBounds(6, 226, 138, 27);

		operationsText = new HashMap<>();
		operationsText.put(0, "Translação");
		operationsText.put(1, "Rotação");
		operationsText.put(2, "Reflexão");
		operationsText.put(3, "Escala");
		operationsText.put(4, "Cisalhamento");

		for (Integer operation : operationsText.keySet()) {
			cbTransformations.addItem(operation + " " + operationsText.get(operation));
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
		createButtons();
		createLabels();
		createTextFields();
		createTextAreaShowOperations();

		//hidden angulo
		lblAngulo.setVisible(false);
		txtAngulo.setVisible(false);        

		cbTransformations.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				if ( cbTransformations.getSelectedIndex() == 1) { //angulo 
					lblAngulo.setVisible(true);
					txtAngulo.setVisible(true);
									
					lblX.setVisible(false);
					txtX.setVisible(false);
					
					
					lblY.setVisible(false);
					txtY.setVisible(false);
				} else {
					//hidden angulo
					lblAngulo.setVisible(false);
					txtAngulo.setVisible(false);
					
					//show others
					lblX.setVisible(true);
					txtX.setVisible(true);
					
					lblY.setVisible(true);
					txtY.setVisible(true);
				}
			}
		});

	}

	private void createLabels( ) {
		lblX = new JLabel("X");
		lblX.setBounds(16, 93, 15, 16);
		lblX.setVisible(true);
		panel.add(lblX);

		lblY = new JLabel("Y");
		lblY.setBounds(16, 116, 15, 16);
		lblY.setVisible(true);
		panel.add(lblY);

		lblAngulo = new JLabel("° ");
		lblAngulo.setBounds(16, 144, 15, 16);
		lblAngulo.setVisible(false);
		panel.add(lblAngulo); 

	}

	private void createButtons () {
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

		btnAplicar = new JButton("Aplicar");
		btnAplicar.setBounds(16, 535, 117, 29);
		btnAplicar.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				makeTransformation();				
			}
		});

		btnAdicionar = new JButton("Adicionar");
		btnAdicionar.setBounds(6, 253, 138, 29);
		btnAdicionar.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				addTransformation();
			}
		});        

		panel.add(btnAdicionar);        
		panel.add(btnTriangulo);
		panel.add(btnQuadrado);
		panel.add(btnAplicar);

	}

	private void createTextAreaShowOperations() {
		showOperations = new TextArea();
		showOperations.setBounds(6, 304, 138, 150);
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
		canvas.setBounds(0, 0, 650, 600);
		canvas.addGLEventListener(this);

		animator = new FPSAnimator(canvas, FPS);
	}

	private void configViewport(GL2 gl, GLU glu, int width, int height) {

		int lastLeftX, lastRightX;
		int lastBottomY, lastTopY;
		
		lastRightX = width / 2;
		lastLeftX = - lastRightX;
		
		lastTopY = height / 2;
		lastBottomY = - lastTopY;
		
		int[] viewport = new int[4];
		double[] modelview = new double[16];
		double[] projection = new double[16];

		gl.glClearColorIi(1, 0, 0, 0);
		gl.glGetIntegerv(GL.GL_VIEWPORT, viewport, 0); //coordinates of screen
		gl.glGetDoublev(GL2.GL_MODELVIEW_MATRIX, modelview, 0);
		gl.glGetDoublev(GL2.GL_PROJECTION_MATRIX, projection, 0);

		gl.glViewport(0, 0, width, height);
		gl.glMatrixMode(GL2.GL_PROJECTION);		
		glu.gluOrtho2D(lastLeftX, (float)lastRightX, lastBottomY, (float)lastTopY); // coordinates.
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

	public void createTriangulo() {
		int x = 200, y = 100;
		//Triangulo t = new Triangulo(new Point2D(x, y + 150), new Point2D(x + 100, y + 150), new Point2D(x + 50, y + 250), polygonNumber++);
		Triangulo t = new Triangulo(polygonNumber++);
		polygons.add(t);
		x += 100;
		canvas.display();

	}

	private void makeTransformation() {
		//make transformation in the last object on polygons list
		if (polygons.size() == 0) return;
		Model m = polygons.get(polygons.size() - 1);
		m.setOperations(operations);		
		canvas.display();
		operations = new ArrayList<Operation>();
		showOperations.setText(""); // clean operationsText
	}

	private void addTransformation() {
		
		boolean angulo = false, reflexao = false;
		StringBuilder transformation = new StringBuilder();
		Integer transformationSelected = cbTransformations.getSelectedIndex();
		Operation operation = new Operation(transformationSelected);

		if (transformationSelected == 1){
			if (txtAngulo.getText().equals(""))
				return;
			else
				angulo = true;
		} else if (transformationSelected == 2) {
			reflexao = true;
		} else if (txtX.getText().equals("") && txtY.getText().equals("")) {
			return;
		}
		
		transformation.append(operationsText.get(transformationSelected).substring(0, 2) + ": (" ); 
		
		if (angulo) {	
			
			
			if (!txtAngulo.getText().equals("")) transformation.append("* = " + txtAngulo.getText());
	
			transformation.append(")");
	
			showOperations.setText(showOperations.getText() +  transformation.toString() + "\n");
			
		} else if (reflexao) {
			
			operations.add(operation);
			
		} else {
			
			
			if (!txtX.getText().equals("")) { 
				transformation.append("x = " + txtX.getText());
				try {
					operation.setX(Float.parseFloat(txtX.getText().toString()));
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "numero no X");
				}
				if (!txtY.getText().equals("")) transformation.append(", ");
			} else {
				operation.setX(0f);
			}
			if (!txtY.getText().equals("")) {
				transformation.append("y = " + txtY.getText());
				try {
					operation.setY(Float.parseFloat(txtY.getText().toString()));
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "numero no Y");
				}
			} else {
				operation.setY(0f);
			}
			
						
			operations.add(operation);
	
			transformation.append(")");
	
			showOperations.setText(showOperations.getText() +  transformation.toString() + "\n");
		}
	}

}
