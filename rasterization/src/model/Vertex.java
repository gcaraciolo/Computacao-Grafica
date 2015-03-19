package model;
//
//public class Vertex {
//
//	private Coordinate coordinate;
//	private Color color;
//	
//	public Vertex () { 		}
//	
//	public Vertex (Coordinate coordinate, Color color) { 	
//		this.coordinate = coordinate;
//		this.color = color;
//	}
//	
//	public Coordinate getCoordinate() { return this.coordinate; }
//	public Color getColor() { return this.color; }
//}
import java.nio.FloatBuffer;
import java.util.Random;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
// GLUT, FPSAnimator

import com.jogamp.common.nio.Buffers;

public class Vertex {

	//////////////// Constants /////////////////////////

	// Number of Points in the array.
	final int nbPoints = 20000;

	//////////////// Variables /////////////////////////

	// We use a buffer and an array for the vertex data
	// to more compatible with C.
	FloatBuffer points;
	float[] pointsData;
	FloatBuffer colors;
	float[] colorsData;

	Random random = new Random();

	public Vertex(GL2 gl) {
		gl.glEnableClientState( GL2.GL_VERTEX_ARRAY );
		gl.glEnableClientState( GL2.GL_COLOR_ARRAY );
		//gl.glEnableClientState( GL.GL_NORMAL_ARRAY );

		initArrayData( gl );
	}

	///////////////// create data ///////////////////////
	private void initArrayData( GL2 gl )
	{
		// Create data points on the surface of a cube.
		int nbValues = nbPoints * 3;
		pointsData = new float[ nbValues ];
		colorsData = new float[ nbValues ];
		for( int i=0; i < nbPoints; i++ )
		{
			pointsData[ i ] = (float)Math.random();
			pointsData[ i+1 ] = (float)Math.random();
			pointsData[ i+2 ] = (float)Math.random();
			// Cast on random side surface.
			int sel = random.nextInt( 3 );
			pointsData [ i + sel ] = random.nextInt( 2 );
			//
			colorsData[ i ] = (float)Math.random();
			colorsData[ i+1 ] = (float)Math.random();
			colorsData[ i+2 ] = (float)Math.random();
			colorsData [ i + sel ] = 1f;
		}
		// Points.
		points = Buffers.newDirectFloatBuffer( nbValues ); //BufferUtil.newFloatBuffer( nbValues );
		points.put( pointsData, 0, nbValues );
		points.rewind();
		gl.glVertexPointer( 3, GL.GL_FLOAT, 0, points );
		// Colors.
		colors = Buffers.newDirectFloatBuffer( nbValues ); //BufferUtil.newFloatBuffer( nbValues );
		colors.put( colorsData, 0, nbValues );
		colors.rewind();
		gl.glColorPointer( 3, GL.GL_FLOAT, 0, colors );
	}

	//////////////////////// draw /////////////////////////

	public void draw( GL2 gl )
	{
		gl.glColor3f( 0f, 1f, 0f ); 
        gl.glBegin( GL.GL_POINTS ); 
		for( int i=0; i < nbPoints; i++ ) 
		{
			gl.glVertex3fv( pointsData, i*3 );
		    gl.glVertex3f( points.get( i*3 ),
		 			 points.get( i*3 +1),
		 			 points.get( i*3 +2) );
	
		 	  gl.glArrayElement( i );
		}      
		
		gl.glEnd();
		gl.glColor3f( 1f, 0f, 0f ); 
		gl.glDrawArrays( GL.GL_POINTS, 0, nbPoints );
	}

}
