package org.ocp.metier;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.ocp.dao.InterventionRepository;
import org.ocp.entities.Intervention;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InterventionMetierImpl implements InterventionMetier{
	@Autowired
	private InterventionRepository interventionRepository;

	@Override
	public Intervention saveIntervention(Intervention intervention) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String strDate = dateFormat.format(date);
		intervention.setDate_demande(strDate);
		return interventionRepository.save(intervention);
	}

	@Override
	public Intervention updateIntervention(Intervention intervention) {
		Intervention i = interventionRepository.findOne(intervention.getId_intervention());
		i.setEtat_intervention(intervention.getEtat_intervention());
		i.setNiveau_intervention(intervention.getNiveau_intervention());
		return  interventionRepository.saveAndFlush(i);
	}

	@Override
	public boolean deleteIntervention(int idIntervention) {
		interventionRepository.delete(idIntervention);
		return true;
	}

	@Override
	public List<Intervention> listeInterventions() {
		return interventionRepository.findAll();
	}

	@Override
	public List<Intervention> interventionsByOperateur(String codeOperateur) {
		return interventionRepository.findInterventionByCode(codeOperateur);
	}

	@Override
	public Intervention interventionsById(int idIntervention) {
		return interventionRepository.findOne(idIntervention);
	}

	@Override
	public Intervention affecterIntervention(Intervention intervention) {
		Intervention i = interventionRepository.findOne(intervention.getId_intervention());
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String strDate = dateFormat.format(date);
		i.setDate_ouverture(strDate);
		i.setOperateur(intervention.getOperateur());
		return interventionRepository.saveAndFlush(i);
	}

	@Override
	public Intervention intervenir(Intervention intervention) {
		Intervention i = interventionRepository.findOne(intervention.getId_intervention());
		i.setEtat_intervention(1);
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String strDate = dateFormat.format(date);
		i.setDate_intervention(strDate);
		interventionRepository.saveAndFlush(i);
		return i;
	}
	
	@Override
	public Intervention terminer(Intervention intervention) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String strDate = dateFormat.format(date);
		Intervention i = interventionRepository.findOne(intervention.getId_intervention());
		i.setDate_cloture(strDate);
		i.setObservation_intervention(intervention.getObservation_intervention());
		i.setNiveau_intervention(intervention.getNiveau_intervention());
		i.setEtat_intervention(2);
		interventionRepository.saveAndFlush(i);
		return i;
	}

	@Override
	public List<Intervention> EnAttenteIntervs() {
		return interventionRepository.lastEnAttenteIntervs();
	}
	

	@Override
	public List<Intervention> EnCoursIntervs() {
		return interventionRepository.lastEnCoursIntervs();
	}

	@Override
	public List<Intervention> TermineeIntervs() {
		return interventionRepository.lastTermineesIntervs();
	}

	@Override
	public List<Intervention> EnAttenteIntervsOper(String codeOperateur) {
		return interventionRepository.lastEnAttenteIntervsOper(codeOperateur);
	}

	@Override
	public List<Intervention> EnCoursIntervsOper(String codeOperateur) {
		return interventionRepository.lastEnCoursIntervsOper(codeOperateur);
	}

	@Override
	public List<Intervention> TermineeIntervsOper(String codeOperateur) {
		return interventionRepository.lastTermineesIntervsOper(codeOperateur);
	}

	@Override
	public Intervention passerNiveau(Intervention intervention) {
		Intervention i = interventionRepository.findOne(intervention.getId_intervention());
		i.setNiveau_intervention(intervention.getNiveau_intervention()+1);
		interventionRepository.saveAndFlush(i);
		return i;
	}

	@Override
	public List<Intervention> searchNiveauEtat(int niveau, int etat) {
		return interventionRepository.searchNiveauEtat(niveau, etat);
	}

	@Override
	public List<Intervention> searchNiveauEtatDateDemande(int niveau, int etat, String dateDemande) {
		return interventionRepository.searchNiveauEtatDateDemande(niveau, etat, dateDemande);
	}

	@Override
	public List<Intervention> searchDate(String dateDemande) {
		return interventionRepository.searchDate(dateDemande);
	}

	@Override
	public List<Intervention> searchEtat(int etat) {
		return interventionRepository.searchEtat(etat);
	}

	@Override
	public List<Intervention> searchEtatDate(int etat, String dateDemande) {
		return interventionRepository.searchEtatDate(etat, dateDemande);
	}

	@Override
	public List<Intervention> searchNiveau(int niveau) {
		return interventionRepository.searchNiveau(niveau);
	}

	@Override
	public List<Intervention> searchNiveauDate(int niveau, String dateDemande) {
		return interventionRepository.searchNiveauDate(niveau, dateDemande);
	}

	@Override
	public Intervention terminerOpFour(Intervention intervention) {
		Intervention i = interventionRepository.findOne(intervention.getId_intervention());
		i.setEtat_intervention(2);
		i.setNiveau_intervention(4);
		i.setObservation_intervention(intervention.getObservation_intervention());
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String strDate = dateFormat.format(date);
		i.setDate_cloture(strDate);
		interventionRepository.saveAndFlush(i);
		return i;
	}

	@Override
	public List<Intervention> interventionsByOperateurState(String codeOperateur, int etat) {
		return interventionRepository.findInterventionByCodeState(codeOperateur, etat);
	}
	
	
	
}
