package com.elegps.tscweb.model.hstmodel;

import java.util.Date;

public class TbAquaticPrice {
	private int id;
	private String name; // ��Ʒ����
	private double price; // �۸�
	private String type; // 1-�Ƿ� 2-����
	private String sort; // ����

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

}
