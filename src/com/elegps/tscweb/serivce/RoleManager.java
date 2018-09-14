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

public interface RoleManager {
	/**
	 * 根据查询条件取得记录总数
	 * 
	 * @param role_name
	 * @return 查询条件取得记录总数
	 */
	int getRole_SearchCount(String role_name);

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
	 * @param role_name
	 * @return 指定页及查询条件取得记录信息
	 */
	List<TbRoleInfo> getTbRoleInfoby_name(int pageNo, int pageSize,
			String role_name);

	/**
	 * 根据menu_id列表 删除记录
	 * 
	 * @param role_id
	 */
	Boolean deleteRole(String[] role_id);
	
	
	/**
	 * 创建一条信息
	 * @param role_name
	 * @return
	 */
	String createRole(String role_name);
	
	
	/**
	 * 返回特定指定条件角色信息
	 * @param role_id 角色ID
	 */
	TbRoleInfo getRoleInfoby_roleid(String role_id);
	
	
	/**根据role_id更新记录列表
	 * 更新记录列表
	 * @param role_id
	 * @param role_name
	 */
	String modifyRole(String role_id,String role_name);
	
	/**
	 * 
	 * @return 所有的角色信息
	 */
	List<TbRoleInfo> getAllRole_Info();
}