package com.dzt.musicplay.list;

import com.dzt.musicplay.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SingerFragment extends Fragment {

	private View mView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		initWidgets();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return mView;
	}

	private void initWidgets() {
		LayoutInflater inflater = LayoutInflater.from(getActivity());
		mView = inflater.inflate(R.layout.singer_fragment,
				(ViewGroup) getActivity().findViewById(R.id.tabpage_frame),
				false);
	}
}
