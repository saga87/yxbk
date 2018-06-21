package com.wkrj.video.bean;
/**
 * @author: wkrj_syg
 * @date:2018年3月14日 
 * 
 */
public class WkrjVideo {

	private String video_id;//图书id
	private String video_name;//图书名称
	private String speaker;//主讲人
	private String speaker_unit;//主讲人单位
	private String data_id;//视频分类
	private String coverpath;//封面
	private String recommend;//是否推荐
	private String content;//介绍
	private String recommendtime;//推荐时间
	private String addtime;//发布时间
	private String video_path;
	
	
	public String getVideo_path() {
		return video_path;
	}
	public void setVideo_path(String video_path) {
		this.video_path = video_path;
	}
	public String getVideo_id() {
		return video_id;
	}
	public void setVideo_id(String video_id) {
		this.video_id = video_id;
	}
	public String getVideo_name() {
		return video_name;
	}
	public void setVideo_name(String video_name) {
		this.video_name = video_name;
	}
	public String getSpeaker() {
		return speaker;
	}
	public void setSpeaker(String speaker) {
		this.speaker = speaker;
	}
	public String getSpeaker_unit() {
		return speaker_unit;
	}
	public void setSpeaker_unit(String speaker_unit) {
		this.speaker_unit = speaker_unit;
	}
	public String getData_id() {
		return data_id;
	}
	public void setData_id(String data_id) {
		this.data_id = data_id;
	}
	public String getCoverpath() {
		return coverpath;
	}
	public void setCoverpath(String coverpath) {
		this.coverpath = coverpath;
	}
	public String getRecommend() {
		return recommend;
	}
	public void setRecommend(String recommend) {
		this.recommend = recommend;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRecommendtime() {
		return recommendtime;
	}
	public void setRecommendtime(String recommendtime) {
		this.recommendtime = recommendtime;
	}
	public String getAddtime() {
		return addtime;
	}
	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}
	
	
	
}
