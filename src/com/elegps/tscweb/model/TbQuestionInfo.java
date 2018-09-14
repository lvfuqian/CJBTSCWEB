package com.elegps.tscweb.model;

import java.util.Date;

public class TbQuestionInfo {
	Integer Id;
	int Type;
    String Content;
    String Recorder;
    Date Recorder_Time;
    String Henchman;
    String Resolyent;
    String Solve_Man;
    Date Solve_Time;
    int State;
    String Telephone;
    String Cep;
    String Cname;
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public int getType() {
		return Type;
	}
	public void setType(int type) {
		Type = type;
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	public String getRecorder() {
		return Recorder;
	}
	public void setRecorder(String recorder) {
		Recorder = recorder;
	}
	public Date getRecorder_Time() {
		return Recorder_Time;
	}
	public void setRecorder_Time(Date recorder_Time) {
		Recorder_Time = recorder_Time;
	}
	public String getHenchman() {
		return Henchman;
	}
	public void setHenchman(String henchman) {
		Henchman = henchman;
	}
	public String getResolyent() {
		return Resolyent;
	}
	public void setResolyent(String resolyent) {
		Resolyent = resolyent;
	}
	public String getSolve_Man() {
		return Solve_Man;
	}
	public void setSolve_Man(String solve_Man) {
		Solve_Man = solve_Man;
	}
	public Date getSolve_Time() {
		return Solve_Time;
	}
	public void setSolve_Time(Date solve_Time) {
		Solve_Time = solve_Time;
	}
	public int getState() {
		return State;
	}
	public void setState(int state) {
		State = state;
	}
	public String getTelephone() {
		return Telephone;
	}
	public void setTelephone(String telephone) {
		Telephone = telephone;
	}
	public String getCep() {
		return Cep;
	}
	public void setCep(String cep) {
		Cep = cep;
	}
	public String getCname() {
		return Cname;
	}
	public void setCname(String cname) {
		Cname = cname;
	}

}
