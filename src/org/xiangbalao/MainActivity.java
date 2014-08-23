package org.xiangbalao;

import org.xiangbalao.swithcer.AutoSwithcerManager;
import org.xiangbalao.swithcer.GuideGallery;
import org.xiangbalao.swithcer.ImageAdapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.auto.R;

public class MainActivity extends Activity {

	Handler autoGalleryHandler = new Handler() {
		public void handleMessage(Message message) {
			super.handleMessage(message);
			switch (message.what) {
			case 1:
				AutoSwithcerManager.mGuideGallery.setSelection(message
						.getData().getInt("pos"));
				break;
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.url_connection_image);

		// 第一步 初始化
		initSwithcer();
		AutoSwithcerManager.startTimeTaks(autoGalleryHandler);

	}

	
	
	/**
	 * 
	 * 初始化
	 */
	private void initSwithcer() {

		AutoSwithcerManager.mGuideGallery = (GuideGallery) findViewById(R.id.image_wall_gallery);
		ImageAdapter imageAdapter = new ImageAdapter(this);
		AutoSwithcerManager.mGuideGallery.setAdapter(imageAdapter);

		LinearLayout pointLinear = (LinearLayout) findViewById(R.id.gallery_point_linear);
		pointLinear.setBackgroundColor(Color.argb(200, 135, 135, 152));

		for (int i = 0; i < 4; i++) {
			ImageView pointView = new ImageView(this);
			if (i == 0) {
				pointView.setBackgroundResource(R.drawable.feature_point_cur);
			} else
				pointView.setBackgroundResource(R.drawable.feature_point);
			pointLinear.addView(pointView);
		}

		AutoSwithcerManager.mGuideGallery
				.setOnItemClickListener(new OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {

						Uri uri;
						Intent intent;

						switch (arg2 % ImageAdapter.imgs.length) {
						case 0:
							uri = Uri.parse("http://www.36939.net/");
							intent = new Intent(Intent.ACTION_VIEW, uri);
							startActivity(intent);

							break;

						default:
							break;
						}

					}
				});

	}

	public void changePointView(int cur_position) {
		LinearLayout pointLinear = (LinearLayout) findViewById(R.id.gallery_point_linear);
		View view = pointLinear.getChildAt(AutoSwithcerManager.positon);
		View curView = pointLinear.getChildAt(cur_position);
		if (view != null && curView != null) {
			ImageView pointView = (ImageView) view;
			ImageView curPointView = (ImageView) curView;
			pointView.setBackgroundResource(R.drawable.feature_point);
			curPointView.setBackgroundResource(R.drawable.feature_point_cur);
			AutoSwithcerManager.positon = cur_position;
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		AutoSwithcerManager.timeFlag = false;
	}

	@Override
	protected void onPause() {
		super.onPause();
		AutoSwithcerManager.timeTaks.timeCondition = false;
	}

}
