package com.zyh.resume;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;

/**
 * 主界面
 * @author zyh
 */
public class HomeActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		init();
	}

	private ListView lv;
	private MyAdapter adapter;
	private List<View> views;

	private ImageView iv_photo;
	private RotateAnimation ra;

	private void init() {
		initDate();

		// 旋转动画
		ra = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		ra.setDuration(10000);
		ra.setFillEnabled(true);
		ra.setRepeatCount(Integer.MAX_VALUE);
		ra.setFillAfter(true);
		ra.setInterpolator(this, android.R.anim.linear_interpolator);
		// 为头像设置旋转
		iv_photo = (ImageView) findViewById(R.id.iv_photo);
		iv_photo.startAnimation(ra);
		iv_photo.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
					case MotionEvent.ACTION_DOWN:
						if (ra != null) iv_photo.clearAnimation();
						break;
					case MotionEvent.ACTION_UP:
						if (ra != null) iv_photo.startAnimation(ra);
						break;
				}
				return true;
			}
		});
		lv = (ListView) findViewById(R.id.lv);
		adapter = new MyAdapter();
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Animation shake = AnimationUtils.loadAnimation(HomeActivity.this, R.anim.shake);
				view.startAnimation(shake);
			}
		});
	}

	private void initDate() {
		views = new ArrayList<>();
		View base = View.inflate(this, R.layout.item_base, null);
		views.add(base);

		View purpose = View.inflate(this, R.layout.item_purpose, null);
		views.add(purpose);
		View skill = View.inflate(this, R.layout.item_skill, null);
		views.add(skill);
		View project = View.inflate(this, R.layout.item_project, null);
		project.findViewById(R.id.ll_p1).setOnClickListener(this);
		project.findViewById(R.id.ll_p2).setOnClickListener(this);
		views.add(project);

		View foot = View.inflate(this, R.layout.item_foot, null);
		views.add(foot);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.ll_p1:
				startActivity(new Intent(this, Project1Activity.class));
				break;
			case R.id.ll_p2:
				startActivity(new Intent(this, Project1Activity.class));
				break;
			default:
				break;
		}
	}

	class MyAdapter extends BaseAdapter {
		public int getCount() {
			return views.size();
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			convertView = views.get(position);
			return convertView;
		}

		public Object getItem(int position) {
			return null;
		}

		public long getItemId(int position) {
			return 0;
		}
	}
}
