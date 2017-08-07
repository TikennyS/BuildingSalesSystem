package cn.edu.zucc.manager_home.model;
import java.util.List;

import cn.edu.zucc.manager_home.util.BaseException;

public class info_house {
	public static final String[] tblhouseTitle={"地址","楼层","总楼层","房屋类型","朝向","面积","报价","状态"};

	  private String Name;
	  private String address;
	  private String floor;
	  private String total;
	  private String orientation;
	  private String area;
	  private String price;
	  private String type;
	  private String userid;
	  private String status;
	  private String customername;
	  public String getCell(int col,List<info_house>allregion,int i){
		    if(col==0) return allregion.get(i).getAddress();
			else if(col==1) return allregion.get(i).getFloor();
			else if(col==2) return allregion.get(i).getTotal();
			else if(col==3) return allregion.get(i).getType();
			else if(col==4) return allregion.get(i).getOrientation();
			else if(col==5) return allregion.get(i).getArea();
			else if(col==6) return  allregion.get(i).getPrice();
			else if(col==7) return  allregion.get(i).getStatus();
			else return "";
		}
	  
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCustomername() {
		return customername;
	}
	public void setCustomername(String customername) {
		this.customername = customername;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getFloor() {
		return floor;
	}
	public void setFloor(String floor) {
		this.floor = floor;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getOrientation() {
		return orientation;
	}
	public void setOrientation(String orientation) {
		this.orientation = orientation;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	  
}
