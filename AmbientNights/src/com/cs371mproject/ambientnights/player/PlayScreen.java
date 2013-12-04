package com.cs371mproject.ambientnights.player;

import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.Toast;

import com.cs371mproject.ambientnights.R;

public class PlayScreen extends Activity {

	private ImageButton backgroundButton = null;
	private boolean playing = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.player_screen);
		
		Bundle extras = getIntent().getExtras();
		final String themeName = extras.getString("theme");
		

		backgroundButton = (ImageButton)findViewById(R.id.backgroundButton);
		int id = getResources().getIdentifier("com.cs371mproject.ambientnights:drawable/" + themeName.toString().toLowerCase(Locale.ENGLISH) , null, null);
		backgroundButton.setImageResource(id);
		backgroundButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// stops previous theme when switching to new one
				Intent intentHack = new Intent(getApplicationContext(), 				
						Player.class);
				stopService(intentHack);
				
				
				Intent intent = new Intent(getApplicationContext(), 				
						Player.class);
				if(playing){
					Toast.makeText(getApplicationContext(), "Pause",
							Toast.LENGTH_LONG).show();
					stopService(intent);
					playing = false;
				}
				else{
					intent.putExtra(Player.START_PLAY, true);
					intent.putExtra("theme", themeName);
					startService(intent);
					playing = true;
				}
			}
		});
		
		Toast.makeText(getApplicationContext(), "Touch Image to Play/Pause",
				Toast.LENGTH_LONG).show();

	}
}

