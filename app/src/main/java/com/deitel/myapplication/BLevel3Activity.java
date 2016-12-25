package com.deitel.myapplication;

import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class BLevel3Activity extends Activity {

	CountDownTimer b;
	Sound sound, error, gameover, win, time, clock;
	TextView tv[] = new TextView[6];
	ImageView iv[] = new ImageView[8];
	Sound mp3;
	int sai = 0, dung = 0;

	String dapan[][] = {
			{ "Wetsuit", "Snorkel", "Umbrella", "Kickboard", "Bathing suit" },

			{ "Shell", "Flipper", "Ball", "Cooler", "Sunglasses" },

			{ "Towel", "Sandcastle", "Mask", "Whistle", "Scuba tank" } };
	int[][] phatam = {
			{ R.raw.wetsuit, R.raw.snorkel, R.raw.umbrella, R.raw.kickboard,
					R.raw.bathing_suit },

			{ R.raw.shell, R.raw.flipper, R.raw.ball, R.raw.cooler,
					R.raw.sunglasses },

			{ R.raw.beach_towe, R.raw.sandcastle, R.raw.mask, R.raw.whistle,
					R.raw.scuba_tank } };

	int pic[][] = {
			{ R.drawable.bathing_suit, R.drawable.bucket, R.drawable.wetsuit,
					R.drawable.surfboard, R.drawable.snorkel,
					R.drawable.frisbee, R.drawable.umbrella,
					R.drawable.kickboard },

			{ R.drawable.beach_ball, R.drawable.lifeguard_chair,
					R.drawable.sunglass, R.drawable.shell, R.drawable.flipper,
					R.drawable.suntan, R.drawable.cooler,
					R.drawable.safety_life_vest },

			{ R.drawable.beach_chair, R.drawable.life_preserver,
					R.drawable.swimming_trunks, R.drawable.whistle,
					R.drawable.beach_towel, R.drawable.beach_mask,
					R.drawable.sandcastle, R.drawable.scuba_tank } };
	int[] namePic = { 0, 1, 2, 3, 4, 5, 6, 7 };
	int[][] dapans = { { 2, 4, 6, 7, 0 }, { 3, 4, 0, 6, 2 }, { 4, 6, 5, 3, 7 } };

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.beach_level3);

		sound = new Sound(BLevel3Activity.this, R.raw.choose3);
		error = new Sound(BLevel3Activity.this, R.raw.errorsounds);
		gameover = new Sound(BLevel3Activity.this, R.raw.gameover);
		win = new Sound(BLevel3Activity.this, R.raw.gamewin);
		time = new Sound(BLevel3Activity.this, R.raw.timeout);
		clock = new Sound(BLevel3Activity.this, R.raw.clocksounds);

		tv[0] = (TextView) findViewById(R.id.tvda121);
		tv[1] = (TextView) findViewById(R.id.tvda221);
		tv[2] = (TextView) findViewById(R.id.tvda321);
		tv[3] = (TextView) findViewById(R.id.tvda421);
		tv[4] = (TextView) findViewById(R.id.tvda521);
		tv[5] = (TextView) findViewById(R.id.tvTime1);

		iv[0] = (ImageView) findViewById(R.id.imageView121);
		iv[1] = (ImageView) findViewById(R.id.imageView221);
		iv[2] = (ImageView) findViewById(R.id.imageView321);
		iv[3] = (ImageView) findViewById(R.id.imageView421);
		iv[4] = (ImageView) findViewById(R.id.imageView521);
		iv[5] = (ImageView) findViewById(R.id.imageView621);
		iv[6] = (ImageView) findViewById(R.id.imageView721);
		iv[7] = (ImageView) findViewById(R.id.imageView821);

		final int index = GLevel1Activity.randomCauHoi(dapan);

		tv[0].setText(dapan[index][0]);
		tv[1].setText(dapan[index][1]);
		tv[2].setText(dapan[index][2]);
		tv[3].setText(dapan[index][3]);
		tv[4].setText(dapan[index][4]);

		iv[0].setImageResource(pic[index][0]);
		iv[1].setImageResource(pic[index][1]);
		iv[2].setImageResource(pic[index][2]);
		iv[3].setImageResource(pic[index][3]);
		iv[4].setImageResource(pic[index][4]);
		iv[5].setImageResource(pic[index][5]);
		iv[6].setImageResource(pic[index][6]);
		iv[7].setImageResource(pic[index][7]);

		sound.play();

		b = new CountDownTimer(17000, 1000) {

			@Override
			public void onTick(long millisUntilFinished) {
				// TODO Auto-generated method stub
				clock.loop();
				tv[5].setText("" + formatTime(millisUntilFinished));
			}

			@Override
			public void onFinish() {
				// TODO Auto-generated method stub
				clock.stop();
				TimeOut();
			}
		}.start();

		// event
		iv[0].setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				int i = check(0, index);
				if (i != -1) {
					mp3 = new Sound(BLevel3Activity.this, phatam[index][i]);
					iv[0].setVisibility(ImageView.INVISIBLE);
					mp3.play();
					tv[i].setVisibility(TextView.INVISIBLE);
					dung++;
					checkWin();
				} else {
					error.play();
					sai++;
					checkError();
				}
			}
		});
		iv[1].setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int i = check(1, index);
				if (i != -1) {
					mp3 = new Sound(BLevel3Activity.this, phatam[index][i]);
					mp3.play();
					iv[1].setVisibility(ImageView.INVISIBLE);
					tv[i].setVisibility(TextView.INVISIBLE);
					dung++;
					checkWin();
				} else {
					error.play();
					sai++;
					checkError();
				}

			}
		});
		iv[2].setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int i = check(2, index);
				if (i != -1) {
					mp3 = new Sound(BLevel3Activity.this, phatam[index][i]);
					mp3.play();
					iv[2].setVisibility(ImageView.INVISIBLE);
					tv[i].setVisibility(TextView.INVISIBLE);
					dung++;
					checkWin();
				} else {
					error.play();
					sai++;
					checkError();
				}
			}
		});
		iv[3].setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int i = check(3, index);
				if (i != -1) {
					mp3 = new Sound(BLevel3Activity.this, phatam[index][i]);
					mp3.play();
					iv[3].setVisibility(ImageView.INVISIBLE);
					tv[i].setVisibility(TextView.INVISIBLE);
					dung++;
					checkWin();
				} else {
					error.play();
					sai++;
					checkError();
				}
			}
		});
		iv[4].setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int i = check(4, index);
				if (i != -1) {
					mp3 = new Sound(BLevel3Activity.this, phatam[index][i]);
					mp3.play();
					iv[4].setVisibility(ImageView.INVISIBLE);
					tv[i].setVisibility(TextView.INVISIBLE);
					dung++;
					checkWin();
				} else {
					error.play();
					sai++;
					checkError();
				}
			}
		});
		iv[5].setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int i = check(5, index);
				if (i != -1) {
					mp3 = new Sound(BLevel3Activity.this, phatam[index][i]);
					mp3.play();
					iv[5].setVisibility(ImageView.INVISIBLE);
					tv[i].setVisibility(TextView.INVISIBLE);
					dung++;
					checkWin();
				} else {
					error.play();
					sai++;
					checkError();
				}
			}
		});
		iv[6].setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int i = check(6, index);
				if (i != -1) {
					mp3 = new Sound(BLevel3Activity.this, phatam[index][i]);
					mp3.play();
					iv[6].setVisibility(ImageView.INVISIBLE);
					tv[i].setVisibility(TextView.INVISIBLE);
					dung++;
					checkWin();
				} else {
					error.play();
					sai++;
					checkError();
				}
			}
		});
		iv[7].setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int i = check(7, index);
				if (i != -1) {
					mp3 = new Sound(BLevel3Activity.this, phatam[index][i]);
					mp3.play();
					iv[7].setVisibility(ImageView.INVISIBLE);
					tv[i].setVisibility(TextView.INVISIBLE);
					dung++;
					checkWin();
				} else {
					error.play();
					sai++;
					checkError();
				}
			}
		});

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ((keyCode == KeyEvent.KEYCODE_BACK)) {
			b.cancel();
			clock.stop();
			sound.stop();
			Intent intent = new Intent(BLevel3Activity.this,
					ChooseActivity.class);
			startActivityForResult(intent, 0);
			finish();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	public static int randomCauHoi(String dapan[][]) {
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Random random = new Random();
		int index = random.nextInt(dapan.length);
		return index;
	}

	public int check(int i, int index) {
		for (int j = 0; j < dapans[index].length; j++) {
			if (namePic[i] == dapans[index][j])
				return j;
		}
		return -1;
	}

	public void GameOver() {
		b.cancel();
		clock.stop();
		gameover.play();
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Nhìn chữ chọn hình");
		builder.setMessage("Game Over!You have more errors!");
		builder.setNegativeButton("Back menu",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						dialog.dismiss();
						Intent intent = new Intent(BLevel3Activity.this,
								MainActivity.class);
						startActivityForResult(intent, 0);
						finish();
					}
				});
		builder.setPositiveButton("Play again",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						dialog.dismiss();
						Intent intent = new Intent(BLevel3Activity.this,
								BLevel3Activity.class);
						startActivityForResult(intent, 0);
						finish();
					}
				});
		builder.show();
	}

	public void Win() {
		b.cancel();
		clock.stop();
		win.play();
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Nhìn chữ chọn hình");
		builder.setMessage("Finished Beach and Game");
		builder.setNegativeButton("Back",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						dialog.dismiss();
						Intent intent = new Intent(BLevel3Activity.this,
								ChooseActivity.class);
						startActivityForResult(intent, 0);
						finish();
					}
				});
		builder.show();
	}

	public void TimeOut() {
		b.cancel();
		clock.stop();
		time.play();
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Nhìn chữ chọn hình");
		builder.setMessage("Time out!");
		builder.setNegativeButton("Back menu",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						dialog.dismiss();
						Intent intent = new Intent(BLevel3Activity.this,
								MainActivity.class);
						startActivityForResult(intent, 0);
						finish();
					}
				});
		builder.setPositiveButton("Play again",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						dialog.dismiss();
						Intent intent = new Intent(BLevel3Activity.this,
								BLevel3Activity.class);
						startActivityForResult(intent, 0);
						finish();
					}
				});
		builder.show();
	}

	public String formatTime(long millis) {
		String output = "00:00";
		long seconds = millis / 1000;
		long minutes = seconds / 60;

		seconds = seconds % 60;
		minutes = minutes % 60;

		String sec = String.valueOf(seconds);
		String min = String.valueOf(minutes);

		if (seconds < 10)
			sec = "0" + seconds;
		if (minutes < 10)
			min = "0" + minutes;

		output = min + " : " + sec;
		return output;
	}

	public void checkError() {
		if (sai > 4) {
			GameOver();
		}
	}

	public void checkWin() {
		if (dung == 5) {
			Win();
		}
	}

}
