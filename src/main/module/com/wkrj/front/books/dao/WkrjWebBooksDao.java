/**
 * 
 */
package com.wkrj.front.books.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.wkrj.data.bean.WkrjData;

/**
 * @author linpeng
 * @date 2018年5月7日
 */
public interface WkrjWebBooksDao {

	long countBooks(@Param("books_name")String books_name,@Param("data_id")String data_id);
	
	List<Map<String,Object>> getBooksById(@Param("offset")int offset,@Param("rows")int rows,@Param("books_id")String books_id);
	List<Map<String, Object>> getBooksFileById(@Param("books_id")String books_id);
	
	List<Map<String,Object>> listNewBook(@Param("offset")int offset,@Param("rows")int rows);
	
	List<WkrjData> getData();
	
	List<WkrjData> getSecondData(@Param("data_parent_id")String data_parent_id);
	
	boolean addPageviews(@Param("books_id")String books_id); 
	
	List<Map<String,Object>> listBooks(@Param("offset")int offset,@Param("rows")int rows,@Param("data_id")String data_id);
	
	List<Map<String,Object>> searchBooks(@Param("offset")int offset,@Param("rows")int rows,@Param("books_name")String books_name);
}
