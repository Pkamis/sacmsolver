package sacm.cs.ou.edu;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLU;
import android.util.Log;

class RubiksCube implements Renderer 
{
	 Quaternion mCubeRotation = Quaternion.fromAxis(new Vector3D(1f,1f,0f), (float)Math.PI/4);
	float rotateIncrement = 0;

	Cube[][][] chickens = new Cube[3][3][3];

	private Colors r = Colors.RED;
	private Colors g = Colors.GREEN;
	private Colors b = Colors.BLUE;
	private Colors y = Colors.YELLOW;
	private Colors o = Colors.ORANGE;
	private Colors w = Colors.WHITE;
	private Colors k = Colors.BLACK;



	public RubiksCube()
	{
		initializeColors();
	}
	
	public void rotateView (Quaternion rotate)
	{
		mCubeRotation = mCubeRotation.multiplyQuaternion(rotate);
	}


	public void onDrawFrame(GL10 gl) 
	{
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);        
		gl.glLoadIdentity();

		gl.glTranslatef(0f, 0f, -20f);
		rotateIncrement += 1;
//		rotateView(Quaternion.fromAxis(new Vector3D(1f,1f,0f), (float)Math.PI/4+rotateIncrement));
//		gl.glRotatef(mCubeRotation.getAngle()*180f/(float)Math.PI, mCubeRotation.getX(),mCubeRotation.getY(),mCubeRotation.getZ());
		gl.glRotatef(rotateIncrement, 1f, 1f, 0f);
		gl.glTranslatef(-2.0f, 2.0f, 0f);

		for(int x = 0; x < 3f; x++)
			for(int y = 0; y < 3f; y++)
				for(int z = 0; z < 3f; z++)
				{
					chickens[x][y][z].draw(gl,2.1f*z,-2.1f*y,-2.1f*x);
				}


		gl.glLoadIdentity();
	}


	private void initializeColors ()
	{
		// front right top back left bottom
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
	}

	private void swapper(int x1, int y1, int z1, int x2, int y2, int z2)
	{
		Cube temp = chickens[x1][y1][z1];
		chickens[x1][y1][z1] = chickens[x2][y2][z2];
		chickens[x2][y2][z2] = temp;
	}

	public void rotate(boolean left_right,int type, int dist)
	{
		switch(type)
		{
		case 0:
			for(int i = 0; i < (left_right? 1:3); i++)
			{
				for(int x = 0; x<3;x++)
					for(int y = 0; y<3; y++)
						chickens[dist][x][y].setRotation(Quaternion.fromAxis(new Vector3D(0,0,1f), (float) -Math.PI/2f));
				for(int x = 0; x<3;x++)
					for(int y = 0; y<3; y++)
					{
						swapper(dist, 0,0,dist, 0, 2);
						swapper(dist, 0,0,dist, 2, 2);
						swapper(dist, 0,0,dist, 2, 0);

						swapper(dist, 1,0,dist, 0, 1);
						swapper(dist, 1,0,dist, 1, 2);
						swapper(dist, 1,0,dist, 2, 1);
					}
			}
			break;
		case 1:
			for(int i = 0; i < (left_right? 1:4); i++)
			{
				for(int x = 0; x<3;x++)
					for(int y = 0; y<3; y++)
						chickens[x][dist][y].setRotation(Quaternion.fromAxis(new Vector3D(0,1f,0), (float) -Math.PI/2f));
				for(int x = 0; x<3;x++)
					for(int y = 0; y<3; y++)
					{
						swapper(0, dist,0,2, dist, 0);
						swapper(0, dist,0, 2, dist, 2);
						swapper(0, dist,0,0, dist, 2);

						swapper(1, dist,0,2, dist, 1);
						swapper(1, dist,0,1, dist, 2);
						swapper(1, dist,0,0, dist, 1);
					}
			}
			break;
		case 2:
			for(int i = 0; i < (left_right? 1:4); i++)
			{
				for(int x = 0; x<3;x++)
					for(int y = 0; y<3; y++)
						chickens[x][y][dist].setRotation(Quaternion.fromAxis(new Vector3D(1f,0,0), (float) -Math.PI/2f));		
				for(int x = 0; x<3;x++)
					for(int y = 0; y<3; y++)
					{
						swapper(0, 0,dist,2, 0,dist);
						swapper(0, 0,dist, 2, 2,dist);
						swapper(0, 0,dist,0, 2,dist);

						swapper(1, 0,dist,2, 1,dist);
						swapper(1, 0,dist,1, 2,dist);
						swapper(1, 0,dist,0, 1,dist);
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

