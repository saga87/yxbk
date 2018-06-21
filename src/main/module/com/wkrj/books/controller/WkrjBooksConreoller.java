package com.wkrj.books.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import wkrjsystem.utils.AjaxJson;
import wkrjsystem.utils.FileUtils;
import wkrjsystem.utils.UtilsHelper;

import com.wkrj.books.bean.WkrjBooks;
import com.wkrj.books.bean.WkrjBooksFile;
import com.wkrj.books.service.WkrjBooksService;
import com.wkrj.data.service.WkrjDataService;

/**
 * @author: wkrj_syg
 * @date:2018年3月14日 
 * 
 */
@Controller
@RequestMapping("books/WkrjBooksController")
public class WkrjBooksConreoller {

	@Autowired
	private WkrjBooksService wkrjBooksService;
	
	
	@Autowired
	private WkrjDataService wkrjDataService;
	
	
	@RequestMapping("listBooks")
	@ResponseBody
	public Object listBooks(int pagesize, int page,String books_name,String sortorder,String sortname,String data_id){
		int offset = (page-1) * pagesize;
		long count = wkrjBooksService.countBooks( books_name,data_id );
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		if (count>0) {
			list = wkrjBooksService.listBooks(offset, pagesize, books_name,data_id);
			return UtilsHelper.returnMap(list, count);
		}
		return UtilsHelper.returnMap(list, list.size());
	}
	/**
	 * 获取附件列表
	 * @param books_id
	 * @return
	 */
	@RequestMapping("listBooksFile")
	@ResponseBody
	public Object listBooksFile(String books_id){
		return wkrjBooksService.listFile(null, books_id);
	}
	
	/**
	 * 添加信息
	 * @param books
	 * @return
	 */
	@RequestMapping("addBooks")
	@ResponseBody
	public Object addBooks(HttpServletRequest request,WkrjBooks books,WkrjBooksFile filebooks){
		AjaxJson json = new AjaxJson();
		if (wkrjBooksService.addBooks(books, filebooks)) {
			json.setSuccess(true);
			json.setMsg("添加成功");
		}
		return json;
			
	}
	/**
	 * 更新信息
	 * @param books
	 * @return
	 */
	@RequestMapping("updateBooks")
	@ResponseBody
	public Object updateBooks(WkrjBooks books,WkrjBooksFile filebooks){
		AjaxJson json = new AjaxJson();
		if (wkrjBooksService.updateBooks(books, filebooks)) {
			json.setSuccess(true);
			json.setMsg("更新成功");
		}
		return json;
		
	}
	/**
	 * 删除信息
	 * @param books_id
	 * @return
	 */
	@RequestMapping("deleBooks")
	@ResponseBody
	public Object deleBooks(HttpServletRequest request,String books_id,String path){
		AjaxJson json = new AjaxJson();
		if (wkrjBooksService.deleBooks( request,books_id)) {
			json.setSuccess(true);
			json.setMsg("删除成功");
		}
		return json;
		
	}

	@RequestMapping("uploadFile")
	@ResponseBody
	public Object uploadFile(HttpServletRequest request){
		List<Map<String, String>> list = FileUtils.lkh_uploadFile(request, "books");
		return list;
	}
	
	@RequestMapping("deleFile")
	@ResponseBody
	public Object deleFile(HttpServletRequest request,String path){
		AjaxJson json = new AjaxJson();
		String ctxPath = request.getSession().getServletContext().getRealPath("/");
		if(!"".equals(path) && path != null){
			FileUtils.lkh_delFile(String.format(ctxPath+"%s", path));
			json.setSuccess(true);
			json.setMsg("删除成功");
			return json;
		}
		return json;
	}
	
	/**
	 * 获取数据字典
	 * @param data_parent_id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getDataTree")
	public Object getDataTree(String data_parent_id ){
		List<Map<String,Object>> list = wkrjDataService.getDataList(data_parent_id);
		return list;
		
	}
	
	
}
