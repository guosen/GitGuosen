package com.joke.android.face.impl;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Context;

import com.google.gson.Gson;
import com.joke.android.base.MAsyncException;
import com.joke.android.entity.JokeWrapper;
import com.joke.android.face.ICallback;
import com.joke.android.face.IUser;
import com.joke.android.provider.HttpProvider;
import com.joke.android.provider.MyAsyncTask;

public class DefaultRUser extends HttpProvider implements IUser{

	public DefaultRUser(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void userLogin(Activity activity,ICallback<String>callback, final String username, final String pwd) {
		// TODO Auto-generated method stub
		MyAsyncTask<String>task=new MyAsyncTask<String>(activity,callback) {

			@Override
			public String handler() throws MAsyncException {
				// TODO Auto-generated method stub
				String uri=serverUrl;
				
				Map<String, Object> parametersPair=new HashMap<String, Object>();
				parametersPair.put("username",username);
				parametersPair.put("password", pwd);
				String bact=post(uri,null, parametersPair);
				return null;
			}
		};
		task.execute();
	}

	@Override
	public void JokeContent(Activity activity, ICallback<JokeWrapper> callback) {
		// TODO Auto-generated method stub
			MyAsyncTask<JokeWrapper>task=new MyAsyncTask<JokeWrapper>(activity,callback) {

			@Override
			public JokeWrapper handler() throws MAsyncException {
				// TODO Auto-generated method stub
				String uri=serverUrl+"/servlet/MyServlet";
				String back=post(uri, null, null);
				back=back.substring(1,back.length()-1);
				return new Gson().fromJson(back, JokeWrapper.class);
			}
		};
		task.execute();
		
	}

	@Override
	public void AddJoke(Activity activity, ICallback<String> callback,
			final String joke) {
		// TODO Auto-generated method stub
		MyAsyncTask<String>task=new MyAsyncTask<String>(activity,callback) {

			@Override
			public String handler() throws MAsyncException {
				// TODO Auto-generated method stub
				String uri=serverUrl+"/servlet/AddJoke";
				Map<String, Object> parametersPair=new HashMap<String, Object>();
				parametersPair.put("jokecontent",joke); 
				String str=urlEncoder(joke);
				String bact=post(uri, null, parametersPair);
				return bact;
			}
		};
		task.execute();
		
		
		
		
		
	}

}
