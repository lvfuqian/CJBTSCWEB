package com.elegps.tscweb.serivce;

import java.util.List;

import com.elegps.tscweb.exception.MessageException;
import com.elegps.tscweb.model.TbDdmsMsListInfo;
import com.elegps.tscweb.model.TbGpsMsListInfo;
import com.elegps.tscweb.model.TbGrpInfo;
import com.elegps.tscweb.model.TbGrpMsListInfo;
import com.elegps.tscweb.model.TbMenuInfo;
import com.elegps.tscweb.model.TbMsInfo;
import com.elegps.tscweb.model.TbRoleInfo;
import com.elegps.tscweb.model.TbRoleMenuInfo;

public interface UserRoleManager {
	
	/**
	 * 根据查询条件取得记录总数
	 * @param user_id
	 * @param role_id
	 * @return  查询条件取得记录总数
	 */
   int getUserRole_SearchCount(String user_id,String role_id);
         
   /**
	 * 根据每页记录数，总记录数获取总页数
	 * @param count 总记录数
	 * @param pageSize 每页显示的记录数
	 * @return 计算得到的总页数
	 */
	int getPageCount(int count , int pageSize);
	
	
	/**
	    * 根据指定页及查询条件取得记录信息
	    * @param pageNo
	    * @param pageSize
	    * @param user_id
	    * @param role_id
	    * @return 指定页及查询条件取得记录信息
	    */
	List getUserRole_SearchByPage(int pageNo,int pageSize,String user_id,String role_id);
	
	
	 /**
	    * 创建一条用户角色关系信息
	    * @param user_id
	    * @param role_id
	    * @return
	    */
	   String createUserRoleInfo(String user_id,String role_id);
	   
	   
	   /**
	    * 根据userrole_id列表 删除记录
	    * @param userrole_id
	    * @return
	    */
	   Boolean deleteUserRole(String[] userrole_id);
	   
	   
	   /**根据用户id查询该用户我角色id
	    * 
	    * @param user_id
	    * @return
	    */
	   String getRoidinfo_ByUserid(String user_id);
	
}