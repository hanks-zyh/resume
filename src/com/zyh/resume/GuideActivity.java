package com.zyh.resume;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.zyh.resume.ui.VerticalSlidingView;
import com.zyh.resume.ui.VerticalSlidingView.OnPageScrollListener;

public class GuideActivity extends FragmentActivity {

	private SharedPreferences sp;

	private VerticalSlidingView mSlideView;
	private View[] mViews = new View[4];

	private boolean mIsFirst = true;

	private ImageView mT1TitleImage;
	private ImageView mT1TimeImage;
	private ImageView mT1BatteryImage;
	private ImageView mT1NextImage;

	private ImageView mT2TitleImage;
	private ImageView mT2ContentImage;
	private ImageView mT2NextImage;

	private ImageView mT3TitleImage;
	private ImageView mT3RocketImage;
	private ImageView mT3Cloud1;
	private ImageView mT3Cloud2;
	private ImageView mT3Cloud3;
	private ImageView mT3Cloud4;
	private ImageView mT3NextImage;

	private ImageView mT4PanelImage;
	private ImageView mT4ContentImage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_guide);
		sp = getSharedPreferences("config", MODE_PRIVATE);
		// 竖直滑动布局
		mSlideView = (VerticalSlidingView) findViewById(R.id.sliding_view);
		mSlideView.setOnPageScrollListener(new MyPageScrollListener());
		initViews();
	}

	class MyPageScrollListener implements OnPageScrollListener {
		@Override
		public void onPageChanged(int position) {
			switch (position) {
				case 0:
					layout1AnimStart();
					break;
				case 1:
					layout2AnimStart();
					break;
				case 2:
					layout3AnimStart();
					break;
				case 3:
					layout4AnimStart();
					break;
			}
		}

	}

	private void goHomeActivity() {
		startActivity(new Intent(this, HomeActivity.class));
		this.finish();
	}

	private void layout1AnimStart() {
		mT1TitleImage.setVisibility(View.VISIBLE);
		mT1BatteryImage.setVisibility(View.VISIBLE);
		mT1TimeImage.setVisibility(View.VISIBLE);
		mT1TitleImage.startAnimation(AnimationUtils.loadAnimation(this, R.anim.title_scale_anim));
		((AnimationDrawable) mT1TimeImage.getBackground()).start();
		mT1BatteryImage.startAnimation(AnimationUtils.loadAnimation(this, R.anim.t1_battery_anim));
		mT1NextImage.startAnimation(AnimationUtils.loadAnimation(this, R.anim.next_anim));
		clearLayout2Anim();
	}

	private void layout2AnimStart() {

		mT2TitleImage.setVisibility(View.VISIBLE);
		mT2ContentImage.setVisibility(View.VISIBLE);

		mT2TitleImage.startAnimation(AnimationUtils.loadAnimation(this, R.anim.title_scale_anim));
		mT2ContentImage.startAnimation(AnimationUtils.loadAnimation(this, R.anim.t2_content_anim));
		mT2NextImage.startAnimation(AnimationUtils.loadAnimation(this, R.anim.next_anim));

		clearLayout1Anim();
		clearLayout3Anim();
	}

	private void layout3AnimStart() {
		mT3TitleImage.setVisibility(View.VISIBLE);
		mT3Cloud1.setVisibility(View.VISIBLE);
		mT3Cloud2.setVisibility(View.VISIBLE);
		mT3Cloud3.setVisibility(View.VISIBLE);
		mT3Cloud4.setVisibility(View.VISIBLE);

		mT3TitleImage.startAnimation(AnimationUtils.loadAnimation(this, R.anim.title_scale_anim));
		((AnimationDrawable) mT3RocketImage.getBackground()).start();
		mT3Cloud1.startAnimation(AnimationUtils.loadAnimation(this, R.anim.t3_translate1));
		mT3Cloud2.startAnimation(AnimationUtils.loadAnimation(this, R.anim.t3_translate2));
		mT3Cloud3.startAnimation(AnimationUtils.loadAnimation(this, R.anim.t3_translate3));
		mT3Cloud4.startAnimation(AnimationUtils.loadAnimation(this, R.anim.t3_translate4));
		mT3NextImage.startAnimation(AnimationUtils.loadAnimation(this, R.anim.next_anim));

		clearLayout2Anim();
		clearLayout4Anim();

	}

	private void layout4AnimStart() {
		mT4PanelImage.setVisibility(View.VISIBLE);
		mT4ContentImage.setVisibility(View.VISIBLE);

		mT4PanelImage.startAnimation(AnimationUtils.loadAnimation(this, R.anim.t4_panel_anim));
		mT4ContentImage.startAnimation(AnimationUtils.loadAnimation(this, R.anim.title_scale_anim));

		clearLayout3Anim();
	}

	private void clearLayout1Anim() {
		mT1TitleImage.clearAnimation();
		mT1TitleImage.setVisibility(View.INVISIBLE);
		mT1BatteryImage.clearAnimation();
		mT1BatteryImage.setVisibility(View.INVISIBLE);
		mT1TimeImage.setVisibility(View.INVISIBLE);
	}

	private void clearLayout2Anim() {
		mT2TitleImage.clearAnimation();
		mT2TitleImage.setVisibility(View.INVISIBLE);
		mT2ContentImage.clearAnimation();
		mT2ContentImage.setVisibility(View.INVISIBLE);
	}

	private void clearLayout3Anim() {
		mT3TitleImage.clearAnimation();
		mT3TitleImage.setVisibility(View.INVISIBLE);
		mT3Cloud1.clearAnimation();
		mT3Cloud2.clearAnimation();
		mT3Cloud3.clearAnimation();
		mT3Cloud4.clearAnimation();
		mT3Cloud1.setVisibility(View.INVISIBLE);
		mT3Cloud2.setVisibility(View.INVISIBLE);
		mT3Cloud3.setVisibility(View.INVISIBLE);
		mT3Cloud4.setVisibility(View.INVISIBLE);
		((AnimationDrawable) mT3RocketImage.getBackground()).stop();
	}

	private void clearLayout4Anim() {
		mT4PanelImage.clearAnimation();
		mT4ContentImage.clearAnimation();
		mT4ContentImage.setVisibility(View.INVISIBLE);
	}

	private void initViews() {
		LayoutInflater inflater = LayoutInflater.from(this);
		View layout1 = inflater.inflate(R.layout.tutorial_1_layout, null);
		mViews[0] = layout1;
		mT1TitleImage = (ImageView) layout1.findViewById(R.id.t1_fixed);
		mT1TimeImage = (ImageView) layout1.findViewById(R.id.t1_icon1);
		mT1BatteryImage = (ImageView) layout1.findViewById(R.id.t1_icon2);
		mT1NextImage = (ImageView) layout1.findViewById(R.id.t1_next);

		View layout2 = inflater.inflate(R.layout.tutorial_2_layout, null);
		mViews[1] = layout2;
		mT2TitleImage = (ImageView) layout2.findViewById(R.id.t2_fixed);
		mT2ContentImage = (ImageView) layout2.findViewById(R.id.t2_icon1);
		mT2NextImage = (ImageView) layout2.findViewById(R.id.t2_next);

		View layout3 = inflater.inflate(R.layout.tutorial_3_layout, null);
		mViews[2] = layout3;
		mT3TitleImage = (ImageView) layout3.findViewById(R.id.t3_icon1);
		mT3RocketImage = (ImageView) layout3.findViewById(R.id.t3_icon6);
		mT3Cloud1 = (ImageView) layout3.findViewById(R.id.t3_icon2);
		mT3Cloud2 = (ImageView) layout3.findViewById(R.id.t3_icon3);
		mT3Cloud3 = (ImageView) layout3.findViewById(R.id.t3_icon4);
		mT3Cloud4 = (ImageView) layout3.findViewById(R.id.t3_icon5);
		mT3NextImage = (ImageView) layout3.findViewById(R.id.t3_next);

		View layout4 = inflater.inflate(R.layout.tutorial_4_layout, null);
		mViews[3] = layout4;
		mT4PanelImage = (ImageView) layout4.findViewById(R.id.t4_icon1);
		mT4ContentImage = (ImageView) layout4.findViewById(R.id.t4_icon2);
		layout4.findViewById(R.id.t4_start).setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				sp.edit().putBoolean("showguide", false).commit();
				goHomeActivity();
			}
		});;
		ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
		mSlideView.addView(mViews[0], lp);
		mSlideView.addView(mViews[1], lp);
		mSlideView.addView(mViews[2], lp);
		mSlideView.addView(mViews[3], lp);

	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
		if (mIsFirst) {
			layout1AnimStart();
			mIsFirst = false;
		}
	}

}
