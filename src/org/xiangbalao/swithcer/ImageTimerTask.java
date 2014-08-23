package org.xiangbalao.swithcer;

import java.util.TimerTask;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class ImageTimerTask extends TimerTask {
	int gallerypisition;
	public GuideGallery images_ga;
	public Handler autoGalleryHandler;

	public ImageTimerTask(GuideGallery images_ga, Handler autoGalleryHandler) {
		super();
		this.images_ga = images_ga;
		this.autoGalleryHandler=autoGalleryHandler;
	}

	public volatile boolean timeCondition = true;

	public void run() {
		synchronized (this) {
			while (!timeCondition) {
				try {
					Thread.sleep(100);
					wait();
				} catch (InterruptedException e) {
					Thread.interrupted();
				}
			}
		}
		try {
			gallerypisition = images_ga.getSelectedItemPosition() + 1;
			
			System.out.println(gallerypisition + "");
			
			
			
			Message msg = new Message();
			Bundle date = new Bundle();// 存放数据
			date.putInt("pos", gallerypisition);
			msg.setData(date);
			msg.what = 1;// 消息标识
			autoGalleryHandler.sendMessage(msg);
		} catch (Exception e) {

			e.printStackTrace();
		}
	}
}
