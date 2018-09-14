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
			throw new MessageException("��ʼ��ҵ���߼���������쳣...");
		}
	}


	public void getDeduct(){
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
			String dateNow = dateFormat.format(new Date());
            List<String> list= deductdao.findArrearsMS(sess, dateNow);//�ó�Ƿ�ѵ�
            System.out.println("����������������"+list.size()+"���ն�Ƿ�ѣ���������������");
            StringBuffer msid = new StringBuffer();
            if(list.size()>0){
            	for(int i = 0 ; i< list.size(); i++){
            		msid.append("'").append(list.get(i)).append("'"); 
					if(i!= list.size()-1){
						msid.append(",");
					}
				}
            	deductdao.frozenMS(sess, msid.toString());//Ƿ�ѾͰ�������
            	System.out.println("��������������������"+list.size()+"���նˣ���������������");
            }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
	}

	
}
