package hska.iwi.eShopMaster.model.businessLogic.manager.impl;
import hska.iwi.eShopMaster.model.businessLogic.manager.UserManager;
import hska.iwi.eShopMaster.model.data.objects.Role;
import hska.iwi.eShopMaster.model.services.RoleService;
import hska.iwi.eShopMaster.model.services.UserService;
import hska.iwi.eShopMaster.model.services.impl.RoleServiceImpl;
import hska.iwi.eShopMaster.model.services.impl.UserServiceImpl;
import hska.iwi.eShopMaster.model.data.objects.User;
import hska.iwi.eShopMaster.model.util.DockerLogger;

@SuppressWarnings("deprecation")
public class UserManagerImpl implements UserManager {

	DockerLogger logger = new DockerLogger(UserManagerImpl.class.getSimpleName());
	private final UserService userService;
	private final RoleService roleService;
	
	public UserManagerImpl() {
		userService = new UserServiceImpl();
		roleService = new RoleServiceImpl();
	}

	public void registerUser(String username, String name, String lastname, String password, Role role) {
		User newUser = new User(role.getId(), name, lastname, username, password);
		userService.addUser(newUser);
	}

	public User searchUser(User[] users, String username){
		try {
			for (User user : users) {
				if (user.getUsername().equals(username)) {
					return user;
					
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
		User[] users = userService.getAllUsers();
		return searchUser(users, username);
	}

	public User getUserByPasswordCredentials(String username, String password) {
		try {
			if (username == null || username.equals("")) {
				return null;
			}
			User[] users = userService.getLoginUser(username, password);
			return searchUser(users, username);
		}catch (Exception e) {
			return null;
		}
	}

	public Role getRoleByLevel(int level) {
		return roleService.getRoleByLevel(level);
	}

	public boolean doesUserAlreadyExist(String username) {
		User dbUser = this.getUserByUsername(username);
    	if (dbUser.getFirstname() != null && !dbUser.getFirstname().equals("fallback")) {
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
