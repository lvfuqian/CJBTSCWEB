package com.elegps.tscweb.serivce.impl;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jgroups.tests.perf.Data;

import com.elegps.tscweb.comm.HibernateUtil;
import com.elegps.tscweb.dao.DeductDaoFactory;
import com.elegps.tscweb.dao.impl.DeductDaoHibernate;
import com.elegps.tscweb.exception.MessageException;
import com.elegps.tscweb.serivce.DeductManager;

public class DeductManagerImpl implements DeductManager {

	private DeductDaoFactory deductdao;
	public DeductManagerImpl() throws MessageException {
		try {
			deductdao = new DeductDaoHibernate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new MessageException("初始化业务逻辑组件出现异常...");
		}
	}


	public void getDeduct(){
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
			String dateNow = dateFormat.format(new Date());
            List<String> list= deductdao.findArrearsMS(sess, dateNow);//拿出欠费的
            System.out.println("《《《《《《《有"+list.size()+"个终端欠费！》》》》》》》");
            StringBuffer msid = new StringBuffer();
            if(list.size()>0){
            	for(int i = 0 ; i< list.size(); i++){
            		msid.append("'").append(list.get(i)).append("'"); 
					if(i!= list.size()-1){
						msid.append(",");
					}
				}
            	deductdao.frozenMS(sess, msid.toString());//欠费就把他砍了
            	System.out.println("《《《《《《《冻结了"+list.size()+"个终端！》》》》》》》");
            }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
	}

	
}
