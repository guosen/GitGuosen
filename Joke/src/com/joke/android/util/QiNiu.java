package com.joke.android.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import com.qiniu.auth.JSONObjectRet;
import com.qiniu.auth.PutPolicy;
import com.qiniu.io.IO;
import com.qiniu.io.PutExtra;


public class QiNiu {
	public static final String bucketName = "caiba";
	public static final String domain = bucketName + ".qiniudn.com";
	private String key;//图片在七牛那存储的名字,这个key和下载的时候也有关系
	public String uptoken;// upToken 这里需要自行获取. SDK 将不实现获取过程. 当token过期后才再获取一遍

	
	/**
	 * 上传文件
	 * @param context
	 * @param upToken 获取的token
	 * @param key 文件标识
	 * @param uri 文件uri
	 * @param extra 
	 */
	public static void upFile(Context context,String upToken,final String key,Uri uri,PutExtra extra){
		IO.putFile(context, upToken, key, uri, extra, new JSONObjectRet() {
			@Override
			public void onProcess(long current, long total) {
			
			}
			@Override
			public void onSuccess(JSONObject resp) {
//				String downUrl = "http://" + domain + "/" + key;
				String downUrl = "http://" + domain + "/" + key;
			}
			@Override
			public void onFailure(Exception ex) {
//				String message = ex.getMessage();
			}
		});
	}
	
	/**
	 * 获取上传到七牛的token
	 * @param key 文件的key
	 * @return
	 */
	public static String getUpToken(String key){
		PutExtra extra = new PutExtra();
		extra.params = new HashMap<String, String>();
		String scope = bucketName + ":" + key;
		PutPolicy putPolicy = new PutPolicy(scope);
		try {
			String uptoken = putPolicy.token();
			return uptoken;
		} catch (JSONException e) {
		}
		return null;
	}
	public static void getPic(String url,final ImageView imageView){
		new AsyncTask<String, Void, Bitmap>(){
			@Override
			protected Bitmap doInBackground(String... params) {
				Bitmap bitmap = null;
				try {
					URL url = new URL(params[0]);
					URLConnection openConnection = url.openConnection();
					InputStream is = openConnection.getInputStream();
					bitmap = BitmapFactory.decodeStream(is);
				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				return bitmap;
			}
			@Override
			protected void onPostExecute(Bitmap result) {
				imageView.setImageBitmap(result);
			};
			
		}.execute(url);
	}
	public void upload(){
		key = "201411041652";
		PutExtra extra = new PutExtra();
		extra.params = new HashMap<String, String>();
//		String scope = QiNiuConstants.BUCKET + ":" + key;//  linli:+key  ,那菜吧就是  caiba:+key
		String scope = "jokeapp:"+key;
		PutPolicy putPolicy = new PutPolicy(scope);
		try {
			uptoken = putPolicy.token();
			Log.e("uptoken", ""+uptoken);
		} catch (JSONException e) {
		}
	}
}
