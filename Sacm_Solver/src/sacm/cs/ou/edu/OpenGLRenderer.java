package sacm.cs.ou.edu;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLU;
import android.util.Log;

class OpenGLRenderer implements Renderer {

    //private Cube mCube1;
    //private Cube mCube2 = new Cube(0f, 0f, -2.1f, .2f);
    //private Cube mCube3 = new Cube(0f, 0f, -2.1f, .1f);
    //private Cube mCube4 = new Cube(0f, 0f, -2.1f, 0f);
    
    //private float mCubeRotation;
	public OpenGLRenderer(){
		//
		mCubes.rotate(true, 0, 0);
        mCubes.rotate(true, 1, 0);
        mCubes.rotate(true, 1, 0);
        Log.e("", "fghjk");
	}

    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.5f); 
            
        gl.glClearDepthf(1.0f);
        gl.glEnable(GL10.GL_DEPTH_TEST);
        gl.glDepthFunc(GL10.GL_LEQUAL);

        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT,
                  GL10.GL_NICEST);
            
    }
    RubiksCube mCubes = new RubiksCube();

    public void onDrawFrame(GL10 gl) {
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);        
        gl.glLoadIdentity();
        
//        mCubeRotation -= Math.random()*5;
        
        
        gl.glTranslatef(-2.0f, 2.0f, -20f);
        gl.glRotatef(45, 1f,1f, 0f);
       
            
         

        
        
        for(int x = 0; x < 3f; x++)
        	for(int y = 0; y < 3f; y++)
        		for(int z = 0; z < 3f; z++)
        		{
        			mCubes.chickens[x][y][z].draw(gl,2.1f*z,-2.1f*y,-2.1f*x);
        		}
       
        
        //Cube2.draw(gl);
        //mCube3.draw(gl);
        //mCube4.draw(gl);
        
        
        gl.glLoadIdentity();
            
        
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

