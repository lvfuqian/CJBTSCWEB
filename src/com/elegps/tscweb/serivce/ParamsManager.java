package com.elegps.tscweb.serivce;

import java.util.List;
import com.elegps.tscweb.model.TbParamsInfo;

public interface ParamsManager {

	/**
	 * �����Ϣ֪ͨ
	 * @param sess
	 * @param paramsInfo
	 */
	String add(TbParamsInfo paramsInfo);
	
	/**
	 * 
	 * @return ��ѯ����֪ͨ��Ϣ
	 */
	List<TbParamsInfo> get(); 
	
	/**
	 * ֪ͨ��Ϣ
	 * ��ҳ������ѯ������
	 * @param sess
	 * @param msid type flag operator
	 * @return
	 */
	Integer getCount(int pageNo,int pageSize,String msid,Integer type,Integer flag,String operator);
	
	/**
	 * ֪ͨ��Ϣ
	 * ��ҳ������ѯ
	 * @param msid type flag operator
	 * @return 
	 */
	List<TbParamsInfo> get(int pageNo,int pageSize,String msid,Integer type,Integer flag,String operator); 
	
	/**
	 *  �޸�֪ͨ��Ϣ
	 * @param sess
	 * @param paramsInfo
	 */
	Boolean update(TbParamsInfo paramsInfo);
	
	/**
	 * id����֪ͨ��Ϣ
	 * @param sess,id
	 * @return
	 */
	TbParamsInfo findById(int id);
	
	/**
	 * �����ն�id��ѯ
	 * @param sess,msid
	 * @return
	 */
	TbParamsInfo findByMsId(String msid);
	
	/**
	 * ɾ����Ϣ֪ͨ
	 * @param sess
	 * @param id
	 */
	Boolean delete(Integer id);
	/**
	 * ����ɾ����Ϣ֪ͨ
	 * @param sess
	 * @param id
	 */
	Boolean delete(String[] id);
}
