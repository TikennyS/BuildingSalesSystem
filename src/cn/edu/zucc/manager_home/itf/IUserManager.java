package cn.edu.zucc.manager_home.itf;
import cn.edu.zucc.manager_home.model.info_user;
import cn.edu.zucc.manager_home.util.BaseException;
public interface IUserManager {
	public info_user reg(String userid,String pwd,String pwd2) throws BaseException;
	public info_user login(String userid, String pwd) throws BaseException;
	public void changePwd(info_user user, String oldPwd, String newPwd,String newPwd2) throws BaseException;
}
