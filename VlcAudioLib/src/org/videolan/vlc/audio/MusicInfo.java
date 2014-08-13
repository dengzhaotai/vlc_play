package org.videolan.vlc.audio;

import java.io.Serializable;

/**
 * ������Ϣʵ����
 * 
 * @author dzt
 * @date 2014.08.06
 * 
 */
public class MusicInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	private long id; // ����ID
	private String title; // ��������
	private String album; // ר��
	private long albumId;// ר��ID
	private String displayName; // ��ʾ����
	private String artist; // ��������
	private long duration; // ����ʱ��
	private long size; // ������С
	private String mimeType; // MIME����
	private String urlPath; // ����·��
	private String lrcTitle; // �������
	private String lrcSize; // ��ʴ�С

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
