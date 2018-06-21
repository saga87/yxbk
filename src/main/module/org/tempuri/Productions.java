package org.tempuri;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement  
public class Productions {

	private List<Production> pros;
	private BaseInfo info;

	public List<Production> getPros() {
		return pros;
	}

	public void setPros(List<Production> pros) {
		this.pros = pros;
	}

	public BaseInfo getInfo() {
		return info;
	}

	public void setInfo(BaseInfo info) {
		this.info = info;
	}

	 //无参够着函数一定需要，否则JXBContext无法正常解析。  
	 public Productions() {  
	     super();  
	 }  

}
