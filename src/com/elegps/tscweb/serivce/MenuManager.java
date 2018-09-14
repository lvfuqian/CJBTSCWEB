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

public interface MenuManager {
	/**
	 * 根据查询条件取得记录总数
	 * 
	 * @param menu_name
	 * @return 查询条件取得记录总数
	 */
	int getMenu_SearchCount(String menu_name);

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
	 * @param menu_name
	 * @return 指定页及查询条件取得记录信息
	 */
	List getTbMenuInfoby_name(int pageNo, int pageSize,
			String menu_name);

	/**
	 * 根据menu_id列表 删除记录
	 * 
	 * @param grpid_list
	 */
	Boolean deleteMenu(String[] menu_id);
	
	
	/**
	 * 创建一条信息
	 * @param menu_name
	 * @param url
	 * @return
	 */
	String createMenu(String menu_name,String pmenuid,String url);
	
	
	/**
	 * 返回特定指定条件菜单信息
	 * @param menu_id 菜单ID
	 */
	TbMenuInfo getMenuInfoby_menuid(String menu_id);
	
	
	/**根据menu_id更新记录列表
	 * 更新记录列表
	 * @param menu_id
	 * @param meun_name
	 * @param url
	 */
	String modifyMenu(String menu_id,String meun_name, String url,String pmenu_id);
	
	/**
	 * 
	 * @return 所有的菜单信息
	 */
	List<TbMenuInfo> getAllMenu_Info();
	
	
	/**
	 * 用于角色与菜单关系批量添加时(取得该roleid没有添加的菜单)
	 * @param roleid
	 * @return
	 */
	List getAllMenu_Info_not_Byroleid(String roleid);
}