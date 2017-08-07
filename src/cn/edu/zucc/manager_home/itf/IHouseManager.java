package cn.edu.zucc.manager_home.itf;

import java.util.List;

import cn.edu.zucc.manager_home.model.info_building;
import cn.edu.zucc.manager_home.model.info_house;
import cn.edu.zucc.manager_home.util.BaseException;

public interface IHouseManager {

	public info_house addhouse(info_building building,  String address,
			String floor, String total,String type, String orientation, String area,
			String price) throws BaseException;
	public List<info_house> loadhouse(info_building building) throws BaseException;
	
	public void deletehouse(info_house house) throws BaseException;
}
