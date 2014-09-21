package com.zyh.resume.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

public class VerticalSlidingView extends ViewGroup {

	private Scroller mScroller;
	private VelocityTracker mVelocityTracker;
	private int mTotalHeight;
	private int mTolerance;
	private int mStartYPosition;
	private int mEndYPosition;
	private OnPageScrollListener mPageScrollListener;
	private int mCurrentPage = -1;

	private boolean mIsScrolling = false;

	public VerticalSlidingView(Context context) {
		super(context);
		paramsInit(context);
	}

	// 通过XMl构造使用
	public VerticalSlidingView(Context context, AttributeSet attrs) {
		super(context, attrs);
		paramsInit(context);
	}

	private void paramsInit(Context context) {
		mScroller = new Scroller(context);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		int count = getChildCount();
		int height = getMeasuredHeight();
		int top = 0;
		for (int i = 0; i < count; ++i) {
			View childView = getChildAt(i);
			if (childView.getVisibility() != View.GONE) {
				childView.layout(l, top, r, top + height);
				top += height;
			}
		}
		mTotalHeight = height * (count - 1);
		mTolerance = height / 2;
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		int count = getChildCount();
		for (int i = 0; i < count; ++i) {
			View childView = getChildAt(i);
			measureChild(childView, widthMeasureSpec, heightMeasureSpec);
		}
	}

	private int mLastY;

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		if (mVelocityTracker == null) {
			mVelocityTracker = VelocityTracker.obtain();
		}
		mVelocityTracker.addMovement(event);

		switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				if (!mScroller.isFinished()) {
					mScroller.abortAnimation();
				}
				mLastY = (int) event.getY();
				mStartYPosition = getScrollY();
				break;
			case MotionEvent.ACTION_MOVE:
				int y = (int) event.getY();
				int distance = mLastY - y;
				int scrollY = getScrollY();
				// 边界检查
				if (distance < 0 && scrollY + distance < 0) {
					distance = 0 - scrollY;
				} else if (distance > 0 && scrollY + distance > mTotalHeight) {
					distance = mTotalHeight - scrollY;
				}
				scrollBy(0, distance);
				mLastY = y;
				break;
			case MotionEvent.ACTION_UP:

				mEndYPosition = getScrollY();
				int posDiff = mEndYPosition - mStartYPosition;

				mVelocityTracker.computeCurrentVelocity(1000);
				int velocityY = (int) mVelocityTracker.getYVelocity();
				mVelocityTracker.recycle();
				mVelocityTracker = null;

				if (Math.abs(velocityY) >= 600 || Math.abs(posDiff) > mTolerance) {
					int dis = 0;
					if (posDiff > 0) {
						dis = getMeasuredHeight() - posDiff;
					} else if (posDiff < 0) {
						dis = -(getMeasuredHeight() + posDiff);
					}
					mScroller.startScroll(0, 0, 0, dis);
				} else {
					mScroller.startScroll(0, 0, 0, -posDiff);
				}

				postInvalidate();
				break;
			default:
				break;
		}

		return true;
	}

	@Override
	public void computeScroll() {
		if (mScroller.computeScrollOffset()) {
			int scroll = mScroller.getCurrY();
			if (scroll > 0 && mEndYPosition + scroll > mTotalHeight) {
				scroll = mTotalHeight - mEndYPosition;
			} else if (scroll < 0 && mEndYPosition + scroll < 0) {
				scroll = -mEndYPosition;
			}
			scrollTo(0, mEndYPosition + scroll);
			mIsScrolling = true;
			postInvalidate();
		} else if (mIsScrolling) {
			if (mPageScrollListener != null) {
				int position = getScrollY() / getMeasuredHeight();
				if (position != mCurrentPage) {
					mCurrentPage = position;
					mPageScrollListener.onPageChanged(mCurrentPage);
				}
			}
			mIsScrolling = false;
		}
	}

	public void setOnPageScrollListener(OnPageScrollListener listener) {
		mPageScrollListener = listener;
	}

	public interface OnPageScrollListener {
		public void onPageChanged(int position);
	}

}
