package com.wkrj.books.bean;
/**
 * @author: wkrj_syg
 * @date:2018年3月14日 
 * 
 */
public class WkrjBooks {

	private String books_id;//图书id
	private String books_name;//图书名称
	private String books_author;//作者
	private String publishinghouse;//出版社
	private String publishing_time;//出版日期
	private String coverpath;//封面
	private String pagination;//页码
	private String languagetype;//语言类型
	private String content;//介绍
	private String addtime;//发布时间
	private String books_path;//关联图书路径
	private String data_id;//图书分类
	
	
	
	public String getData_id() {
		return data_id;
	}
	public void setData_id(String data_id) {
		this.data_id = data_id;
	}
	public String getBooks_path() {
		return books_path;
	}
	public void setBooks_path(String books_path) {
		this.books_path = books_path;
	}
	public String getBooks_id() {
		return books_id;
	}
	public void setBooks_id(String books_id) {
		this.books_id = books_id;
	}
	public String getBooks_name() {
		return books_name;
	}
	public void setBooks_name(String books_name) {
		this.books_name = books_name;
	}
	public String getBooks_author() {
		return books_author;
	}
	public void setBooks_author(String books_author) {
		this.books_author = books_author;
	}
	public String getPublishinghouse() {
		return publishinghouse;
	}
	public void setPublishinghouse(String publishinghouse) {
		this.publishinghouse = publishinghouse;
	}
	public String getPublishing_time() {
		return publishing_time;
	}
	public void setPublishing_time(String publishing_time) {
		this.publishing_time = publishing_time;
	}
	public String getCoverpath() {
		return coverpath;
	}
	public void setCoverpath(String coverpath) {
		this.coverpath = coverpath;
	}
	public String getPagination() {
		return pagination;
	}
	public void setPagination(String pagination) {
		this.pagination = pagination;
	}
	public String getLanguagetype() {
		return languagetype;
	}
	public void setLanguagetype(String languagetype) {
		this.languagetype = languagetype;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAddtime() {
		return addtime;
	}
	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}
	
	
}
