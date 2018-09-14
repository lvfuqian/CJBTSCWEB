package com.elegps.tscweb.dao;

import java.util.List;

import org.hibernate.Session;

import com.elegps.tscweb.model.TbQuestionInfo;

public interface QuestionDaoFactory {
	/**
	 * ��ѯ�ܼ�¼��
	 * @param sess
	 * @param type
	 * @param state
	 * @param visitstate
	 * @param recorder
	 * @param henchman
	 * @param solve_man
	 * @return
	 */
	int getQuestion_sertchCount(Session sess,String type,String state, String recorder,String henchman,String solve_man);

	
	/**
	 * ��������ѯ��ָ��ҳ�ļ�¼��
	 * @param sess
	 * @param pageNo
	 * @param pagesize
	 * @param type
	 * @param state
	 * @param visitstate
	 * @param recorder
	 * @param henchman
	 * @param solve_man
	 * @return
	 */
	List getQuestion_sertchByPage(Session sess, int pageNo, int pagesize,
			String type, String state,String recorder,
			String henchman, String solve_man);

/**
 * ��id��ѯ
 * @param sess
 * @param id
 * @return
 */
	TbQuestionInfo get(Session sess, String id);

}
