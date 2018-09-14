package com.elegps.tscweb.serivce.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.elegps.tscweb.comm.HibernateUtil;
import com.elegps.tscweb.comm.SessionFactoryBuilder;
import com.elegps.tscweb.dao.QuestionDaoFactory;
import com.elegps.tscweb.dao.impl.QuestionDaoHibernate;
import com.elegps.tscweb.exception.MessageException;
import com.elegps.tscweb.model.TbMsInfo;
import com.elegps.tscweb.model.TbQuestionInfo;
import com.elegps.tscweb.serivce.QuestionManager;

public class QuestionManagerImpl implements QuestionManager {

	private QuestionDaoFactory questiondao;
	public QuestionManagerImpl() throws MessageException {
		try {
			questiondao = new QuestionDaoHibernate();

		} catch (Exception e) {
			e.printStackTrace();
			throw new MessageException("初始化业务逻辑组件出现异常...");
		}

	}
	public int getQuestion_sertch(String type, String state, 
			String recorder, String henchman, String solve_man) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			Integer count=questiondao.getQuestion_sertchCount(sess,type,state, recorder,henchman,solve_man);
			if(count!=null){
				tx.commit();
				return count;
			}else{
				tx.commit();
				return 0;
			}			
		} catch (Exception e) {
			if (null != tx)
				tx.rollback();
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
		return 0;
	}

	
	/**
	 * 根据每页记录数，总记录数获取总页数
	 * 
	 * @param count
	 *            总记录数
	 * @param pageSize
	 *            每页显示的记录数
	 * @return 计算得到的总页数
	 */
	public int getPageCount(int count, int pageSize) {
		return (count + pageSize - 1) / pageSize;
	}
	
	
	public List<TbQuestionInfo> getQuestioinfoby_page(int pageNo, int pagesize,
			String type, String state, String recorder,
			String henchman, String solve_man) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			List ml = questiondao.getQuestion_sertchByPage(sess, pageNo, pagesize,type,state,recorder,henchman,solve_man);
			if (ml != null && ml.size() > 0) {
				List<TbQuestionInfo> result = new ArrayList<TbQuestionInfo>();
				for (Object obj : ml) {
					TbQuestionInfo me = (TbQuestionInfo) obj;
					result.add(me);
				}
				tx.commit();
				return result;
			}else{
				tx.commit();
				return null;
			}		
		} catch (Exception e) {
			if (null != tx)
				tx.rollback();
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
		return null;
	}
	public String createQuestion(String username,String type, String question,String cep,String cname, String telephone,String recordertime) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = sdf.parse(recordertime);		
			TbQuestionInfo Question = new TbQuestionInfo();
			Question.setType(Integer.parseInt(type));
			Question.setContent(question);
			Question.setCep(cep);
			Question.setCname(cname);
			Question.setTelephone(telephone);
			Question.setRecorder(username);
			Question.setRecorder_Time(date);
			Question.setHenchman("");
			Question.setSolve_Man("");
			Question.setResolyent("");
			sess.save(Question);
		    tx.commit();
		    return new String("sucess");
		} catch (Exception e) {
			if (null != tx)
				tx.rollback();
			e.printStackTrace();
			
		} finally {
			HibernateUtil.closeSession();
		}
		return null;
	}
	public TbQuestionInfo getQuestionby_id(String id) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			TbQuestionInfo qeustion=questiondao.get(sess, id);
			if(qeustion!=null){
				tx.commit();
				return qeustion;
			}else{
				tx.commit();
				return null;
			}			
		} catch (Exception e) {
			if (null != tx)
				tx.rollback();
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
		return null;
	}
	public String modifyQuestion(String userName, String id,String type, String question,
			String resolvent, String solve_man, String state,String cep,String cname,String telephone) {
		Transaction tx=null;
		try {
			Date date = new Date();	
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			TbQuestionInfo Question=questiondao.get(sess, id);
			if(Question!=null){
				Question.setType(Integer.parseInt(type));
				Question.setContent(question);
				Question.setResolyent(resolvent);
				Question.setSolve_Man(solve_man);
				Question.setState(Integer.parseInt(state));
				Question.setCep(cep);
				Question.setCname(cname);
				Question.setTelephone(telephone);
				if(Integer.parseInt(state)==1&&Question.getSolve_Time()==null){
					Question.setSolve_Time(date);
					Question.setHenchman(userName);
				}
				sess.update(Question);
			}
			tx.commit();
			return new String("sucess");
		} catch (Exception e) {
			if (null != tx)
				tx.rollback();
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
		return null;
	}

	
}
