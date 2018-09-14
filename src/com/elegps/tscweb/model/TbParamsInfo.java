package com.elegps.tscweb.model;

import java.util.Date;

/**
 * 消息通知
 * TbParamsInfo entity
 *@author wanglei
 *@date : 2015-6-5 下午04:27:29
 */
public class TbParamsInfo implements java.io.Serializable {
	
	private Integer id;
	private String msid;
	private String content;//参数内容
	private Date crateTime;//保存时间
	private Integer type;//0: 规划路线 1: 短信
	private Integer count;//发送次数 默认0
	private Integer flag;//是否发送 0: 未发送 1: 发送
	private String operator;//下发人
	
	/**
	 * 
	 */
	public TbParamsInfo(){
		
	}
	
	/**
	 * 
	 * @param id
	 * @param msid
	 * @param content参数内容
	 * @param crateTime保存时间
	 * @param type0: 规划路线 1: 短信
	 * @param count发送次数 默认0
	 * @param flag是否发送 0: 未发送 1: 发送
	 * @param operator下发人
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
