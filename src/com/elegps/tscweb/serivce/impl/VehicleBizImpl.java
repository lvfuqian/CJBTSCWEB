package com.elegps.tscweb.serivce.impl;

import java.util.Date;
import java.util.List;

import com.elegps.tscweb.dao.impl.TrafficDAOImpl;
import com.elegps.tscweb.model.PositionLast;
import com.elegps.tscweb.dao.TrafficDAO;
import com.elegps.tscweb.model.Vehicle;
import com.elegps.tscweb.serivce.TrafficBiz;

public class VehicleBizImpl implements TrafficBiz<Vehicle> {
	private TrafficDAO<Vehicle> dao;

	public VehicleBizImpl() {
		dao = new TrafficDAOImpl<Vehicle>();
	}

	public void create(Vehicle vehicle) {
//		vehicle=new Vehicle();
		vehicle.setMobileId(36);
		vehicle.setDriverId(88);
		vehicle.setVehicleTypeId(8);
		vehicle.setColorId(18);
		vehicle.setOverduetime(new Date());
		vehicle.setCreatetime(new Date());
		vehicle.setLoginenable("1");
		vehicle.setLoginpass("88888888");
		vehicle.setRemark("''");
		dao.create(vehicle);
		
		PositionLast last=new PositionLast();
		last.setVehicleId(vehicle.getId());
		last.setUserId(0);
		last.setLastruntime(new Date());
		last.setDifferencetime(new Date());
		last.setFeasibility("1");
		last.setReceiveDate(new Date());
		last.setGpsDate(new Date());
		dao.create(last);
	}

	
	public List<Vehicle> listAll( int pageNo, int pageSize,
			String chepai,String cheji,String gps) {
		int fromIdx=(pageNo-1)*pageSize;
		StringBuilder hql=new StringBuilder();
		hql.append(" FROM  Vehicle as  v  WHERE 1=1 ");
		if(chepai!=null&&!"".equals(chepai)){
			hql.append("  AND   v.chepai like ? ");
		}
		if(cheji!=null&&!"".equals(cheji)){
			hql.append("	AND  v.cheji like ? ");
		}
		if(gps!=null&&!"".equals(gps)){
			hql.append("	AND  v.GPRS like ? ");
		}
		hql.append(" ORDER BY v.id DESC");
		if(chepai!=null)
			return dao.listAll(hql.toString(), fromIdx, pageSize, "%"+chepai+"%");
		if(cheji!=null&&!"".equals(cheji))
			return dao.listAll(hql.toString(), fromIdx, pageSize, "%"+cheji+"%");
		if(gps!=null&&!"".equals(gps))
			return dao.listAll(hql.toString(), fromIdx, pageSize, "%"+gps+"%");
		return dao.listAll(hql.toString(), fromIdx, pageSize);
	}

	
	public int totalCount(String chepai,String cheji,String gps) {
		StringBuilder hql=new StringBuilder();
		hql.append("SELECT COUNT(v.id) FROM  Vehicle  as v WHERE 1=1  ");
		if(chepai!=null&&!"".equals(chepai)){
			hql.append("	AND  v.chepai like ? ");
			return dao.totalCount(hql.toString(), "%"+chepai+"%");
		}
		if(cheji!=null&&!"".equals(cheji)){
			hql.append("	AND  v.cheji like ? ");
			return dao.totalCount(hql.toString(), "%"+cheji+"%");
		}
		if(gps!=null&&!"".equals(gps)){
			hql.append("	AND  v.GPRS like ? ");
			return dao.totalCount(hql.toString(), "%"+gps+"%");
		}
		return dao.totalCount(hql.toString());
	}

	
	public int update(String chepai,String cheji,String gprs,int id) {
		String  hql=" UPDATE Vehicle SET chepai=?, cheji=? , GPRS=? WHERE id=?";
		return dao.executeQuery(hql, chepai,cheji,gprs,id);
	}

	
	public  Vehicle getBean(Object... objects) {
		String hql=" FROM Vehicle  WHERE id=?";
		return (Vehicle)dao.getBean(hql, objects);
	}
	public int delete(int id) {
		String hql=" DELETE   Vehicle WHERE id=?";
		String hql_L="DELETE PositionLast WHERE  vehicleId=?";
		if(dao.executeQuery(hql, id)>0||	dao.executeQuery(hql_L, id)>0){
			return 1;
		}
		return 0;
	}

}
