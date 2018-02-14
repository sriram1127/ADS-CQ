package com.hib.entities;

import java.io.Serializable;
import java.sql.Timestamp;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "log")
public class Log implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5054182441959138882L;

	public Log() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Log(String ip, Timestamp date_time) {
		super();
		this.ip = ip;
		this.date_time = date_time;
	}

	public Log(String ip, Timestamp date_time, Integer statusId, Integer requestId, Integer agentId) {
		super();
		this.ip = ip;
		this.date_time = date_time;
		this.statusId = statusId;
		this.requestId = requestId;
		this.agentId = agentId;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "ip")
	private String ip;

	@Column(name = "date_time")
	private Timestamp date_time;

	@Column(name = "statusid")
	private Integer statusId;

	@Column(name = "requestid")
	private Integer requestId;

	@Column(name = "useragentid")
	private Integer agentId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Timestamp getDate_time() {
		return date_time;
	}

	public void setDate_time(Timestamp date_time) {
		this.date_time = date_time;
	}

	public Integer getStatusId() {
		return statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}

	public Integer getRequestId() {
		return requestId;
	}

	public void setRequestId(Integer requestId) {
		this.requestId = requestId;
	}

	public Integer getAgentId() {
		return agentId;
	}

	public void setAgentId(Integer agentId) {
		this.agentId = agentId;
	}

}
