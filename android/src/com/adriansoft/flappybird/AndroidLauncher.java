package com.adriansoft.flappybird;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.adriansoft.flappybird.FlappyBird;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new FlappyBird(), config);



	}
	/*
	public void newScore(int score){
		SharedPreferences preferences = getSharedPreferences("nuevorecord", Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = preferences.edit();
		editor.putInt("puntage" ,score);
	}

	 */
}
