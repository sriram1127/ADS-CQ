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
@Table(name = "errorreport")
public class Report implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3169135425004616050L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "requested_time")
	private Timestamp requestedTime;

	@Column(name = "start_time")
	private Timestamp startTime;

	@Column(name = "end_time")
	private Timestamp endTime;

	@Column(name = "ip")
	private String ip;

	@Column(name = "message")
	private String message;

	@Column(name = "threshold")
	private Integer threshold;

	public Report() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Report(Integer id, Timestamp requestedTime, Timestamp startTime, Timestamp endTime, String ip,
			String message, Integer threshold) {
		super();
		this.id = id;
		this.requestedTime = requestedTime;
		this.startTime = startTime;
		this.endTime = endTime;
		this.ip = ip;
		this.message = message;
		this.threshold = threshold;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Timestamp getRequestedTime() {
		return requestedTime;
	}

	public void setRequestedTime(Timestamp requestedTime) {
		this.requestedTime = requestedTime;
	}

	public Timestamp getStartTime() {
		return startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public Timestamp getEndTime() {
		return endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getThreshold() {
		return threshold;
	}

	public void setThreshold(Integer threshold) {
		this.threshold = threshold;
	}

}
