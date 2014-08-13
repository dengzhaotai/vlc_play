package com.dzt.musicplay.constant;

import android.util.Log;

/**
 * 定义全局的常量
 * 
 * @author Administrator
 * 
 */
public class GlobalConstants {

	public static final String TAG = "MusicPlayApp_dzt";
	private static final boolean mShowLog = true;

	/**
	 * 您的ak
	 */
	public static final String AK = "0FsaXqU8dSw0CGoM5j0HOhqu";
	/**
	 * //您的sk的前16位
	 */
	public static final String SK = "U5l7EtHPMf6B4ZSz"; // VmncDOfd02eFzIB0
	
	public static final int PLAY_MSG = 1;		//播放
	public static final int PAUSE_MSG = 2;		//暂停
	public static final int STOP_MSG = 3;		//停止
	public static final int CONTINUE_MSG = 4;	//继续
	public static final int PRIVIOUS_MSG = 5;	//上一首
	public static final int NEXT_MSG = 6;		//下一首
	public static final int PROGRESS_CHANGE = 7;//进度改变
	public static final int PLAYING_MSG = 8;	//正在播放
	
	public static void print_i(Class<?> cls, String log) {
		if (mShowLog)
			Log.i(TAG, cls.toString() + "-------------------->" + log);
	}
}
