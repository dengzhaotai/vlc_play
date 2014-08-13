package com.dzt.musicplay;

import java.util.Locale;

import android.app.Application;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.preference.PreferenceManager;

import com.dzt.musicplay.constant.GlobalConstants;

/**
 * 音乐播放器，使用百度媒体云
 * 相关SDK下载：http://developer.baidu.com/wiki/index.php?title=docs/cplat
 * /media/video/sdk
 * 
 * @author Administrator
 * @date 2014.08.05
 */
public class MusicPlayApp extends Application {

	private static MusicPlayApp instance = null;

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		instance = this;
		SharedPreferences pref = PreferenceManager
				.getDefaultSharedPreferences(this);
		String p = pref.getString("set_locale", "");
		if (p != null && !p.equals("")) {
			Locale locale;
			// workaround due to region code
			if (p.equals("zh-TW")) {
				locale = Locale.TRADITIONAL_CHINESE;
			} else if (p.startsWith("zh")) {
				locale = Locale.CHINA;
			} else if (p.equals("pt-BR")) {
				locale = new Locale("pt", "BR");
			} else if (p.equals("bn-IN") || p.startsWith("bn")) {
				locale = new Locale("bn", "IN");
			} else {
				/**
				 * Avoid a crash of java.lang.AssertionError: couldn't
				 * initialize LocaleData for locale if the user enters
				 * nonsensical region codes.
				 */
				if (p.contains("-"))
					p = p.substring(0, p.indexOf('-'));
				locale = new Locale(p);
			}
			Locale.setDefault(locale);
			Configuration config = new Configuration();
			config.locale = locale;
			getBaseContext().getResources().updateConfiguration(config,
					getBaseContext().getResources().getDisplayMetrics());
		}
		GlobalConstants.print_i(getClass(), "onCreate");
	}

	public static MusicPlayApp getInstance() {
		return instance;
	}

	public static boolean isRunning() {
		if (instance == null)
			return false;
		else
			return true;
	}
}
