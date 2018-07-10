package top.nrd90m.iwm.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;

import top.nrd90m.iwm.R;
import top.nrd90m.iwm.book.BookMainActivity;
import top.nrd90m.iwm.gb.MainActivity_Gb;
import top.nrd90m.iwm.yee.MainActivity_Yee;

public class MainActivity_HomePage extends Activity implements OnClickListener {
	private ImageView img, img_one, img_two, img_three;
	private ImageView su_one, su_two, su_three;
	public static boolean h = false;
	public static boolean l = false;
	public static boolean j = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
	}

	public void init() {

		su_one = (ImageView) findViewById(R.id.su_one);
		su_two = (ImageView) findViewById(R.id.su_two);
		su_three = (ImageView) findViewById(R.id.su_three);

		img = (ImageView) findViewById(R.id.img_one);
		img.setOnClickListener(this);

		img_one = (ImageView) findViewById(R.id.img_two);
		img_one.setOnClickListener(this);

		img_two = (ImageView) findViewById(R.id.img_three);
		img_two.setOnClickListener(this);

		img_three = (ImageView) findViewById(R.id.img_four);
		img_three.setOnClickListener(this);
	}

	@Override
	protected void onResume() {
		if (h) {

			su_one.setVisibility(View.GONE);
		}
		if (l) {

			su_two.setVisibility(View.GONE);
		}

		super.onResume();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.img_one:
			Intent intent = new Intent(this, BookMainActivity.class);
			startActivity(intent);
			break;
		case R.id.img_two:
			if (h) {
				Intent intent1 = new Intent(this, MainActivity_Yee.class);
				startActivity(intent1);
			} else {
				Toast.makeText(getApplicationContext(), "未解锁", 0).show();
			}
			break;
		case R.id.img_three:
			if (l) {
				Intent intent2 = new Intent(this, MainActivity_Gb.class);
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
