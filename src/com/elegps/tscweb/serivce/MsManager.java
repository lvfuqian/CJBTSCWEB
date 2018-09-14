package com.elegps.tscweb.serivce;

import java.util.List;

import org.hibernate.Session;

import com.elegps.tscweb.model.TbMsInfo;
import com.elegps.tscweb.model.TbMsInfoExt;
import com.elegps.tscweb.model.TbPFInfo;


public interface MsManager 
{ 
	//每页显示的数目
	int MESSAGE_PAGE_SIZE = 6;
	
	/**
	 * 创建一条终端用户信息
	 * @param msId 终端号码
	 * @param msName 终端名称
	 * @param passwd 终端密码
	 * @param msType 终端所属类型
	 * @param Flag  终端用户的状态
	 * @return 新创终端的主键,如果创建失败，返回-1。
	 */
	String createMs(String msId ,int mssl, String msName , String passwd,int msType,int Flag,
			String epid,String ms_level,String modid,TbPFInfo package_fee,int ms_df,int call,int mileageas,String memo,
			String c03,String c04,String netWorkType,String msCategory,int pagentid);

	/**
	 * 创建一条终端用户信息
	 * @param msId 终端号码
	 * @param msName 终端名称
	 * @param passwd 终端密码
	 * @param msType 终端所属类型
	 * @param Flag  终端用户的状态
	 * @param roleType  终端用户角色（1：船长，0：船员）
	 * @param familyNumbers  终端用户的亲情号码（逗号拼接的字符串）
	 * @return 新创终端的主键,如果创建失败，返回-1。
	 */
	String createMs(String msId ,int mssl, String msName , String passwd,int msType,int Flag,
					String epid,String ms_level,String modid,TbPFInfo package_fee,int ms_df,int call,int mileageas,String memo,
					String c03,String c04,String netWorkType,String msCategory,int pagentid,String roleType,String familyNumbers);

	/**
	 * 返回特定页面所有终端用户信息
	 * @param pageNo 指定页码
	 * @param  pagesize 每页显示的条数
	 * @return 指定页的全部终端用户信息
	 */
	List <TbMsInfo> getAllMs_typeByPage(int pageNo,int pagesize);
	
	
	/**
	 * 返回特定页面所有终端用户状态信息
	 * @param pageNo 指定页码
	 * @param  pagesize 每页显示的条数
	 * @return 指定页的全部终端用户用户状态信息  按在线状态排序
	 */
	List <TbMsInfo> getAllMsOnline_allByPage(int pageNo,int pagesize);
	
	
	/**
	 * 返回特定终端用户类型页面所有终端用户信息
	 * @param pageNo 指定页码
	 * @param  pagesize 每页显示的条数
	 * @param  ms_type 终端用户类型
	 * @return 指定终端用户类型页的全部终端用户信息
	 */ 
	List <TbMsInfo> getMs_typeByPage(int pageNo,int pagesize,int ms_type);
	
	
	/**
	 * 返回特定终端所有用户状态页面所有终端用户信息
	 * @param pageNo 指定页码
	 * @param  pagesize 每页显示的条数
	 * @return 指定终端用户状态页的全部用户信息
	 */ 
	List <TbMsInfo> getAllMs_flagByPage(int pageNo,int pagesize);
	
	
	/**
	 * 返回特定终端所有用户状态为flag页面所有终端用户信息
	 * @param pageNo 指定页码
	 * @param  pagesize 每页显示的条数
	 * @param  ms_type 终端用户状态
	 * @return 指定终端用户状态为flag页的全部用户信息
	 */ 
	List <TbMsInfo> getMs_flagByPage(int pageNo,int pagesize,int flag);
	
	
	/**
	 * 返回特定终端用户类型页面所有终端用户信息
	 * @param pageNo 指定页码
	 * @param  pagesize 每页显示的条数
	 * @param  ms_onlineStatus 终端用户在线状态
	 * @return 指定终端用户类型页的全部终端用户信息
	 */
	List <TbMsInfo> getMs_OnlineByPage(int pageNo,int pagesize,int ms_onlineStatus);
	
	
	/**
	 * 根据ms_id做模糊查询
	 * @return 终端用户ms_id做模糊查询页的全部终端用户信息
	 */
	List <TbMsInfo> getTBMsinfoby_msid(int pageNo,int pagesize,String ms_id);
	
	/**
	 * 返回特定指定条件终端用户信息
	 * @param msId 终端号码
	 */
	TbMsInfo getTBMsinfoby_msid(String msid);
	
	
	
	/**
	 * 返回特定指定条件终端用户信息
	 * @param msId 终端号码
	 * @param msType 终端所属类型
	 * @param Flag  终端用户的状态
	 * @return 指定页的全部终端用户信息
	 */
	List getTbMsInfo(String msId,int msType,int Flag);
	
	
	
	
	
	/**
	 * 获取终端用户类型信息数量
	 * @param ms_type 终端用户类型对应总记录数
	 * @return 终端用户类型信息的数量 getMsCount
	 */
	int getMs_typeCount(int ms_type);
	
	/**
	 * 获取所有终端用户类型信息数量
	 * @param ms_type 终端用户类型对应总记录数
	 * @return 终端用户类型信息的数量 
	 */
	int getAllMs_typeCount();
	
	
	/**
	 * 获取所有终端用户状态信息数量
	 * 终端用户状态对应总记录数
	 * @return 终端用户状态信息的数量 
	 */
	int getAllMs_flagCount();
	
	/**
	 * 获取终端用户状态信息数量
	 * @param flag 终端用户状态对应总记录数
	 * @return 终端用户状态为flag信息的数量 
	 */
	int getMs_flagCount(int flag);
	
	
	/**
	 * 获取终端用户在线态信息数量
	 * @param ms_Online 终端用户类型对应总记录数
	 * @return 终端用户在线态信息的数量
	 */
	int getMs_OnlineCount(int ms_Online);
	
	
	/**
	 * 获取所有终端用户在线态信息数量
	 * @return 终端用户在线态信息的数量
	 */
	int getAllMsOnline_allCount();
	
	
	/**
	 * 根据ms_id做模糊查询
	 * @return 终端用户ms_id做模糊查询的数量
	 */
	int getMsms_idCount(String ms_id);
	
	
	
	/**
	 * 根据每页记录数，总记录数获取总页数
	 * @param count 总记录数
	 * @param pageSize 每页显示的记录数
	 * @return 计算得到的总页数
	 */
	int getPageCount(int count , int pageSize);
	
	
	/**根据ms_id列表
	 * 删除记录
	 * @param msidlist
	 */
	Boolean deleteMs(String[] msidlist);
	
	
	/**根据ms_id更新记录列表
	 * 更新记录列表
	 * @param ms_id
	 * @param ms_name
	 * @param ms_type
	 * @param delddms
	 */
	String modifyMs(String ms_id,String ep_id,String ms_name,int ms_type,int ms_flag,int delddms,String modid,String password,int ms_level,TbPFInfo packagefee,int ms_df,int call,int mileageas,String memo,
			String ismobile,String c04,String msCategory,int pagentid,String nwType);
	/**
	 * 修改对应关系表记录
	 * @param ms_id
	 * @param SIM_Num
	 * @param deviceNum
	 * @param enterpriseId
	 * @param carPlateColor
	 * @return
	 */
	int updateMsExt(String ms_id,String SIM_Num,String deviceNum,int enterId,int carPlateColor,String newmsId);
	/**
	 * 执行指定的hql语句修改
	 * @param hql
	 * @param obj
	 */
	void update (String hql,Object...obj);
	
	/**
	 * 批量修改
	 * @param msList
	 * @return
	 */
	Boolean updateMs(List<TbMsInfo> msList);

	/**
	 * 修改亲情号码
	 * @param info
	 * @return
	 */
	Boolean updateFamilyNumbers(TbMsInfo info);
	
	/**
	 * 获取一条对象
	 * @param ms_id
	 * @author luyun
	 * @date 2011-03-14
	 */
	TbMsInfoExt getExtById(String ms_id);
	/**
	 * 检索对象是否存在
	 * @param hql
	 * @param obj
	 * @return
	 */
	 public int getMsById(String hql,Object...obj);
	
	/**根据ms表中所有信息(falg=1)记录列表
	 */
	List <TbMsInfo> getAllMs_Info(String pagentid,String childagentid,String ep);
	
	
	/**
	 * 用于群组与终端用户关系批量添加时(取得该grpid没有添加的终端信息)
	 * @param grpid
	 * @return
	 */
	List getAllMs_Info_not_Bygrpid(String grpid,String ep);
	List getAllMs_InfoBygrpid(String grpid,String ep);
	
	List getAllMs_Info_notQF_Bygrpid(String grpid,String ep);
	
	/**
	 * 用于GPS与终端关系批量添加时(取得该gpsid没有添加的终端信息)
	 * @param gpsid
	 * @return
	 */
	List getAllMs_Info_not_Bygpsid(String ep,String gpsid);


	/**终端表查询数量
	 * @param user_type  终端类型
	 * @param statue  在线(离线)状态
	 * @param flag  有效状态
	 * @param ms_id  终端号码
	 * @return 终端用户模糊查询的数量
	 */
	int getMs_sertch(String user_type,String statue,String flag,String ms_id,String ms_name,String pagentid,String childagentid,String ep,String ismobile,String arrearage);
	
	
	
	/**
	 *@param user_type  终端类型
	 * @param statue  在线(离线)状态
	 * @param flag  有效状态
	 * @param ms_id  终端号码
	 * @return 终端用户ms_id做模糊查询页的全部终端用户信息
	 */
	List <TbMsInfo> getTBMsinfoby_mspage(int pageNo, int pagesize,
			String user_type,String statue,String flag,String ms_id,String ms_name,String pagentid,String childagentid,String ep,String ismobile,String arrearage);
	
	/**
	 * @author wanglei
	 *@param user_type  终端类型
	 * @param statue  在线(离线)状态
	 * @param flag  有效状态
	 * @param ms_id  终端号码
	 * @return 终端用户ms_id做模糊查询页的全部终端用户信息
	 */
	List <TbMsInfo> getTBMsinfoby_mspage(int pageNo, int pagesize,
			String user_type,String statue,String flag,String ms_id,String ms_name,String pagentid,String childagentid,
			String ep,String ismobile,String arrearage,int roleId,String agentid,String epId);

	/**
	 * @author wanglei
	 * id数组查找msinfo list
	 * @param msidlist
	 * @return
	 */
	List<TbMsInfo> msInfoList(String[] msidlist);
	//	
//	/**
//	 * @author wanglei
//	 *@param user_type  终端类型
//	 * @param statue  在线(离线)状态
//	 * @param flag  有效状态
//	 * @param ms_id  终端号码
//	 * @return 终端用户ms_id做模糊查询页的全部终端用户信息
//	 */
//	List <TbMsInfo> getTBMsinfoListby_mspage(int pageNo, int pagesize,
//			String user_type,String statue,String flag,String ms_id,String ms_name,String pagentid,String childagentid,String ep,String ismobile,String arrearage);
//	
	
	/**根据ms_id列表
	 * 冻结记录
	 * @param msidlist
	 */
	Boolean msdj(String[] msidlist);
	
	/**
	 * 根据终端id，修改终端密码
	 * @param msID
	 * @param pwd
	 * @return
	 */
	Boolean updatePwd(String msID,String pwd);
	/**
	 * 修改终端
	 * @param msinfo
	 * @return
	 */
	Boolean update(TbMsInfo msinfo);
	
	/**
	 * 根据代理商ID查询待分配到企业的终端
	 * @param aid
	 * @return
	 */
	List getMsInfo_byAid(String aid,int msCount);
	
	/**
	 * 根据要充值的企业查询终端（不包括调度用户）
	 * @param ep_id
	 * @return
	 */
	List getMsInfo_byEpid(String ep_id);
	List getMsInfo_byEpid(StringBuffer ep_id);
	List getMsInfo_byEpid(StringBuffer ep_id,String ms);
	
	//-------------------------------分级调度用到的-----------------------------------
	/**epid（企业ID）和grpid(群组id) 
	 * 
	 */
	List <TbMsInfo> getNonentityMsinfo_ByGrpid(String epid,String grpid);
	
	public List<TbMsInfo> listMsInfoByEpId(int epid,int mstype,String bool);
	public List<TbMsInfo> listMsInfoByEpId(int epid,int mstype,String bool,String msid);

	String findMsIdByPhone (String phone);

}