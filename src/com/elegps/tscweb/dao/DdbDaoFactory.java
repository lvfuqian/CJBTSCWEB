/**
 * �Զ���ͨ�ýӿ�
 * ��Ŀ TSCWEBTREE
 * package com.elegps.tscweb.dao
 * @author luyun
 * @date 2011-03-16
 */
package com.elegps.tscweb.dao;

import java.util.List;

import org.hibernate.Session;

/**
 * Ϊ�����û������Ϣ
 * @author ACER
 *@date 2011-03-16
 */
public interface DdbDaoFactory<T> {
	//��Ӷ���
	public void create(Session session,Object objects);
	//�޸Ķ���
	public int update(Session session, String hql, Object...objects);
	//ɾ������
	public int executeQuery(Session session, String hql, Object...objects);
	//��ѯ����
	public List<T> listObjectInfo(Session session, String hql, int pageNO,int pageSize);
	//����������ѯ����
	public List<T> listObject(Session session, String hql, int pageNO,int pageSize,Object...objects);
	//ͳ������
	public int toaltCount(Session session , String hql, Object...objects);
	//���һ������
	public <T> T getBean(Session session, String hql, Object...objcts);
	public int getMaxId(Session session,Object obj);
}
