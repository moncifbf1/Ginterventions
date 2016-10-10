package org.ocp.service;

import java.util.List;
import org.ocp.dao.RoleRepository;
import org.ocp.entities.Administrateur;
import org.ocp.entities.Operateur;
import org.ocp.entities.Responsable;
import org.ocp.entities.Role;
import org.ocp.entities.User;
import org.ocp.entities.Utilisateur;
import org.ocp.metier.UserMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRestService {

	@Autowired
	private UserMetier userMetier;

	@Autowired
	private RoleRepository roleRepository;
	
	@RequestMapping(value="/searchUser/{search:.+}",method=RequestMethod.GET)
	public List<User> seachUsers(@PathVariable("search") String search) {
		return userMetier.seachUsers(search);
	}
	
	@RequestMapping(value="/utilisateur/{username:.+}",method=RequestMethod.GET)
	public User findUser(@PathVariable("username") String username) {
		return userMetier.findUser(username);
	}
	
	@Secured(value={"ROLE_ADMINISTRATEUR"})
	@RequestMapping(value="/countOperateurs", method=RequestMethod.GET)
	public int countOperateurs() {
		return userMetier.countOperateurs();
	}

	@Secured(value={"ROLE_ADMINISTRATEUR"})
	@RequestMapping(value="/administrateurs")
	public List<Administrateur> getAdministrateurs() {
		return userMetier.getAdministrateurs();
	}
	@Secured(value={"ROLE_RESPONSABLE","ROLE_ADMINISTRATEUR"})
	@RequestMapping(value="/operateurs",method=RequestMethod.GET)
	public List<Operateur> getOperateurs() {
		return userMetier.getOperateurs();
	}
	@Secured(value={"ROLE_ADMINISTRATEUR","ROLE_RESPONSABLE"})
	@RequestMapping(value="/utilisateurs")
	public List<Utilisateur> getUtilisateurs() {
		return userMetier.getUtilisateurs();
	}
	@Secured(value={"ROLE_ADMINISTRATEUR"})
	@RequestMapping(value="/responsables")
	public List<Responsable> getResponsables(){
		return userMetier.getResponsables();
	}
	@Secured(value={"ROLE_ADMINISTRATEUR"})
	@RequestMapping(value="/updateUser", method=RequestMethod.PUT)
	public @ResponseBody User updateUser(@RequestBody User user) {
		return userMetier.updateUser(user);
	}
	@Secured(value={"ROLE_ADMINISTRATEUR"})
	@RequestMapping(value="/utilisateurs/{username:.+}", method=RequestMethod.DELETE)
	public void deleteUser(@PathVariable("username") String username) {
		userMetier.deleteUser(username);
	}
	@Secured(value={"ROLE_ADMINISTRATEUR"})
	@RequestMapping(value="/addUser",method=RequestMethod.POST)
	public @ResponseBody User saveUser(@RequestBody User u){
		return userMetier.saveUser(u);
	}
	@Secured(value={"ROLE_ADMINISTRATEUR","ROLE_OPERATEUR","ROLE_RESPONSABLE"})
	@RequestMapping(value="/findUsers")
	public List<User> findAllUsers(){
		return userMetier.findAllUsers();
	}	
	@Secured(value={"ROLE_ADMINISTRATEUR"})
	@RequestMapping(value="/roles")
	public List<Role> getRoles(){
		return roleRepository.findAll();
	}	
	
	@RequestMapping(value="/findRestrictedUsers/{username:.+}")
	public List<User> findRestrictedUsers(@PathVariable("username") String username) {
		return userMetier.findRestrictedUsers(username);
	}
	
	
}
