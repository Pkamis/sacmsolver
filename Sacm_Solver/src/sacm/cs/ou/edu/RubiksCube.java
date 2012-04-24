/**
 * This class initializes n^3 individual cubes and puts them together to form one big rubiks cube.
 * It also initializes the colors and updates the frame.
 */

package sacm.cs.ou.edu;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLU;
import android.util.Log;

class RubiksCube implements Renderer 
{
	Quaternion mCubeRotation = Quaternion.fromAxis(new Vector3D(1f,1f,0f), (float)Math.PI/4);
	float rotateIncrement = 45;

	Cube[][][] chickens;
	int cubeSize = 3;
	private boolean okToDraw = false;

	
	private boolean rotating = false;
	private final int maxSpeed = 40;
	private int speed;
	private boolean turnLR;
	private Axis turnType;
	private int turnDist;

	public RubiksCube()
	{
		initializeCubes();
		initializeColors();
		okToDraw = true;
	}

	/** Sets the size of the 3 dimensional array and builds all n^3 individual cubes. */
	private void initializeCubes ()
	{
		chickens = new Cube[cubeSize][cubeSize][cubeSize];

		for (int x = 0; x < cubeSize; x++)
			for (int y = 0; y < cubeSize; y++)
				for (int z = 0; z < cubeSize; z++)
				{
					chickens[z][y][x] = new Cube();
				}
	}

	public void rotateView (Quaternion rotate) 
	{
		// TODO
		mCubeRotation = mCubeRotation.multiplyQuaternion(rotate);
	}

	/**
	 *  Resets the cube to its initial positioning.
	 * @param size The size of the new Rubiks cube
	 */
	public void reset (int size)
	{
		okToDraw = false;
		cubeSize = size;
		initializeCubes();
		initializeColors();
		okToDraw = true;
	}

	/**
	 * Called from the activity view to paint the canvas using OpenGL rendering.
	 * Iterates over the 3 dimensional array telling each individual cube to draw itself based on position within the Rubiks cube.
	 */
	public void onDrawFrame(GL10 gl) 
	{
		if (okToDraw)
		{
			if(rotating) // TODO
			{
				if (speed > 0)
				{
					speed--;
					rotateCube(turnLR,turnType,turnDist,(float)((-Math.PI/2)/maxSpeed));

					if(speed == maxSpeed/2)
						rotateArray(turnLR,turnType,turnDist);
				}
				else
				{
					rotating = false;
				}
			}


			gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);        
			gl.glLoadIdentity(); // Reset the current rotation/translation

			gl.glTranslatef(0f, 0f, -6f*cubeSize); // Draws the cube further down (based on cube size) so that the camera zooms out to see the whole cube
			rotateIncrement += .5; // slowly rotates the cube
			//			gl.glRotatef(mCubeRotation.getAngle()*180f/(float)Math.PI, mCubeRotation.getX(),mCubeRotation.getY(),mCubeRotation.getZ());
			gl.glRotatef(rotateIncrement, 1f, 1f, 0f);
			gl.glTranslatef(-cubeSize+1, cubeSize-1, cubeSize-1);

			for(int x = 0; x < cubeSize; x++)
				for(int y = 0; y < cubeSize; y++)
					for(int z = 0; z < cubeSize; z++)
					{
						// When going from a small cube to a big cube it may not have all the cubes initialized by the time it wants to draw them
						try { chickens[x][y][z].draw(gl,new Vector3D (2.2f*z,-2.2f*y,-2.2f*x)); } 
						catch(NullPointerException e) { Log.e("Null Pointer Exception in drawing?!","Cube: ("+x+","+y+","+z+")"); }
					}
		}
	}

	/** Loops through each face of the cube and sets the designated color in the individual cubes. */
	private void initializeColors ()
	{
		int s = cubeSize;

		// front
		for (int x = 0; x < s; x++)
			for (int y = 0; y < s; y++)
				chickens[0][y][x].setColor(Face.FRONT,Colors.RED);
		// right
		for (int z = 0; z < s; z++)
			for (int y = 0; y < s; y++)
				chickens[z][y][s-1].setColor(Face.RIGHT,Colors.WHITE);
		// top
		for (int z = 0; z < s; z++)
			for (int x = 0; x < s; x++)
				chickens[z][0][x].setColor(Face.TOP,Colors.GREEN);
		// back
		for (int x = 0; x < s; x++)
			for (int y = 0; y < s; y++)
				chickens[s-1][y][x].setColor(Face.BACK,Colors.PURPLE);
		// left
		for (int z = 0; z < s; z++)
			for (int y = 0; y < s; y++)
				chickens[z][y][0].setColor(Face.LEFT,Colors.YELLOW);
		// bottom
		for (int z = 0; z < s; z++)
			for (int x = 0; x < s; x++)
				chickens[z][s-1][x].setColor(Face.BOTTOM,Colors.BLUE);

		
		// Finishes setting up the cubes so that they can be drawn
		for (int x = 0; x < s; x++)
			for (int y = 0; y < s; y++)
				for (int z = 0; z < s; z++)
				{
					chickens[z][y][x].finalizeCube();
				}


		// front right top back left bottom
		/*
		chickens[0][0][0] = new Cube(new Colors[] {r,k,g,k,y,k});
		chickens[0][0][1] = new Cube(new Colors[] {r,k,g,k,k,k});
		chickens[0][0][2] = new Cube(new Colors[] {r,w,g,k,k,k});

		chickens[0][1][0] = new Cube(new Colors[] {r,k,k,k,y,k});
		chickens[0][1][1] = new Cube(new Colors[] {r,k,k,k,k,k});
		chickens[0][1][2] = new Cube(new Colors[] {r,w,k,k,k,k});

		chickens[0][2][0] = new Cube(new Colors[] {r,k,k,k,y,b});
		chickens[0][2][1] = new Cube(new Colors[] {r,k,k,k,k,b});
		chickens[0][2][2] = new Cube(new Colors[] {r,w,k,k,k,b});

		chickens[1][0][0] = new Cube(new Colors[] {k,k,g,k,y,k});
		chickens[1][0][1] = new Cube(new Colors[] {k,k,g,k,k,k});
		chickens[1][0][2] = new Cube(new Colors[] {k,w,g,k,k,k});

		chickens[1][1][0] = new Cube(new Colors[] {k,k,k,k,y,k});
		chickens[1][1][1] = new Cube(new Colors[] {k,k,k,k,k,k});
		chickens[1][1][2] = new Cube(new Colors[] {k,w,k,k,k,k});

		chickens[1][2][0] = new Cube(new Colors[] {k,k,k,k,y,b});
		chickens[1][2][1] = new Cube(new Colors[] {k,k,k,k,k,b});
		chickens[1][2][2] = new Cube(new Colors[] {k,w,k,k,k,b});

		chickens[2][0][0] = new Cube(new Colors[] {k,k,g,o,y,k});
		chickens[2][0][1] = new Cube(new Colors[] {k,k,g,o,k,k});
		chickens[2][0][2] = new Cube(new Colors[] {k,w,g,o,k,k});

		chickens[2][1][0] = new Cube(new Colors[] {k,k,k,o,y,k});
		chickens[2][1][1] = new Cube(new Colors[] {k,k,k,o,k,k});
		chickens[2][1][2] = new Cube(new Colors[] {k,w,k,o,k,k});

		chickens[2][2][0] = new Cube(new Colors[] {k,k,k,o,y,b});
		chickens[2][2][1] = new Cube(new Colors[] {k,k,k,o,k,b});
		chickens[2][2][2] = new Cube(new Colors[] {k,w,k,o,k,b});
		 */
	}

	/** Swaps 2 cube locations within the rubiks cube */
	private void swapper(int x1, int y1, int z1, int x2, int y2, int z2)
	{
		Cube temp = chickens[x1][y1][z1];
		chickens[x1][y1][z1] = chickens[x2][y2][z2];
		chickens[x2][y2][z2] = temp;

		Quaternion temp1 = chickens[x1][y1][z1].currLoc;
		chickens[x1][y1][z1].currLoc = chickens[x2][y2][z2].currLoc;
		chickens[x2][y2][z2].currLoc = temp1;
	}

	/**
	 * Rotates a slice of the cube if it's not already rotating.
	 * @param left_right Whether to rotate clockwise or counterclockwise.
	 * @param type Which axis to rotate around.
	 * @param dist Distance inward from the side of the cube. 0 = first layer, 1 = second layer, etc.
	 */
	public void rotate(boolean left_right,Axis type, int dist)
	{
		if (!rotating)
		{
			turnLR = left_right;
			turnType = type;
			turnDist = dist;

			rotating = true;
			speed = maxSpeed;
		}
		//		rotate(left_right,type,dist, (float)-Math.PI/2f);
	}

	private void rotateCube (boolean left_right,Axis type, int dist, float angle)
	{
		switch(type)
		{
		case Z:
			for(int i = 0; i < (left_right? 1:3); i++)
				for(int x = 0; x < cubeSize; x++)
					for(int y = 0; y < cubeSize; y++)
					{
						chickens[dist][x][y].setRotation(Quaternion.fromAxis(new Vector3D(0,0,1f), angle)); 
						//chickens[dist][x][y].rotating = true;
					}
			break;
		case Y:
			for(int i = 0; i < (left_right? 1:4); i++)
				for(int x = 0; x < cubeSize; x++)
					for(int y = 0; y < cubeSize; y++)
					{
						chickens[x][dist][y].setRotation(Quaternion.fromAxis(new Vector3D(0,1f,0), angle));
						//chickens[x][dist][y].rotating = true;
					}
			break;
		case X:
			for(int i = 0; i < (left_right? 1:4); i++)
				for(int x = 0; x < cubeSize; x++)
					for(int y = 0; y < cubeSize; y++)
					{
						chickens[x][y][dist].setRotation(Quaternion.fromAxis(new Vector3D(1f,0,0), angle));	
						//chickens[x][y][dist].rotating = true;
					}
			break;
		}
	}

	/**
	 * Rotates the cubes of a slice within the 3-dimensional array, changing their positions.
	 * @param left_right Whether to rotate clockwise or counter-clockwise
	 * @param type The axis of rotation
	 * @param dist The distance inward from the face of the cube
	 */
	private void rotateArray (boolean left_right, Axis type, int dist)
	{
		int s = cubeSize - 1;

		switch(type)
		{
		case Z:
			for(int i = 0; i < (left_right? 3:1); i++)
			{
				for(int x = 0; x < cubeSize/2; x++)
					for(int y = x; y < cubeSize-x-1; y++)
					{
						swapper(dist, y, x, dist, s-x, y);
						swapper(dist, y, x, dist, s-y, s-x);
						swapper(dist, y, x, dist, x, s-y);
					}
			}
			break;
		case Y:
			for(int i = 0; i < (left_right? 1:3); i++)
			{
				for(int x = 0; x < cubeSize/2; x++)
					for(int z = x; z < cubeSize-x-1; z++)
					{
						swapper( z, dist, x, s-x, dist, z);
						swapper( z, dist, x, s-z, dist, s-x);
						swapper( z, dist, x,  x, dist, s-z);
					}
			}
			break;
		case X:
			for(int i = 0; i < (left_right? 1:3); i++)
			{	
				for(int y = 0; y < cubeSize/2; y++)
					for(int z = y; z < cubeSize-y-1; z++)
					{
						swapper( z, y, dist, s-y, z,dist);
						swapper( z, y, dist, s-z, s-y,dist);
						swapper( z, y, dist, y, s-z,dist);
					}
			}
			break;
		}
	}


	public void onSurfaceCreated(GL10 gl, EGLConfig config) 
	{
		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.5f); 

		gl.glClearDepthf(1.0f);
		gl.glEnable(GL10.GL_DEPTH_TEST);
		gl.glDepthFunc(GL10.GL_LEQUAL);

		gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);
	}

	public void onSurfaceChanged(GL10 gl, int width, int height) {
		gl.glViewport(0, 0, width, height);
		gl.glMatrixMode(GL10.GL_PROJECTION);
		gl.glLoadIdentity();
		GLU.gluPerspective(gl, 45.0f, (float)width / (float)height, 0.1f, 100.0f);
		gl.glViewport(0, 0, width, height);

		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
	}


}

