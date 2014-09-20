package com.zyh.resume.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class NetTools {

	public static boolean isNetworkAvailable(Context context) {
		ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity == null) {
			Log.i("Network", "网络不可用");
			return false;
		} else {
			NetworkInfo networkInfo = connectivity.getActiveNetworkInfo();
			if (networkInfo == null || !networkInfo.isAvailable()) {
				return false;
			}
		}
		return true;

		// 下面可以判断网络类型
		// else{
		// NetworkInfo[] info = connectivity.getAllNetworkInfo();
		// if(info != null){
		// for(int i=0; i<info.length; i++){
		// if(info[i].getState() == NetworkInfo.State.CONNECTED){
		// Log.i("Network", "网络可用");
		// return true;
		// }
		// }
		// }
		// }
		// return false;
	}

}
