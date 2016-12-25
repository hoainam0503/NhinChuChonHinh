package com.deitel.myapplication;





import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {

    ImageView iv1, iv2;
    Sound sound;
    AlertDialog.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sound = new Sound(this, R.raw.welcome);
        sound.play();

        iv1 = (ImageView)findViewById(R.id.ivPlay);
        iv2 = (ImageView)findViewById(R.id.ivStop);
        sound = new Sound(MainActivity.this, R.raw.genie);
        sound.play();
        sound.loop();

        builder = new AlertDialog.Builder(this);
        iv2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                builder.setTitle("Game");
                builder.setMessage("Do you want to exit?");
                builder.setPositiveButton("OK", new OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int which) {
                        // TODO Auto-generated method stub
                        sound.stop();
                        MainActivity.this.finish();
                    }
                });
                builder.setNegativeButton("Cancel", new OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub

                    }
                });
                builder.show();
            }
        });

        iv1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                builder.setTitle("Game");
                builder.setMessage("Are you ready?");
                builder.setPositiveButton("OK", new OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int which) {
                        // TODO Auto-generated method stub
                        Intent intent = new Intent(MainActivity.this, ChooseActivity.class);
                        startActivityForResult(intent, 0);
                        sound.stop();
                        finish();

                    }
                });
                builder.show();
            }
        });

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            sound.stop();
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
