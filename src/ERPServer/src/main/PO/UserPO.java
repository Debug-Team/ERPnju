package main.PO;

import main.VO.UserVO;

public class UserPO {
	
	private int id;
	private String name;
	private String sex;  //male and female
	private int age;
	private String jobNum;
	private String password;
	private String type;  //FinancialStaff, Manager, SalesMan, StockManager
	private boolean isSupremeAuthority;
	
	public UserPO() {}
	
	public UserPO(UserVO vo) {
		this.id = vo.getId();
		this.name = vo.getName();
		this.sex = vo.getSex();
		this.age = vo.getAge();
		this.jobNum = vo.getJobNum();
		this.password = vo.getPassword();
		this.type = vo.getType();
		this.isSupremeAuthority = vo.getIsSupremeAuthority();
	}
	
	public UserPO(String name, String sex, int age, String jobNum, String password, String type, boolean isSupremeAuthority) {
		this.name = name;
		this.sex = sex;
		this.age = age;
		this.jobNum = jobNum;
		this.password = password;
		this.type = type;
		this.isSupremeAuthority = isSupremeAuthority;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getJobNum() {
		return jobNum;
	}

	public void setJobNum(String jobNum) {
		this.jobNum = jobNum;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean getIsSupremeAuthority() {
		return isSupremeAuthority;
	}

	public void setIsSupremeAuthority(boolean isSupremeAuthority) {
		this.isSupremeAuthority = isSupremeAuthority;
	}

}
