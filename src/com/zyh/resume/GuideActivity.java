package com.zyh.resume;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class GuideActivity extends FragmentActivity {

	private ViewPager viewPager;
	private List<View> views;
	private SharedPreferences sp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_guide);
		sp = getSharedPreferences("config", MODE_PRIVATE);

		LinearLayout ll1 = (LinearLayout) View.inflate(this, R.layout.guide01, null);
		LinearLayout ll2 = (LinearLayout) View.inflate(this, R.layout.guide02, null);
		RelativeLayout ll3 = (RelativeLayout) View.inflate(this, R.layout.guide03, null);
		ll3.findViewById(R.id.tv_finish).setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				sp.edit().putBoolean("showguide", false).commit();
				goHomeActivity();
			}

		});;
		views = new ArrayList<>();
		views.add(ll1);
		views.add(ll2);
		views.add(ll3);

		viewPager = (ViewPager) findViewById(R.id.viewpager);
		viewPager.setAdapter(new MyPagerAdapter());
	}

	private void goHomeActivity() {
		startActivity(new Intent(this, HomeActivity.class));
		this.finish();
	}

	private class MyPagerAdapter extends PagerAdapter {

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView(views.get(position));
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			container.addView(views.get(position));
			return views.get(position);
		}

		@Override
		public int getCount() {
			return views.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

	}

}
