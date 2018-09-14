package com.elegps.tscweb.model;

import java.util.Date;

/**
 * ��Ϣ֪ͨ
 * TbParamsInfo entity
 *@author wanglei
 *@date : 2015-6-5 ����04:27:29
 */
public class TbParamsInfo implements java.io.Serializable {
	
	private Integer id;
	private String msid;
	private String content;//��������
	private Date crateTime;//����ʱ��
	private Integer type;//0: �滮·�� 1: ����
	private Integer count;//���ʹ��� Ĭ��0
	private Integer flag;//�Ƿ��� 0: δ���� 1: ����
	private String operator;//�·���
	
	/**
	 * 
	 */
	public TbParamsInfo(){
		
	}
	
	/**
	 * 
	 * @param id
	 * @param msid
	 * @param content��������
	 * @param crateTime����ʱ��
	 * @param type0: �滮·�� 1: ����
	 * @param count���ʹ��� Ĭ��0
	 * @param flag�Ƿ��� 0: δ���� 1: ����
	 * @param operator�·���
	 */
	public TbParamsInfo(Integer id,String msid,String content,Date crateTime,Integer type,Integer count,Integer flag,String operator){
		this.id=id;
		this.msid = msid;
		this.content = content;
		this.crateTime = crateTime;
		this.type = type;
		this.count = count;
		this.flag = flag;
		this.operator = operator;
	}
	
	
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the msid
	 */
	public String getMsid() {
		return msid;
	}
	/**
	 * @param msid the msid to set
	 */
	public void setMsid(String msid) {
		this.msid = msid;
	}
	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}
	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * @return the crateTime
	 */
	public Date getCrateTime() {
		return crateTime;
	}
	/**
	 * @param crateTime the crateTime to set
	 */
	public void setCrateTime(Date crateTime) {
		this.crateTime = crateTime;
	}
	/**
	 * @return the type
	 */
	public Integer getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * @return the count
	 */
	public Integer getCount() {
		return count;
	}
	/**
	 * @param count the count to set
	 */
	public void setCount(Integer count) {
		this.count = count;
	}
	/**
	 * @return the flag
	 */
	public Integer getFlag() {
		return flag;
	}
	/**
	 * @param flag the flag to set
	 */
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	/**
	 * @return the operator
	 */
	public String getOperator() {
		return operator;
	}
	/**
	 * @param operator the operator to set
	 */
	public void setOperator(String operator) {
		this.operator = operator;
	}
	
	
}
