package com.elegps.tscweb.serivce;

import java.util.List;


import com.elegps.tscweb.model.TbQuestionInfo;


public interface QuestionManager 
{ 
  /**
   * 求总记录条数
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
	 * 总页数
	 * @return
	 */
	int getPageCount(int questionCount, int pageSize);
	
	
	/**
	 * 按条件
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
	 * 添加客服信息
	 * @param type
	 * @param question
	 * @param telphone
	 * @return
	 */
	String createQuestion(String usename,String type, String question,String cep,String cname, String telphone,String recordertime);

	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	TbQuestionInfo getQuestionby_id(String id);

	
	/**
	 * 修改客服信息
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