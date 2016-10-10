package org.ocp.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Fournisseur implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id_fournisseur;
	private String nom_fournisseur;
	private String mail_fournisseur;
	private String tel_fournisseur;
	
	@OneToMany(mappedBy="fournisseur")
	private Collection<Model> models;
	
	public int getId_fournisseur() {
		return id_fournisseur;
	}
	public void setId_fournisseur(int id_fournisseur) {
		this.id_fournisseur = id_fournisseur;
	}
	public String getNom_fournisseur() {
		return nom_fournisseur;
	}
	public void setNom_fournisseur(String nom_fournisseur) {
		this.nom_fournisseur = nom_fournisseur;
	}
	public String getMail_fournisseur() {
		return mail_fournisseur;
	}
	public void setMail_fournisseur(String mail_fournisseur) {
		this.mail_fournisseur = mail_fournisseur;
	}
	public String getTel_fournisseur() {
		return tel_fournisseur;
	}
	public void setTel_fournisseur(String tel_fournisseur) {
		this.tel_fournisseur = tel_fournisseur;
	}
	@JsonIgnore
	@XmlTransient
	public Collection<Model> getModels() {
		return models;
	}
	public void setModels(Collection<Model> models) {
		this.models = models;
	}
	
}
