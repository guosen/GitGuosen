package com.joke.android.face;

import com.joke.android.entity.JokeWrapper;

import android.app.Activity;

public interface IUser {
      void userLogin(Activity activity,ICallback<String> callback,String username,String pwd);
      
      void JokeContent(Activity activity,ICallback<JokeWrapper> callback);
      
      void AddJoke(Activity activity,ICallback<String>callback,String joke);
}
