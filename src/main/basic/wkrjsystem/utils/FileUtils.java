package wkrjsystem.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public class FileUtils {

	/**
	 * @param args
	 */
	public static List<Map<String, String>> lkh_uploadFile(HttpServletRequest request,String dirname){
		ArrayList<Map<String, String>> newlist=new ArrayList<Map<String,String>>();
		//文件上传
		MultipartHttpServletRequest multipartRequest=(MultipartHttpServletRequest) request;
		try {
			multipartRequest.setCharacterEncoding("UTF-8");
			DateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String data = sdf.format(new Date());
        //文件路径
		String path = "upload/"+dirname+"/"+data+"/";
		String realPath = multipartRequest.getSession().getServletContext().getRealPath("/") + "/" + path;
		File file = new File(realPath);
		if (!file.exists()) {//创建目录
			file.mkdirs();
		}			
		String fileName = "";
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			String fileComponentName=entity.getKey();//获取组件名
			List<MultipartFile> list2 = multipartRequest.getFiles(fileComponentName);
			for (MultipartFile mf : list2) {				
				fileName = mf.getOriginalFilename();// 获取文件名
				if(fileName==null||"".equals(fileName)||""==fileName){//空文件，直接返回
					return null;
				}
				String onlyfileName=fileName.substring(0,fileName.lastIndexOf("."));//只取名字，去掉扩展名
				String extend = fileName.substring(fileName.lastIndexOf(".")+1);// 获取文件扩展名
				SimpleDateFormat df=new SimpleDateFormat("yyyyMMdd");
				String noextfilename=noextfilename=df.format(new Date())+(int)(Math.random()*1000000);			
				String myfilename=noextfilename+"."+extend;//自定义文件名称
				//文件全路径
				String savePath = realPath + myfilename;// 文件保存全路径				
				String fileurl=path+myfilename;
								
				File savefile = new File(savePath);
				// 文件拷贝到指定硬盘目录
				FileCopyUtils.copy(mf.getBytes(), savefile);
				HashMap<String, String> map=new HashMap<String,String>();
				map.put("fileextend", extend);//文件扩展名
				map.put("fileurl", fileurl);//文件相对路径
				map.put("filename", onlyfileName);//文件名	
				map.put("fileComponentName", fileComponentName);//前台文件组件的name值
				newlist.add(map);				
			}			
		}		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return newlist;
				
	}	
	/**
	 * 删除文件
	 * @param realPath
	 * @return
	 */
	public static boolean lkh_delFile(String realPath){
		try {			
			File fileDelete = new File(realPath);
			if (fileDelete.exists() && fileDelete.isFile()) {
				fileDelete.delete();
			}
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}

		return true;
	}
	
	/**
	 * 下载附件
	 * @param request
	 * @param response
	 * @param fileName
	 * @param realName
	 * @throws Exception
	 */
	@RequestMapping("/downloadFile")
    public static void downloadFile(HttpServletRequest request,  
            HttpServletResponse response, String fileName, 
            String realName)  {  
        response.setContentType("text/html;charset=UTF-8");  
//      response.setContentType("text/plain;charset=UTF-8");  
        try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}  
        BufferedInputStream bis = null;  
        BufferedOutputStream bos = null;  
        
        String ctxPath = request.getSession().getServletContext().getRealPath("/");
        
        String downLoadPath = ctxPath + fileName;  
  
        if(null==realName || "".equals(realName)){
        	realName = fileName;
        }
        
        long fileLength = new File(downLoadPath).length();  
  
        try {
			 response.setHeader("Content-disposition", "attachment; filename="  
			        + new String(realName.getBytes("utf-8"), "ISO8859-1"));
			
			 response.setHeader("Content-Length", String.valueOf(fileLength));  
			  
		        bis = new BufferedInputStream(new FileInputStream(downLoadPath));  
		        bos = new BufferedOutputStream(response.getOutputStream());  
		        byte[] buff = new byte[2048];  
		        int bytesRead;  
		        while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {  
		            bos.write(buff, 0, bytesRead);  
		        }  
		        bis.close();  
		        bos.close();  
			
		} catch (Exception e) {
//			response.setHeader("Location", response.encodeRedirectURL("index"));
			e.printStackTrace();
		}  
    }  

}
