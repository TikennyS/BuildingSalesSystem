package cn.edu.zucc.manager_home.itf;

import java.util.List;

import cn.edu.zucc.manager_home.model.info_house;
import cn.edu.zucc.manager_home.model.info_sales;
import cn.edu.zucc.manager_home.util.BaseException;

public interface ISalesManager {
	public info_sales sold(info_house house,String CustomerName,String Commission) throws BaseException;
	
	public List<info_sales> loadsales() throws BaseException;

}
