package com.elegps.tscweb.serivce;

import java.util.List;

import com.elegps.tscweb.exception.MessageException;
import com.elegps.tscweb.model.TbAgentInfo;
import com.elegps.tscweb.model.TbDdmsMsListInfo;
import com.elegps.tscweb.model.TbGpsMsListInfo;
import com.elegps.tscweb.model.TbGrpInfo;
import com.elegps.tscweb.model.TbGrpMsListInfo;
import com.elegps.tscweb.model.TbMenuInfo;
import com.elegps.tscweb.model.TbMsInfo;
import com.elegps.tscweb.model.TbRoleInfo;
import com.elegps.tscweb.model.TbUserInfo;

public interface UserManager {
	/**
	 * 根据查询条件(用户名)取得记录总数
	 * 
	 * @param user_name
	 * @return 查询条件取得记录总数
	 */
	int getUser_SearchCount(String user_name, int agent_id);

	/**
	 * 根据每页记录数，总记录数获取总页数
	 * 
	 * @param count
	 *            总记录数
	 * @param pageSize
	 *            每页显示的记录数
	 * @return 计算得到的总页数
	 */
	int getPageCount(int count, int pageSize);
	
	
	/**
	 * 根据指定页及查询条件取得记录信息
	 * 
	 * @param user_name
	 * @return 指定页及查询条件取得记录信息
	 */
	List getTbUserInfoby_name(int pageNo, int pageSize,
			String user_name,int agent_id);
	

	/**
	 * 创建一条用户信息
	 * @param user_name
	 * @param psw
	 * @return
	 */
	String createUser(String user_name,String psw,String roleid,String agentid,String epid);
	
	/**
	 * 根据user_id列表 删除记录
	 * 
	 * @param user_id
	 */
	Boolean deleteUser(String[] user_id);
	
	
	/**
	 * 返回特定指定条件用户信息
	 * @param user_id 用户ID
	 */
	TbUserInfo getUserInfoby_userid(String user_id);
	
	
	/**根据user_id更新记录列表
	 * 更新记录列表
	 * @param user_id
	 * @param user_name
	 * @param psw
	 */
	String modifyUser(String user_id,String user_name,String psw,String roleid,String agentid,String epid);
	
	
	/**
	 * 
	 * @return 返回所有用户信息
	 */
	List<TbUserInfo> getAllUser_Info();
	
	
	/**
	 * @return 返回所有没有分配角色用户信息
	 */
	List<TbUserInfo> getNotUser_Info();
	
	
   /**
    * 
    * @return返回所有代理商
    */
	List<TbAgentInfo> getAllAgent_Info();
	//修改用户密码
    Boolean ChangePassword(String name, String psasword);
    
    /**
	 * 修改user信息
	 * @param user
	 * @return
	 */
	Boolean updateUser(TbUserInfo user);
	
	TbUserInfo get_User(String name);
}