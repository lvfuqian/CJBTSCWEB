package com.elegps.tscweb.model;

import java.util.Date;

/**
 * TbPackageFeeInfo entity
 * @author wanglei
 *
 */
public class TbPFInfo implements java.io.Serializable {
	
	//套餐tb
	private Integer pfId; 	//主键id
	private String pfType;	//套餐类型
	private String pfHow;	//套餐费用
	private Integer pfTime;	//套餐时限(月)
	/**
	 * @param pfId the pfId to set
	 */
	public void setPfId(Integer pfId) {
		this.pfId = pfId;
	}
	/**
	 * @return the pfId
	 */
	public Integer getPfId() {
		return pfId;
	}
	/**
	 * @param pfType the pfType to set
	 */
	public void setPfType(String pfType) {
		this.pfType = pfType;
	}
	/**
	 * @return the pfType
	 */
	public String getPfType() {
		return pfType;
	}
	/**
	 * @param pfHow the pfHow to set
	 */
	public void setPfHow(String pfHow) {
		this.pfHow = pfHow;
	}
	/**
	 * @return the pfHow
	 */
	public String getPfHow() {
		return pfHow;
	}
	/**
	 * @param pfTime the pfTime to set
	 */
	public void setPfTime(Integer pfTime) {
		this.pfTime = pfTime;
	}
	/**
	 * @return the pfTime
	 */
	public Integer getPfTime() {
		return pfTime;
	}
}
