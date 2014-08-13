package org.videolan.vlc.audio;

import java.io.Serializable;

/**
 * 音乐信息实体类
 * 
 * @author dzt
 * @date 2014.08.06
 * 
 */
public class MusicInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	private long id; // 歌曲ID
	private String title; // 歌曲名称
	private String album; // 专辑
	private long albumId;// 专辑ID
	private String displayName; // 显示名称
	private String artist; // 歌手名称
	private long duration; // 歌曲时长
	private long size; // 歌曲大小
	private String mimeType; // MIME类型
	private String urlPath; // 歌曲路径
	private String lrcTitle; // 歌词名称
	private String lrcSize; // 歌词大小

	public MusicInfo() {
		super();
	}

	public MusicInfo(String urlPath, boolean flag) {
		this.urlPath = urlPath;
	}

	public MusicInfo(long id, String title, String album, long albumId,
			String displayName, String artist, long duration, long size,
			String mimeType, String urlPath, String lrcTitle, String lrcSize) {
		super();
		this.id = id;
		this.title = title;
		this.album = album;
		this.albumId = albumId;
		this.displayName = displayName;
		this.artist = artist;
		this.duration = duration;
		this.size = size;
		this.mimeType = mimeType;
		this.urlPath = urlPath;
		this.lrcTitle = lrcTitle;
		this.lrcSize = lrcSize;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public long getAlbumId() {
		return albumId;
	}

	public void setAlbumId(long albumId) {
		this.albumId = albumId;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public String getUrlPath() {
		return urlPath;
	}

	public void setUrlPath(String urlPath) {
		this.urlPath = urlPath;
	}

	public String getLrcTitle() {
		return lrcTitle;
	}

	public void setLrcTitle(String lrcTitle) {
		this.lrcTitle = lrcTitle;
	}

	public String getLrcSize() {
		return lrcSize;
	}

	public void setLrcSize(String lrcSize) {
		this.lrcSize = lrcSize;
	}

	@Override
	public String toString() {
		return "MusicInfo [id=" + id + ", title=" + title + ", album=" + album
				+ ", albumId=" + albumId + ", displayName=" + displayName
				+ ", artist=" + artist + ", duration=" + duration + ", size="
				+ size + ", mimeType=" + mimeType + ", urlPath=" + urlPath
				+ ", lrcTitle=" + lrcTitle + ", lrcSize=" + lrcSize + "]";
	}
}
