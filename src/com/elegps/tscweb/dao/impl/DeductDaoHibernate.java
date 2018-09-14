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
	 * 终端扣费（2015-5-15 废除此功能 @wanglei）
	 * 
	 */
	public void excuteDeduct(Session sess) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String deductdate=dateFormat.format(new Date());
		String nextdeductdate="";
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<进入dao");
		try{
			String hql="from TbMsInfo ms where ms.charge_date='"+deductdate+"'";
			List msdeduct = sess.createQuery(hql).list();
			for(int i=0;i<msdeduct.size();i++){
				
				TbMsInfo ms=(TbMsInfo)msdeduct.get(i);
				TbMSDeductInfo msdeductinfo=new TbMSDeductInfo();
				//如果终端余额大于等于月租时
				if(ms.getBalance_cash()>=ms.getPackage_fee()){
					//记录终端扣月租历史记录
					msdeductinfo.setEpId(ms.getEpid());
	                msdeductinfo.setMsId(ms.getMsId());
	                msdeductinfo.setDeductcash(ms.getPackage_fee());
	                msdeductinfo.setDeductdate(new Date());
	                sess.save(msdeductinfo);
					//够扣月租时取得下一次扣费日期
					nextdeductdate=DeductMonthDay.getnextDate();
					ms.setBalance_cash(ms.getBalance_cash()-ms.getPackage_fee());
					ms.setCharge_date(nextdeductdate);
					ms.setIs_Arrearage(1);  //1代表没有欠费 0代现欠费
					ms.setMsMessage_post(1);  //0:通知终端余额不足，请充值 (短信通知) 1:不通知短信
                    sess.update(ms);
                    
				}else if(ms.getBalance_cash()>0){
					float f=ms.getBalance_cash();
				    //记录终端扣月租历史记录
					msdeductinfo.setEpId(ms.getEpid());
	                msdeductinfo.setMsId(ms.getMsId());
	                msdeductinfo.setDeductcash((int)f);
	                msdeductinfo.setDeductdate(new Date());
	                sess.save(msdeductinfo);
					//不够扣月租时取得下一次扣费日期
					nextdeductdate=DeductMonthDay.getnextDate(ms.getPackage_fee(),ms.getBalance_cash()); //第一个参数月租，第二个余额
					ms.setBalance_cash(0);
					ms.setCharge_date(nextdeductdate);
					ms.setIs_Arrearage(1);  //1代表没有欠费 0代现欠费
					if(DeductMonthDay.getDate(ms.getPackage_fee(),ms.getBalance_cash())<=5){  //剩余金额大于五天时不短信提示
					    ms.setMsMessage_post(1);  //0:通知终端余额不足，请充值 (短信通知) 1:不通知短信
					}else{
						ms.setMsMessage_post(0);
					}
                    sess.update(ms);                   
				}else{
					ms.setBalance_cash(0);
					ms.setCharge_date("");
					ms.setIs_Arrearage(0);  //1代表没有欠费 0代现欠费
					ms.setMsMessage_post(0);
                    sess.update(ms);
				}
			}
			System.out.println("dao执行完毕<<<<<<<<<<<<<<<<<<<<<<<<<<<");
			sess.beginTransaction().commit();
		}catch (Exception e){
			sess.beginTransaction().rollback();
		}finally{
			
		}
		
	}
	
	/**
	 * 根据套餐到期时间查询所有欠费终端
	 * @param sess
	 * @param dataNow
	 * @return
	 */
	public List<String> findArrearsMS(Session sess ,String dataNow){
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<进入dao,欠费MS查找");
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
	 * 冻结所有欠费终端
	 * @param sess
	 * @param msid
	 */
	public void frozenMS(Session sess,String msid){
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<进入dao,欠费冻结MS");
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
