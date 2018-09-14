package com.elegps.tscweb.dao;

import org.hibernate.Session;

import com.elegps.tscweb.model.TbMsInfo;
import com.elegps.tscweb.model.TbMsInfoExt;

import java.io.Serializable;
import java.util.List;

public interface MsDaoFactory
{
	/**
	 * 根据主键加载终端用户信息
	 * @param sess 持久化操作所需要的Hiberate Session
	 * @param MS_Id 需要加载的终端用户信息
	 * @return 加载的终端用户信息
	 */
	TbMsInfo get(Session sess , String MS_Id);  //这里不行主键只能为int,long不能为字符串

	/**
	 * 保存终端用户信息
	 * @param sess 持久化操作所需要的Hiberate Session
	 * @param m 需要保存的终端用户信息
	 * @retun 终端用户信息的主键
	 */
    String save(Session sess , TbMsInfo m);
    
    /**
     * 创建对象
     * @param session 持久化操作所需要的Hibernate Session
     * @param obj 需要保存的对象信息
     * @author ACER
     * @date 2011-03-14
     */
    public void create(Session session,Object obj);
    /**
     * 修改对象
     * @param session 持久化操作所需要的Hibernate Session
     * @param hql
     * @param obj
     * @author ACER
     * @date 2011-03-14
     */
    public int update(Session session,String hql,Object...obj);
    
    /**
     * 根据ID获取对应表内容
     * @param session
     * @param hql
     * @param obj
     * @author ACER
     * @date 2011-03-14
     */
    public  Object getExtById(Session session,String hql,Object...obj);
    /**
     * 检索对象是否存在
     * @param session
     * @param hql
     * @param obj
     * @return
     */
     public int getMsById(Session session ,String hql,Object...obj);
    
    
	/**
	 * 删除终端用户信息
	 * @param sess 持久化操作所需要的Hiberate Session
	 * @param m 需要删除的终端用户信息
	 */
    void delete(Session sess , TbMsInfo m);

	/**
	 * 删除终端用户信息
	 * @param sess 持久化操作所需要的Hiberate Session
	 * @param id 需要删除的终端用户信息MS_Id
	 */
    void delete(Session sess , String MS_Id);

	/**
	 * 修改终端用户信息
	 * @param sess 持久化操作所需要的Hiberate Session
	 * @param m 需要修改的终端用户信息
	 */
    void update(Session sess , TbMsInfo m);
    
	/**
	 * 查询指定用户状态为1、指定页的终端用户信息
	 * @param sess 持久化操作所需要的Hiberate Session
	 * @param pageNo 需要查询的指定页
	 * @param  pageSize  页的个数
	 * @return 查询到的终端用户信息集合  
	 */
    List findAllMs_typeByPage(Session sess , int pageNo , int pageSize);
    
    
    /**
	 * 查询指定用户、指定页的终端用户信息
	 * @param sess 持久化操作所需要的Hiberate Session
	 * @param pageNo 需要查询的指定页
	 * @param pageSize 需要查询的指定页数量
	 * @param ms_type 需要查询的指定终端用户类型
	 * @return 查询到的终端用户信息集合  findMs_typeByPage
	 */
    List findMs_typeByPage(Session sess , int pageNo , int pageSize,int ms_type);

	
	/**
	 * 查询终端用户类型信息的条数
	 * @param sess 持久化操作所需要的Hiberate Session
	 * @param ms_type 终端用户类型
	 * @return 终端用户类型信息条数
	 */
	Integer findMs_typeCount(Session sess,int ms_type);
	
	
	/**
	 * 查询终端用户类型信息的条数
	 * @param sess 持久化操作所需要的Hiberate Session
	 * @param ms_type 终端用户类型
	 * @return 所有终端用户类型信息条数
	 */
	Integer findAllMs_typeCount(Session sess);
	
	/**
	 * 返回特定指定条件终端用户信息
	 * @param msId 终端号码
	 * @param msType 终端所属类型
	 * @param Flag  终端用户的状态
	 * @return 指定页的全部终端用户信息
	 */
	List getTbMsInfo(Session sess,String msId,int msType,int Flag);
	
	
	/**
	 * 查询终端用户在线状态信息的条数
	 * @param sess 持久化操作所需要的Hiberate Session
	 * @param ms_online 终端用户在线状态
	 * @return 终端用户在线状态信息条数
	 */
	Integer findMs_OnlineCount(Session sess,int ms_online);
	
	
	/**
	 * 查询所有终端用户状态信息的条数
	 * @param sess 持久化操作所需要的Hiberate Session
	 * @param ms_online 终端用户状态
	 * @return 所有终端用户状态信息条数
	 */
	Integer findMs_flagAllCount(Session sess);
	
	
	/**
	 * 查询所有终端用户在线状态信息的条数
	 * @param sess 持久化操作所需要的Hiberate Session
	 * @param ms_online 终端用户在线状态
	 * @return 所有终端用户在线状态信息条数
	 */
	Integer  findAllMs_OnlineCount(Session sess);
	
	
	/**
	 * 根据ms_id做模糊查询
	 * @return 终端用户ms_id做模糊查询的数量
	 */
	Integer  findMs_idAllCount(Session sess,String ms_id);
	
	
	/**
	 * 获取终端用户状态信息数量
	 * @param flag 终端用户状态对应总记录数
	 * @return 终端用户状态为flag信息的数量 
	 */
	Integer findMs_flagAllCount(Session sess,int flag);
	
	
	/**
	 * 查询指定用户在线状态为1、指定页的终端用户信息
	 * @param sess 持久化操作所需要的Hiberate Session
	 * @param pageNo 需要查询的指定页
	 * @param  pageSize  页的个数
	 * @return 查询到的终端用户所有状态信息集合 按在线状态排序
	 */
	List findAllOnlineByPage(Session sess , int pageNo , int pageSize);
	
	
	/**
	 * 根据ms_id做模糊查询
	 * @return 终端用户ms_id做模糊查询页的全部终端用户信息
	 */
	List findMs_idlistByPage(Session sess , int pageNo ,int pageSize,String ms_id);
	
	/**
	 * 查询指定用户状态为1、指定页的终端用户信息
	 * @param sess 持久化操作所需要的Hiberate Session
	 * @param pageNo 需要查询的指定页
	 * @param  pageSize  页的个数
	 * @param   onlineStatus 终端在线状态
	 * @return 查询到的终端用户信息集合 按在线状态排序
	 */
	List findMs_OnlineByPage(Session sess , int pageNo , int pageSize, int onlineStatus);
	
	
	/**
	 * 查询所有终端用户状态信息
	 * @param sess 持久化操作所需要的Hiberate Session
	 * @param ms_online 终端用户在线状态
	 * @return 所有终端用户在线状态信息
	 */
	List findMs_flaglistByPage(Session sess , int pageNo , int pageSize);
	
	/**
	 * 查询指定终端用户状态为flag信息
	 * @param sess 持久化操作所需要的Hiberate Session
	 * @param ms_online 终端用户在线状态
	 * @return 指定终端用户状态为flag信息
	 */
	List findMs_flaglistByPage(Session sess , int pageNo , int pageSize,int falg);
	
	/**根据ms表中所有信息(falg=1)记录列表
	 */
	List findMs_Info(Session sess,String pagentid,String childagentid,String ep);
	
	
	/**
	 * 用于群组与终端用户关系批量添加时(取得该grpid没有添加的终端信息
	 * @param sess
	 * @param grpid
	 * @return
	 */
	List findMs_Info_bygrpid(Session sess,String grpid,String ep);
	
	List findMs_Falg_bygrpid(Session sess,String grpid,String ep);
	
	/**
	 * 用于终端套餐转移时(取得没欠费的终端）
	 * @param sess
	 * @param grpid
	 * @return
	 */
	List find_notQF_Ms_Info_bygrpid(Session sess,String grpid,String ep,String dateNow);
	
	/**
	 * 用于GPS与终端用户关系批量添加时(取得该gpsid没有添加的终端信息
	 * @param sess
	 * @param gpsid
	 * @return
	 */
	List findMs_Info_bygpsid(Session sess,String ep,String gpsid);

	/**终端表查询数量
	 * @param user_type  终端类型
	 * @param statue  在线(离线)状态
	 * @param flag  有效状态
	 * @param ms_id  终端号码
	 * @return 终端用户模糊查询的数量
	 * sess,user_type,statue,flag, ms_id
	 */
	Integer  findMs_sertchCount(Session sess,String user_type,String statue,String flag,String ms_id,String ms_name,String pagentid,String childagentid,String ep,String ismobile,String arrearage);
	

	/**终端表查询数量
	 * @param user_type  终端类型
	 * @param statue  在线(离线)状态
	 * @param flag  有效状态
	 * @param ms_id  终端号码
	 * @return 终端用户模糊查询的数量
	 * sess,user_type,statue,flag, ms_id
	 */
	List  findMs_sertchByPage(Session sess, int pageNo, int pageSize,
			String user_type,String statue,String flag,String ms_id,String ms_name,String pagentid,String childagentid,String ep,String ismobile,String arrearage);
	
	/**终端表查询数量
	 * @author wanglei
	 * @param user_type  终端类型
	 * @param statue  在线(离线)状态
	 * @param flag  有效状态
	 * @param ms_id  终端号码
	 * @return 终端用户模糊查询的数量
	 * sess,user_type,statue,flag, ms_id
	 */
	List  findMsListInfo_sertchByPage(Session sess, int pageNo, int pageSize,
			String user_type,String statue,String flag,String ms_id,String ms_name,String pagentid,String childagentid,String ep,String ismobile,String arrearage,int roleId,String agentid,String epId);
	
	
	/**
	 * 根据用户单位删除终端、群组与终端、调度
	 * @param sess
	 * @param epid
	 */
	void deleteMsInfoByEp_id(Session sess,String epid);
	/**
	 * 根据代理商ID查询待分配到企业的终端
	 * @param aid
	 * @return
	 */
	List getMsInfo_byAid(Session sess,String aid,int msCount);
	/**
	 * 根据充值企业查询终端（不包括调度用户）
	 * @param sess
	 * @param ep_id
	 * @return
	 */
	List getMsInfo_byEpidao(Session sess,String ep_id);
	List getMsInfo_byEpidao(Session sess,StringBuffer ep_id);
	List getMsInfo_byEpidao(Session sess,StringBuffer ep_id,String ms);
	
	
	//-------------------------------分级调度用到的-----------------------------------
	/**epid（企业ID）和grpid(群组id) 
	 * 
	 */
	List getNonentityMsinfo_ByGrpid(Session sess,String epid,String grpid);

	List<TbMsInfo> listMsInfo(Session session);
	public List<TbMsInfo> listMsInfoByEpId(Session session,Object...objects);
	public List<TbMsInfo> listMsInfoIsByEpId(Session session,Object...objects);
	
	String findMsIdByPhone (Session sess,String phone);
	
}