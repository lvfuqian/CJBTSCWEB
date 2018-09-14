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

public interface RoleMenuManager {
	/**
	 * 根据查询条件取得记录总数
	 * @param role_id
	 * @param menu_id
	 * @return  查询条件取得记录总数
	 */
   int getRoleMenu_SearchCount(String role_id,String menu_id);
   
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
	    * @param role_id
	    * @param menu_id
	    * @return  指定页及查询条件取得记录信息
	    */
	   List getRoleMenu_SearchByPage(int pageNo,int pageSize,String role_id,String menu_id);
	   
	   /**
	    * 创建一条
	    * @param role_id
	    * @param menu_id
	    * @return
	    */
	   String createRoleMenuInfo(String role_id,String menu_id);
	   
	   /**
	    * 根据rolemenu_id列表 删除记录
	    * @param rolemenu_id
	    * @return
	    */
	   Boolean deleteRoleMenu(String[] rolemenu_id);
	   
	   /**
	    * 跟据角色ID查询已经存在的菜单信息
	    * @param roleid
	    * @return
	    */
	   List  getMenu_info(String roleid);
	   
	   /**
	    * 跟据角色ID批量添加菜单
	    * @param roleid
	    * @param menuid
	    * @return
	    */
	  String createRoleMenuInfo_ByRoleid(String roleid,String[] menuid);
	      
}