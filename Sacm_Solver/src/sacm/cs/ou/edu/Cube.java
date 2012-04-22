package sacm.cs.ou.edu;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

import android.util.Log;

public class Cube {
	float [] translate = {0f,0f,0f};

	private float CubeRotation;

	public float[] current_Rotation = {0f, 0f, 0f};


	private FloatBuffer vertexBuffer;
	public FloatBuffer colorBuffer;
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
		CubeRotation = 0f;
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
					returnColors[i] = .1f;					i++;
					returnColors[i]= .1f;					i++;
					returnColors[i] = .1f;					i++;
					returnColors[i] = 1f;					i++;

				}
				break;

			}
		}
		return returnColors;
	}
	public void draw(GL10 gl, float x, float y, float z) {
		//TODO params for different base vertex position

		//        gl.glFrontFace(GL10.GL_CW);
				//        
				//        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
		//        gl.glColorPointer(4, GL10.GL_FLOAT, 0, colorBuffer);
		//        
		//        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		//        gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
		//         
		//        gl.glDrawElements(GL10.GL_TRIANGLES, indicies.length, GL10.GL_UNSIGNED_BYTE, 
		//                        indexBuffer);
		//            
		//        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		//        gl.glDisableClientState(GL10.GL_COLOR_ARRAY);

		gl.glTranslatef(x,y,z);
		//Log.e("",""+CubeRotation);
		//CubeRotation += 9;
		float temporary = new Float(CubeRotation);
		gl.glRotatef(temporary, current_Rotation[0], current_Rotation[1], current_Rotation[2]);
		//.e("",""+CubeRotation);
		gl.glFrontFace(GL10.GL_CW);

		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
		gl.glColorPointer(4, GL10.GL_FLOAT, 0, colorBuffer);


		//TODO find a way to handle the translate
		//gl.glTranslatef(0f, 0f, -10f);
		//mCubeRotation -= .5f;



		//gl.glRotatef(mCubeRotation, 1f, 1f, 1f);

		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glEnableClientState(GL10.GL_COLOR_ARRAY);

		gl.glDrawElements(GL10.GL_TRIANGLES, indicies.length, GL10.GL_UNSIGNED_BYTE, 
				indexBuffer);

		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glDisableClientState(GL10.GL_COLOR_ARRAY);

		gl.glRotatef(-temporary, current_Rotation[0], current_Rotation[1], current_Rotation[2]);
		gl.glTranslatef(-x,-y,-z);
	}

	
	boolean rotx = false;
	boolean roty = false;
	boolean rotz = false;

	public void setRotation(float rotDeg, boolean[] dirs){
		Log.w("",""+CubeRotation);

		//TODO matrix multiplication for rotation
		CubeRotation = (CubeRotation+rotDeg)%360;
		
		if (dirs[0]){
			if(rotx) rotx = false;
			else rotx = true;
		}
		if (dirs[1]){
			if(roty) roty = false;
			else roty = true;
		}
		if (dirs[2]){
			if(rotz) rotz = false;
			else rotz = true;
		}
		
		current_Rotation[0] = (rotx? 1:0);
		current_Rotation[1] = (roty? 1:0);
		current_Rotation[2] = (rotz? 1:0);
	}


}
