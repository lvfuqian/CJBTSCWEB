package com.elegps.tscweb.model.hstmodel;

public class TbAquaticType {
	private int id;
	private int classId; // ����id
	private String pid; // ���ุid,��idΪ0Ϊ��������
	private String name; // ��������

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getClassId() {
		return classId;
	}

	public void setClassId(int classId) {
		this.classId = classId;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
