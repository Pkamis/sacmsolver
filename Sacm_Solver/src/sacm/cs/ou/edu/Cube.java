package sacm.cs.ou.edu;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import javax.microedition.khronos.opengles.GL10;

public class Cube 
{
	float [] translate = {0f,0f,0f};

	private Quaternion mRotation = Quaternion.fromAxis(new Vector3D(1f,1f,1f), 0);

	public Quaternion currLoc = new Quaternion(Float.MAX_VALUE, 0f, 0f, 0f);
	public Quaternion rotLoc = new Quaternion(0f, 0f, 0f, 0f);
	public boolean rotating = false;

	private FloatBuffer vertexBuffer;
	private FloatBuffer colorBuffer;
	private ByteBuffer indexBuffer;

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

	public float colours[];

	protected static byte indicies[] = {
		0, 1, 2, 2, 3, 1,//Face1
		4, 5, 6, 5, 6, 7,//Face2
		8, 9, 10, 9, 10, 11,//Face3
		12, 13, 14, 13, 14, 15,//Face4
		16, 17, 18, 17, 18, 19,//Face5
		20, 21, 22, 21, 22, 23//Face6
	};

	public Cube(Colors[] color){
		colours = setColor(color);

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


	}
	
	private float[] setColor(Colors[] color) {
		float[] returnColors = new float[96];
		int i = 0;
		for(Colors col: color){
			switch (col){

			case BLUE:
				for (int j=0; j<4;j++){
					returnColors[i] = 0f;					i++;
					returnColors[i]= 0f;					i++;
					returnColors[i] = 1f;					i++;
					returnColors[i] = 1f;					i++;

				}
				break;


			case RED:
				for (int j=0; j<4;j++){
					returnColors[i] = 1f;					i++;
					returnColors[i]= 0f;					i++;
					returnColors[i] = 0f;					i++;
					returnColors[i] = 1f;					i++;

				}
				break;

			case GREEN:
				for (int j=0; j<4;j++){
					returnColors[i] = 0f;					i++;
					returnColors[i]= 1f;					i++;
					returnColors[i] = 0f;					i++;
					returnColors[i] = 1f;					i++;

				}
				break;

			case ORANGE:
				for (int j=0; j<4;j++){
					returnColors[i] = 1f;					i++;
					returnColors[i]= .5f;					i++;
					returnColors[i] = 0f;					i++;
					returnColors[i] = 1f;					i++;

				}
				break;

			case WHITE:
				for (int j=0; j<4;j++){
					returnColors[i] = 1f;					i++;
					returnColors[i]= 1f;					i++;
					returnColors[i] = 1f;					i++;
					returnColors[i] = 1f;					i++;

				}
				break;

			case YELLOW:
				for (int j=0; j<4;j++){
					returnColors[i] = 1f;					i++;
					returnColors[i]= 1f;					i++;
					returnColors[i] = 0f;					i++;
					returnColors[i] = 1f;					i++;

				}
				break;

			case BLACK:
				for (int j=0; j<4;j++){
					returnColors[i] = 1f;					i++;
					returnColors[i]= 0f;					i++;
					returnColors[i] = 1f;					i++;
					returnColors[i] = 1f;					i++;

				}
				break;

			}
		}
		return returnColors;
	}
	public void draw(GL10 gl, float x, float y, float z) {
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
		
		if(currLoc.x==Float.MAX_VALUE)
		{
			currLoc = new Quaternion(x,y,z, 0f);
			rotLoc = new Quaternion(x,y,z, 0f);
		}

		gl.glTranslatef(currLoc.x,currLoc.y,currLoc.z);
		gl.glRotatef(mRotation.getAngle()*180f/(float)Math.PI, mRotation.getX(),mRotation.getY(),mRotation.getZ());
//		gl.glRotatef(270,0,0,1);
		
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
		gl.glTranslatef(-currLoc.x,-currLoc.y,-currLoc.z);
	}


	public void setRotation(Quaternion rotate)
	{
		mRotation = mRotation.multiplyQuaternion(rotate,mRotation);
	}


}
