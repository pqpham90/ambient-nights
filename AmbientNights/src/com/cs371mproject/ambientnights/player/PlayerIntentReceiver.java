package com.cs371mproject.ambientnights.player;

import android.content.Context;
import android.content.Intent;

public class PlayerIntentReceiver extends android.content.BroadcastReceiver {
		   
	public void onReceive(Context ctx, Intent intent) {
		if (intent.getAction().equals(
				android.media.AudioManager.ACTION_AUDIO_BECOMING_NOISY)) {
			Intent myIntent = new Intent(ctx, Player.class);
			ctx.stopService(myIntent);
		}
	}
}

