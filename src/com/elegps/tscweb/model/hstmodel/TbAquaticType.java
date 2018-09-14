package com.elegps.tscweb.model.hstmodel;

public class TbAquaticType {
	private int id;
	private int classId; // 分类id
	private String pid; // 分类父id,父id为0为顶级分类
	private String name; // 分类名称

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
