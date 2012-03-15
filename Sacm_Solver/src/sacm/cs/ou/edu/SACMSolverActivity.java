package sacm.cs.ou.edu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SACMSolverActivity extends Activity implements OnClickListener
{
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		((Button)findViewById(R.id.gameButton)).setOnClickListener(this);
		((Button)findViewById(R.id.optionsButton)).setOnClickListener(this);
	}

	public void onClick(View v)
	{
		switch(v.getId())
		{
		case R.id.gameButton: startActivity(new Intent(this, GameActivity.class)); break;
		case R.id.optionsButton: startActivity(new Intent(this, OptionsActivity.class)); break;
		}
	}
}