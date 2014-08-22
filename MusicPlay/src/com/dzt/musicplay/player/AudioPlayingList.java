package com.dzt.musicplay.player;

import java.util.ArrayList;
import java.util.List;

import org.videolan.libvlc.Media;

/**
 * ���Ҫ���ŵ��б���һ����ʾ�ǵ�ǰ���ڲ��ŵ��ļ�
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