package com.zyh.resume.util;

import android.content.Context;
import android.widget.Toast;

public class ToastUtils {

	/**
	 * 显示toast
	 * @param context 上下文
	 * @param text toast文本
	 */
	public static void show(Context context, String text) {
		Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
	}

	/**
	 * 显示toast
	 * @param context 上下文
	 * @param text toast文本
	 * @param islong 长显示
	 */
	public static void show(Context context, String text, boolean islong) {
		if (islong) {
			Toast.makeText(context, text, Toast.LENGTH_LONG).show();
		} else {
			show(context, text);
		}
	}
}
