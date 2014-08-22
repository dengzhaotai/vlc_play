package com.dzt.musicplay.player;

import java.util.ArrayList;

import org.videolan.libvlc.Media;
import org.videolan.vlc.util.AudioUtil;
import org.videolan.vlc.util.BitmapCache;

import com.dzt.musicplay.R;
import com.dzt.musicplay.constant.GlobalVariables;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * ÏÔÊ¾×¨¼­Í¼Æ¬
 * 
 * @author Administrator
 * 
 */
public class AlbumsFragment extends Fragment {

	private View mView;
	private ImageView mAlbumsImg;
	private TextView mName;
	private TextView mSinger;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		initWidgets();
		System.out.println("AlbumsFragment-------------------onCreate");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		ArrayList<Media> list = AudioPlayingList.getInstance()
				.GetServicePlayList();
		if (list.size() != 0) {
			Media media = list.get(GlobalVariables.mCurrPos);
			Bitmap cover = AudioUtil.getCover(getActivity(), media, 64);
			if (cover == null) {
				cover = BitmapFactory.decodeResource(getResources(),
						R.drawable.default_music_icon);
				System.out.println("--------------------------------is null");
			}
			mName.setText(media.getTitle());
			mSinger.setText(media.getArtist());
			mAlbumsImg.setImageBitmap(cover);
		}

		return mView;
	}

	private void initWidgets() {
		LayoutInflater inflater = LayoutInflater.from(getActivity());
		mView = inflater.inflate(R.layout.albums_fragment,
				(ViewGroup) getActivity().findViewById(R.id.vp_play), false);
		mAlbumsImg = (ImageView) mView.findViewById(R.id.iv_albums_image);
		mName = (TextView) mView.findViewById(R.id.tv_name_albums);
		mSinger = (TextView) mView.findViewById(R.id.tv_singer_albums);
	}
}
