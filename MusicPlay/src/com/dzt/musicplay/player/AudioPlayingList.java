package com.dzt.musicplay.player;

import java.util.ArrayList;
import java.util.List;

import org.videolan.vlc.audio.MusicInfo;

/**
 * 添加要播放的列表，第一个表示是当前正在播放的文件
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