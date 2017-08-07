package cn.edu.zucc.manager_home.comtrol.example;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cn.edu.zucc.manager_home.itf.IReigonManager;
import cn.edu.zucc.manager_home.model.info_building;
import cn.edu.zucc.manager_home.model.info_region;
import cn.edu.zucc.manager_home.util.BaseException;
import cn.edu.zucc.manager_home.util.BusinessException;
import cn.edu.zucc.manager_home.util.DBUtil;
public class ExampleRegionManager implements IReigonManager {

		public info_region addRegion(String name) throws BaseException {
			
			Connection conn=null;
			PreparedStatement st=null;
			if("".equals(name)) throw new BusinessException("名字不能为空");
	     	try{
				conn=DBUtil.getConnection();
				String sql="insert into info_reigon(Name,UserID,UIName) values(?,?,?)";
				st=conn.prepareStatement(sql);
	        	st.setString(1, name);
				st.setString(2, ExampleUserManager.loginname);
				st.setString(3, name);
				st.executeUpdate();
	     		st.close();
				conn.close();
			}catch(Exception e){

				throw new BaseException(e.getMessage());

			}


			return null;
		}

		@Override
		public List<info_region> loadAll() throws BaseException {
			List<info_region> result=new ArrayList<info_region>();
			//BeanPlan p=new BeanPlan();
			Connection conn=null;
			PreparedStatement st=null;
			ResultSet rs=null;
	     	try{
				conn=DBUtil.getConnection();
				String sql="select * from info_reigon where UserID=?";
				st=conn.prepareStatement(sql);
				st.setString(1, ExampleUserManager.loginname);
				rs=st.executeQuery();
				while(rs.next()){
					info_region p=new info_region();
					p.setName(rs.getString("Name"));
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

		public void deleteRegion(info_region region) throws BaseException {
			// TODO Auto-generated method stub
			Connection conn=null;
			PreparedStatement st=null;
	     	try{
				conn=DBUtil.getConnection();
				String sql="delete from info_reigon where Name=? and UserID=?";
				st=conn.prepareStatement(sql);
	            st.setString(1, region.getName());
				st.setString(2, ExampleUserManager.loginname);
				st.executeUpdate();
				sql="delete from info_building where region=?";
				st=conn.prepareStatement(sql);
				st.setString(1, region.getName());
				st.executeUpdate();
	     		st.close();
				conn.close();
			}catch(Exception e){

				throw new BaseException("存在楼盘,所选区域不可删除");

			}
		}
		public void modify(info_region region, String name) throws BaseException{
			Connection conn=null;
			PreparedStatement st=null;
			ResultSet rs=null;
			try{
				conn=DBUtil.getConnection();
				String sql="select * from  info_reigon where UIname=? and UserID=?";
				st=conn.prepareStatement(sql);
				st.setString(1, name);
				st.setString(2, ExampleUserManager.loginname);
				rs=st.executeQuery();
				if(rs.next()) throw new BaseException("已存在该区域");
				
				sql="update info_reigon set UIName=? where name=? and UserID=?";
				st=conn.prepareStatement(sql);
				st.setString(1, name);
	            st.setString(2, region.getName());
				st.setString(3, ExampleUserManager.loginname);
				st.executeUpdate();
	     		st.close();
				conn.close();
			}catch(Exception e){

				throw new BaseException(e.getMessage());

			}
			
		}
}

