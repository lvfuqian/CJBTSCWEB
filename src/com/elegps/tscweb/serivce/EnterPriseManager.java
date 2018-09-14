package com.elegps.tscweb.serivce;

import java.util.List;



import com.elegps.tscweb.model.TbAgentInfo;
import com.elegps.tscweb.model.TbEnterpriseInfo;
import com.elegps.tscweb.model.TbMsInfo;




public interface EnterPriseManager 
{ 
	/**
	 * 用户单位数量查询
	 * @param aggent_id  代理商id
	 * @param ep_name  用户单位名称
	 * @return
	 */
	int getEp_sertch(String aggent_id,String childagentid,String ep_name,int ep_id,int r_id);
	
	/**
	 * 根据每页记录数，总记录数获取总页数
	 * @param count 总记录数
	 * @param pageSize 每页显示的记录数
	 * @return 计算得到的总页数
	 */
	int getPageCount(int count , int pageSize);
	
  /**
   * 
   * @param pageNo
   * @param pageSize
   * @param agent_id
   * @param ep_name
   * @return  跟据用户单位ep_name做模糊查询页的全部用户单位信息
   */

	List <TbEnterpriseInfo> getEpinfoby_mspage(int pageNo,
			int pageSize, String agent_id,String childagentid,String ep_name, int ep_id,int r_id);
	
	
//	/**
//	 * 返回指定代理商id下的一级代理跟二级代理
//	 * @param agent_id
//	 * @return
//	 */
//	List getAgentinfo_byagentid(String agent_id);
	
	
	/**
	 * 返回特定指定条件用户详细信息
	 * @param epId 企业id
	 */
	TbEnterpriseInfo getEpinfo_byepid(String epId);
	
	/**
	 * 增加用户单位信息
	 * @param ep_name
	 * @param ep_address
	 * @param ep_tel
	 * @param ep_email
	 * @param ep_url
	 * @param ep_man
	 * @param ep_qq
	 * @param agent_id
	 * @param ep_remark
	 * @return
	 */
	String createEp(String ep_name,String ep_address,String ep_tel,String ep_tel1,String ep_email,String ep_url,String ep_man,String ep_man1,String ep_qq,String agent_id,String ep_remark);
	
	
	/**
	 * 修改用户单位信息
	 * @param ep_id
	 * @param ep_name
	 * @param ep_address
	 * @param ep_tel
	 * @param ep_email
	 * @param ep_url
	 * @param ep_man
	 * @param ep_qq
	 * @param ep_remark
	 * @return
	 */
	String modifyEp(String agent_id,String ep_id,String ep_name,String ep_address,String ep_tel,String ep_tel1,String ep_email,String ep_url,String ep_man,String ep_man1,String ep_qq,String ep_remark);
	
	
	/**
	 *根据用户单位id删除用户单位信息 
	 * @param epidlist
	 * @return
	 */
	Boolean deleteEp(String[] epidlist);
	
    /**
     * 返加指定代理商下所有的用户单位
     * @param agentid
     * @return
     */
	List <TbEnterpriseInfo> getEpinfo_byagentid(String pagentid,String childagentid,int a_id,int r_id);
	
	
	
	/**
     * 返加指定代理商下指定的用户单位
     * @param epid
     * @return
     */
	List <TbEnterpriseInfo> getEpinfo_byeid(String pagentid,String childagentid,int ep_id,int r_id);
	/**
     * 根据企业名称查询信息
     * @param agentname
     * @return
     */
    TbEnterpriseInfo getEpByName(String epname);
    /**
     * 根据企业ID查询所属代理商
     */
    TbAgentInfo getAgent_ByEpName(String epid);
    
    
    /**
     * 指定企业余额ID，查询企业信息
     * @param ep_id
     * @return
     */
    List getChargeEpinfo_byepid(String ep_id);
    

    /**
     * 返加指定代理商下所有的用户单位
     * @param agentid
     * @return
     */
	List <TbEnterpriseInfo> getEpinfo_byagentid(String agent);


	/**
	 * 修改ep信息
	 * @param ep
	 * @return
	 */
	Boolean updateEp(TbEnterpriseInfo ep);
	
	/**
	 * 增加用户单位信息
	 * @param ep_name
	 * @param ep_address
	 * @param ep_tel
	 * @param ep_email
	 * @return
	 */
	String createEp(String ep_name,String ep_address,
			String ep_tel,String ep_tel1,String productID,String sIID,String bizID,
			String areaCode,String custID,String custAccount,String summary,
			String agent_id);
}