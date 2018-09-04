package main.VO;

import java.io.Serializable;
import java.util.ArrayList;

import main.PO.CouponPO;
import main.PO.CustomerPO;

public class CustomerVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String ID = "";
	private String category = ""; //客户类别
	private String level = "";
	private String name = "";
	private String number = "";
	private String address = "";
	private String email = "";
	private String postCode = "";
	private double receive = 0;//应收
	private double payment = 0;//应付
	private double receivelimit;//应收额度
	private String defaultsalesman = "";//默认业务员
	private String staffID = "";//操作人员id
	private boolean isDeleted = false;
	private ArrayList<CouponVO> couponList = new ArrayList<>(); 
	
	public CustomerVO(CustomerPO po) {
		this.ID = po.getID();
		this.category = po.getCategory();
		this.level = po.getLevel();
		this.name = po.getName();
		this.number = po.getNumber();
		this.address = po.getAddress();
		this.email = po.getEmail();
		this.receive = po.getReceive();
		this.payment = po.getPayment();
		this.receivelimit = po.getReceivelimit();
		this.defaultsalesman = po.getDefaultsalesman();
		this.isDeleted = po.getIsDeleted();
		this.postCode = po.getPostCode();
		if(po.getCouponList()!=null)
			for(CouponPO tmp : po.getCouponList()){
				this.couponList.add(new CouponVO(tmp));
			}
	}
	
	public CustomerVO(String iD, String category, String level, String name, String number, String address,
			String email, String postCode, double receive, double payment, double receivelimit, String defaultsalesman,
			String staffID, boolean isDeleted, ArrayList<CouponVO> couponList) {
		ID = iD;
		this.category = category;
		this.level = level;
		this.name = name;
		this.number = number;
		this.address = address;
		this.email = email;
		this.postCode = postCode;
		this.receive = receive;
		this.payment = payment;
		this.receivelimit = receivelimit;
		this.defaultsalesman = defaultsalesman;
		this.staffID = staffID;
		this.isDeleted = isDeleted;
		this.couponList = couponList;
	}

	public CustomerVO(String iD, String category, String level, String name, String number, String address,
			String email, double receive, double payment, double receivelimit, String defaultsalesman, String date,
			String staffID) {
		ID = iD;
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
		this.staffID = staffID;
	}
	
	public CustomerVO(String iD, String category, String level, String name, String number, String address,
			String email, double receive, double payment, double receivelimit, String defaultsalesman) {
		ID = iD;
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
	
	//删除
	public CustomerVO(String ID, String name,  double receive,double payment, double receivelimit){
		this.ID = ID;
		this.name = name;
		this.receive = receive;
		this.payment = payment;
		this.receivelimit = receivelimit;
	}
	
	public String getID() {
		return ID;
	}
	
	public String getNumber() {
		return number;
	}
	
	public void setNumber(String number) {
		this.number = number;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getLevel() {
		return level;
	}
	
	public void setLevel(String level) {
		this.level = level;
	}
	
	public String getCategory(){
		return category;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public double getReceive() {
		return receive;
	}
	
	public void setReceive(Double receive) {
		this.receive = receive;
	}
	
	public double getPayment() {
		return payment;
	}
	
	public void setPayment(Double payment) {
		this.payment = payment;
	}
	
	public double getReceivelimit() {
		return receivelimit;
	}
	
	public void setReceivelimit(Double receivelimit) {
		this.receivelimit = receivelimit;
	}
	
	public String getDefaultsalesman() {
		return defaultsalesman;
	}
	
	public void setDefaultsalesman(String defaultsalesman){
		this.defaultsalesman = defaultsalesman;
	}

	public String getStaffID() {
		return staffID;
	}

	public void setID(String iD) {
		ID = iD;
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

	public void setStaffID(String staffID) {
		this.staffID = staffID;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public ArrayList<CouponVO> getCouponList() {
		return couponList;
	}

	public void setCouponList(ArrayList<CouponVO> couponList) {
		this.couponList = couponList;
	}

	
	
	
}
