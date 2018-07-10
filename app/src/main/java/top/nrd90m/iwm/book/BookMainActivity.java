package top.nrd90m.iwm.book;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.TextView;

import top.nrd90m.iwm.demo.MainActivity_HomePage;
import top.nrd90m.iwm.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class BookMainActivity extends Activity {
	boolean p = false;
	private Button bt;
	private Animation an;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.book_main);
		bt = (Button) findViewById(R.id.but);
		TextView tv = (TextView) findViewById(R.id.text);
		//获取 raw里的 txt文本 
		InputStream text=	getResources().openRawResource(R.raw.a);
		tv.setText(getString(text));
		// 动画旋转
		an = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		an.setInterpolator(new LinearInterpolator());// 不停顿
		an.setRepeatCount(100);// 重复次数
		an.setFillAfter(true);// 停在最后
		an.setDuration(4000);// 旋转一次的时间
		bt.startAnimation(an);
		Intent intent = new Intent(BookMainActivity.this, MusicService.class);
		startService(intent);
		// 动画开始
		bt.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (!p) {
					// 首次点击后 停止动画 跟音乐
					bt.clearAnimation();
					Intent intent = new Intent(BookMainActivity.this,MusicService.class);
					stopService(intent);
					p = true;
				} else {
					// 当第二次点击后 开启动画 跟音乐
					bt.startAnimation(an);
					Intent intent = new Intent(BookMainActivity.this,MusicService.class);
					startService(intent);
					p = false;
				}
			}
		});
	}
	// 关闭时杀死服务
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		Intent intent = new Intent(BookMainActivity.this, MusicService.class);
		stopService(intent);
		super.onStop();
	}
	/**
	 * @param inputStream 解析txt文本
	 * @return
	 */
	public static String getString(InputStream inputStream) {
	    InputStreamReader inputStreamReader = null;
	    try {  
	    	//设置字体格式 gbk   乱码尝试别的（utf -8 等 ）
	        inputStreamReader = new InputStreamReader(inputStream, "gbk");
	    } catch (UnsupportedEncodingException e1) {
	        e1.printStackTrace();  
	    }  
	    BufferedReader reader = new BufferedReader(inputStreamReader);
	    StringBuffer sb = new StringBuffer("");
	    String line;
	    try {  
	        while ((line = reader.readLine()) != null) {  
	            //分行空格
	        	sb.append(line); 
	            sb.append("\n");  
	        }  
	    } catch (IOException e) {
	        e.printStackTrace();  
	    }  
	    //返回 string
	    return sb.toString();  
	}  
	@Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
        	MainActivity_HomePage.h=true;
            finish();
            
            return true;  
        } else  
            return super.onKeyDown(keyCode, event);  
    } 
}