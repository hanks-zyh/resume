package com.zyh.resume.util;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.BitmapFactory;

import com.zyh.resume.R;

/**
 * 关于Apk信息的工具类
 * @author zyh
 */
public class AppUtils {

	/**
	 * 创建桌面快捷图标 //需添加权限
	 */
	public static void installShortCut(Context context, String activityName) {

		SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
		boolean hasShrotcut = sp.getBoolean("shortcut", false);
		if (hasShrotcut) return;

		// 发送广播意图，告诉桌面要创建快捷图标
		Intent intent = new Intent();
		intent.setAction("com.android.launcher.action.INSTALL_SHORTCUT");

		// 快捷方式包含3个信息 1.名称2.图标 3.意图
		intent.putExtra(Intent.EXTRA_SHORTCUT_ICON, BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher));
		intent.putExtra(Intent.EXTRA_SHORTCUT_NAME, context.getResources().getString(R.string.app_name));

		// 桌面图标点击意图
		Intent shortcutIntent = new Intent();
		shortcutIntent.setAction("android.intent.action.MAIN");
		shortcutIntent.addCategory("android.intent.category.LAUNCHER");
		shortcutIntent.setClassName(context.getPackageName(), activityName);

		intent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
		context.sendBroadcast(intent);
		ToastUtils.show(context, "创建快捷方式：" + context.getResources().getString(R.string.app_name));
		sp.edit().putBoolean("shortcut", true).commit();
	}

	/**
	 * 得到应用的版本号
	 */
	public static String getAppVersion(Context context) {
		PackageManager pm = context.getPackageManager();
		try {
			// 功能清单文件
			PackageInfo info = pm.getPackageInfo(context.getPackageName(), 0);
			return info.versionName;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
			// 不会发生
			return "1.0";
		}
	}
}
