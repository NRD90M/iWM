package top.nrd90m.iwm.gb;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TableLayout;

import top.nrd90m.iwm.R;
import top.nrd90m.iwm.gb.PersonalScrollView.onTurnListener;
import top.nrd90m.iwm.gb.RotateAnimation.InterpolatedTimeListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity_Gb extends Activity implements
		InterpolatedTimeListener, onTurnListener {
	private ImageView iv_personal_bg;
	private ImageView image_header;
	private PersonalScrollView personalScrollView;
	private View line_up;

	private TableLayout tl_main;
	private int current_id;

	private int drawable_id[] = { R.mipmap.cat_one,
			R.mipmap.cat_two,
			R.mipmap.cat_three };
	private ListView listView;
	List<String> data;
	private TimelineAdapter timelineAdapter;

	protected void initView() {
		setContentView(R.layout.circle_main);
		personalScrollView = (PersonalScrollView) findViewById(R.id.personalScrollView);
		iv_personal_bg = (ImageView) findViewById(R.id.iv_personal_bg);
		line_up = (View) findViewById(R.id.line_up);
		image_header = (ImageView) findViewById(R.id.image_header);
		personalScrollView.setTurnListener(this);

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initView();
		personalScrollView.setImageView(iv_personal_bg);
		personalScrollView.setLine_up(line_up);

		listView = (ListView) this.findViewById(R.id.listview);

		timelineAdapter = new TimelineAdapter(this, getData());
		listView.setAdapter(timelineAdapter);

	}

	@Override
	public void onTurn() {
		RotateAnimation animation = new RotateAnimation();
		animation.setFillAfter(true);
		animation.setInterpolatedTimeListener(this);
		image_header.startAnimation(animation);
		current_id = current_id < drawable_id.length - 1 ? ++current_id : 0;
	}

	@Override
	public void interpolatedTime(float interpolatedTime) {
		if (interpolatedTime > 0.5f) {
			Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
					drawable_id[current_id]);
			image_header.setImageBitmap(bitmap);
		}
	}

	private List<Map<String, Object>> getData() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("title", "这一天之前也许也许你我都很陌生");// 文字
		map.put("years", "2014.1.28");// 时间
		map.put("imgs", R.mipmap.circle_avatar);// 图片
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("title", "那他你消失了2消失，而我度过了2年");
		map.put("years", "2014.4.28");
		map.put("imgs", R.mipmap.circle_avatar);
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("title", "你说我唱歌不好听， 也就你能听听了。我笑得多和群");
		map.put("years", "2014.6.28");
		map.put("imgs", R.mipmap.circle_avatar);
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("title", "你生气的时候是丑的，可惜没有如果");
		map.put("years", "2014.9.28");
		map.put("imgs", R.mipmap.circle_avatar);
		list.add(map);
		map = new HashMap<String, Object>();
		map.put("title", "你说南国没有雪，我在雪地里站了2小时");
		map.put("years", "2014.11.28");
		map.put("imgs", R.mipmap.circle_avatar);
		list.add(map);
		map = new HashMap<String, Object>();
		map.put("title", "看那雪地上留下的2行脚印，今天南北国都在下雪");
		map.put("years", "2015.1.28");
		map.put("imgs", R.mipmap.circle_avatar);
		list.add(map);
		return list;
		/*
		 * ps： 若不想设置图片， 把整 map.put("imgs", R.mipmap.bg);去掉 ， 并在adapter加个判断
		 * 为空的时候 把那条item的img隐藏掉！！
		 */
	}

}
