package org.ocp.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Model implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id_model;
	private String nom_model;
	private String description_model;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="INTERV_MATER", 
            joinColumns = { @JoinColumn (name = "model") }, 
            inverseJoinColumns = { @JoinColumn(name = "intervention") })
	private Collection<Intervention> interventions;
	
	@ManyToOne
	@JoinColumn(name="fournisseur")
	private Fournisseur fournisseur;
	
	public int getId_model() {
		return id_model;
	}
	public void setId_model(int id_model) {
		this.id_model = id_model;
	}
	public String getNom_model() {
		return nom_model;
	}
	public void setNom_model(String nom_model) {
		this.nom_model = nom_model;
	}
	public String getDescription_model() {
		return description_model;
	}
	public void setDescription_model(String description_model) {
		this.description_model = description_model;
	}
	public Fournisseur getFournisseur() {
		return fournisseur;
	}
	public void setFournisseur(Fournisseur fournisseur) {
		this.fournisseur = fournisseur;
	}	
}
