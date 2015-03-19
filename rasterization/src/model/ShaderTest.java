package model;

import java.awt.event.MouseEvent;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GL2ES2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.GLU;

import refactoring2.Viewport;

public class ShaderTest extends Viewport implements Runnable  {
	
	
	public ShaderTest(GLCanvas canvas, GLU glu, MouseEvent mouse, int width,
			int height) {
		super(canvas, glu, mouse, width, height);
		// TODO Auto-generated constructor stub
	}

	private int program;
	IntBuffer vertexArray = IntBuffer.allocate(1);
	
	
	
	private IntBuffer buffers = IntBuffer.allocate(2);
	private float[] square = {
	        -1.0f, -1.0f,
	        1.0f, -1.0f,
	        1.0f, 1.0f,
	        1.0f, 1.0f,
	        -1.0f, 1.0f,
	        -1.0f, -1.0f,
	};

	private float[] colorData = {
	        255, 0, 0,
	        255, 255, 0,
	        0, 255, 0,
	        0, 255, 0,
	        0, 0, 255,
	        255, 0, 0
	};

	FloatBuffer vertexFB = FloatBuffer.wrap(square);
	FloatBuffer colorFB = FloatBuffer.wrap(colorData);
	

	@Override
	public void display(GLAutoDrawable drawable) {	
		GL2 gl = drawable.getGL().getGL2();
		gl.glClear(GL2.GL_DEPTH_BUFFER_BIT | GL2.GL_COLOR_BUFFER_BIT);

	    gl.glUseProgram(program);
	    gl.glBindVertexArray(vertexArray.get(0));
	    gl.glDrawArrays(GL.GL_TRIANGLES, 0, 6);
		
	}

	@Override
	public void dispose(GLAutoDrawable arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();

		/**/
		program = gl.glCreateProgram();

		// Create vertexShader.
		int vertexShader = gl.glCreateShader(GL2ES2.GL_VERTEX_SHADER);
		String[] vertexShaderSource = new String[1];
		vertexShaderSource[0] = "#version 330\n" +
		    "layout(location=0) in vec2 position;\n" +
		    "layout(location=1) in vec3 color;\n" +
		    "out vec3 vColor;\n" +
		    "void main(void)\n" +
		    "{\n" +
		    "gl_Position = vec4(position, 0.0, 1.0);\n" +
		    "vColor = vec4(color, 1.0);\n" +
		    "}\n";
		gl.glShaderSource(vertexShader, 1, vertexShaderSource, null);
		gl.glCompileShader(vertexShader);

		// Create and fragment shader.
		int fragmentShader = gl.glCreateShader(GL2ES2.GL_FRAGMENT_SHADER);
		String[] fragmentShaderSource = new String[1];
		fragmentShaderSource[0] = "#version 330\n" +
		    "in vec4 vColor;\n" +
		    "out vec4 fColor;\n" +
		    "void main(void)\n" +
		    "{\n" +
		    "fColor = vColor;\n" +
		    "}\n";
		gl.glShaderSource(fragmentShader, 1, fragmentShaderSource, null);
		gl.glCompileShader(fragmentShader);

		// Attach shaders to program.
		gl.glAttachShader(program, vertexShader);
		gl.glAttachShader(program, fragmentShader);
		gl.glLinkProgram(program);
		
		
		// Create Vertex Array.
		gl.glGenVertexArrays(1, vertexArray);
		gl.glBindVertexArray(vertexArray.get(0));

		// Specify how data should be sent to the Program.

		// VertexAttribArray 0 corresponds with location 0 in the vertex shader.
		gl.glEnableVertexAttribArray(0);
		gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, buffers.get(0));
		gl.glVertexAttribPointer(0, 2, GL.GL_FLOAT, false, 0, 0);

		// VertexAttribArray 1 corresponds with location 1 in the vertex shader.
		gl.glEnableVertexAttribArray(1);
		gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, buffers.get(1));
		gl.glVertexAttribPointer(1, 3, GL.GL_FLOAT, false, 0, 0);
		
		/**/
		
	    gl.glEnableClientState(GL2.GL_VERTEX_ARRAY);


	    gl.glEnable(GL2.GL_DEPTH_TEST);
	    gl.glClearDepthf(10.0f);
	    gl.glClearColor(0.8f, 0.6f, 0.8f, 1.0f);
	    gl.glDepthFunc(GL2.GL_LEQUAL);

	    gl.glGenBuffers(2, buffers);

	    gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, buffers.get(0));
	    gl.glBufferData(GL2.GL_ARRAY_BUFFER, 4 * 6 * 2, vertexFB, GL2.GL_STATIC_DRAW);


	    gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, buffers.get(1));
	    gl.glBufferData(GL2.GL_ARRAY_BUFFER, 4 * 6 * 3, colorFB, GL2.GL_STREAM_DRAW);
	    
	    
	 // Create Vertex Array.
	    gl.glGenVertexArrays(1, vertexArray);
	    gl.glBindVertexArray(vertexArray.get(0));

	    // Specify how data should be sent to the Program.

	    // VertexAttribArray 0 corresponds with location 0 in the vertex shader.
	    gl.glEnableVertexAttribArray(0);
	    gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, buffers.get(0));
	    gl.glVertexAttribPointer(0, 2, GL.GL_FLOAT, false, 0, 0);

	    // VertexAttribArray 1 corresponds with location 1 in the vertex shader.
	    gl.glEnableVertexAttribArray(1);
	    gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, buffers.get(1));
	    gl.glVertexAttribPointer(1, 3, GL.GL_FLOAT, false, 0, 0);
		
	}

	@Override
	public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3,
			int arg4) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(GL2 gl, GLU glu) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("aslosd");
	}
}
