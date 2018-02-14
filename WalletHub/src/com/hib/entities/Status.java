package com.hib.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "status")
public class Status implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1369959914103548716L;

	public Status() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Status(Integer id, int code) {
		super();
		this.id = id;
		this.code = code;
	}

	public Status(int code) {
		super();
		this.code = code;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "code")
	private int code;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

}
