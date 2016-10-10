package org.ocp.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Intervention implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id_intervention;
	private String titre_intervention;
	private String commentIntervention;
	private int niveau_intervention;
	private int etat_intervention;
	private String duree_intervention;
	private String date_demande;
	private String date_ouverture;
	private String date_intervention;
	private String date_cloture;
	private String observation_intervention;
	private String interveModel;
	private String intervFournisseur;
	private int idModel;
	
	@ManyToOne
	@JoinColumn(name="codeOperateur")
	private Operateur operateur;
	
	@ManyToOne
	@JoinColumn(name="codeUtilisateur")
	private Utilisateur utilisateur;
	
	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "interventions")
	private List<Model> models;

	public String getInterveModel() {
		return interveModel;
	}

	public void setInterveModel(String interveModel) {
		this.interveModel = interveModel;
	}

	public String getIntervFournisseur() {
		return intervFournisseur;
	}

	public void setIntervFournisseur(String intervFournisseur) {
		this.intervFournisseur = intervFournisseur;
	}

	public Utilisateur getUser() {
		return utilisateur;
	}
		
	public void setUser(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	public int getId_intervention() {
		return id_intervention;
	}
	public void setId_intervention(int id_intervention) {
		this.id_intervention = id_intervention;
	}
	public String getTitre_intervention() {
		return titre_intervention;
	}
	public void setTitre_intervention(String titre_intervention) {
		this.titre_intervention = titre_intervention;
	}
	public int getNiveau_intervention() {
		return niveau_intervention;
	}
	public void setNiveau_intervention(int niveau_intervention) {
		this.niveau_intervention = niveau_intervention;
	}
	public int getEtat_intervention() {
		return etat_intervention;
	}
	public void setEtat_intervention(int etat_intervention) {
		this.etat_intervention = etat_intervention;
	}
	public String getDuree_intervention() {
		return duree_intervention;
	}
	public void setDuree_intervention(String duree_intervention) {
		this.duree_intervention = duree_intervention;
	}	
	public Operateur getOperateur() {
		return operateur;
	}
	
	public void setOperateur(Operateur operateur) {
		this.operateur = operateur;
	}
	public String getCommentIntervention() {
		return commentIntervention;
	}
	public void setCommentIntervention(String commentIntervention) {
		this.commentIntervention = commentIntervention;
	}
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	public String getDate_ouverture() {
		return date_ouverture;
	}
	public void setDate_ouverture(String date_ouverture) {
		this.date_ouverture = date_ouverture;
	}
	public String getDate_cloture() {
		return date_cloture;
	}
	public void setDate_cloture(String date_cloture) {
		this.date_cloture = date_cloture;
	}
	public String getDate_intervention() {
		return date_intervention;
	}
	public void setDate_intervention(String date_intervention) {
		this.date_intervention = date_intervention;
	}
	public String getObservation_intervention() {
		return observation_intervention;
	}
	public void setObservation_intervention(String observation_intervention) {
		this.observation_intervention = observation_intervention;
	}
	@JsonIgnore
	public List<Model> getModels() {
		return models;
	}
	public void setModels(List<Model> models) {
		this.models = models;
	}
	
	public String getDate_demande() {
		return date_demande;
	}
	public void setDate_demande(String date_demande) {
		this.date_demande = date_demande;
	}
	public Intervention() {
		super();
	}
	public int getIdModel() {
		return idModel;
	}
	public void setIdModel(int idModel) {
		this.idModel = idModel;
	}
	public Intervention(String titre_intervention, String commentIntervention, int niveau_intervention,
			int etat_intervention, String duree_intervention, String date_ouverture, String date_intervention,
			String date_cloture, String observation_intervention) {
		super();
		this.titre_intervention = titre_intervention;
		this.commentIntervention = commentIntervention;
		this.niveau_intervention = niveau_intervention;
		this.etat_intervention = etat_intervention;
		this.duree_intervention = duree_intervention;
		this.date_ouverture = date_ouverture;
		this.date_intervention = date_intervention;
		this.date_cloture = date_cloture;
		this.observation_intervention = observation_intervention;
	}
	
}
