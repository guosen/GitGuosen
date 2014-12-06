package com.joke.android;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.joke.android.face.ICallback;
import com.joke.android.face.IUser;
import com.joke.android.face.impl.DefaultRUser;
import com.joke.android.widget.MyViewPagerAdapter;

public class NewJokeActivity extends FragmentActivity implements OnClickListener{
	private ViewPager mPager;
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
	}
	private GridView mGridView;
	private MyViewPagerAdapter mPagerAdapter;
	private LinearLayout jokeitem;
	
	private IUser user;
	private EditText editText;
	private ImageView sendjoke;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_joke_send);
		jokeitem=(LinearLayout) findViewById(R.id.jokeitem);
		mPager = (ViewPager) findViewById(R.id.pager);
		mPagerAdapter = new MyViewPagerAdapter(getSupportFragmentManager(),jokeitem);
		mPager.setAdapter(mPagerAdapter);
		initView();
	}
	
	void initView(){
	    user=new DefaultRUser(this);
		editText=(EditText) findViewById(R.id.jokecontent);
		sendjoke=(ImageView) findViewById(R.id.header_right);
		editText.setOnClickListener(this);
		sendjoke.setOnClickListener(this);
		
		editText.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				sendjoke.setImageResource(R.drawable.ic_send_btn_enabled);
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				//sendjoke.setImageResource(R.drawable.ic_send_btn_enabled);
			}
		});
		
	}
	public  static void enterActivity(Activity activity)
	{
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.jokecontent:
			
			break;
		case R.id.header_right:
			
			if(!editText.getText().toString().equals("")){
				String content=editText.getText().toString();
				
				user.AddJoke(NewJokeActivity.this, callback, content);
				
			}
			break;
		default:
			break;
		}
	}
	ICallback<String> callback=new ICallback<String>() {
		
		@Override
		public void onSucceed(String result) {
			// TODO Auto-generated method stub
			Toast.makeText(NewJokeActivity.this, "分享成功!!", Toast.LENGTH_LONG).show();
		}
		
		@Override
		public void onFail(String error) {
			// TODO Auto-generated method stub
			
		}
	};
	
	public void onBack(View view){
		
		onBackPressed();
	}

}
