package sacm.cs.ou.edu;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import javax.microedition.khronos.opengles.GL10;

public class Cube 
{
	private Quaternion mRotation = Quaternion.fromAxis(new Vector3D(1f,1f,1f), 0);

	public Quaternion currLoc = new Quaternion(Float.MAX_VALUE, 0f, 0f, 0f);
	public Quaternion rotLoc = new Quaternion(0f, 0f, 0f, 0f);
	private boolean rotating = false;

	private FloatBuffer vertexBuffer;
	private FloatBuffer colorBuffer;
	private ByteBuffer indexBuffer;
	private boolean okToDraw = false;

	protected static float verticies[]= {
		// FRONT
		-1.0f, -1.0f,  1.0f,  // 0. left-bottom-front
		1.0f, -1.0f,  1.0f,  // 1. right-bottom-front
		-1.0f,  1.0f,  1.0f,  // 2. left-top-front
		1.0f,  1.0f,  1.0f,  // 3. right-top-front

		// RIGHT
		1.0f, -1.0f,  1.0f,  // 1. right-bottom-front
		1.0f, -1.0f, -1.0f,  // 6. right-bottom-back
		1.0f,  1.0f,  1.0f,  // 3. right-top-front
		1.0f,  1.0f, -1.0f,  // 7. right-top-back

		// TOP
		-1.0f,  1.0f,  1.0f,  // 2. left-top-front
		1.0f,  1.0f,  1.0f,  // 3. right-top-front
		-1.0f,  1.0f, -1.0f,  // 5. left-top-back
		1.0f,  1.0f, -1.0f,  // 7. right-top-back

		// BACK
		-1.0f, -1.0f, -1.0f,  // 4. left-bottom-back
		1.0f, -1.0f, -1.0f,  // 6. right-bottom-back
		-1.0f,  1.0f, -1.0f,  // 5. left-top-back
		1.0f,  1.0f, -1.0f,  // 7. right-top-back

		// LEFT
		-1.0f, -1.0f, -1.0f,  // 4. left-bottom-back
		-1.0f, -1.0f,  1.0f,  // 0. left-bottom-front 
		-1.0f,  1.0f, -1.0f,  // 5. left-top-back
		-1.0f,  1.0f,  1.0f,  // 2. left-top-front


		// BOTTOM
		-1.0f, -1.0f, -1.0f,  // 4. left-bottom-back
		1.0f, -1.0f, -1.0f,  // 6. right-bottom-back
		-1.0f, -1.0f,  1.0f,  // 0. left-bottom-front
		1.0f, -1.0f,  1.0f   // 1. right-bottom-front
	};

	/** The colors for each vertex of the cube.
	 *  4 values per vertex * 4 vertices per face * 6 faces = 96
	 *  Color: [red][green][blue][alpha]
	 */
	public float colours[] = new float[96];

	protected static byte indicies[] = 
		{
		0, 1, 2, 2, 3, 1,//Face1
		4, 5, 6, 5, 6, 7,//Face2
		8, 9, 10, 9, 10, 11,//Face3
		12, 13, 14, 13, 14, 15,//Face4
		16, 17, 18, 17, 18, 19,//Face5
		20, 21, 22, 21, 22, 23//Face6
		};

	public Cube()
	{
		int i = 0;
		// Initialize all 6 sides to black
		for (int f = 0; f < 6; f++)
			for (int v = 0; v < 4; v++)
				{
					colours[i] = .3f; i++;
					colours[i] = .3f; i++;
					colours[i] = .3f; i++;
					colours[i] = 1f; i++;
				}
	}

	/** Called once all of the colors have been assigned */
	public void finalizeCube ()
	{
		ByteBuffer byteBuf =  ByteBuffer.allocateDirect(verticies.length*4);
		byteBuf.order(ByteOrder.nativeOrder());
		vertexBuffer = byteBuf.asFloatBuffer();
		vertexBuffer.put(verticies);
		vertexBuffer.position(0);

		byteBuf = ByteBuffer.allocateDirect(colours.length * 4);
		byteBuf.order(ByteOrder.nativeOrder());
		colorBuffer = byteBuf.asFloatBuffer();
		colorBuffer.put(colours);
		colorBuffer.position(0);

		indexBuffer = ByteBuffer.allocateDirect(indicies.length);
		indexBuffer.put(indicies);
		indexBuffer.position(0);

		okToDraw = true;
	}

	/**
	 * Sets an entire face of the cube to a specific color.
	 * @param face The face to paint
	 * @param color The color to set the face as
	 */
	public void setColor (Face face, Colors color)
	{
		int index = -1;

		// Find the initial index within the color array
		// Each face contains 16 values: 4 for each color * 4 vertices for each face
		switch (face)
		{
		case FRONT: 	index = 16*0; break;
		case RIGHT: 	index = 16*1; break;
		case TOP: 		index = 16*2; break;
		case BACK: 		index = 16*3; break;
		case LEFT: 		index = 16*4; break;
		case BOTTOM: 	index = 16*5; break;
		}

		for (int i = index; i < index+16; i+=4)
		{
			switch (color)
			{
			case BLUE:
				for (int j=0; j<4;j++){
					colours[i] = 0f;			i++;
					colours[i] = 0f;			i++;
					colours[i] = 1f;			i++;
					colours[i] = 1f;			i++;
				}
				break;
			case RED:
				for (int j=0; j<4;j++){
					colours[i] = 1f;			i++;
					colours[i] = 0f;			i++;
					colours[i] = 0f;			i++;
					colours[i] = 1f;			i++;
				}
				break;
			case GREEN:
				for (int j=0; j<4;j++){
					colours[i] = 0f;			i++;
					colours[i] = 1f;			i++;
					colours[i] = 0f;			i++;
					colours[i] = 1f;			i++;
				}
				break;
			case PURPLE:
				for (int j=0; j<4;j++){
					colours[i] = 1f;			i++;
					colours[i]= .5f;			i++;
					colours[i] = 0f;			i++;
					colours[i] = 1f;			i++;
				}
				break;
			case WHITE:
				for (int j=0; j<4;j++){
					colours[i] = 1f;			i++;
					colours[i] = 1f;			i++;
					colours[i] = 1f;			i++;
					colours[i] = 1f;			i++;
				}
				break;
			case YELLOW:
				for (int j=0; j<4;j++){
					colours[i] = 1f;			i++;
					colours[i]= 1f;				i++;
					colours[i] = 0f;			i++;
					colours[i] = 1f;			i++;
				}
				break;
			}
		}
	}

	/**
	 * Draws this individual cube on the canvas, based on it's position in the Rubiks Cube
	 * @param gl The OpenGL handle for rendering 
	 * @param mLocation The (x,y,z) location to be drawn at
	 */
	public void draw(GL10 gl, Vector3D mLocation) 
	{
		if (okToDraw)
		{

			if(rotating){
				rotLoc.multiplyQuaternion(new Quaternion(.5f,.5f,.5f,0f));
				gl.glTranslatef(rotLoc.x,rotLoc.y,rotLoc.z);
				gl.glRotatef(mRotation.getAngle()*180f/(float)Math.PI, mRotation.getX(),mRotation.getY(),mRotation.getZ());
				//			gl.glRotatef(270,0,0,1);

				gl.glFrontFace(GL10.GL_CW);
				gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
				gl.glColorPointer(4, GL10.GL_FLOAT, 0, colorBuffer);


				gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
				gl.glEnableClientState(GL10.GL_COLOR_ARRAY);

				gl.glDrawElements(GL10.GL_TRIANGLES, indicies.length, GL10.GL_UNSIGNED_BYTE, 
						indexBuffer);

				gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
				gl.glDisableClientState(GL10.GL_COLOR_ARRAY);

				gl.glRotatef(-mRotation.getAngle()*180f/(float)Math.PI, mRotation.getX(),mRotation.getY(),mRotation.getZ());
				gl.glTranslatef(-rotLoc.x,-rotLoc.y,-rotLoc.z);



				rotating = false;
				return;

			}

			//		if(currLoc.x==Float.MAX_VALUE)
			//		{
			//			currLoc = new Quaternion(x,y,z, 0f);
			//			rotLoc = new Quaternion(x,y,z, 0f);
			//		}

			//		gl.glTranslatef(currLoc.x,currLoc.y,currLoc.z); quaternions are for rotations because they deal with angles. for translation we can just store x/y/z values as a coordinate
			gl.glTranslatef(mLocation.x,mLocation.y,mLocation.z);
			gl.glRotatef(mRotation.getAngle()*180f/(float)Math.PI, mRotation.getX(),mRotation.getY(),mRotation.getZ());

			gl.glFrontFace(GL10.GL_CW);
			gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
			gl.glColorPointer(4, GL10.GL_FLOAT, 0, colorBuffer);


			gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
			gl.glEnableClientState(GL10.GL_COLOR_ARRAY);

			gl.glDrawElements(GL10.GL_TRIANGLES, indicies.length, GL10.GL_UNSIGNED_BYTE, indexBuffer);

			gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
			gl.glDisableClientState(GL10.GL_COLOR_ARRAY);

			gl.glRotatef(-mRotation.getAngle()*180f/(float)Math.PI, mRotation.getX(),mRotation.getY(),mRotation.getZ()); // Resets the current rotation
			gl.glTranslatef(-mLocation.x,-mLocation.y,-mLocation.z); // Resets the current translation
			//		gl.glTranslatef(-currLoc.x,-currLoc.y,-currLoc.z);
		}
	}


	public void setRotation(Quaternion rotate)
	{
		mRotation = mRotation.multiplyQuaternion(rotate,mRotation);
	}


}
