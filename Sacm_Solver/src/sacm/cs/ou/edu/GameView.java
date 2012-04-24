package sacm.cs.ou.edu;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Point;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

public class GameView extends GLSurfaceView
{
	/** The activity running this view */
	Activity mActivity;
	
	RubiksCube cube;

	Point down;
	Point move;

	public GameView(Activity activity, Context context) 
	{
		super(context);
		mActivity = activity;
		cube = new RubiksCube(); // create a default cube to start with
		this.setRenderer(cube); // can only be called once
		promptSize();
	}
	
	/** Displays a list of numbers for the user to choose a size for the rubiks cube */
	private void promptSize ()
	{
		final CharSequence[] items = {"2","3","4","5","6","7"};
		
		AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
		builder.setTitle("Cube Size"); builder.setCancelable(true);
		builder.setItems(items, new DialogInterface.OnClickListener() 
		{
			public void onClick(DialogInterface dialog, int item) 
			{
				cube.reset(item+2);
			}});
		AlertDialog alert = builder.create(); alert.show();
	}
	
	/** Called every time a touch is registered on the screen */
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
					// TODO randomly rotates a side of the cube
					int num = (int)(Math.random()*cube.cubeSize);
					Axis axis = Axis.Z;
					switch (num) {
					case 0: axis = Axis.Z; break;
					case 1: axis = Axis.Y; break;
					case 2: axis = Axis.X; break;
					}
					cube.rotate(((int)(Math.random()*2) == 0)? true:true, axis, (int)(Math.random()*cube.cubeSize));
				}
			});
		}
		return true;
	}
}
