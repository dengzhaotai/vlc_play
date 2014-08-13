package com.dzt.musicplay;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.widget.ImageButton;
import android.widget.TextView;

import com.dzt.musicplay.TabFragment.OnTabClickListener;
import com.dzt.musicplay.constant.GlobalConstants;
import com.dzt.musicplay.list.SingerFragment;
import com.dzt.musicplay.list.SongFragment;
import com.dzt.musicplay.list.SongFragment.onChangePlayListener;
import com.dzt.musicplay.player.AudioServiceController;
import com.dzt.musicplay.widgets.CustomImageButton;
import com.dzt.musicplay.widgets.CustomViewPager;

public class MusicPlayActivity extends Activity implements onChangePlayListener {

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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_music_play);
		initTab();
		initViewPager();
		GlobalConstants.print_i(getClass(), "onCreate");
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		AudioServiceController.getInstance().bindAudioService(this);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		AudioServiceController.getInstance().unbindAudioService(this);
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
		mTabFragment.setBtnOnSel(TAB_INDEX0);
		mSongFragment.setOnChangePlayListener(this);
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
	}

}
