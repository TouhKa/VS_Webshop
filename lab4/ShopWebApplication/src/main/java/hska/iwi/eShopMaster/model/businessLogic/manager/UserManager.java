package hska.iwi.eShopMaster.model.businessLogic.manager;

import hska.iwi.eShopMaster.model.businessLogic.manager.impl.microservices.User;
import hska.iwi.eShopMaster.model.database.dataobjects.Role;


public interface UserManager {
    
    public void registerUser(String username, String name, String lastname, String password, Role role);
    
    public User getUserByUsername(String username);
    
    public boolean deleteUserById(String id);
    
    public Role getRoleByLevel(int level);
    
    public boolean doesUserAlreadyExist(String username);

    public User searchUser(User[] users, String password);
}
