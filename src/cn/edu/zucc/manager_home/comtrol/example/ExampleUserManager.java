package cn.edu.zucc.manager_home.comtrol.example;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import cn.edu.zucc.manager_home.itf.IUserManager;
import cn.edu.zucc.manager_home.model.info_user;
import cn.edu.zucc.manager_home.util.BaseException;
import cn.edu.zucc.manager_home.util.BusinessException;
import cn.edu.zucc.manager_home.util.DBUtil;
public class ExampleUserManager implements IUserManager{
	public static String loginname;
	public static String loginpwd;
	public info_user reg(String userid,String pwd,String pwd2) throws BaseException {
		// TODO Auto-generated method stub
		Connection conn=null;
		PreparedStatement st=null;
		ResultSet rs=null;
		if(pwd.equals(pwd2)==true&&"".equals(pwd)==false){
		  try{
			conn=DBUtil.getConnection(); 
			String sql="select * from info_user where Name=?";
			st=conn.prepareStatement(sql);
			st.setString(1, userid);
			rs=st.executeQuery();
			if(rs.next()) throw new BusinessException("用户名已存在");
			sql ="insert into info_user(Name,password) values(?,?)"; 
			st =conn.prepareStatement(sql);
			st.setString(1,userid);
			st.setString(2, pwd);
           st.executeUpdate();
			st.close();
		}catch(Exception e){
			throw new BaseException(e.getMessage());
		}finally{
			if(conn!=null)
				try {
					conn.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		   }
		}else  {if(pwd.equals(pwd2)==false)throw new BusinessException("两次密码不一样");
		            else throw new BusinessException("密码不能为空");
		           }
		return null;
}
	public info_user login(String userid, String pwd) throws BaseException {
		// TODO Auto-generated method stub
		
		Connection conn=null;
		PreparedStatement st=null;
		ResultSet rs=null;
		info_user user1=null;
		try{
			conn=DBUtil.getConnection(); 
			String sql ="select * from info_user where Name=?"; 
			st =conn.prepareStatement(sql);
			st.setString(1,userid);
            rs=st.executeQuery();
            if(!rs.next()) throw new BusinessException("用户不存在");
            if(!pwd.equals(rs.getString(2)))  throw new BusinessException("密码不正确");				
            loginname=userid;
            loginpwd=pwd;
			sql ="select * from info_user where Name=?";

			while(rs.next()){

				info_user user = new info_user();
				user.setUsername(userid);
				return user;
			} 
			st.close();
			rs.close();
		}catch(Exception e){
			throw new BaseException(e.getMessage());
		}
		  
		return null;
	}


	public void changePwd(info_user user, String oldPwd, String newPwd,String newPwd2) throws BaseException 
	{
		Connection conn=null;
		PreparedStatement st=null;
		ResultSet rs=null;
		try{
			conn=DBUtil.getConnection(); 
			String sql ="select userid,pwd from info_user where Name=?"; 
			st =conn.prepareStatement(sql);
			System.out.println(loginname);
			st.setString(1,loginname);
            rs=st.executeQuery();
            if(!oldPwd.equals(loginpwd)) throw new BusinessException("原密码错误");
            if(!newPwd.equals(newPwd2)==true&&newPwd!=null) throw new BusinessException("新密码两次不一样");
            sql="update info_user set Password=? where Name=?";
			st=conn.prepareStatement(sql);
			st.setString(1, newPwd);
			st.setString(2, loginname);
			st.execute();
			st.close();
			throw new BusinessException("修改成功");
	}catch(Exception e){
		throw new BaseException(e.getMessage());
	}

	}
}


