package org.ocp.metier;

import java.util.Collection;
import java.util.List;

import org.ocp.dao.FournisseurRepository;
import org.ocp.entities.Fournisseur;
import org.ocp.entities.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FournisseurMetierImpl implements FournisseurMetier{
	@Autowired
	private FournisseurRepository fournisseurRepository;
	
	@Override
	public Fournisseur saveFournisseur(Fournisseur f) {
		return fournisseurRepository.save(f);
	}

	@Override
	public Fournisseur updateFournisseur(Fournisseur f) {
		Fournisseur fournisseur = fournisseurRepository.findOne(f.getId_fournisseur());
		fournisseur.setNom_fournisseur(f.getNom_fournisseur());
		fournisseur.setTel_fournisseur(f.getTel_fournisseur());
		fournisseur.setMail_fournisseur(f.getMail_fournisseur());
		fournisseur.setModels(f.getModels());
		fournisseurRepository.saveAndFlush(fournisseur);
		return fournisseur;
	}

	@Override
	public boolean deleteFournisseur(int id_fournisseur) {
		fournisseurRepository.delete(id_fournisseur);
		return true;
	}

	@Override
	public List<Fournisseur> listFournisseurs() {
		return fournisseurRepository.findAll();
	}

	@Override
	public boolean addModelToFournisseur(Fournisseur f, Model m) {
		Fournisseur fournisseur = fournisseurRepository.findOne(f.getId_fournisseur());
		fournisseur.getModels().add(m);
		return true;
	}

	@Override
	public boolean deleteModelFromFournisseur(Fournisseur f, Model m) {
		Fournisseur fournisseur = fournisseurRepository.findOne(f.getId_fournisseur());
		Collection<Model> models = fournisseur.getModels();
		for(Model model : models){
		    if(model.getId_model() == m.getId_model()){
		        models.remove(model);
		    }
		}
		return true;
	}

	@Override
	public Fournisseur findFournisseurByNom(String nom) {
		return fournisseurRepository.findFournisseurByNom(nom);
	}

	@Override
	public Fournisseur findFournisseurById(int idFournisseur) {
		return fournisseurRepository.findOne(idFournisseur);
	}

	
}
