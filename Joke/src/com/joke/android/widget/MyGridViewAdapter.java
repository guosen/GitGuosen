package com.joke.android.widget;

import java.util.ArrayList;
import com.joke.android.R;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MyGridViewAdapter extends BaseAdapter {
	private ArrayList<Integer> imagelist;
	private ArrayList<Integer> imagelistlarge;//用来存储大张图片
	private int index;//用来表示是第几页的图片
	private Activity context;
	private LinearLayout sendview;
	LinearLayout view;
	public MyGridViewAdapter(Activity activity,ArrayList<Integer> list,LinearLayout view,int index){
		imagelist=list;
		context=activity;
		this.view=view;
		this.index=index;
		
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return imagelist.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return imagelist.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if(convertView==null)
		{
			convertView=LayoutInflater.from(context).inflate(R.layout.simple_grid_item,null);
		}
		ImageView imageview=(ImageView) convertView.findViewById(R.id.imageview);
		imageview.setImageResource(imagelist.get(position));
		imageview.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				setImagelistlarge();
				view.setBackgroundResource(imagelistlarge.get(position));
			}
		});
		return convertView;
	}
	private void setImagelistlarge(){
		if(index==0){
		imagelistlarge=new ArrayList<Integer>();
		imagelistlarge.add(R.drawable.l_1);
		imagelistlarge.add(R.drawable.l_2);
		imagelistlarge.add(R.drawable.l_3);
		imagelistlarge.add(R.drawable.l_4);
		imagelistlarge.add(R.drawable.l_5);
		imagelistlarge.add(R.drawable.l_6);
		imagelistlarge.add(R.drawable.l_7);
		imagelistlarge.add(R.drawable.l_8);
		imagelistlarge.add(R.drawable.l_9);
		imagelistlarge.add(R.drawable.l_10);
		}else if(index==1){
		imagelistlarge=new ArrayList<Integer>();
		imagelistlarge.add(R.drawable.l_11);
		imagelistlarge.add(R.drawable.l_12);
		imagelistlarge.add(R.drawable.l_13);
		imagelistlarge.add(R.drawable.l_14);
		imagelistlarge.add(R.drawable.l_15);
		imagelistlarge.add(R.drawable.l_16);
		imagelistlarge.add(R.drawable.l_17);
		imagelistlarge.add(R.drawable.l_18);
		imagelistlarge.add(R.drawable.l_19);
		imagelistlarge.add(R.drawable.l_20);
		}else if(index==2){
		imagelistlarge=new ArrayList<Integer>();
		imagelistlarge.add(R.drawable.l_21);
		imagelistlarge.add(R.drawable.l_22);
		imagelistlarge.add(R.drawable.l_23);
		imagelistlarge.add(R.drawable.l_24);
		imagelistlarge.add(R.drawable.l_25);
		imagelistlarge.add(R.drawable.l_26);
		imagelistlarge.add(R.drawable.l_27);
		imagelistlarge.add(R.drawable.l_28);
		imagelistlarge.add(R.drawable.l_29);
		imagelistlarge.add(R.drawable.l_30);
		}
	}

}
