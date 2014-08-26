package com.dzt.musicplay.player;

import java.util.ArrayList;

import org.videolan.vlc.interfaces.IAudioPlayer;
import org.videolan.vlc.util.Strings;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.os.MessageQueue.IdleHandler;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.SeekBar.OnSeekBarChangeListener;

import com.dzt.musicplay.R;
import com.dzt.musicplay.ViewPagerAdapter;
import com.dzt.musicplay.list.SongFragment;
import com.dzt.musicplay.list.SongFragment.onUpdateProgressListener;
import com.dzt.musicplay.widgets.CustomViewPager;

public class PlayerActivity extends Activity implements OnClickListener,
		IAudioPlayer {
	private static final int TAB_INDEX0 = 0;
	private static final int TAB_INDEX1 = 1;
	private CustomViewPager mViewPageContainer;
	private FragmentManager mFM;
	private ArrayList<Fragment> mFragmentList = null;
	private AlbumsFragment mAlbumsFragment;
	private LyricFragment mLyricFragment;
	private ImageView img1, img2;
	private TextView mTime, mLength;
	private ImageButton mPausePlay, mPrevious, mNext;
	private SeekBar mSeekBar;
	private AudioServiceController mAudioController;
	private SeekBarChangeListener mSeekBarListener = new SeekBarChangeListener();
	private boolean mIsFirst = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.player_activity);
		initWidgets();
		mAudioController = AudioServiceController.getInstance();
		if (mAudioController.isPlaying()) {
			mPausePlay.setImageResource(R.drawable.bottom_play_selector);
		} else {
			mPausePlay.setImageResource(R.drawable.bottom_stop_selector);
		}
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		mAudioController.addAudioPlayer(this);
		AudioServiceController.getInstance().bindAudioService(this);
		mIsFirst = true;
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		mAudioController.removeAudioPlayer(this);
		AudioServiceController.getInstance().unbindAudioService(this);
		mIsFirst = false;
	}

	private void initWidgets() {
		img1 = (ImageView) findViewById(R.id.icon_1);
		img2 = (ImageView) findViewById(R.id.icon_2);
		mTime = (TextView) findViewById(R.id.tv_time);
		mLength = (TextView) findViewById(R.id.tv_length);
		mPausePlay = (ImageButton) findViewById(R.id.btn_play_pause_play);
		mPausePlay.setOnClickListener(this);
		mPrevious = (ImageButton) findViewById(R.id.btn_previous_play);
		mPrevious.setOnClickListener(this);
		mNext = (ImageButton) findViewById(R.id.btn_next_play);
		mNext.setOnClickListener(this);

		mFM = getFragmentManager();
		mAlbumsFragment = new AlbumsFragment();
		mLyricFragment = new LyricFragment();
		mFragmentList = new ArrayList<Fragment>();
		mFragmentList.add(mAlbumsFragment);
		mFragmentList.add(mLyricFragment);
		mViewPageContainer = (CustomViewPager) findViewById(R.id.vp_play);
		mViewPageContainer.setAdapter(new ViewPagerAdapter(mFM, mFragmentList));
		mViewPageContainer
				.setOnPageChangeListener(new ViewPagerOnChangeListener());
		mViewPageContainer.setCurrentItem(TAB_INDEX0);
		mSeekBar = (SeekBar) findViewById(R.id.sb_play);
		mSeekBar.setOnSeekBarChangeListener(mSeekBarListener);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		final int id = arg0.getId();
		switch (id) {
		case R.id.btn_play_pause_play:
			playPause();
			break;
		case R.id.btn_previous_play:
			previous();
			break;
		case R.id.btn_next_play:
			next();
			break;
		default:
			break;
		}
	}

	private void playPause() {
		if (mAudioController.isPlaying()) {
			mPausePlay.setImageResource(R.drawable.bottom_play_selector);
			mAudioController.pause();
		} else {
			mPausePlay.setImageResource(R.drawable.bottom_stop_selector);
			mAudioController.play();
		}
	}

	private void previous() {
		mAudioController.previous();
	}

	private void next() {
		mAudioController.next();
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateProgress() {
		// TODO Auto-generated method stub
		int time = mAudioController.getTime();
		int length = mAudioController.getLength();
		if (mIsFirst || (time == 0)) {
			mIsFirst = false;
			mSeekBar.setMax(length);
		}
		mSeekBar.setProgress(time);
		mTime.setText(Strings.millisToString(time));
		mLength.setText(Strings.millisToString(length));
	}

	/**
	 * ½ø¶ÈÌõ¼àÌýÆ÷
	 * 
	 * @author Administrator
	 * 
	 */
	private class SeekBarChangeListener implements OnSeekBarChangeListener {

		@Override
		public void onProgressChanged(SeekBar seekBar, int progress,
				boolean fromUser) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {
			// TODO Auto-generated method stub

		}

	}

	/**
	 * ViewPagerÇÐ»»µÄ¼àÌýÆ÷
	 * 
	 * @author Administrator
	 * 
	 */
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
				img1.setImageResource(R.drawable.page_icon_sel);
				img2.setImageResource(R.drawable.page_icon);
				System.out.println("0000000000000000000000000");
				break;
			case TAB_INDEX1:
				img1.setImageResource(R.drawable.page_icon);
				img2.setImageResource(R.drawable.page_icon_sel);
				System.out.println("111111111111111111111111111");
				break;
			default:
				break;
			}
		}
	}
}
