package org.tempuri;

import java.util.ArrayList;

public class QueryResult {
	
	private BaseInfo baseInfo;
	private ArrayList<Production> productions;
	
	public BaseInfo getBaseInfo() {
		return baseInfo;
	}

	public void setBaseInfo(BaseInfo baseInfo) {
		this.baseInfo = baseInfo;
	}
	
	public QueryResult() { 
		 productions= new ArrayList<Production>(); 
    } 
	
	public ArrayList<Production> getProductions() {
		return productions;
	}
	public void setProductions(ArrayList<Production> productions) {
		this.productions = productions;
	}
	public void addProduction(Production production) {
		this.productions.add(production);
	}
	
}
