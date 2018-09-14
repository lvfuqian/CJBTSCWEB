package com.elegps.tscweb.serivce.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.elegps.tscweb.comm.HibernateUtil;
import com.elegps.tscweb.dao.WeatherAnalysisDaoFactory;
import com.elegps.tscweb.dao.impl.WeatherAnalysisDaoHibernate;
import com.elegps.tscweb.exception.MessageException;
import com.elegps.tscweb.model.hstmodel.TbWeatherAnalysis;
import com.elegps.tscweb.serivce.WeatherAnalysisManager;

public class WeatherAnalysisManagerImpl implements WeatherAnalysisManager{
	private WeatherAnalysisDaoFactory dao;
	public WeatherAnalysisManagerImpl() throws MessageException {
		try {
			dao =new WeatherAnalysisDaoHibernate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new MessageException("初始化业务逻辑组件出现异常...");
		}
	}
	@Override
	public void save(TbWeatherAnalysis wa) {
		Transaction tx = null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			dao.add(sess, wa);
			tx.commit();
		} catch (Exception e) {
			if(tx!=null){
				tx.rollback();
			}
			e.printStackTrace();
		}finally {
			//HibernateUtil.closeSession();
		}
		
	}
}
