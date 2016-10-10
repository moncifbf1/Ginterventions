package org.ocp.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Message implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id_message;
	private String objet_message;
	private String text_message;
	private String date_message;
	private String heure_message;
	private String codeEmetteur;
	private String codeDestinataire;
	private int etat_message;
	
	public int getId_message() {
		return id_message;
	}
	public void setId_message(int id_message) {
		this.id_message = id_message;
	}
	public String getObjet_message() {
		return objet_message;
	}
	public void setObjet_message(String objet_message) {
		this.objet_message = objet_message;
	}
	public String getText_message() {
		return text_message;
	}
	public void setText_message(String text_message) {
		this.text_message = text_message;
	}
	public String getDate_message() {
		return date_message;
	}
	public void setDate_message(String date_message) {
		this.date_message = date_message;
	}
	public String getHeure_message() {
		return heure_message;
	}
	public void setHeure_message(String heure_message) {
		this.heure_message = heure_message;
	}
	public String getCodeEmetteur() {
		return codeEmetteur;
	}
	public void setCodeEmetteur(String codeEmetteur) {
		this.codeEmetteur = codeEmetteur;
	}
	public String getCodeDestinataire() {
		return codeDestinataire;
	}
	public void setCodeDestinataire(String codeDestinataire) {
		this.codeDestinataire = codeDestinataire;
	}
	
	public int getEtat_message() {
		return etat_message;
	}
	public void setEtat_message(int etat_message) {
		this.etat_message = etat_message;
	}
	public Message(String objet_message, String text_message, String date_message, String heure_message,
			String codeEmetteur, String codeDestinataire) {
		super();
		this.objet_message = objet_message;
		this.text_message = text_message;
		this.date_message = date_message;
		this.heure_message = heure_message;
		this.codeEmetteur = codeEmetteur;
		this.codeDestinataire = codeDestinataire;
	}
	public Message() {
		super();
		// TODO Auto-generated constructor stub
	}
}
