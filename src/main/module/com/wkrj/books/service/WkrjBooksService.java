package com.wkrj.books.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;

import com.wkrj.books.bean.WkrjBooks;
import com.wkrj.books.bean.WkrjBooksFile;


/**
 * @author: wkrj_syg
 * @date:2018年3月14日 
 * 
 */
public interface WkrjBooksService {

	/**
	 * 获取列表
	 * @param offset
	 * @param rows
	 * @return
	 */
	List<Map<String,Object>> listBooks(int offset,int rows,String books_name,String data_id);
	/**
	 * 获取信息数量
	 * @return
	 */
	long  countBooks(String books_name,String data_id);
	/**
	 * 添加信息
	 * @param books
	 * @return
	 */
	boolean addBooks(WkrjBooks books,WkrjBooksFile filebooks);
	/**
	 * 更新信息
	 * @param books
	 * @return
	 */
	boolean updateBooks(WkrjBooks books,WkrjBooksFile filebooks);
	/**
	 * 删除信息
	 * @param books_id
	 * @return
	 */
	boolean deleBooks(HttpServletRequest request,String books_id);

	/**
	 * 获取附件列表
	 * @param notice_id
	 * @return
	 */
	List<Map<String,Object>> listFile(@Param("file_id")String file_id,@Param("books_id")String books_id);
	/**
	 * 删除附件信息
	 * @param notice_id
	 * @return
	 */
	boolean deleFile(HttpServletRequest request,@Param("file_id")String file_id,@Param("books_id")String books_id);

}
