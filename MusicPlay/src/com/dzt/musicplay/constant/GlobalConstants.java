package com.dzt.musicplay.constant;

import android.util.Log;

/**
 * ����ȫ�ֵĳ���
 * 
 * @author Administrator
 * 
 */
public class GlobalConstants {

	public static final String TAG = "MusicPlayApp_dzt";
	private static final boolean mShowLog = true;

	/**
	 * ����ak
	 */
	public static final String AK = "0FsaXqU8dSw0CGoM5j0HOhqu";
	/**
	 * //����sk��ǰ16λ
	 */
	public static final String SK = "U5l7EtHPMf6B4ZSz"; // VmncDOfd02eFzIB0
	
	public static final int PLAY_MSG = 1;		//����
	public static final int PAUSE_MSG = 2;		//��ͣ
	public static final int STOP_MSG = 3;		//ֹͣ
	public static final int CONTINUE_MSG = 4;	//����
	public static final int PRIVIOUS_MSG = 5;	//��һ��
	public static final int NEXT_MSG = 6;		//��һ��
	public static final int PROGRESS_CHANGE = 7;//���ȸı�
	public static final int PLAYING_MSG = 8;	//���ڲ���
	
	public static void print_i(Class<?> cls, String log) {
		if (mShowLog)
			Log.i(TAG, cls.toString() + "-------------------->" + log);
	}
}
