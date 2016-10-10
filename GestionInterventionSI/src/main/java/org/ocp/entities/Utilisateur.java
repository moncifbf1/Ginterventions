package org.ocp.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@DiscriminatorValue("US")
public class Utilisateur extends User implements Serializable{
	@OneToMany(mappedBy="utilisateur", fetch=FetchType.LAZY, cascade = CascadeType.REMOVE)
	private Collection<Intervention> interventions;
	
	@JsonIgnore
	@XmlTransient
	public Collection<Intervention> getInterventions() {
		return interventions;
	}
	public void setInterventions(Collection<Intervention> interventions) {
		this.interventions = interventions;
	}
	public Utilisateur() {
		super();
	}
}
