package org.ocp.metier;

import java.util.List;

import org.ocp.dao.InterventionRepository;
import org.ocp.dao.OperateurRepository;
import org.ocp.dao.ResponsableRepository;
import org.ocp.entities.Intervention;
import org.ocp.entities.Operateur;
import org.ocp.entities.Responsable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ResponsableMetierImpl implements ResponsableMetier {
	@Autowired
	private ResponsableRepository responsableRepository;
	@Autowired
	private InterventionRepository interventionRepository;
	@Autowired
	private OperateurRepository operateurRepository;
	
	@Override
	public boolean affecterIntervention(int idIntervention, int idOperateur) {
		Operateur o = operateurRepository.findOne(idOperateur);
		Intervention i = interventionRepository.findOne(idIntervention);
		i.setOperateur(o);
		return true;
	}

	@Override
	public boolean desaffecterIntervention(int idIntervention) {
		Intervention i = interventionRepository.findOne(idIntervention);
		i.setOperateur(null);
		return true;
	}

	@Override
	public List<Responsable> listeResponsables() {
		return responsableRepository.findAll();
	}

	@Override
	public Responsable getResponsableByCode(String code_responsable) {
		return responsableRepository.findResponsableByCode(code_responsable);
	}
}
