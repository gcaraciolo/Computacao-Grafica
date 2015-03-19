package refactoring2;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.GLU;


/**
 * 
 * @author gcaraciolo
 *
 * the method draw is call by the display method.
 */

public abstract class Viewport implements GLEventListener, 
   								   MouseListener,
   						     MouseMotionListener
{
	
		private GLCanvas canvas;
		private MouseEvent mouse;
		private GLU glu;
		
		private int viewport[];
		private double modelview[];
		private double projection[];
		
		//size
		private int width;
		private int height;
		
		public GLCanvas getCanvas() {
			return canvas;
		}
			
		
		public Viewport(int width, int height) {
			canvas = new GLCanvas();
			glu = new GLU();
			
			
			viewport 	= new int[4];
			modelview 	= new double[16];
			projection  = new double[16];
						
			this.width = width;
			this.height = height;
			
			initListeners();
			
		}
		
		public Viewport (GLCanvas canvas, GLU glu, MouseEvent mouse, int width, int height) {
			this.canvas = canvas;
			this.glu = glu;		
			this.mouse = mouse;
			
			viewport 	= new int[4];
			modelview 	= new double[16];
			projection  = new double[16];
			
			this.width = width;
			this.height = height;
			
			initListeners();
		}

		
		private void initListeners() {
			//set listeners
			canvas.addGLEventListener(this);
			//canvas.addMouseListener(this); //add event mouse to this canvas.
		}
		
		
		@Override
		public void init(GLAutoDrawable drawable) {
			System.out.println("init viewport");
			GL2 gl = drawable.getGL().getGL2();
			
			try {
				checkVersionGL(gl);
			} catch(Exception e) {
				System.out.println(e);
			}
		
			if (glu == null) glu = new GLU();
		}
		
		@Override
		public void reshape(GLAutoDrawable drawable, 
										      int x, 
										      int y, 
										  int width,
										  int height) 
		{
			GL2 gl = drawable.getGL().getGL2();
			System.out.println("reshap viewport");
			
			
			gl.glClearColorIi(0, 0, 0, 0);
			gl.glGetIntegerv(GL.GL_VIEWPORT, viewport, 0); //coordinates of screen
			gl.glGetDoublev(GL2.GL_MODELVIEW_MATRIX, modelview, 0);
			gl.glGetDoublev(GL2.GL_PROJECTION_MATRIX, projection, 0);
			
			gl.glViewport(0, 0, width, height);
			gl.glMatrixMode(GL2.GL_PROJECTION);
			glu.gluOrtho2D(0.0, (float)width, 0.0, (float)height);		
			gl.glMatrixMode(GL2.GL_MODELVIEW);
		}
		
		@Override
		public void display(GLAutoDrawable drawable) {
			GL2 gl = drawable.getGL().getGL2();
			System.out.println("display viewport");
			
			draw(gl, glu);
			
			gl.glFlush();
		}

		public abstract void draw(GL2 gl, GLU glu);
		
		@Override
		public void dispose(GLAutoDrawable drawable) {
			GL2 gl = drawable.getGL().getGL2();
			System.out.println("dispose viewport");
			
		}	

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		
		public void checkVersionGL(GL2 gl) throws Exception {
			String versionStr = gl.glGetString( GL2.GL_VERSION );
			System.out.println( "GL version:"+versionStr ); 
			versionStr = versionStr.substring( 0, 4);
			float version = new Float( versionStr ).floatValue();
			boolean versionOK = ( version >= 1.59f ) ? true : false;
			System.out.println( "GL version:"+versionStr+"  ->"+versionOK ); 

			// Check if extension is available.
			boolean extensionOK = gl.isExtensionAvailable
					("GL_ARB_vertex_buffer_object");
			System.out.println( "VBO extension: "+extensionOK ); 

			// Check for VBO functions.
			boolean functionsOK = 
					gl.isFunctionAvailable("glGenBuffersARB") &&
					gl.isFunctionAvailable("glBindBufferARB") &&
					gl.isFunctionAvailable("glBufferDataARB") &&
					gl.isFunctionAvailable("glDeleteBuffersARB");      
			System.out.println( "Functions: "+ functionsOK); 

			if( ! extensionOK || ! functionsOK ) 
			{
				// VBO not supported.
				throw new Exception("VBOs not supported.");
			}
		}

}
