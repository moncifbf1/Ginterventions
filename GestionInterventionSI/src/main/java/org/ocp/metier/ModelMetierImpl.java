package org.ocp.metier;

import java.util.List;

import org.ocp.dao.ModelRepository;
import org.ocp.entities.Fournisseur;
import org.ocp.entities.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModelMetierImpl implements ModelMetier{
	@Autowired
	private ModelRepository modelRepository;

	@Override
	public Model saveModel(Model m) {
		return modelRepository.save(m);
	}

	@Override
	public Model updateModel(Model m) {
		Model model = modelRepository.findOne(m.getId_model());
		model.setDescription_model(m.getDescription_model());
		model.setNom_model(m.getNom_model());
		model.setFournisseur(m.getFournisseur());
		modelRepository.saveAndFlush(model);
		return model;
	}

	@Override
	public boolean deleteModel(int idModel) {
		modelRepository.delete(idModel);
		return true;
	}

	@Override
	public List<Model> listModels() {
		return modelRepository.findAll();
	}

	@Override
	public Model getModel(int nom) {
		Model m = modelRepository.findOne(nom);
		return m;
	}

	@Override
	public boolean addFournisseurModel(Model m, Fournisseur f) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Model> listModelsByFournisseur(String nom) {
		return modelRepository.findModelbyFournisseur(nom);
	}
	
}
