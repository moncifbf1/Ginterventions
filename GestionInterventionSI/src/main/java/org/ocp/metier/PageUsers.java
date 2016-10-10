package org.ocp.metier;

import java.io.Serializable;
import java.util.List;

import org.ocp.entities.User;

public class PageUsers implements Serializable{
	private List<User> users;
	private int page;
	private int nombreUsers; 
	private int totalUsers;
	private int totalPages;
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getNombreUsers() {
		return nombreUsers;
	}
	public void setNombreUsers(int nombreUsers) {
		this.nombreUsers = nombreUsers;
	}
	public int getTotalUsers() {
		return totalUsers;
	}
	public void setTotalUsers(int totalUsers) {
		this.totalUsers = totalUsers;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	
}
