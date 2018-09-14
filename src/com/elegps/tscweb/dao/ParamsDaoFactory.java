package com.elegps.tscweb.dao;

import java.util.List;
import org.hibernate.Session;

import com.elegps.tscweb.model.TbParamsInfo;

public interface ParamsDaoFactory {
	
	/**
	 * �����Ϣ֪ͨ
	 * @param sess
	 * @param paramsInfo
	 */
	void add(Session sess,TbParamsInfo paramsInfo);
	
	/**
	 * 
	 * @return ��ѯ����֪ͨ��Ϣ
	 */
	List<TbParamsInfo> get(Session sess); 
	
	/**
	 * ֪ͨ��Ϣ
	 * ��ҳ������ѯ������
	 * @param sess
	 * @param msid type flag operator
	 * @return
	 */
	Integer getCount(Session sess,int pageNo,int pageSize,String msid,Integer type,Integer flag,String operator);
	
	/**
	 * ֪ͨ��Ϣ
	 * ��ҳ������ѯ
	 * @param msid type flag operator
	 * @return 
	 */
	List<TbParamsInfo> get(Session sess,int pageNo,int pageSize,String msid,Integer type,Integer flag,String operator); 
	
	/**
	 *  �޸�֪ͨ��Ϣ
	 * @param sess
	 * @param paramsInfo
	 */
	void update(Session sess,TbParamsInfo paramsInfo);
	
	/**
	 * id����֪ͨ��Ϣ
	 * @param sess,id
	 * @return
	 */
	TbParamsInfo findById(Session sess,int id);
	
	/**
	 * �����ն�id��ѯ
	 * @param sess,msid
	 * @return
	 */
	TbParamsInfo findByMsId(Session sess,String msid);
	
	/**
	 * ɾ����Ϣ֪ͨ
	 * @param sess
	 * @param paramsInfo
	 */
	void delete(Session sess,TbParamsInfo paramsInfo);
	
}
