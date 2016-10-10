package org.ocp.metier;

import java.util.List;

import org.ocp.entities.Responsable;

public interface ResponsableMetier {
	public boolean affecterIntervention(int idIntervention, int idOperateur);
	public boolean desaffecterIntervention(int idIntervention);
	public List<Responsable> listeResponsables();
	public Responsable getResponsableByCode(String code_responsable);
}
