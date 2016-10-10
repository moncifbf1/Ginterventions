package org.ocp.entities;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("AD")
public class Administrateur extends User implements Serializable{

	public Administrateur() {
		super();
	}

		
}
