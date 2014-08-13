package com.dzt.musicplay.list;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.videolan.vlc.interfaces.IAudioPlayer;
import org.videolan.vlc.audio.MusicInfo;
import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.dzt.musicplay.R;
import com.dzt.musicplay.WeakHandler;
import com.dzt.musicplay.constant.GlobalConstants;
import com.dzt.musicplay.player.AudioServiceController;
import com.dzt.musicplay.utils.MediaUtils;
import com.dzt.vlcaudiolib.VlcAndroidLib;

public class SongFragment extends Fragment implements IAudioPlayer {

	private View mSongView;
	private ListView mLv = null;
	private SongAdapter mAdapter = null;
	private List<HashMap<String, String>> mList = null;
	List<MusicInfo> mAudioList = new ArrayList<MusicInfo>();

	private AudioServiceController mAudioController = null;
	private onChangePlayListener listener = null;

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
		initWidgets();
		mList = new ArrayList<HashMap<String, String>>();
		mAdapter = new SongAdapter(mList, getActivity());
		mLv.setAdapter(mAdapter);
		GlobalConstants.print_i(getClass(), "onCreate");
		mLv.setOnItemClickListener(new OnItemClickListener() {

			// 监听ListView的点击动作，根据点击的内容进行播放
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				String singer = mList.get(position).get("Artist");
				String name = mList.get(position).get("displayName");
				GlobalConstants.print_i(getClass(),
						"onItemClick----->position = " + position + " url = "
								+ name);
				playAudio(position);
				if (listener != null) {
					listener.onChangePlay(singer, name);
				}
			}

		});
		searchThread.start();
		// searchHandler.sendEmptyMessageDelayed(2, 1000);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return mSongView;
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		GlobalConstants.print_i(getClass(), "onDestroy");
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
		List<MusicInfo> list = MediaUtils.getMp3Infos(getActivity());
		for (Iterator<MusicInfo> it = list.iterator(); it.hasNext();) {
			MusicInfo info = it.next();
			// GlobalConstants.print_i(getClass(), info.toString());
			mList.add(MediaUtils.getMusicMap(info));
		}
		// mList = MediaUtils.getMusicMaps(list);
		GlobalConstants.print_i(getClass(), "size = " + mList.size());
		searchHandler.sendEmptyMessageDelayed(1, 500);
	}

	private void updateList() {
		mAudioList = MediaUtils.getMp3Infos(getActivity());
		mAdapter.notifyDataSetChanged();
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
		if (!mAudioController.IsConnected()) {
			GlobalConstants.print_i(getClass(), "playAudio Is not Connected");
			if (hConnect == null)
				hConnect = new AudioServiceConnectHandler();
			mAudioController.AddConnectedCallbackHandler(hConnect);
			hConnect.SetPosition(pos);
			return;
		}
		mAudioController.loadMediaList(mAudioList, pos);
		// String tmp = mAudioController.getItem();
		// if (tmp == null) {
		// mAudioController.loadMediaList(mAudioList, pos);
		// } else {
		// if (tmp.compareToIgnoreCase(mAudioList.get(pos).getUrlPath()) != 0) {
		// mAudioController.loadMediaList(mAudioList, pos);
		// }
		// }

		// AudioServiceController.getInstance().bindAudioService(getActivity());
		// mAudioController.addAudioPlayer(this);
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
			case 0:
				owner.searchFile();
				break;
			case 1:
				owner.updateList();
				break;
			case 2:
				// owner.play();
			default:
				break;
			}
		}
	}

	private void play() {
		GlobalConstants.print_i(getClass(), "play ----------");
		VlcAndroidLib.init(getActivity());
		VlcAndroidLib.init(getActivity());
		if (VlcAndroidLib.getInstance() != null) {
			GlobalConstants.print_i(getClass(), "play mVlcLib != null");
			VlcAndroidLib.getInstance().readFile(getActivity(),
					"/mnt/flash/Music/爱与痛的边缘.mp3");

			VlcAndroidLib.getInstance().play(getActivity());
		} else {
			GlobalConstants.print_i(getClass(), "play mVlcLib == null");
		}
	}

	private Thread searchThread = new Thread(new SearchThread());

	private class SearchThread implements Runnable {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			searchHandler.sendEmptyMessage(0);
		}
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateProgress() {
		// TODO Auto-generated method stub

	}

}
