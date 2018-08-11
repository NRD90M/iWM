package top.nrd90m.iwm.gallery;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import top.nrd90m.iwm.main.MainActivity;
import top.nrd90m.iwm.R;
import top.nrd90m.iwm.gallery.GalleryAdapter.OnItemClickLitener;
import top.nrd90m.iwm.gallery.MyRecycler.OnItemScrollChangeListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GalleryActivity extends Activity
{

	private MyRecycler mRecyclerView;
	private GalleryAdapter mAdapter;
	private List<Integer> mDatas;
	private List<String> mContent;
	private ImageView mImg ;
	private TextView mText;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.gallery_main);
		mText=(TextView) findViewById(R.id.text_content);
		mImg = (ImageView) findViewById(R.id.id_content);
		mDatas = new ArrayList<Integer>(Arrays.asList(
				R.mipmap.lover_one,
				R.mipmap.lover_two,
				R.mipmap.lover_three,
				R.mipmap.lover_four,
				R.mipmap.lover_five,
				R.mipmap.lover_six,
				R.mipmap.lover_seven,
				R.mipmap.lover_eight,
				R.mipmap.lover_nine));
		mContent=new ArrayList<String>(Arrays.asList(
				"第一次见你，我说，一眼万年。",
				"小可爱在教室为我照的第一张照片，用它做了好长时间的聊天背景呢。",
				"这是我可爱的大兔兔。",
				"本想发朋友圈的，喜欢这非主流的可爱老婆。",
				"老婆的小白兔大了呢，以后老公多喂喂它。",
				"媳妇儿很乖，出去吃饭也想着老公。",
				"丹顶鹤看一眼就够了，但是你我百看不厌。",
				"没B612的媳妇儿，依旧很迷人。",
				"老婆，你口活一定很棒，我捡到宝了！"));
		mRecyclerView = (MyRecycler) findViewById(R.id.recyclerview_horizontal);
		LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
		linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

		mRecyclerView.setLayoutManager(linearLayoutManager);
		mAdapter = new GalleryAdapter(this, mDatas);
		mRecyclerView.setAdapter(mAdapter);
		//滚动
		mRecyclerView.setOnItemScrollChangeListener(new OnItemScrollChangeListener()
		{
			@Override
			public void onChange(View view, int position)
			{
				mImg.setImageResource(mDatas.get(position));
				mText.setText(mContent.get(position));
			}
		});
		//点击
		mAdapter.setOnItemClickLitener(new OnItemClickLitener()
		{
			@Override
			public void onItemClick(View view, int position)
			{
				mImg.setImageResource(mDatas.get(position));
				mText.setText(mContent.get(position));
			}
		});

	}
	//重写返回键
	@Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
        	MainActivity.three=true;
            finish();
            return true;  
        } else  
            return super.onKeyDown(keyCode, event);  
    } 
}
