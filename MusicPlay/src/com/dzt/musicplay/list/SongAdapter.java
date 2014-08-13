package com.dzt.musicplay.list;

import java.util.HashMap;
import java.util.List;

import org.videolan.vlc.audio.MusicInfo;
import org.videolan.vlc.util.AudioUtil;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dzt.musicplay.R;
import com.dzt.musicplay.utils.MediaUtils;

public class SongAdapter extends BaseAdapter {

	private List<HashMap<String, String>> mList = null;
	private Context mContext = null;
	// 导入布局
	private LayoutInflater mInflater = null;

	SongAdapter(List<HashMap<String, String>> list, Context context) {
		mList = list;
		mContext = context;
		mInflater = LayoutInflater.from(mContext);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.song_list_item, null);
			holder.album = (ImageView) convertView.findViewById(R.id.iv_album);
			holder.name = (TextView) convertView.findViewById(R.id.song_name);
			holder.time = (TextView) convertView.findViewById(R.id.song_time);
			holder.singer = (TextView) convertView
					.findViewById(R.id.song_signer);
			convertView.setTag(holder);
		} else {
			// 如果已经存在就取出TAG
			holder = (ViewHolder) convertView.getTag();
		}
		// 设置list中TextView的显示
		MusicInfo media = new MusicInfo(Integer.parseInt(mList.get(position)
				.get("id")), mList.get(position).get("title"), mList.get(
				position).get("album"), Integer.parseInt(mList.get(position)
				.get("albumId")), mList.get(position).get("displayName"), mList
				.get(position).get("Artist"), 0, Long.parseLong(mList.get(
				position).get("size")), null, mList.get(position).get("url"),
				null, null);
		Bitmap cover = AudioUtil.getCover(mContext, media, 64);

		if (cover == null) {
			holder.album.setImageResource(R.drawable.default_music_icon);
			System.out.println("cover====================null");
		} else {
			holder.album.setImageBitmap(cover);
		}

		holder.name.setText(mList.get(position).get("displayName").toString());
		holder.time.setText(mList.get(position).get("duration").toString());
		holder.singer.setText(mList.get(position).get("Artist").toString());
		return convertView;
	}

	final class ViewHolder {
		ImageView album;
		TextView name;
		TextView time;
		TextView singer;
	}
}
