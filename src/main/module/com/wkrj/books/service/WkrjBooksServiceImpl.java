package com.wkrj.books.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wkrj.books.bean.WkrjBooks;
import com.wkrj.books.bean.WkrjBooksFile;
import com.wkrj.books.dao.WkrjBooksDao;

import wkrjsystem.utils.FileUtils;
import wkrjsystem.utils.Guid;
import wkrjsystem.utils.UtilsHelper;


/**
 * @author: wkrj_syg
 * @date:2018年3月14日 
 * 
 */
@Service("wkrjBooksService")
@Transactional
public class WkrjBooksServiceImpl implements WkrjBooksService{
	
	@Autowired
	private WkrjBooksDao DAO;
		
	@Override
	public List<Map<String, Object>> listBooks(int offset,int rows,String books_name,String data_id) {
		// TODO Auto-generated method stub
		return DAO.listBooks( offset, rows, books_name,null,data_id);
	}

	@Override
	public long countBooks(String books_name,String data_id) {
		// TODO Auto-generated method stub
		return DAO.countBooks(  books_name,data_id );
	}

	@Override
	public boolean addBooks(WkrjBooks books,WkrjBooksFile filebooks) {
		// TODO Auto-generated method stub
		try {
			String books_id = Guid.getGuid();
			books.setBooks_id(books_id);
			books.setAddtime(UtilsHelper.getDateFormatTime());
			String path = filebooks.getFile_path();
			if (!"".equals(path) && path != null) {
				String [] names = filebooks.getFile_name().split(",");
				String [] paths = filebooks.getFile_path().split(",");
				String [] types = filebooks.getFile_type().split(",");
				for (int i = 0; i < paths.length; i++) {
					WkrjBooksFile file = new WkrjBooksFile();
					file.setBooks_id(books_id);
					file.setFile_id(Guid.getGuid());
					file.setFile_name(names[i]);
					file.setFile_path(paths[i]);
					file.setFile_type(types[i]);
					DAO.addBooksFile(file);
				}
			}
			DAO.addBooks(books);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean updateBooks(WkrjBooks books,WkrjBooksFile filebooks) {
		// TODO Auto-generated method stub
		try {
			//先删除数据之前的附件信息重新添加
			DAO.deleBooksFile(null, books.getBooks_id());
			String path = filebooks.getFile_path();
			if (!"".equals(path) && path != null) {
				String [] names = filebooks.getFile_name().split(",");
				String [] paths = filebooks.getFile_path().split(",");
				String [] types = filebooks.getFile_type().split(",");
				for (int i = 0; i < paths.length; i++) {
					WkrjBooksFile file = new WkrjBooksFile();
					file.setBooks_id(books.getBooks_id());
					file.setFile_id(Guid.getGuid());
					file.setFile_name(names[i]);
					file.setFile_path(paths[i]);
					file.setFile_type(types[i]);
					DAO.addBooksFile(file);
				}
			}
			DAO.updateBooks(books);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean deleBooks(HttpServletRequest request,String books_id) {
		// TODO Auto-generated method stub
		try {
			String idesString [] = books_id.split(",");
			for (int i = 0; i < idesString.length; i++) {
				String idString = idesString[i];
				this.deleFile(request, null, idString);
				DAO.deleBooks(idString);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		return true;
	}


	@Override
	public List<Map<String, Object>> listFile(String file_id, String books_id) {
		// TODO Auto-generated method stub
		return DAO.listBooksFile(file_id,books_id);
	}

	@Override
	public boolean deleFile(HttpServletRequest request, String file_id,
			String books_id) {
		// TODO Auto-generated method stub
		String ctxPath = request.getSession().getServletContext().getRealPath("/");
		if (file_id != null) {
			List<Map<String,Object>> list = DAO.listBooksFile(file_id, null);
			if (list.size()>0) {
				String path = list.get(0).get("file_path").toString();
				FileUtils.lkh_delFile(String.format(ctxPath+"%s", path));
				return DAO.deleBooksFile(file_id, null);
			}
		}
		if (books_id != null) {
			List<Map<String,Object>> booklist = DAO.listBooks(0, 10, null, books_id,null);
			if (booklist.get(0).containsKey("coverpath")) {
				String coverpath = booklist.get(0).get("coverpath").toString();
				FileUtils.lkh_delFile(String.format(ctxPath+"%s", coverpath));
			}
			List<Map<String,Object>> filelist = DAO.listBooksFile(null, books_id);
			if (filelist.size()>0) {
				for (int i = 0; i < filelist.size(); i++) {
					String path = filelist.get(i).get("file_path").toString();
					FileUtils.lkh_delFile(String.format(ctxPath+"%s", path));
				}
				if(!DAO.deleBooksFile(null, books_id)){
					return false;
				}
			}
		}
		return true;
	}


	
}
