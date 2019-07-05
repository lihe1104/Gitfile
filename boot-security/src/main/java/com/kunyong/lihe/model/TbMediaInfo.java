package com.kunyong.lihe.model;

import java.util.Date;

public class TbMediaInfo extends BaseEntity<Long> {

	private String mType;
	private String name;
	private String descc;
	private Integer opeUser;
	private Date opeTime;
	private String url;
	private String status;
	private Integer groupId;

	public String getMType() {
		return mType;
	}
	public void setMType(String mType) {
		this.mType = mType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescc() {
		return descc;
	}
	public void setDescc(String descc) {
		this.descc = descc;
	}
	public Integer getOpeUser() {
		return opeUser;
	}
	public void setOpeUser(Integer opeUser) {
		this.opeUser = opeUser;
	}
	public Date getOpeTime() {
		return opeTime;
	}
	public void setOpeTime(Date opeTime) {
		this.opeTime = opeTime;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getGroupId() {
		return groupId;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

}
