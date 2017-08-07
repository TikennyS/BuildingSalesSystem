package cn.edu.zucc.manager_home.itf;

import java.util.List;

import cn.edu.zucc.manager_home.model.info_building;
import cn.edu.zucc.manager_home.model.info_region;
import cn.edu.zucc.manager_home.util.BaseException;

public interface IReigonManager {
	public info_region addRegion(String name) throws BaseException;
	
	List<info_region> loadAll() throws BaseException;

	public void deleteRegion(info_region region) throws BaseException;

	public void modify(info_region region, String name) throws BaseException;

}
