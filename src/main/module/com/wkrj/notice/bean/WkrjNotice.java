package com.wkrj.notice.bean;
/**
 * @author: wkrj_syg
 * @date:2018年3月14日 
 * 
 */
public class WkrjNotice {

	private String notice_id;//工匠id
	private String notice_title;//标题
	private String notice_content;//内容
	private String addtime;//发布时间
	public String getNotice_id() {
		return notice_id;
	}
	public void setNotice_id(String notice_id) {
		this.notice_id = notice_id;
	}
	public String getNotice_title() {
		return notice_title;
	}
	public void setNotice_title(String notice_title) {
		this.notice_title = notice_title;
	}
	public String getNotice_content() {
		return notice_content;
	}
	public void setNotice_content(String notice_content) {
		this.notice_content = notice_content;
	}
	public String getAddtime() {
		return addtime;
	}
	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}
	
	
	
	
}
