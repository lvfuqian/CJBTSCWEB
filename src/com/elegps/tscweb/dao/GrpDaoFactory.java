package com.elegps.tscweb.dao;

import org.hibernate.Session;

import com.elegps.tscweb.model.TbAgentInfo;
import com.elegps.tscweb.model.TbEnterpriseInfo;
import com.elegps.tscweb.model.TbGrpInfo;
import com.elegps.tscweb.model.TbMsInfo;

import java.util.List;
import java.util.Map;

public interface GrpDaoFactory
{
	/**
	 * 用于群组id生成
	 * @param sess
	 * @param grp 群组号前17位进行模糊查询，获取1条数据
	 * @return 最大的群组id
	 */
	String getGrpId(Session sess ,String grp);
	
	/**
	 * 查询所有群组类型信息的条数
	 * @param sess 持久化操作所需要的Hiberate Session
	 * @return 所有群组类型信息条数
	 */
	Integer findAllGrp_typeCount(Session sess);
	
	
	/**
	 * 查询指定群组有效状态为1、指定页的群组信息
	 * @param sess 持久化操作所需要的Hiberate Session
	 * @param pageNo 需要查询的指定页
	 * @param  pageSize  页的个数
	 * @return 查询到的群组信息集合  
	 */
    List findAllGrp_typeByPage(Session sess , int pageNo , int pageSize);
    
    
    /**
	 * 查询群组类型信息的条数
	 * @param sess 持久化操作所需要的Hiberate Session
	 * @param grp_type 群组类型
	 * @return 群组类型信息条数
	 */
	Integer findGrp_typeCount(Session sess,int grp_type);
	
	
	/**
	 * 查询指定群组类型、指定页的群组信息
	 * @param sess 持久化操作所需要的Hiberate Session
	 * @param pageNo 需要查询的指定页
	 * @param pageSize 需要查询的指定页数量
	 * @param grp_type 需要查询的指定群组类型
	 * @return 查询到的群组信息集合  
	 */
    List findGrp_typeByPage(Session sess , int pageNo , int pageSize,int grp_type);
    
    /**
	 * 根据主键加载群组信息
	 * @param sess 持久化操作所需要的Hiberate Session
	 * @param grp_id 需要加载的群组信息
	 * @return 加载的群组信息
	 */
    TbGrpInfo get(Session sess , String grp_id);  //这里不行主键只能为int,long不能为字符串
    
    /**
	 * 查询所有群组在线通话状态信息的条数
	 * @param sess 持久化操作所需要的Hiberate Session
	 * @return 所有群组在线通话状态信息条数
	 */
	Integer  findAllGrp_statusCount(Session sess);
	
	
	/**
	 * 查询所有群组有效在线状态为1、指定页的终端用户信息
	 * @param sess 持久化操作所需要的Hiberate Session
	 * @param pageNo 需要查询的指定页
	 * @param  pageSize  页的个数
	 * @return 查询到的群组所有有效状态信息集合 按在线通话状态排序
	 */
	List findAllStatueByPage(Session sess , int pageNo , int pageSize);
	
	
	/**
	 * 查询群组有效在线状态信息的条数
	 * @param sess 持久化操作所需要的Hiberate Session
	 * @param status 群组有效在线状态
	 * @return 群组有效在线状态信息的条数
	 */
	Integer findGrp_StatusCount(Session sess,int status);
	
	
	
	/**
	 * 查询群组有效在线状态信息
	 * @param sess 持久化操作所需要的Hiberate Session
	 * @param pageNo 需要查询的指定页
	 * @param  pageSize  页的个数
	 * @param   status 群组有效在线状态
	 * @return 查询到的群组有效在线状态信息集合 按有效在线状态排序
	 */
	List findGrp_StatusByPage(Session sess , int pageNo , int pageSize, int status);
	
	

	/**
	 * 根据grpid做模糊查询
	 * @return 群组grpid做模糊查询的数量
	 */
	Integer  findGrp_idAllCount(Session sess,String grpid);
	
	

	/**
	 * 根据grpid做模糊查询
	 * @return 群组grpid做模糊查询页的全部群组信息
	 */
	List findGrp_idlistByPage(Session sess , int pageNo ,int pageSize,String grpid);
	
	
	
	/**
	 * 查询所有群组有效状态信息的条数
	 * @param sess 持久化操作所需要的Hiberate Session
	 * @return 所有群组有效状态信息条数
	 */
	Integer findGrp_flagAllCount(Session sess);
	
	/**
	 * 查询所有群组有效状态信息
	 * @param sess 持久化操作所需要的Hiberate Session
	 * @return 所有群组有效状态信息
	 */
	List findGrp_flaglistByPage(Session sess , int pageNo , int pageSize);
	
	
	/**
	 * 获取指定群组有效状态信息数量
	 * @param flag 群组有效状对应总记录数
	 * @return 指定群组有效状态信息数量 
	 */  
	Integer findGrp_flagCount(Session sess,int flag);
	
	
	/**
	 * 查询指定群组有效状态信息
	 * @param sess 持久化操作所需要的Hiberate Session
	 * @param falg 群组有效状态
	 * @return 指定群组有效状态信息
	 */
	List findGrp_flaglistByPage(Session sess , int pageNo , int pageSize,int falg);
	
	
	/**
	 * 返回所有有效状态(状态为1)的终端用户信息
	 * @return 返回所有有效状态(状态为1)的终端用户信息
	 */ 
	List findMs_flagList(Session sess);
	
	
	/**
	 * 保存终端群组
	 * @param sess 持久化操作所需要的Hiberate Session
	 * @param m 需要保存的群组信息
	 * @retun 群组信息的主键
	 */
    String save(Session sess , TbGrpInfo m);
	
    /**
	 * 所有群组信息
	 * @return 指定页的全部群组信息
	 */
    List findAllGrp_Info(Session sess,String pagentid,String childagentid,String ep);
    List findBaseGrp_Info(Session sess,String pagentid,String childagentid,String ep);
    
    
    /**
     * 根据grp_id,ms_id查询是不是这个终端创建的组
     * @param sess
     * @param grp_id
     * @param ms_id
     * @return
     */
    Boolean findGrp_InfobyMs_id(Session sess,String grp_id,String ms_id);
    
    
    /**群组表查询数量
	 * @param grp_type  群组类型
	 * @param statue  通话状态
	 * @param flag  有效状态
	 * @param ms_id  群组号码
	 * @param ms_id  群组名称
     * @param ep2 
	 * @return 群组模糊查询的数量
	 */
	Integer  findGrp_sertchCount(Session sess,String user_type,String statue,String flag,String ms_id,String ms_name,String pagentid,String childagentid, String ep);
	
	
	/**群组表查询信息
	 * @param grp_type  群组类型
	 * @param statue  通话状态
	 * @param flag  有效状态
	 * @param grp_id  群组号码
	 * @param grp_name  群组名称
	 * @param ep2 
	 * @return 群组模糊查询的信息
	 */
	List  findGrp_sertchByPage(Session sess, int pageNo, int pageSize,
			String grp_type,String statue,String flag,String grp_id,String grp_name,String pagentid,String childagentid, String ep);
	
	
	/**
	 * 用于群组与终端用户关系批量添加时(取得该msid没有添加的群组信息)
	 * @param sess
	 * @param msid
	 * @return
	 */
	List findGrp_Info_bymsid(Session sess,String msid,String ep);
	
	/**
	 * 根据用户单位id,删除群组,及群组与终端对应关系
	 * @param sess
	 * @param epid
	 */
	void deleteGrpInfoByEp_id(Session sess,String epid);
	
	/**
	 * 根据用户单位ID查询用户单位信息 zr
	 * @param sess
	 * @param id
	 * @return
	 */
	TbEnterpriseInfo getEpNameByEpid(Session sess,Integer id);
	
	/**
	 * 所有用户单位查询 zr
	 */
	List getAllEp(Session sess);
	
	/**
	 * 根据群组ID查询群组所属的用户单位 zr
	 */
	TbEnterpriseInfo getNameByGrpid(Session sess,String grpid);
	
	/**
	 * 用于群组与终端用户关系跨企业批量添加时(取得该源企业没有添加的群组信息)
	 * @param sess
	 * @param msid
	 * @return
	 */
	List findGrp_Info_bymsidGrp(Session sess,String msid,String yep);
	
	/**
	 * 根据群组创建者ID（MS_ID）查找所有群组信息
	 * @param sess
	 * @param msid
	 * @return
	 */
	List findGrp_Info_byMsId(Session sess,String msid);
	
	//------分级调度用到的删除群组-------------
	Boolean grpall_byid(Session sess,String grpid);
	
	//添加对象
	public void create(Session session,Object objects);
	//修改对象
	public int update(Session session, String hql, Object...objects);
	public void update(Session session,TbGrpInfo grpInfo);
	//删除对象
	public int executeQuery(Session session, String hql, Object...objects);
	//查询对象
	public List listObjectInfo(Session session, String hql, int pageNO,int pageSize);
	//根据条件查询对象
	public List listObject(Session session, String hql, int pageNO,int pageSize,Object...objects);
	//统计条数
	public int toaltCount(Session session , String hql, Object...objects);
	//获得一个对象
	public  Object getBean(Session session, String hql, Object...objcts);
	
	/**
	 * 群组名、id模糊查询
	 * @param nameOrId
	 * @param epid
	 * @return
	 */
	public Map<String,String> getGrpByNameOrId(Session session,String nameOrId,int epid);
}