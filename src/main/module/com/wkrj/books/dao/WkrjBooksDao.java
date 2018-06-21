package com.wkrj.books.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.wkrj.books.bean.WkrjBooks;
import com.wkrj.books.bean.WkrjBooksFile;


/**
 * @author: wkrj_syg
 * @date:2018年3月14日 
 * 
 */
public interface WkrjBooksDao {

	/**
	 * 获取列表
	 * @param offset
	 * @param rows
	 * @return
	 */
	List<Map<String,Object>> listBooks(@Param("offset")int offset,@Param("rows")int rows,@Param("books_name")String books_name,@Param("books_id")String books_id,@Param("data_id")String data_id);
	/**
	 * 
	 * @return
	 */
	long  countBooks(@Param("books_name")String books_name,@Param("data_id")String data_id);
	/**
	 * 添加信息
	 * @param books
	 * @return
	 */
	boolean addBooks(WkrjBooks books);
	/**
	 * 更新信息
	 * @param books
	 * @return
	 */
	boolean updateBooks(WkrjBooks books);
	/**
	 * 删除信息
	 * @param books_id
	 * @return
	 */
	boolean deleBooks(@Param("books_id")String books_id);
	
	/**
	 * 获取附件列表
	 * @param books_id
	 * @return
	 */
	List<Map<String,Object>> listBooksFile(@Param("file_id")String file_id,@Param("books_id")String books_id);

	/**
	 * 添加附件信息
	 * @param booksFile
	 * @return
	 */
	boolean addBooksFile(WkrjBooksFile booksFile);
	/**
	 * 更新附件信息
	 * @param booksFile
	 * @return
	 */
	boolean updateBooksFile(WkrjBooksFile booksFile);
	/**
	 * 删除附件信息
	 * @param books_id
	 * @param file_id
	 * @return
	 */
	boolean deleBooksFile(@Param("file_id")String file_id,@Param("books_id")String books_id);
	
}
