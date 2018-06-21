package org.tempuri;

public class BaseInfo {
	 public String corpname;
	 public String corpcode;
	public String getCorpname() {
		return corpname;
	}
	public void setCorpname(String corpname) {
		this.corpname = corpname;
	}
	public String getCorpcode() {
		return corpcode;
	}
	public void setCorpcode(String corpcode) {
		this.corpcode = corpcode;
	}
	@Override
	public String toString() {
		return "BaseInfo [corpname=" + corpname + ", corpcode=" + corpcode
				+ "]";
	}
	
	
}
