package com.joke.android.util;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;

public class AndroidDeviceUtil {

	public static String getDeviceName(){
		
		return android.os.Build.DEVICE;
	}

	public static String getImei(Context context){
		if (context == null){
			throw new NullPointerException("context == null");
		}

		TelephonyManager telephonyManager = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);  
		return telephonyManager.getDeviceId();  
	}
	
	public static String getMacAddress(Context context){
		if (context == null){
			throw new NullPointerException("context == null");
		}
		
		WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);  
		WifiInfo info = wifi.getConnectionInfo(); 
		return info.getMacAddress();
	}
	
}
