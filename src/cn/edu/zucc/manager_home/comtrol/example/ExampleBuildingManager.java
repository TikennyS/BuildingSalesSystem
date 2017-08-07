package cn.edu.zucc.manager_home.comtrol.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.edu.zucc.manager_home.itf.IBuildingManager;
import cn.edu.zucc.manager_home.itf.IReigonManager;
import cn.edu.zucc.manager_home.model.info_building;
import cn.edu.zucc.manager_home.model.info_region;
import cn.edu.zucc.manager_home.util.BaseException;
import cn.edu.zucc.manager_home.util.BusinessException;
import cn.edu.zucc.manager_home.util.DBUtil;

public class ExampleBuildingManager implements IBuildingManager {
	public info_building addBuilding(info_region Region,String name,String BuildType,String Data,String Street,
			String Developer,String Ratio,String Density,String Park)
			throws BaseException {
		
		Connection conn=null;
		PreparedStatement st=null;
		if("".equals(name)) throw new BusinessException("名字不能为空");
		if("".equals(Region)) throw new BusinessException("请选择区域");
		if(Integer.parseInt(Data)>2016||"".equals(Data)) throw new BusinessException("时间无效");
		if("".equals(Street)||"".equals(Developer)||"".equals(Ratio)||"".equals(Density)||"".equals(Park)) throw new BusinessException("请将信息填写完整");
     	try{
			conn=DBUtil.getConnection();
			String sql="insert into info_building(Name,Data,Region,Street,Developer,Park,BuildType,Ratio,Density,UserID,UIName) values(?,?,?,?,?,?,?,?,?,?,?)";
			st=conn.prepareStatement(sql);
        	st.setString(1, name);
        	st.setString(2,Data);
        	st.setString(3,Region.getName());
        	st.setString(4,Street);
        	st.setString(5,Developer);
        	st.setString(6,Park);
        	st.setString(7,BuildType);
        	st.setString(8,Ratio);
        	st.setString(9,Density);
			st.setString(10,ExampleUserManager.loginname);
			st.setString(11,name);
			st.executeUpdate();
     		st.close();
			conn.close();
		}catch(Exception e){
			throw new BaseException("重名不可添加");}
		return null;
		}

	public List<info_building> loadbuilding1(info_region region) throws BaseException {
		List<info_building> result=new ArrayList<info_building>();
		Connection conn=null;
		PreparedStatement st=null;
		ResultSet rs=null;

     	try{
			conn=DBUtil.getConnection();
			String sql="select * from info_building where Region=? and UserID=?";
			st=conn.prepareStatement(sql);
		    st.setString(1,region.getName());
            st.setString(2,ExampleUserManager.loginname);
			rs=st.executeQuery();
			while(rs.next()){
				info_building p=new info_building();
				p.setName(rs.getString("Name"));
				p.setUIName(rs.getString("UIName"));
				p.setData(rs.getString("Data"));
				p.setRegion(rs.getString("Region"));
				p.setStreet(rs.getString("Street"));
				p.setDeveloper(rs.getString("Developer"));
				p.setPark(rs.getString("Park"));
				p.setBuildType(rs.getString("BuildType"));
				p.setRatio(rs.getString("Ratio"));
				p.setDensity(rs.getString("Density"));
				p.setUserID(rs.getString("UserID"));
				p.setUIName(rs.getString("UIName"));
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

	public void deletebuilding(info_building building) throws BaseException {
		// TODO Auto-generated method stub
		Connection conn=null;
		PreparedStatement st=null;
     	try{
			conn=DBUtil.getConnection();
			String sql="delete from info_building where Name=? and UserID=? ";
			st=conn.prepareStatement(sql);
            st.setString(1,building.getName());
            st.setString(2,ExampleUserManager.loginname);
			st.executeUpdate();
     		st.close();
			conn.close();
		}catch(Exception e){

			throw new BaseException("存在房屋信息，不可删除");

		}
	}
	public void modify(info_building building,String region,String name,String BuildType,String Data,String Street,
			String Developer,String Ratio,String Density,String Park) throws BaseException{
		Connection conn=null;
		PreparedStatement st=null;
		ResultSet rs=null;
		try{
			conn=DBUtil.getConnection();
			/*String sql="select * from  info_building where UIname=? and UserID=? ";
			st=conn.prepareStatement(sql);
			st.setString(1, name);
			st.setString(2, ExampleUserManager.loginname);
	
			rs=st.executeQuery();
			if(rs.next()) throw new BaseException("已存在该房屋");*/
			
			
			String sql="select UIName from info_reigon where UIname=? and UserID=?";
			st=conn.prepareStatement(sql);
			st.setString(1, region);
			st.setString(2, ExampleUserManager.loginname);
			rs=st.executeQuery();
			if(!rs.next()) throw new BaseException("不存在该区域");
			
		    
			sql="update info_building set UIName=?,BuildType=?,Data=?,Street=?,Developer=?,Ratio=?,Density=?,"
					+ "Park=? ,Region=? where name=? and UserID=?";
			st=conn.prepareStatement(sql);
			st.setString(1, name);
			st.setString(2, BuildType);
			st.setString(3, Data);
			st.setString(4, Street);
			st.setString(5, Developer);
			st.setString(6, Ratio);
			st.setString(7, Density);
			st.setString(8, Park);
			st.setString(9, region);
            st.setString(10, building.getName());
			st.setString(11, ExampleUserManager.loginname);
			st.executeUpdate();
     		st.close();
			conn.close();
		}catch(Exception e){

			throw new BaseException(e.getMessage());

		}
		
	}

}
