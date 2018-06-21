package wkrjsystemdev.module.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import wkrjsystemdev.module.bean.WkrjModule;
import wkrjsystemdev.module.dao.WkrjModuleDao;
import wkrjsystemdev.permission.dao.WkrjPermissionDao;
import wkrjsystemdev.permission.service.WkrjPermissionService;
import wkrjsystemdev.roledev.bean.WkrjRoleMenuDev;
import wkrjsystemdev.roledev.dao.WkrjRoleDevDao;

@Service("wkrjModuleService")
@Transactional
public class WkrjModuleServiceImpl implements WkrjModuleService {

	@Autowired
	private WkrjModuleDao dao;
	
	@Autowired
	private WkrjPermissionDao permissionDao;
	
	@Autowired
	private WkrjPermissionService service;
	
	@Autowired
	private WkrjRoleDevDao roleDao;
	
	//路径
	private String realPath= getClass().getClassLoader().getResource("/").getPath()+ "../../system/icons/";

	@Override
	public List<Map<String,Object>> getLeftMenu(String parentId) {
		
		List<Map<String,Object>> all = new ArrayList<Map<String,Object>>();//记录所有的module
		
		List<WkrjModule> leftMenu = this.dao.getLeftMenu(parentId);
		
		for(WkrjModule module : leftMenu){
			
			Map<String,Object> map = new HashMap<String,Object>();
			Map<String,Object> attributesMap = new HashMap<String,Object>();
			
			map.put("id", module.getModule_id());
			map.put("text", module.getModule_name());
			if(null!=module.getModule_icon() && !"".equals(module.getModule_icon()) && !"".equals(module.getModule_icon()))
			map.put("icon", "system/icons/"+module.getModule_icon()+".png");
			attributesMap.put("url", module.getModule_url());
			//attributesMap.put("icon", "systemdev/icons/"+module.getModule_icon()+".png");
			map.put("attributes", attributesMap);
			
			//不是叶子节点
			if(this.dao.getLeftMenuChildCount(module.getModule_id())>0){
				//map.put("state", "closed");
				map.put("children", this.getLeftMenu(module.getModule_id()));
			}
			
			all.add(map);
		}
		
		return all;
	}
	
	@Override
	public List<Map<String,Object>> getLeftMenuByRole(String parentId,String roleIds) {
		
		List<Map<String,Object>> all = new ArrayList<Map<String,Object>>();//记录所有的menu
		
		if(null!=roleIds && !"".equals(roleIds)){
			
			String roleChildMenuId="";//记录菜单拥有的子节点的id
			String roleMenuId="";//记录角色所拥有的菜单的id
			
			List<WkrjRoleMenuDev> roleMenus = this.roleDao.getMenuByRoleIds(roleIds.split(","));
			
			if(roleMenus.size()>0){
			
				for(WkrjRoleMenuDev roleMenu:roleMenus){
					roleMenuId += roleMenu.getMenu_id();
					roleMenuId +=","; 
				}
				
				for(WkrjRoleMenuDev roleMenu:roleMenus){
					
				List<WkrjModule> menus = this.dao.getMenuById(roleMenu.getMenu_id(),parentId);
				
				if(menus.size()>0){
					
					for(WkrjModule menu : menus){
					
						Map<String,Object> map = new HashMap<String,Object>();
						Map<String,Object> attributesMap = new HashMap<String,Object>();
						
						map.put("id", menu.getModule_id());
						map.put("text", menu.getModule_name());
						map.put("iconCls", menu.getModule_icon());
						attributesMap.put("url", menu.getModule_url());
						attributesMap.put("iconCls", menu.getModule_icon());
						map.put("attributes", attributesMap);
						
						//判断是不是叶子节点
						List<WkrjModule> gridInfo = this.dao.checkNodeisLeaf(menu.getModule_id());
						if(gridInfo.size()>0){
							
							//进入后需要判断包括的子节点是不是被赋予了权限
							for(WkrjModule grid:gridInfo){
								
								roleChildMenuId = grid.getModule_id();
								roleChildMenuId +=","; 
								
								if(roleMenuId.indexOf(roleChildMenuId)>=0){
									
									//map.put("state", "closed");
									map.put("children", this.getLeftMenuByRole(menu.getModule_id(),roleIds));
								}
								
							}
							
						}
						
						all.add(map);
						
					}
						
				}
				
			}
		}
			
	}
		
		return all;
	}

	@Override
	public List<Map<String,Object>> getGridInfo(String parentId) {
		
		List<Map<String,Object>> all = new ArrayList<Map<String,Object>>();//记录所有的module
		
		List<WkrjModule> leftMenu = this.dao.getGridInfo(parentId);
		
		for(WkrjModule module : leftMenu){
			
			Map<String,Object> map = new HashMap<String,Object>();
			Map<String,Object> attrMap = new HashMap<String,Object>();
			
			map.put("id", module.getModule_id());
			map.put("module_id", module.getModule_id());
			map.put("module_url", module.getModule_url());
			map.put("name", module.getModule_name());
			map.put("text", module.getModule_name());
			map.put("module_name", module.getModule_name());
			map.put("module_order", module.getModule_order());
			map.put("module_is_display", (true==module.isModule_is_display()?"1":"0"));
			if(null!=module.getModule_icon() && !"".equals(module.getModule_icon()) && !"".equals(module.getModule_icon()))
			map.put("iconCls","<img src='system/icons/"+module.getModule_icon()+".png'/>'");
			map.put("module_icon", module.getModule_icon());
			map.put("module_other", module.getModule_other());
			map.put("module_parent_id", module.getModule_parent_id());
			map.put("module_level", module.isModule_level());
			
			attrMap.put("module_url", module.getModule_url());
			attrMap.put("module_parent_id", module.getModule_parent_id());
			attrMap.put("module_other", module.getModule_other());
			attrMap.put("module_order", module.getModule_order());
			attrMap.put("module_is_display", module.isModule_is_display());
			
			map.put("attributes", attrMap);
			
			//不是叶子节点
			if(this.dao.getGridInfoChildCount(module.getModule_id())>0){
				//map.put("state", "closed");
				map.put("children", this.getGridInfo(module.getModule_id()));
				//all.addAll(this.getGridInfo(module.getModule_id()));
			}else{
//				return ;
			}
			
			all.add(map);
		}
		
		return all;
	}
	@Override
	public List<Map<String,Object>> getDisplayGridInfo(String parentId) {
		
		List<Map<String,Object>> all = new ArrayList<Map<String,Object>>();//记录所有的module
		
		List<WkrjModule> leftMenu = this.dao.getDisplayGridInfo(parentId);
		
		for(WkrjModule module : leftMenu){
			
			Map<String,Object> map = new HashMap<String,Object>();
			Map<String,Object> attrMap = new HashMap<String,Object>();
			
			map.put("id", module.getModule_id());
			map.put("module_id", module.getModule_id());
			map.put("module_url", module.getModule_url());
			map.put("name", module.getModule_name());
			map.put("text", module.getModule_name());
			map.put("module_name", module.getModule_name());
			map.put("module_order", module.getModule_order());
			map.put("module_is_display", (true==module.isModule_is_display()?"隐藏":"显示"));
			map.put("iconCls", module.getModule_icon());
			if(null!=module.getModule_icon() && !"".equals(module.getModule_icon()) && !"".equals(module.getModule_icon()))
			map.put("icon", "system/icons/"+module.getModule_icon()+".png");
			map.put("module_icon", module.getModule_icon());
			map.put("module_other", module.getModule_other());
			map.put("module_parent_id", module.getModule_parent_id());
			map.put("module_level", module.isModule_level());
			
			attrMap.put("module_url", module.getModule_url());
			attrMap.put("module_parent_id", module.getModule_parent_id());
			attrMap.put("module_other", module.getModule_other());
			attrMap.put("module_order", module.getModule_order());
			attrMap.put("module_is_display", module.isModule_is_display());
			
			map.put("attributes", attrMap);
			
			//不是叶子节点
			if(this.dao.getDisplayGridInfoChildCount(module.getModule_id())>0){
				//map.put("state", "closed");
				map.put("children", this.getDisplayGridInfo(module.getModule_id()));
			}else{
//				return ;
			}
			
			all.add(map);
		}
		
		return all;
	}

	@Override
	public boolean addModule(WkrjModule module) {
		
		return dao.addModule(module);
	}
	
	@Override
	public boolean updateModule(WkrjModule module,String yFileName) {
		
		//如果图标未被使用则删除
		if(!"".equals(yFileName) && !dao.checkeIsHaveUseIcon(yFileName) && !dao.checkeIsHaveUseIconModule(yFileName)){
			this.delIcon(yFileName);
		}
		
		return dao.updateModule(module);
	}
	
	@Override
	public boolean delModule(String id,String icon) {
		
		if(service.delPermissionByModuleId(id)){
			if(dao.delModule(id)){
				if(!dao.checkeIsHaveUseIcon(icon) && !dao.checkeIsHaveUseIconModule(icon)){
					return this.delIcon(icon);
				}else{
					return true;
				}
			}
		}
		
		return false;
	}
	
	/**
	 * 删除图标物理地址
	 * @param icon
	 * @return
	 */
	public boolean delIcon(String icon) {
			
		//删除本地附件
		String sPath = this.realPath+icon+".png";
		File file = new File(sPath);  
		   // 路径为文件且不为空则进行删除  
		if (file.isFile() && file.exists()) {  
			return file.delete();  
		}
		return true;
	}
	
	public String uploadPic(MultipartFile module_icons,HttpServletRequest request,String foledArrress){

		if(null==foledArrress || "".equals(foledArrress)){
			foledArrress="system/icons/";
		}
		
		String realPath = request.getSession().getServletContext().getRealPath("/")+foledArrress;
		
		String yFileName = module_icons.getOriginalFilename();//原文件名
		
		long timeMillis = System.currentTimeMillis();//获取时间戳 这种速度更快一些
		String extra = "wkrj";//由于样式不能是数字所以加上wkrj来区分
		
		String xFileName = extra+timeMillis+extra+yFileName.substring(yFileName.lastIndexOf("."), yFileName.length());//现文件名
		
		try {
			
			FileUtils.copyInputStreamToFile(module_icons.getInputStream(), new File(realPath,xFileName));
//			String content = "."+extra+timeMillis+extra+"{background:url('"+xFileName+"') no-repeat center center;}";
//			this.writeContentToFile(realPath+"/wkrjicon.css", content);
			
			
		} catch (Exception e) {
			System.err.println("上传附件出错了,位于WkrjModuleController.java中");
		}
		return "{\"success\":\"true\",\"filename\":\""+extra+timeMillis+extra+"\"}";
	} 
	
	
	public void writeContentToFile(String file, String conent) {     
        BufferedWriter out = null;     
        try {     
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true)));     
            out.write(conent);     
        } catch (Exception e) {     
            e.printStackTrace();     
        } finally {     
            try {     
                if(out != null){  
                    out.close();     
                }  
            } catch (IOException e) {     
                e.printStackTrace();     
            }     
        }     
    }

	@Override
	public int checkIsHaveChildren(String id) {
		return this.dao.getGridInfoChildCount(id);
	}  
	
}
