package com.joke.android.widget;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;

public class MyViewPagerAdapter extends FragmentStatePagerAdapter {

	private static final int NUM_PIC = 3;
    
	private  LinearLayout view;
	public MyViewPagerAdapter(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
	}
	public MyViewPagerAdapter(FragmentManager fm,LinearLayout view) {
		super(fm);
		this.view=view;
		// TODO Auto-generated constructor stub
	}
	@Override
	public Fragment getItem(int arg0) {
		// TODO Auto-generated method stub
		 /*return new PictureSlideFragment();*/
		 return  PictureSlideFragment.newInstance(arg0,view);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return NUM_PIC;
	}


}
