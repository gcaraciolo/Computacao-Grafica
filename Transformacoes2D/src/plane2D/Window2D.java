package plane2D;

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

import com.jogamp.opengl.util.FPSAnimator;


/**
 * 
 * @author Guilherme Caraciolo 201210799-5
 * @author Dimas Santos
 * @author Arthur Brito
 *
 */
public class Window2D extends JFrame implements GLEventListener						   
{
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
	private JLabel lblObject;
	private JRadioButton rbtnX;
	private JRadioButton rbtnY;
	private JComboBox<String> cbTransformations;
	private TextArea showOperations;

	//lista de valores de operacao
	private HashMap<Integer, String> operationsText;	
	//<tipo da operacao, x, y, angulo>	
	private ArrayList<Polygon> polygons;	
	private JComboBox<String> objects;
	
	


	public Window2D(String title, int width, int height) {
		setBackground(Color.YELLOW);
		this.title = title;
		this.glu = new GLU();
		polygons = new ArrayList<>();	
		objects  = new JComboBox<String>();
		objects.setBounds(6, 180, 138, 27);
		
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
		panel.add(objects);

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
//		createTextAreaShowOperations();
		createRadioButtons();

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

					rbtnX.setVisible(false);
					rbtnY.setVisible(false);
				} else if ( cbTransformations.getSelectedIndex() == 2) {					
					rbtnX.setVisible(true);
					rbtnY.setVisible(true);

					lblX.setVisible(true);
					txtX.setVisible(false);


					lblY.setVisible(true);
					txtY.setVisible(false);

					//hidden angulo
					lblAngulo.setVisible(false);
					txtAngulo.setVisible(false);

				} else {
					//hidden angulo
					lblAngulo.setVisible(false);
					txtAngulo.setVisible(false);

					//show others
					lblX.setVisible(true);
					txtX.setVisible(true);

					lblY.setVisible(true);
					txtY.setVisible(true);

					rbtnX.setVisible(false);
					rbtnY.setVisible(false);
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
		
		lblObject = new JLabel("Object");
		lblObject.setBounds(6, 160, 100, 20);
		lblObject.setVisible(true);
		panel.add(lblObject);

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
		btnQuadrado.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				createQuadrado();				
			}
		});

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
				addTrans();
			}
		});        

		panel.add(btnAdicionar);        
		panel.add(btnTriangulo);
		panel.add(btnQuadrado);
		panel.add(btnAplicar);

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

	private void createRadioButtons() {
		rbtnX = new JRadioButton();
		rbtnX.setBounds(85, 93, 115, 16);

		rbtnY = new JRadioButton();
		rbtnY.setBounds(85,116, 115, 16);

		rbtnX.setVisible(false);
		rbtnY.setVisible(false);


		panel.add(rbtnX);
		panel.add(rbtnY);
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

		for (Polygon polygon : polygons) {
			polygon.draw(gl, glu);
		}	

		gl.glFlush();

	}

	public void createQuadrado() {
		Quadrado q = new Quadrado(polygonNumber++);
		polygons.add(q);
		objects.addItem("Quadrado: " + q.getID());
		canvas.display();
	}

	public void createTriangulo() {
		Triangulo t = new Triangulo(polygonNumber++);
		polygons.add(t);
		objects.addItem("Triangulo: " + t.getID());
		canvas.display();
	}

	private void makeTransformation() {	
		canvas.display();					
	}

	private boolean hasX() {
		if (txtX.getText().equals(""))
			return false;
		return true;			
	}

	private boolean hasY() {
		if (txtY.getText().equals(""))
			return false;
		return true;			
	}

	private boolean hasTheta() {
		if (txtAngulo.getText().equals(""))
			return false;
		return true;
	}

	private void addTranslate() {
		try {
			Polygon p = polygons.get(objects.getSelectedIndex() + 1);			
			if (p instanceof Triangulo) {

				Triangulo t = (Triangulo) p;
				double tx = 0, ty = 0;

				// this check must be after the user put the data in the text field
				try {
					tx = Double.parseDouble(txtX.getText().toString());
					ty = Double.parseDouble(txtY.getText().toString());	
				}catch (Exception e){

				}finally {	

					if (hasX() && hasY())  t.translate(tx, ty);
					else if (hasX()) t.translate(tx, 0);
					else if (hasY()) t.translate(0, ty);
				}
			} else if (p instanceof Quadrado) {
				Quadrado q = (Quadrado) p;
				double tx = 0, ty = 0;

				// this check must be after the user put the data in the text field
				try {
					tx = Double.parseDouble(txtX.getText().toString());
					ty = Double.parseDouble(txtY.getText().toString());	
				}catch (Exception e){

				}finally {	

					if (hasX() && hasY())  q.translate(tx, ty);
					else if (hasX()) q.translate(tx, 0);
					else if (hasY()) q.translate(0, ty);
				}

			}
		} catch(Exception e) {
			// implementar exceptions
			// nao há objetos na tela
		}
	}

	private void addRotate() {
		try {
			Polygon p = polygons.get(objects.getSelectedIndex() + 1);	
			if (p instanceof Triangulo) {

				Triangulo t = (Triangulo) p;
				double theta = 0;

				// this check must be after the user put the data in the text field
				try {
					theta = Double.parseDouble(txtAngulo.getText().toString());	
				}catch (Exception e){

				}finally {	

					if (hasTheta()) t.rotate(new WCPt2D(0, 0), theta);
				}
			} else if (p instanceof Quadrado) {
				Quadrado q = (Quadrado) p;
				double theta = 0;

				// this check must be after the user put the data in the text field
				try {
					theta = Double.parseDouble(txtAngulo.getText().toString());		
				}catch (Exception e){

				}finally {	

					if (hasTheta()) q.rotate(new WCPt2D(0, 0), theta);
				}

			}
		} catch(Exception e) {
			// implementar exceptions
			// nao há objetos na tela
		}
	}

	private void addScale() {
		try {
			Polygon p = polygons.get(objects.getSelectedIndex() + 1);		
			if (p instanceof Triangulo) {

				Triangulo t = (Triangulo) p;
				double sx = 0, sy = 0;

				// this check must be after the user put the data in the text field
				try {
					sx = Double.parseDouble(txtX.getText().toString());
					sy = Double.parseDouble(txtY.getText().toString());	
				}catch (Exception e){

				}finally {	

					if (hasX() && hasY())  t.scale(new WCPt2D (0,0) ,sx, sy);
					else if (hasX()) t.scale(new WCPt2D (0,0) ,sx, 1);
					else if (hasY()) t.scale(new WCPt2D (0,0) ,1, sy);
				}
			} else if (p instanceof Quadrado) {
				Quadrado q = (Quadrado) p;
				double sx = 0, sy = 0;

				// this check must be after the user put the data in the text field
				try {
					sx = Double.parseDouble(txtX.getText().toString());
					sy = Double.parseDouble(txtY.getText().toString());		
				}catch (Exception e){

				}finally {	
					if (hasX() && hasY())  q.scale(new WCPt2D (0,0) ,sx, sy);
					else if (hasX()) q.scale(new WCPt2D (0,0) ,sx, 1);
					else if (hasY()) q.scale(new WCPt2D (0,0) ,1, sy);
				}

			}
		} catch(Exception e) {
			// implementar exceptions
			// nao há objetos na tela
		}
	}

	private void addReflexion() {
		try {
			Polygon p = polygons.get(objects.getSelectedIndex() + 1);		
			// if the radio buttons is selected, the going to return true. Otherwise false;
			if (p instanceof Triangulo) {
				Triangulo t = (Triangulo) p;				
				t.reflexion(rbtnX.isSelected(), rbtnY.isSelected());
			} else if (p instanceof Quadrado) {
				Quadrado q = (Quadrado) p;
				q.reflexion(rbtnX.isSelected(), rbtnY.isSelected());				
			}
		} catch(Exception e) {
			// implementar exceptions
			// nao há objetos na tela
		}
	}

	private void addShear() {
		try {
			Polygon p = polygons.get(objects.getSelectedIndex() + 1);
			if (p instanceof Triangulo) {
				Triangulo t = (Triangulo) p;
				double a = 0, b = 0;

				// this check must be after the user put the data in the text field
				try {
					if (hasX())
						a = Double.parseDouble(txtX.getText().toString());
					if (hasY())
						b = Double.parseDouble(txtY.getText().toString());	
				}catch (Exception e){

				}finally {	

					if (hasX() && hasY())  t.shear(a, b);
					else if (hasX()) t.shear(a, 0);
					else if (hasY()) t.shear(0, b);
				}
			} else if (p instanceof Quadrado) {
				Quadrado q = (Quadrado) p;
				double a = 0, b = 0;

				// this check must be after the user put the data in the text field
				try {
					if (hasX())
						a = Double.parseDouble(txtX.getText().toString());
					if (hasY())
						b = Double.parseDouble(txtY.getText().toString());			
				}catch (Exception e){

				}finally {	
					if (hasX() && hasY())  q.shear(a, b);
					else if (hasX()) q.shear(a, 0);
					else if (hasY()) q.shear(0, b);
				}
				
			}
		} catch(Exception e) {
			// implementar exceptions
			// nao há objetos na tela
		}
	}

	private void addTrans() {
		Integer transformationType = cbTransformations.getSelectedIndex();
		switch (transformationType) {
		case 0: //translate
			addTranslate();
			break;
		case 1: //rotate
			addRotate();
			break;
		case 2: //reflexion
			addReflexion();
			break;
		case 3: //scale
			addScale();
			break;
		case 4: //shar -> TODO
			addShear();
			break;
		}

	}

}




