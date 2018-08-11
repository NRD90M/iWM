package top.nrd90m.iwm.circle;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TableLayout;

import top.nrd90m.iwm.R;
import top.nrd90m.iwm.circle.PersonalScrollView.onTurnListener;
import top.nrd90m.iwm.circle.RotateAnimation.InterpolatedTimeListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CircleActivity extends Activity implements
		InterpolatedTimeListener, onTurnListener {
	private ImageView iv_personal_bg;
	private ImageView image_header;
	private PersonalScrollView personalScrollView;
	private View line_up;

	private TableLayout tl_main;
	private int current_id;

	private int drawable_id[] = {
			R.mipmap.cat_one,
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
		map.put("title", "我们在茫茫人海相遇，感觉是巧合，更相信是命中注定。");// 文字
		map.put("years", "2018.5.4");// 时间
		map.put("imgs", R.mipmap.circle_avatar_one);// 图片
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("title", "意料之外的，今天你答应做我女朋友，我好开心。");
		map.put("years", "2018.5.14");
		map.put("imgs", R.mipmap.circle_avatar_two);
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("title", "小可爱高考了，就像我也在参加高考一样，希望我们超常发挥，在青岛上学。");
		map.put("years", "2018.6.7");
		map.put("imgs", R.mipmap.circle_avatar_three);
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("title", "高考成绩出来了，第二天凑巧我帮你查出来了，分数还算可以，你开心的亲了我一口，我也很开心，但是还夹杂一丝失落。");
		map.put("years", "2018.6.24");
		map.put("imgs", R.mipmap.circle_avatar_four);
		list.add(map);
		map = new HashMap<String, Object>();
		map.put("title", "老婆给我过生日买了条超好看的裤子，并且说了好多爱我的话，超开心的一个生日！");
		map.put("years", "2018.6.28");
		map.put("imgs", R.mipmap.circle_avatar_five);
		list.add(map);
		map = new HashMap<String, Object>();
		map.put("title", "二表没录上，和你打电话总听你和妈妈在吵，我也不想给你压力了，我多想让你来我身边。");
		map.put("years", "2018.7.31");
		map.put("imgs", R.mipmap.circle_avatar_six);
		list.add(map);
		map = new HashMap<String, Object>();
		map.put("title", "我们狠狠吵了一架，我爱你，所以我不会放手,也感谢老婆的宽容，我希望我们都能为彼此改改，么么哒，老婆。");
		map.put("years", "2018.8.4");
		map.put("imgs", R.mipmap.circle_avatar_seven);
		list.add(map);
		return list;
		/*
		 * ps： 若不想设置图片， 把整 map.put("imgs", R.mipmap.bg);去掉 ， 并在adapter加个判断
		 * 为空的时候 把那条item的img隐藏掉！！
		 */
	}

}
