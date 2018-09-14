package com.elegps.tscweb.serivce;

import java.util.List;

import com.elegps.tscweb.model.Vehicle;

public interface TrafficBiz<T> {
	public List<T> listAll(int pageNo, int pageSize,
			String chepai,String chejis,String gps);

	public int totalCount(String chepai,String chejis,String gps);

	public int update(String chepai,String cheji,String gprs,int id);

	public void create(T t);
	public int delete(int id);
	public <T> T getBean(Object... objects);
}
