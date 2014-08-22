package com.dzt.musicplay.list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.videolan.libvlc.Media;
import org.videolan.vlc.MediaLibrary;
import org.videolan.vlc.interfaces.IAudioPlayer;
import org.videolan.vlc.util.Strings;
import org.videolan.vlc.util.Util;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.dzt.musicplay.R;
import com.dzt.musicplay.WeakHandler;
import com.dzt.musicplay.constant.GlobalConstants;
import com.dzt.musicplay.constant.GlobalVariables;
import com.dzt.musicplay.list.AudioBrowserListAdapter.ListItem;
import com.dzt.musicplay.player.AudioServiceController;

public class SongFragment extends Fragment implements IAudioPlayer {

	private View mSongView;
	private ListView mLv = null;
	private AudioBrowserListAdapter mSongsAdapter;
	private List<HashMap<String, String>> mList = null;

	private AudioServiceController mAudioController = null;
	private onChangePlayListener listener = null;
	private MediaLibrary mMediaLibrary;
	private int mFlingViewPosition = 0;

	public interface onChangePlayListener {
		public void onChangePlay(String singer, String url);
	}

	public void setOnChangePlayListener(onChangePlayListener listener) {
		this.listener = listener;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		mAudioController = AudioServiceController.getInstance();
		mMediaLibrary = MediaLibrary.getInstance(getActivity());
		initWidgets();
		mList = new ArrayList<HashMap<String, String>>();
		// 创建歌曲列表的适配器
		mSongsAdapter = new AudioBrowserListAdapter(getActivity(),
				AudioBrowserListAdapter.ITEM_WITH_COVER);

		GlobalConstants.print_i(getClass(), "onCreate");
		mLv.setOnItemClickListener(new OnItemClickListener() {

			// 监听ListView的点击动作，根据点击的内容进行播放
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				ListItem item = mSongsAdapter.getItem(position);
				GlobalVariables.mCurrPos = position + 1;
				String singer = item.mSubTitle;
				String name = item.mTitle;

				GlobalConstants.print_i(getClass(),
						"onItemClick----->position = " + position + " url = "
								+ name);
				playAudio(position);
				if (listener != null) {
					listener.onChangePlay(singer, name);
				}
			}

		});
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		mLv.setAdapter(mSongsAdapter);
		return mSongView;
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		mAudioController.addAudioPlayer(this);
		searchFile();
		mMediaLibrary.addUpdateHandler(searchHandler);
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		mMediaLibrary.removeUpdateHandler(searchHandler);
		mAudioController.removeAudioPlayer(this);
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		GlobalConstants.print_i(getClass(), "onDestroy");
		mSongsAdapter.clear();
		super.onDestroy();
	}

	private void initWidgets() {
		LayoutInflater inflater = LayoutInflater.from(getActivity());
		mSongView = inflater.inflate(R.layout.song_fragment,
				(ViewGroup) getActivity().findViewById(R.id.tabpage_frame),
				false);
		mLv = (ListView) mSongView.findViewById(R.id.song_list);
	}

	private void searchFile() {
		List<Media> audioList = MediaLibrary.getInstance(getActivity())
				.getAudioItems();

		if (audioList.isEmpty())
			GlobalConstants.print_i(getClass(),
					"searchFile----->audioList is null ");
		else
			GlobalConstants.print_i(getClass(),
					"searchFile----->audioList is not null");

		mSongsAdapter.clear();

		Collections.sort(audioList, MediaComparators.byName);
		for (int i = 0; i < audioList.size(); i++) {
			Media media = audioList.get(i);
			mSongsAdapter.add(media.getTitle(), media.getArtist(), media);
		}
		mSongsAdapter.addScrollSections();
		mSongsAdapter.notifyDataSetChanged();

		GlobalConstants.print_i(getClass(), "size = " + mList.size());
		searchHandler.sendEmptyMessageDelayed(1, 500);
	}

	AudioServiceConnectHandler hConnect = null;

	private class AudioServiceConnectHandler extends Handler {
		private int mPosition = -1;

		public void SetPosition(int position) {
			mPosition = position;
		}

		@Override
		public void dispatchMessage(Message msg) {
			// TODO Auto-generated method stub
			super.dispatchMessage(msg);
			GlobalConstants.print_i(getClass(),
					"dispatchMessage-------->mPosition = " + mPosition);
			mAudioController.RemoveConnectedCallbackHandler(this);
			if (mPosition != -1) {
				playAudio(mPosition);
				mPosition = -1;
			}
		}
	}

	private void playAudio(int pos) {
		GlobalConstants.print_i(getClass(), "playAudio");
		List<Media> medias = mSongsAdapter.getItems();
		if (!mAudioController.IsConnected()) {
			GlobalConstants.print_i(getClass(), "playAudio Is not Connected");
			if (hConnect == null)
				hConnect = new AudioServiceConnectHandler();
			mAudioController.AddConnectedCallbackHandler(hConnect);
			hConnect.SetPosition(pos);
			return;
		}
		ArrayList<String> mediaLocation = mSongsAdapter.getLocations(pos);
		System.out.println("-------------------------------------songListener");
		mAudioController.load(mediaLocation, 0);

		// mAudioController.loadMediaList(medias, pos);
		GlobalConstants.print_i(getClass(), "playAudio----end tmp = ");
	}

	private Handler searchHandler = new SearchHandler(this);

	private static class SearchHandler extends WeakHandler<SongFragment> {

		public SearchHandler(SongFragment owner) {
			super(owner);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			SongFragment owner = getOwner();
			if (owner == null)
				return;
			switch (msg.what) {
			case MediaLibrary.MEDIA_ITEMS_UPDATED:
				GlobalConstants.print_i(getClass(),
						"SearchHandler----------MEDIA_ITEMS_UPDATED");
				owner.searchFile();
				break;
			case 1:
				// owner.updateList();
				break;
			case 2:
				// owner.play();
			default:
				break;
			}
		}
	}

	@Override
	public synchronized void update() {
		// TODO Auto-generated method stub
		System.out.println("------------------------update");
		if (mAudioController == null)
			return;
		if (mAudioController.isShuffling()) {
			// mShuffle.setImageResource(Util.getResourceFromAttribute(act,
			// R.attr.ic_shuffle_pressed));
			System.out.println("------------------------update1");
		} else {
			// mShuffle.setImageResource(Util.getResourceFromAttribute(act,
			// R.attr.ic_shuffle_normal));
			System.out.println("------------------------update2");
		}
		switch (mAudioController.getRepeatType()) {
		case None:
			// mRepeat.setImageResource(Util.getResourceFromAttribute(act,
			// R.attr.ic_repeat_normal));
			System.out.println("------------------------update3");
			break;
		case Once:
			// mRepeat.setImageResource(Util.getResourceFromAttribute(act,
			// R.attr.ic_repeat_one));
			System.out.println("------------------------update4");
			break;
		default:
		case All:
			// mRepeat.setImageResource(Util.getResourceFromAttribute(act,
			// R.attr.ic_repeat_pressed));
			System.out.println("------------------------update5");
			break;
		}
		// if (mAudioController.hasNext())
		// mNext.setVisibility(ImageButton.VISIBLE);
		// else
		// mNext.setVisibility(ImageButton.INVISIBLE);
		// if (mAudioController.hasPrevious())
		// mPrevious.setVisibility(ImageButton.VISIBLE);
		// else
		// mPrevious.setVisibility(ImageButton.INVISIBLE);
		// mTimeline.setOnSeekBarChangeListener(mTimelineListner);

		// updateList();
	}

	@Override
	public synchronized void updateProgress() {
		// TODO Auto-generated method stub
		int time = mAudioController.getTime();
		int length = mAudioController.getLength();
		System.out.println("------updateProgress time = "
				+ Strings.millisToString(time) + " length = "
				+ Strings.millisToString(length));
	}

}
