package com.elegps.tscweb.serivce;

import java.util.List;

import com.elegps.tscweb.model.TabSysconfig;

public interface DdbManager<T> {

	public List<T> listAll(int pageNo, int pageSize, String userName,
			String msid,String loginid);
	public List<T> listAll(int pageNo, int pageSize, String userName,
			String msid,String loginid,StringBuffer ms_id);
	public void create(Object objects);

	public int update(String hql, Object... objects);

	public void delete(int userid);

	public int totalCount(TabSysconfig tsc);

	public int totalCount(String userName, String msid,String loginid,StringBuffer ms_id);
	public int totalCount(String userName, String msid,String loginid);
	public <T> T getBean(int id);

	public <T> T getBean(String hql, Object... objects);

	public List<T> getListBean(int pageNo, int pageSize);
	public List<T> getListBean(int pageNo, int pageSize,StringBuffer ms_id);
	public List<T> getBeanTotal(Object... objects);

	public int getMaxId(Object obj);

	public List<T> listRole(int pageNo, int pageSize);
	public <T> T getRoleBean(String hql, Object... objects);
}
