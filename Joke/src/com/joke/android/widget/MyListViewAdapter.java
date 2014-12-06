package com.joke.android.widget;

import java.util.ArrayList;
import java.util.List;

import com.joke.android.R;
import com.joke.android.entity.JokeContent;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MyListViewAdapter extends BaseAdapter{

	private ArrayList<Integer> imagelistlarge;
	private List<JokeContent> data = new ArrayList<JokeContent>();
	
	private Context mContext;
	
	public MyListViewAdapter(Context context, List<JokeContent> data)
	{
		mContext = context;
		this.data = data;
		initBack();
	}
	
	public void refreshData(List<JokeContent> data)
	{
		this.data = data;
		notifyDataSetChanged();
		
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object getItem(int pos) {
		// TODO Auto-generated method stub
		return data.get(pos);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int pos, View view, ViewGroup parent) {
		// TODO Auto-generated method stub

		if (view == null)
		{
			view = LayoutInflater.from(mContext).inflate(R.layout.listview_item_layout, null);
		}
		
		TextView textView = (TextView) view.findViewById(R.id.textView);
		textView.setText(data.get(pos).getContent());
		View itembackground=view.findViewById(R.id.jokeitem);
		int resid=imagelistlarge.get(data.get(pos).getImageid());
		itembackground.setBackgroundResource(resid);
		//.setBackgroundResource(R.drawable.l_25);
		return view;
	}
	void initBack(){

	    imagelistlarge = new ArrayList<Integer>();
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
