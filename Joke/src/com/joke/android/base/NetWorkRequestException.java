package com.joke.android.base;

public class NetWorkRequestException extends RuntimeException  {

	private static final long serialVersionUID = -6462438312265390338L;
	
	public NetWorkRequestException(Throwable throwable) {
		super(throwable);
	}
	
	public NetWorkRequestException(String detailMessage) {
		super(detailMessage);
	}

}
