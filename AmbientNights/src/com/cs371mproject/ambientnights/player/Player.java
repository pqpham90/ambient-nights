package com.cs371mproject.ambientnights.player;

import java.util.Locale;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.cs371mproject.ambientnights.R;
import com.cs371mproject.ambientnights.content.Theme;

public class Player extends Service {
	private String themeName = null;
	private Theme theme = null;

	private MediaPlayer player = null;
	private boolean isPlaying = false;

	private static int classID = 241; // just a number

	public static String START_PLAY = "START_PLAY";

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Bundle extras = intent.getExtras();
		themeName = extras.getString("theme");

		theme = new Theme(themeName.toLowerCase(Locale.ENGLISH));

		if (intent.getBooleanExtra(START_PLAY, false)) {
			play();
		}
		return Service.START_STICKY;
	}

	private void play() {
		if (player == null) {
			isPlaying = true;

			Intent intent = new Intent(this, PlayScreen.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
					| Intent.FLAG_ACTIVITY_SINGLE_TOP);

			PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);

			Notification notification = new Notification.Builder(
					getApplicationContext()).setContentTitle("Ambient Nights")
					.setContentText("Now Playing: \"" + themeName + "\"")
					.setSmallIcon(R.drawable.ic_launcher).setContentIntent(pi)
					.build();

			player = new MediaPlayer();
			try {
				AssetFileDescriptor track = getAssets().openFd(
						themeName + "/" + theme.getTrack1());
				player.setDataSource(track.getFileDescriptor(),
						track.getStartOffset(), track.getLength());
				player.prepare();
			} catch (Exception e) {
				Log.d("Exception", "Exception thrown from media player");
			}

			player.setLooping(true);
			player.start();
			Toast.makeText(getApplicationContext(), "Play",
					Toast.LENGTH_LONG).show();

			startForeground(classID, notification);
		} 
		else {
			Toast.makeText(getApplicationContext(), "Play",
					Toast.LENGTH_LONG).show();
			player.start();
		}
	}

	@Override
	public void onDestroy() {
		stop();
	}

	private void stop() {
		if (isPlaying) {
			isPlaying = false;
			if (player != null) {
				player.release();
				player = null;
			}
			stopForeground(true);
		}
	}

}
