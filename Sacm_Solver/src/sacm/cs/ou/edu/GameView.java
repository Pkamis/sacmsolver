package sacm.cs.ou.edu;

import android.content.Context;
import android.graphics.Point;
import android.opengl.GLSurfaceView;
import android.util.Log;
import android.view.MotionEvent;

public class GameView extends GLSurfaceView
{
	RubiksCube cube;

	Point down;
	Point move;

	public GameView(Context context) 
	{
		super(context);
		cube = new RubiksCube();
		this.setRenderer(cube);
	}
	public boolean onTouchEvent (MotionEvent e)
	{
//		if (e.getPointerCount() == 1)
//		{
//			switch (e.getAction())
//			{
//			case MotionEvent.ACTION_DOWN: 	down = new Point((int)e.getX(),(int)e.getY()); move = new Point((int)e.getX(),(int)e.getY()); break;
//			case MotionEvent.ACTION_MOVE: 	down = move; move = new Point((int)e.getX(e.getPointerId(e.getPointerCount()-1)),(int)e.getY()); break;
//			}
//
//			queueEvent(new Runnable() 
//			{
//				public void run() {
//					cube.rotateView(Quaternion.fromAxis(new Vector3D(down.x-move.x,down.y-move.y,0), .01f));
//				}
//			});
//		}
//		else if (e.getPointerCount() == 2)
		{
			queueEvent(new Runnable() 
			{
				public void run() {
					cube.rotate(((int)(Math.random()*2) == 0)? true:true, (int)(Math.random()*3), (int)(Math.random()*3));
				}
			});
		}
		return true;
	}
}
