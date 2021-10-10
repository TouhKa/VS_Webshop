package hska.iwi.eShopMaster.model.businessLogic.manager.impl;

import hska.iwi.eShopMaster.model.businessLogic.manager.UserManager;
import hska.iwi.eShopMaster.model.database.dataAccessObjects.RoleDAO;
import hska.iwi.eShopMaster.model.database.dataAccessObjects.UserDAO;
import hska.iwi.eShopMaster.model.database.dataobjects.Role;
import hska.iwi.eShopMaster.controller.microservices.UserServiceAction;
import hska.iwi.eShopMaster.model.businessLogic.manager.impl.microservices.User;

@SuppressWarnings("deprecation")
public class UserManagerImpl implements UserManager {
	UserDAO helper;
	UserServiceAction userServiceAction = new UserServiceAction();
	public UserManagerImpl() {
		helper = new UserDAO();
	}

	public void registerUser(String username, String name, String lastname, String password, Role role) {

		hska.iwi.eShopMaster.model.database.dataobjects.User user = new hska.iwi.eShopMaster.model.database.dataobjects.User(username, name, lastname, password, role);
		helper.saveObject(user);
	}

	public hska.iwi.eShopMaster.model.database.dataobjects.User getUserByUsername(String username) {
		if (username == null || username.equals("")) {
			return null;
		}
//		--- test
		User[] users = (User[]) userServiceAction.getAllUsers();
//		 --- test
		return helper.getUserByUsername(username);
	}

	public boolean deleteUserById(int id) {
		hska.iwi.eShopMaster.model.database.dataobjects.User user = new hska.iwi.eShopMaster.model.database.dataobjects.User();
		user.setId(id);
		helper.deleteObject(user);
		return true;
	}

	public Role getRoleByLevel(int level) {
		RoleDAO roleHelper = new RoleDAO();
		return roleHelper.getRoleByLevel(level);
	}

	public boolean doesUserAlreadyExist(String username) {

		hska.iwi.eShopMaster.model.database.dataobjects.User dbUser = this.getUserByUsername(username);
    	
    	if (dbUser != null){
    		return true;
    	}
    	else {
    		return false;
    	}
	}

	public boolean validate(hska.iwi.eShopMaster.model.database.dataobjects.User user) {
		if (user.getFirstname().isEmpty() || user.getPassword().isEmpty() || user.getRole() == null || user.getLastname() == null || user.getUsername() == null) {
			return false;
		}
		
		return true;
	}

}
