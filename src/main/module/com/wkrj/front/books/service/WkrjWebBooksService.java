/**
 * 
 */
package com.wkrj.front.books.service;

import java.util.List;
import java.util.Map;



import com.wkrj.data.bean.WkrjData;

/**
 * @author linpeng
 * @date 2018年5月7日
 */
public interface WkrjWebBooksService {
	long countBooks(String books_name,String data_id); 
	List<Map<String,Object>> getBooksById(int offset,int rows,String books_id);
	
	List<Map<String,Object>> getBooksFileById(String books_id);
	List<Map<String,Object>> listNewBook(int offset,int rows);
	
	List<WkrjData> getData();
	
	List<WkrjData> getSecondData(String data_parent_id);
	
	boolean addPageviews(String books_id);
	
	List<Map<String,Object>> listBooks(int offset,int rows,String data_id);
	
	List<Map<String,Object>> searchBooks(int offset,int rows,String books_name);
	
}
