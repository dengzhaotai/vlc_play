package com.dzt.musicplay;

import java.util.ArrayList;

import org.videolan.libvlc.LibVlcException;
import org.videolan.libvlc.LibVlcUtil;
import org.videolan.vlc.MediaLibrary;
import org.videolan.vlc.util.VLCInstance;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dzt.musicplay.TabFragment.OnTabClickListener;
import com.dzt.musicplay.constant.GlobalConstants;
import com.dzt.musicplay.list.SingerFragment;
import com.dzt.musicplay.list.SongFragment;
import com.dzt.musicplay.list.SongFragment.onChangePlayListener;
import com.dzt.musicplay.list.SongFragment.onUpdateProgressListener;
import com.dzt.musicplay.player.AudioService;
import com.dzt.musicplay.player.AudioServiceController;
import com.dzt.musicplay.player.PlayerActivity;
import com.dzt.musicplay.widgets.CustomViewPager;

public class MusicPlayActivity extends Activity implements
		onChangePlayListener, OnClickListener, onUpdateProgressListener {

	private static final int TAB_INDEX0 = 0;
	private static final int TAB_INDEX1 = 1;

	private TabFragment mTabFragment;
	private CustomViewPager mViewPageContainer;
	private FragmentManager mFM;
	private ArrayList<Fragment> mFragmentList = null;
	private SongFragment mSongFragment;
	private SingerFragment mSingerFragment;
	private ImageButton mPlayPause;
	private TextView mSongName;
	private TextView mSingerName;
	private RelativeLayout mLayout;
	private ProgressBar mPBar;
	private boolean mScanNeeded = true;
	// private AudioPlayer mAudioPlayer;
	private AudioServiceController mAudioController;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// CPU类型检查
		if (!LibVlcUtil.hasCompatibleCPU(this)) {
			GlobalConstants.print_i(getClass(), "" + LibVlcUtil.getErrorMsg());
			finish();
			super.onCreate(savedInstanceState);
			return;
		}

		try {
			// Start LibVLC
			VLCInstance.getLibVlcInstance(getApplicationContext());
		} catch (LibVlcException e) {
			e.printStackTrace();
			finish();
			super.onCreate(savedInstanceState);
			return;
		}

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_music_play);
		initTab();
		initViewPager();
		mAudioController = AudioServiceController.getInstance();
		GlobalConstants.print_i(getClass(), "onCreate");
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		mAudioController.bindAudioService(this);
		/*
		 * FIXME: this is used to avoid having MainActivity twice in the
		 * backstack
		 */
		if (getIntent().hasExtra(AudioService.START_FROM_NOTIFICATION))
			getIntent().removeExtra(AudioService.START_FROM_NOTIFICATION);

		/* Load media items from database and storage */
		if (mScanNeeded)
			MediaLibrary.getInstance(getApplicationContext()).loadMediaItems(
					getApplicationContext());
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		/* Check for an ongoing scan that needs to be resumed during onResume */
		mScanNeeded = MediaLibrary.getInstance(getApplicationContext())
				.isWorking();
		/* Stop scanning for files */
		MediaLibrary.getInstance(getApplicationContext()).stop();
		mAudioController.unbindAudioService(this);
	}

	private void initViewPager() {
		mSongFragment = new SongFragment();
		mSingerFragment = new SingerFragment();

		mFragmentList = new ArrayList<Fragment>();

		mFragmentList.add(mSongFragment);
		mFragmentList.add(mSingerFragment);

		mViewPageContainer = (CustomViewPager) findViewById(R.id.tabpage_frame);

		mViewPageContainer.setAdapter(new ViewPagerAdapter(mFM, mFragmentList));
		mViewPageContainer
				.setOnPageChangeListener(new ViewPagerOnChangeListener());
		mViewPageContainer.setCurrentItem(TAB_INDEX0);
		mSongFragment.setOnChangePlayListener(this);
		mSongFragment.setOnUpdateProgresListener(this);
		mPBar = (ProgressBar) findViewById(R.id.pb_bottom_play);
	}

	/**
	 * 初始化Tab，Tab是用Fragment实现的
	 */
	private void initTab() {
		mFM = getFragmentManager();
		mTabFragment = (TabFragment) mFM.findFragmentById(R.id.tab_view);
		mTabFragment.setOnTabClickListerner(new TabOnClickListener());
		mPlayPause = (ImageButton) findViewById(R.id.btn_play_pause);
		mSongName = (TextView) findViewById(R.id.song_name_play);
		mSingerName = (TextView) findViewById(R.id.song_signer_play);
		mLayout = (RelativeLayout) findViewById(R.id.bootom_play_bar);
		mPlayPause.setOnClickListener(this);
		mLayout.setOnClickListener(this);
	}

	private class TabOnClickListener implements OnTabClickListener {

		@Override
		public void onTabClick(int tabIndex) {
			// TODO Auto-generated method stub
			if (mViewPageContainer.getCurrentItem() == mTabFragment
					.getCurTabPos())
				return;
			switch (tabIndex) {
			case TAB_INDEX0:
				mViewPageContainer.setCurrentItem(TAB_INDEX0);
				break;
			case TAB_INDEX1:
				mViewPageContainer.setCurrentItem(TAB_INDEX1);
				break;
			default:
				break;
			}
		}
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		// if (outState != null)
		// super.onSaveInstanceState(outState);
	}

	private class ViewPagerOnChangeListener implements OnPageChangeListener {

		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onPageSelected(int arg0) {
			// TODO Auto-generated method stub
			switch (arg0) {
			case TAB_INDEX0:
				mTabFragment.setBtnOnSel(TAB_INDEX0);
				break;
			case TAB_INDEX1:
				mTabFragment.setBtnOnSel(TAB_INDEX1);
				break;
			default:
				break;
			}
		}
	}

	@Override
	public void onChangePlay(String singer, String name) {
		// TODO Auto-generated method stub
		mSongName.setText(name);
		mSingerName.setText(singer);
		mPlayPause.setImageResource(R.drawable.bottom_stop_selector);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		final int id = v.getId();
		switch (id) {
		case R.id.btn_play_pause:
			System.out.println("play flag = " + mAudioController.isPlaying());
			if (mAudioController.isPlaying()) {
				mPlayPause.setImageResource(R.drawable.bottom_play_selector);
				mAudioController.pause();
			} else {
				mPlayPause.setImageResource(R.drawable.bottom_stop_selector);
				mAudioController.play();
			}
			break;
		case R.id.bootom_play_bar:
			System.out.println("mLayout---------->onClick");
			Intent intent = new Intent(this, PlayerActivity.class);
			startActivity(intent);
			break;
		default:
			break;
		}
	}

	@Override
	public void onUpdateProgress(int time, int length) {
		// TODO Auto-generated method stub
		if (time == 0) {
			mPBar.setMax(length);
		}
		mPBar.setProgress(time);
	}
}
