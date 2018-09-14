package com.elegps.tscweb.serivce;

import java.util.List;

import org.hibernate.Session;

import com.elegps.tscweb.exception.MessageException;
import com.elegps.tscweb.model.TbGrpInfo;
import com.elegps.tscweb.model.TbGrpMsListInfo;




public interface GrpMsManager 
{ 
	/**
	 * 获取群组所有信息数量
	 * 群组所有信息总记录数
	 * @return 获取群组所有信息数量 
	 */
	int getAllGrp_idCount();

	/**
	 * 根据每页记录数，总记录数获取总页数
	 * @param count 总记录数
	 * @param pageSize 每页显示的记录数
	 * @return 计算得到的总页数
	 */
	int getPageCount(int count , int pageSize);
	
	/**
	 * 返回群组终端对应关系指定页面所有群组终端对应关系信息
	 * @param pageNo 指定页码
	 * @param  pagesize 每页显示的条数
	 * @return 返回群组终端对应关系指定页面所有群组终端对应关系信息
	 */ 
	List <TbGrpMsListInfo> getAllGrpid_InfoByPage(int pageNo,int pagesize);
	
	/**
	 * 获取指定群组号所有信息数量
	 * @param grp_id 群组号
	 * @return 获取指定群组号所有信息数量
	 */
	int getGrp_idCount(String grp_id);
	
	
	/**
	 * 返回群组终端对应关系中按群组号指定页面所有群组终端对应关系信息
	 * @param pageNo 指定页码
	 * @param  pagesize 每页显示的条数
	 * @param  grp_id 群组号
	 * @return 返回群组终端对应关系指定页面所有群组终端对应关系信息
	 */ 
	List <TbGrpMsListInfo> getGrpid_InfoByPage(int pageNo,int pagesize,String grp_id);
	
	
	/**
	 * 获取终端所有信息数量
	 * 群组所有信息总记录数
	 * @return 获取群组所有信息数量 
	 */
	int getAllms_idCount();
	
	
	/**
	 * 返回群组终端对应关系指定页面所有群组终端对应关系信息
	 * @param pageNo 指定页码
	 * @param  pagesize 每页显示的条数
	 * @return 返回群组终端对应关系指定页面所有群组终端对应关系信息
	 */ 
	List <TbGrpMsListInfo> getAllMsid_InfoByPage(int pageNo,int pagesize);
	
	
	/**
	 * 获取指定终端号所有信息数量
	 * @param ms_id 终端号
	 * @return 获取指定终端号所有信息数量
	 */
	int getMs_idCount(String ms_id);
	
	
	
	/**
	 * 群组终端对应关系中按终端号指定页面所有群组终端对应关系信息
	 * @param pageNo 指定页码
	 * @param  pagesize 每页显示的条数
	 * @param  ms_id 终端号
	 * @return 返回群组终端对应关系中按终端号指定页面所有群组终端对应关系信息
	 */ 
	List <TbGrpMsListInfo> getMsid_InfoByPage(int pageNo,int pagesize,String ms_id);
	

	/**根据grp_id,ms_id 删除记录
	 * @param grpmsidlist
	 */
	Boolean deleteGrpMs(String[] grpmsidlist);
	
	
	/**根据grp_id删除记录
	 * @param grp_id 群组号码
	 */
	void GrpMsInfodeletebyGrp_id(String grp_id);
	
	/**根据ms_id删除记录
	 * @param ms_id 群组号码
	 */
	void GrpMsInfodeletebyms_id(String ms_id);
	
	
	/**
	 * 创建一条群组终端对应关系信息
	 * @param grp_id 群组号码
	 * @param ms_id 终端号
	 * @return 新创终端的主键,如果创建失败，返回null。
	 */  
	String createGrpMsInfo(String grp_id , String ms_id);
	
	/**
	 * 根据查询条件取得记录总数
	 * @param grpid
	 * @param msid
	 * @return  查询条件取得记录总数
	 */
   int getGrpMs_SearchCount(String grpid,String msid,String pagentid,String childagentid,String ep);
   
  
  
   /**
    * 跟据群组号查询已经存在的终端信息
    * @param grpid
    * @return
    */
   List  getMs_info(String grpid);
   
   
   /**
    * 跟据终端号查询已经存在的群组信息
    * @param msid
    * @return
    */
   List getGrp_info(String msid);
   
   /**
    * 跟据群组号批量添加终端号
    * @param grpid
    * @param msid
    * @return
    */
  String createGrpMsInfo_ByGrpid(String grpid,String[] msid);
  
  /**
   * 跟据终端号批量添加群组号
   * @param grpid
   * @param msid
   * @return
   */
 String createGrpMsInfo_ByMsid(String msid,String[] grpid);

 
 List getGrpMs_SearchByPage(int pageNo,
			int pageSize,String grpid, String msid, String pagentid,String childagentid,String ep);
 
 
 /**
  * 根据群组id,终端id查询群组终端对应关系
  * @param grpid
  * @param msid
  * @return
  */
 TbGrpMsListInfo getGrpMs_ByGrpMsid(String grpid,String msid);
 
 
 /**
  * 修改群组配置信息
  * @param grpid
  * @param msid
  * @param config
  * @return
  */
 String modifyGrpMs(String grpid,String rgrpid,String msid,String config);
 
 /**
  * 查找终端所在的主要群组（隶属类型0：MS_Id隶属GRP_Id  ， MS_Id在GRP_Id中的通话设置：0：允许通话）
  * @param sess
  * @param ms_id
  * @return
  */
	TbGrpMsListInfo getGrp(String ms_id);
	
	/**
	 * 获取某个群组的终端数量
	 * @param grpId
	 * @return
	 */
	Integer grpMsCount(String grpId);
	
	/**
	 * 获取某个群组的在线终端数
	 * @param grpId
	 * @return
	 */
	Integer grpOnLineMsCount(String grpId);
	
	boolean deleteGrpMsByMsId(String msId);
	
	/**
	 * 切换群组（其他群组都禁止通话）
	 * @return
	 */
	boolean changeGrpMsConfig(String msId,String grpId);
	
	/**
    * 跟据群组号查询终端状态信息
    * @param grpid
    * @return
    */
   List  getMsState(String grpid);
	
}