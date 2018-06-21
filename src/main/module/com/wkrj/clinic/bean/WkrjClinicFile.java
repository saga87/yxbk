/**
 * 
 */
package com.wkrj.clinic.bean;

/**
 * @author linpeng
 * @date 2018年4月24日
 */
public class WkrjClinicFile {

	private String file_id;//附件id
	private String clinic_id;//临床id
	private String file_name;//附件名称
	private String file_size;//附件大小
	private String file_path;//附件路径
	private String file_type;//附件类型
	
	public String getFile_id() {
		return file_id;
	}
	public void setFile_id(String file_id) {
		this.file_id = file_id;
	}
	public String getClinic_id() {
		return clinic_id;
	}
	public void setClinic_id(String clinic_id) {
		this.clinic_id = clinic_id;
	}
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	public String getFile_size() {
		return file_size;
	}
	public void setFile_size(String file_size) {
		this.file_size = file_size;
	}
	public String getFile_path() {
		return file_path;
	}
	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}
	public String getFile_type() {
		return file_type;
	}
	public void setFile_type(String file_type) {
		this.file_type = file_type;
	}
	
	
}
