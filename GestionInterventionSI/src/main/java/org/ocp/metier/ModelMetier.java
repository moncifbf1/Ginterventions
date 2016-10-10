package org.ocp.metier;

import java.util.List;

import org.ocp.entities.Fournisseur;
import org.ocp.entities.Model;

public interface ModelMetier {
	public Model saveModel(Model m);
	public Model updateModel(Model m);
	public boolean deleteModel(int idModel);
	public List<Model> listModels();
	public List<Model> listModelsByFournisseur(String nom);
	public boolean addFournisseurModel(Model m , Fournisseur f);
	public Model getModel(int nom);
}
