package com.elegps.tscweb.dao;

import org.hibernate.Session;

import com.elegps.tscweb.model.TbAgentInfo;
import com.elegps.tscweb.model.TbGrpInfo;
import com.elegps.tscweb.model.TbMsInfo;

import java.util.List;

public interface AgentDaoFactory
{
	/**
	 * 根据查询条件取得记录总数
	 * 
	 * @param agent_name
	 * @return 查询条件取得记录总数
	 */
	Integer getAgent_SearchCount(Session sess, String agent_name,String agent_type);
	
	/**
	 * 根据查询条件取得记录总数
	 * 
	 * @param agent_name
	 * @return 查询条件取得记录总数
	 */
	Integer getAgent_SearchCount(Session sess, String agent_name,String agent_type,int pid);
	
	
	/**
	 * 根据指定页及查询条件取得记录信息
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param agent_name
	 * @return 指定页及查询条件取得记录信息
	 */
	List getTbAgentInfo_ByPage(Session sess, int pageNo, int pageSize,String agent_name,
			String agent_type);
	/**
	 * 根据指定页及查询条件取得记录信息
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param agent_name
	 * @return 指定页及查询条件取得记录信息
	 */
	List getTbAgentInfo_ByPage(Session sess, int pageNo, int pageSize,String agent_name,
			String agent_type,int pid);
	
	/**
	 * 根据主键加载终端代理商信息
	 * @param sess 持久化操作所需要的Hiberate Session
	 * @param agentId 需要加载的代理商信息
	 * @return 加载的代理商信息
	 */
	TbAgentInfo get(Session sess , String agentId);  //这里不行主键只能为int,long不能为字符串
	/**
	 * 添加代理商  zr
	 * @param sess
	 * @param agentinfo
	 */
	String save(Session sess,TbAgentInfo agentinfo);
	/**
	 * 修改代理商 zr
	 * @param sess
	 * @param agentinfo
	 */
	void update(Session sess,TbAgentInfo agentinfo);
	/**
	 * 查询所有一级代理商(代理商父级ID为0的) zr
	 * @param sess
	 * @return
	 */
	List getAgentByagentpid(Session sess,int agent_id,int r_id);
	
	List getAgentNotIncludeZBByagentpid(Session sess);
	
	/**
	 * 查询所有代理商 zr
	 * @param sess
	 * @return
	 */
	List getAllAgent(Session sess);
	
	/**
	 * 根据代理商ID删除代理商信息
	 */
    void delete(Session sess,String id);
    /**
     * 根据代理商名称加载信息
     * @param sess
     * @param agent_name
     * @return
     */
    TbAgentInfo getagent_byname(Session sess, String agentid,String agent_name);
    /**
     * 根据ID查询父级代理商的名称
     */
    TbAgentInfo getName_byid(Session sess,String id);
    /**
     * 根据代理商名称查询代理商信息 zr
     * @param sess
     * @param agent_name
     * @return
     */
    TbAgentInfo getAgent_Byagentname(Session sess, String agent_name);


    /**供DWR调用，查询指定一级代理商下的二级代理商
     * 父id
     * @param sess
     * @param paramentid
     * @return
     */
	List getChildAgentByParamentid(Session sess, String paramentid);
	
	 /**
     * 二级代理商用户时二级代理商下拉框显示信息
     * @param agent
     * @return
     */
    List getChildAgentByAId(Session sess,String agent);

	 /**
     *根据代理商id，查询代理商类型(一级代理商，二级代理商） 
     * @param agent_Id
     * @return
     */
	int getAgentType(Session sess, String agent_Id);

}