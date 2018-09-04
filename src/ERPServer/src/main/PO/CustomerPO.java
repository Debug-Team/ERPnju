package main.PO;

import java.util.ArrayList;

import main.VO.CouponVO;
import main.VO.CustomerVO;

public class CustomerPO {
	private String ID;
	private String category; // 客户类别
	private String level;
	private String name;
	private String number;
	private String address;
	private String email;
	private String postCode;
	private double receive;// 应收
	private double payment;// 应付
	private double receivelimit;// 应收额度
	private String defaultsalesman;// 默认业务员
	private boolean isDeleted;
	private ArrayList<CouponPO> couponList = new ArrayList<>();
	
	public CustomerPO() {}
	
	public CustomerPO(CustomerVO vo) {
		this.ID = vo.getID();
		this.category = vo.getCategory();
		this.level = vo.getLevel();
		this.name = vo.getName();
		this.number = vo.getNumber();
		this.address = vo.getAddress();
		this.email = vo.getEmail();
		this.receive = vo.getReceive();
		this.payment = vo.getPayment();
		this.receivelimit = vo.getReceivelimit();
		this.defaultsalesman = vo.getDefaultsalesman();
		this.isDeleted = vo.isDeleted();
		this.postCode = vo.getPostCode();
		for(CouponVO tmp : vo.getCouponList()){
			this.couponList.add(new CouponPO(tmp));
		}
	}

	public CustomerPO(String ID, String category, String level, String name, String number, String address,
			String email, double receive, double payment, double receivelimit, String defaultsalesman) {
		this.ID = ID;
		this.category = category;
		this.level = level;
		this.name = name;
		this.number = number;
		this.address = address;
		this.email = email;
		this.receive = receive;
		this.payment = payment;
		this.receivelimit = receivelimit;
		this.defaultsalesman = defaultsalesman;
	}

	// 删除
	public CustomerPO(String ID, String name, double receive, double payment, double receivelimit) {
		this.ID = ID;
		this.name = name;
		this.receive = receive;
		this.payment = payment;
		this.receivelimit = receivelimit;
	}

	// 删除
	public boolean modify(String string, String level, String number, String address, String email, double receivelimit,
			String defaultsalesman) {
		this.address = address;
		this.category = string;
		this.level = level;
		this.number = number;
		this.email = email;
		this.receivelimit = receivelimit;
		this.defaultsalesman = defaultsalesman;
		return true;
	}

	public String getID() {
		return ID;
	}

	public String getCategory() {
		return category;
	}

	public String getLevel() {
		return level;
	}

	public String getName() {
		return name;
	}

	public String getNumber() {
		return number;
	}

	public String getAddress() {
		return address;
	}

	public String getEmail() {
		return email;
	}

	public double getReceive() {
		return receive;
	}

	public double getPayment() {
		return payment;
	}

	public double getReceivelimit() {
		return receivelimit;
	}

	public String getDefaultsalesman() {
		return defaultsalesman;
	}

	public boolean getIsDeleted() {
		return isDeleted;
	}
	
	public void setID(String iD) {
		ID = iD;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setReceive(double receive) {
		this.receive = receive;
	}

	public void setPayment(double payment) {
		this.payment = payment;
	}

	public void setReceivelimit(double receivelimit) {
		this.receivelimit = receivelimit;
	}

	public void setDefaultsalesman(String defaultsalesman) {
		this.defaultsalesman = defaultsalesman;
	}

	public void setIsDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public ArrayList<CouponPO> getCouponList() {
		return couponList;
	}

	public void setCouponList(ArrayList<CouponPO> couponList) {
		this.couponList = couponList;
	}

}
