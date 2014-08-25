package com.dzt.musicplay.player;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.dzt.musicplay.R;
import com.dzt.musicplay.ViewPagerAdapter;
import com.dzt.musicplay.list.SongFragment;
import com.dzt.musicplay.list.SongFragment.onUpdateProgressListener;
import com.dzt.musicplay.widgets.CustomViewPager;

public class PlayerActivity extends Activity implements OnClickListener {
	private static final int TAB_INDEX0 = 0;
	private static final int TAB_INDEX1 = 1;
	private CustomViewPager mViewPageContainer;
	private FragmentManager mFM;
	private ArrayList<Fragment> mFragmentList = null;
	private AlbumsFragment mAlbumsFragment;
	private LyricFragment mLyricFragment;
	private ImageView img1, img2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.player_activity);
		initWidgets();
	}

	private void initWidgets() {
		findViewById(R.id.btn_play_pause_play).setOnClickListener(this);
		findViewById(R.id.btn_next_play).setOnClickListener(this);
		findViewById(R.id.btn_previous_play).setOnClickListener(this);
		img1 = (ImageView) findViewById(R.id.icon_1);
		img2 = (ImageView) findViewById(R.id.icon_2);

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

	}

	private void previous() {

	}

	private void next() {

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
