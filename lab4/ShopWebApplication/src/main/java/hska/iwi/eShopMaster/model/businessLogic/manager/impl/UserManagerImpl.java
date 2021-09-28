package hska.iwi.eShopMaster.model.businessLogic.manager.impl;

import hska.iwi.eShopMaster.model.businessLogic.manager.UserManager;
import hska.iwi.eShopMaster.model.database.dataAccessObjects.RoleDAO;
import hska.iwi.eShopMaster.model.database.dataAccessObjects.UserDAO;
import hska.iwi.eShopMaster.model.database.dataobjects.Role;
import hska.iwi.eShopMaster.model.database.dataobjects.User;

import hska.iwi.eShopMaster.controller.microservices.UserServiceAction;
import hska.iwi.eShopMaster.model.businessLogic.manager.impl.microservices.MicroUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;


@SuppressWarnings("deprecation")
public class UserManagerImpl implements UserManager {
	UserDAO helper;

	UserServiceAction userServiceAction = new UserServiceAction();
//	AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext("hska.iwi.eShopMaster");
//	OAuth2RestTemplate bean = (OAuth2RestTemplate) ctx.getBean("restTemplate");
	public UserManagerImpl() {
		helper = new UserDAO();
	}

	public void registerUser(String username, String name, String lastname, String password, Role role) {

		User user = new User(username, name, lastname, password, role);
		helper.saveObject(user);
	}

	public User getUserByUsername(String username) {
		if (username == null || username.equals("")) {
			return null;
		}
//		--- test
		MicroUser[] users = userServiceAction.getAllUsers();
//		 --- test
		return helper.getUserByUsername(username);
	}

	public boolean deleteUserById(int id) {
		User user = new User();
		user.setId(id);
		helper.deleteObject(user);
		return true;
	}

	public Role getRoleByLevel(int level) {
		RoleDAO roleHelper = new RoleDAO();
		return roleHelper.getRoleByLevel(level);
	}

	public boolean doesUserAlreadyExist(String username) {
		
    	User dbUser = this.getUserByUsername(username);
    	
    	if (dbUser != null){
    		return true;
    	}
    	else {
    		return false;
    	}
	}

	public boolean validate(User user) {
		if (user.getFirstname().isEmpty() || user.getPassword().isEmpty() || user.getRole() == null || user.getLastname() == null || user.getUsername() == null) {
			return false;
		}
		
		return true;
	}

}
