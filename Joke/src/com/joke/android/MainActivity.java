package com.joke.android;

import com.joke.android.face.ICallback;
import com.joke.android.face.IUser;
import com.joke.android.face.impl.DefaultRUser;
import com.joke.android.util.QiNiu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ImageView;

public class MainActivity extends Activity {
    private ImageView jokeimg;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		/*IUser user=new DefaultRUser(this);
		jokeimg=(ImageView) findViewById(R.id.jokeimg);
		String downUrl = "http://" + "jokeapp.qiniudn.com" + "/" +"l_8.jpg";
		QiNiu.getPic(downUrl, jokeimg);
		user.userLogin(MainActivity.this, usercallback,"lin","asas");*/
		
		
		
	}

	ICallback<String> usercallback=new ICallback<String>() {
		
		@Override
		public void onSucceed(String result) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onFail(String error) {
			// TODO Auto-generated method stub
			
		}
	};

}
