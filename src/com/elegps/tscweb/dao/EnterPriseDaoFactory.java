package com.elegps.tscweb.dao;

import java.util.List;

import org.hibernate.Session;

import com.elegps.tscweb.model.TbAgentInfo;
import com.elegps.tscweb.model.TbEnterpriseInfo;
import com.elegps.tscweb.model.TbMsInfo;


public interface EnterPriseDaoFactory
{
	/**
	 * 用户单位数量查询
	 * @param aggent_id  代理商id
	 * @param ep_name  用户单位名称
	 * @return
	 */
	Integer  findEp_sertchCount(Session sess,String aggent_id,String childagentid,String ep_name, int ep_id,int r_id);
	
	/**
	 * 查询用户信息
	 * @param sess
	 * @param pageNo
	 * @param pageSize
	 * @param agent_id
	 * @param ep_name
	 * @return
	 */
	List getEpInfo(Session sess,int pageNo,int pageSize,String agent_id,String childagentid,String ep_name, int ep_id,int r_id);
	
	
//	/**
//	 * 返回指定代理商id下所有的一级代理和二级代理
//	 * @param sess
//	 * @param agent_id
//	 * @return
//	 */
//	List getAgentInfo(Session sess,String agent_id);
	
	
	/**
	 * 根据主键加载用户单位信息
	 * @param sess 持久化操作所需要的Hiberate Session
	 * @param epId 需要加载的用户单位信息
	 * @return 加载的用户单位信息
	 */
	TbEnterpriseInfo get(Session sess , String epId);  //这里不行主键只能为int,long不能为字符串
	
	/**
	 * 增加用户信息
	 * @param sess
	 * @param m
	 * @return
	 */
    String save(Session sess , TbEnterpriseInfo m);
    
    
    /**
     * 返回指定代理商id下所有用户单位
     * @param sess
     * @param agentid
     * @return
     */
    List getEpInfo_byagentid(Session sess , String pagentid,String childagentid,int a_id,int r_id);
    
    /**
     * 返回指定代理商id下指定用户单位
     * @param sess
     * @param epid
     * @return
     */
    List getEpinfo_byeid(Session sess , String pagentid,String childagentid,int ep_id,int r_id);
    
    /**
     * 根据用户单位id删除用户单位信息
     * @param sess
     * @param epid
     */
    void delete(Session sess,String epid);
    /**
     * 根据企业名称查询
     * @param sess
     * @param ep_name
     * @return
     */
    TbEnterpriseInfo getEp_Byepname(Session sess, String ep_name);
    
    
    /**
     * 根据企业名称查询
     * @param sess
     * @param ep_name
     * @return
     */
    TbEnterpriseInfo getEp_Byepname(Session sess,String ep_id, String ep_name);
    /**
     * 根据用户ID查询所属代理商
     * @param sess
     * @param epid
     * @return
     */
    TbAgentInfo getAgent_ByEpid(Session sess,String epid);
    
    
    /**
     * 根据企业余额中的企业号查询企业信息
     * @param sess
     * @param epid
     * @return
     */
    List getEpInfo(Session sess,String epid);
    
    List getEpInfo_byagentid(Session sess , String agent);
    
    /**
     * 修改信息
     * @param sess
     * @param ep
     */
    void updateInfo(Session sess,TbEnterpriseInfo ep);
}
    