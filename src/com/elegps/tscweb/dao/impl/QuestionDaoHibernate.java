package com.elegps.tscweb.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.elegps.tscweb.dao.QuestionDaoFactory;
import com.elegps.tscweb.model.TbMsInfo;
import com.elegps.tscweb.model.TbQuestionInfo;

public class QuestionDaoHibernate implements QuestionDaoFactory {

	/**
	 * ��������ѯ��¼������
	 */
	public int getQuestion_sertchCount(Session sess, String type, String state,
		   String recorder, String henchman,
			String solve_man) {
		String strtype = ""; // ����
		String strstate = ""; // �Ƿ���״̬
		String strrecorder = ""; // ��¼��
		String strhenchman = ""; // ������
		String strsolve_man = ""; // �����
		String hql = "select count(q.Id) from TbQuestionInfo q where 1=1";
		if (!(type.equals(""))) // ���Ͳ���ȫ������
			strtype = " and q.Type = " + type;

		if (!(state.equals(""))) // �Ƿ���״̬����ȫ��
			strstate = " and q.State = " + state;

		if (recorder.trim().length() > 0) // ��¼�˷ǿ�,�����ȴ���0
			strrecorder = " and q.Recorder like '%" + recorder + "%' ";

		if (henchman.trim().length() > 0) // �����˷ǿ�,�����ȴ���0
			strhenchman = " and q.Henchman like '%" + henchman + "%' ";

		if (solve_man.trim().length() > 0) // ����˷ǿ�,�����ȴ���0
			strsolve_man = " and q.Solve_Man like '%" + solve_man + "%' ";

		hql += strtype + strstate  + strrecorder + strhenchman
				+ strsolve_man;
		try {
			Object obj = sess.createQuery(hql).uniqueResult();
			if (obj != null) {
				int mscount = (Integer) obj;
				return mscount;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sess != null) {
				// sess.close();
			}
		}
		return 0;
	}

	public List getQuestion_sertchByPage(Session sess, int pageNo,
			int pagesize, String type, String state, 
			String recorder, String henchman, String solve_man) {
		String strtype = ""; // ����
		String strstate = ""; // �Ƿ���״̬
		String strrecorder = ""; // ��¼��
		String strhenchman = ""; // ������
		String strsolve_man = ""; // �����
		String hql = "from TbQuestionInfo q where 1=1";
		if (!(type.equals(""))) // ���Ͳ���ȫ������
			strtype = " and q.Type = " + type;

		if (!(state.equals(""))) // �Ƿ���״̬����ȫ��
			strstate = " and q.State = " + state;

		if (recorder.trim().length() > 0) // ��¼�˷ǿ�,�����ȴ���0
			strrecorder = " and q.Recorder like '%" + recorder + "%' ";

		if (henchman.trim().length() > 0) // �����˷ǿ�,�����ȴ���0
			strhenchman = " and q.Henchman like '%" + henchman + "%' ";

		if (solve_man.trim().length() > 0) // ����˷ǿ�,�����ȴ���0
			strsolve_man = " and q.Solve_Man like '%" + solve_man + "%' ";

		hql += strtype + strstate  + strrecorder + strhenchman
				+ strsolve_man + " order by q.Id desc";
		try {
			int offset = (pageNo - 1) * pagesize;
			List list = sess.createQuery(hql).setFirstResult(offset)
					.setMaxResults(pagesize).list();
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

	public TbQuestionInfo get(Session sess, String id) {
		try {
			List question = sess.createQuery("from TbQuestionInfo q where q.Id="+id).list();
			if (question != null && question.size() > 0) {
				return (TbQuestionInfo) question.get(0);
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

}
