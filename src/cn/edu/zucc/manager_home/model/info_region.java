package cn.edu.zucc.manager_home.model;

import java.util.List;

import cn.edu.zucc.manager_home.model.info_region;

public class info_region {
	public static final String[] tableTitles={"ÇøÓòÃû³Æ"};
	private String Name;
	private String UIName;
   
	public String getUIName() {
		return UIName;
	}

	public void setUIName(String uIName) {
		UIName = uIName;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		this.Name = name;
	}
	public String getCell(int col,List<info_region> allregion,int i){
		if(col==0) return allregion.get(i).getUIName();
		else return "";	
	}
}
