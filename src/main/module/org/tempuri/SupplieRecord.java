package org.tempuri;

public class SupplieRecord {

	private int id;
	private String corpName;//农资店名称
	private String corpCode;//营业执照号
	private String orderNO;//销售订单号
	private String dealerName;//客户姓名
	private String dealerNO;//客户身份证号
	private String orderTime;//销售日期
	private String cHPName;//产品名称
	private String cHPCode;//产品备案号
	private float saleNum;//货品数量
	private String guige;//产品规格

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCorpName() {
		return corpName;
	}

	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}

	public String getCorpCode() {
		return corpCode;
	}

	public void setCorpCode(String corpCode) {
		this.corpCode = corpCode;
	}

	public String getOrderNO() {
		return orderNO;
	}

	public void setOrderNO(String orderNO) {
		this.orderNO = orderNO;
	}

	public String getDealerName() {
		return dealerName;
	}

	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}

	public String getDealerNO() {
		return dealerNO;
	}

	public void setDealerNO(String dealerNO) {
		this.dealerNO = dealerNO;
	}

	public String getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}

	public String getcHPName() {
		return cHPName;
	}

	public void setcHPName(String cHPName) {
		this.cHPName = cHPName;
	}

	public String getcHPCode() {
		return cHPCode;
	}

	public void setcHPCode(String cHPCode) {
		this.cHPCode = cHPCode;
	}

	public float getSaleNum() {
		return saleNum;
	}

	public void setSaleNum(float saleNum) {
		this.saleNum = saleNum;
	}

	public String getGuige() {
		return guige;
	}

	public void setGuige(String guige) {
		this.guige = guige;
	}

}
