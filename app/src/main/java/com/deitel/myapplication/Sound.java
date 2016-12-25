package com.deitel.myapplication;

import android.content.Context;
import android.media.MediaPlayer;

public class Sound {
	private MediaPlayer mPlayer;

	private boolean mPlaying = false;
	private boolean mLoop = false;
	public boolean complet = false;

	public Sound(Context ctx, int resID) {
		mPlayer = MediaPlayer.create(ctx, resID);
		mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
			public void onCompletion(MediaPlayer mp) {
				mPlaying = false;
				complet = true;
				if (mLoop) {
					mp.start();
				}
			}

		});
	}

	public void play() {
		if (mPlaying)
			return;

		if (mPlayer != null) {
			mPlaying = true;
			mPlayer.start();
		}
	}

	public void replay() {
		if (mPlaying)
			return;

		if (mPlayer != null) {
			mPlaying = true;
			mPlayer.seekTo(0);
			mPlayer.start();
		}
	}

	public void stop() {
		try {
			mLoop = false;
			if (mPlaying) {
				mPlaying = false;
				mPlayer.pause();
			}

		} catch (Exception e) {
		}
	}

	public void loop() {
		mLoop = true;
		mPlaying = true;
		mPlayer.start();
	}

	//synchronized
	
	public void release() {
		if (mPlayer != null) {
			mPlayer.release();
			mPlayer = null;
		}
	}
}
