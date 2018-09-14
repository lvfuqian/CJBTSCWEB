package com.elegps.tscweb.serivce;

import java.util.List;


import com.elegps.tscweb.model.TbQuestionInfo;


public interface QuestionManager 
{ 
  /**
   * ���ܼ�¼����
   * @param type
   * @param state
   * @param visitstate
   * @param recorder
   * @param henchman
   * @param solve_man
   * @return
   */
	int getQuestion_sertch(String type,String state,String recorder,String henchman,String solve_man);
	
	/**
	 * ��ҳ��
	 * @return
	 */
	int getPageCount(int questionCount, int pageSize);
	
	
	/**
	 * ������
	 * @param pageNo
	 * @param pagesize
	 * @param user_type
	 * @param statue
	 * @param flag
	 * @param ms_id
	 * @param ms_name
	 * @param agent
	 * @return
	 */
	List <TbQuestionInfo> getQuestioinfoby_page(int pageNo, int pagesize,
			String type,String state,String recorder,String henchman,String solve_man);

	/**
	 * ��ӿͷ���Ϣ
	 * @param type
	 * @param question
	 * @param telphone
	 * @return
	 */
	String createQuestion(String usename,String type, String question,String cep,String cname, String telphone,String recordertime);

	/**
	 * ����id��ѯ
	 * @param id
	 * @return
	 */
	TbQuestionInfo getQuestionby_id(String id);

	
	/**
	 * �޸Ŀͷ���Ϣ
	 * @param userName
	 * @param id
	 * @param question
	 * @param question2
	 * @param resolvent
	 * @param solve_man
	 * @param state
	 * @param visitstate
	 * @return
	 */
	String modifyQuestion(String userName, String id,String type, String question, String resolvent, String solve_man, String state,
			String cep,String cname,String telephone);
	
}