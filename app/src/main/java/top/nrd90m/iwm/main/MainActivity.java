package top.nrd90m.iwm.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;

import top.nrd90m.iwm.R;
import top.nrd90m.iwm.letter.LetterActivity;
import top.nrd90m.iwm.circle.CircleActivity;
import top.nrd90m.iwm.gallery.GalleryActivity;

public class MainActivity extends Activity implements OnClickListener {
	private ImageView  img_one, img_two, img_three, img_four;
	private ImageView heart_one, heart_two, heart_three, heart_four;
	public static boolean one = true;
	public static boolean two = false;
	public static boolean three = false;
	public static boolean four = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
	}

	public void init() {

		heart_one = (ImageView) findViewById(R.id.heart_one);
		heart_two = (ImageView) findViewById(R.id.heart_two);
		heart_three = (ImageView) findViewById(R.id.heart_three);
		heart_four = (ImageView) findViewById(R.id.heart_four);

		img_one = (ImageView) findViewById(R.id.img_one);
		img_one.setOnClickListener(this);

		img_two = (ImageView) findViewById(R.id.img_two);
		img_two.setOnClickListener(this);

		img_three = (ImageView) findViewById(R.id.img_three);
		img_three.setOnClickListener(this);

		img_four = (ImageView) findViewById(R.id.img_four);
		img_four.setOnClickListener(this);
	}

	@Override
	protected void onResume() {
		if (one) {

			heart_one.setVisibility(View.GONE);
		}
		if (two) {

			heart_two.setVisibility(View.GONE);
		}
        if (three) {

            heart_three.setVisibility(View.GONE);
        }
        if (four) {

            heart_four.setVisibility(View.GONE);
        }

		super.onResume();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.img_one:
			Intent intent = new Intent(this, LetterActivity.class);
			startActivity(intent);
			break;
		case R.id.img_two:
			if (two) {
				Intent intent1 = new Intent(this, GalleryActivity.class);
				startActivity(intent1);
			} else {
				Toast.makeText(getApplicationContext(), "未解锁", 0).show();
			}
			break;
		case R.id.img_three:
			if (three) {
				Intent intent2 = new Intent(this, CircleActivity.class);
				startActivity(intent2);
			} else {
				Toast.makeText(getApplicationContext(), "未解锁", 0).show();
			}
			break;
		case R.id.img_four:

			Toast.makeText(getApplicationContext(), "未找到正确的激活方式", 0).show();

			break;
		default:
			break;
		}
	}

	private long exitTime = 0;

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK
				&& event.getAction() == KeyEvent.ACTION_DOWN) {
			if ((System.currentTimeMillis() - exitTime) > 2000) {
				Toast.makeText(getApplicationContext(), "再按一次退出程序",
						Toast.LENGTH_SHORT).show();
				exitTime = System.currentTimeMillis();
			} else {
				finish();
				System.exit(0);
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

}
