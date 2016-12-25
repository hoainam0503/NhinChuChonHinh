package com.deitel.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;

public class ChooseActivity extends Activity{

	Sound sound;
	ImageView iv1, iv2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.choose_to_play);
		
		sound = new Sound(this, R.raw.haruharu);
		sound.loop();
		
		iv1 = (ImageView)findViewById(R.id.imageView1);
		iv2 = (ImageView)findViewById(R.id.imageView2);
		iv1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(ChooseActivity.this, GLevel1Activity.class);
				startActivityForResult(intent, 0);
				sound.stop();
				finish();
			}
		});
		iv2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(ChooseActivity.this, BLevel1Activity.class);
				startActivityForResult(intent, 0);
				sound.stop();
				finish();
			}
		});
	}
	@Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
        	sound.stop();
        	Intent intent = new Intent(ChooseActivity.this, MainActivity.class);
			startActivityForResult(intent, 0);		
			finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
