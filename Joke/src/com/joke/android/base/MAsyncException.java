package com.joke.android.base;

public class MAsyncException  extends Exception {

	/**
	 * 定制的功能异常
	 */
	private static final long serialVersionUID = 7059228005548508455L;
	
	public MAsyncException(){
		
	}
	
	public MAsyncException(String msg){
		super(msg);
	}

}
