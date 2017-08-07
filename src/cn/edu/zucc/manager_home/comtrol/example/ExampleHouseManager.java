package cn.edu.zucc.manager_home.comtrol.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cn.edu.zucc.manager_home.itf.IBuildingManager;
import cn.edu.zucc.manager_home.itf.IHouseManager;
import cn.edu.zucc.manager_home.model.info_building;
import cn.edu.zucc.manager_home.model.info_house;
import cn.edu.zucc.manager_home.model.info_region;
import cn.edu.zucc.manager_home.util.BaseException;
import cn.edu.zucc.manager_home.util.BusinessException;
import cn.edu.zucc.manager_home.util.DBUtil;

public class ExampleHouseManager implements IHouseManager{
	public info_house addhouse(info_building building, String address,String floor,
			String total, String type,String orientation, String area,String price) throws BaseException
	{	Connection conn=null;
	PreparedStatement st=null;
	if("".equals(address)||"".equals(floor)||"".equals(total)||"".equals(type)||"".equals(orientation)||"".equals(area)||"".equals(price)) throw new BusinessException("请将信息填写完整");
 	try{
		conn=DBUtil.getConnection();
		String sql="insert into info_house(BuildingName,Address,Floor,TotalFloor,Housetype,Orientation,Area,price,UserID) values(?,?,?,?,?,?,?,?,?)";
		st=conn.prepareStatement(sql);
    	st.setString(1,building.getName());
    	st.setString(2,address);
    	st.setString(3,floor);
    	st.setString(4,total);
    	st.setString(5,type);
    	st.setString(6,orientation);
    	st.setString(7,area);
    	st.setString(8,price);
		st.setString(9,ExampleUserManager.loginname);
		st.executeUpdate();
 		st.close();
		conn.close();
	}catch(Exception e){
		throw new BaseException("位置不可重复");}
		return null;		
	}
	public List<info_house> loadhouse(info_building building) throws BaseException {
		List<info_house> result=new ArrayList<info_house>();
		Connection conn=null;
		PreparedStatement st=null;
		ResultSet rs=null;

     	try{
			conn=DBUtil.getConnection();
			String sql="select * from info_house where BuildingName=? and UserID=?";
			st=conn.prepareStatement(sql);
			System.out.println(building.getName());
		    st.setString(1,building.getName());
            st.setString(2,ExampleUserManager.loginname);
			rs=st.executeQuery();
			
			while(rs.next()){
				info_house p=new info_house();
				p.setName(rs.getString("BuildingName"));
				p.setAddress(rs.getString("Address"));
				p.setFloor(rs.getString("Floor"));
				p.setTotal(rs.getString("TotalFloor"));
				p.setType(rs.getString("Housetype"));
				p.setOrientation(rs.getString("Orientation"));
				p.setArea(rs.getString("Area"));
				p.setPrice(rs.getString("price"));
				p.setUserid(rs.getString("UserID"));
				p.setStatus(rs.getString("Status"));
				p.setCustomername(rs.getString("CustomerName"));
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
	public void deletehouse(info_house house) throws BaseException {
		// TODO Auto-generated method stub
		Connection conn=null;
		PreparedStatement st=null;
     	try{
			conn=DBUtil.getConnection();
			String sql="delete from info_house where Address=? and UserID=? ";
			st=conn.prepareStatement(sql);
			System.out.println(house.getAddress());
            st.setString(1,house.getAddress());
            st.setString(2,ExampleUserManager.loginname);
			st.executeUpdate();
     		st.close();
			conn.close();
		}catch(Exception e){

			throw new BaseException(e.getMessage());

		}
	}

}
