package org.videolan.vlc.util;

import android.util.Log;

public class Constant {

	private static final String TAG = "com.dzt.vlcaudiolib";
	private static final boolean mIsShowLog = true;

	public static void print_i(String msg) {
		if (mIsShowLog)
			Log.i(TAG, "--------------------->" + msg);
	}
}
