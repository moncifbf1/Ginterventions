package org.ocp.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.annotations.Expose;

@Entity
public class Role implements Serializable{	
	@Id
	@Column(name = "role")
	private String role;
	private String description;
	
	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "roles")
	@Expose(serialize = false)
	private List<User> users;
	
	@JsonIgnore
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Role(String role, String description) {
		super();
		this.role = role;
		this.description = description;
	}
	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
