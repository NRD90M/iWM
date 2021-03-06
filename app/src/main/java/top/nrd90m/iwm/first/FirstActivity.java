package top.nrd90m.iwm.first;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.OvershootInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Toast;
import android.widget.VideoView;

import java.text.SimpleDateFormat;
import java.util.Date;

import top.nrd90m.iwm.main.MainActivity;
import top.nrd90m.iwm.R;

public class FirstActivity extends FragmentActivity {
	private VideoView videoView;
	private View frameView;
	private View upView;
	private View downView;
	private View handsunshineView;
	Animation translateUp;
	Animation translateUpBack;
	Animation translateDown;
	Animation translateDownBack;
	Animation zoomOutEnter;
	private int videoFrameWidth = 720;
	private int videoFrameHeight = 540;
	String postdate="2018-08-17";
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	private static final String TAG = "FirstActivity";

	Date curDate = new Date(System.currentTimeMillis());//获取当前时间

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i(TAG, "postdate: "+postdate);
		Log.i(TAG, "formatter: "+formatter.format(curDate));

		if(!formatter.format(curDate).equals(postdate))
		{
			Toast.makeText(this,"小可爱，情人节当天才能打开哦！",Toast.LENGTH_LONG).show();
			finish();
			return;
		}


		setContentView(R.layout.first_view);
		videoView = (VideoView) findViewById(R.id.video_view_show);
		frameView = findViewById(R.id.frame_view);
		upView = findViewById(R.id.up_view);
		downView = findViewById(R.id.down_view);
		handsunshineView = findViewById(R.id.handsunshine_view);

		// 获得屏蔽宽度
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		int screenWidth = dm.widthPixels;

		// 设置视频控件高度
		int videoViewHeight = (int) (screenWidth * (1.0f * videoFrameHeight / videoFrameWidth));
		ViewGroup.LayoutParams params = videoView.getLayoutParams();
		params.height = videoViewHeight;
		videoView.setLayoutParams(params);
		// 设置视频控件周围红色框高度
		params = frameView.getLayoutParams();
		params.height = videoViewHeight;
		frameView.setLayoutParams(params);
		translateUp = new TranslateAnimation(0, 0, 0, -videoViewHeight / 2f);
		translateUp.setDuration(3000);
		translateUp.setFillAfter(true);
		translateUpBack = new TranslateAnimation(0, 0, -videoViewHeight / 2f, 0);
		translateUpBack.setDuration(3000);
		translateUpBack.setFillAfter(true);
		translateDown = new TranslateAnimation(0, 0, 0, videoViewHeight / 2f);
		translateDown.setDuration(3000);
		translateDown.setFillAfter(true);
		translateDownBack = new TranslateAnimation(0, 0, videoViewHeight / 2f,
				0);
		translateDownBack.setDuration(3000);
		translateDownBack.setFillAfter(true);
		zoomOutEnter = new ScaleAnimation(0, 1, 0, 1,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		zoomOutEnter.setDuration(6000);
		zoomOutEnter.setInterpolator(new OvershootInterpolator());
		zoomOutEnter.setAnimationListener(new DeleteAnimationListener());
		upView.startAnimation(translateUp);
		downView.startAnimation(translateDown);
		videoView.setVideoURI(Uri.parse("android.resource://"
				+ getPackageName() + "/" + R.raw.love_video));
		videoView.start();
		videoView
				.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
					@Override
					public void onCompletion(MediaPlayer mp) {
						upView.startAnimation(translateUpBack);
						downView.startAnimation(translateDownBack);
						handsunshineView.startAnimation(zoomOutEnter);
						handsunshineView.setVisibility(View.VISIBLE);
					}
				});

	}
	// 播放完成
	private class DeleteAnimationListener implements AnimationListener {
		public void onAnimationEnd(Animation animation) {
			// TODO Auto-generated method stub

			Intent intent = new Intent();
			intent.setClass(FirstActivity.this,
					MainActivity.class);
			startActivity(intent);
			finish();

			// Toast.makeText(getApplicationContext(), "进入主页", 0).show();
		}

		public void onAnimationRepeat(Animation animation) {

		}

		public void onAnimationStart(Animation animation) {

		}
	}
}
