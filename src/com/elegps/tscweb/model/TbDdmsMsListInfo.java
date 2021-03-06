package com.elegps.tscweb.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * TbMsInfo generated by MyEclipse Persistence Tools
 */

public class TbDdmsMsListInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	private String ddms_id;
	private String ms_id;
	private Date create_time;
	private String c01;
	private String c02;
	
	
	 // 必须重新定义equals()与hashCode()

	  public boolean equals(Object obj) {

	      if(obj == this) {

	          return true;

	      }
	      if(!(obj instanceof TbDdmsMsListInfo)) {

	          return false;

	      }

	      TbDdmsMsListInfo ddmsmslist = (TbDdmsMsListInfo) obj;
	      return new EqualsBuilder()
	               .append(this.ddms_id, ddmsmslist.getDdms_id())
	               .append(this.ms_id, ddmsmslist.getMs_id())
	               .isEquals();
	  }

	 

	  public int hashCode() {

	      return new HashCodeBuilder()

	               .append(this.ddms_id)

	               .append(this.ms_id)

	               .toHashCode();

	  }



	public String getDdms_id() {
		return ddms_id;
	}



	public void setDdms_id(String ddms_id) {
		this.ddms_id = ddms_id;
	}



	public String getMs_id() {
		return ms_id;
	}



	public void setMs_id(String ms_id) {
		this.ms_id = ms_id;
	}



	public Date getCreate_time() {
		return create_time;
	}



	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}



	public String getC01() {
		return c01;
	}



	public void setC01(String c01) {
		this.c01 = c01;
	}



	public String getC02() {
		return c02;
	}



	public void setC02(String c02) {
		this.c02 = c02;
	}
	
	

}