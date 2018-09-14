package com.elegps.tscweb.dao;

import java.util.List;

import org.hibernate.Session;

import com.elegps.tscweb.model.TbORGAdvanceInfo;

public interface AdvanceDaoFactory
{
	/**
	 * 返回指定条件，指定页数的记录
	 * @param sess
	 * @param pageNo
	 * @param pageSize
	 * @param agent
	 * @param ep
	 * @return
	 */
	 List findAdvance_SearchByPage(Session sess,int pageNo,int pageSize,String pagentid,String childagentid,String ep,String advanceresult);
	 
	 
	 /**
	  * 返回总条数
	  * @param sess
	  * @param agent
	  * @param ep
	  * @return
	  */
    Integer findadvance_Count(Session sess,String pagentid,String childagentid,String ep,String advanceresult);
    
    /**
     * 保存预充值信息
     * @param sess
     * @param orgadvance
     * @return
     */
    String save(Session sess , TbORGAdvanceInfo orgadvance);
    
   /**
    * 根据预充值id查询记录
    * @param sess
    * @param advanceid
    * @return
    */
    TbORGAdvanceInfo get(Session sess , String advanceid);  //这里不行主键只能为int,long不能为字符串
    
    /**
     * 删除预充值信息
     * @param sess
     * @param advanceid
     */
    void delete(Session sess,String advanceid);
    
    
    /**
     * 充值历史记录数
     * @param sess
     * @param agent
     * @param ep
     * @param advanceresult
     * @return
     */
    Integer getCharge_Count(Session sess,String pagentid,String childagentid,String ep);
    
    
    
    /**
     * 充值历史
     * @param sess
     * @param pageNo
     * @param pageSize
     * @param agent
     * @param ep
     * @return
     */
    List getCharge_SearchByPage(Session sess,int pageNo,int pageSize,String pagentid,String childagentid,String ep);
	
}