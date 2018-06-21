/**
 * 
 */
package com.wkrj.front.books.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wkrj.data.bean.WkrjData;
import com.wkrj.front.books.service.WkrjWebBooksService;

/**
 * @author linpeng
 * @date 2018年5月7日
 */
@Controller
@RequestMapping("front/web/books")
public class WkrjWebBooksController {
	
	@Autowired
	private WkrjWebBooksService wkrjWebService;
	
	@RequestMapping("countBooks")
	@ResponseBody
	public Object countBooks(String books_name,String data_id){
		return wkrjWebService.countBooks(books_name,data_id);
	}
	
	@RequestMapping("listBooks")
	@ResponseBody
	public Object listBooks(int page,int rows,String data_id){
		int offset = (page-1)*rows;
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list = wkrjWebService.listBooks(offset, rows,data_id);
		return list;
	}
	
	
	@RequestMapping("searchBooks")
	@ResponseBody
	public Object searchBooks(int page,int rows,String books_name){
		int offset = (page-1)*rows;
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list = wkrjWebService.searchBooks(offset, rows,books_name);
		return list;
	}
	
	
	@RequestMapping("getBooksById")
	@ResponseBody
	public Object getBooksById(int page,int rows,String books_id){
		int offset = (page-1)*rows;
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		if(null == books_id ||"".equals(books_id)){
			return list;
		}
		list = wkrjWebService.getBooksById(offset, rows, books_id);
		return list;
	}
	
	
	@RequestMapping("getBooksFileById")
	@ResponseBody
	public Object getBooksFileById(String books_id){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list = wkrjWebService.getBooksFileById(books_id);
		return list;
	}
	
	@RequestMapping("listNewBook")
	@ResponseBody
	public Object listNewBook(int page,int rows){
		int offset = (page-1)*rows;
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list = wkrjWebService.listNewBook(offset, rows);
		return list;
	}
	
	@ResponseBody
	@RequestMapping("getData")
	public Object getData(){
		List<WkrjData> list = wkrjWebService.getData();
		return list;
	}
	
	@ResponseBody
	@RequestMapping("getSecondData")
	public Object getSecondData(String data_parent_id){
		List<WkrjData> list = wkrjWebService.getSecondData(data_parent_id);
		return list;
	}
	
//	@ResponseBody
//	@RequestMapping("getPDF")
//	public void getPDF(HttpServletRequest request
//			,HttpServletResponse response,String path){
//		try {
//			request.setCharacterEncoding("utf-8");
//			response.setCharacterEncoding("utf-8");
//			if(path!=null&&!"".equals(path)){
//				URL pdfpath = new URL("http://172.19.35.41:8078/examples/books/a.pdf");
//				HttpURLConnection con = (HttpURLConnection) pdfpath.openConnection();
//				con.setConnectTimeout(20000);
//				con.setReadTimeout(20000);
//				con.connect();
//				response.setContentType("application/pdf;charset=UTF-8");  
//				OutputStream os = response.getOutputStream();
//				InputStream is = con.getInputStream();
//				int b;
//				while((b=is.read())!=-1){
//					os.write(b);
//				}
//				os.close();
//				is.close();
//			}
//			
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		} catch (MalformedURLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
	
	@ResponseBody
	@RequestMapping("addPageviews")
	public Object addPageviews(String books_id){
		return wkrjWebService.addPageviews(books_id);
	}
	
	
}
