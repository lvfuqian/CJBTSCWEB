package com.elegps.tscweb.serivce;

import java.util.List;

/**
 * 公用接口
 * 
 * @author LuYun
 * 
 * @param <T>
 */
public interface BaseManage<T> {
	public void create(T t);

	public int update(T t);

	public void delete(T t);

	public int total(Object... objects);

	public List<T> listAll(int pageNo, int pageSize, Object... obj);

	public T getBeanInfo(String PK);
}
