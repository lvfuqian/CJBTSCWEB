package com.elegps.tscweb.dao;

import java.util.List;

public interface TrafficDAO<T> {
	public int executeQuery(String hql, Object... object);

	public void create(Object object);

	public int totalCount(String hql, Object... objects);

	public int totalCount(String hql);

	public List<T> listAll(String hql, int pageNo, int pageSize,
			Object... objects);

	public List<T> listAll(String hql, int pageNo, int pageSize);

	public <T> T getBean(String hql, Object... objects);
}
