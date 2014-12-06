package com.joke.android.face;

public interface ICallback<T> {
     
    public void onSucceed(T result);
	
	public void onFail(String error);
}
