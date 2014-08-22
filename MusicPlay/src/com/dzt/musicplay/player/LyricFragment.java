package com.dzt.musicplay.player;

import com.dzt.musicplay.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * œ‘ æ∏Ë¥ 
 * 
 * @author Administrator
 * 
 */
public class LyricFragment extends Fragment {
	private View mView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		initWidgets();
		System.out.println("LyricFragment-------------------onCreate");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return mView;
	}

	private void initWidgets() {
		LayoutInflater inflater = LayoutInflater.from(getActivity());
		mView = inflater.inflate(R.layout.lyric_fragment,
				(ViewGroup) getActivity().findViewById(R.id.vp_play), false);
	}
}
