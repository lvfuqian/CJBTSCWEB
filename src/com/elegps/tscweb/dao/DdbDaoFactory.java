/**
 * 自定义通用接口
 * 项目 TSCWEBTREE
 * package com.elegps.tscweb.dao
 * @author luyun
 * @date 2011-03-16
 */
package com.elegps.tscweb.dao;

import java.util.List;

import org.hibernate.Session;

/**
 * 为调度用户添加信息
 * @author ACER
 *@date 2011-03-16
 */
public interface DdbDaoFactory<T> {
	//添加对象
	public void create(Session session,Object objects);
	//修改对象
	public int update(Session session, String hql, Object...objects);
	//删除对象
	public int executeQuery(Session session, String hql, Object...objects);
	//查询对象
	public List<T> listObjectInfo(Session session, String hql, int pageNO,int pageSize);
	//根据条件查询对象
	public List<T> listObject(Session session, String hql, int pageNO,int pageSize,Object...objects);
	//统计条数
	public int toaltCount(Session session , String hql, Object...objects);
	//获得一个对象
	public <T> T getBean(Session session, String hql, Object...objcts);
	public int getMaxId(Session session,Object obj);
}
