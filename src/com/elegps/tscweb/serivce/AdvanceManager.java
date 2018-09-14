package com.elegps.tscweb.serivce;

import java.util.List;

import com.elegps.tscweb.model.TbORGAdvanceInfo;




public interface AdvanceManager 
{ 
	
	/**
	 * 根据查询条件代现商名称,企业名称取得记录总数
	 * @param agent,ep
	 * @return 查询条件取得记录总数
	 */
	int getAdvance_SearchCount(String pagentid,String childagentid,String ep,String advancereulst);
	
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
	 *  根据指定页及查询条件取得记录信息
	 * @param pageNo
	 * @param pageSize
	 * @param agent
	 * @param ep
	 * @return
	 */
	 List getAdvance_SearchByPage(int pageNo,int pageSize,String pagentid,String childagentid,String ep,String advanceresult);
	 
	 
     /**
      * 增加预充值信息
      * @param orgtype
      * @param orgid
      * @param advance
      * @param advanename
      * @return
      */
	 String createAdvance(String orgtype, String orgid, String advance,String advanename);
	 
	 
	 /**
	  * 根据预充值id查询预充值信息
	  * @param advanceid
	  * @return
	  */
	 TbORGAdvanceInfo getTbORGAdvanceInfo_byadvancetid(String advanceid);
	 
	 /**
	  * 修改预充值信息
	  * @param advanceid
	  * @param advance
	  * @return
	  */
	 String modifyAdvanece(String advanceid, String advance);
	 
	 /**
	  * 删除预充值信息
	  * @param advanceidlist
	  * @return
	  */
	 Boolean deleteAdvance(String[] advanceidlist);
	 
	 /**
	  * 验证
	  * @param advanceid
	  * @param aresult
	  * @param remark
	  * @param validatename
	  * @return
	  */
	 String validate(String advanceid,String aresult,String remark,String validatename);
	 
	 /**
	  * 查询充值历史记录信息总数
	  * @param agent
	  * @param ep
	  * @return
	  */
	 int getCharge_SearchCount(String pagentid,String childagentid,String ep);
	 
	 
	 /**
	  * 查询充值历史记录信息
	  * @param pageNo
	  * @param pageSize
	  * @param agent
	  * @param ep
	  * @return
	  */
	 List getCharge_SearchByPage(int pageNo,int pageSize,String pagentid,String childagentid,String ep);
}

