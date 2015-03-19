package model;

import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.awt.GLCanvas;
import javax.swing.JFrame;

import com.jogamp.common.nio.Buffers;


public class VBOTest implements GLEventListener {
	
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        GLCanvas canvas = new GLCanvas();
        canvas.addGLEventListener(new VBOTest());
        frame.add(canvas);
        frame.setSize(640, 480);
        frame.setVisible(true);
    }

    private FloatBuffer vertices;
    private ShortBuffer indices;
    private int VBOVertices;
    private int VBOIndices;

    public void init(GLAutoDrawable drawable) {
        float[] vertexArray = {-0.2f,  0.5f, 0,
                                0.5f,  0.5f, 0,
                                0.5f, -0.5f, 0,
                               -0.5f, -0.5f, 0};
        vertices = Buffers.newDirectFloatBuffer(vertexArray.length);
        vertices.put(vertexArray);
        vertices.flip();

        short[] indexArray = {0, 1, 2, 0, 2, 3};
        indices = Buffers.newDirectShortBuffer(indexArray.length);
        indices.put(indexArray);
        indices.flip();

        GL2 gl = drawable.getGL().getGL2();
        int[] temp = new int[2];
        gl.glGenBuffers(2, temp, 0);

        VBOVertices = temp[0];
        gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, VBOVertices);
        gl.glBufferData(GL2.GL_ARRAY_BUFFER, vertices.capacity() * Buffers.SIZEOF_FLOAT,
                            vertices, GL2.GL_STATIC_DRAW);
        gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, 0);

        VBOIndices = temp[1];
        gl.glBindBuffer(GL2.GL_ELEMENT_ARRAY_BUFFER, VBOIndices);
        gl.glBufferData(GL2.GL_ELEMENT_ARRAY_BUFFER, indices.capacity() * Buffers.SIZEOF_SHORT,
                            indices, GL2.GL_STATIC_DRAW);
        gl.glBindBuffer(GL2.GL_ELEMENT_ARRAY_BUFFER, 0);
    }

    public void display(GLAutoDrawable drawable) {
    	GL2 gl = drawable.getGL().getGL2();

        gl.glEnableClientState(GL2.GL_VERTEX_ARRAY);

        gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, VBOVertices);
        gl.glVertexPointer(3, GL2.GL_FLOAT, 0, 0);
        gl.glBindBuffer(GL2.GL_ELEMENT_ARRAY_BUFFER, VBOIndices);
        gl.glDrawElements(GL2.GL_TRIANGLES, indices.capacity(), GL2.GL_UNSIGNED_SHORT, 0);

        gl.glDisableClientState(GL2.GL_VERTEX_ARRAY);
    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {}

	@Override
	public void dispose(GLAutoDrawable arg0) {
		// TODO Auto-generated method stub
		
	}
}
