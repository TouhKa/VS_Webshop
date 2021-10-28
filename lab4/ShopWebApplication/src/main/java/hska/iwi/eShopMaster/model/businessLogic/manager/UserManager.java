package hska.iwi.eShopMaster.model.businessLogic.manager;

import hska.iwi.eShopMaster.model.data.objects.User;
import hska.iwi.eShopMaster.model.data.objects.Role;


public interface UserManager {
    
    void registerUser(String username, String name, String lastname, String password, Role role);
    
    User getUserByUsername(String username);
    
    Role getRoleByLevel(int level);
    
    boolean doesUserAlreadyExist(String username);

    User searchUser(User[] users, String password);
}
