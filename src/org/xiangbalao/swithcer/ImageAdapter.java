package org.xiangbalao.swithcer;

import java.util.List;

import org.xiangbalao.MainActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

import com.example.auto.R;

public class ImageAdapter extends BaseAdapter {
	private List<String> imageUrls; // 图片地址list
	private Context context;
	private ImageAdapter self;
	Uri uri;
	Intent intent;
	ImageView imageView;
	public static Integer[] imgs = { R.drawable.one, R.drawable.two,
			R.drawable.three, R.drawable.four };

	/**
	 * 自动轮播适配器
	 * 
	 * @param context
	 */
	public ImageAdapter(/* List<String> imageUrls, */Context context) {
		// this.imageUrls = imageUrls;
		this.context = context;
		this.self = this;
	}

	public int getCount() {
		return Integer.MAX_VALUE;
	}

	public Object getItem(int position) {
		return imageUrls.get(position % imgs.length);
	}

	public long getItemId(int position) {
		return position;
	}

	@SuppressWarnings("unused")
	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			try {
				switch (msg.what) {
				case 0: {
					self.notifyDataSetChanged();
				}
					break;
				}

				super.handleMessage(msg);
			} catch (Exception e) {
			}
		}
	};

	public View getView(int position, View convertView, ViewGroup parent) {

		// Bitmap image;
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(R.layout.item,
					null); // 实例化convertView
			Gallery.LayoutParams params = new Gallery.LayoutParams(
					Gallery.LayoutParams.WRAP_CONTENT,
					Gallery.LayoutParams.WRAP_CONTENT);
			convertView.setLayoutParams(params);
			// image =
			// ((ImageActivity)context).imagesCache.get(imageUrls.get(position %
			// imageUrls.size())); //从缓存中读取图片
			/*
			 * if(image==null){ //当缓存中没有要使用的图片时，先显示默认的图片 image =
			 * ((ImageActivity)context).imagesCache.get("background_non_load");
			 * //异步加载图片 LoadImageTask task = new LoadImageTask(convertView);
			 * task.execute(imageUrls.get(position % imageUrls.size())); }
			 */
			convertView.setTag(imgs);

		} else {
			// image = (Bitmap) convertView.getTag();
		}
		// TextView textView = (TextView)
		// convertView.findViewById(R.id.gallery_text);
		// textView.setText(position % imgs.length+" sdfsdfdsfdf");
		// textView.setBackgroundColor(Color.argb(200, 135, 135, 152));

		imageView = (ImageView) convertView.findViewById(R.id.gallery_image);
		imageView.setImageResource(imgs[position % imgs.length]);
		// 设置缩放比例：保持原样
		imageView.setScaleType(ImageView.ScaleType.FIT_XY);
		// textView.setText(position % imgs.length+" sdfsdfdsfdf");

		
		//TODO  
		((MainActivity) context).changePointView(position % imgs.length);

		/*
		 * imageView.setOnClickListener(new OnClickListener() {
		 * 
		 * @Override public void onClick(View v) { // TODO Auto-generated method
		 * stub
		 * 
		 * //((String) imageView).setSpan(new URLSpan("http://www.36939.net/"),
		 * 13, 15,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		 * 
		 * 
		 * } });
		 */
		return convertView;

	}

}
