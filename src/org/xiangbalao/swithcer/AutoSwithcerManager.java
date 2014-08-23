package org.xiangbalao.swithcer;

import java.util.Timer;

import android.os.Handler;

public class AutoSwithcerManager {

	// private Context mContext;
	// private View laoutView;

	public static boolean timeFlag = true;
	// 退出轮播标志位
	public static boolean isExit = false;
	public static Thread timeThread = null;

	/**
	 * 圆点位置
	 */
	public static int positon = 0;

	public static GuideGallery mGuideGallery;

	public static AutoSwithcerManager mAutoSwithcerManager;

	public static ImageTimerTask timeTaks = null;

	/**************************************/

	public static AutoSwithcerManager getInstance() {

		if (mAutoSwithcerManager == null) {

			synchronized (AutoSwithcerManager.class) {

				if (mAutoSwithcerManager == null) {

					mAutoSwithcerManager = new AutoSwithcerManager();

				}

			}

		}
		return mAutoSwithcerManager;

	}

	/**************************************/

	public static void startTimeTaks(Handler mHandler) {
		// 第二步 新建定时器
		AutoSwithcerManager.timeTaks = new ImageTimerTask(
				AutoSwithcerManager.mGuideGallery, mHandler);
		Timer autoGallery = new Timer();
		autoGallery.scheduleAtFixedRate(AutoSwithcerManager.timeTaks, 3000,
				3000);

		// 第三步，开启线程
		AutoSwithcerManager.timeThread = new Thread() {
			public void run() {
				while (!AutoSwithcerManager.isExit) {
					try {
						Thread.sleep(1500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					synchronized (AutoSwithcerManager.timeTaks) {
						if (!AutoSwithcerManager.timeFlag) {
							AutoSwithcerManager.timeTaks.timeCondition = true;
							AutoSwithcerManager.timeTaks.notifyAll();
						}
					}
					AutoSwithcerManager.timeFlag = true;
				}
			};
		};
		AutoSwithcerManager.timeThread.start();
	}

}
