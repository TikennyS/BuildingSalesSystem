package cn.edu.zucc.manager_home.itf;

import java.util.List;

import cn.edu.zucc.manager_home.model.info_customer;
import cn.edu.zucc.manager_home.util.BaseException;
import cn.edu.zucc.manager_home.util.BusinessException;

public interface ICustomerManager {

	void addcustomer(String name, String age, String phone, String address,String email) throws BusinessException, BaseException;
	 
	public void deletecustomer(info_customer customer) throws BaseException;
	
	public List<info_customer> search(String keywwords) throws BaseException;

}
