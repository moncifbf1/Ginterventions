package org.ocp.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.google.gson.annotations.Expose;
import io.searchbox.annotations.JestId;
import javax.persistence.JoinColumn;
import com.fasterxml.jackson.annotation.JsonSubTypes;

@Entity
@Table(name="users")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="TYPEUSER", discriminatorType=DiscriminatorType.STRING,length=2)
@JsonTypeInfo(use=JsonTypeInfo.Id.NAME,include=JsonTypeInfo.As.PROPERTY,property="type")
@JsonSubTypes({
	@Type(name="ADMINISTRATEUR", value=Administrateur.class),
	@Type(name="RESPONSABLE", value=Responsable.class),
	@Type(name="UTILISATEUR", value=Utilisateur.class),
	@Type(name="OPERATEUR", value=Operateur.class)
	
})
public class User implements Serializable{
	@Id
	@JestId
	private String username;
	private String discrimUser;
	private String firstName;
	private String lastName;
	private String password;
	private boolean actived;
	private String dateCreation;
	private String telephone;
	private String token;
	private String mail;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name="USERS_ROLES", 
            joinColumns = { @JoinColumn (name = "username") }, 
            inverseJoinColumns = { @JoinColumn(name = "role") })
	@Expose(serialize = false)
	private Collection<Role> roles;
	
	public String getDiscrimUser() {
		return discrimUser;
	}
	public void setDiscrimUser(String discrimUser) {
		this.discrimUser = discrimUser;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Collection<Role> getRoles() {
		return roles;
	}
	
	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(String dateCreation) {
		this.dateCreation = dateCreation;
	}
	public boolean isActived() {
		return actived;
	}
	public void setActived(boolean actived) {
		this.actived = actived;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public User(String username, String firstName, String lastName, String password, boolean actived,
			String dateCreation) {
		super();
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.actived = actived;
		this.dateCreation = dateCreation;
	}
	public User() {
		super();
	}
	
}
