package org.ocp.metier;

import java.util.List;

import org.ocp.entities.Intervention;

public interface InterventionMetier {
	public Intervention saveIntervention(Intervention intervention);
	public Intervention updateIntervention(Intervention intervention);
	public boolean deleteIntervention(int idIntervention);
	public List<Intervention> listeInterventions();
	public List<Intervention> interventionsByOperateur(String codeOperateur);
	public List<Intervention> interventionsByOperateurState(String codeOperateur, int etat);
	public Intervention interventionsById(int idIntervention);
	public Intervention affecterIntervention(Intervention intervention);
	public Intervention intervenir(Intervention intervention);
	public Intervention terminer(Intervention intervention);
	public Intervention terminerOpFour(Intervention intervention);
	public List<Intervention> EnAttenteIntervs();
	public List<Intervention> EnCoursIntervs();
	public List<Intervention> TermineeIntervs();
	public List<Intervention> EnAttenteIntervsOper(String codeOperateur);
	public List<Intervention> EnCoursIntervsOper(String codeOperateur);
	public List<Intervention> TermineeIntervsOper(String codeOperateur);
	public Intervention passerNiveau(Intervention intervention);
	
	//SEARCH METHODES
	public List<Intervention> searchDate(String dateDemande);
	public List<Intervention> searchEtat(int etat);
	public List<Intervention> searchEtatDate(int etat, String dateDemande);
	public List<Intervention> searchNiveau(int niveau);
	public List<Intervention> searchNiveauDate(int niveau, String dateDemande);
	public List<Intervention> searchNiveauEtat(int niveau, int etat);
	public List<Intervention> searchNiveauEtatDateDemande(int niveau, int etat, String dateDemande);
}
