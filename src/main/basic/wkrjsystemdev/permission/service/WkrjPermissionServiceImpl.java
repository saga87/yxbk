package wkrjsystemdev.permission.service;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wkrjsystemdev.permission.bean.WkrjPermission;
import wkrjsystemdev.permission.dao.WkrjPermissionDao;


@Service("wkrjPermissionService")
@Transactional
public class WkrjPermissionServiceImpl implements WkrjPermissionService{

	//路径
	private String realPath= getClass().getClassLoader().getResource("/").getPath()+ "../../system/icons/";
	
	@Autowired
	private WkrjPermissionDao dao;
	
	@Override
	public List<WkrjPermission> getPermissionList(int offset, int page, String module_id) {
		return dao.getPermissionList(offset, page, module_id);
	}

	@Override
	public long getPermissionListCount(String module_id) {
		return dao.getPermissionListCount(module_id);
	}

	@Override
	public boolean addPermission(WkrjPermission module) {
		return this.dao.addPermission(module);
	}

	@Override
	public boolean updatePermission(WkrjPermission module) {
		return this.dao.updatePermission(module);
	}

	@Override
	public boolean delPermission(String id, String icon) {
		
		if(dao.delPermission(id)){
			return this.delIcon(icon);
		}
		
		return false;
	}
	
	/**
	 * 删除权限通过模块id
	 * 首先删除记录然后再删除图标
	 * @param moduleId
	 * @return
	 */
	public boolean delPermissionByModuleId(String moduleId){
		
		//通过moduleId获取所有的权限
		List<WkrjPermission> list = this.dao.getPermissionByModuleId(moduleId);
		boolean sus=true;
		
		if(list.size()>0){
			
			for(WkrjPermission p:list){
				sus = this.delPermission(p.getPerm_id(), p.getPerm_icon());
				if(!sus){
					return false;
				}
			}
				
		}else{
			return sus;
		}
		
		
		return sus;
	}
	
	
	/**
	 * 删除图标物理地址
	 * @param icon
	 * @return
	 */
	public boolean delIcon(String icon) {
			
		//删除本地附件
		if(null==icon || "".equals(icon) || "zxh".equals(icon)){
			return true;
		}else{
			String sPath = this.realPath+icon+".png";
			File file = new File(sPath);  
			   // 路径为文件且不为空则进行删除  
			if (file.isFile() && file.exists()) {  
				return file.delete();  
			}else{
				return true;
			}
		}
	}

	@Override
	public boolean checkIsHavePermission(String perm_flag,String id) {
		
		int count = this.dao.checkIsHavePermission(perm_flag,id);
		if(count>0){
			return true;
		}else{
			return false;
		}
		
	}

}