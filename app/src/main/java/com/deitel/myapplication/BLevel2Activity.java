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

public class BLevel2Activity extends Activity {

	Sound sound, error, gameover, win, time, clock;
	TextView tv[] = new TextView[6];
	ImageView iv[] = new ImageView[12];
	Sound mp3;
	int sai = 0, dung = 0;
	CountDownTimer b;
	String dapan[][] = {
			{ "Wetsuit", "Whistle", "Surfboard", "Suntan lotion", "Sandcastle" },
			{ "Snorkel", "Shell", "Beach chair", "Safety life vest", "Cooler" },
			{ "Flipper", "Bucket", "Ball", "Life preserver", "Frisbee" } };
	int[][] phatam = {
			{ R.raw.wetsuit, R.raw.whistle, R.raw.surfboard, R.raw.suntan_lotion,
					R.raw.sandcastle },
			{ R.raw.snorkel, R.raw.shell, R.raw.beach_chair, R.raw.safety_life_vest,
					R.raw.cooler },
			{ R.raw.flipper, R.raw.bucket, R.raw.ball,
					R.raw.life_preserver, R.raw.frisbee } };

	int pic[][] = {
			{ R.drawable.umbrella, R.drawable.wetsuit,
					R.drawable.wave, R.drawable.whistle,
					R.drawable.swimming_trunks, R.drawable.surfboard,
					R.drawable.sunglass, R.drawable.suntan, R.drawable.snorkel,
					R.drawable.shell, R.drawable.scuba_tank,
					R.drawable.sandcastle },
			{ R.drawable.snorkel, R.drawable.wetsuit,
						R.drawable.shell, R.drawable.whistle,
						R.drawable.beach_chair, R.drawable.surfboard,
						R.drawable.safety_life_vest, R.drawable.suntan, R.drawable.life_preserver2,
						R.drawable.bucket, R.drawable.cooler,
						R.drawable.sandcastle },
			{ R.drawable.flipper, R.drawable.wetsuit,
							R.drawable.bucket, R.drawable.sunglass,
							R.drawable.frisbee, R.drawable.surfboard,
							R.drawable.safety_life_vest, R.drawable.life_preserver, R.drawable.beach_ball,
							R.drawable.whistle, R.drawable.snorkel,
							R.drawable.kickboard } };
	int[] namePic = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 };
	int[][] dapans = { { 1, 3, 5, 7, 11}, { 0, 2, 4, 6, 10 }, { 0, 2, 8, 7, 4 } };

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.beach_level2);

		sound = new Sound(this, R.raw.choose2);
		sound.play();

		error = new Sound(BLevel2Activity.this, R.raw.errorsounds);
		gameover = new Sound(BLevel2Activity.this, R.raw.gameover);
		win = new Sound(BLevel2Activity.this, R.raw.gamewin);
		time = new Sound(BLevel2Activity.this, R.raw.timeout);
		clock = new Sound(BLevel2Activity.this, R.raw.clocksounds);

		tv[0] = (TextView) findViewById(R.id.tvda12);
		tv[1] = (TextView) findViewById(R.id.tvda22);
		tv[2] = (TextView) findViewById(R.id.tvda32);
		tv[3] = (TextView) findViewById(R.id.tvda42);
		tv[4] = (TextView) findViewById(R.id.tvda52);
		tv[5] = (TextView) findViewById(R.id.tvTime);

		final int index = BLevel1Activity.randomCauHoi(dapan);

		tv[0].setText(dapan[index][0]);
		tv[1].setText(dapan[index][1]);
		tv[2].setText(dapan[index][2]);
		tv[3].setText(dapan[index][3]);
		tv[4].setText(dapan[index][4]);

		// ImageView
		iv[0] = (ImageView) findViewById(R.id.imageView12);
		iv[1] = (ImageView) findViewById(R.id.imageView22);
		iv[2] = (ImageView) findViewById(R.id.imageView32);
		iv[3] = (ImageView) findViewById(R.id.imageView42);
		iv[4] = (ImageView) findViewById(R.id.imageView52);
		iv[5] = (ImageView) findViewById(R.id.imageView62);
		iv[6] = (ImageView) findViewById(R.id.imageView72);
		iv[7] = (ImageView) findViewById(R.id.imageView82);
		iv[8] = (ImageView) findViewById(R.id.imageView92);
		iv[9] = (ImageView) findViewById(R.id.imageView102);
		iv[10] = (ImageView) findViewById(R.id.imageView112);
		iv[11] = (ImageView) findViewById(R.id.imageView122);

		iv[0].setImageResource(pic[index][0]);
		iv[1].setImageResource(pic[index][1]);
		iv[2].setImageResource(pic[index][2]);
		iv[3].setImageResource(pic[index][3]);
		iv[4].setImageResource(pic[index][4]);
		iv[5].setImageResource(pic[index][5]);
		iv[6].setImageResource(pic[index][6]);
		iv[7].setImageResource(pic[index][7]);
		iv[8].setImageResource(pic[index][8]);
		iv[9].setImageResource(pic[index][9]);
		iv[10].setImageResource(pic[index][10]);
		iv[11].setImageResource(pic[index][11]);

		// time

		b = new CountDownTimer(37000, 1000) {

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
					mp3 = new Sound(BLevel2Activity.this, phatam[index][i]);
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
					mp3 = new Sound(BLevel2Activity.this, phatam[index][i]);
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
					mp3 = new Sound(BLevel2Activity.this, phatam[index][i]);
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
					mp3 = new Sound(BLevel2Activity.this, phatam[index][i]);
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
					mp3 = new Sound(BLevel2Activity.this, phatam[index][i]);
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
					mp3 = new Sound(BLevel2Activity.this, phatam[index][i]);
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
					mp3 = new Sound(BLevel2Activity.this, phatam[index][i]);
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
					mp3 = new Sound(BLevel2Activity.this, phatam[index][i]);
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
		iv[8].setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int i = check(8, index);
				if (i != -1) {
					mp3 = new Sound(BLevel2Activity.this, phatam[index][i]);
					mp3.play();
					iv[8].setVisibility(ImageView.INVISIBLE);
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
		iv[9].setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int i = check(9, index);
				if (i != -1) {
					mp3 = new Sound(BLevel2Activity.this, phatam[index][i]);
					mp3.play();
					iv[9].setVisibility(ImageView.INVISIBLE);
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
		iv[10].setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int i = check(10, index);
				if (i != -1) {
					mp3 = new Sound(BLevel2Activity.this, phatam[index][i]);
					mp3.play();
					iv[10].setVisibility(ImageView.INVISIBLE);
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
		iv[11].setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int i = check(11, index);
				if (i != -1) {
					mp3 = new Sound(BLevel2Activity.this, phatam[index][i]);
					mp3.play();
					iv[11].setVisibility(ImageView.INVISIBLE);
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
			Intent intent = new Intent(BLevel2Activity.this,
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
						Intent intent = new Intent(BLevel2Activity.this,
								MainActivity.class);
						startActivityForResult(intent, 0);
						finish();
					}
				});
		builder.setPositiveButton("Play again",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						dialog.dismiss();
						Intent intent = new Intent(BLevel2Activity.this,
								BLevel2Activity.class);
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
		builder.setMessage("You win!");
		builder.setNegativeButton("Play again",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						dialog.dismiss();
						Intent intent = new Intent(BLevel2Activity.this,
								BLevel2Activity.class);
						startActivityForResult(intent, 0);
						finish();
					}
				});
		builder.setPositiveButton("Continue",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						dialog.dismiss();
						Intent intent = new Intent(BLevel2Activity.this,
						BLevel3Activity.class);
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
						Intent intent = new Intent(BLevel2Activity.this,
								MainActivity.class);
						startActivityForResult(intent, 0);
						finish();
					}
				});
		builder.setPositiveButton("Play again",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						dialog.dismiss();
						Intent intent = new Intent(BLevel2Activity.this,
								BLevel2Activity.class);
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
		if (sai > 5) {
			GameOver();
		}
	}

	public void checkWin() {
		if (dung == 5) {
			Win();
		}
	}
}
