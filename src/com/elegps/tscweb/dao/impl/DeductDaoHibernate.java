package com.elegps.tscweb.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import com.elegps.tscweb.comm.DeductMonthDay;
import com.elegps.tscweb.dao.DeductDaoFactory;
import com.elegps.tscweb.model.TbMSDeductInfo;
import com.elegps.tscweb.model.TbMsInfo;


public class DeductDaoHibernate implements DeductDaoFactory {



	/**
	 * �ն˿۷ѣ�2015-5-15 �ϳ��˹��� @wanglei��
	 * 
	 */
	public void excuteDeduct(Session sess) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String deductdate=dateFormat.format(new Date());
		String nextdeductdate="";
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<����dao");
		try{
			String hql="from TbMsInfo ms where ms.charge_date='"+deductdate+"'";
			List msdeduct = sess.createQuery(hql).list();
			for(int i=0;i<msdeduct.size();i++){
				
				TbMsInfo ms=(TbMsInfo)msdeduct.get(i);
				TbMSDeductInfo msdeductinfo=new TbMSDeductInfo();
				//����ն������ڵ�������ʱ
				if(ms.getBalance_cash()>=ms.getPackage_fee()){
					//��¼�ն˿�������ʷ��¼
					msdeductinfo.setEpId(ms.getEpid());
	                msdeductinfo.setMsId(ms.getMsId());
	                msdeductinfo.setDeductcash(ms.getPackage_fee());
	                msdeductinfo.setDeductdate(new Date());
	                sess.save(msdeductinfo);
					//��������ʱȡ����һ�ο۷�����
					nextdeductdate=DeductMonthDay.getnextDate();
					ms.setBalance_cash(ms.getBalance_cash()-ms.getPackage_fee());
					ms.setCharge_date(nextdeductdate);
					ms.setIs_Arrearage(1);  //1����û��Ƿ�� 0����Ƿ��
					ms.setMsMessage_post(1);  //0:֪ͨ�ն����㣬���ֵ (����֪ͨ) 1:��֪ͨ����
                    sess.update(ms);
                    
				}else if(ms.getBalance_cash()>0){
					float f=ms.getBalance_cash();
				    //��¼�ն˿�������ʷ��¼
					msdeductinfo.setEpId(ms.getEpid());
	                msdeductinfo.setMsId(ms.getMsId());
	                msdeductinfo.setDeductcash((int)f);
	                msdeductinfo.setDeductdate(new Date());
	                sess.save(msdeductinfo);
					//����������ʱȡ����һ�ο۷�����
					nextdeductdate=DeductMonthDay.getnextDate(ms.getPackage_fee(),ms.getBalance_cash()); //��һ���������⣬�ڶ������
					ms.setBalance_cash(0);
					ms.setCharge_date(nextdeductdate);
					ms.setIs_Arrearage(1);  //1����û��Ƿ�� 0����Ƿ��
					if(DeductMonthDay.getDate(ms.getPackage_fee(),ms.getBalance_cash())<=5){  //ʣ�����������ʱ��������ʾ
					    ms.setMsMessage_post(1);  //0:֪ͨ�ն����㣬���ֵ (����֪ͨ) 1:��֪ͨ����
					}else{
						ms.setMsMessage_post(0);
					}
                    sess.update(ms);                   
				}else{
					ms.setBalance_cash(0);
					ms.setCharge_date("");
					ms.setIs_Arrearage(0);  //1����û��Ƿ�� 0����Ƿ��
					ms.setMsMessage_post(0);
                    sess.update(ms);
				}
			}
			System.out.println("daoִ�����<<<<<<<<<<<<<<<<<<<<<<<<<<<");
			sess.beginTransaction().commit();
		}catch (Exception e){
			sess.beginTransaction().rollback();
		}finally{
			
		}
		
	}
	
	/**
	 * �����ײ͵���ʱ���ѯ����Ƿ���ն�
	 * @param sess
	 * @param dataNow
	 * @return
	 */
	public List<String> findArrearsMS(Session sess ,String dataNow){
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<����dao,Ƿ��MS����");
		try {
			List<String> list = sess.createQuery(
					"select m.msId from TbMsInfo m where m.msMoneyTime <='"+dataNow+"' and m.flag != 0 ").list();
							//+ " order by m.msId desc").list();
			if (list != null) {
				return list;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sess != null) {
				// sess.close();
			}
		}
		return null;
	}
	
	/**
	 * ��������Ƿ���ն�
	 * @param sess
	 * @param msid
	 */
	public void frozenMS(Session sess,String msid){
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<����dao,Ƿ�Ѷ���MS");
		try {
			sess.createQuery(
					"update TbMsInfo m set m.pf.pfId = -1,m.is_Arrearage = 0,m.flag = 0 where m.msId in ("+msid+") ")
					.executeUpdate();  ;
							//+ " order by m.msId desc").list();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sess != null) {
				// sess.close();
			}
		}
	}
}
