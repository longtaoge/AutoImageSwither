package org.xiangbalao.swithcer;

import java.util.TimerTask;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.Gallery;



/**
 * 轮播控件
 * 
 * @author longtaoger
 * 
 */
public class GuideGallery extends Gallery {

	// private ImageActivity m_iact;

	public GuideGallery(Context context) {
		super(context);

	}

	public GuideGallery(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public GuideGallery(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}



	/**
	 * e1 开始向量，就是手指第一次触摸点的位置 e2 最后一次手指触摸点的位置， 结束的向量 velocityX
	 * 测量fling沿X轴上的速度，像素/每秒
	 * 
	 * 　　velocityY 测量fling沿Y轴上的速度，像素/每秒
	 */
	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		int kEvent;
		if (isScrollingLeft(e1, e2)) { // Check if scrolling left
			kEvent = KeyEvent.KEYCODE_DPAD_LEFT;
			System.out.println("AAAA" + this.getSelectedItemPosition());
		} else { // Otherwise scrolling right
			kEvent = KeyEvent.KEYCODE_DPAD_RIGHT;
			System.out.println("BBB" + this.getSelectedItemPosition());
		}
		onKeyDown(kEvent, null);
		if (this.getSelectedItemPosition() == 0)
			this.setSelection(ImageAdapter.imgs.length);
		System.out.println("DDD" + this.getSelectedItemPosition());
		new java.util.Timer().schedule(new TimerTask() {
			public void run() {
				AutoSwithcerManager.timeFlag = false;
				this.cancel();
			}
		}, 2000);
		return true;

	}

	private boolean isScrollingLeft(MotionEvent e1, MotionEvent e2) {
		System.out.println(this.getSelectedItemPosition());
		return e2.getX() > e1.getX();

	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {

		AutoSwithcerManager.timeTaks.timeCondition = false;

		return super.onScroll(e1, e2, distanceX, distanceY);
	}

}
