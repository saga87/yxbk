/**
 * 
 */
package com.wkrj.front.books.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wkrj.data.bean.WkrjData;
import com.wkrj.front.books.dao.WkrjWebBooksDao;

/**
 * @author linpeng
 * @date 2018年5月7日
 */
@Service("wkrjWebBooksService")
@Transactional
public class WkrjWebBooksServiceImpl implements WkrjWebBooksService{

	@Autowired
	private WkrjWebBooksDao DAO;
	
	@Override
	public long countBooks(String books_name,String data_id) {
		return DAO.countBooks(books_name,data_id);
	}

	@Override
	public List<Map<String, Object>> getBooksById(int offset, int rows,
			String books_id) {
		return DAO.getBooksById(offset,rows,books_id);
	}

	@Override
	public List<Map<String, Object>> getBooksFileById(String books_id) {
		return DAO.getBooksFileById(books_id);
	}

	@Override
	public List<Map<String, Object>> listNewBook(int offset, int rows) {
		return DAO.listNewBook(offset, rows);
	}
	@Override
	public List<WkrjData> getData() {
		// TODO Auto-generated method stub
		return DAO.getData();
	}

	@Override
	public List<WkrjData> getSecondData(String data_parent_id) {
		// TODO Auto-generated method stub
		return DAO.getSecondData(data_parent_id);
	}

	@Override
	public boolean addPageviews(String books_id) {
		return DAO.addPageviews(books_id);
	}

	@Override
	public List<Map<String, Object>> listBooks(int offset, int rows,
			String data_id) {
		return DAO.listBooks(offset, rows, data_id);
	}

	@Override
	public List<Map<String, Object>> searchBooks(int offset, int rows,
			String books_name) {
		// TODO Auto-generated method stub
		return DAO.searchBooks(offset, rows, books_name);
	}

}
