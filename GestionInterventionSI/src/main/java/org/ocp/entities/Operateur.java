package org.ocp.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@DiscriminatorValue("OP")
public class Operateur extends User implements Serializable{
	@OneToMany(mappedBy="operateur", fetch=FetchType.LAZY)
	private Collection<Intervention> interventions;
	
	@JsonIgnore
	@XmlTransient
	public Collection<Intervention> getInterventions() {
		return interventions;
	}
	public void setInterventions(Collection<Intervention> interventions) {
		this.interventions = interventions;
	}
	public Operateur() {
		super();
	}
	
}
