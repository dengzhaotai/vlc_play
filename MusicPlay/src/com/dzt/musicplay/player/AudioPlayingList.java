package com.dzt.musicplay.player;

import java.util.ArrayList;
import java.util.List;

import org.videolan.vlc.audio.MusicInfo;

/**
 * ���Ҫ���ŵ��б���һ����ʾ�ǵ�ǰ���ڲ��ŵ��ļ�
 * 
 * @author Administrator
 * 
 */
public class AudioPlayingList {
	static AudioPlayingList mInstance = null;

	ArrayList<MusicInfo> mPlayList = null;

	public static AudioPlayingList getInstance() {
		if (mInstance == null)
			mInstance = new AudioPlayingList();
		return mInstance;
	}

	private AudioPlayingList() {

	}

	public ArrayList<MusicInfo> GetServicePlayList() {
		if (mPlayList == null)
			mPlayList = new ArrayList<MusicInfo>();
		return mPlayList;
	}

	public void SetServicePlayList(List<MusicInfo> mediaPathList) {
		if (mPlayList == null)
			mPlayList = new ArrayList<MusicInfo>();

		mPlayList.clear();
		for (MusicInfo f : mediaPathList) {
			mPlayList.add(f);
		}
	}

	public int GetServicePlayListCnt() {
		if (mPlayList == null)
			mPlayList = new ArrayList<MusicInfo>();
		return mPlayList.size();
	}

}