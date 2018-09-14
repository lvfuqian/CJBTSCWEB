package com.elegps.tscweb.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.elegps.tscweb.dao.QuestionDaoFactory;
import com.elegps.tscweb.model.TbMsInfo;
import com.elegps.tscweb.model.TbQuestionInfo;

public class QuestionDaoHibernate implements QuestionDaoFactory {

	/**
	 * 按条件查询记录的总数
	 */
	public int getQuestion_sertchCount(Session sess, String type, String state,
		   String recorder, String henchman,
			String solve_man) {
		String strtype = ""; // 类型
		String strstate = ""; // 是否解决状态
		String strrecorder = ""; // 记录人
		String strhenchman = ""; // 跟踪人
		String strsolve_man = ""; // 解决人
		String hql = "select count(q.Id) from TbQuestionInfo q where 1=1";
		if (!(type.equals(""))) // 类型不是全部类型
			strtype = " and q.Type = " + type;

		if (!(state.equals(""))) // 是否解决状态不是全部
			strstate = " and q.State = " + state;

		if (recorder.trim().length() > 0) // 记录人非空,并长度大于0
			strrecorder = " and q.Recorder like '%" + recorder + "%' ";

		if (henchman.trim().length() > 0) // 跟踪人非空,并长度大于0
			strhenchman = " and q.Henchman like '%" + henchman + "%' ";

		if (solve_man.trim().length() > 0) // 解决人非空,并长度大于0
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
		String strtype = ""; // 类型
		String strstate = ""; // 是否解决状态
		String strrecorder = ""; // 记录人
		String strhenchman = ""; // 跟踪人
		String strsolve_man = ""; // 解决人
		String hql = "from TbQuestionInfo q where 1=1";
		if (!(type.equals(""))) // 类型不是全部类型
			strtype = " and q.Type = " + type;

		if (!(state.equals(""))) // 是否解决状态不是全部
			strstate = " and q.State = " + state;

		if (recorder.trim().length() > 0) // 记录人非空,并长度大于0
			strrecorder = " and q.Recorder like '%" + recorder + "%' ";

		if (henchman.trim().length() > 0) // 跟踪人非空,并长度大于0
			strhenchman = " and q.Henchman like '%" + henchman + "%' ";

		if (solve_man.trim().length() > 0) // 解决人非空,并长度大于0
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
