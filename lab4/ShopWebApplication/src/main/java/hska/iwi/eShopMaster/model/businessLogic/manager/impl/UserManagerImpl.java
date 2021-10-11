package hska.iwi.eShopMaster.model.businessLogic.manager.impl;

import hska.iwi.eShopMaster.controller.LoginAction;
import hska.iwi.eShopMaster.model.businessLogic.manager.UserManager;
import hska.iwi.eShopMaster.model.database.dataAccessObjects.RoleDAO;
import hska.iwi.eShopMaster.model.database.dataAccessObjects.UserDAO;
import hska.iwi.eShopMaster.model.database.dataobjects.Role;
import hska.iwi.eShopMaster.controller.microservices.UserServiceAction;
import hska.iwi.eShopMaster.model.businessLogic.manager.impl.microservices.User;
import hska.iwi.eShopMaster.model.util.DockerLogger;

@SuppressWarnings("deprecation")
public class UserManagerImpl implements UserManager {
	UserDAO helper;
	DockerLogger logger = new DockerLogger(UserManagerImpl.class.getSimpleName());

	UserServiceAction userServiceAction = new UserServiceAction();
	public UserManagerImpl() {
		helper = new UserDAO();
	}

	public void registerUser(String username, String name, String lastname, String password, Role role) {
		User newUser = new User(role.getId(), name, lastname, username, password);
		User newUserAnswer =  userServiceAction.addUser(newUser);
		logger.write("New User: " +newUserAnswer.getUsername());
	}

	public User searchUser(User[] users, String username){
		try {
			for (int i = 0; i < users.length; i++) {
				logger.write(users[i].getUsername() + ", " + users[i].getPassword());
				logger.close();
				if (users[i].getUsername().equals(username)) {
					return users[i];

				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return new User(1, "fallback", "fallback", "fallback", "fallback");
	}

	public User getUserByUsername(String username) {
		if (username == null || username.equals("")) {
			return null;
		}
		User[] users = userServiceAction.getAllUsers();
		return searchUser(users, username);
	}

	//TODO this method is never implemented in the legacy webshop
	public boolean deleteUserById(String id) {
		hska.iwi.eShopMaster.model.database.dataobjects.User user = new hska.iwi.eShopMaster.model.database.dataobjects.User();
		user.setId(Integer.parseInt(id));
		helper.deleteObject(user);
		userServiceAction.deleteUser(id);
		return true;
	}
	//TODO delete as not needed anymore
	public Role getRoleByLevel(int level) {
		RoleDAO roleHelper = new RoleDAO();
		return roleHelper.getRoleByLevel(level);
	}

	public boolean doesUserAlreadyExist(String username) {
		User dbUser = this.getUserByUsername(username);
    	
    	if ((dbUser.getFirstname() == null )&& (dbUser.getFirstname() != "fallback")){
			logger.write("User " + username + " is existing");
			logger.close();
    		return true;
    	}
    	else {
			logger.write("User " + username + " is not existing");
			logger.close();
    		return false;
    	}
	}

}
