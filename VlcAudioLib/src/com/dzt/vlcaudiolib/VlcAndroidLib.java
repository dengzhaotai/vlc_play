package com.dzt.vlcaudiolib;

import org.videolan.libvlc.LibVLC;
import org.videolan.libvlc.LibVlcException;
import org.videolan.vlc.util.Constant;
import org.videolan.vlc.util.VLCInstance;

import android.content.Context;

public class VlcAndroidLib {

	private static VlcAndroidLib instance;

	private VlcAndroidLib() {
		instance = this;
	}

	public static VlcAndroidLib getInstance() {
		if (instance == null)
			instance = new VlcAndroidLib();
		return instance;
	}

	public static void init(Context context) {
		try {
			Constant.print_i("[VlcAndroidLib]-------------->init");
			// Start LibVLC
			VLCInstance.getLibVlcInstance(context);
		} catch (LibVlcException e) {
			Constant.print_i("[VlcAndroidLib]-------------->init faild");
			e.printStackTrace();
		}
	}

	public void readFile(Context context, String path) {
		try {
			VLCInstance.getLibVlcInstance(context).readMedia(context,
					LibVLC.PathToURI(path));
		} catch (LibVlcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void play(Context context) {
		try {
			VLCInstance.getLibVlcInstance(context).customizePlay();
		} catch (LibVlcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
