package cn.edu.zucc.manager_home.comtrol.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cn.edu.zucc.manager_home.itf.ICustomerManager;
import cn.edu.zucc.manager_home.model.info_building;
import cn.edu.zucc.manager_home.model.info_customer;
import cn.edu.zucc.manager_home.model.info_house;
import cn.edu.zucc.manager_home.util.BaseException;
import cn.edu.zucc.manager_home.util.BusinessException;
import cn.edu.zucc.manager_home.util.DBUtil;

public class ExampleCustomerManager implements ICustomerManager{

	public void addcustomer(String name, String age, String phone,String address, String email) throws BaseException {
		// TODO Auto-generated method stub

		Connection conn=null;
		PreparedStatement st=null;
		if("".equals(name)||"".equals(age)||"".equals(phone)||"".equals(address)||"".equals(email)) throw new BusinessException("请将信息填写完整");
		try{
			conn=DBUtil.getConnection();
			String sql="insert into info_customer(Name,Age,Phone,Address,Email,UserID) values(?,?,?,?,?,?)";
			st=conn.prepareStatement(sql);
        	st.setString(1, name);
        	st.setString(2,age);
        	st.setString(3,phone);
        	st.setString(4,address);
        	st.setString(5,email);
			st.setString(6,ExampleUserManager.loginname);
			st.executeUpdate();
     		st.close();
			conn.close();
		}catch(Exception e){
			throw new BaseException(e.getMessage());}

		}
	
	public List<info_customer> loadcustomer() throws BaseException
	{   List<info_customer> result=new ArrayList<info_customer>();
	    Connection conn=null;
	    PreparedStatement st=null;
	    ResultSet rs=null;
	    try{
			conn=DBUtil.getConnection();
			String sql="select * from info_customer where UserID=?";
			st=conn.prepareStatement(sql);
            st.setString(1,ExampleUserManager.loginname);
			rs=st.executeQuery();
			while(rs.next()){
				info_customer p=new info_customer();
				p.setName(rs.getString("Name"));
				p.setAge(rs.getString("Age"));
				p.setPhone(rs.getString("Phone"));
				p.setAddress(rs.getString("Address"));
				p.setEmail(rs.getString("Email"));
				p.setUserID(rs.getString("UserID"));
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
	 public void deletecustomer(info_customer customer) throws BaseException
	 {
		 Connection conn=null;
		 PreparedStatement st=null;
	     	try{
				conn=DBUtil.getConnection();
				String sql="delete from info_customer where Name=? and UserID=? ";
				st=conn.prepareStatement(sql);
	            st.setString(1,customer.getName());
	            st.setString(2,ExampleUserManager.loginname);
				st.executeUpdate();
	     		st.close();
				conn.close();
			}catch(Exception e){

				throw new BaseException("当前用户已有交易信息，不可删除");

			}
	 }
		
	
public List<info_customer> search(String keywwords) throws BaseException
{   
	List<info_customer> result=new ArrayList<info_customer>();
	 Connection conn=null;
	 PreparedStatement st=null;
	 ResultSet rs=null;
	 
    	try{
			conn=DBUtil.getConnection();
			
			String sql="select * from info_customer where  name like ? or phone like ? or address like ? or email like ? and UserID=? ";
			
			st=conn.prepareStatement(sql);
            st.setString(1,"%"+keywwords+"%");System.out.println(keywwords);
            st.setString(2,"%"+keywwords+"%");
            st.setString(3,"%"+keywwords+"%");
            st.setString(4,"%"+keywwords+"%");
            st.setString(5,ExampleUserManager.loginname);
			rs=st.executeQuery();
			
			while(rs.next()){
				
				info_customer p=new info_customer();
				p.setName(rs.getString("Name"));
				p.setAge(rs.getString("Age"));
				p.setPhone(rs.getString("Phone"));
				p.setAddress(rs.getString("Address"));
				p.setEmail(rs.getString("Email"));
				p.setUserID(rs.getString("UserID"));
				result.add(p);	
			}
		}catch(Exception e){

			throw new BaseException(e.getMessage());

		}
    	return result;
    	}

	
}

