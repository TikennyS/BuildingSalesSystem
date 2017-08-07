package cn.edu.zucc.manager_home.itf;

import java.util.List;

import cn.edu.zucc.manager_home.model.info_building;
import cn.edu.zucc.manager_home.model.info_region;
import cn.edu.zucc.manager_home.util.BaseException;

public interface IBuildingManager {

	public info_building addBuilding(info_region region2,String Data,String Region,String Street,String Developer,
			String Park,String BuildType,String Ratio,String Density) throws BaseException;
	
	List<info_building> loadbuilding1(info_region curRegion) throws BaseException;

	void deletebuilding(info_building building) throws BaseException;
	public void modify(info_building building,String region,String name,String BuildType,String Data,String Street,
			String Developer,String Ratio,String Density,String Park) throws BaseException;

}
