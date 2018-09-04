package main.VO;

import java.io.Serializable;

public class ReciptConditionVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String part;
	private String type;
	
	ReciptConditionVO (String part, String type){
		this.part = part;
		this.type = type;
	}
	
	public String getPartID() {
		return part;
	}
	
	public String getType() {
		return type;
	}
}
