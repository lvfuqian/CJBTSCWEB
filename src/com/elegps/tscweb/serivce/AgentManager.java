package com.elegps.tscweb.serivce;

import java.util.List;

import org.hibernate.Session;

import com.elegps.tscweb.model.TbAgentInfo;




public interface AgentManager 
{ 
	/**
	 * 根据查询条件代现商名称取得记录总数
	 * @param agent_name
	 * @return 查询条件取得记录总数
	 */
	int getAgent_SearchCount(String agent_name,String agent_one);
	
	/**
	 * 根据查询条件代现商名称取得记录总数
	 * @param agent_name
	 * @return 查询条件取得记录总数
	 */
	int getAgent_SearchCount(String agent_name,String agent_one,int pid);
	
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
	 * 根据指定页及查询条件取得记录信息	 * 
	 * @param agent_name
	 * @return 指定页及查询条件取得记录信息
	 */
	List getTbAgentInfoby_name(int pageNo, int pageSize,String agent_name ,
			String agent_type);
	
	/**
	 * 根据指定页及查询条件取得记录信息	 * 
	 * @param agent_name
	 * @return 指定页及查询条件取得记录信息
	 */
	List getTbAgentInfoby_name(int pageNo, int pageSize,String agent_name ,
			String agent_type,int pid);
	/**
	 * 返回特定指定条件代理商详细信息
	 * @param agentId 代理商id
	 */
	TbAgentInfo getTbAgentinfo_byagentid(String agentId);
	/**
	 * 添加一条代理商信息   zr
	 * @param agentid
	 * @param agentname
	 * @param agentaddress
	 * @param agenttel
	 * @param agentemail
	 * @param agenturl
	 * @param agentman
	 * @param agentqq
	 * @param agentpid
	 * @param agentremark
	 */
	public String saveagent(String agentname,
			String agentaddress, String agenttel,String agenttel1,String agentemail,
			String agenturl, String agentman,String agentman1, String agentqq, String agentpid,
			String agentremark);
	/**
	 * 修改代理商信息,不能修改与所属上一级代理商的关系 zr
	 * @param agentaddress
	 * @param agenttel
	 * @param agentemail
	 * @param agenturl
	 * @param agentman
	 * @param agentqq
	 * @param agentremark
	 */
	public String updateagent(String agentid,String agentname,String agentaddress, String agenttel,String agenttel1, String agentemail,
			String agenturl, String agentman,String agentman1,String agentqq,String agentremark);
	
	/**
	 * 修改代理商信息
	 * @param sess
	 * @param aInfo
	 * @return id
	 */
	Boolean updateAgent(TbAgentInfo aInfo);
	
	/**
	 * 查询所有的一级代理商(代理商父ID为0的) zr
	 * @return 
	 */
    public List getParentAgent(int agent_id,int r_id);
    
    /**
     * 获取除了总部以外的代理商
     */
    public List getParentAgentNotIncludeZB();
    
    /**
     * 根据代理商ID查询代理商信息  zr
     * @return
     */
    public TbAgentInfo getAgent_ByAgentID(String agentid);
    /**
     * 查询所有代理商 zr
     */
    List getAll_Agent();
    /**
     * 代理商信息删除 zr
     */
    boolean deleteagentById(String[] agentid);
    
    /**
     * 根据ID获得代理商名称
     */
    public TbAgentInfo getAgentName(String id);
    
    /**
     * 根据代理商名称查询代理商信息zr
     * @param agentname
     * @return
     */
    public TbAgentInfo getAgentByName(String agentname);

    
    /**供DWR调用，查询指定一级代理商下的二级代理商
     * 父id
     * @param paramentid
     * @return
     */
    public  List getChildAgentByParamentid(String paramentid);
    
    /**
     * 二级代理商用户时二级代理商下拉框显示信息
     * @param agent
     * @return
     */
    public List getChildAgentByAId(String agent);

    /**
     *根据代理商id，查询代理商类型(一级代理商，二级代理商） 
     * @param agent_Id
     * @return
     */
	int getAgenttype(String agent_Id);
}