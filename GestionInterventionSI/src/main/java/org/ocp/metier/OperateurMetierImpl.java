package org.ocp.metier;


import java.util.Date;
import java.util.List;
import org.ocp.dao.InterventionRepository;
import org.ocp.dao.OperateurRepository;
import org.ocp.entities.Intervention;
import org.ocp.entities.Operateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OperateurMetierImpl implements OperateurMetier{
	@Autowired
	private OperateurRepository operateurRepository;
	@Autowired
	private InterventionRepository interventionRepository;
	
	@Override
	public boolean demarrerIntervention(int idIntervention) {
		Intervention i = interventionRepository.findOne(idIntervention);
		i.setDate_intervention(new Date().toString());
		i.setEtat_intervention(1);
		interventionRepository.saveAndFlush(i);
		return true;
	}

	@Override
	public boolean finirIntervention(int idIntervention) {
		Intervention i = interventionRepository.findOne(idIntervention);
//		String start = i.getHoraire_intervention();
//		Date end = new Date();
//		long diff = end.getTime() - start.getTime();
//		long diffSeconds = diff / 1000;         
//		long diffMinutes = diff / (60 * 1000);         
//		long diffHours = diff / (60 * 60 * 1000);
//		i.setDuree_intervention(diffHours+"h"+diffMinutes+"m"+diffSeconds+"s");
//		i.setEtat_intervention(2);
//		interventionRepository.saveAndFlush(i);
		return true;
	}

	@Override
	public List<Operateur> listOperateurs() {
		return operateurRepository.findAll();
	}

	@Override
	public Operateur getOperateurByCode(String code_operateur) {
		return operateurRepository.findOperateurByCode(code_operateur);
	}

}
