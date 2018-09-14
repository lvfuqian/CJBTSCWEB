package com.elegps.tscweb.model;

import java.util.Date;

/**
 * TbMenuInfo entity.
 * 
 * @author MyEclipse Persistence Tools
 */
//²Ëµ¥
public class TbMenuInfo implements java.io.Serializable {

	// Fields

	private Integer menuId;
	private String menuName;
	private Integer pmenuId;
	private String url;
	private Date create_time;
	private Integer c02;
	private String c03;
	private String c04;

	// Constructors

	/** default constructor */
	public TbMenuInfo() {
	}

	/** minimal constructor */
	public TbMenuInfo(Integer menuId, String menuName, Integer pmenuId,
			String url) {
		this.menuId = menuId;
		this.menuName = menuName;
		this.pmenuId = pmenuId;
		this.url = url;
	}

	// Property accessors

	public Integer getMenuId() {
		return this.menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public String getMenuName() {
		return this.menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public Integer getPmenuId() {
		return this.pmenuId;
	}

	public void setPmenuId(Integer pmenuId) {
		this.pmenuId = pmenuId;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getC02() {
		return c02;
	}

	public void setC02(Integer c02) {
		this.c02 = c02;
	}

	public String getC03() {
		return this.c03;
	}

	public void setC03(String c03) {
		this.c03 = c03;
	}

	public String getC04() {
		return this.c04;
	}

	public void setC04(String c04) {
		this.c04 = c04;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

}