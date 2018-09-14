package com.elegps.tscweb.serivce;

import java.util.List;
import java.util.Map;

import com.elegps.tscweb.exception.MessageException;
import com.elegps.tscweb.model.TabBaseGrpextinfo;
import com.elegps.tscweb.model.TabSysconfig;
import com.elegps.tscweb.model.TbEnterpriseInfo;
import com.elegps.tscweb.model.TbGrpInfo;




public interface GrpManager 
{ 
	/**
	 * 用于群组id生成
	 * @param grp 群组号前17位进行模糊查询，获取1条数据
	 * @return 最大的群组id
	 */
	String getGrpId(String grp);
	/**
	 * 获取所有群组类型信息数量
	 * @return 所有群组类型信息的数量 
	 */
	int getAllgrp_typeCount();
	
	
	/**
	 * 根据每页记录数，总记录数获取总页数
	 * @param count 总记录数
	 * @param pageSize 每页显示的记录数
	 * @return 计算得到的总页数
	 */
	int getPageCount(int count , int pageSize);
	
	
	
	/**
	 * 返回特定页面所有群组信息
	 * @param pageNo 指定页码
	 * @param  pagesize 每页显示的条数
	 * @return 指定页的全部群组信息
	 */
	List <TbGrpInfo> getAllGrp_typeByPage(int pageNo,int pagesize);
	
	
	/**
	 * 获取群组类型信息数量
	 * @param grp_type 终端用户类型对应总记录数
	 * @return 终端用户类型信息的数量 
	 */
	int getGrp_typeCount(int grp_type);
	
	
	/**
	 * 返回特定群组类型页面所有终端用户信息
	 * @param pageNo 指定页码
	 * @param  pagesize 每页显示的条数
	 * @param  grp_type 群组类型
	 * @return 指定群组类型页的全部群组信息
	 */ 
	List <TbGrpInfo> getGrp_typeByPage(int pageNo,int pagesize,int grp_type);
	
	
	/**
	 * 返回特定指定条件群组信息
	 * @param grpid 群组号码
	 */
	TbGrpInfo getGrpinfoby_grpid(String grpid);
	
	
	/**
	 * 获取所有群组在线通话态信息数量
	 * @return 群组在线通话态信息数量
	 */
	int getAllGrpStatus_allCount();
	
	
	/**
	 * 获取所有群组在线通话态信息
	 * @param pageNo 指定页码
	 * @param  pagesize 每页显示的条数
	 * @return 指定页的全部终端用户用户状态信息  按在线状态排序
	 */
	List <TbGrpInfo> getAllGrpStatus_allByPage(int pageNo,int pagesize);
	
	
	/**
	 * 获取群组在线通话态信息数量
	 * @param stauts 群组线通话态信息对应总记录数
	 * @return 群组在线通话态信息数量
	 */
	int getGrp_StauteCount(int stauts);
	
	
	/**
	 * 返回指定获取群组在线通话态信息
	 * @param pageNo 指定页码
	 * @param  pagesize 每页显示的条数
	 * @param  status 群组在线通话态
	 * @return 指定获取群组在线通话态信息
	 */
	List <TbGrpInfo> getGrp_StatusByPage(int pageNo,int pagesize,int status);

	
	/**根据grpid
	 * 删除记录
	 * @param grpid_list
	 */
	Boolean deleteGrp(String grpid);
	
	/**
	 * 根据grp_id做模糊查询
	 * @return 群组grp_id做模糊查询的数量
	 */
	int getGrp_idCount(String grp_id);
	
	
	
	/**
	 * 根据grpid做模糊查询
	 * @return 群组grpid做模糊查询页的全部群组信息
	 */
	List <TbGrpInfo> getTbGrpinfoby_grpid(int pageNo,int pagesize,String grpid);
	
	
	
	/**
	 * 获取群组有效状态信息数量
	 * 群组有效状态对应总记录数
	 * @return 群组有效状态信息的数量 
	 */
	int getAllGrp_flagCount()throws MessageException;
	
	
	/**
	 * 返回所有群组有效状态页面所有群组信息
	 * @param pageNo 指定页码
	 * @param  pagesize 每页显示的条数
	 * @return 所有群组有效状态页面所有群组信息
	 */ 
	List <TbGrpInfo> getAllGrp_flagByPage(int pageNo,int pagesize);
	
	
	/**
	 * 获取指定群组有效状态页面所有群组信息数量
	 * @param flag 终端用户状态对应总记录数
	 * @return 指定群组有效状态页面所有群组信息数量
	 */
	int getGrp_flagCount(int flag);
	
	
	/**
	 * 返回指定群组有效状态页面所有群组信息
	 * @param pageNo 指定页码
	 * @param  pagesize 每页显示的条数
	 * @param  flag 群组有效状态
	 * @return 指定群组有效状态页面所有群组信息
	 */ 
	List <TbGrpInfo> getGrp_flagByPage(int pageNo,int pagesize,int flag);

	
	/**
	 * 返回所有有效状态(状态为1)的终端用户信息
	 * @return 返回所有有效状态(状态为1)的终端用户信息
	 */ 
	public List getTbMsInfo();
	
	
	/**
	 * 创建一条群组信息
	 * @param grp_id 群组号码
	 * @param grp_name 群组名称
	 * @param ms_id 创建组的终端号
	 * @param grp_type 终端所属类型
	 * @param Flag  终端用户的状态
	 * @return 新创终端的主键,如果创建失败，返回null。
	 */  
	String createGrp(String grp_id , String grp_name , String ms_id,int grp_type,int flag,int talksc,String ep_id,int grp_lf,String grp_pid,String C03);
	
	
	/**根据grp_id更新记录列表
	 * 更新记录列表
	 * @param grp_id
	 * @param grp_name
	 * @param grp_type
	 * @param grp_flag
	 */
	String modifyGrp(String grp_id,String ep_id,String grp_name,int grp_type,int grp_flag,int talksc,int grp_lf,String C03);
	
	/**
	 * 修改群组信息
	 * @param grpInfo
	 */
	void update(TbGrpInfo grpInfo);
	
	/**
	 * 所有群组信息
	 * @return 指定页的全部群组信息
	 */
	List <TbGrpInfo> getAllGrp_Info(String pagentid,String childagentid,String ep);
	List <TbGrpInfo> getBaseGrp_Info(String pagentid,String childagentid,String ep);
		
	
	
	/**
	 * 用于群组与终端用户关系批量添加时(取得该msid没有添加的群组信息)
	 * @param msid
	 * @return
	 */
	List getAllGrp_Info_not_Bymsid(String msid,String ep);

	
	/**群组表查询数量
	 * @param grp_type  群组类型
	 * @param statue  通话状态
	 * @param flag  有效状态
	 * @param ep2 
	 * @param ms_id  群组号码
	 * @param ms_id  群组名称
	 * @return 群组模糊查询的数量
	 */
	int getGrp_sertch(String grp_type,String statue,String flag,String grp_id,String grp_name,String pagentid,String childagentid, String ep);
	
	
	/**群组表查询信息
	 * @param grp_type  群组类型
	 * @param statue  通话状态
	 * @param flag  有效状态
	 * @param grp_id  群组号码
	 * @param grp_name  群组名称
	 * @param ep2 
	 * @return 群组模糊查询的信息
	 */
	List <TbGrpInfo> getTBGrpinfoby_grppage(int pageNo, int pagesize,
			String grp_type,String statue,String flag,String grp_id,String grp_name,String pagentid,String childagentid, String ep);
   /**
    * 查询所有用户单位信息 zr
    * @return
    */
	List selectEp();
	/**
	 * 根据群组ID查询群组所属的用户单位 zr
	 * @param epid
	 * @return
	 */
	TbEnterpriseInfo getEpBygrpid(String grpid);
	
	
	/**
	 * 根据群组创建者ID（MS_ID）查找所有群组信息
	 * @param msid
	 * @return
	 */
	List findGrp_Info_byMsId(String msid);
	
	/**
	 * 用于群组与终端用户关系跨企业批量添加时(取得该源代企业没有添加的群组信息)
	 * @param msid
	 * @return
	 */
	List getAllGrp_Info_not_BymsidGrp(String msid,String yep);
	public List listAll(int pageNo, int pageSize, TabBaseGrpextinfo baseGrp);

	public void create(TabBaseGrpextinfo baseGrp);

	public int update(String hql, Object... objects);

	public int delete(String grpid);

	public int totalCount(TabBaseGrpextinfo baseGrp);
	public <T> T getBean(String grpId);
	
	
	/**
	 * 创建一条群组信息
	 * @param grp_id 群组号码
	 * @param grp_name 群组名称
	 * @param ms_id 创建组的终端号
	 * @param grp_type 终端所属类型
	 * @param Flag  终端用户的状态
	 * @return 新创终端的主键,如果创建失败，返回null。
	 */  
	String createGrp(String grp_id , String grp_name , String ms_id,int grp_type,int flag,int talksc,String ep_id,int grp_lf,String grp_pid,String C03,String desc);
	
	/**
	 * 群组名、id模糊查询
	 * @param nameOrId
	 * @param epid
	 * @return
	 */
	public Map<String,String> getGrpByNameOrId(String nameOrId,int epid);
}