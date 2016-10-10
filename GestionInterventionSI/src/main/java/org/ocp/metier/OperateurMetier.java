package org.ocp.metier;

import java.util.List;

import org.ocp.entities.Operateur;

public interface OperateurMetier {
	public boolean demarrerIntervention(int idIntervention);
	public boolean finirIntervention(int idIntervention);
	public List<Operateur> listOperateurs();
	public Operateur getOperateurByCode(String code_operateur);
}
