package cn.edu.zucc.manager_home;

import cn.edu.zucc.manager_home.comtrol.example.ExampleBuildingManager;
import cn.edu.zucc.manager_home.comtrol.example.ExampleCustomerManager;
import cn.edu.zucc.manager_home.comtrol.example.ExampleHouseManager;
import cn.edu.zucc.manager_home.comtrol.example.ExampleRegionManager;
import cn.edu.zucc.manager_home.comtrol.example.ExampleSalesManager;
import cn.edu.zucc.manager_home.comtrol.example.ExampleUserManager;
import cn.edu.zucc.manager_home.itf.IBuildingManager;
import cn.edu.zucc.manager_home.itf.ICustomerManager;
import cn.edu.zucc.manager_home.itf.IHouseManager;
import cn.edu.zucc.manager_home.itf.IReigonManager;
import cn.edu.zucc.manager_home.itf.ISalesManager;
import cn.edu.zucc.manager_home.itf.IUserManager;

public class Manager_HomeUtil {
	public static IUserManager userManager=new ExampleUserManager();
	public static IReigonManager regionManager=new ExampleRegionManager();
	public static IBuildingManager buildingManager=new ExampleBuildingManager();
	public static IHouseManager houseManager=new ExampleHouseManager();
	public static ISalesManager salesManager=new ExampleSalesManager();
	public static ICustomerManager customerManager=new ExampleCustomerManager();
	

}
