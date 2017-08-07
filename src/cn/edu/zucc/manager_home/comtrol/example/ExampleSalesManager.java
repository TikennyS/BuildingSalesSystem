package cn.edu.zucc.manager_home.comtrol.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.edu.zucc.manager_home.itf.ISalesManager;
import cn.edu.zucc.manager_home.model.info_building;
import cn.edu.zucc.manager_home.model.info_customer;
import cn.edu.zucc.manager_home.model.info_house;
import cn.edu.zucc.manager_home.model.info_sales;
import cn.edu.zucc.manager_home.util.BaseException;
import cn.edu.zucc.manager_home.util.BusinessException;
import cn.edu.zucc.manager_home.util.DBUtil;
public class ExampleSalesManager implements ISalesManager {
	public info_sales sold(info_house house,String CustomerName,String Commission) throws BaseException
	{
	if(CustomerName==null) throw new BusinessException("客户姓名不能为空");
	try{Connection conn=null;
	PreparedStatement st=null;
	ResultSet rs=null;
		String sql;
		Date date=new Date();
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String time=format.format(date); 
		conn=DBUtil.getConnection();
		sql="select Name from info_customer where Name=?";
		st=conn.prepareStatement(sql);
		st.setString(1,CustomerName);
		rs=st.executeQuery();
		if(!rs.next()) throw new BusinessException("请先添加该客户信息");
		
		sql="select Status from info_house where Status=? and Address=?";
		st=conn.prepareStatement(sql);
		st.setString(1,"SOLD");
		st.setString(2,house.getAddress());
		rs=st.executeQuery();
		if(rs.next()) throw new BusinessException("该房屋已经出售");
		
		sql="insert into info_sales(Time,HouseName,CustomerName,Commission,UserID) values(?,?,?,?,?)";
		st=conn.prepareStatement(sql);
		st.setString(1,time);
    	st.setString(2,house.getAddress());
    	st.setString(3,CustomerName);
    	st.setString(4,Commission);
		st.setString(5,ExampleUserManager.loginname);
		st.executeUpdate();
		st.close();
		
		sql="update info_house set Status=?,CustomerName=? where Address=?";
		st=conn.prepareStatement(sql);
		st.setString(1,"SOLD");
		st.setString(2,CustomerName);
		st.setString(3,house.getAddress());
		st.execute();
		st.close();
		conn.close();
	}catch(Exception e){
		throw new BaseException(e.getMessage());}	
		return null;
		
	}
	public List<info_sales> loadsales() throws BaseException
	{  List<info_sales> result=new ArrayList<info_sales>();
       Connection conn=null;
       PreparedStatement st=null;
       ResultSet rs=null;
       try{
			conn=DBUtil.getConnection();
			String sql="select * from info_sales where UserID=?";
			st=conn.prepareStatement(sql);
           st.setString(1,ExampleUserManager.loginname);
			rs=st.executeQuery();
			while(rs.next()){
				info_sales p=new info_sales();
				p.setAddress(rs.getString("HouseName"));
				p.setName(rs.getString("CustomerName"));
				p.setTime(rs.getString("Time"));
				p.setCommission(rs.getString("Commission"));
				result.add(p);	
			}
			conn.close();
			st.close();
			rs.close();
		}catch(Exception e){
			throw new BaseException(e.getMessage());
		}
	   return result;
		
	}

}
