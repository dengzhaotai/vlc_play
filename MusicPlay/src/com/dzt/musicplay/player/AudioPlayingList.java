package com.dzt.musicplay.player;

import java.util.ArrayList;
import java.util.List;

import org.videolan.libvlc.Media;

/**
 * 添加要播放的列表，第一个表示是当前正在播放的文件
 * 
 * @author Administrator
 * 
 */
public class AudioPlayingList {
	static AudioPlayingList mInstance = null;

	ArrayList<Media> mPlayList = null;

	public static AudioPlayingList getInstance() {
		if (mInstance == null)
			mInstance = new AudioPlayingList();
		return mInstance;
	}

	private AudioPlayingList() {

	}

	public ArrayList<Media> GetServicePlayList() {
		if (mPlayList == null)
			mPlayList = new ArrayList<Media>();
		return mPlayList;
	}

	public void SetServicePlayList(List<Media> mediaPathList) {
		if (mPlayList == null)
			mPlayList = new ArrayList<Media>();

		mPlayList.clear();
		for (Media f : mediaPathList) {
			mPlayList.add(f);
		}
	}

	public int GetServicePlayListCnt() {
		if (mPlayList == null)
			mPlayList = new ArrayList<Media>();
		return mPlayList.size();
	}

}