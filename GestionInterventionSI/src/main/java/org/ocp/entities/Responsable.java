package org.ocp.entities;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("RE")
public class Responsable extends User implements Serializable{

	public Responsable() {
		super();
	}
	
}
