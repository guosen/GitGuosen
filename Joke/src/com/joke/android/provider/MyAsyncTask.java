package com.joke.android.provider;

import com.joke.android.base.MAsyncException;
import com.joke.android.base.NetWorkException;
import com.joke.android.base.NetWorkRequestException;
import com.joke.android.entity.EntityWrapper;
import com.joke.android.face.ICallback;

import android.app.Activity;
import android.os.AsyncTask;


public abstract class MyAsyncTask<Result> extends AsyncTask<Void, Void, Result> {

	private Activity activity;
	protected ICallback<Result> callback;
	protected boolean isSucceed = true;
	protected String error;
	protected EntityWrapper entityWrapper;

	public MyAsyncTask(Activity activity, ICallback<Result> callback) {
		this.activity = activity;
		this.callback = callback;
	}

	public abstract Result handler() throws MAsyncException;

	@Override
	protected Result doInBackground(Void... params) {
		try {
			return handler();
		} catch (Exception e) {
			isSucceed = false;
			error = getErrorMsg(e);
		}
		return null;
	}

	public void doError() {
	}

	public void doSuccessAfter() {
	}

	@Override
	protected void onPostExecute(Result result) {
		if (isSucceed) {
			if (result instanceof EntityWrapper) {
				EntityWrapper entity = (EntityWrapper) result;
				if (entity.getMeta().getStatus() != 0) {
					if (activity == null
							|| (activity != null && !activity.isFinishing())) {
						callback.onFail("[code=" + entity.getMeta().getStatus()
								+ "]" + entity.getMeta().getMsg());
					}
					return;
				}
			}
			if (entityWrapper != null) {
				if (entityWrapper.getMeta().getStatus() != 0) {
					if (activity == null
							|| (activity != null && !activity.isFinishing())) {
						callback.onFail("[code="
								+ entityWrapper.getMeta().getStatus() + "]"
								+ entityWrapper.getMeta().getMsg());
					}
					return;
				}
			}
			doSuccessAfter();
			if (activity == null
					|| (activity != null && !activity.isFinishing())) {
				callback.onSucceed(result);
			}
		} else {
			doError();
			if (activity == null
					|| (activity != null && !activity.isFinishing())) {
				callback.onFail(error);
			}
		}
	}

	private String getErrorMsg(Exception e) {
		String errorMsg = "";
		if (e instanceof NetWorkRequestException) {
			errorMsg = "服务器正在维护中，请5分钟后刷新";
		} else if (e instanceof NetWorkException) {
			errorMsg = "你的网络貌似不给力，重新连接试试……";
		} else if(e instanceof MAsyncException){
			if(e.getMessage()!=null){
				errorMsg=e.getMessage();
			}else{
				errorMsg="操作失败";
			}
		} else{
			return "操作失败";
		}
		return "操作失败" + "[" + errorMsg + "]";
	}
	

}

