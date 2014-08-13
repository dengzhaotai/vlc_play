/*
 * Copyright (C) Apical
 */
package com.dzt.musicplay;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dzt.musicplay.widgets.CustomImageButton;

public class TabFragment extends Fragment {

	private CustomImageButton btn_SongList;
	private CustomImageButton btn_SingerList;
	private CustomImageButton m_btnWhichSel; // 保存处于选中状态的按钮
	private View m_tabview;
	private OnTabClickListener m_tabClickListener;
	private int m_curTabIdx = -1;

	static interface OnTabClickListener {
		void onTabClick(int tabIndex);
	}

	public void setOnTabClickListerner(OnTabClickListener listener) {
		m_tabClickListener = listener;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		m_tabview = inflater.inflate(R.layout.tab_fragment, container, false);
		initComponents();
		return m_tabview;
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	private void initComponents() {

		btn_SongList = (CustomImageButton) m_tabview
				.findViewById(R.id.btn_song);
		btn_SongList.setOnClickListener(new ClickEvent());

		btn_SingerList = (CustomImageButton) m_tabview
				.findViewById(R.id.btn_singer);
		btn_SingerList.setOnClickListener(new ClickEvent());
		setBtnOnSel(0);
	}

	/**
	 * 设置按钮是否被选中
	 * 
	 * @param idxBtn
	 *            :表示要选中的是哪个Btn
	 */
	public void setBtnOnSel(int idxBtn) {
		if (m_btnWhichSel != null)
			m_btnWhichSel.setSelected(false);
		switch (idxBtn) {
		case 0:
			btn_SongList.setSelected(true);
			m_btnWhichSel = btn_SongList;
			m_curTabIdx = 0;
			break;
		case 1:
			btn_SingerList.setSelected(true);
			m_btnWhichSel = btn_SingerList;
			m_curTabIdx = 1;
			break;
		default:
			m_curTabIdx = -1;
			break;
		}
	}

	public int getCurTabPos() {
		return m_curTabIdx;
	}

	class ClickEvent implements View.OnClickListener {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub

			if (arg0 == btn_SongList) {
				setBtnOnSel(0);
				if (m_tabClickListener != null)
					m_tabClickListener.onTabClick(0);
			} else if (arg0 == btn_SingerList) {
				setBtnOnSel(1);
				if (m_tabClickListener != null)
					m_tabClickListener.onTabClick(1);
			}
		}

	}
}
