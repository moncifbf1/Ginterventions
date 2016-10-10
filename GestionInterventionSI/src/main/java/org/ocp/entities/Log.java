package org.ocp.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Log implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id_log;
	private String userCode;
	private String heure;
	private String date; 
	private String action;
	private String userType;
	
	
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public int getId_log() {
		return id_log;
	}
	public void setId_log(int id_log) {
		this.id_log = id_log;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getHeure() {
		return heure;
	}
	public void setHeure(String heure) {
		this.heure = heure;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public Log(String userCode, String heure, String date, String action) {
		super();
		this.userCode = userCode;
		this.heure = heure;
		this.date = date;
		this.action = action;
	}
	public Log() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
