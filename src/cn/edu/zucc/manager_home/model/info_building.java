package cn.edu.zucc.manager_home.model;

import java.util.List;

import javax.swing.JLabel;



public class info_building {
  public static final String[] tblbuildingTitle={"楼盘名称","建筑类型","建筑年代","所在街道","开发商","容积率","绿化率","停车位"};
  private String UIName;
  private String Name;
  private String Data;
  private String Region;
  private String Street;
  private String Developer;
  private String Park;
  private String BuildType;
  private String Ratio;
  private String Density;
  private String UserID;
  public String getCell(int col,List<info_building>allregion,int i){
		if(col==0) return allregion.get(i).getUIName();
		else if(col==1) return allregion.get(i).getBuildType();
		else if(col==2) return allregion.get(i).getData();
		else if(col==3) return allregion.get(i).getStreet();
		else if(col==4) return allregion.get(i).getDeveloper();
		else if(col==5) return allregion.get(i).getRatio();
		else if(col==6) return  allregion.get(i).getDensity();
		else if(col==7) return  allregion.get(i).getPark();
		else return "";
	}
  
public String getUIName() {
	return UIName;
}

public void setUIName(String uIName) {
	UIName = uIName;
}

public String getDensity() {
	return Density;
}
public void setDensity(String density) {
	Density = density;
}
public String getUserID() {
	return UserID;
}
public void setUserID(String userID) {
	UserID = userID;
}

  
public String getName() {
	return Name;
}
public void setName(String name) {
	Name = name;
}

public String getData() {
	return Data;
}
public void setData(String data) {
	Data = data;
}
public String getRegion() {
	return Region;
}
public void setRegion(String region) {
	Region = region;
}
public String getStreet() {
	return Street;
}
public void setStreet(String street) {
	Street = street;
}
public String getDeveloper() {
	return Developer;
}
public void setDeveloper(String developer) {
	Developer = developer;
}
public String getPark() {
	return Park;
}
public void setPark(String park) {
	Park = park;
}
public String getBuildType() {
	return BuildType;
}
public void setBuildType(String buildType) {
	BuildType = buildType;
}
public String getRatio() {
	return Ratio;
}
public void setRatio(String ratio) {
	Ratio = ratio;
}

}
