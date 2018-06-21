package wkrjsystem.utils;

import java.io.IOException;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class UtilsHelper {
	
	/**
	 * 时间格式化
	 */
	public static DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static Map<String, Object> returnMap(List<?> list, long size) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("Rows", list);
		map.put("Total", size);

		return map;
	}
	
	/**
	 * 获取当前时间
	 * @return
	 */
	public static String getDateFormatTime(){
		return df.format(new Date());
	}

	/**
	* 创建指定数量的随机字符串 add by SYG 201814
	* @param numberFlag ture数字 fasle 大写字母
	* @param length 字符串长度
	* @return
	 */
	public static String capitalRandom(boolean numberFlag, int length){
		 String retStr = "";
		 String strTable = numberFlag ? "1234567890" : "1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		 int len = strTable.length();
		 boolean bDone = true;
		  retStr = "";
		  int count = 0;
		  for (int i = 0; i < length; i++) {
		  double dblR = Math.random() * len;
		  int intR = (int) Math.floor(dblR);
		  char c = strTable.charAt(intR);
		  if (('0' <= c) && (c <= '9')) {
		   count++;
		  }
		  retStr += strTable.charAt(intR);
		  }
		 return retStr;
	}
	
	/*
	 * 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址;
	 * 
	 * @param request
	 * 
	 * @return
	 * 
	 * @throws IOException
	 */
	public final static String getIpAddress(HttpServletRequest request)
			throws IOException {
		// 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址

		String ip = request.getHeader("X-Forwarded-For");

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			if (ip == null || ip.length() == 0
					|| "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("Proxy-Client-IP");
			}
			if (ip == null || ip.length() == 0
					|| "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("WL-Proxy-Client-IP");
			}
			if (ip == null || ip.length() == 0
					|| "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("HTTP_CLIENT_IP");
			}
			if (ip == null || ip.length() == 0
					|| "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("HTTP_X_FORWARDED_FOR");
			}
			if (ip == null || ip.length() == 0
					|| "unknown".equalsIgnoreCase(ip)) {
				ip = request.getRemoteAddr();
			}
		} else if (ip.length() > 15) {
			String[] ips = ip.split(",");
			for (int index = 0; index < ips.length; index++) {
				String strIp = (String) ips[index];
				if (!("unknown".equalsIgnoreCase(strIp))) {
					ip = strIp;
					break;
				}
			}
		}
		return ip;
	}

}
