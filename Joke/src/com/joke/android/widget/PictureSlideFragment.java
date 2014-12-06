package com.joke.android.widget;

import java.util.ArrayList;
import java.util.HashMap;

import com.joke.android.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;

public class PictureSlideFragment extends Fragment {
	private static GridView MyGridview;
	
	private static LinearLayout sendview;
	private int mIndex;//索引
	 @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	            Bundle savedInstanceState) {
	        
	        View v = inflater.inflate(R.layout.fragment_picture_slide, container, false);
	        MyGridview=(GridView) v.findViewById(R.id.MyGridview);
	        ArrayList<HashMap<String,Object>> imagelist = new ArrayList<HashMap<String,Object>>();
	        HashMap<String,Object> map1 = new HashMap<String,Object>();
	        map1.put("image", R.drawable.s_1);
	        imagelist.add(map1);
	        
	        
	        HashMap<String,Object> map2 = new HashMap<String,Object>();
	        map2.put("image", R.drawable.s_2);
	        imagelist.add(map2);
	        
	        
	        
	        HashMap<String,Object> map3 = new HashMap<String,Object>();
	        map3.put("image", R.drawable.s_3);
	        imagelist.add(map3);
	        
	        ArrayList<Integer>list=new ArrayList<Integer>();
	        list.add(R.drawable.s_1);
	        list.add(R.drawable.s_2);
	        list.add(R.drawable.s_3);
	        list.add(R.drawable.s_4);
	        list.add(R.drawable.s_5);
	        list.add(R.drawable.s_6);
	        list.add(R.drawable.s_7);
	        list.add(R.drawable.s_8);
	        list.add(R.drawable.s_9);
	        list.add(R.drawable.s_10);
	        
	        ArrayList<Integer>list2=new ArrayList<Integer>();
	        list2.add(R.drawable.s_11);
	        list2.add(R.drawable.s_12);
	        list2.add(R.drawable.s_13);
	        list2.add(R.drawable.s_14);
	        list2.add(R.drawable.s_15);
	        list2.add(R.drawable.s_16);
	        list2.add(R.drawable.s_17);
	        list2.add(R.drawable.s_18);
	        list2.add(R.drawable.s_19);
	        list2.add(R.drawable.s_20);
	        
	        ArrayList<Integer>list3=new ArrayList<Integer>();
	        list3.add(R.drawable.s_21);
	        list3.add(R.drawable.s_22);
	        list3.add(R.drawable.s_23);
	        list3.add(R.drawable.s_24);
	        list3.add(R.drawable.s_25);
	        list3.add(R.drawable.s_26);
	        list3.add(R.drawable.s_27);
	        list3.add(R.drawable.s_28);
	        list3.add(R.drawable.s_29);
	        list3.add(R.drawable.s_30);
	        
	        
	        ArrayList<Integer>listpage=new ArrayList<Integer>();
	        if(mIndex==0)listpage=list;
	        else if (mIndex==1)listpage=list2;
	        else if (mIndex==2)listpage=list3;
	        MyGridViewAdapter aapter=new MyGridViewAdapter(getActivity(), listpage,sendview,mIndex);
	        SimpleAdapter simpleAdapter = new SimpleAdapter(getActivity(), imagelist, R.layout.simple_grid_item,new String[] {"image"}, new int[]{R.id.imageview});
	        MyGridview.setAdapter(aapter);
	        
	        
	        return v;
	    }
	 public static PictureSlideFragment newInstance(int index,LinearLayout view) {
	        PictureSlideFragment f = new PictureSlideFragment();
	        sendview=view;
	        Bundle args = new Bundle();
	        args.putInt("index", index);
	        f.setArguments(args);
	        return f;
	    }
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		 mIndex = getArguments() != null ? getArguments().getInt("index") : 1;
	}
}
