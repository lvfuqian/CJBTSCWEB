package com.elegps.tscweb.model.hstmodel;

public class TbTyphoonList {
	private int id;
	private String no; // 台风编号
	private String nameCn; // 台风中文名
	private String nameEn; // 台风英文名

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getNameCn() {
		return nameCn;
	}

	public void setNameCn(String nameCn) {
		this.nameCn = nameCn;
	}

	public String getNameEn() {
		return nameEn;
	}

	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}

}
