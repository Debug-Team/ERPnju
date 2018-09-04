package main.PO;

import main.VO.ReciptVO;

public class ReciptPO {
	//所有属性必须为private
	private String id;
	private String state;

	//默认构造方法
	public ReciptPO() {
		this.id = "";
		this.state = "Unckecked";
	}
	
	public ReciptPO(ReciptVO vo) {
		this.id = vo.getId();
		this.state = vo.getState();
	}
	
	public ReciptPO(String id, String state) {
		this.id = id;
		this.state = state;
	}
	
	//所有属性配备get、set方法
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
